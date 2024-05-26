/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mavenproject1;

import java.awt.List;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import javax.swing.JList;
import javax.swing.JOptionPane;


public class FileWriterA {

        public static void createMainProjectFile(String klasorIsmi) {
        // Programın çalışma dizinini al
        String calismaDizini = System.getProperty("user.dir");

        // Klasörün oluşturulacağı yol
        String klasorYolu = calismaDizini + File.separator + klasorIsmi;

        // Klasör nesnesi oluştur
        File klasor = new File(klasorYolu);

        // Klasörü oluştur
        boolean basarili = klasor.mkdir();

        if (basarili) {
            System.out.println("Klasör başarıyla oluşturuldu: " + klasorYolu);
        } else {
            System.out.println("Klasör oluşturma başarısız.");
        }
    }
        
    public static void createProject(String altKlasorIsmi) {
        String anaKlasorIsmi = "Projects";
        String calismaDizini = System.getProperty("user.dir");
        String klasorYolu = calismaDizini + File.separator + anaKlasorIsmi + File.separator + altKlasorIsmi;
        File klasor = new File(klasorYolu);
        boolean basarili = klasor.mkdir();

        if (basarili) {
            System.out.println("Alt klasör başarıyla oluşturuldu: " + klasorYolu);
        } else {
            System.out.println("Alt klasör oluşturma başarısız.");
        }
    } 
    
    public static void outputFile(String filePath,String altKlasor) {
        String calismaDizini = System.getProperty("user.dir");
        String klasorYolu = calismaDizini + File.separator + filePath;
        String dosyaYolu = klasorYolu+"/"+altKlasor; // Not defterinin oluşturulacağı dosya yolunu belirtin
        
        try {
            // Dosyayı oluştur
            FileWriter fileWriter = new FileWriter(dosyaYolu);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            bufferedWriter.close();
            fileWriter.close();
            
            System.out.println("Not defteri başarıyla oluşturuldu ve yazıldı.");
        } catch (IOException e) {
            System.out.println("Hata: " + e.getMessage());
        }
    }
    public static void createProjectWithNotepad(String altKlasorIsmi) {
        String anaKlasorIsmi = "Projects";
        String calismaDizini = System.getProperty("user.dir");
        String klasorYolu = calismaDizini + File.separator + anaKlasorIsmi + File.separator + altKlasorIsmi;
        File klasor = new File(klasorYolu);
        boolean basarili = klasor.mkdir();

        if (basarili) {
            System.out.println("Alt klasör başarıyla oluşturuldu: " + klasorYolu);
            String txtFileName;
            txtFileName = "Output" + ".txt";
            createFileInFolder(klasorYolu,"Description.txt");
            createFileInFolder(klasorYolu, txtFileName);
            createFileInFolder(klasorYolu,"Language.txt");// Not defteri oluşturma
            FileWriterA.createStudentFile(altKlasorIsmi, "Assignments");
            FileWriterA.createStudentFile(altKlasorIsmi, "Reports");
            FileWriterA.createStudentFile(altKlasorIsmi, "Analyse");
        } else {
            System.out.println("Alt klasör oluşturma başarısız.");
        }
    }
    public static void renameFile(String sourceFilePath, String newFileName) throws IOException {
        // Kaynak dosya yolunu oluştur
        Path sourcePath = Paths.get(sourceFilePath);
        // Hedef dosya yolunu oluştur (aynı dizinde yeni isimle)
        Path targetPath = sourcePath.resolveSibling(newFileName);

        // Dosyayı yeniden adlandır
        Files.move(sourcePath, targetPath, StandardCopyOption.REPLACE_EXISTING);
    }
    public static void createStudentFile(String mainFolder,String altKlasorIsmi) {
        String anaKlasorIsmi = mainFolder;
        String calismaDizini = System.getProperty("user.dir");
        String klasorYolu = calismaDizini + File.separator + "Projects" + File.separator + anaKlasorIsmi + File.separator + altKlasorIsmi;
        File klasor = new File(klasorYolu);
        System.out.println(klasorYolu);
        boolean basarili = klasor.mkdir();

        if (basarili) {
            System.out.println("Alt klasör başarıyla oluşturuldu: " + klasorYolu);
        } else {
            System.out.println("Alt klasör oluşturma başarısız.");
        }
    }

    public static void createFileInFolder(String folderPath, String fileName) {
        try {
            String filePath = folderPath + File.separator + fileName;
            File file = new File(filePath);
            boolean success = file.createNewFile();

            if (success) {
                System.out.println("Dosya başarıyla oluşturuldu: " + filePath);
            } else {
                System.out.println("Dosya oluşturma başarısız.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }   
    
    public static void txtOverwrite(String altKlasorIsmi,String title){
        String anaKlasorIsmi = "Projects";
        String calismaDizini = System.getProperty("user.dir");
        String klasorYolu = calismaDizini + File.separator + anaKlasorIsmi + File.separator + title + File.separator + altKlasorIsmi +".txt"; 
        System.out.println(klasorYolu);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(klasorYolu))) {
            for (String satir : MultiLanguagePathCompiler.list) {
                writer.write(satir);
                writer.newLine(); // Yeni satıra geç
            }
            System.out.println("Dosya başarıyla yazıldı.");
        } catch (IOException e) {
            System.out.println("Dosya yazma hatası: " + e.getMessage());
        }        
    }
    
    public static String extensionOfFile(String dosyaYolu) {
        if (dosyaYolu == null || dosyaYolu.isEmpty()) {
            return "";
        }

        // Dosya yolunu "\\" veya "/" karakterlerine göre ayırarak dosya adını al
        String[] parcalar = dosyaYolu.split("[\\\\/]");

        // En son parça dosya adını içerir
        String dosyaAdi = parcalar[parcalar.length - 1];

        // Uzantıyı atarak dosya adını döndür
        int uzantiIndex = dosyaAdi.lastIndexOf('.');
        if (uzantiIndex != -1) {
            dosyaAdi = dosyaAdi.substring(0, uzantiIndex);
        }

        return dosyaAdi;
    }
public static String lastOfFilePathWithExtension(String dosyaYolu) {
    if (dosyaYolu == null || dosyaYolu.isEmpty()) {
        return "";
    }

    // Dosya yolunu "\\" veya "/" karakterlerine göre ayırarak dosya adını al
    String[] parcalar = dosyaYolu.split("[\\\\/]");

    // En son parça dosya adını içerir
    String dosyaAdi = parcalar[parcalar.length - 1];

    // Uzantıyı kaldırmadan dosya adını döndür
    return dosyaAdi;
} 
    public static void txtOverwriteAlternative(String altKlasorIsmi,ArrayList<String> lsit,String title){
        String anaKlasorIsmi = "Projects";
        String calismaDizini = System.getProperty("user.dir");
        String klasorYolu = calismaDizini + File.separator + anaKlasorIsmi + File.separator + title + File.separator + altKlasorIsmi +".txt"; 
        System.out.println(klasorYolu);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(klasorYolu))) {
            for (String satir : lsit) {
                writer.write(satir);
                writer.newLine(); // Yeni satıra geç
            }
            System.out.println("Dosya başarıyla yazıldı.");
        } catch (IOException e) {
            System.out.println("Dosya yazma hatası: " + e.getMessage());
        }        
    }
    public static String[] assignmentList() {
        String dizinYolu = "Projects"; // Projenizin ana dizininden bir alt dizin
        File dizin = new File(dizinYolu);
        String[] dosyaIsimleri = dizin.list();
        return dosyaIsimleri;
    }
    public static String[] ImportAssignmentList(String name) {
        String dizinYolu = "Projects" + File.separator + name + File.separator + "Assignments"; // Projenizin ana dizininden bir alt dizin
        File dizin = new File(dizinYolu);
        String[] dosyaIsimleri = dizin.list();
        return dosyaIsimleri;
    } 
    public static String[] reportList(String name) {
        String dizinYolu = "Projects"+File.separator+name+File.separator+"Reports"; // Projenizin ana dizininden bir alt dizin
        File dizin = new File(dizinYolu);
        String[] dosyaIsimleri = dizin.list();
        return dosyaIsimleri;
    }
    public static void deleteFolder(String name){
            String relativePath = "Projects"+ File.separator + name;

            // Dosyanın tam yolunu almak için çalışma dizinini kullanabilirsiniz
            String workingDirectory = System.getProperty("user.dir");

            // Relative path ve çalışma dizini birleştirilerek dosyanın tam yolu oluşturulur
            String filePath = workingDirectory + File.separator + relativePath;
            System.out.println(filePath);
            // Dosya nesnesi oluşturulur
            File file = new File(filePath);

            // Dosyanın varlığını ve silinip silinemediğini kontrol eder
            if (file.exists()) {
                if (file.delete()) {
                    System.out.println("Dosya başarıyla silindi.");
                } else {
                    System.out.println("Dosya silinemedi.");
                }
            } else {
                System.out.println("Dosya bulunamadı.");
            }   
    }
    
    public static void deleteWholeFile(String name){
        String[] listOfFile = {"Assignments","Reports","Description.txt","Language.txt","Output.txt"};
        String tempName = "";
        for(int i=0;i<5;i++){
            tempName = listOfFile[i];
            FileWriterA.deleteFolder(name + File.separator + tempName);
        }
        FileWriterA.deleteFolder(name);
    }

    public static void analysePointText(String path, double deger) {
        try {
            FileWriter dosyaYazici = new FileWriter(path);
            PrintWriter yazici = new PrintWriter(dosyaYazici);
            yazici.println(deger);
            yazici.close();
            System.out.println("Not defteri oluşturuldu ve değer yazıldı.");
        } catch (IOException e) {
            System.out.println("Dosya oluşturma hatası: " + e.getMessage());
        }
    }
    
}
