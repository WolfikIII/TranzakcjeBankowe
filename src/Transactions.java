import java.sql.*;
import java.util.Scanner;

public class Transactions extends Login {
    Scanner scanner = new Scanner(System.in);
    static String URL = "jdbc:mysql://localhost:3306/tranzakcje";
    static String USER = "root";
    static String PASSWORD = "zaq1@WSXcde3";
    String imie;
    String nazwisko;
    int numerRachunku;
    int numerRachunku2;
    static float kasa;
    float saldo1;
    float saldo2;
    float saldoPoPrzelewie;
    float saldo3;
    public void wplacKase(){
        System.out.println("Podaj kwote do wpłaty");
        float wplacanaKwota = scanner.nextFloat();
        System.out.println("Podaj numer konta");
        numerRachunku2 = scanner.nextInt();
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            Statement wplac = connection.createStatement();
            Statement sprawdzenieIstnieniaRachunkuKonta = connection.createStatement();
            Statement sprawdzenieStanuKontaPrzedPrzelewem = connection.createStatement();
            String sprawdzStanKontaPrzedPrzelewem = "SELECT tranzakcje.konto.saldo\n" +
                    "FROM  tranzakcje.klient\n" +
                    "INNER JOIN tranzakcje.konto\n" +
                    "ON tranzakcje.klient.idkonto = tranzakcje.konto.idkonto\n" +
                    "INNER JOIN tranzakcje.login\n" +
                    "ON tranzakcje.klient.idlogin = tranzakcje.login.idlogin\n" +
                    "AND tranzakcje.konto.numerKonta = '" + numerRachunku2 + "'";
            ResultSet resultSet2 = sprawdzenieStanuKontaPrzedPrzelewem.executeQuery(sprawdzStanKontaPrzedPrzelewem);
            resultSet2.next();
            saldo3 = resultSet2.getFloat("saldo");
            String sprawdzenieKonta = "SELECT tranzakcje.konto.numerKonta FROM tranzakcje.konto WHERE numerKonta = '"+numerRachunku2+"'";
            ResultSet resultSet = sprawdzenieIstnieniaRachunkuKonta.executeQuery(sprawdzenieKonta);
            if (resultSet.next()) {
                String wplacenie = "UPDATE tranzakcje.konto SET saldo = '" + wplacanaKwota+ "' + '"+saldo3+"' WHERE numerKonta = '" + numerRachunku2 + "'";
                wplac.executeUpdate(wplacenie);
                System.out.println("Kwota została wpłacona");
            }else {
                System.out.println("Nie znaleziono takiego numeru konta");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

    }
    public void przelejSrodki() {
        if (sprawdzZaloguj = true) {
            Connection connection = null;

            System.out.println("Imie: ");
            imie = scanner.next();
            System.out.println("Nazwisko: ");
            nazwisko = scanner.next();
            System.out.println("Podaj numer konta: ");
            numerRachunku = scanner.nextInt();
            System.out.println("Kwota przelewu: ");
            kasa = scanner.nextFloat();
            try {
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
                Statement query = connection.createStatement();
                Statement sprawdzStanKontaUzytkownika1 = connection.createStatement();
                Statement sprawdzStanKontaUzytkownika2 = connection.createStatement();
                String sprawdzanieStanuKonta1 = "SELECT tranzakcje.konto.saldo\n" +
                        "FROM  tranzakcje.klient\n" +
                        "INNER JOIN tranzakcje.konto\n" +
                        "ON tranzakcje.klient.idkonto = tranzakcje.konto.idkonto\n" +
                        "INNER JOIN tranzakcje.login\n" +
                        "ON tranzakcje.klient.idlogin = tranzakcje.login.idlogin\n" +
                        "AND tranzakcje.login.login = '" + login + "'";
                String sprawdzanieStanuKonta2 = "SELECT tranzakcje.konto.saldo\n" +
                        "FROM  tranzakcje.klient\n" +
                        "INNER JOIN tranzakcje.konto\n" +
                        "ON tranzakcje.klient.idkonto = tranzakcje.konto.idkonto\n" +
                        "INNER JOIN tranzakcje.login\n" +
                        "ON tranzakcje.klient.idlogin = tranzakcje.login.idlogin\n" +
                        "AND tranzakcje.konto.numerKonta = '" + numerRachunku + "'";
                ResultSet stanKonta1 = sprawdzStanKontaUzytkownika1.executeQuery(sprawdzanieStanuKonta1);
                ResultSet stanKonta2 = sprawdzStanKontaUzytkownika2.executeQuery(sprawdzanieStanuKonta2);
                stanKonta1.next();
                stanKonta2.next();
                saldo1 = stanKonta1.getFloat("saldo");
                saldo2 = stanKonta2.getFloat("saldo");
                if (saldo1 >= kasa) {
                    String queryPrzelew = "SELECT * FROM tranzakcje.klient, tranzakcje.konto WHERE imie = '" + imie + "'and nazwisko = '" + nazwisko + "' and numerKonta = '" + numerRachunku + "'";
                    ResultSet resultSet = query.executeQuery(queryPrzelew);
                    if (resultSet.next()) {
                        Statement query2 = connection.createStatement();
                        String queryPrzelew2 = "UPDATE tranzakcje.konto SET saldo = '" + saldo2 + "' + '" + kasa + "' WHERE numerKonta = '" + numerRachunku + "'";
                        query2.executeUpdate(queryPrzelew2);
                        Statement query3 = connection.createStatement();
                        String queryPrzelew3 = "UPDATE tranzakcje.konto INNER JOIN tranzakcje.klient \n" +
                                "ON tranzakcje.konto.idkonto=tranzakcje.klient.idkonto\n" +
                                "INNER join tranzakcje.login ON tranzakcje.klient.idlogin=tranzakcje.login.idlogin\n" +
                                "SET tranzakcje.konto.saldo = '" + saldo1 + "' - '" + kasa + "' where tranzakcje.login.login = '" + login + "'";
                        query3.executeUpdate(queryPrzelew3);
                    } else {
                        System.out.println("Nie udało zrealizować się przelewu wprowadzone dane są nieprawidłowe!");
                    }
                }
                else {
                    System.out.println("Za malo srodkow na koncie");
                }

                    Statement sprawdzStanKontaUzytkownika3 = connection.createStatement();
                    String sprawdzanieStanuKonta3 = "SELECT tranzakcje.konto.saldo\n" +
                            "FROM  tranzakcje.klient\n" +
                            "INNER JOIN tranzakcje.konto\n" +
                            "ON tranzakcje.klient.idkonto = tranzakcje.konto.idkonto\n" +
                            "INNER JOIN tranzakcje.login\n" +
                            "ON tranzakcje.klient.idlogin = tranzakcje.login.idlogin\n" +
                            "AND tranzakcje.login.login = '" + login + "'";
                    ResultSet stanKonta3 = sprawdzStanKontaUzytkownika3.executeQuery(sprawdzanieStanuKonta3);
                    stanKonta3.next();
                    saldoPoPrzelewie = stanKonta3.getFloat("saldo");
                    System.out.println("Twoje saldo po przelewie wynosi " + saldoPoPrzelewie);
                } catch(SQLException e){
                    e.printStackTrace();
                }

            }
        }
    }

