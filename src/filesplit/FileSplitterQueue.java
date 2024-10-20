/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package filesplit;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class FileSplitterQueue {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // Meminta input nama file dari pengguna
        System.out.print("Masukkan nama file teks (misal: file.txt): ");
        String fileName = scanner.nextLine();
        
        // Meminta input ukuran potongan file
        System.out.print("Masukkan jumlah baris per potongan: ");
        int chunkSize = scanner.nextInt();
        scanner.nextLine(); // Konsumsi newline

        // Membaca file dan memotongnya sesuai dengan input pengguna
        Queue<String> fileChunks = new LinkedList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            StringBuilder chunk = new StringBuilder();
            int lineCount = 0;

            // Membaca setiap baris dari file
            while ((line = reader.readLine()) != null) {
                chunk.append(line).append("\n");
                lineCount++;
                
                // Jika sudah mencapai ukuran potongan, simpan ke queue
                if (lineCount == chunkSize) {
                    fileChunks.add(chunk.toString());
                    chunk.setLength(0); // Reset chunk
                    lineCount = 0;
                }
            }
            
            // Jika ada sisa baris yang belum dimasukkan ke queue
            if (chunk.length() > 0) {
                fileChunks.add(chunk.toString());
            }
        } catch (IOException e) {
            System.out.println("Terjadi kesalahan saat membaca file: " + e.getMessage());
        }

        // Menampilkan potongan-potongan file yang sudah diproses
        int chunkNumber = 1;
        while (!fileChunks.isEmpty()) {
            System.out.println("=== Potongan " + chunkNumber + " ===");
            System.out.println(fileChunks.poll());
            chunkNumber++;
        }

        scanner.close();
    }
}
   

