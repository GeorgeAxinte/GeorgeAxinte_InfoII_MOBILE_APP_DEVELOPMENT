public class NumericConverter {
    public static int hexToDecimal(String hex){
        int decimal=0;
        for(int i=0;i<hex.length();i++){
            char ch=hex.charAt(i);
            int val=Character.getNumericValue(ch);
            decimal=decimal*16+val;
        }
        return decimal;
    }
    public static void main(String args[]){
        String hex="1A3";
        int decimal = hexToDecimal(hex);
        System.out.println(decimal);
    }
}

