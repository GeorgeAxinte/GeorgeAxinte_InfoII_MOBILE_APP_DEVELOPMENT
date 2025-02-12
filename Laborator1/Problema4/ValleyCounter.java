public class ValleyCounter {
    public static int valleyCounter(String valley,int ValleyNumber) {
        int contor=0;
        for(int i=0;i<valley.length();i++){
            char ch=valley.charAt(i);
            if(ch=='D') {
                contor--;
            }
            else if(ch=='U'){
                contor++;
                if(contor>0)
                    ValleyNumber++;
            }
            else break;
        }
        return ValleyNumber;
    }
    public static void main(String[] args) {
        String Valley = "UDDDUDUU";
        System.out.println(valleyCounter(Valley,0));
    }
}
