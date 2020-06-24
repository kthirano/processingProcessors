public final class aluCompute {
    public static String findResult (String op1, String op2, alu operation){
        switch(operation){
            case ALU_AND:
                String resultAnd = "";
                for (int i = 0; i < 32; i++){
                    if (op1.charAt(i) == '1' && op2.charAt(i) == '1'){
                        resultAnd += "1";
                    }
                    else {
                        resultAnd += "0";
                    }
                }
                return resultAnd;
            case ALU_OR:
                String resultOr = "";
                for (int i = 0; i < 32; i++){
                    if (op1.charAt(i) == '1' || op2.charAt(i) == '1'){
                        resultOr += "1";
                    }
                    else {
                        resultOr += "0";
                    }
                }
                return resultOr;
            case ALU_ADD:
                return bin.signExtend(bin.intToBin(bin.binToInt(op1) + bin.binToInt(op2)), 32);
            case ALU_SUB:
                return bin.signExtend(bin.intToBin(bin.binToInt(op1) - bin.binToInt(op2)), 32);
            case ALU_XOR:
                String resultXOr = "";
                for (int i = 0; i < 32; i++){
                    if ((op1.charAt(i) == '1' && op2.charAt(i) == '0') || (op2.charAt(i) == '1' && op1.charAt(i) == '0')){
                        resultXOr += "1";
                    }
                    else {
                        resultXOr += "0";
                    }
                }
                return resultXOr;
            case ALU_SLT:
                if (bin.binToInt(op1) < bin.binToInt(op2)){
                    return bin.signExtend("01", 32);
                }
                else{
                    return bin.signExtend("00", 32);
                }
            case ALU_SLTU:
                if (bin.uBinToInt(op1) < bin.uBinToInt(op2)){
                    return bin.signExtend("01", 32);
                }
                else{
                    return bin.signExtend("00", 32);
                }
            case ALU_SRA:
                int SRAfactor = bin.uBinToInt(op2);
                String resultSRA = op1;
                if (SRAfactor >= 32){
                    resultSRA = bin.bitExtend("", 32, resultSRA.charAt(0));
                }
                else{
                    resultSRA = resultSRA.substring(0, SRAfactor);
                    resultSRA = bin.signExtend(resultSRA, 32);
                }
                return resultSRA;
            case ALU_SLL:
                int SLLfactor = bin.uBinToInt(op2);
                String resultSLL = op1;
                if (SLLfactor >= 32){
                    resultSLL = bin.bitExtend("", 32, '0');
                }
                else{
                    resultSLL = resultSLL.substring(0, SLLfactor);
                    resultSLL += bin.bitExtend("", SLLfactor, '0');
                }
                return resultSLL;
            case ALU_SRL:
                int SRLfactor = bin.uBinToInt(op2);
                String resultSRL = op1;
                if (SRLfactor >= 32){
                    resultSRL = bin.bitExtend("", 32, '0');
                }
                else{
                    resultSRL = resultSRL.substring(0,SRLfactor);
                    resultSRL = bin.bitExtend("", SRLfactor, '0') + resultSRL;
                }
                return resultSRL;
            case ALU_SGE:
                if (bin.binToInt(op1) >= bin.binToInt(op2)){
                    return bin.signExtend("01", 32);
                }
                else{
                    return bin.signExtend("00", 32);
                }
            case ALU_SGEU:
                if (bin.uBinToInt(op1) >= bin.uBinToInt(op2)){
                    return bin.signExtend("01", 32);
                }
                else{
                    return bin.signExtend("00", 32);
                }
            default:
                return bin.bitExtend("0", 32, '0');
        }
    }

    public static boolean branchOut (String op1, String op2, alu operation){
        switch (operation){
            case ALU_SUB:
                int subResult = bin.binToInt(op1) - bin.binToInt(op2);
                if (subResult == 0){return true;}
                else{return false;}
            case ALU_SLT:
                return bin.binToInt(op1) < bin.binToInt(op2);
            case ALU_SLTU:
                return bin.uBinToInt(op1) < bin.uBinToInt(op2);
            case ALU_SGE:
                return bin.binToInt(op1) >= bin.binToInt(op2);
            case ALU_SGEU:
                return bin.uBinToInt(op1) >= bin.uBinToInt(op2);
            default:
                return false;
        }
    }

}
