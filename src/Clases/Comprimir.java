
package Clases;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author VICTUS
 */
public class Comprimir {
  public static void comprimirArchivo(String FilePath) {
        HashMap<String, Integer> Cadena = new HashMap<>();
        StringBuilder contenido = new StringBuilder();
        int index = 1; // Índice inicial para el diccionario
        String saltoDeLineaMarcador = "<EOL>"; // Marcador para los saltos de línea

        try (BufferedReader br = new BufferedReader(new FileReader(FilePath))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                contenido.append(linea).append(" ").append(saltoDeLineaMarcador).append(" ");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        String[] palabras = contenido.toString().split("\\s+");
        StringBuilder contenidoComprimido = new StringBuilder();
        
        for (String palabra : palabras) {
            if (!Cadena.containsKey(palabra)) {
                Cadena.put(palabra, index++);
            }
            contenidoComprimido.append(Cadena.get(palabra)).append(" ");
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FilePath))) {
            // Escribir el diccionario al principio del archivo
            for (Map.Entry<String, Integer> entry : Cadena.entrySet()) {
                bw.write(entry.getKey() + ":" + entry.getValue() + "\n");
            }
            bw.write("---\n"); // Separador entre diccionario y contenido comprimido
            bw.write(contenidoComprimido.toString().trim());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }  
}
