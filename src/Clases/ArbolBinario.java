/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author VICTUS
 */

public class ArbolBinario {

    public static NodoArbol raiz;
    static final String CHAR_LIST = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyzÁÉÍÓÚáéíóúÑñÜü";

    public ArbolBinario() {

    }

    //Metodo para insertar un nodo en el arbol
    public static void AgregarNodo(String nombre, long dpi) {
        NodoArbol nuevo = new NodoArbol(nombre, dpi);

        if (raiz == null) {
            raiz = nuevo;
        } else {
            NodoArbol auxiliar = raiz;
            NodoArbol padre;

            while (true) {
                padre = auxiliar;
                if (dpi < auxiliar.dpi) {
                    auxiliar = auxiliar.hijoIzquierdo;
                    if (auxiliar == null) {
                        padre.hijoIzquierdo = nuevo;
                        return;
                    }
                } else {
                    auxiliar = auxiliar.hijoDerecho;
                    if (auxiliar == null) {
                        padre.hijoDerecho = nuevo;
                        return;
                    }
                }
            }
        }
    }

    public boolean EstaVacio() {
        return raiz == null;
    }

    //Recorridos
    //Metodo para recorrer el arbol InOrden (Izquierda, Raiz, Derecha)
    public void InOrden(NodoArbol raiz, DefaultTableModel nodos) {
        if (raiz != null) {

            InOrden(raiz.hijoIzquierdo, nodos);
            nodos.addRow(new Object[]{raiz.nombre, raiz.dpi, raiz.depa, raiz.municipio, raiz.lugVacuna, raiz.NumDosis, raiz.FVacuna1, raiz.FVacuna2, raiz.FVacuna3});
            InOrden(raiz.hijoDerecho, nodos);

        }
    }

    //Metodo para recorrer el arboel PreOrden (Raiz, Izquierda, Derecha)
    public void PreOrden(NodoArbol raiz, DefaultTableModel nodos) {
        if (raiz != null) {

            nodos.addRow(new Object[]{raiz.nombre, raiz.dpi, raiz.depa, raiz.municipio, raiz.lugVacuna, raiz.NumDosis, raiz.FVacuna1, raiz.FVacuna2, raiz.FVacuna3});
            PreOrden(raiz.hijoIzquierdo, nodos);
            PreOrden(raiz.hijoDerecho, nodos);

        }
    }

    //Metodo para recorrer el arbol PostOrden (Izquierda, Derecha, Raiz)
    public void PostOrden(NodoArbol raiz, DefaultTableModel nodos) {
        if (raiz != null) {

            PostOrden(raiz.hijoIzquierdo, nodos);
            PostOrden(raiz.hijoDerecho, nodos);
            nodos.addRow(new Object[]{raiz.nombre, raiz.dpi, raiz.depa, raiz.municipio, raiz.lugVacuna, raiz.NumDosis, raiz.FVacuna1, raiz.FVacuna2, raiz.FVacuna3});
            
        }
    }

    //Metodo para buscar un Nodo en el Arbol
    public NodoArbol BuscarNodo(long d) {
        NodoArbol aux = raiz;

        while (aux.dpi != d) {
            if (d < aux.dpi) {
                aux = aux.hijoIzquierdo;
            } else {
                aux = aux.hijoDerecho;
            }
            if (aux == null) {
                return null;
            }
        }
        return aux;
    }

    public boolean MostrarNodoBuscado(long dpi, DefaultTableModel nodos) {
        NodoArbol nodoEncontrado = BuscarNodo(dpi);
        if (nodoEncontrado != null) {
            nodos.addRow(new Object[]{nodoEncontrado.nombre, nodoEncontrado.dpi, nodoEncontrado.depa, nodoEncontrado.municipio, nodoEncontrado.lugVacuna, nodoEncontrado.NumDosis, nodoEncontrado.FVacuna1, nodoEncontrado.FVacuna2, nodoEncontrado.FVacuna3});
            return true;
        }
        return false;
    }

    //Métodos para guardar en archivo de texto usando InOrden
    public void guardarEnArchivoInOrden(String Archivo) {
        File archivo = new File(Archivo);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(archivo))) {
            guardarInOrden(raiz, writer);
            JOptionPane.showMessageDialog(null, "Datos guardados en el archivo " + Archivo + " usando InOrden. Ruta: " + archivo.getAbsolutePath(), "Guardado", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void guardarInOrden(NodoArbol r, BufferedWriter writer) throws IOException {
        if (r != null) {
            guardarInOrden(r.hijoIzquierdo, writer);
            writer.write(r.nombre + "\t" + new DecimalFormat("#").format(r.dpi));
            writer.newLine();
            guardarInOrden(r.hijoDerecho, writer);
        }
    }

    public boolean EliminarNodo(long d) {
        NodoArbol auxiliar = raiz;
        NodoArbol padre = raiz;
        boolean EsHijoIzq = true;

        while (auxiliar.dpi != d) {
            padre = auxiliar;
            if (d < auxiliar.dpi) {
                EsHijoIzq = true;
                auxiliar = auxiliar.hijoIzquierdo;
            } else {
                EsHijoIzq = false;
                auxiliar = auxiliar.hijoDerecho;
            }
            if (auxiliar == null) {
                return false; //nunca lo encontro
            }
        }//fin del while

        if (auxiliar.hijoIzquierdo == null && auxiliar.hijoDerecho == null) {//Si es una HOja o solo la raiz
            if (auxiliar == raiz) {
                raiz = null; //el nodo a eliminar es la raiz y apuntamos a null
            } else if (EsHijoIzq) {
                padre.hijoIzquierdo = null;
            } else {
                padre.hijoDerecho = null;
            }
        } else if (auxiliar.hijoDerecho == null) {
            if (auxiliar == raiz) {
                raiz = auxiliar.hijoIzquierdo;
            } else if (EsHijoIzq) {
                padre.hijoIzquierdo = auxiliar.hijoIzquierdo;
            } else {
                padre.hijoDerecho = auxiliar.hijoIzquierdo;
            }
        } else if (auxiliar.hijoIzquierdo == null) {
            if (auxiliar == raiz) {
                raiz = auxiliar.hijoDerecho;
            } else if (EsHijoIzq) {
                padre.hijoIzquierdo = auxiliar.hijoDerecho;
            } else {
                padre.hijoDerecho = auxiliar.hijoDerecho;
            }
        } else {
            NodoArbol reemplazo = ObtenerNodoReemplazo(auxiliar);
            if (auxiliar == raiz) {
                raiz = reemplazo;
            } else if (EsHijoIzq) {
                padre.hijoIzquierdo = reemplazo;
            } else {
                padre.hijoDerecho = reemplazo;
            }
            reemplazo.hijoIzquierdo = auxiliar.hijoIzquierdo;
        }
        return true;
    }

    public NodoArbol ObtenerNodoReemplazo(NodoArbol nodoreemp) {
        NodoArbol reemplazopadre = nodoreemp;
        NodoArbol reemplazo = nodoreemp;
        NodoArbol auxiliar = nodoreemp.hijoDerecho;

        while (auxiliar != null) {
            reemplazopadre = reemplazo;
            reemplazo = auxiliar;
            auxiliar = auxiliar.hijoIzquierdo;
        }
        if (reemplazo != nodoreemp.hijoDerecho) {
            reemplazopadre.hijoIzquierdo = reemplazo.hijoDerecho;
            reemplazo.hijoDerecho = nodoreemp.hijoDerecho;
        }
        return reemplazo;
    }

    public void actualizarDatos(long dpi, String nombre, String departamento, String municipio, String lugarVacunacion, int cantidadDosis, String fechaVacuna1, String fechaVacuna2, String fechaVacuna3) {
        NodoArbol actualizar = BuscarNodo(dpi);
        if (actualizar != null) {
            actualizar.setNombre(nombre);
            actualizar.setDepa(departamento);
            actualizar.setMunicipio(municipio);
            actualizar.setLugVacuna(lugarVacunacion);
            actualizar.setNumDosis(cantidadDosis);
            actualizar.setFVacuna1(fechaVacuna1);
            actualizar.setFVacuna2(fechaVacuna2);
            actualizar.setFVacuna3(fechaVacuna3);
            //JOptionPane.showMessageDialog(null, "Datos actualizados correctamente", "Actualizando", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Nodo no encontrado", "Actualizacion", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void actualizarDatos(long dpi, String nombre, String departamento, String municipio, String lugarVacunacion, int cantidadDosis, String fechaVacuna1, String fechaVacuna2, String fechaVacuna3, long dpiAnterior) {
        NodoArbol actualizar = BuscarNodo(dpiAnterior);
        if (actualizar != null) {
            if (actualizar.dpi == dpi) {
                actualizar.setNombre(nombre);
                actualizar.setDepa(departamento);
                actualizar.setMunicipio(municipio);
                actualizar.setLugVacuna(lugarVacunacion);
                actualizar.setNumDosis(cantidadDosis);
                actualizar.setFVacuna1(fechaVacuna1);
                actualizar.setFVacuna2(fechaVacuna2);
                actualizar.setFVacuna3(fechaVacuna3);
                JOptionPane.showMessageDialog(null, "Datos actualizados correctamente", "Actualizando", JOptionPane.INFORMATION_MESSAGE);
            } else {
                EliminarNodo(actualizar.dpi);
                AgregarNodo(nombre, dpi);
                NodoArbol actualizar2 = BuscarNodo(dpi);
                actualizar2.setNombre(nombre);
                actualizar2.setDepa(departamento);
                actualizar2.setMunicipio(municipio);
                actualizar2.setLugVacuna(lugarVacunacion);
                actualizar2.setNumDosis(cantidadDosis);
                actualizar2.setFVacuna1(fechaVacuna1);
                actualizar2.setFVacuna2(fechaVacuna2);
                actualizar2.setFVacuna3(fechaVacuna3);
                JOptionPane.showMessageDialog(null, "Datos actualizados correctamente", "Actualizando", JOptionPane.INFORMATION_MESSAGE);
            }

        } else {
            JOptionPane.showMessageDialog(null, "Nodo no encontrado", "Actualizacion", JOptionPane.ERROR_MESSAGE);
        }
    }

    public String guardarPreOrdenTXT(NodoArbol raiz) {
        StringBuilder guardarString = new StringBuilder();
        recorrerPreOrden(raiz, guardarString);
        return guardarString.toString();
    }

    private void recorrerPreOrden(NodoArbol raiz, StringBuilder guardarString) {
        if (raiz != null) {
            guardarString.append(raiz.nombre).append("|").append(raiz.dpi).append("|").append(raiz.depa).append("|").append(raiz.municipio).append("|").append(raiz.lugVacuna).append("|").append(raiz.NumDosis).append("|").append(raiz.FVacuna1).append("|").append(raiz.FVacuna2).append("|").append(raiz.FVacuna3).append("\n");

            recorrerPreOrden(raiz.hijoIzquierdo, guardarString);

            recorrerPreOrden(raiz.hijoDerecho, guardarString);
        }
    }

    public void encryptRegistro(NodoArbol raiz, int desplazamiento) {
        if (raiz != null) {
            encryptRegistro(raiz.hijoIzquierdo, desplazamiento);
            
            raiz.nombre = encrypt(raiz.nombre, desplazamiento);
            raiz.dpi = Long.parseLong(encrypt(String.valueOf(raiz.dpi), desplazamiento));
            raiz.depa = encrypt(raiz.depa, desplazamiento);
            raiz.municipio = encrypt(raiz.municipio, desplazamiento);
            raiz.lugVacuna = encrypt(raiz.lugVacuna, desplazamiento);
            raiz.NumDosis = Integer.parseInt(encrypt(String.valueOf(raiz.NumDosis), desplazamiento));
            raiz.FVacuna1 = encrypt(raiz.FVacuna1, desplazamiento);
            raiz.FVacuna2 = encrypt(raiz.FVacuna2, desplazamiento);
            raiz.FVacuna3 = encrypt(raiz.FVacuna3, desplazamiento);
            
            encryptRegistro(raiz.hijoDerecho, desplazamiento);
        }
    }

    public void decryptRegistro(NodoArbol raiz, int desplazamiento) {
        if (raiz != null) {
            decryptRegistro(raiz.hijoIzquierdo, desplazamiento);
            
            raiz.nombre = decrypt(raiz.nombre, desplazamiento);
            raiz.dpi = Long.parseLong(decrypt(String.valueOf(raiz.dpi), desplazamiento));
            raiz.depa = decrypt(raiz.depa, desplazamiento);
            raiz.municipio = decrypt(raiz.municipio, desplazamiento);
            raiz.lugVacuna = decrypt(raiz.lugVacuna, desplazamiento);
            raiz.NumDosis = Integer.parseInt(decrypt(String.valueOf(raiz.NumDosis), desplazamiento));
            raiz.FVacuna1 = decrypt(raiz.FVacuna1, desplazamiento);
            raiz.FVacuna2 = decrypt(raiz.FVacuna2, desplazamiento);
            raiz.FVacuna3 = decrypt(raiz.FVacuna3, desplazamiento);
            
            decryptRegistro(raiz.hijoDerecho, desplazamiento);
        }
    }

    public static String encrypt(String encrypt, int desplazamiento) {
        if (encrypt != null && !"null".equals(encrypt)) {
            StringBuilder result = new StringBuilder();
            for (int i = 0; i < encrypt.length(); i++) {
                char ch = encrypt.charAt(i);
                if (Character.isDigit(ch)) {
                    char base = '0';
                    ch = (char) ((ch - base + desplazamiento) % 10 + base);
                    result.append(ch);
                } else {
                    int index = CHAR_LIST.indexOf(ch);
                    if (index != -1) {
                        int shiftedIndex = (index + desplazamiento) % CHAR_LIST.length();
                        result.append(CHAR_LIST.charAt(shiftedIndex));
                    } else {
                        result.append(ch);
                    }
                }
            }
            return result.toString();
        } else {
            return null;
        }
    }

    public static String decrypt(String decrypt, int desplazamiento) {
        if (decrypt != null && !"null".equals(decrypt)) {
            StringBuilder result = new StringBuilder();
            for (int i = 0; i < decrypt.length(); i++) {
                char ch = decrypt.charAt(i);
                if (Character.isDigit(ch)) {
                    char base = '0';
                    ch = (char) ((ch - base - desplazamiento + 10) % 10 + base);
                    result.append(ch);
                } else {
                    int index = CHAR_LIST.indexOf(ch);
                    if (index != -1) {
                        int shiftedIndex = (index - desplazamiento + CHAR_LIST.length()) % CHAR_LIST.length();
                        result.append(CHAR_LIST.charAt(shiftedIndex));
                    } else {
                        result.append(ch);
                    }
                }
            }
            return result.toString();
        } else {
            return null;
        }
    }

    public int nodosCompletos(NodoArbol subArbol) {
        if (subArbol == null) {
            return 0;
        } else {
            if (subArbol.hijoIzquierdo != null && subArbol.hijoDerecho != null) {
                return nodosCompletos(subArbol.hijoIzquierdo) + nodosCompletos(subArbol.hijoDerecho) + 1;
            }
            return nodosCompletos(subArbol.hijoIzquierdo) + nodosCompletos(subArbol.hijoDerecho);
        }
    }
    
    public int contarNodos(NodoArbol nodo) {
        if (nodo == null) {
            return 0;
        }
        return 1 + contarNodos(nodo.hijoIzquierdo) + contarNodos(nodo.hijoDerecho);
    }
}
