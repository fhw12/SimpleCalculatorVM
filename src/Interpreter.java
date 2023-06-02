import java.util.ArrayList;

public class Interpreter {
    ArrayList<Byte> bytecode;
    ArrayList<Integer> stack = new ArrayList<>();
    int ip;

    public Interpreter(ArrayList<Byte> bytecode_){
        bytecode = bytecode_;
    }

    private byte getInstruction(){
        return bytecode.get(ip);
    }

    private byte getInstruction(int shift){
        return bytecode.get(ip + shift);
    }

    private void push(int value){
        stack.add(value);
    }

    private int pop(){
        int value = stack.get(stack.size() - 1);
        stack.remove(stack.size() - 1);

        return value;
    }

    public void eval() {
        switch (getInstruction()){
            case 0x01 -> {
                push(getInstruction(1));
                ip += 2;
            }

            case 0x02 -> {
                int arg1 = pop();
                int arg2 = pop();
                push(arg1 + arg2);
                ip += 1;
            }

            case 0x03 -> {
                int arg1 = pop();
                int arg2 = pop();
                push(arg2 - arg1);
                ip += 1;
            }

            case 0x04 -> {
                int arg1 = pop();
                int arg2 = pop();
                push(arg1 * arg2);
                ip += 1;
            }

            case 0x05 -> {
                int arg1 = pop();
                int arg2 = pop();
                push(arg2 / arg1);
                ip += 1;
            }
        }
    }

    public int execute(){
        while(ip < bytecode.size()){
            eval();
        }

        return pop();
    }
}
