public final class alu_controller {
    public static alu findOp(String aluop, String funct3, String funct7) throws Exception{
        boolean[] operation = new boolean[4];
        operation[3]= (((aluop.equals("10"))&&((funct3.equals("110")) || (funct3.equals("001")) || (funct3.equals("100"))||(funct7.equals("0000000") && (funct3.equals("101")))||(funct3.equals("011")))) ||
                ( (aluop.equals("01")) && ((funct3.equals("110")) || (funct3.equals("111")))));
        operation[2]= (((aluop.equals("10"))&&((funct3.equals("000"))||(funct3.equals("001"))||(funct3.equals("100")) ) ) ||
                ((aluop.equals("00")) && ((funct3.equals("000"))||(funct3.equals("001"))||(funct3.equals("100")) || (funct3.equals("101")) || (funct3.equals("010"))) ) ||
                ((aluop.equals("01")) && ((funct3.equals("000")) || (funct3.equals("001")) || (funct3.equals("111")) || (funct3.equals("101")))) ||
                (aluop.equals("11")));
        operation[1]=  (( (aluop.equals("10"))&& ((funct7.equals("0100000")&&(funct3.equals("000"))) || (funct3.equals("100")) ||(funct3.equals("101")) ) ) ||
                ((aluop.equals("01"))&& ((funct3.equals("000")) || (funct3.equals("001")))  ));
        operation[0]= (( (aluop.equals("10")) && ((funct3.equals("010")) || (funct3.equals("011"))  )  ) ||
                ((aluop.equals("01")) && ( (funct3.equals("100")) || (funct3.equals("101")) || (funct3.equals("110")) || (funct3.equals("111")))));
        String result = "";
        for (int i = 0; i < 4; i++ ){
            if (operation[i]){result += "1";}
            else {result += "0";}
        }
        return operation(result);
    }
    private static alu operation (String aluopcode) throws Exception{
        switch (aluopcode){
            case "0000":
                return alu.ALU_AND;
            case "0001":
                return alu.ALU_OR;
            case "0010":
                return alu.ALU_ADD;
            case "0110":
                return alu.ALU_SUB;
            case "1100":
                return alu.ALU_NOR;
            case "0111":
                return alu.ALU_XOR;
            case "1000":
                return alu.ALU_SLT;
            case "1001":
                return alu.ALU_SLTU;
            case "0100":
                return alu.ALU_SRA;
            case "0011":
                return alu.ALU_SLL;
            case "0101":
                return alu.ALU_SRL;
            case "1010":
                return alu.ALU_SGE;
            case "1011":
                return alu.ALU_SGEU;
            default:
                throw new RISCVException("Unsupported ALU opcode: " + aluopcode);
        }
    }
}
