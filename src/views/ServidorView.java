package views;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import servidor.ServidorTCP;

public class ServidorView extends javax.swing.JFrame {
    
    ServidorTCP server;
    
    public ServidorView() {
        initComponents();
        this.setIconImage(new ImageIcon(getClass().getResource("/views/icons/Server-25.png")).getImage());
    }
    
    private void start() {
        try {
            String i = JOptionPane.showInputDialog("Digite a porta de espera do servidor: ", "3322");
            if (i != null) {
                final int porta = Integer.parseInt(i);
                new Thread() {
                    public void run() {
                        server = new ServidorTCP(porta);
                        server.esperaConexao();
                    }
                }.start();
                status.setEnabled(true);
                buttonOn.setText("Desligar");
                buttonOn.setIcon(new ImageIcon(getClass().getResource("/views/icons/Off-25.png")));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "É necessário digitar uma porta válida.");
        }
    }
    
    private void close() {
        try {
            server.close();
            server = null;
            status.setEnabled(false);
            buttonOn.setText("Iniciar");
            buttonOn.setIcon(new ImageIcon(getClass().getResource("/views/icons/On-25.png")));
        } catch (Throwable ex) {
            Logger.getLogger(ServidorView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelButtons = new javax.swing.JPanel();
        buttonOn = new javax.swing.JButton();
        buttonRestart = new javax.swing.JButton();
        buttonCancelar = new javax.swing.JButton();
        panelProcurar = new javax.swing.JPanel();
        status = new javax.swing.JLabel();
        titulo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Servidor");
        setMaximumSize(new java.awt.Dimension(482, 311));
        setMinimumSize(new java.awt.Dimension(482, 311));

        buttonOn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/views/icons/On-25.png"))); // NOI18N
        buttonOn.setText("Iniciar");
        buttonOn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonOnActionPerformed(evt);
            }
        });
        panelButtons.add(buttonOn);

        buttonRestart.setIcon(new javax.swing.ImageIcon(getClass().getResource("/views/icons/Restart-25.png"))); // NOI18N
        buttonRestart.setText("Reiniciar");
        buttonRestart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonRestartActionPerformed(evt);
            }
        });
        panelButtons.add(buttonRestart);

        buttonCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/views/icons/Cancelar-25.png"))); // NOI18N
        buttonCancelar.setText("Cancelar");
        buttonCancelar.setMaximumSize(null);
        buttonCancelar.setMinimumSize(null);
        buttonCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonCancelarActionPerformed(evt);
            }
        });
        panelButtons.add(buttonCancelar);

        panelProcurar.setBorder(javax.swing.BorderFactory.createTitledBorder("Status"));

        status.setIcon(new javax.swing.ImageIcon(getClass().getResource("/views/icons/loading.gif"))); // NOI18N
        status.setDisabledIcon(new javax.swing.ImageIcon(getClass().getResource("/views/icons/sleep.png"))); // NOI18N
        status.setEnabled(false);

        javax.swing.GroupLayout panelProcurarLayout = new javax.swing.GroupLayout(panelProcurar);
        panelProcurar.setLayout(panelProcurarLayout);
        panelProcurarLayout.setHorizontalGroup(
            panelProcurarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelProcurarLayout.createSequentialGroup()
                .addGap(150, 150, 150)
                .addComponent(status)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelProcurarLayout.setVerticalGroup(
            panelProcurarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelProcurarLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(status)
                .addContainerGap())
        );

        titulo.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        titulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        titulo.setText("Servidor");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelButtons, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 466, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelProcurar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(titulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(titulo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(panelProcurar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(panelButtons, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void buttonOnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonOnActionPerformed
        if (buttonOn.getText().equals("Iniciar")) {
            this.start();
        } else {
            this.close();
        }
    }//GEN-LAST:event_buttonOnActionPerformed

    private void buttonRestartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonRestartActionPerformed
        try {
            if (server != null) {
                server.restart();
            } else {
                this.start();
            }
        } catch (IOException ex) {
            Logger.getLogger(ServidorView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_buttonRestartActionPerformed

    private void buttonCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonCancelarActionPerformed
        this.close();
        this.dispose();
    }//GEN-LAST:event_buttonCancelarActionPerformed

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ServidorView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ServidorView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ServidorView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ServidorView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ServidorView().setVisible(true);
            }
        });
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonCancelar;
    private javax.swing.JButton buttonOn;
    private javax.swing.JButton buttonRestart;
    private javax.swing.JPanel panelButtons;
    private javax.swing.JPanel panelProcurar;
    private javax.swing.JLabel status;
    private javax.swing.JLabel titulo;
    // End of variables declaration//GEN-END:variables
}
