package TablaSimbolos;

import java.util.ArrayList;
import java.util.Collections;
import JFlex.Tokens;

public class TablaSimbolos {
    private ArrayList<Tokens> ListaTokens;

    public TablaSimbolos() {
        this.ListaTokens = new ArrayList<Tokens>();
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
    public void clear(){
        this.ListaTokens.clear();
    }

    public Boolean buscarToken(Tokens t) {
        if(this.ListaTokens.isEmpty()){
            return false;
        }
        for (Tokens tokens : this.ListaTokens) {
            if (tokens.getLexema().equals(t.getLexema())) {
                return true;
            }
        }
        return false;
    }
    public ArrayList<Tokens> verTablaSimbolos(){
        return this.ListaTokens;
    }
}
