package com.kasouza.lilo.shared.lang;

/*
|
    sort $1 $2;
    nop $3 $4
|
    intersection $1 $2;
    intersection $3 $4  
| 
    sort $1;
    nop $2 
| 
    sort $1 
| 
    sort $2;
    nop $1
*/

public class Tokenizer {
    public static final char SIMPLE_VARIABLE_SYMBOL = '$';
    public static final char PIPE_SYMBOL = '|';
    public static final char SEMICOLON_SYMBOL = ';';

    private String input;
    private int cursor;

    public Tokenizer() {
        this.reset("");
    }

    public Tokenizer(String input) {
        reset(input);
    }

    public void reset(String input) {
        this.input = input;
        this.cursor = 0;
    }

    public boolean isEOF() {
        return cursor >= input.length();
    }

    public boolean hasNextToken() {
        return !isEOF();
    }

    public Token getNextToken() {
        // Ignore whitespace
        if (this.isEOF()) {
            return null;
        }

        if (Character.isWhitespace(input.charAt(cursor))) {
            while (!isEOF() && Character.isWhitespace(input.charAt(cursor))) {
                this.cursor++;
            }

            return getNextToken();
        }

        // Identifiers
        if (Character.isLetter(input.charAt(cursor)) || input.charAt(cursor) == '_') {
            StringBuilder sb = new StringBuilder();

            while (!isEOF() && isValidIdentifierCharacter(input.charAt(cursor))) {
                sb.append(input.charAt(cursor));
                cursor++;
            }

            return new Token(TokenType.IDENTIFIER, sb.toString());
        }

        // Simple variables
        if (input.charAt(cursor) == SIMPLE_VARIABLE_SYMBOL) {
            StringBuilder sb = new StringBuilder();

            sb.append(input.charAt(cursor));
            cursor++;

            while (!isEOF() && Character.isDigit(input.charAt(cursor))) {
                sb.append(input.charAt(cursor));
                cursor++;
            }

            return new Token(TokenType.SIMPLE_VARIABLE, sb.toString());
        }

        // Pipe
        if (input.charAt(cursor) == PIPE_SYMBOL) {
            Token token = new Token(TokenType.PIPE, Character.toString(input.charAt(cursor)));
            cursor++;

            return token;
        }

        // Pipe
        if (input.charAt(cursor) == SEMICOLON_SYMBOL) {
            Token token = new Token(TokenType.SEMICOLON, Character.toString(input.charAt(cursor)));
            cursor++;

            return token;
        }

        return null;
    }

    private boolean isValidIdentifierCharacter(char ch) {
        return Character.isLetter(ch) || Character.isDigit(ch) || ch == '_';
    }
}
