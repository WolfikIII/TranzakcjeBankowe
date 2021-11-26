import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class Login {
    //Transactions transactions = new Transactions();
    String login;
    String haslo;
    boolean sprawdzZaloguj;
    Scanner scan = new Scanner(System.in);
    static String URL = "jdbc:mysql://localhost:3306/tranzakcje";
    static String USER = "root";
    static String PASSWORD = "zaq1@WSXcde3";
    public String wyswietlKonto() {
      Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            Statement display = connection.createStatement();
            String displayQuery = "SELECT tranzakcje.login.login, tranzakcje.klient.imie, tranzakcje.klient.nazwisko, tranzakcje.konto.saldo, tranzakcje.konto.numerKonta \n" +
                    "FROM  tranzakcje.klient \n" +
                    "INNER JOIN tranzakcje.konto \n" +
                    "ON tranzakcje.klient.idkonto = tranzakcje.konto.idkonto\n" +
                    "INNER JOIN tranzakcje.login\n" +
                    "ON tranzakcje.klient.idlogin = tranzakcje.login.idlogin\n" +
                    "AND tranzakcje.login.login = '"+login+"'; ";
            ResultSet resultSet = display.executeQuery(displayQuery);
            while (resultSet.next()){
                System.out.println("Login: " + resultSet.getString("login"));
                System.out.println("Imie: " + resultSet.getString("imie"));
                System.out.println("Nazwisko: " + resultSet.getString("nazwisko"));
                System.out.println("Saldo: " + resultSet.getString("saldo") + "zł");
                System.out.println("Numer konta: " + resultSet.getString("numerKonta"));
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return login;
    }

    public String zaloguj(){
        System.out.println("Login: ");
        login = scan.next();
        System.out.println("Haslo: ");
        haslo = scan.next();
        Connection connection = null;


        try{
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            Statement log = connection.createStatement();
            String queryLogin = "SELECT * FROM tranzakcje.login WHERE login = '"+login+"'and haslo = '"+haslo+"'";
            ResultSet logowanie = log.executeQuery(queryLogin);
            if(logowanie.next()){
                System.out.println("Zalogowano!");
                wyswietlKonto();
                sprawdzZaloguj = true;


            }
            else
                System.out.println("Zły login lub hasło");
            sprawdzZaloguj = false;
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return login;
    }

}
