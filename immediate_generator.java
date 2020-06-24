public final class immediate_generator {
    public static String imm(String inst) throws Exception{
        opcode op = instrOps.findOp(inst);
        switch(op){
            case OP_LOAD:
            case OP_JALR:
                String flippedImmLoad = bin.flipStr(inst.substring(20,32));
                String extendedLoad = bin.signExtend(flippedImmLoad, 32); //signExtend extends bit 0, so we need to flip the string
                return extendedLoad;
            case OP_OP_IMM:
                if (instrOps.func3(inst) == "001" || instrOps.func3(inst) == "101"){
                    String flippedImmImm = bin.flipStr(inst.substring(20,25));
                    return bin.bitExtend(flippedImmImm, 32, '0');
                }
                else{
                    String flippedImmImm2 = bin.flipStr(inst.substring(20,32));
                    return bin.signExtend(flippedImmImm2,32);
                }
            case OP_STORE:
                String flippedImmStore = bin.flipStr(inst.substring(7,12) + inst.substring(25,32));
                return bin.signExtend(flippedImmStore, 32);
            case OP_LUI:
            case OP_AUIPC:
                return bin.bitExtend(inst.substring(12,31), 32, '0');
            case OP_JAL:
                String ImmJAL = "0" + inst.substring(21,31) + inst.substring(20,21) + inst.substring(12,20) + inst.substring(31,32);
                return bin.signExtend(ImmJAL, 32);
            case OP_BRANCH:
                String ImmBr = inst.substring(8,12) + inst.substring(25,31) + inst.substring(7,8) + inst.substring(31,32);
                return bin.signExtend(ImmBr, 32);
            default:
                return bin.bitExtend("0", 32, '0');
        }
    }
}
