import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
Registry registry = new Registry();
Transactions transactions = new Transactions();
        System.out.println("Zaloguj: 1");
        System.out.println("Zarejestruj 2");
        int wybor = scanner.nextInt();
        switch (wybor){
            case 1: transactions.zaloguj();
            break;
            case 2: registry.rejestracja();
            break;
        }

        if (transactions.sprawdzZaloguj = true) {
            System.out.println("");
            System.out.println("");
            System.out.println("Przelej środki: 1");
            System.out.println("Wplac kase: 2");
            int wybor2 = scanner.nextInt();
            switch (wybor2){
                case 1: transactions.przelejSrodki();
                break;
                case 2: transactions.wplacKase();
                break;
            }

        }
    }
}
