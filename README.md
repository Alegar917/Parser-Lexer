# Parser-Lexer
A Parser/Lexer for a Simple language
The Lexer turns the characters in a file into the tokens of the Simple language.
The Lexer will identify the following token types:
Identifier: a sequence that starts with a letter and is followed by letters and digits
Integer: a sequence of digits
AssignOp: a single ‘=’
PlusOp: a single ‘+’
UnknownOp: e.g., ‘$’
EndOfFile
The Parser identifies whether the input file statement code is syntactically correct and report that it is correct or report an error.
