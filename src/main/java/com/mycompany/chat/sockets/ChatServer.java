package com.mycompany.chat.sockets;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
   Un servidor que ejecuta el ChatService.
   Puede aceptar m�ltiples conexiones de m�ltiples clientes.
*/
public class ChatServer

{ 
    
    private ChatRoom chatRoom;
    private ExecutorService threadExecutor;
    private ServerSocket serverSocket;
    public ChatServer(ChatRoom chatRoom) {
        this.chatRoom = chatRoom;
        this.threadExecutor = Executors.newCachedThreadPool();
    }
    
    public void startServer() throws IOException {
    threadExecutor.submit(() -> {
        final int PORT = 8888;
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Server started on port " + PORT);
            while (!serverSocket.isClosed()) {
                Socket clientSocket = serverSocket.accept();
                handleClientConnection(clientSocket);
            }
        } catch (IOException ex) {
            Logger.getLogger(ChatServer.class.getName()).log(Level.SEVERE, "Error in server socket", ex);
        }
    });
}
    private void handleClientConnection(Socket clientSocket) {
    try {
        ChatService chatService = new ChatService(clientSocket, chatRoom);
        chatRoom.add(chatService);
        threadExecutor.execute(chatService);
    } catch (IOException ex) {
        Logger.getLogger(ChatServer.class.getName()).log(Level.SEVERE, "Error handling client connection", ex);
    }
}

}
