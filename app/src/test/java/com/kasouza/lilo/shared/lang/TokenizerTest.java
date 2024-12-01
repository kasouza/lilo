package com.kasouza.lilo.shared.lang;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class TokenizerTest {
    @Test
    void hasNextTokenAndReset() {
        System.out.println("her");
        var tokenizer = new Tokenizer("teste");
        assertTrue(tokenizer.hasNextToken());
        assertFalse(tokenizer.isEOF());

        tokenizer.reset("");
        assertFalse(tokenizer.hasNextToken());
        assertTrue(tokenizer.isEOF());
    }

    @Test
    void identifier() {
        String program = "saske sort_test123 _start_undescore";
        var tokenizer = new Tokenizer(program);

        this.assertNextToken(tokenizer, TokenType.IDENTIFIER, "saske");
        this.assertNextToken(tokenizer, TokenType.IDENTIFIER, "sort_test123");
        this.assertNextToken(tokenizer, TokenType.IDENTIFIER, "_start_undescore");

        tokenizer.reset("123");
        assertNull(tokenizer.getNextToken());
    }

    @Test
    void simpleVariable() {
        var tokenizer = new Tokenizer("$1 $2 $231");
        this.assertNextToken(tokenizer, TokenType.SIMPLE_VARIABLE, "$1");
        this.assertNextToken(tokenizer, TokenType.SIMPLE_VARIABLE, "$2");
        this.assertNextToken(tokenizer, TokenType.SIMPLE_VARIABLE, "$231");
    }

    @Test
    void pipe() {
        var tokenizer = new Tokenizer("| $1 | ||");
        this.assertNextToken(tokenizer, TokenType.PIPE, "|");
        this.assertNextToken(tokenizer, TokenType.SIMPLE_VARIABLE, "$1");
        this.assertNextToken(tokenizer, TokenType.PIPE, "|");
        this.assertNextToken(tokenizer, TokenType.PIPE, "|");
    }

    @Test
    void semicolon() {
        var tokenizer = new Tokenizer("; $1 ; ;;");
        this.assertNextToken(tokenizer, TokenType.SEMICOLON, ";");
        this.assertNextToken(tokenizer, TokenType.SIMPLE_VARIABLE, "$1");
        this.assertNextToken(tokenizer, TokenType.SEMICOLON, ";");
        this.assertNextToken(tokenizer, TokenType.SEMICOLON, ";");
    }

    @Test
    void ignoringWhitespace() {
        var tokenizer = new Tokenizer("id1$1id2$2$3|;$4id3");
        assertNextToken(tokenizer, TokenType.IDENTIFIER, "id1");
        assertNextToken(tokenizer, TokenType.SIMPLE_VARIABLE, "$1");
        assertNextToken(tokenizer, TokenType.IDENTIFIER, "id2");
        assertNextToken(tokenizer, TokenType.SIMPLE_VARIABLE, "$2");
        assertNextToken(tokenizer, TokenType.SIMPLE_VARIABLE, "$3");
        assertNextToken(tokenizer, TokenType.PIPE, "|");
        assertNextToken(tokenizer, TokenType.SEMICOLON, ";");
        assertNextToken(tokenizer, TokenType.SIMPLE_VARIABLE, "$4");
        assertNextToken(tokenizer, TokenType.IDENTIFIER, "id3");
    }

    void assertNextToken(Tokenizer tokenizer, TokenType type, String value) {
        Token token = tokenizer.getNextToken();
        assertNotNull(token);
        assertEquals(type, token.getType());
        assertEquals(value, token.getValue());
    }
}
