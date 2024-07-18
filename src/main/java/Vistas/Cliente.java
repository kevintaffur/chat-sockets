/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Vistas;

import com.mycompany.chat.sockets.ChatClient;
import java.awt.Color;
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


/**
 *
 * @author Belén
 */
public class Cliente extends javax.swing.JFrame {

    private ChatClient clienteSocket;

    public Cliente() {
        initComponents();
        txtPanelChat.setEnabled(false);
        txtPanelChat.setDisabledTextColor(Color.BLACK);
        btnLogout.setEnabled(false);
        btnEnviarMensaje.setEnabled(false);
        clienteSocket = new ChatClient();
 this.getContentPane().setBackground(Color.getHSBColor(0.5444f, 0.6126f, 0.4353f));
        // Agrega un KeyListener al campo de texto
        txtNombreCliente.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!Character.isLetterOrDigit(c)) {
                    e.consume();
                }
            }

            @Override
            public void keyPressed(KeyEvent e) {
                // No necesitamos implementar este método, pero es necesario para la interfaz KeyListener
            }

            @Override
            public void keyReleased(KeyEvent e) {
                // No necesitamos implementar este método, pero es necesario para la interfaz KeyListener
            }
        });
    }

// Método para enviar un mensaje
    private void enviarMensaje() {
        String mensaje = txtMensajeCliente.getText().trim();
        String nuevoMensaje;
        if (!mensaje.isEmpty()) {
            nuevoMensaje =   txtNombreCliente.getText() + ": " + mensaje;
            clienteSocket.enviarMensajes("CHAT " + nuevoMensaje);
            txtPanelChat.setEditable(true);
            txtMensajeCliente.setEnabled(true);
            txtMensajeCliente.setText("");
        }

        System.out.print("Mensaje enviado\n");
    }
    
        private void enviarMensaje1() {
        String mensaje =  txtNombreCliente.getText() + ": " + txtMensajeCliente.getText().trim();
         
            clienteSocket.enviarMensajes("CHAT " + mensaje);
            txtPanelChat.setEditable(true);
            txtMensajeCliente.setEnabled(true);
            txtMensajeCliente.setText("");
    }

// Método para iniciar el cliente
    private void iniciarChat() {
        String nombre = txtNombreCliente.getText();
        if (!nombre.isEmpty()) {
            try {
                System.out.println("Intentando conectar al servidor con el nombre de usuario: " + nombre);
                clienteSocket.registrarMensajes(messageSocketClient());
                clienteSocket.login(nombre);
                System.out.println("Conexión exitosa. Cliente en línea con el nombre de usuario: " + nombre);
                ClienteLogueado();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Error al iniciar el chat: " + e.getMessage());
            }
        } else {
            JOptionPane.showMessageDialog(this, "Ingrese un nombre de usuario para iniciar el chat");
        }
    }

    private void ClienteLogueado() {
        String nombre = txtNombreCliente.getText();
        labelDescripcionCliente.setText("Usuario: " + nombre);
        btnLogin.setEnabled(false);
        btnLogout.setEnabled(true);
        btnEnviarMensaje.setEnabled(true);
        txtNombreCliente.setEnabled(false); // Deshabilita la edición del nombre después de registrarse
//            clienteSocket.registerOnMessage(createMessageSocketClient());
        clienteSocket.enviarMensajes("LOGIN " + nombre);
    }

    private void limpiarInterfaz() {
        labelDescripcionCliente.setText("");
        txtNombreCliente.setText("");
        txtMensajeCliente.setText("");
        txtPanelChat.setText("");
        btnLogin.setEnabled(true);
        btnLogout.setEnabled(false);
        btnEnviarMensaje.setEnabled(false);
        txtNombreCliente.setEnabled(true);
        if (clienteSocket != null) {
            clienteSocket.logout();
        }
    }

    private interfaceSocketClient messageSocketClient() {
        return new interfaceSocketClient() {
            @Override
            public void mensajeRecibido(String response) {
                txtPanelChat.append(response + "\n");
            }

            @Override
            public void servidorCerrado() {
//                JOptionPane.showMessageDialog(null, "El servidor está cerrado!");
                limpiarInterfaz();
            }

            @Override
            public void logout() {
                
                limpiarInterfaz();
            }

        };
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        txtNombreCliente = new javax.swing.JTextField();
        btnLogin = new javax.swing.JButton();
        labelDescripcionCliente = new javax.swing.JLabel();
        btnLogout = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtPanelChat = new javax.swing.JTextArea();
        txtMensajeCliente = new javax.swing.JTextField();
        btnEnviarMensaje = new javax.swing.JButton();

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(102, 255, 153));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel2.setText("Nombre de Usuario:");

        txtNombreCliente.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txtNombreCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNombreClienteActionPerformed(evt);
            }
        });

        btnLogin.setBackground(new java.awt.Color(102, 255, 153));
        btnLogin.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnLogin.setText("Login");
        btnLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoginActionPerformed(evt);
            }
        });

        labelDescripcionCliente.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        labelDescripcionCliente.setText("Usuario: ");
        labelDescripcionCliente.setToolTipText("");

        btnLogout.setBackground(new java.awt.Color(153, 255, 153));
        btnLogout.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnLogout.setText("Logout");
        btnLogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLogoutActionPerformed(evt);
            }
        });

        txtPanelChat.setColumns(20);
        txtPanelChat.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txtPanelChat.setRows(5);
        jScrollPane1.setViewportView(txtPanelChat);

        txtMensajeCliente.setToolTipText("");

        btnEnviarMensaje.setBackground(new java.awt.Color(153, 255, 153));
        btnEnviarMensaje.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnEnviarMensaje.setText("Enviar");
        btnEnviarMensaje.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEnviarMensajeActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 565, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 29, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(txtNombreCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(59, 59, 59))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(txtMensajeCliente)
                        .addGap(18, 18, 18)
                        .addComponent(btnEnviarMensaje, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(35, 35, 35))))
            .addGroup(layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(labelDescripcionCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnLogout, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelDescripcionCliente)
                    .addComponent(btnLogout, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(48, 48, 48)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNombreCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtMensajeCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEnviarMensaje, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(38, 38, 38))
        );

        jLabel2.getAccessibleContext().setAccessibleName("labelNombreChatter");
        txtNombreCliente.getAccessibleContext().setAccessibleName("");
        btnLogin.getAccessibleContext().setAccessibleName("btnLogin");
        labelDescripcionCliente.getAccessibleContext().setAccessibleName("labelNombreRegistrado");
        btnLogout.getAccessibleContext().setAccessibleName("btnLogout");
        btnLogout.getAccessibleContext().setAccessibleDescription("");
        txtMensajeCliente.getAccessibleContext().setAccessibleName("txtMensajeChatter");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtNombreClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNombreClienteActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_txtNombreClienteActionPerformed

    
    private void btnEnviarMensajeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEnviarMensajeActionPerformed
        enviarMensaje();
    }//GEN-LAST:event_btnEnviarMensajeActionPerformed

    private void btnLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoginActionPerformed
    iniciarChat();
    }//GEN-LAST:event_btnLoginActionPerformed

    
    
    
   

    private void btnLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogoutActionPerformed
        // TODO add your handling code here:
        limpiarInterfaz();
        JOptionPane.showMessageDialog(null, "cliente desconectado del servidor!");
        System.out.print("Sesion cerrada\n");
    }//GEN-LAST:event_btnLogoutActionPerformed

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
            java.util.logging.Logger.getLogger(Cliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Cliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Cliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Cliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
                new Cliente().setVisible(true);
        });
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEnviarMensaje;
    private javax.swing.JButton btnLogin;
    private javax.swing.JButton btnLogout;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel labelDescripcionCliente;
    private javax.swing.JTextField txtMensajeCliente;
    private javax.swing.JTextField txtNombreCliente;
    private javax.swing.JTextArea txtPanelChat;
    // End of variables declaration//GEN-END:variables
}

