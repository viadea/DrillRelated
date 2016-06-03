/**
 *  * Created by Hao Zhu on 2016-06-02.
 *   */
import java.sql.*;

public class HealthCheck {
    static final String JDBC_DRIVER = "org.apache.drill.jdbc.Driver";
    public static String Drillbit_Host = "localhost";
    public static String Drillbit_Port = "31010";
    static final String DB_URL_Prefix = "jdbc:drill:drillbit=";

    public static String USER = "mapr";
    public static String PASS = "mapr";

    public static void main(String[] args) {
        Connection conn = null;
        Statement stmt = null;
        if (args.length > 0) {
          USER = args[0];
        }
        if (args.length > 1) {
          PASS = args[1];
        }       
        if (args.length > 2) {
          Drillbit_Host = args[2];
        }   
        if (args.length > 3) {
          Drillbit_Port = args[3];
        }  

        try{
            String DB_URL = DB_URL_Prefix + Drillbit_Host + ":" + Drillbit_Port;
            Class.forName(JDBC_DRIVER);
            System.out.println("Step 1. Creating Drill Connection to : " + DB_URL);
            conn = DriverManager.getConnection(DB_URL,USER,PASS);

            stmt = conn.createStatement();
            String sql = "select hostname,user_port from sys.drillbits";
            System.out.println("Step 2. Fetching all drillbits");
            ResultSet rs = stmt.executeQuery(sql);

            while(rs.next()) {
                String hostname = rs.getString("hostname");
                String user_port = rs.getString("user_port");
                Check_Drillbit(hostname,user_port);
                
            }

            rs.close();
            stmt.close();
            conn.close();
        } catch(SQLException se) {
            se.printStackTrace();
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            try{
                if(stmt!=null)
                    stmt.close();
            } catch(SQLException se2) {
            }
            try {
                if(conn!=null)
                    conn.close();
            } catch(SQLException se) {
                se.printStackTrace();
            }
        }
    }

    
    public static int Check_Drillbit(String hostname, String user_port){
        System.out.println("Connecting to " + hostname + ":" + user_port);
        Connection conn = null;
        Statement stmt = null;
        
        try{  
          String DB_URL = DB_URL_Prefix + hostname + ":" + user_port;
          Class.forName(JDBC_DRIVER);
          conn = DriverManager.getConnection(DB_URL,USER,PASS);
          stmt = conn.createStatement();
          String sql = "select * from sys.version";
          ResultSet rs = stmt.executeQuery(sql);
          while(rs.next()) {    
            }
          rs.close();
          stmt.close();
          conn.close();
          System.out.println("Successful!");
          return 0;

        } catch(SQLException se) {
            se.printStackTrace();
            return 1;
        } catch(Exception e) {
            e.printStackTrace();
            return 1;
        } finally {
            try{
                if(stmt!=null)
                    stmt.close();
            } catch(SQLException se2) {
            }
            try {
                if(conn!=null)
                    conn.close();
            } catch(SQLException se) {
                se.printStackTrace();
            }
        }


        
    }
}
