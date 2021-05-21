/*
 * Fichero: Editor.java
 * Autor: Chuidiang
 * Fecha: 28/11/06 21:46
 */


import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Event;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.Action;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JScrollPane;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;
import javax.swing.WindowConstants;
import javax.swing.text.DefaultEditorKit;
import javax.swing.text.JTextComponent;


/**
 * Ejemplo de un editor simple, para ver c�mo crear barras de men�, acciones en ellas
 * y algunos truquillos m�s.
 *
 * @author Chuidiang
 *
  */
public class Editor
{
    /** 
     * Area de texto para escribir  
     */
    private JTextArea areaTexto,areaError,areaLexico;
    private JScrollPane scroll;
    private NumeroLinea lineas;

    /**
     * Crea un nuevo objeto Editor.
     */
    public Editor()
    {
        JMenuBar barraMenu = new JMenuBar();
        
        // Area de texto de 24 filas y 80 columnas
        scroll = new JScrollPane();
        areaTexto = new JTextArea(24, 80);
        construyeMenuArchivo(barraMenu);
        construyeMenuEditar(barraMenu);
        JFrame v = construyeVentanaEditor(barraMenu);
        visualizaVentana(v);
    }

    /**
     * main del programa.<br>
     * @param args Se ignoran.
     */
    public static void main(String[] args)
    {
        new Editor();
    }

    /**
     * Construye la ventana con un JTextArea dentro.<br>
     * Se le pasa la barra de men� que a�ade en la parte superior.
     *
     * @param barraMenu Barra de men� para el editor.
     *
     * @return La ventana principal del editor.
     */
    private JFrame construyeVentanaEditor(JMenuBar barraMenu)
    {
        JFrame v = new JFrame("S2H Editor");
        
        // La ventana tiene por defecto un BorderLayout. El men� va en la parte superior,
        // es decir, en el norte NORTH.
        v.getContentPane().add(barraMenu, BorderLayout.NORTH);
        
        // Para que se partan autom�ticamente las l�neas al llegar al final 
        areaTexto.setLineWrap(true);
        
        // Para que el partido se haga respetando las palabras. Solo se parte la
        // linea en los espacios entre palabras.
        areaTexto.setWrapStyleWord(true);
        JScrollPane scroll = new JScrollPane();
        scroll.setViewportView(areaTexto);
        lineas = new NumeroLinea(areaTexto);
        scroll.setRowHeaderView(lineas);
        // El scroll con el JTextArea se añade por defecto en el centro del BorderLayout.
        v.getContentPane().add(scroll);
        
        return v;
    }

    /**
     * Prepara la dimensi�n y posici�n de la ventana y la visualiza.
     *
     * @param v Ventana a visualizar.
     */
    private void visualizaVentana(JFrame v)
    {
    	// Para salir del programa si se pulsa la x de la esquina superior derecha de
    	// la ventana.
        v.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        v.addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent evt){
                close();
            }
        });
        // La ventana coge el tama�o justo para que se vean completamente los componentes
        // que tiene dentro, es decir, el JTextArea y el men�.
        v.pack();

        // Se obtienen las dimensiones en pixels de la pantalla.
        Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
        
        // Se obtienen las dimensiones en pixels de la ventana.
        Dimension ventana = v.getSize();
        
        // Una cuenta para situar la ventana en el centro de la pantalla.
        v.setLocation(
            (pantalla.width - ventana.width) / 2,
            (pantalla.height - ventana.height) / 2);
        
        // Se visualiza la ventana.
        v.setVisible(true);
    }

    /**
     * Contruye el men� "Archivo", con las opciones "Salvar", "Cargar" y "Salir".
     *
     * @param barraMenu Barra de men� en la que a�adir el men� "Archivo".
     */
    private void close(){
        if (JOptionPane.showConfirmDialog(null, "¿Desea realmente salir del sistema?",
                "Salir del sistema", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
            System.exit(0);
    }
    private void construyeMenuArchivo(JMenuBar barraMenu)
    {
    	// Se crean los tres items del men�.
        JMenuItem salvar = new JMenuItem(new AccionSalvarFichero(areaTexto));
        JMenuItem cargar = new JMenuItem(new AccionCargarFichero(areaTexto));
        JMenuItem salir = new JMenuItem(new AccionSalir(areaTexto));
        
        // Se crea el men� "Archivo"
        JMenu menuArchivo = new JMenu("Archivo");
        
        // Se a�aden los items al men�
        menuArchivo.add(salvar);
        menuArchivo.add(cargar);
        menuArchivo.add(salir);
        
        // Se a�ade el men� a la barra general de men�.
        barraMenu.add(menuArchivo);
    }

    /**
     * Construye el men� "Editar" con las opciones "Buscar", "Cortar", "Copiar" y "Pegar".
     *
     * @param barraMenu Barra de men� a la que a�adir el men� "Editar".
     */
    private void construyeMenuEditar(JMenuBar barraMenu)
    {
    	// Construimos el item del men� "Buscar".
        JMenuItem buscar = new JMenuItem(new AccionBuscar(areaTexto));
        
        // Construimos el item del men� "Copiar". Para ello aprovechamos el Action que el
        // JTextArea tiene dentro.
        
        // Se obtiene el Action de "copiar" que tiene el JTextArea dentro.
        Action accionCopiar = areaTexto.getActionMap()
                                       .get(DefaultEditorKit.copyAction);
        
        // Ponemos el nombre en espa�ol. Por defecto es algo como "copy to clipboard".
        accionCopiar.putValue(Action.NAME, "Copiar");
        
        // Ponemos una tecla aceleradora. No hace falta, pero si no la ponemos no se ver�
        // en el men�, aunque funcionar� igual. Ctrl-C siver para copiar.
        accionCopiar.putValue(
            Action.ACCELERATOR_KEY,
            KeyStroke.getAWTKeyStroke('C', Event.CTRL_MASK));

        // Se crea el item de men� con la accion copiar.
        JMenuItem copiar = new JMenuItem(accionCopiar);

        // Idem para cortar.
        Action accionCortar = areaTexto.getActionMap()
                                       .get(DefaultEditorKit.cutAction);
        accionCortar.putValue(Action.NAME, "Cortar");
        accionCortar.putValue(
            Action.ACCELERATOR_KEY,
            KeyStroke.getAWTKeyStroke('X', Event.CTRL_MASK));
        JMenuItem cortar = new JMenuItem(accionCortar);

        // Idem para pegar
        Action accionPegar = areaTexto.getActionMap()
                                      .get(DefaultEditorKit.pasteAction);
        accionPegar.putValue(Action.NAME, "Pegar");
        accionPegar.putValue(
            Action.ACCELERATOR_KEY,
            KeyStroke.getAWTKeyStroke('V', Event.CTRL_MASK));
        JMenuItem pegar = new JMenuItem(accionPegar);

        // Se crea el menu de "editar" y se le a�aden los items, con un separador entre
        // "buscar" y "cortar".
        JMenu menuEditar = new JMenu("Editar");
        menuEditar.add(buscar);
        menuEditar.add(new JSeparator());
        menuEditar.add(cortar);
        menuEditar.add(copiar);
        menuEditar.add(pegar);
        
        // Se a�ade el men� "editar" a la barra de men�.
        barraMenu.add(menuEditar);
    }
}
