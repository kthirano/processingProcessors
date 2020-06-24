public final class bin {
    //we only deal with signed binary here
    public static int binToInt(String bin){
        /*
        int total = 0;
        for (int i = bin.length() - 1; i >= 0; i-- ){
            if (bin.charAt(i) == '1'){
                total += Math.pow(2, (bin.length() - i - 1));
            }
        }
        */
        int total = uBinToInt(bin);
        if (bin.charAt(0) == '1'){
            total = total - (int)Math.pow(2, (bin.length()));
        }
        return total;
    }

    public static int uBinToInt(String bin){
        int total = 0;
        for (int i = bin.length() - 1; i >= 0; i-- ){
            if (bin.charAt(i) == '1'){
                total += Math.pow(2, (bin.length() - i - 1));
            }
        }
        return total;
    }

    public static String flipStr(String bin){
        String newIns = "";
        int len = bin.length();
        for (int i = 0; i < len; i++){
            newIns += bin.charAt(len - 1 - i);
        }
        return newIns;
    }

    public static String intToBin(int num){
        if (num >= 0){return posIntToBin(num);}
        else {return negIntToBin(num);}
    }

    private static String posIntToBin(int num){
        int startingPow = 0;
        int compareTo = 1;
        while (compareTo <= num){
            startingPow++;
            compareTo = (int)Math.pow(2, startingPow);
        }
        String result = "";
        int tempNum = num;
        while (startingPow >= 0){
            int power = (int)Math.pow(2, startingPow);
            if (tempNum < power){
                result += '0';
            }
            else{
                result += '1';
                tempNum -= power;
            }
            startingPow--;
        }
        return result;
    }
    private static String negIntToBin(int num){
        String complement = posIntToBin(-1 * num - 1);
        String result = "";
        for (int i = 0; i < complement.length(); i++){
            if(complement.charAt(i) == '1'){
                result += '0';
            }
            else{
                result += '1';
            }
        }
        return result;
    }
    public static String signExtend(String binary, int targetLen){
            char sign = binary.charAt(0);
            return bitExtend(binary, targetLen, sign);
    }

    public static String bitExtend(String binary, int targetLen, char bit){
        if (binary.length() > targetLen){
            throw new AssertionError("Cannot sign extend to " + targetLen + " from " + binary.length() + " bits");
        }
        else if (binary.length() == targetLen){
            return binary;
        }
        else{
            int numRep = targetLen - binary.length();
            String result = "";
            for (int i = 0; i < numRep; i++){
                result += bit;
            }
            result += binary;
            return result;
        }
    }
}
