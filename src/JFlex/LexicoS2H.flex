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
Letra = [A-Za-z]
Digito = [0-9]
Espacio = " "
OpArit = "*"|"/"|"+"|"-"
OpAsig = "="
OpRel = ">"|"<"|">="|"<="|"=="|"!="
OpBool = "&&"|"||"|"!"
Par_Apertura = "("
Par_Cierre = ")"
Llave_Apertura = "{"
Llave_Cierre = "}"
Cor_Apertura = "["
Cor_Cierre = "]"
Car_Esp = "_"|","|"."|";"|":"|"#"|"?"

//Componentes Lexicos -> Palabras Reservadas


//Componentes Lexicos Variables
Numero = {Digito} {Digito}*
Identificador = {Letra} {Letra}*
SaltoDeLinea = \n|\r|\r\n

InputCharacter = [^\r\n]
Comentarios = {TraditionalComentarios} | {EOLComentarios} | {DocumentationComentarios}
TraditionalComentarios = "/*" [^*] ~"*/" | "/*" "*"+ "/"
// Comentarios can be the last line of the file, without line terminator.
EOLComentarios = "//" {InputCharacter}* {SaltoDeLinea}?
DocumentationComentarios = "/**" {ComentariosContent} "*"+ "/"
ComentariosContent = ( [^*] | \*+ [^/*] )*

%%
//Sección de las reglas
{Numero} {
    Tokens t = new Tokens (yytext(),"NUMERO",yyline+1,yycolumn+1,true);
    this._existenTokens = true;
    return t;
}
{Identificador} {
    Tokens t = new Tokens (yytext(),"IDENTIFICADOR",yyline+1,yycolumn+1,true);
    this._existenTokens = true;
    return t;
}
{OpArit} {
    Tokens t = new Tokens (yytext(),"OP_ARIT",yyline+1,yycolumn+1,true);
    this._existenTokens = true;
    return t;
}
{OpAsig} {
    Tokens t = new Tokens (yytext(),"OP_ASIG",yyline+1,yycolumn+1,true);
    this._existenTokens = true;
    return t;
}
{OpRel} {
    Tokens t = new Tokens (yytext(),"OP_REL",yyline+1,yycolumn+1,true);
    this._existenTokens = true;
    return t;
}
{OpBool} {
    Tokens t = new Tokens (yytext(),"OP_BOOL",yyline+1,yycolumn+1,true);
    this._existenTokens = true;
    return t;
}
{Llave_Apertura} {
    Tokens t = new Tokens (yytext(),"LLAVE_APER",yyline+1,yycolumn+1,true);
    this._existenTokens = true;
    return t;
}
{Llave_Cierre} {
    Tokens t = new Tokens (yytext(),"LLAVE_CIER",yyline+1,yycolumn+1,true);
    this._existenTokens = true;
    return t;
}
{Cor_Apertura} {
    Tokens t = new Tokens (yytext(),"COR_APER",yyline+1,yycolumn+1,true);
    this._existenTokens = true;
    return t;
}
{Cor_Cierre} {
    Tokens t = new Tokens (yytext(),"COR_CIER",yyline+1,yycolumn+1,true);
    this._existenTokens = true;
    return t;
}
{Par_Apertura} {
    Tokens t = new Tokens (yytext(),"PAR_APER",yyline+1,yycolumn+1,true);
    this._existenTokens = true;
    return t;
}
{Par_Cierre} {
    Tokens t = new Tokens (yytext(),"PAR_CIER",yyline+1,yycolumn+1,true);
    this._existenTokens = true;
    return t;
}
{Car_Esp} {
    Tokens t = new Tokens (yytext(),"CAR_ESP",yyline+1,yycolumn+1,true);
    this._existenTokens = true;
    return t;
}
{Comentarios} {
    Tokens t = new Tokens (yytext(),"COMENTARIO",yyline+1,yycolumn+1,true);
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