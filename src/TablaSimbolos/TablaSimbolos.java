package TablaSimbolos;

import java.util.ArrayList;
import java.util.Collections;
import JFlex.Tokens;

public class TablaSimbolos {
    private ArrayList<Tokens> ListaTokens;

    public TablaSimbolos() {
        ListaTokens = new ArrayList<Tokens>();
    }

    public void addToken(Tokens t) {
        if (!buscarToken(t)) {
            this.ListaTokens.add(t);
            sortTabla();
        }
    }

    public void sortTabla() {
        Collections.sort(ListaTokens);
    }

    public Boolean buscarToken(Tokens t) {
        for (Tokens tokens : ListaTokens) {
            if (tokens.getLexema() == t.getLexema()) {
                return true;
            }
        }
        return false;
    }
    public ArrayList<Tokens> verTablaSimbolos(){
        return this.ListaTokens;
    }
}
