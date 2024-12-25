package JdbcConnect;

import static JdbcConnect.newJDBCConnextion.*;

public class jdbcCon {
    public static void main(String [] args){


        System.out.println(ContainUser("admin"));
        System.out.println(ContainInfo("450981200304292717","19914940985"));
        System.out.println(ContainLogin("admin", "password"));


    }

}
