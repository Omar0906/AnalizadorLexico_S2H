package JFlex;

public class Tokens implements Comparable<Tokens> {

  String _token;
  String _lexema;
  int _column;
  int _row;
  boolean _impr;

  public String getLexema() {
    return this._lexema;
  }

  public void setLexema(String lexema) {
    this._lexema = lexema;
  }

  public String getToken() {
    return this._token;
  }

  public void setToken(String newToken) {
    this._token = newToken;
  }

  public int getColumn() {
    return this._column;
  }

  public void setColumn(int column) {
    this._column = column;
  }

  public int getRow() {
    return this._row;
  }

  public void setRow(int row) {
    this._row = row;
  }

  public Boolean isPrintable() {
    return this._impr;
  }

  public Boolean getPrint() {
    return this._impr;
  }

  public void setPrint(Boolean print) {
    this._impr = print;
  }

  Tokens(String lexema, String token, int row, int column, boolean impr) {
    this._lexema = lexema;
    this._token = token;
    this._column = column;
    this._row = row;
    this._impr = impr;
  }

  @Override
  public String toString() {
    return "Lexema: " + this._lexema + " Token: " + this._token + " Fila: " + this._row + " Columna: " + this._column
        + ";\n";
  }

  @Override
  public int compareTo(Tokens t) {
    return this.getLexema().compareTo(t.getLexema());
  }

  public Object[] tokenObject() {
    Object[] temp = { this._lexema, this._token, this._row, this._column };
    return temp;
  }
}
