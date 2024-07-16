package com.mycompany.chat.sockets;

import java.io.InputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
   Programa cliente del chat.
*/
public class ChatClient
{
  private String nombre;   
 private Socket socketCliente;
    private final int PORT = 8888;
    private final String HOST = "localhost";
    private ExecutorService servicioEjecutor;
    private BufferedReader lectorMensajes;
    private PrintWriter escritorMensajes;
    private interfaceSocketClient interfazSocketCliente;
     private final Flag done = new Flag(false);

    
    public interface interfaceSocketClient {
        void mensajeRecibido(String mensaje);
        void servidorCerrado();
        void logout();
    }

    public ChatClient(){
        servicioEjecutor = Executors.newFixedThreadPool(2);
    }

    public void enviarMensajes(String mensaje) {
        if (lectorMensajes != null) {
            System.out.println("Enviando mensaje: " + mensaje);  
            escritorMensajes.println(mensaje);
        }
    }

    public void registrarMensajes(interfaceSocketClient messageSocketClient) {
        this.interfazSocketCliente = messageSocketClient;
    }

    public void logout() {        
        try {
            if (socketCliente != null && !socketCliente.isClosed()) {
                enviarMensajes("LOGOUT");
                escritorMensajes.close();
                lectorMensajes.close();
                socketCliente.close();
            }
        } catch (IOException e) {
            if (interfazSocketCliente != null) {
            }
        }
    }

    public void login(String nombre) throws IOException {
        this.nombre = nombre;
        socketCliente = new Socket(HOST, PORT);
        System.out.print("Usuario agregado "+ nombre+"\n");
        lectorMensajes = new BufferedReader(new InputStreamReader(socketCliente.getInputStream()));
        escritorMensajes = new PrintWriter(socketCliente.getOutputStream(), true);

        accionServidor();
    }

      private void accionServidor() {
        class OutputRunnable implements Runnable {
            public void run() {
                try {
                    String mensajeDesdeServidor;
                    while (!done.getFlag() && (mensajeDesdeServidor = lectorMensajes.readLine()) != null) {
                        if (interfazSocketCliente != null) {
                            interfazSocketCliente.mensajeRecibido(mensajeDesdeServidor);
                        }
                        
                    }
                } catch (IOException e) {
                    if (interfazSocketCliente != null) {
                        interfazSocketCliente.servidorCerrado();
                    }
                } finally {
                    if (interfazSocketCliente != null) {
                        interfazSocketCliente.logout();
                    }
                }
            }
        }

        OutputRunnable or = new OutputRunnable();
        Thread t = new Thread(or);
        t.start();
    }

}