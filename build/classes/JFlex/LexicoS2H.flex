%%
%class LexicoS2H
%{//Código personalizado 
    private boolean _existenTokens = true;
    public boolean _existenTokens(){
        return this._existenTokens;
    }
    
%}
%public
%unicode
%line
%column
%type Tokens
%init{
//Código que se ejecuta al inicio de la clase,
%init}
%eof{
    //Código que se ejecutará al final del proceso
    this._existenTokens = false;
%eof}

//A partir de aqui se ponen las expresiones regulares
Digito = [0-9]
Numero = {Digito} {Digito}*
Letra = [A-Za-z]
Palabra = {Letra} {Letra}*
OpArit = "*"|"/"|"+"|"-"
Comentario_Linea = "#" {Letra}*
Espacio = " "
SaltoDeLinea = \n|\r|\r\n
FinDeInstruccion = ";"

%%
//Sección de las reglas
{Numero} {
    Tokens t = new Tokens (yytext(),"NUMERO",yyline+1,yycolumn+1,true);
    this._existenTokens = true;
    return t;
}
{Palabra} {
    Tokens t = new Tokens (yytext(),"PALABRA",yyline+1,yycolumn+1,true);
    this._existenTokens = true;
    return t;
}
{OpArit} {
    Tokens t = new Tokens (yytext(),"Operador",yyline+1,yycolumn+1,true);
    this._existenTokens = true;
    return t;
}
{Comentario_Linea} {
    Tokens t = new Tokens (yytext(),"Comentario de una línea",yyline+1,yycolumn+1,true);
    this._existenTokens = true;
    return t;
}
{FinDeInstruccion} {
    Tokens t = new Tokens (yytext(),";",yyline+1,yycolumn+1,true);
    this._existenTokens = true;
    return t;
}
{Espacio} {
    //Este lexema se ignora
    Tokens t = new Tokens (yytext(),"Espacio en blanco",yyline+1,yycolumn+1,false);
    this._existenTokens = true;
    return t;
}
{SaltoDeLinea} {
    //Vamos a ignorar este también.
    Tokens t = new Tokens (yytext(),"Salto de Linea",yyline+1,yycolumn+1,false);
    this._existenTokens = true;
    return t;
}