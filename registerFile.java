public class registerFile {
    private String[] registers;
    /*
    Register widths depend on the RISCV base variant. for RV32, they are 32 bits long,
    RV64 are 64 bits long, RV128 are 128 bits long etc
     */
    public registerFile(){
        this.registers = new String[32];
        //this.registers[0] = bin.bitExtend("0", 32, '0');
        for (int i = 0; i < 32; i ++){
            this.registers[i] = bin.bitExtend("0", 32, '0');
        }
    }
    public String get(int reg){
        return registers[reg];
    }

    public void handleWrite(String address, String val, boolean wrEnable){
        if (wrEnable){
            write(val, bin.uBinToInt(address));
        }
    }

    private void write(String val, int reg){
        if(reg > 32 || reg < 0){
            throw new IndexOutOfBoundsException("Invalid register address: " + reg);
        }
        registers[reg] = val;
    }
    public void reset() {
        for (int i = 0; i < 32; i++) {
            registers[i] = bin.bitExtend("0", 32, '0');
        }
    }
}
