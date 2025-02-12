public class NumerePrietene {
    public static int sumaDivizorilor(int nr) {
        int suma = 1;
        for (int i = 2; i <= nr / 2; i++) {
            if (nr % i == 0) {
                suma += i;
            }
        }
        return suma;
    }

    public static boolean suntPrietene(int a, int b) {
        return sumaDivizorilor(a) == b && sumaDivizorilor(b) == a;
    }

    public static void main(String[] args) {
        int nr1 = 220, nr2 = 284;
        if (suntPrietene(nr1, nr2)) System.out.println(nr1 + " si " + nr2 + " sunt numere prietene");
        else System.out.println(nr1 + " si " + nr2 + " nu sunt numere prietene");
    }
}