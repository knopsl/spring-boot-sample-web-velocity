package sample.velocity;

import java.sql.*;

/**
 * Created by stepanma on 29.12.2014.
 */
public class SQLiteJDBC {
    Connection c;
    Statement stmt;

    public SQLiteJDBC() {
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:test.db");
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }

    public void createtable() {
        try {
            stmt = c.createStatement();
            String sql = "CREATE TABLE USERS " +
                    "(ID INT PRIMARY KEY     NOT NULL," +
                    " NAME           TEXT    NOT NULL, " +
                    " AGE            INT     NOT NULL, " +
                    " ADDRESS        CHAR(50)";

            stmt.executeUpdate(sql);
            stmt.close();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }

    }

    public String readtable() {

            ResultSet rs = null;
            StringBuilder stringBuilder = new StringBuilder();
        try {
            stmt = c.createStatement();
            rs = stmt.executeQuery("SELECT * FROM USERS");
            while (rs.next()) {

                int id = rs.getInt("id");
                String name = rs.getString("name");
                int age = rs.getInt("age");
                String address = rs.getString("address");
                stringBuilder.append("ID = " + id);
                stringBuilder.append("NAME = " + name);
                stringBuilder.append("AGE = " + age);
                stringBuilder.append("ADDRESS = " + address);


            }

            rs.close();
            stmt.close();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());

        }

        return stringBuilder.toString();
    }

    public void insertdata() {

        try {
            stmt = c.createStatement();
            String sql = "INSERT INTO COMPANY (ID,NAME,AGE,ADDRESS) " +
                    "VALUES (1, 'Paul', 32, 'California');";
            stmt.executeUpdate(sql);

            sql = "INSERT INTO COMPANY (ID,NAME,AGE,ADDRESS) " +
                    "VALUES (2, 'Allen', 25, 'Texas');";
            stmt.executeUpdate(sql);

            sql = "INSERT INTO COMPANY (ID,NAME,AGE,ADDRESS) " +
                    "VALUES (3, 'Teddy', 23, 'Norway');";
            stmt.executeUpdate(sql);

            sql = "INSERT INTO COMPANY (ID,NAME,AGE,ADDRESS) " +
                    "VALUES (4, 'Mark', 25, 'Rich-Mond ');";
            stmt.executeUpdate(sql);

            stmt.close();
            c.commit();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());

        }
    }

}


