

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Random;
import java.util.Scanner;

public class Registry {
    Scanner scanner = new Scanner(System.in);
    Random random = new Random();
    static String URL = "jdbc:mysql://localhost:3306/tranzakcje";
    static String USER = "root";
    static String PASSWORD = "zaq1@WSXcde3";
    String log;
    String pass;
    String email;
    String imie;
    String nazwisko;
    int nrKonta;
    int idkonto;
    int idlogin;

    public void rejestracja() {
        System.out.println("Wpisz login: ");
        log = scanner.next();
        System.out.println("Wpisz hasło: ");
        pass = scanner.next();
        System.out.println("Podaj adres email: ");
        email = scanner.next();
        System.out.println("Konto zostało utworzone, alby kontynuować podaj swoje dane: ");
        System.out.println("Podaj imie: ");
        imie = scanner.next();
        System.out.println("Podaj nazwisko: ");
        nazwisko = scanner.next();
        System.out.println("Twój numer konta to: ");
        nrKonta = random.nextInt(899999)+100000;
        System.out.println(nrKonta);
    Connection connection = null;
    try
    {
        connection = DriverManager.getConnection(URL, USER, PASSWORD);
        Statement zczytaj = connection.createStatement();
        String sql4 = "SELECT idkonto FROM tranzakcje.konto ORDER BY idkonto DESC";
        ResultSet czytaj = zczytaj.executeQuery(sql4);
        czytaj.next();
        idlogin = czytaj.getInt("idkonto") + 4;
        idkonto = czytaj.getInt("idkonto") + 1;
        Statement create = connection.createStatement();
        Statement create2 = connection.createStatement();
        Statement create3 = connection.createStatement();
        String sql3 = "INSERT INTO tranzakcje.konto (numerKonta, saldo) VALUES ('" + nrKonta + "', null)";
        String sql = "INSERT INTO tranzakcje.login (login, haslo, email) VALUES ('" + log + "', '" + pass + "', '" + email + "')";
        String sql2 = "INSERT INTO tranzakcje.klient (imie, nazwisko, idlogin, idkonto) VALUES ('" + imie + "', '" + nazwisko + "', '"+idlogin+"', '"+idkonto+"')";
        create.executeUpdate(sql3);
        create2.executeUpdate(sql);
        create3.executeUpdate(sql2);


    }
    catch(
    Exception e)

    {
        e.printStackTrace();
    }
}


}
