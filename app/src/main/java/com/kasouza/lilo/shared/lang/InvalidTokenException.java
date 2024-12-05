package com.kasouza.lilo.shared.lang;

public class InvalidTokenException extends SyntaxException {
    protected TokenType expected;
    protected TokenType found;

    InvalidTokenException(TokenType expected, TokenType found) {
        super("Invalid token, expected " + expected.toString() + " found " + found.toString());

        this.expected = expected;
        this.found = found;
    }

    public TokenType getExpected() {
        return expected;
    }

    public TokenType getFound() {
        return found;
    }
}
