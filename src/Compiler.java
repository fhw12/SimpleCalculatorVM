import java.util.ArrayList;

public class Compiler {
    private final Node rootNode;
    private final ArrayList<Byte> bytecode = new ArrayList<>();
    public Compiler(Node rootNode_){
        rootNode = rootNode_;
    }

    public void jmpNode(Node node){
        if(node.left != null){
            generateCode(node.left);
        }

        if(node.right != null){
            generateCode(node.right);
        }
    }

    public void generateCode(Node node){
        switch (node.type){
            case "number" -> {
                bytecode.add((byte) 0x01);
                bytecode.add(Byte.parseByte(node.value));
            }

            case "BinOp" -> {
                switch (node.value) {
                    case "+" -> {
                        jmpNode(node);
                        bytecode.add((byte) 0x02);
                    }

                    case "-" -> {
                        jmpNode(node);
                        bytecode.add((byte) 0x03);
                    }

                    case "*" -> {
                        jmpNode(node);
                        bytecode.add((byte) 0x04);
                    }

                    case "/" -> {
                        jmpNode(node);
                        bytecode.add((byte) 0x05);
                    }
                }
            }
        }
    }

    public ArrayList<Byte> compile(){
        generateCode(rootNode);
        return bytecode;
    }
}