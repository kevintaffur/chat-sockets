package com.mycompany.chat.sockets;

import java.io.InputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import javax.swing.JOptionPane;

/**
   Ejecuta los comandos de un protocolo de chat room 
   simple recibidos de un socket.
*/
public class ChatService implements Runnable
{
   private String userName;
   private Socket s;
   private ChatRoom chatRoom;
   private PrintWriter out;
   private BufferedReader in;
   private boolean loggedIn;
   

   /**
      Construye un objeto del servicio que procesa comandos
      de un socket para un chat room.
      @param aSocket el socket
      @param aChatRoom el chat room
   */
   



   public ChatService(Socket aSocket, ChatRoom aChatRoom)throws IOException
   {  
      this.s = aSocket;
      this.chatRoom = aChatRoom;
      loggedIn = false;
      this.out = new PrintWriter(aSocket.getOutputStream(), true);
      this.in = new BufferedReader(new InputStreamReader(aSocket.getInputStream()));
   }
 

   /**
      Ejecuta los comandos hasta recibir un LOGOUT o el fin de
      los datos de entrada.
   */
   public void principal()
   {
      try {
            while (true) {
                if (!in.ready()) continue;
                String line = in.readLine();
                int commandDelimiterPos = line.indexOf(' ');
                if (commandDelimiterPos < 0) commandDelimiterPos = line.length();
                String command = line.substring(0, commandDelimiterPos);
                line = line.substring(commandDelimiterPos);//.trim();
                String response = executeCommand(command, line);
                putMessage(response);
            }
        } catch (IOException e) {
            handleDisconnect();
        }
   }

   /**
      Env�a el mensaje a trav�s del socket.
      @param msg el mensaje a ser enviado
   */
   public void putMessage(String msg) 
   {
      if (out != null) 
      {
         out.println(msg); 
        out.flush();
      }
   }

   /**
      Ejecuta un comando.
      @param command el comando
      @param line el resto de la linea del comando
      @return la respuesta a ser enviada al cliente
   */
public String executeCommand(String command, String line) throws IOException {
    System.out.println("Comando recibido: " + command);  // Para depuración
    System.out.println("Mensaje recibido: " + line); 

    if (command.equals("LOGIN")) {
        return executeLogin(line);
    } else if (command.equals("CHAT")) {
        return executeChat(line);
    } else if (command.equals("LOGOUT")) {
        return executeLogout();
    } else {
        return executeInvalidCommand();
    }
}
     
 private String executeLogin(String username) throws IOException {
    if (loggedIn) {
        return "Administrador del chat room: Ya estás conectado como " + userName;
    }

    userName = username;
    chatRoom.register(userName, this);
    chatRoom.broadcast(userName, "LOGIN", this);
    loggedIn = true;
    return "Administrador del chat room: Hola, " + userName + ".";
}

private String executeChat(String message) throws IOException {
    if (!loggedIn) {
        return "Administrador del chat room: Debes hacer LOGIN primero";
    }

    chatRoom.broadcast(userName, message, this);
    chatRoom.HistorialMensajes(message);
    return userName + ": " + message;
}

private String executeLogout() throws IOException {
    if (!loggedIn) {
        handleDisconnect();
        return "Administrador del chat room: Debes hacer LOGIN primero";
    }

    chatRoom.broadcast(userName, "LOGOUT", this);
    chatRoom.leave(userName, this);
    loggedIn = false;
    userName = null;
    handleDisconnect();
    return "Adios!";
}

private String executeInvalidCommand() {
    handleDisconnect();
    return "Administrador del chat room: Comando inválido";
}
   /**
      Retorna el nombre del usuario de este servicio.
      @return el nombre del usuario de este servicio
   */
   public String getUserName()
   {
      return userName;
   }
   public void desconectar() {
        try {
            s.close();
        } catch (IOException e) {
            System.out.println("Error al cerrar el socket: " + e.getMessage());
        }
    }
  
    public void handleDisconnect() {
        chatRoom.leave(userName, this);
        desconectar();
        JOptionPane.showMessageDialog(null, "El servidor está cerrado!");
    }

    @Override
    public void run() {
        principal();
    }
}
