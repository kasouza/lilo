package com.kasouza.lilo;

import com.kasouza.lilo.shared.lang.Parser;
import com.kasouza.lilo.shared.lang.SyntaxException;
import com.kasouza.lilo.shared.lang.ast.ProgramNode;

public class App {
    public static void main(String[] args) {
        String code = """
                    func $1 $2 ; func2 $3 $4 |
                    func $1 $2 ; func2 $3 $4
                """;

        // TODO: Add tests fo tha parsar
        try {
            Parser parser = new Parser(code);
            ProgramNode prog = parser.parse();
            System.out.println(prog);

        } catch (SyntaxException e) {
            System.out.println(e.getMessage());
        }
    }
}
