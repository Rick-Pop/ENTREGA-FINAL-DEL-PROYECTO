/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

public class Descomprimir {
    public static void descomprimirArchivo(String FilePath) {
        HashMap<Integer, String> diccionario = new HashMap<>();
        StringBuilder contenidoComprimido = new StringBuilder();
        boolean leyendoDiccionario = true;
        String saltoDeLineaMarcador = "<EOL>"; // Marcador para los saltos de línea

        try (BufferedReader br = new BufferedReader(new FileReader(FilePath))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                if (linea.equals("---")) {
                    leyendoDiccionario = false;
                    continue;
                }

                if (leyendoDiccionario) {
                    String[] partes = linea.split(":");
                    diccionario.put(Integer.parseInt(partes[1].trim()), partes[0].trim());
                } else {
                    contenidoComprimido.append(linea).append(" ");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        String[] indices = contenidoComprimido.toString().split("\\s+");
        StringBuilder contenidoDescomprimido = new StringBuilder();
        
        for (String indice : indices) {
            int clave = Integer.parseInt(indice);
            String palabra = diccionario.get(clave);
            if (palabra.equals(saltoDeLineaMarcador)) {
                contenidoDescomprimido.append("\n");
            } else {
                contenidoDescomprimido.append(palabra).append(" ");
            }
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FilePath))) {
            bw.write(contenidoDescomprimido.toString().trim());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
