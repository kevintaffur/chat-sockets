package com.mycompany.chat.sockets;

/**
 *
 * @author Belén
 */
public interface interfaceSocketServer {
    void join(String name);
 void exit(String name);
 void log(String message);
 void HistorialMensajes(String message);
    
}
