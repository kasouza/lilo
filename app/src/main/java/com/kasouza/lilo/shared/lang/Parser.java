package com.kasouza.lilo.shared.lang;

import java.util.ArrayList;
import java.util.List;

import com.kasouza.lilo.shared.lang.ast.ProgramNode;
import com.kasouza.lilo.shared.lang.ast.StepNode;
import com.kasouza.lilo.shared.lang.ast.VariableNode;
import com.kasouza.lilo.shared.lang.ast.stmt.FunctionStatementNode;
import com.kasouza.lilo.shared.lang.ast.stmt.StatementNode;

public class Parser {
    private Tokenizer tokenizer;
    private Token lookahead;

    public Parser(String input) {
        tokenizer = new Tokenizer();
        reset(input);
    }

    public void reset(String input) {
        tokenizer.reset(input);
        lookahead = tokenizer.getNextToken();
    }

    public ProgramNode parse() throws SyntaxException {
        return program();
    }

    /**
     * Program
     *  : Steps
     *  ;
     */
    private ProgramNode program() throws SyntaxException {
        if (getLookaheadTokenType() == TokenType.IDENTIFIER) {
            List<StepNode> steps = steps();

            if (getLookaheadTokenType() != null) {
                throw new SyntaxException("Invalid token " + lookahead.getValue());
            }

            return new ProgramNode(steps);
        }

        throw new SyntaxException("Invalid program");
    }

    /**
     * Steps
     *  : Step
     *  | Step PIPE Steps
     *  | Step PIPE Steps
     *  ;
     */
    private List<StepNode> steps() throws SyntaxException {
        List<StepNode> steps = new ArrayList<>();

        if (getLookaheadTokenType() == TokenType.IDENTIFIER) {
            steps.add(step());

            if (getLookaheadTokenType() == TokenType.PIPE) {
                eat(TokenType.PIPE);

                List<StepNode> otherSteps = steps();
                for (var step : otherSteps) {
                    steps.add(step);
                }
            }

        }

        return steps;
    }

    /**
     * Step
     *  : Statements
     *  ;
     */
    private StepNode step() throws SyntaxException {
        if (getLookaheadTokenType() == TokenType.IDENTIFIER) {
            List<StatementNode> statements = statements();
            return new StepNode(statements);
        }

        throw new SyntaxException("Invalid step");
    }

    /**
     * Statements
     *  : Statement
     *  | Statement SEMICOLON Statements
     *  ;
     */
    private List<StatementNode> statements() throws SyntaxException {
        List<StatementNode> statements = new ArrayList<>();

        if (getLookaheadTokenType() == TokenType.IDENTIFIER) {
            statements.add(statement());
            
            if (getLookaheadTokenType() == TokenType.SEMICOLON) {
                eat(TokenType.SEMICOLON);
                var otherStatements = statements();

                for (var stmt : otherStatements) {
                    statements.add(stmt);
                }
            }
        }

        return statements;
    }

    /**
     * Statement
     *  : FunctionStatement
     *  ;
     */
    private StatementNode statement() throws SyntaxException {
        if (getLookaheadTokenType() == TokenType.IDENTIFIER) {
            return functionStatement();
        }

        throw new SyntaxException("Invalid statement");
    }

    /**
     * FunctionStatement
     *  : IDENTIFIER Variables
     *  ;
     */
    private FunctionStatementNode functionStatement() throws SyntaxException {
        Token identifier = eat(TokenType.IDENTIFIER);
        List<VariableNode> variables = variables();

        return new FunctionStatementNode(identifier.getValue(), variables);
    }

    /**
     * Variables
     *  : Variable
     *  | Variable Variables
     *  ;
     */
    private List<VariableNode> variables() throws SyntaxException {
        List<VariableNode> variables = new ArrayList<>();

        while (getLookaheadTokenType() == TokenType.SIMPLE_VARIABLE) {
            variables.add(variable());
        }

        return variables;
    }

    /**
     * Variable
     *  : SIMPLE_VARIABLE
     *  ;
     */
    private VariableNode variable() throws SyntaxException {
        Token token = eat(TokenType.SIMPLE_VARIABLE);
        return new VariableNode(token.getValue());
    }

    private Token eat(TokenType type) throws InvalidTokenException {
        if (getLookaheadTokenType() != type) {
            throw new InvalidTokenException(type, getLookaheadTokenType());
        }

        Token token = lookahead;
        lookahead = tokenizer.getNextToken();

        return token;
    }

    private TokenType getLookaheadTokenType() {
        return lookahead != null ? lookahead.getType() : null;
    }
}
