import java.util.ArrayList;

import static java.lang.Character.isDigit;

class Token{
    public String type;
    public String value;

    public Token(String type_, String value_){
        type = type_;
        value = value_;
    }
}

public class Lexer {
    private final String source;
    private int pos;
    private final ArrayList<Token> tokens = new ArrayList<>();
    public Lexer(String source_){
        source = source_;
    }

    public void pushToken(String type, String value){
        tokens.add(new Token(type, value));
    }

    public boolean charNotNull(){
        return pos < source.length();
    }

    public Character getChar(){
        return source.charAt(pos);
    }

    public String parseNumber(){
        String value = "";

        while(charNotNull() && isDigit(getChar())){
            value = value.concat(getChar().toString());
            pos++;
        }

        pos--;

        return value;
    }

    public ArrayList<Token> Parse(){
        while (charNotNull()){
            Character currentChar = getChar();
            if(currentChar == '+'){
                pushToken("plus", "+");
            } else if(currentChar == '-'){
                pushToken("minus", "-");
            } else if(currentChar == '*'){
                pushToken("mul", "*");
            } else if(currentChar == '/'){
                pushToken("div", "/");
            } else if(isDigit(currentChar)){
                pushToken("number", parseNumber());
            }
            pos++;
        }

        return tokens;
    }
}
