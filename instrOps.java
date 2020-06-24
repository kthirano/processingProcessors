public final class instrOps {
    public static opcode findOp(String instr) throws Exception{
        String op = bin.flipStr(instr.substring(0,7));
        switch (op){
            case "0000011":
                return opcode.OP_LOAD;
            case "0000111":
                return opcode.OP_LOAD_FP;
            case "0001011":
                return opcode.OP_CUSTOM_0;
            case "0001111":
                return opcode.OP_MISC_MEM;
            case "0010011":
                return opcode.OP_OP_IMM;
            case "0010111":
                return opcode.OP_AUIPC;
            case "0011011":
                return opcode.OP_OP_IMM_32;
            case "0100011":
                return opcode.OP_STORE;
            case "0100111":
                return opcode.OP_STORE_FP;
            case "0101011":
                return opcode.OP_CUSTOM_1;
            case "0101111":
                return opcode.OP_AMO;
            case "0110011":
                return opcode.OP_OP;
            case "0110111":
                return opcode.OP_LUI;
            case "0111011":
                return opcode.OP_OP_32;
            case "1000011":
                return opcode.OP_MADD;
            case "1000111":
                return opcode.OP_MSUB;
            case "1001011":
                return opcode.OP_NMSUB;
            case "1001111":
                return opcode.OP_NMADD;
            case "1010011":
                return opcode.OP_OP_FP;
            case "1011011":
                return opcode.OP_CUSTOM_2;
            case "1100011":
                return opcode.OP_BRANCH;
            case "1100111":
                return opcode.OP_JALR;
            case "1101111":
                return opcode.OP_JAL;
            case "1110011":
                return opcode.OP_SYSTEM;
            case "1111011":
                return opcode.OP_CUSTOM_3;
            default:
                throw new RISCVException("Invalid opcode: " + op);
        }
    }

    public static String func3(String instr){return bin.flipStr(instr.substring(12, 15));}

    public static String func7(String instr){return bin.flipStr(instr.substring(25, 32));}

    public static String rs1(String instr){return bin.flipStr(instr.substring(15, 20));}

    public static String rs2(String instr){return bin.flipStr(instr.substring(20, 25));}

    public static String rd(String instr){return bin.flipStr(instr.substring(7, 12));}
}
