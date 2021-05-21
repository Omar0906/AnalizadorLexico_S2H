/*
 * Fichero: AccionSalvarFichero.java
 * Autor: Chuidiang
 * Fecha: 29/11/06 20:45
 */


import java.awt.Event;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

import javax.swing.Action;
import javax.swing.KeyStroke;
import javax.swing.text.JTextComponent;


/**
 * Salva el texto del JTextArea en un fichero.<br>
 * Hereda de AbstractAccionFichero as� que s�lo se tiene que preocupar de configurarse
 * y de salvar el fichero.
 *
 * @author Chuidiang
 *
  */
public class AccionSalvarFichero extends AbstractAccionFichero
{
    /**
     * serial uid
     */
    private static final long serialVersionUID = -4691302660823746530L;

    /**
     * Crea un nuevo objeto AccionSalvarFichero.
     *
     * @param componenteTexto JTextArea que contiene el texto que se quiere salvar.
     */
    public AccionSalvarFichero(JTextComponent componenteTexto)
    {
    	
        super(componenteTexto, Opciones.SALVAR);
        this.putValue(Action.NAME, "Guardar como...");
        this.putValue(
            Action.ACCELERATOR_KEY,
            KeyStroke.getAWTKeyStroke('G', Event.CTRL_MASK));
    }

    /**
     * Salva el texto del JTextArea en el fichero que se le pasa.
     *
     * @param fichero Fichero en el que se quiere salvar el texto.
     *
     * @throws FileNotFoundException Excepci�n si hay problemas.
     */
    protected void actuarSobreElFichero(File fichero)
        throws FileNotFoundException
    {
        PrintWriter writer = new PrintWriter(fichero);
        writer.print(componenteTexto.getText());
        writer.close();
    }
}
