import java.util.Scanner;

public class consoleInteract {
    private Scanner reader;
    private String[] commands = {"done", "next (n)", "inst", "opcode", "func3", "rs1", "rs2", "rd", "list", "readdata1", "readdata2", "immediate", "operation",
                    "aluop", "aluresult"};

    public consoleInteract(){
        reader = new Scanner(System.in);
    }

    public int read(registerFile rF, dataMemory dM, controller cont, int PC, String instr, String readData1, String readData2, String immediate, alu operation, String aluResult) throws Exception{
        System.out.println("PC: " + PC);
        while (true){
            String s = reader.next();
            switch (s){
                case "done":
                    return -1; //-1 for done, 0 for pause, 1 for go next
                case "next":
                case "n":
                    return 1;
                case "inst":
                    System.out.println(bin.flipStr(instr));
                    break;
                case "opcode":
                    System.out.println(instrOps.findOp(instr));
                    break;
                case "func3":
                    System.out.println(instrOps.func3(instr));
                    break;
                case "func7":
                    System.out.println(instrOps.func7(instr));
                    break;
                case "rs1":
                    System.out.println(bin.binToInt(instrOps.rs1(instr)));
                    break;
                case "rs2":
                    System.out.println(bin.binToInt(instrOps.rs2(instr)));
                    break;
                case "rd":
                    System.out.println(bin.binToInt(instrOps.rd(instr)));
                    break;
                case "readdata1":
                    System.out.println(bin.binToInt(readData1));
                    break;
                case "readdata2":
                    System.out.println(bin.binToInt(readData2));
                    break;
                case "immediate":
                    System.out.println(bin.binToInt(immediate));
                    break;
                case "operation":
                    System.out.println(operation);
                    break;
                case "aluop":
                    System.out.println(cont.aluop);
                    break;
                case "aluresult":
                    System.out.println(bin.binToInt(aluResult));
                    break;
                case "list":
                case "\n":
                default:
                    printCommands();
            }
        }
    }

    private void printCommands(){
        System.out.println("Available commands:");
        for (int i = 0; i < commands.length; i++){
            System.out.print(commands[i] + " ");
        }
        System.out.println();
    }

}
