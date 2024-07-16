/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.chat.sockets;

/**
 *
 * @author Belén
 */
public interface interfaceSocketServer {
    void unirse(String name);
    void salir(String name);
    void log(String message);
    void HistorialMensajes(String message);
    
}
