/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wdym_proyecto1_sistemasinteligentes;

import java.util.Scanner;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Harold Mendoza
 */
public class WDYM_Proyecto1_SistemasInteligentes {

    /**
     * @param args the command line arguments
     * @throws SQLException
     */
    public static void main(String[] args) throws SQLException {
        // TODO code application logic here
        File posicion = new File("./");
        boolean exit = false;
        Scanner sc = new Scanner(System.in);
        String comando = "";
        String[] arrayls = {"ks", "os", ";s", ",s", ".s", "lw", "lx", "la", "ld", "lz", "sl", ";a", ";d", ";w", ";x",
            ";z", "ka", "kd", "kw", "kx", "kz", "ma", "md", "mw", "mx", "mz", "oa", "od", "ow", "ox", "oz", ",a",
            ",d", ",w", ",x", ",z", ".a", ".d", ".w", ".x", ".z"};
        String[] arraydate = {"dqte", "dzte", "dste", "dwte", "dxte", "dare", "daee", "daye", "dage", "dafe", "dahe",
            "da5e", "da6e", "datw", "datd", "datr", "dat3", "dat4", "sate", "fate", "xate", "cate", "eate", "wate",
            "rate"};
        Connection myConn = null;
        Statement myStmt = null;
        ResultSet myRs = null;

        String user = "root";
        String pass = "HM2018QazXSW";

        try {
            // Get Connection to database
            myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/wdym_database", user, pass);
            System.out.println("Database Connection Success");

            do {

                // Create a Statement
                myStmt = myConn.createStatement();

                // Execute SQL query
                myRs = myStmt.executeQuery("select * from wdym_database.commands");

                System.out.println("Insert Command:");
                comando = sc.next();

                // Process the result set
                while (myRs.next()) {
                    if (myRs.getString("oldCommand").equals(comando) && myRs.getString("newCommand").equals("ls")) {
                        comando = "ls";
                    }
                    if (myRs.getString("oldCommand").equals(comando) && myRs.getString("newCommand").equals("date")) {
                        comando = "date";
                    }
                }

                // Exit
                if (comando.equals("exit")) {
                    exit = true;
                }
                // ls
                if (comando.equals("ls")) {
                    imprimirLS(posicion);
                }
                // validar ls
                for (int i = 0; i < arrayls.length; i++) {
                    if (comando.equals(arrayls[i])) {
                        Scanner sc2 = new Scanner(System.in);
                        String answer = "";
                        System.out.println("Did You Mean: \"ls\" [y]es or [n]o?");
                        answer = sc2.next();
                        if (answer.equals("y") || answer.equals("yes")) {
                            String sql = "insert into commands"
                                    + "(oldCommand, newCommand)"
                                    + "values ('" + arrayls[i] + "' ," + "'ls'" + ")";
                            myStmt.execute(sql);
                            System.out.println("Learning...");
                            imprimirLS(posicion);
                        } else {
                            System.out.println("Command couldn't been identified. Please Try Again.");
                        }
                    }
                }

                // date
                if (comando.equals("date")) {
                    datecmd();
                }
                // validar date
                for (int i = 0; i < arraydate.length; i++) {
                    if (comando.equals(arraydate[i])) {
                        Scanner sc3 = new Scanner(System.in);
                        String answer = "";
                        System.out.println("Did You Mean: \"date..\" [y]es or [n]o?");
                        answer = sc3.next();
                        if (answer.equals("y") || answer.equals("yes")) {
                            String sql = "insert into commands"
                                    + "(oldCommand, newCommand)"
                                    + "values ('" + arraydate[i] + "' ," + "'date'" + ")";
                            myStmt.execute(sql);
                            System.out.println("Learning...");
                            datecmd();
                        } else {
                            System.out.println("Command couldn't been identified. Please Try Again.");
                        }
                    }
                }

            } while (!exit);

        } catch (SQLException ex) {
            Logger.getLogger(WDYM_Proyecto1_SistemasInteligentes.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (myRs != null) {
                myRs.close();
            }

            if (myStmt != null) {
                myStmt.close();
            }

            if (myConn != null) {
                myConn.close();
            }

        }

    }

    public static void imprimirLS(File posicion) {

        File[] files = posicion.listFiles();
        for (File file : files) {
            if (file.isDirectory()) {
                // textoActual += "Directorio: " + file.getName() + "\n";
                System.out.println("     <DIR>   " + file.getName());
            }
            if (file.isFile()) {
                // textoActual += "Archivo: " + file.getName() + "\n";
                System.out.println("     <FILE>   " + file.getName());
            }
        }
    }

    public static void datecmd() {
        Date fecha = new Date();
        SimpleDateFormat df = new SimpleDateFormat("EEE, MMM d, YYYY");

        Date tiempo = new Date();
        SimpleDateFormat df2 = new SimpleDateFormat("hh:mm:ss a");

        System.out.println(df.format(fecha) + "  " + df2.format(tiempo));
    }

}
