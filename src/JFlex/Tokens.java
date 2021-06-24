package JFlex;

public class Tokens implements Comparable<Tokens> {

  String _token;
  String _lexema;
  int _column;
  int _row;
  String _tipo;
  boolean _impr;
  boolean _error;
  String _msg;
  int errorCode;

  Tokens(String lexema, String token, int row, int column, boolean impr) {
    this._lexema = lexema.trim();
    this._token = token;
    this._column = column;
    this._row = row;
    this._impr = impr;
    this._error = false;
    this._msg = "";
    this.errorCode = 0;
    this._tipo = "";
  }
  Tokens(int row, int column,boolean error,int errorCode,String msg) {
    this._lexema = "";
    this._token = "";
    this._column = column;
    this._row = row;
    this._impr = true;
    this._error = error;
    this._msg = msg;
    this.errorCode = errorCode;
    this._tipo = "";
  }
  public String getTipo(){
      return this._tipo;
  }
  public void setTipo(String tipo){
      this._tipo = tipo;
  }
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
  public Boolean isError(){
    return this._error;
  }

  public Boolean getPrint() {
    return this._impr;
  }

  public void setPrint(Boolean print) {
    this._impr = print;
  }
  public void setError(Boolean error) {
    this._error = error;
  }
  public String getMsgError(){
    return this._msg;
  } 
  public void setMsgError(String msg){
    this._msg = msg;
  }
  public boolean isID(){
      if(this._token == "IDENTIFICADOR"){
          return true;
      }
      return false;
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
    Object[] temp = { this._lexema, this._row, this._column };
    return temp;
  }
  public Object[] tokenLexico(){
      Object[] temp = {this._lexema,this._token,this._row,this._column};
      return temp;
  }
}
