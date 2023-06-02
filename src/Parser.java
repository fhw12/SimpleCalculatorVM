import java.util.ArrayList;

class Node{
    public String type;
    public String value;
    public Node left;
    public Node right;

    public Node(String type_, String value_, Node left_, Node right_){
        type = type_;
        value = value_;
        left = left_;
        right = right_;
    }
}

public class Parser {
    private final ArrayList<Token> tokens;
    private int pos;

    public Parser(ArrayList<Token> tokens_){
        tokens = tokens_;
    }

    private boolean tokenNotNull(){
        return pos < tokens.size();
    }

    private Token getCurrentToken(){
        return tokens.get(pos);
    }

    private Node number(){
        Token token = getCurrentToken();
        pos += 1;
        return new Node("number", token.value, null, null);
    }

    private Node mulDiv(){
        Node node = number();
        while(tokenNotNull()){
            Token token = getCurrentToken();
            switch (token.type) {
                case "mul" -> {
                    pos += 1;
                    node = new Node("BinOp", "*", node, number());
                }
                case "div" -> {
                    pos += 1;
                    node = new Node("BinOp", "/", node, number());
                }

                default -> {
                    return node;
                }
            }
        }

        return node;
    }

    private Node plusMinus(){
        Node node = mulDiv();
        while(tokenNotNull()){
            Token token = getCurrentToken();
            switch (token.type) {
                case "plus" -> {
                    pos += 1;
                    node = new Node("BinOp", "+", node, mulDiv());
                }
                case "minus" -> {
                    pos += 1;
                    node = new Node("BinOp", "-", node, mulDiv());
                }

                default -> {
                    return node;
                }
            }
        }

        return node;
    }

    public Node Parse(){
        return plusMinus();
    }
}