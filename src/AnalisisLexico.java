import java.io.BufferedReader;
import java.io.Reader;
import java.io.StringReader;
import javax.swing.AbstractAction;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.JTextComponent;

import org.xml.sax.helpers.ParserAdapter;

import java.util.ArrayList;
import java.util.List;
import JFlex.LexicoS2H;
import JFlex.Tokens;

public class AnalisisLexico extends AbstractAction {
    private JTextComponent jtext;
    private Editor padre;

    public AnalisisLexico(JTextComponent jtext, Editor padre) {
        super("Analisis Lexico");
        this.jtext = jtext;
        this.padre = padre;
    }

    @Override
    public void actionPerformed(java.awt.event.ActionEvent e) {
        analisisLexico();
    }

    private void analisisLexico() {// Obtenemos el texto del textArea
        ArrayList<Object[]> listaTemp = new ArrayList<Object[]>();
        try { 
            Reader inputString = new StringReader(jtext.getText());
            BufferedReader buffer = new BufferedReader(inputString);
            LexicoS2H analizador = new LexicoS2H(buffer);
            while (analizador._existenTokens()) {
                try {
                    Tokens token = analizador.yylex();
                    if (token.isPrintable()) {
                        Object[] t = { token.getLexema(), token.getToken(), token.getRow(), token.getColumn() };
                        listaTemp.add(t);
                    }
                    padre.actualizarTabla(listaTemp);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                    for (Object[] objects : listaTemp) {
                        System.out.println(" "+objects[0]+", "+objects[1]+", "+objects[2]+", "+objects[3]);
                    }
                }
            }
            padre.actualizarTabla(listaTemp);
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }
}
