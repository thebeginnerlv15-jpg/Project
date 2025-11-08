package util;


import java.io.*;
import java.util.*;


public class FileUtil {
    public static List<String> readAllLines(String file) {
        List<String> lines = new ArrayList<>();
        File f = new File(file);
        if (!f.exists()) return lines;
        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            String line;
            while ((line = br.readLine()) != null) lines.add(line.trim());
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
        return lines;
    }


    public static void writeAllLines(String file, List<String> lines) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
            for (String line : lines) bw.write(line + System.lineSeparator());
        } catch (IOException e) {
            System.out.println("Error writing file: " + e.getMessage());
        }
    }


    public static void appendLine(String file, String line) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file, true))) {
            bw.write(line + System.lineSeparator());
        } catch (IOException e) {
            System.out.println("Error appending file: " + e.getMessage());
        }
    }
}
