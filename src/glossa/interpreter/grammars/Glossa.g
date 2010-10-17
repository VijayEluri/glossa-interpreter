grammar Glossa;

options{
	output = AST;
	ASTLabelType = CommonTree;
}


tokens
{
  BLOCK;
  NEG;
  VARSDECL;
  ARRAY;
  ARRAY_ITEM;
  ARRAY_INDEX;
  ARRAY_DIMENSION;
}

@lexer::header{
package glossa.interpreter;
}

@header{
package glossa.interpreter;
}


/*
**************************
*	Grammar rules:
**************************
*/

unit	:	program;

program	:	PROGRAM^ id1=ID (NEWLINE!)+
		declarations
		BEGIN!  (NEWLINE!)+
		block
		END_PROGRAM! (id2=ID)? (NEWLINE!)+;
		
declarations
	:	(constDecl | varDecl)*;
		
constDecl
	:	CONSTANTS^ (NEWLINE!)+ constAssign*;

constAssign
	:	ID EQ^ expr (NEWLINE!)+;
	
varDecl	:	VARIABLES^ (NEWLINE!)+ varsDecl*;
	
varsDecl
	:	varType^ COLON! varDeclItem (COMMA! varDeclItem)* (NEWLINE!)+;

varDeclItem
	:	ID 
	| 	ID arrayDimension -> ^(ARRAY ID arrayDimension);

arrayDimension
	:	(LBRACKET dimension+=expr RBRACKET)+ -> ^(ARRAY_DIMENSION expr+);
	
varType	:	BOOLEANS
	|	STRINGS
	|	INTEGERS
	|	REALS;
		
block	:	stm*  -> ^(BLOCK stm*);

stm	:	printStm
	|	assingmentStm;
	
printStm	:	PRINT^ expr ( ','! expr )* (NEWLINE!)+	;
	
assingmentStm
	:	ID ASSIGN^ expr (NEWLINE!)+;
	
expr	:	eqExpr (AND^ eqExpr | OR^ eqExpr)*;

eqExpr	:	compExpr (EQ^ compExpr | NEQ^ compExpr )*	;

compExpr:	addExpr (LT^ addExpr | LE^ addExpr | GT^ addExpr | GE^ addExpr  )*	;

addExpr	:	multExpr (PLUS^ multExpr | MINUS^ multExpr )*	;

multExpr	:	powExpr (TIMES^ powExpr | DIA^ powExpr | DIV^ powExpr | MOD^ powExpr )*	;

powExpr	:	unaryExpr (POW^ unaryExpr)*;

unaryExpr
	:	PLUS! atom
	|	MINUS atom -> ^(NEG atom)
	|	NOT atom -> ^(NOT atom)
	|	atom;


atom	:	CONST_TRUE
	|	CONST_FALSE
	|	CONST_STR
	|	CONST_INT
	|	CONST_REAL
	|	arrayItem
	|	ID
	|	'('! expr ')'!;
	
	
arrayItem
	:	ID arraySubscript -> ^(ARRAY_ITEM ID arraySubscript);
	
arraySubscript
	:	(LBRACKET expr RBRACKET)+ -> ^(ARRAY_INDEX expr+);


/*
**************************
*	Tokens:
**************************
*/

ASSIGN
	:	'<-';
	
COMMA	:	',';

DOUBLE_DOT
	:	'..';
	
COLON	:	':';

LPAR	:	'(';

RPAR	:	')';

LBRACKET:	'[';

RBRACKET:	']';

PLUS	:	'+';

MINUS	:	'-';

TIMES	:	'*';

DIA	:	'/';

POW	:	'^';

DIV	:	('D'|'d')('I'|'i')('V'|'v');

MOD	:	('M'|'m')('O'|'o')('D'|'d');

LE	:	'<=';

LT	:	'<';

GE	:	'>=';

GT	:	'>';

EQ	:	'=';

NEQ	:	'<>';


AND	:	KAPPA ALPHA IOTA;

OR	:	ETA_TONOS;

NOT	:	(OMICRON|OMICRON_TONOS) CHI IOTA;


PROGRAM :	 PI RHO (OMICRON | OMICRON_TONOS) GAMMA RHO ALPHA MU MU ALPHA;
	
END_PROGRAM
	:	TAU (EPSILON|EPSILON_TONOS) LAMDA OMICRON SIGMA_TELIKO '_' PI RHO OMICRON GAMMA RHO (ALPHA | ALPHA_TONOS) MU MU ALPHA TAU OMICRON SIGMA_TELIKO;
	
VARIABLES
	:	MU EPSILON TAU ALPHA BETA LAMDA ETA TAU (EPSILON | EPSILON_TONOS) SIGMA_TELIKO;

CONSTANTS
	:	SIGMA TAU ALPHA THETA EPSILON RHO (EPSILON|EPSILON_TONOS) SIGMA_TELIKO;

READ	:	DELTA IOTA (ALPHA | ALPHA_TONOS) BETA ALPHA SIGMA EPSILON;

PRINT	:	GAMMA RHO (ALPHA | ALPHA_TONOS) PSI EPSILON;

BEGIN	:	ALPHA RHO CHI (ETA | ETA_TONOS);

PROCEDURE
	:	DELTA IOTA ALPHA DELTA IOTA KAPPA ALPHA SIGMA (IOTA|IOTA_TONOS) ALPHA;
	
END_PROCEDURE
	:	TAU (EPSILON|EPSILON_TONOS) LAMDA OMICRON SIGMA_TELIKO '_' DELTA IOTA ALPHA DELTA IOTA KAPPA ALPHA SIGMA (IOTA|IOTA_TONOS) ALPHA SIGMA_TELIKO;

FUNCTION:	SIGMA UPSILON NU (ALPHA|ALPHA_TONOS) RHO TAU ETA SIGMA ETA;

END_FUNCTION
	:	TAU (EPSILON|EPSILON_TONOS) LAMDA OMICRON SIGMA_TELIKO '_' SIGMA UPSILON NU (ALPHA|ALPHA_TONOS) RHO TAU ETA SIGMA ETA SIGMA_TELIKO;
	
CALL	:	KAPPA (ALPHA|ALPHA_TONOS) LAMDA EPSILON SIGMA EPSILON;


IF	:	ALPHA NU;

THEN	:	TAU (OMICRON | OMICRON_TONOS) TAU EPSILON;

ELSE	:	ALPHA LAMDA LAMDA IOTA (OMEGA | OMEGA_TONOS) SIGMA_TELIKO;

ELSE_IF	:	ALPHA LAMDA LAMDA IOTA (OMEGA | OMEGA_TONOS) SIGMA_TELIKO '_' ALPHA NU;

END_IF	:	TAU (EPSILON|EPSILON_TONOS) LAMDA OMICRON SIGMA_TELIKO '_' ALPHA NU;




SWITCH	:	EPSILON PI (IOTA|IOTA_TONOS) LAMDA EPSILON XI EPSILON;

CASE	:	PI EPSILON RHO (IOTA|IOTA_TONOS) PI TAU OMEGA SIGMA ETA;

END_SWITCH
	:	TAU (EPSILON|EPSILON_TONOS) LAMDA OMICRON SIGMA_TELIKO '_' EPSILON PI IOTA LAMDA OMICRON GAMMA OMEGA NU;



WHILE	:	(OMICRON|OMICRON_TONOS) SIGMA OMICRON;

LOOP	:	EPSILON PI ALPHA NU (ALPHA|ALPHA_TONOS) LAMDA ALPHA BETA EPSILON;

END_LOOP:	TAU (EPSILON|EPSILON_TONOS) LAMDA OMICRON SIGMA_TELIKO '_' EPSILON PI ALPHA NU (ALPHA|ALPHA_TONOS) LAMDA ETA PSI ETA SIGMA_TELIKO;



REPEAT	:	ALPHA RHO CHI (ETA|ETA_TONOS) '_' EPSILON PI ALPHA NU (ALPHA|ALPHA_TONOS) LAMDA ETA PSI ETA SIGMA_TELIKO;

UNTIL	:	MU (EPSILON|EPSILON_TONOS) CHI RHO IOTA SIGMA_TELIKO '_' (OMICRON|OMICRON_TONOS) TAU OMICRON UPSILON;


FOR	:	GAMMA IOTA ALPHA;

FROM	:	ALPHA PI (OMICRON|OMICRON_TONOS);

TO	:	MU (EPSILON|EPSILON_TONOS) CHI RHO IOTA;

STEP	:	MU EPSILON ( '_' | (' '|'\t')+  ) BETA (ETA|ETA_TONOS) MU ALPHA;



INTEGER	:	ALPHA KAPPA (EPSILON|EPSILON_TONOS) RHO ALPHA IOTA ALPHA;

INTEGERS:	ALPHA KAPPA (EPSILON|EPSILON_TONOS) RHO ALPHA IOTA EPSILON SIGMA_TELIKO;

REAL	:	PI RHO ALPHA GAMMA MU ALPHA TAU IOTA KAPPA (ETA|ETA_TONOS);

REALS	:	PI RHO ALPHA GAMMA MU ALPHA TAU IOTA KAPPA (EPSILON|EPSILON_TONOS) SIGMA_TELIKO;

STRING	:	CHI ALPHA RHO ALPHA KAPPA TAU (ETA|ETA_TONOS) RHO ALPHA SIGMA_TELIKO;

STRINGS	:	CHI ALPHA RHO ALPHA KAPPA TAU (ETA|ETA_TONOS) RHO EPSILON SIGMA_TELIKO;

BOOLEAN	:	LAMDA OMICRON GAMMA IOTA KAPPA (ETA|ETA_TONOS);

BOOLEANS	:	LAMDA OMICRON GAMMA IOTA KAPPA (EPSILON|EPSILON_TONOS) SIGMA_TELIKO;






CONST_TRUE
	:	ALPHA LAMDA ETA THETA (ETA|ETA_TONOS) SIGMA_TELIKO;
	
CONST_FALSE
	:	PSI EPSILON UPSILON DELTA (ETA|ETA_TONOS) SIGMA_TELIKO;
	
CONST_STR
	:	'\'' .* '\'';

CONST_INT
	:	DIGIT+;
	
CONST_REAL
	:	DIGIT+ '.' DIGIT+;

ID	:	LETTER (LETTER|DIGIT|'_')*;

COMMENT	:	'!' NOT_EOL* {skip();};

CONT_COMMAND
	:	NEWLINE '&'{skip();};

NEWLINE	:	'\r'? '\n';

WS	:	(' '|'\t')+ {skip();};




/*
**************************
*	Fragments:
**************************
*/

fragment DIGIT
	:	'0'..'9';

fragment LETTER
	:	LATIN_LETTER|GREEK_LETTER;


fragment LATIN_LETTER
	:	'a'..'z' | 'A'..'Z';


fragment GREEK_LETTER
	:	ALPHA | BETA | GAMMA | DELTA | EPSILON | ZETA | ETA | THETA
	|	IOTA | KAPPA | LAMDA | MU | NU | XI | OMICRON | PI | RHO | SIGMA
	|	TAU | UPSILON | PHI | CHI | PSI | OMEGA | SIGMA_TELIKO
	| 	ALPHA_TONOS | EPSILON_TONOS | ETA_TONOS | IOTA_TONOS | UPSILON_TONOS
	|	OMICRON_TONOS | OMEGA_TONOS | IOTA_DIALYTIKA | UPSILON_DIALYTIKA
	|	IOTA_DIALYTIKA_TONOS | UPSILON_DIALYTIKA_TONOS ;

fragment ALPHA
	:	'\u03B1'|'\u0391';
fragment BETA
	:	'\u03B2'|'\u0392';
fragment GAMMA
	:	'\u03B3'|'\u0393';
fragment DELTA
	:	'\u03B4'|'\u0394';
fragment EPSILON
	:	'\u03B5'|'\u0395';
fragment ZETA
	:	'\u03B6'|'\u0396';
fragment ETA
	:	'\u03B7'|'\u0397';
fragment THETA
	:	'\u03B8'|'\u0398';
fragment IOTA
	:	'\u03B9'|'\u0399';
fragment KAPPA
	:	'\u03BA'|'\u039A';
fragment LAMDA
	:	'\u03BB'|'\u039B';
fragment MU
	:	'\u03BC'|'\u039C';
fragment NU
	:	'\u03BD'|'\u039D';
fragment XI
	:	'\u03BE'|'\u039E';
fragment OMICRON
	:	'\u03BF'|'\u039F';
fragment PI
	:	'\u03C0'|'\u03A0';
fragment RHO
	:	'\u03C1'|'\u03A1';
fragment SIGMA
	:	'\u03C3'|'\u03A3';
fragment TAU
	:	'\u03C4'|'\u03A4';
fragment UPSILON
	:	'\u03C5'|'\u03A5';
fragment PHI
	:	'\u03C6'|'\u03A6';
fragment CHI
	:	'\u03C7'|'\u03A7';
fragment PSI
	:	'\u03C8'|'\u03A8';
fragment OMEGA
	:	'\u03C9'|'\u03A9';
fragment SIGMA_TELIKO
	:	'\u03C2'|'\u03A3';	
fragment ALPHA_TONOS
	:	'\u03AC'|'\u0386';
fragment EPSILON_TONOS
	:	'\u03AD'|'\u0388';
fragment ETA_TONOS
	:	'\u03AE'|'\u0389';
fragment IOTA_TONOS
	:	'\u03AF'|'\u038A';
fragment UPSILON_TONOS
	:	'\u03CD'|'\u038E';
fragment OMICRON_TONOS
	:	'\u03CC'|'\u038C';
fragment OMEGA_TONOS
	:	'\u03CE'|'\u038F';
fragment IOTA_DIALYTIKA
	:	'\u03CA'|'\u03AA';
fragment UPSILON_DIALYTIKA
	:	'\u03CB'|'\u03AB';
fragment IOTA_DIALYTIKA_TONOS
	:	'\u0390';
fragment UPSILON_DIALYTIKA_TONOS
	:	'\u03B0';
	
	
fragment NOT_EOL
	:	('\u0000'..'\u0009') | '\u000B' | '\u000C'| ('\u000E'..'\uFFFF') ;
	
	
	
