public class controller {
    public boolean alu_src;
    public String mem2reg;
    public boolean reg_write;
    public boolean mem_read;
    public boolean mem_write;
    //public String write_enable;
    //public String read_enable;
    public String aluop;
    public boolean branch;
    public boolean jalr_mode;
    public boolean jal_mode;
    //boolean lui_mode;
    //String writeback;

    public controller(String instr) throws Exception{
        opcode op = instrOps.findOp(instr);
        alu_src = ( op == opcode.OP_OP_IMM ||
                    op == opcode.OP_LOAD ||
                    op == opcode.OP_STORE ||
                    op == opcode.OP_JALR ||
                    op == opcode.OP_LUI);
        switch (op){
            case OP_LOAD:
            case OP_LOAD_FP:
                mem2reg = "01";
                break;
            case OP_AUIPC:
                mem2reg = "10";
                break;
            case OP_JAL:
            case OP_JALR:
                mem2reg = "11";
                break;
            default:
                mem2reg = "00";
        }
        switch (op){
            case OP_LOAD:
            case OP_OP_IMM:
            case OP_OP:
            case OP_JAL:
            case OP_LUI:
            case OP_AUIPC:
            case OP_JALR:
                reg_write = false;
            default:
                reg_write = true;
        }
        mem_read = (op == opcode.OP_LOAD);
        mem_write = (op == opcode.OP_STORE);
        switch(op){
            case OP_BRANCH:
                aluop = "01";
                break;
            case OP_LUI:
            case OP_AUIPC:
            case OP_JAL:
                aluop = "11";
                break;
            case OP_OP:
            case OP_OP_IMM:
                aluop = "10";
                break;
            default:
                aluop = "00";
        }
        branch = (op == opcode.OP_BRANCH);
        jalr_mode = (op == opcode.OP_JALR);
        jal_mode = (op == opcode.OP_JAL);
    }
}
