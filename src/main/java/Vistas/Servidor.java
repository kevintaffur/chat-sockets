
package Vistas;

import com.mycompany.chat.sockets.ChatClient;

import com.mycompany.chat.sockets.ChatClient.interfaceSocketClient;
import com.mycompany.chat.sockets.ChatClient.interfaceSocketClient;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import com.mycompany.chat.sockets.ChatRoom;
import com.mycompany.chat.sockets.ChatServer;
import com.mycompany.chat.sockets.interfaceSocketServer;
import javax.swing.DefaultListModel;
import java.awt.Color;

/**
 *
 * @author Belén
 */
public final class Servidor extends javax.swing.JFrame {
    
    private ChatServer socketServer;
    private ChatClient clientSocket;
    private interfaceSocketServer messageSocketServer;
    private DefaultListModel<String> connectedUsersList;
    private ChatRoom chatRoom;
    public Servidor() {
   
        initComponents();
        txtAreaHConexiones.setEnabled(false);
        txtAreaHMensajes.setEditable(false);
        txtAreaHConexiones.setDisabledTextColor(Color.BLACK);
           txtAreaHMensajes.setDisabledTextColor(Color.BLACK);
        txtAreaHConexiones.setBackground(Color.white);
        
        connectedUsersList = new DefaultListModel<>();
        JListUsuariosConectados.setModel(connectedUsersList);
this.getContentPane().setBackground(Color.getHSBColor(0.5444f, 0.6126f, 0.4353f));

        messageSocketServer = createMessageSocketServer();
        chatRoom = new ChatRoom(messageSocketServer);
        
        System.out.println("Iniciando listeners");

            try {
                socketServer = new ChatServer(chatRoom);
                socketServer.startServer();
                System.out.println("Servidor iniciado correctamente");
            } catch (Throwable throwable) {
                handleServerError(throwable);
            }
    }
        public interfaceSocketServer createMessageSocketServer() {
        return new interfaceSocketServer() {
            @Override
            public void join(String name) {
                SwingUtilities.invokeLater(() -> {
                    if (name != null && !name.isEmpty()) {
                        connectedUsersList.addElement(name);
                    }
                    if (name != null && !name.isEmpty()) {
                        txtAreaHConexiones.append(name + " se ha conectado.\n");
                    }else{
                    
                    txtAreaHConexiones.append(" se ha conectado.\n");
                    }
                });
            }

            @Override
            public void exit(String name) {
                SwingUtilities.invokeLater(() -> {
                     connectedUsersList.removeElement(name);
                    
                    if (name != null && !name.isEmpty()) {
                        txtAreaHConexiones.append(name + " se ha desconectado.\n");
                    }
               
                
                        // Obtener el contenido actual del JTextArea
        String currentText = txtAreaHMensajes.getText();

        // Dividir el contenido en líneas
        String[] lines = currentText.split("\n");

        // Crear un nuevo StringBuilder para el nuevo contenido
        StringBuilder newText = new StringBuilder();

        // Recorrer las líneas y agregar todas excepto la que contiene el nombre
        for (String line : lines) {
            if (!line.contains(name)) {
                newText.append(line).append("\n");
            }
        }

        // Establecer el nuevo contenido en el JTextArea
        txtAreaHMensajes.setText(newText.toString());
                
                
                
                
                });
           
            
            
            
            
            
            
            
            
            
            }

            @Override
            public void log(String message) {
                SwingUtilities.invokeLater(() -> {
                    if (message != null && !message.isEmpty()) {
                        txtAreaHConexiones.append(message + "\n");
                    }
                });
            }
            
            @Override
            public void HistorialMensajes(String message) {
                SwingUtilities.invokeLater(() -> {
                    if (message != null && !message.isEmpty()) {
                        txtAreaHMensajes.append(message + "\n");
                        
                    }
                });
            }
        };
    }
    
    
   
    /**
     * Creates new form Server
     */
 
    
    
    private void handleServerError(Throwable throwable) {
        SwingUtilities.invokeLater(() -> {
        String errorMessage = "Error al iniciar el servidor:\n" + throwable.getMessage();
        JOptionPane.showMessageDialog(null, errorMessage);
        throwable.printStackTrace();
    });
    }


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        labelTituloServer = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtAreaHConexiones = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        JListUsuariosConectados = new javax.swing.JList<>();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtAreaHMensajes = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(204, 204, 204));

        labelTituloServer.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        labelTituloServer.setText("SERVIDOR");
        labelTituloServer.setToolTipText("");

        txtAreaHConexiones.setColumns(20);
        txtAreaHConexiones.setRows(5);
        jScrollPane1.setViewportView(txtAreaHConexiones);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setText("Estado de conexiones:");

        JListUsuariosConectados.setToolTipText("");
        jScrollPane2.setViewportView(JListUsuariosConectados);
        JListUsuariosConectados.getAccessibleContext().setAccessibleName("JListUsuarios");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setText("Usuarios conectados:");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setText("Historial de mensajes");

        txtAreaHMensajes.setColumns(20);
        txtAreaHMensajes.setRows(5);
        jScrollPane3.setViewportView(txtAreaHMensajes);
        txtAreaHMensajes.getAccessibleContext().setAccessibleName("t");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(40, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel3)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(152, 152, 152)
                            .addComponent(labelTituloServer))
                        .addComponent(jLabel1)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 363, Short.MAX_VALUE)
                        .addComponent(jScrollPane1)
                        .addComponent(jScrollPane3)))
                .addGap(55, 55, 55))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(labelTituloServer)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(86, 86, 86))
        );

        labelTituloServer.getAccessibleContext().setAccessibleName("labelTituloServer");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Servidor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Servidor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Servidor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Servidor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {

            new Servidor().setVisible(true);

        });

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JList<String> JListUsuariosConectados;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel labelTituloServer;
    private javax.swing.JTextArea txtAreaHConexiones;
    private javax.swing.JTextArea txtAreaHMensajes;
    // End of variables declaration//GEN-END:variables
    }
