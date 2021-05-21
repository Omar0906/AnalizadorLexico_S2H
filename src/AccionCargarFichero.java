/*
 * Fichero: AccionCargarFichero.java
 * Autor: Chuidiang
 * Fecha: 29/11/06 20:39
 */


import java.awt.Event;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.Action;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;
import javax.swing.text.JTextComponent;


/**
 * Acci�n para cargar el texto de un archivo y mostrarlo en el JTextArea.<br>
 * Hereda de AbstractAccionFichero as� que s�lo tiene que inicializar las propiedades
 * de la acci�n y preocuparse de c�mo cargar el archivo.
 *
 * @author Chuidiang
 *
  */
public class AccionCargarFichero extends AbstractAccionFichero
{
    /**
     * serial uid
     */
    private static final long serialVersionUID = 7764384764137362305L;

    /**
     * Crea un nuevo objeto AccionCargarFichero.
     *
     * @param componenteTexto Componente en el que mostrar el texto leido.
     */
    public AccionCargarFichero(JTextComponent componenteTexto)
    {
    	// Configurar clase padre para que sea CARGAR archivo.
        super(componenteTexto, Opciones.CARGAR);
        
        // Etiqueta y tecla aceleradora
        this.putValue(Action.NAME, "Abrir ...");
        this.putValue(
            Action.ACCELERATOR_KEY,
            KeyStroke.getAWTKeyStroke('A', Event.CTRL_MASK));
    }

    /**
     * Le el archivo que se le pasa y pone el contenido en el JTextArea.
     *
     * @param archivo Fichero a leer
     *
     * @throws FileNotFoundException No se puede abrir el archivo.
     */
    @Override
    protected void actuarSobreElFichero(File archivo)
        throws FileNotFoundException
    {
    	// Se prepara el reader para leer el archivo. Un StringBuffer para compener el
    	// texto total de forma eficiente
        BufferedReader reader = new BufferedReader(new FileReader(archivo));
        StringBuffer bufferTexto = new StringBuffer();

        try
        {
            String linea = reader.readLine();

            while (linea != null)
            {
            	// Se va a�adiendo las l�neas que se leen y un separador de l�nea
            	// adecuado para el sistema operativo. Este �ltimo puede ser /n o /r/n
            	// dependiendo de si es unix o windows. System.getProperty("line.separator")
            	// nos da el adecuado.
                bufferTexto.append(linea);
                bufferTexto.append(System.getProperty("line.separator"));
                
                // Siguiente linea.
                linea = reader.readLine();
            }
        }
        catch (IOException e)
        {
            JOptionPane.showMessageDialog(
                componenteTexto, e, "Error al leer archivo",
                JOptionPane.ERROR_MESSAGE);
        }

        // Se pone el texto leido en el JTextArea.
        componenteTexto.setText(bufferTexto.toString());
    }
}
