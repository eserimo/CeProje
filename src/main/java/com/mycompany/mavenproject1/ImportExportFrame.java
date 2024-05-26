/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.mavenproject1;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileSystemView;

/**
 *
 * @author Efe
 */
public class ImportExportFrame extends javax.swing.JFrame {

    /**
     * Creates new form ImportExportFrame
     */
    public ImportExportFrame() {
        initComponents();
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jButton1.setText("Export");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Import");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Back");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(593, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addGap(250, 250, 250))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jButton3)
                        .addGap(39, 39, 39))))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(173, 173, 173)
                    .addComponent(jButton2)
                    .addContainerGap(668, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(271, 271, 271)
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 221, Short.MAX_VALUE)
                .addComponent(jButton3)
                .addGap(43, 43, 43))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(266, 266, 266)
                    .addComponent(jButton2)
                    .addContainerGap(294, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    
    
    
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
         
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY); // Sadece klasör seçimi
        int returnValue = fileChooser.showOpenDialog(this);
        File selectedFile = null;
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            selectedFile = fileChooser.getSelectedFile();
            if (selectedFile.isFile()) {
                System.out.println("Seçilen Dosya: " + selectedFile.getPath());
            } else {
                System.err.println("Hata: Bir dosya seçiniz.");
            }
}
        String work = System.getProperty("user.dir");
        String destPath = selectedFile.getPath();
        String fileName = selectedFile.getName();
        String currentPath = work + File.separator + "Projects";
        String ccPath = currentPath + File.separator + fileName;
        
        String yol = currentPath; // Oluşturmak istediğiniz klasörün yolu
        String klasorAdi = fileName; // Oluşturulacak klasörün adı

        Path klasorYolu = Paths.get(yol, klasorAdi);

        try {
            // Klasörü oluştur
            Files.createDirectory(klasorYolu);
            System.out.println("Klasör oluşturuldu: " + klasorYolu);
        } catch (IOException e) {
            System.err.println("Klasör oluşturma hatası: " + e.getMessage());
        }
        
      
        try{
        copyFolder(new File(destPath),new File(ccPath));
        }catch(Exception e){
            
        }
JOptionPane.showMessageDialog(this, "File imported.", "", JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_jButton2ActionPerformed
    public static void copyFolder(File sourceFolder, File destinationFolder) throws IOException {
        // Kaynak klasörün içindekilerin listesini al
        File[] files = sourceFolder.listFiles();
        if (files != null) {
            // Hedef klasörü oluştur
            if (!destinationFolder.exists()) {
                destinationFolder.mkdir();
            }
            
            // Her bir dosya ve alt klasör için kopyalama işlemini gerçekleştir
            for (File file : files) {
                if (file.isDirectory()) {
                    // Eğer dosya bir klasör ise, içeriğini kopyala
                    copyFolder(file, new File(destinationFolder, file.getName()));
                } else {
                    // Eğer dosya bir dosya ise, direkt olarak kopyala
                    Files.copy(file.toPath(), new File(destinationFolder, file.getName()).toPath(), StandardCopyOption.REPLACE_EXISTING);
                }
            }
        }
    }
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
         
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY); // Sadece klasör seçimi
        String work = System.getProperty("user.dir");
        String defaultPath = work + File.separator+"Projects";
        fileChooser.setCurrentDirectory(new File(defaultPath));
        int returnValue = fileChooser.showOpenDialog(this);
        File selectedFile = null;
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            selectedFile = fileChooser.getSelectedFile();
            if (selectedFile.isFile()) {
                System.out.println("Seçilen Dosya: " + selectedFile.getPath());
            } else {
                System.err.println("Hata: Bir dosya seçiniz.");
            }
        }
        String destPath = selectedFile.getPath();
        String fileName = selectedFile.getName();
        String currentPath = work + File.separator + "Projects";
        String desktopPath = System.getProperty("user.home") + File.separator + "Desktop";
        String ccPath = desktopPath + File.separator+fileName;
        
        String yol = desktopPath; // Oluşturmak istediğiniz klasörün yolu
        String klasorAdi = fileName; // Oluşturulacak klasörün adı

        Path klasorYolu = Paths.get(yol, klasorAdi);

        try {
            // Klasörü oluştur
            Files.createDirectory(klasorYolu);
            System.out.println("Klasör oluşturuldu: " + klasorYolu);
        } catch (IOException e) {
            System.err.println("Klasör oluşturma hatası: " + e.getMessage());
        }
        
      
        try{
        copyFolder(new File(destPath),new File(ccPath));
        }catch(Exception e){
            
        }
        JOptionPane.showMessageDialog(this, "exported", "", JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
    }//GEN-LAST:event_jButton3ActionPerformed

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
            java.util.logging.Logger.getLogger(ImportExportFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ImportExportFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ImportExportFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ImportExportFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ImportExportFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    // End of variables declaration//GEN-END:variables
}
