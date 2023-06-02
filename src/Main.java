import java.util.ArrayList;

// Lexer (string => tokens)
// Parser (tokens => ast)
// Compiler (ast => bytecode)
// Interpreter (execute bytecode)

public class Main {
    public static void main(String[] args) {
        String source = "11 - 2 * 3";

        Lexer lexer = new Lexer(source);
        ArrayList<Token> tokens = lexer.Parse();

        Parser parser = new Parser(tokens);
        Node node = parser.Parse();

        Compiler compiler = new Compiler(node);
        ArrayList<Byte> bytecode = compiler.compile();

        Interpreter interpreter = new Interpreter(bytecode);
        int result = interpreter.execute();

        System.out.println(result);
    }
}