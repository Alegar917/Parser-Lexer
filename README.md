# Parser-Lexer
A Parser/Lexer for a Simple language.
The Lexer turns the characters in a file into the tokens of the Simple language.
The Lexer will identify the following token types:
1.Identifier: a sequence that starts with a letter and is followed by letters and digits
2.Integer: a sequence of digits
3.AssignOp: a single ‘=’
4.PlusOp: a single ‘+’
5.UnknownOp: e.g., ‘$’
6.EndOfFile.
The Parser identifies whether the input file statement code is syntactically correct and report that it is correct or report an error.
