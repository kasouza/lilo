package com.kasouza.lilo.shared.lang;

public class Token {
    private TokenType type;
    private String value;

    Token(TokenType type, String value) {
        this.type = type;
        this.value = value;
    }

    TokenType getType() {
        return this.type;
    }

    String getValue() {
        return this.value;
    }
}
