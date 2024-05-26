/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mavenproject1;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;


public class MultiLanguagePathCompiler {
    
    public static ArrayList<String> list;
    
    public static void compileAndRun(String srcFile, String projectName) {
        if(list!=null){
            list.clear();
        }
        String calismaDizini = System.getProperty("user.dir");
        System.out.println(calismaDizini);
        String ourPath = calismaDizini; 
        String a = srcFile;
        String src1 = getFileName(srcFile);
        System.out.println(src1 + "--------");
        copyFiles(a,calismaDizini+File.separator+"Projects"+File.separator+projectName);
        copyFiles(a, ourPath);
        srcFile = ourPath + File.separator + src1;
        System.out.println(srcFile);
        
       
        // Dosyanın uzantısına göre hangi dilde olduğunu belirle
        String extension = getFileExtension(srcFile);
        switch (extension) {
            case "c":
                compileAndRunC(srcFile);
                break;
            case "cpp":
                compileAndRunCpp(srcFile);
                break;
            case "java":
                compileAndRunJava(srcFile);
                break;
            case "py":
                runPython(srcFile);
                break;
            default:
                System.out.println("Desteklenmeyen dosya uzantısı.");
        }
        
        String deleteFilePath = calismaDizini + File.separator + "example.exe";
        String deleteFilePath2 = calismaDizini + File.separator + src1;
        File fil1 = new File(deleteFilePath);
        File fil2 = new File(deleteFilePath2);
        fil1.delete();
        fil2.delete();
        
    }
    

    private static String getFileExtension(String fileName) {
        int lastDotIndex = fileName.lastIndexOf('.');
        if (lastDotIndex == -1) {
            return ""; // Dosya uzantısı yoksa boş döndür
        }
        return fileName.substring(lastDotIndex + 1);
    }
    public static void copyFiles(String sourceFilePath, String destinationDirectoryPath) {
        try {
            File sourceFile = new File(sourceFilePath);
            File destDir = new File(destinationDirectoryPath);

            // Hedef dizin kontrolü ve varsa oluşturma
            if (!destDir.exists()) {
                destDir.mkdirs();
            }

            Path sourcePath = sourceFile.toPath();
            Path destPath = destDir.toPath().resolve(sourceFile.getName());

            Files.copy(sourcePath, destPath, StandardCopyOption.REPLACE_EXISTING);
            System.out.println("Dosya kopyalandı: " + sourceFile.getName());
        } catch (IOException e) {
            System.out.println("Hata oluştu: " + e.getMessage());
        }
    }
    private static void compileAndRunC(String srcFile) {
        // Derleme işlemi
        list = new ArrayList<>();
        String compiledFile = "example";
        String[] runCommand = {"./" + compiledFile};

        try {
            // GCC'yi çağır ve C kodunu derle
            ProcessBuilder compilerProcessBuilder = new ProcessBuilder("gcc", srcFile, "-o", compiledFile);
            compilerProcessBuilder.directory(new File(getFilePath(srcFile))); // Çalışma dizinini ayarla
            Process compilerProcess = compilerProcessBuilder.start();
            compilerProcess.waitFor();

            // Derleme işlemi başarılıysa
            if (compilerProcess.exitValue() == 0) {
                System.out.println("C kodu başarıyla derlendi.");

                // Derlenmiş C dosyasını çalıştır ve çıktısını oku
                Process runProcess = new ProcessBuilder(runCommand).start();
                BufferedReader reader = new BufferedReader(new InputStreamReader(runProcess.getInputStream()));
                String line;
                while ((line = reader.readLine()) != null) {
                    System.out.println("C programının çıktısı: " + line);
                    list.add(line);
                }

                // C programının çalışmasını bekle ve sonucunu al
                int exitCode = runProcess.waitFor();
                System.out.println("C programının çıkış kodu: " + exitCode);
            } else {
                // Derleme işlemi başarısızsa
                System.out.println("C kodu derlenirken bir hata oluştu.");
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static String getFilePath(String filePath) {
        int lastSeparatorIndex = filePath.lastIndexOf(File.separator);
        if (lastSeparatorIndex == -1) {
            return ""; // Dosya yolu geçersizse boş döndür
        }
        return filePath.substring(0, lastSeparatorIndex);
    }

    private static void compileAndRunCpp(String srcFile) {
        list = new ArrayList<>();
        // Derleme işlemi
        String compiledFile = "example";
        String[] runCommand = {"./" + compiledFile};

        try {
            // G++'yı çağır ve C++ kodunu derle
            ProcessBuilder compilerProcessBuilder = new ProcessBuilder("g++", srcFile, "-o", compiledFile);
            compilerProcessBuilder.directory(new File(getFilePath(srcFile))); // Çalışma dizinini ayarla
            Process compilerProcess = compilerProcessBuilder.start();
            compilerProcess.waitFor();

            // Derleme işlemi başarılıysa
            if (compilerProcess.exitValue() == 0) {
                System.out.println("C++ kodu başarıyla derlendi.");

                // Derlenmiş C++ dosyasını çalıştır ve çıktısını oku
                Process runProcess = new ProcessBuilder(runCommand).start();
                BufferedReader reader = new BufferedReader(new InputStreamReader(runProcess.getInputStream()));
                String line;
                while ((line = reader.readLine()) != null) {
                    System.out.println("C++ programının çıktısı: " + line);
                    list.add(line);
                }

                // C++ programının çalışmasını bekle ve sonucunu al
                int exitCode = runProcess.waitFor();
                System.out.println("C++ programının çıkış kodu: " + exitCode);
            } else {
                // Derleme işlemi başarısızsa
                System.out.println("C++ kodu derlenirken bir hata oluştu.");
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void compileAndRunJava(String srcFile) {
        list = new ArrayList<>();
        try {
            // Java kodunu derle
            ProcessBuilder compilerProcess = new ProcessBuilder("javac", srcFile);
            compilerProcess.directory(new File(getFilePath(srcFile))); // Çalışma dizinini ayarla
            Process compileProcess = compilerProcess.start();
            compileProcess.waitFor();

            // Derleme işlemi başarılıysa
            if (compileProcess.exitValue() == 0) {
                System.out.println("Java kodu başarıyla derlendi.");

                // Sınıf adını bul
                String className = getClassName(srcFile);

                // Class dosyasını çalıştır
                Process runProcess = new ProcessBuilder("java", className).start();
                BufferedReader reader = new BufferedReader(new InputStreamReader(runProcess.getInputStream()));
                String line;

                // Java programının çıktısını oku
                System.out.println("Java programının çıktısı:");
                while ((line = reader.readLine()) != null) {
                    System.out.println(line);
                    list.add(line);
                }

                // Java programının hatalarını oku
                BufferedReader errorReader = new BufferedReader(new InputStreamReader(runProcess.getErrorStream()));
                System.out.println("Java programının hata mesajları:");
                while ((line = errorReader.readLine()) != null) {
                    System.out.println(line);
                }

                // Java programının çalışmasını bekle ve sonucunu al
                int exitCode = runProcess.waitFor();
                System.out.println("Java programının çıkış kodu: " + exitCode);
            } else {
                // Derleme işlemi başarısızsa
                System.out.println("Java kodu derlenirken bir hata oluştu.");
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static String getClassName(String srcFile) {
        // Java dosyasını oku ve sınıf adını bul
        try (BufferedReader br = new BufferedReader(new FileReader(srcFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.contains("class ")) {
                    // "class " kelimesini içeren satırı bul
                    int start = line.indexOf("class ") + 6;
                    int end = line.indexOf("{");
                    return line.substring(start, end).trim();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static void runPython(String srcFile) {
        list = new ArrayList<>();
        // Çalıştırma işlemi
        try {
            // Python kodunu çalıştır
            Process runProcess = new ProcessBuilder("python", srcFile).start();
            BufferedReader reader = new BufferedReader(new InputStreamReader(runProcess.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println("Python programının çıktısı: " + line);
                list.add(line);
            }

            // Python programının çalışmasını bekle ve sonucunu al
            int exitCode = runProcess.waitFor();
            System.out.println("Python programının çıkış kodu: " + exitCode);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
    
    public static String getFileName(String path) {
        if (path == null || path.isEmpty()) {
            return null;
        }
        
        // Windows'ta pathi bölmek için \\, Unix tabanlı sistemlerde / kullanılır.
        String separator = System.getProperty("file.separator");
        String[] parts = path.split("\\\\");
        
        // Son parça dosya adını içerir.
        return parts[parts.length - 1];
    }


}
