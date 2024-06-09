/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

/**
 *
 * @author VICTUS
 */
public class ImagenGrafica extends JPanel {
    private ArbolBinario arbol;
    private ArbolBinarioAVL arbolavl;
    private static final int DIAMETRO_INICIAL = 80;
    private static final int RADIO_INICIAL = DIAMETRO_INICIAL / 2;
    private static final int ANCHO_INICIAL = 120;
    private int diametro;
    private int radio;
    private int ancho;
    private static final int OFFSET_MARGEN = 210; // Margen adicional para ajustar la posición de ArbolBinario
    private static final int OFFSET_MARGEN_AVL = 290; // Margen adicional para ajustar la posición de ArbolBinarioAVL

    public void setArbol(ArbolBinario arbol) {
        this.arbol = arbol;
        this.arbolavl = null; // Limpiamos el árbol AVL
        repaint();
    }

    public void setArbolAVL(ArbolBinarioAVL arbolAVL) {
        this.arbolavl = arbolAVL;
        this.arbol = null; // Limpiamos el árbol binario de búsqueda
        repaint();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        if (arbol != null && arbol.raiz != null) {
            ajustarEscala(arbol.contarNodos(arbol.raiz));
            int offsetX = calcularOffsetInicial(arbol.raiz) + OFFSET_MARGEN;
            pintarBinario(g, offsetX, 20, arbol.raiz);
        }
        if (arbolavl != null && arbolavl.raiz != null) {
            ajustarEscala(arbolavl.contarNodos(arbolavl.raiz));
            int offsetX = calcularOffsetInicial(arbolavl.raiz) + OFFSET_MARGEN_AVL;
            pintarAvl(g, offsetX, 20, arbolavl.raiz);
        }
    }

    private void ajustarEscala(int numNodos) {
        double factorEscala = Math.max(0.5, 1.0 - (numNodos * 0.005));
        diametro = (int) (DIAMETRO_INICIAL * factorEscala);
        radio = diametro / 2;
        ancho = (int) (ANCHO_INICIAL * factorEscala);
    }

    private int calcularOffsetInicial(NodoArbol nodo) {
        int nodosCompletos = arbol.nodosCompletos(nodo);
        return (getWidth() / 2) - (nodosCompletos * ancho / 2);
    }

    private int calcularOffsetInicial(NodoArbolAVL nodo) {
        int nodosCompletos = arbolavl.nodosCompletos(nodo);
        return (getWidth() / 2) - (nodosCompletos * ancho / 2);
    }

    public void pintarBinario(Graphics g, int x, int y, NodoArbol subArbol) {
        if (subArbol != null) {
            int extra = arbol.nodosCompletos(subArbol) * ancho / 2;
            g.setColor(Color.WHITE);
            g.fillRect(x, y, ancho, diametro / 3 * 2); // Ajuste de la altura del rectángulo
            g.setColor(Color.BLACK);
            g.drawRect(x, y, ancho, diametro / 3 * 2); // Ajuste de la altura del rectángulo
            g.drawString(String.valueOf(subArbol.dpi), x + 5, y + 15);
            g.drawString(subArbol.nombre, x + 5, y + 35);

            if (subArbol.hijoIzquierdo != null) {
                g.drawLine(x + ancho / 2, y + diametro / 3 * 2, x - ancho / 2 - extra + ancho / 2, y + diametro / 3 * 2 + 30);
            }

            if (subArbol.hijoDerecho != null) {
                g.drawLine(x + ancho / 2, y + diametro / 3 * 2, x + ancho / 2 + extra + ancho / 2, y + diametro / 3 * 2 + 30);
            }

            pintarBinario(g, x - ancho / 2 - extra, y + diametro / 3 * 2 + 30, subArbol.hijoIzquierdo);
            pintarBinario(g, x + ancho / 2 + extra, y + diametro / 3 * 2 + 30, subArbol.hijoDerecho);
        }
    }

    public void pintarAvl(Graphics g, int x, int y, NodoArbolAVL subArbol) {
        if (subArbol != null) {
            int extra = arbolavl.nodosCompletos(subArbol) * ancho / 2;
            g.setColor(Color.WHITE);
            g.fillRect(x, y, ancho, diametro / 3 * 2); // Ajuste de la altura del rectángulo
            g.setColor(Color.BLACK);
            g.drawRect(x, y, ancho, diametro / 3 * 2); // Ajuste de la altura del rectángulo
            g.drawString(String.valueOf(subArbol.dpi), x + 5, y + 15);
            g.drawString(subArbol.nombre, x + 5, y + 35);

            if (subArbol.hijoIzquierdo != null) {
                g.drawLine(x + ancho / 2, y + diametro / 3 * 2, x - ancho / 2 - extra + ancho / 2, y + diametro / 3 * 2 + 30);
            }

            if (subArbol.hijoDerecho != null) {
                g.drawLine(x + ancho / 2, y + diametro / 3 * 2, x + ancho / 2 + extra + ancho / 2, y + diametro / 3 * 2 + 30);
            }

            pintarAvl(g, x - ancho / 2 - extra, y + diametro / 3 * 2 + 30, subArbol.hijoIzquierdo);
            pintarAvl(g, x + ancho / 2 + extra, y + diametro / 3 * 2 + 30, subArbol.hijoDerecho);
        }
    }
   /* private ArbolBinario arbol;
    private ArbolBinarioAVL arbolavl;
    private static final int DIAMETRO_INICIAL = 90;
    private static final int RADIO_INICIAL = DIAMETRO_INICIAL / 2;
    private static final int ANCHO_INICIAL = 140;
    private int diametro;
    private int radio;
    private int ancho;
    private static final int OFFSET_MARGEN = 210; // Incrementar este valor para mover más a la derecha

    public void setArbol(ArbolBinario arbol) {
        this.arbol = arbol;
        this.arbolavl = null; // Limpiamos el árbol AVL
        repaint();
    }

    public void setArbolAVL(ArbolBinarioAVL arbolAVL) {
        this.arbolavl = arbolAVL;
        this.arbol = null; // Limpiamos el árbol binario de búsqueda
        repaint();
    }

    public void paint(Graphics g) {
        super.paint(g);
        if (arbol != null && arbol.raiz != null) {
            ajustarEscala(arbol.contarNodos(arbol.raiz));
            int offsetX = calcularOffsetInicial(arbol.raiz) + OFFSET_MARGEN;
            pintarBinario(g, offsetX, 20, arbol.raiz);
        }
        if (arbolavl != null && arbolavl.raiz != null) {
            ajustarEscala(arbolavl.contarNodos(arbolavl.raiz));
            int offsetX = calcularOffsetInicial(arbolavl.raiz) + OFFSET_MARGEN;
            pintarAvl(g, offsetX, 20, arbolavl.raiz);
        }
    }

    private void ajustarEscala(int numNodos) {
        double factorEscala = Math.max(0.5, 1.0 - (numNodos * 0.005));
        diametro = (int) (DIAMETRO_INICIAL * factorEscala);
        radio = diametro / 2;
        ancho = (int) (ANCHO_INICIAL * factorEscala);
    }

    private int calcularOffsetInicial(NodoArbol nodo) {
        int nodosCompletos = arbol.nodosCompletos(nodo);
        return (getWidth() / 2) - (nodosCompletos * ancho / 2);
    }

    private int calcularOffsetInicial(NodoArbolAVL nodo) {
        int nodosCompletos = arbolavl.nodosCompletos(nodo);
        return (getWidth() / 2) - (nodosCompletos * ancho / 2);
    }

    public void pintarBinario(Graphics g, int x, int y, NodoArbol subArbol) {
        if (subArbol != null) {
            int extra = arbol.nodosCompletos(subArbol) * ancho / 2;
            g.setColor(Color.WHITE);
            g.fillRect(x, y, ancho, diametro / 3 * 2); // Ajuste de la altura del rectángulo
            g.setColor(Color.BLACK);
            g.drawRect(x, y, ancho, diametro / 3 * 2); // Ajuste de la altura del rectángulo
            g.drawString(String.valueOf(subArbol.dpi), x + 5, y + 15);
            g.drawString(subArbol.nombre, x + 5, y + 35);

            if (subArbol.hijoIzquierdo != null) {
                g.drawLine(x + ancho / 2, y + diametro / 3 * 2, x - ancho / 2 - extra + ancho / 2, y + diametro / 3 * 2 + 30);
            }

            if (subArbol.hijoDerecho != null) {
                g.drawLine(x + ancho / 2, y + diametro / 3 * 2, x + ancho / 2 + extra + ancho / 2, y + diametro / 3 * 2 + 30);
            }

            pintarBinario(g, x - ancho / 2 - extra, y + diametro / 3 * 2 + 30, subArbol.hijoIzquierdo);
            pintarBinario(g, x + ancho / 2 + extra, y + diametro / 3 * 2 + 30, subArbol.hijoDerecho);
        }
    }

    public void pintarAvl(Graphics g, int x, int y, NodoArbolAVL subArbol) {
        if (subArbol != null) {
            int extra = arbolavl.nodosCompletos(subArbol) * ancho / 2;
            g.setColor(Color.WHITE);
            g.fillRect(x, y, ancho, diametro / 3 * 2); // Ajuste de la altura del rectángulo
            g.setColor(Color.BLACK);
            g.drawRect(x, y, ancho, diametro / 3 * 2); // Ajuste de la altura del rectángulo
            g.drawString(String.valueOf(subArbol.dpi), x + 5, y + 15);
            g.drawString(subArbol.nombre, x + 5, y + 35);

            if (subArbol.hijoIzquierdo != null) {
                g.drawLine(x + ancho / 2, y + diametro / 3 * 2, x - ancho / 2 - extra + ancho / 2, y + diametro / 3 * 2 + 30);
            }

            if (subArbol.hijoDerecho != null) {
                g.drawLine(x + ancho / 2, y + diametro / 3 * 2, x + ancho / 2 + extra + ancho / 2, y + diametro / 3 * 2 + 30);
            }

            pintarAvl(g, x - ancho / 2 - extra, y + diametro / 3 * 2 + 30, subArbol.hijoIzquierdo);
            pintarAvl(g, x + ancho / 2 + extra, y + diametro / 3 * 2 + 30, subArbol.hijoDerecho);
        }
    }
   /* private ArbolBinario arbol;
    private ArbolBinarioAVL arbolavl;
    private static final int DIAMETRO_INICIAL = 80;
    private static final int RADIO_INICIAL = DIAMETRO_INICIAL / 2;
    private static final int ANCHO_INICIAL = 140;
    private int diametro;
    private int radio;
    private int ancho;

    public void setArbol(ArbolBinario arbol) {
        this.arbol = arbol;
        this.arbolavl = null; // Limpiamos el árbol AVL
        repaint();
    }

    public void setArbolAVL(ArbolBinarioAVL arbolAVL) {
        this.arbolavl = arbolAVL;
        this.arbol = null; // Limpiamos el árbol binario de búsqueda
        repaint();
    }

    public void paint(Graphics g) {
        super.paint(g);
        if (arbol != null && arbol.raiz != null) {
            ajustarEscala(arbol.contarNodos(arbol.raiz));
            pintarBinario(g, getWidth() / 2, 20, arbol.raiz);
        }
        if (arbolavl != null && arbolavl.raiz != null) {
            ajustarEscala(arbolavl.contarNodos(arbolavl.raiz));
            pintarAvl(g, getWidth() / 2, 20, arbolavl.raiz);
        }
    }

    private void ajustarEscala(int numNodos) {
        double factorEscala = Math.max(0.5, 1.0 - (numNodos * 0.005));
        diametro = (int) (DIAMETRO_INICIAL * factorEscala);
        radio = diametro / 2;
        ancho = (int) (ANCHO_INICIAL * factorEscala);
    }

    public void pintarBinario(Graphics g, int x, int y, NodoArbol subArbol) {
        if (subArbol != null) {
            int extra = arbol.nodosCompletos(subArbol) * ancho / 2;
            g.setColor(Color.WHITE);
            g.fillRect(x, y, ancho, diametro / 3 * 2); // Ajuste de la altura del rectángulo
            g.setColor(Color.BLACK);
            g.drawRect(x, y, ancho, diametro / 3 * 2); // Ajuste de la altura del rectángulo
            g.drawString(String.valueOf(subArbol.dpi), x + 5, y + 15);
            g.drawString(subArbol.nombre, x + 5, y + 35);

            if (subArbol.hijoIzquierdo != null) {
                g.drawLine(x + ancho / 2, y + diametro / 3 * 2, x - ancho / 2 - extra + ancho / 2, y + diametro / 3 * 2 + 30);
            }

            if (subArbol.hijoDerecho != null) {
                g.drawLine(x + ancho / 2, y + diametro / 3 * 2, x + ancho / 2 + extra + ancho / 2, y + diametro / 3 * 2 + 30);
            }

            pintarBinario(g, x - ancho / 2 - extra, y + diametro / 3 * 2 + 30, subArbol.hijoIzquierdo);
            pintarBinario(g, x + ancho / 2 + extra, y + diametro / 3 * 2 + 30, subArbol.hijoDerecho);
        }
    }

    public void pintarAvl(Graphics g, int x, int y, NodoArbolAVL subArbol) {
        if (subArbol != null) {
            int extra = arbolavl.nodosCompletos(subArbol) * ancho / 2;
            g.setColor(Color.WHITE);
            g.fillRect(x, y, ancho, diametro / 3 * 2); // Ajuste de la altura del rectángulo
            g.setColor(Color.BLACK);
            g.drawRect(x, y, ancho, diametro / 3 * 2); // Ajuste de la altura del rectángulo
            g.drawString(String.valueOf(subArbol.dpi), x + 5, y + 15);
            g.drawString(subArbol.nombre, x + 5, y + 35);

            if (subArbol.hijoIzquierdo != null) {
                g.drawLine(x + ancho / 2, y + diametro / 3 * 2, x - ancho / 2 - extra + ancho / 2, y + diametro / 3 * 2 + 30);
            }

            if (subArbol.hijoDerecho != null) {
                g.drawLine(x + ancho / 2, y + diametro / 3 * 2, x + ancho / 2 + extra + ancho / 2, y + diametro / 3 * 2 + 30);
            }

            pintarAvl(g, x - ancho / 2 - extra, y + diametro / 3 * 2 + 30, subArbol.hijoIzquierdo);
            pintarAvl(g, x + ancho / 2 + extra, y + diametro / 3 * 2 + 30, subArbol.hijoDerecho);
        }
    }
*/
}
