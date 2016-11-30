package org.pinky83.alfaplus.util;

import java.sql.*;

/**
 * Created by pinky83 on 28.09.2016./
 * For Database connection tests, use only Jaybird driver and native JDBC
 */
public class BaseConnector {
    private static String basePath = "c:\\CLARITYIB.GDB?encoding=WIN1251";
    private static String url = "jdbc:firebirdsql:localhost:" + basePath;
    private static String strUser = "SYSDBA";
    private static String strPassword = "masterkey";
    private static String sqlQuery = "SELECT * FROM xpatient";

    public static void main(String[] args) {
        try{
            Class.forName("org.firebirdsql.jdbc.FBDriver").newInstance();
        }catch (IllegalAccessException | ClassNotFoundException | InstantiationException ici){
            ici.printStackTrace();
        }

        Connection conn = null;
        try{
            conn = DriverManager.getConnection(url, strUser, strPassword);
            if (conn == null) System.err.println("Чет не могу коннектнуться к базе...");

            Statement stmnt = conn.createStatement();
            ResultSet result = stmnt.executeQuery(sqlQuery);
            int fakeCount =5;

            int counter =  result.getMetaData().getColumnCount();
            while (result.next()){
                System.out.println();
                for (int n=1;n < counter+1;n++) {
                    Object obj = result.getObject(n);
                    String current = obj+" | ";
                    if (obj!=null) System.out.print(current);
                }
                //if(--fakeCount==0)break;
            }

            stmnt.close();
            conn.close();
        }catch (SQLException se){
            se.printStackTrace();
        }
    }
}
