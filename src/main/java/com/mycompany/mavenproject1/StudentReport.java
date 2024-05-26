/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.mavenproject1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Efe
 */
public class StudentReport extends javax.swing.JFrame {


    static String name;
    
    public StudentReport(){
        
    }
    
    public StudentReport(String name) {
        initComponents();
        this.name = name;
        String calismaDizini = System.getProperty("user.dir");
        System.out.println(name);
        nameList.setListData(rearrangeTheList());
        pointList.setListData(doubleToString(rearrangeThePointList()));
    }


    public static String[] rearrangeTheList(){
        String workingDirectory = System.getProperty("user.dir");
        String fullPath = workingDirectory + File.separator + "Projects"+File.separator+name+File.separator+"Analyse";
        String[] notebookNames = notDefterleriniListele(fullPath);
        return notebookNames;
    }
    
    public static double[] rearrangeThePointList(){
        String workingDirectory = System.getProperty("user.dir");
        String fullPath = workingDirectory + File.separator + "Projects"+File.separator+name+File.separator+"Analyse";
        double[] pointList = notDefterlerindekiDoubleleriOku(fullPath);
        return pointList;
    }
    public static String[] doubleToString(double[] doubleDizisi) {
        String[] stringDizisi = new String[doubleDizisi.length];
        for (int i = 0; i < doubleDizisi.length; i++) {
            stringDizisi[i] = String.valueOf(doubleDizisi[i]);
        }
        return stringDizisi;
    }
    public static double[] notDefterlerindekiDoubleleriOku(String dosyaYolu) {
        ArrayList<Double> doubleListesi = new ArrayList<>();

        File klasor = new File(dosyaYolu);
        File[] dosyalar = klasor.listFiles();
        
        if (dosyalar != null) {
            for (File dosya : dosyalar) {
                if (dosya.isFile() && dosya.getName().endsWith(".txt")) {
                    try (BufferedReader br = new BufferedReader(new FileReader(dosya))) {
                        String satir;
                        while ((satir = br.readLine()) != null) {
                            try {
                                double doubleDeger = Double.parseDouble(satir);
                                doubleListesi.add(doubleDeger);
                            } catch (NumberFormatException e) {
                                // Double değere dönüştürülemezse, bu satırı atla
                                continue;
                            }
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        // Listeyi double dizisine dönüştür
        double[] doubleDizisi = new double[doubleListesi.size()];
        for (int i = 0; i < doubleListesi.size(); i++) {
            doubleDizisi[i] = doubleListesi.get(i);
        }
        return doubleDizisi;
    }    
    
    public static String[] notDefterleriniListele(String dosyaYolu) {
        File klasor = new File(dosyaYolu);
        ArrayList<String> notDefteriListesi = new ArrayList<>();

        // Dosya yolundaki tüm dosyaları al
        File[] dosyalar = klasor.listFiles();
        if (dosyalar != null) {
            for (File dosya : dosyalar) {
                // Dosya adının uzantısı .txt ise not defteri olarak kabul ediyoruz
                if (dosya.isFile() && dosya.getName().endsWith(".txt")) {
                    notDefteriListesi.add(dosya.getName());
                }
            }
        }

        // Listeyi dizeye dönüştür
        String[] notDefterleri = new String[notDefteriListesi.size()];
        notDefterleri = notDefteriListesi.toArray(notDefterleri);
        return notDefterleri;
    }
    
    public static String[] arrayListToStringArray(ArrayList<String> arrayList) {
        String[] stringArray = new String[arrayList.size()];
        for (int i = 0; i < arrayList.size(); i++) {
            stringArray[i] = arrayList.get(i);
        }
        return stringArray;
    }

    // ArrayList<Double>'ı stringler halinde string array'e dönüştüren metod
    public static String[] doubleArrayListToStringArray(ArrayList<Double> doubleArrayList) {
        String[] stringArray = new String[doubleArrayList.size()];
        for (int i = 0; i < doubleArrayList.size(); i++) {
            stringArray[i] = String.valueOf(doubleArrayList.get(i));
        }
        return stringArray;
    }

    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        pointList = new javax.swing.JList<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        nameList = new javax.swing.JList<>();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        pointList.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(pointList);

        nameList.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane2.setViewportView(nameList);

        jButton1.setText("Back");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Analyse the code and output");
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
                .addGap(55, 55, 55)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(45, 45, 45)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 204, Short.MAX_VALUE)
                .addComponent(jButton2)
                .addGap(97, 97, 97))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addGap(50, 50, 50))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(43, 43, 43)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 424, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 424, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(108, 108, 108)
                        .addComponent(jButton2)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 33, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addGap(23, 23, 23))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:

        this.setVisible(false);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        
        String projectName = name;
        String assgnName = (String)nameList.getSelectedValue();
        AnalyseCodeAndOutput a1 = new AnalyseCodeAndOutput(projectName,assgnName);
        a1.setVisible(true);
        this.setVisible(false);
        
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
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(StudentReport.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(StudentReport.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(StudentReport.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(StudentReport.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new StudentReport().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JList<String> nameList;
    private javax.swing.JList<String> pointList;
    // End of variables declaration//GEN-END:variables
}
