package View;

import Controller.SoundManagerController;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;


/**
 *
 * @author Henrique Valcanaia
 * @author Pedro Cantarutti
 */
public class MainWindow extends javax.swing.JFrame {
 
    public SoundManagerController smc;

    /**
     * Creates new form MainWindow
     */
    public MainWindow() {
        initComponents();
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 2 - this.getSize().width / 2,
                dim.height / 2 - this.getSize().height / 2);
        this.setTitle("TF2_TCP");
        smc = new SoundManagerController();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        taTextContent = new javax.swing.JTextArea();
        tfFilePath = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        taTextContent.setColumns(20);
        taTextContent.setRows(5);
        taTextContent.setText("this text area will show the content of the selected file\n");
        jScrollPane1.setViewportView(taTextContent);

        tfFilePath.setEditable(false);
        tfFilePath.setText("click here to select a file");
        tfFilePath.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tfFilePathMouseClicked(evt);
            }
        });
        tfFilePath.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfFilePathActionPerformed(evt);
            }
        });

        jButton2.setText("Play text");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tfFilePath)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 341, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton2)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tfFilePath, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 202, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton2)
                .addGap(6, 6, 6))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tfFilePathMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tfFilePathMouseClicked
        JFileChooser fc = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Text files", "txt", "text");
        fc.setFileFilter(filter);
        switch (fc.showOpenDialog(null)) {
            case JFileChooser.CANCEL_OPTION: {
                // show msg? nah
                break;
            }
            case JFileChooser.APPROVE_OPTION: {
                File f = fc.getSelectedFile();
                String filePath = f.getName();
                if (filePath.endsWith(".txt")) {
                    BufferedReader in = null;
                    try {
                        taTextContent.setText(""); // a way to "clear" the initial message
                        tfFilePath.setText(f.getAbsolutePath());
                        in = new BufferedReader(new FileReader(f));
                        String line = in.readLine();
                        while (line != null) {
                            taTextContent.append(line + "\n");
                            line = in.readLine();
                        }
                    } catch (FileNotFoundException ex) {
                        Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE,
                                null, ex);
                        JOptionPane.showMessageDialog(null, ex.getMessage(), "Error",
                                JOptionPane.ERROR_MESSAGE);
                    } catch (IOException ex) {
                        Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE,
                                null, ex);
                        JOptionPane.showMessageDialog(null, ex.getMessage(), "Error",
                                JOptionPane.ERROR_MESSAGE);
                    } finally {
                        try {
                            in.close();
                        } catch (IOException ex) {
                            Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE,
                                    null, ex);
                            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error",
                                    JOptionPane.ERROR_MESSAGE);
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Not a txt file!", "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
                break;
            }
            case JFileChooser.ERROR_OPTION: {
                JOptionPane.showMessageDialog(null, "Error!  No file opened.", "Error",
                        JOptionPane.ERROR_MESSAGE);
                break;
            }
            default: {
                System.out.println("not expected");
            }
        }
    }//GEN-LAST:event_tfFilePathMouseClicked

    private void tfFilePathActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfFilePathActionPerformed
    }//GEN-LAST:event_tfFilePathActionPerformed

    
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        String str = taTextContent.getText();
        smc.playText(str);
    }//GEN-LAST:event_jButton2ActionPerformed

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
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MainWindow().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea taTextContent;
    private javax.swing.JTextField tfFilePath;
    // End of variables declaration//GEN-END:variables
}
