public class dataMemory {
    private String[][] memory; //save as bin str, 8 bits long per entry

    public dataMemory(){
        memory = new String[4][512];
    }

    public String handleMemory(int address, String reg2, String fct3, boolean memw, boolean memr){
        if (memr){
            switch (fct3){
                case "010":
                    return loadWord(address);
                case "001":
                    return loadHalf(address);
                case "000":
                    return loadByte(address);
            }
        }
        else if (memw){
            switch (fct3){
                case "010":
                    storeWord(address, bin.binToInt(reg2));
                    break;
                case "001":
                    storeHalf(address, bin.binToInt(reg2));
                    break;
                case "000":
                    storeByte(address, bin.binToInt(reg2));
                    break;
            }
        }
        return bin.bitExtend("0", 32, '0');
    }

    private void checkIndex(int index){
        if (index > 2047 || index <= 0){
            throw new IndexOutOfBoundsException("Memory not in bound to load");
        }
    }
    /*
        Memory will be organized in little-endian fashion:
            - the lowest significant bits will be held in the lowest addressed parcel

        For now we use 4x512 memory structure with a total 2048 bytes of addressable memory, but
        usually in a 32bit architecture 4GB of memory is supported
     */


    //All LOAD instructions must return a 32bit binary String
    private String loadWord(int index){ //index must be between 0 and 255
        checkIndex(index);
        String total = "";
        for (int i = 0; i < 4; i++){
            total = memory[i][index/4] + total;
        }
        return total;
    }
    private String loadHalf(int index){
        checkIndex(index);
        String total = "";
        int whichHalf = index / 4 % 2;
        for (int i = 0; i < 2; i++){
            total = memory[i + whichHalf * 2][index/4] + total;
        }
        return total;
    }
    private String loadByte(int index){
        checkIndex(index);
        int whichQuart = index / 4 % 4;
        String total = memory[whichQuart][index / 4];
        return total;
    }

    private void storeWord(int index, int word){ //word = 32bit, stored as 4 8-bit segments
        checkIndex(index);
        String target = bin.intToBin(word);
        target = bin.signExtend(target, 32);
        for (int i = 0; i < 4; i ++){
            memory[3-i][index/4] = target.substring(i*8, (i+1)*8);
        }
    }
    private void storeHalf(int index, int word){
        checkIndex(index);
        String target = bin.intToBin(word);
        target = bin.signExtend(target, 16);
        int whichHalf = index / 4 % 2;
        for (int i = 0; i < 2; i++){
            memory[3 - (i + whichHalf * 2)][index / 4] = target.substring(i*8, (i+1)*8);
        }
    }
    private void storeByte(int index, int word){
        checkIndex(index);
        String target = bin.intToBin(word);
        target = bin.signExtend(target, 8);
        int whichQuart = index / 4 % 4;
        memory[3 - whichQuart][index / 4] = target;
    }
}
