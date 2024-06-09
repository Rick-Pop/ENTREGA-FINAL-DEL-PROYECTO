/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author VICTUS
 */
public class ArbolBinarioAVL {

    public static NodoArbolAVL raiz;
    static final String CHAR_LIST = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyzÁÉÍÓÚáéíóúÑñÜü";

    public ArbolBinarioAVL() {

    }

    public NodoArbolAVL buscarN(long dpi, NodoArbolAVL raiz) {
        if (raiz == null) {
            return null;
        } else if (raiz.dpi == dpi) {
            return raiz;
        } else if (dpi > raiz.dpi) {
            return buscarN(dpi, raiz.hijoDerecho);
        } else {
            return buscarN(dpi, raiz.hijoIzquierdo);
        }
    }

    //Obtener el Factor de Equilibrio
    public int ObtenerFactEqui(NodoArbolAVL f) {
        if (f == null) {
            return -1;
        } else {
            return f.factEqui;
        }
    }

    //Rotacion Izquierda
    public NodoArbolAVL rotacionIzq(NodoArbolAVL nodo) {
        NodoArbolAVL aux = nodo.hijoIzquierdo;
        nodo.hijoIzquierdo = aux.hijoDerecho;
        aux.hijoDerecho = nodo;

        nodo.factEqui = Math.max(ObtenerFactEqui(nodo.hijoIzquierdo), ObtenerFactEqui(nodo.hijoDerecho)) + 1;
        aux.factEqui = Math.max(ObtenerFactEqui(aux.hijoIzquierdo), ObtenerFactEqui(aux.hijoDerecho)) + 1;

        return aux;
    }

    //Rotacion Derecha
    public NodoArbolAVL rotacionDer(NodoArbolAVL nodo) {
        NodoArbolAVL aux = nodo.hijoDerecho;
        nodo.hijoDerecho = aux.hijoIzquierdo;
        aux.hijoIzquierdo = nodo;

        nodo.factEqui = Math.max(ObtenerFactEqui(nodo.hijoIzquierdo), ObtenerFactEqui(nodo.hijoDerecho)) + 1;
        aux.factEqui = Math.max(ObtenerFactEqui(aux.hijoIzquierdo), ObtenerFactEqui(aux.hijoDerecho)) + 1;

        return aux;
    }

    //Rotacion Doble Izquierda
    public NodoArbolAVL rotacionDobleIzq(NodoArbolAVL nodo) {
        NodoArbolAVL tmp;
        nodo.hijoIzquierdo = rotacionDer(nodo.hijoIzquierdo);
        tmp = rotacionIzq(nodo);

        return tmp;
    }

    //Rotacion Doble Derecha
    public NodoArbolAVL rotacionDobleDer(NodoArbolAVL nodo) {
        NodoArbolAVL tmp;
        nodo.hijoDerecho = rotacionIzq(nodo.hijoDerecho);
        tmp = rotacionDer(nodo);
        return tmp;
    }

    //Insertar al arbol
    public NodoArbolAVL insertarAVL(NodoArbolAVL nuevo, NodoArbolAVL subArbol) {
        NodoArbolAVL nuevoPadre = subArbol;
        if (nuevo.dpi < subArbol.dpi) {
            if (subArbol.hijoIzquierdo == null) {
                subArbol.hijoIzquierdo = nuevo;
            } else {
                subArbol.hijoIzquierdo = insertarAVL(nuevo, subArbol.hijoIzquierdo);
                if ((ObtenerFactEqui(subArbol.hijoIzquierdo) - ObtenerFactEqui(subArbol.hijoDerecho)) == 2) {
                    if (nuevo.dpi < subArbol.hijoIzquierdo.dpi) {
                        nuevoPadre = rotacionIzq(subArbol);
                    } else {
                        nuevoPadre = rotacionDobleIzq(subArbol);
                    }
                }
            }
        } else if (nuevo.dpi > subArbol.dpi) {
            if (subArbol.hijoDerecho == null) {
                subArbol.hijoDerecho = nuevo;
            } else {
                subArbol.hijoDerecho = insertarAVL(nuevo, subArbol.hijoDerecho);
                if ((ObtenerFactEqui(subArbol.hijoDerecho) - ObtenerFactEqui(subArbol.hijoIzquierdo)) == 2) {
                    if (nuevo.dpi > subArbol.hijoDerecho.dpi) {
                        nuevoPadre = rotacionDer(subArbol);
                    } else {
                        nuevoPadre = rotacionDobleDer(subArbol);
                    }
                }
            }
        } else {
            System.out.println("El nodo ya existe");
        }

        //Actualizar Factor de Equilibrio
        if ((subArbol.hijoIzquierdo == null) && (subArbol.hijoDerecho != null)) {
            subArbol.factEqui = subArbol.hijoDerecho.factEqui + 1;
        } else if ((subArbol.hijoDerecho == null) && (subArbol.hijoIzquierdo != null)) {
            subArbol.factEqui = subArbol.hijoIzquierdo.factEqui + 1;
        } else {
            subArbol.factEqui = Math.max(ObtenerFactEqui(subArbol.hijoIzquierdo), ObtenerFactEqui(subArbol.hijoDerecho)) + 1;
        }
        return nuevoPadre;
    }

    //Metodo para mandar a insert al arbol
    public void insertarAlArbol(String nombre, long dpi) {
        NodoArbolAVL nuevo = new NodoArbolAVL(nombre, dpi);
        if (raiz == null) {
            raiz = nuevo;
        } else {
            raiz = insertarAVL(nuevo, raiz);
        }
    }

    //Comprobar si el arbol esta vacio
    public boolean estaVacio() {
        return raiz == null;
    }

    // Método de eliminar 
    public NodoArbolAVL eliminarNodo(NodoArbolAVL nodo, long dpi) {
        if (nodo == null) {
            return null;
        }

        if (dpi < nodo.dpi) {
            nodo.hijoIzquierdo = eliminarNodo(nodo.hijoIzquierdo, dpi);
            if (ObtenerFactEqui(nodo.hijoDerecho) - ObtenerFactEqui(nodo.hijoIzquierdo) == 2) {
                NodoArbolAVL hijoDerecho = nodo.hijoDerecho;
                if (ObtenerFactEqui(hijoDerecho.hijoIzquierdo) <= ObtenerFactEqui(hijoDerecho.hijoDerecho)) {
                    nodo = rotacionDer(nodo);
                } else {
                    nodo = rotacionDobleDer(nodo);
                }
            }
        } else if (dpi > nodo.dpi) {
            nodo.hijoDerecho = eliminarNodo(nodo.hijoDerecho, dpi);
            if (ObtenerFactEqui(nodo.hijoIzquierdo) - ObtenerFactEqui(nodo.hijoDerecho) == 2) {
                NodoArbolAVL hijoIzq = nodo.hijoIzquierdo;
                if (ObtenerFactEqui(hijoIzq.hijoDerecho) <= ObtenerFactEqui(hijoIzq.hijoIzquierdo)) {
                    nodo = rotacionIzq(nodo);
                } else {
                    nodo = rotacionDobleIzq(nodo);
                }
            }
        } else {
            if (nodo.hijoIzquierdo == null || nodo.hijoDerecho == null) {
                nodo = (nodo.hijoIzquierdo != null) ? nodo.hijoIzquierdo : nodo.hijoDerecho;
            } else {
                NodoArbolAVL tmp = encontrarMinimo(nodo.hijoDerecho);
                nodo.dpi = tmp.dpi;
                nodo.hijoDerecho = eliminarNodo(nodo.hijoDerecho, tmp.dpi);
            }
        }

        if (nodo != null) {
            nodo.factEqui = Math.max(ObtenerFactEqui(nodo.hijoIzquierdo), ObtenerFactEqui(nodo.hijoDerecho)) + 1;
        }
        return nodo;
    }

    // Método de encontrar el minimo para sustituir
    private NodoArbolAVL encontrarMinimo(NodoArbolAVL nodo) {
        while (nodo.hijoIzquierdo != null) {
            nodo = nodo.hijoIzquierdo;
        }
        return nodo;
    }

    // Método para mandar a eliminar nodo
    public boolean eliminacion(long dpi) {
        if (buscarN(dpi, raiz) == null) {
            return false;
        }
        raiz = eliminarNodo(raiz, dpi);
        return true;
    }

    // Método para actualizar nodo
    public void actualizarDatos(long dpi, String nombre, String departamento, String municipio, String lugarVacunacion, int cantidadDosis, String fechaVacuna1, String fechaVacuna2, String fechaVacuna3) {
        NodoArbolAVL actualizar = buscarN(dpi, raiz);
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
        NodoArbolAVL actualizar = buscarN(dpiAnterior, raiz);
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
                eliminacion(actualizar.dpi);
                insertarAlArbol(nombre, dpi);
                NodoArbolAVL actualizar2 = buscarN(dpi, raiz);
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

    //Recorridos
    //Metodo para recorrer el arbol InOrden (Izquierda, Raiz, Derecha)
    public void InOrden(NodoArbolAVL raiz, DefaultTableModel nodos) {
        if (raiz != null) {

            InOrden(raiz.hijoIzquierdo, nodos);
            nodos.addRow(new Object[]{raiz.nombre, raiz.dpi, raiz.depa, raiz.municipio, raiz.lugVacuna, raiz.NumDosis, raiz.FVacuna1, raiz.FVacuna2, raiz.FVacuna3});
            InOrden(raiz.hijoDerecho, nodos);

        }
    }

    //Metodo para recorrer el arboel PreOrden (Raiz, Izquierda, Derecha)
    public void PreOrden(NodoArbolAVL raiz, DefaultTableModel nodos) {
        if (raiz != null) {

            nodos.addRow(new Object[]{raiz.nombre, raiz.dpi, raiz.depa, raiz.municipio, raiz.lugVacuna, raiz.NumDosis, raiz.FVacuna1, raiz.FVacuna2, raiz.FVacuna3});
            PreOrden(raiz.hijoIzquierdo, nodos);
            PreOrden(raiz.hijoDerecho, nodos);

        }
    }

    //Metodo para recorrer el arbol PostOrden (Izquierda, Derecha, Raiz)
    public void PostOrden(NodoArbolAVL raiz, DefaultTableModel nodos) {
        if (raiz != null) {

            PostOrden(raiz.hijoIzquierdo, nodos);
            PostOrden(raiz.hijoDerecho, nodos);
            nodos.addRow(new Object[]{raiz.nombre, raiz.dpi, raiz.depa, raiz.municipio, raiz.lugVacuna, raiz.NumDosis, raiz.FVacuna1, raiz.FVacuna2, raiz.FVacuna3});

        }
    }

    public String guardarPreOrdenTXT(NodoArbolAVL raiz) {
        StringBuilder guardarString = new StringBuilder();
        recorrerPreOrden(raiz, guardarString);
        return guardarString.toString();
    }

    private void recorrerPreOrden(NodoArbolAVL raiz, StringBuilder guardarString) {
        if (raiz != null) {
            guardarString.append(raiz.nombre).append("|").append(raiz.dpi).append("|").append(raiz.depa).append("|").append(raiz.municipio).append("|").append(raiz.lugVacuna).append("|").append(raiz.NumDosis).append("|").append(raiz.FVacuna1).append("|").append(raiz.FVacuna2).append("|").append(raiz.FVacuna3).append("\n");

            recorrerPreOrden(raiz.hijoIzquierdo, guardarString);

            recorrerPreOrden(raiz.hijoDerecho, guardarString);
        }
    }

    public boolean MostrarNodoBuscado(long dpi, DefaultTableModel nodos) {
        NodoArbolAVL nodoEncontrado = buscarN(dpi, raiz);
        if (nodoEncontrado != null) {
            nodos.addRow(new Object[]{nodoEncontrado.nombre, nodoEncontrado.dpi, nodoEncontrado.depa, nodoEncontrado.municipio, nodoEncontrado.lugVacuna, nodoEncontrado.NumDosis, nodoEncontrado.FVacuna1, nodoEncontrado.FVacuna2, nodoEncontrado.FVacuna3});
            return true;
        }
        return false;
    }
    
    public void encryptRegistro(NodoArbolAVL raiz, int desplazamiento) {
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
    
    public void decryptRegistro(NodoArbolAVL raiz, int desplazamiento) {
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
    
    public int nodosCompletos(NodoArbolAVL subArbol) {
        if (subArbol == null) {
            return 0;
        } else {
            if (subArbol.hijoIzquierdo != null && subArbol.hijoDerecho != null) {
                return nodosCompletos(subArbol.hijoIzquierdo) + nodosCompletos(subArbol.hijoDerecho) + 1;
            }
            return nodosCompletos(subArbol.hijoIzquierdo) + nodosCompletos(subArbol.hijoDerecho);
        }
    }
    
    public int contarNodos(NodoArbolAVL nodo) {
        if (nodo == null) {
            return 0;
        }
        return 1 + contarNodos(nodo.hijoIzquierdo) + contarNodos(nodo.hijoDerecho);
    }

}
