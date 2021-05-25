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

//Componentes Lexicos -> Palabras Reservadas
PALABRA_RESERVADA = {PR_AIR}|{PR_AT}|{PR_BEGIN}|{PR_BEGINPROC}|{PR_BEGINVAR}|{PR_BOOL}|{PR_BREAK}|{PR_CLOCK}|{PR_DEC}|{PR_DEF}|{PR_DISJOIN}|{PR_END}|{PR_ENDPROC}|{PR_ENDVAR}|{PR_FALSE}|{PR_FLOAT}|{PR_GATE}|{PR_INC}|{PR_INT}|{PR_IS}|{PR_JOIN}|{PR_LIGHTB}|{PR_MODE}|{PR_MODE}|{PR_NOT}|{PR_NOTIFY}|{PR_OFF}|{PR_ON}|{PR_OPEN}|{PR_PARTY}|{PR_SCHEDULE}|{PR_SEL}|{PR_SENSOR}|{PR_SWITCH}|{PR_TEMP}|{PR_TIME}|{PR_TRUE}|{PR_WHEN}|{PR_WHILE}|{PR_YES}
PR_AIR = "AIR"
PR_AT = "AT"
PR_BEGIN = "BEGIN"
PR_BEGINPROC = "BEGIN_PROC"
PR_BEGINVAR = "BEGIN_VAR"
PR_BOOL = "BOOL"
PR_BREAK = "BREAK"
PR_CLOCK = "CLOCK"
PR_DEC = "DEC"
PR_DEF = "DEF"
PR_DISJOIN = "DISJOIN"
PR_END = "END"
PR_ENDPROC = "END_PROC"
PR_ENDVAR = "END_VAR"
PR_FALSE = "FALSE"
PR_FLOAT = "FLOAT"
PR_GATE = "GATE"
PR_INC = "INC"
PR_INT = "INT"
PR_IS = "IS"
PR_JOIN = "JOIN"
PR_LIGHTB = "LIGHTB"
PR_MODE = "MODE"
PR_NOT = "NOT"
PR_NOTIFY = "NOTIFY"
PR_OFF = "OFF"
PR_ON = "ON"
PR_OPEN = "OPEN"
PR_PARTY = "PARTY"
PR_SCHEDULE = "SCHEDULE"
PR_SEL = "SEL"
PR_SENSOR = "SENSOR"
PR_SWITCH = "SWITCH"
PR_TEMP = "TEMP"
PR_TIME = "TIME"
PR_TRUE = "TRUE"
PR_WHEN = "WHEN"
PR_WHILE = "WHILE"
PR_YES = "YES"
//Área de expresiones regulares
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


//Componentes Lexicos Variables
Numero = {Digito}+
Flotante = {Digito}* "." {Digito}+
Booleano = {PR_TRUE}|{PR_FALSE}

TIEMPO_HORA = "0" {Digito} | "1" {Digito}| "2" [0-3]
TIEMPO_MINUTOS = [0-5] {Digito}
TIEMPO = {TIEMPO_HORA} ":" {TIEMPO_MINUTOS}

Identificador = {Letra} ({Letra} | {Digito})*
SaltoDeLinea = \n|\r|\r\n

//Para los errores
NUM_ERROR = ({Digito}+ {Letra}+ {Digito}*)+
FLOAT_ERROR = {Flotante} ("."* {Digito})+

InputCharacter = [^\r\n]
Comentarios = {TraditionalComentarios} | {EOLComentarios} | {DocumentationComentarios}
TraditionalComentarios = "/#" [^*] ~"#/" | "/#" "#"+ "/"
// Comentarios can be the last line of the file, without line terminator.
EOLComentarios = "//" {InputCharacter}* {SaltoDeLinea}?
DocumentationComentarios = "/#" {ComentariosContent} "#"+ "/"
ComentariosContent = ( [^*] | \*+ [^/*] )*

%%
//Sección de las reglas
{Comentarios} {
    Tokens t = new Tokens (yytext(),"COMENTARIO",yyline+1,yycolumn+1,true);
    this._existenTokens = true;
    return t;
}
{Booleano} {
    Tokens t = new Tokens (yytext(),"COMENTARIO",yyline+1,yycolumn+1,true);
    this._existenTokens = true;
    return t;
}
{PALABRA_RESERVADA} {
    Tokens t = new Tokens (yytext(),"PALABRA_RESERVADA",yyline+1,yycolumn+1,true);
    this._existenTokens = true;
    return t;
}
{TIEMPO} {
    Tokens t = new Tokens (yytext(),"TIEMPO",yyline+1,yycolumn+1,true);
    this._existenTokens = true;
    return t;
}
{Flotante} {
    Tokens t = new Tokens (yytext(),"NUMERO_FLOTANTE",yyline+1,yycolumn+1,true);
    this._existenTokens = true;
    return t;
}
{Numero} {
    Tokens t = new Tokens (yytext(),"NUMERO ENTERO",yyline+1,yycolumn+1,true);
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
{NUM_ERROR} {
    Tokens t = new Tokens (yyline+1,yycolumn+1,true,"Número inválido ("+ yytext() +") en: " + yyline + ", columna: " + yycolumn);
    this._existenTokens = true;
    return t;
}
{FLOAT_ERROR} {
    Tokens t = new Tokens (yyline+1,yycolumn+1,true,"Número flotante inválido ("+ yytext() +") en: " + yyline + ", columna: " + yycolumn);
    this._existenTokens = true;
    return t;
}
[^] {
    Tokens t = new Tokens (yyline+1,yycolumn+1,true,"Caracter no válido ("+yytext()+") en línea: " + yyline + ", columna: " + yycolumn);
    this._existenTokens = true;
    return t;
}