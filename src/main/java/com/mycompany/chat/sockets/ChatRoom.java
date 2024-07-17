package com.mycompany.chat.sockets;

import java.io.IOException;
import java.util.Map;
import java.util.HashMap;
import java.util.Collection;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.io.PrintWriter;
import java.util.Collections;
import javax.swing.JOptionPane;

/**
   Un chat room el cual consiste de m�ltiples chatters.
*/
public class ChatRoom
{
     private final List<ChatService> chatters = new ArrayList<>();
   private interfaceSocketServer socketServer;

   public ChatRoom(interfaceSocketServer socketServer) {
        this.socketServer = socketServer;
    }
   
   public void HistorialMensajes(String message) {
        // Aquí envía el mensaje a todos los clientes conectados
      socketServer.HistorialMensajes(message); // Envia el mensaje al historial de mensajes del servidor
       
    }
    public synchronized void add(ChatService cs) {
        chatters.add(cs);
    }
    
    public synchronized void remove(ChatService client) {
        chatters.remove(client);
        if (socketServer != null) {
            socketServer.exit(client.getUserName());
        } else {
            System.err.println("Error: serverMessageHandler is null in ChatRoom.remove()");
        }
        
        updateClientList();
    }
    public synchronized void broadcast(String requestor, String msg, ChatService chatService) {
        for (ChatService client : chatters) {
            if (client != chatService) {
                client.putMessage(requestor + ": " + msg);
            }
        }
    }
    public synchronized void register(String aName, ChatService service) {
        service.putMessage(aName + " se ha unido al chat.");
        socketServer.join(aName);
    }

    public synchronized void leave(String aName, ChatService service) {
        for (ChatService c : chatters) {
            if (c != service) {
                c.putMessage(aName + " se ha desconectado.");
            }
        }
        socketServer.exit(aName);
        updateClientList();
    }

    private void updateClientList() {
               if (socketServer != null) {
            List<String> clientNames = new ArrayList<>();
            for (ChatService client : chatters) {
                clientNames.add(client.getUserName());
            }
        } else {
            System.err.println("servidorSocket es nulo en SalaChat.actualizarListaUsuarios()");
        }
    }

    
    


   /**
      Registrar un chatter en el cuarto.
      @param aName el nombre a registrar
   */
   
 
}