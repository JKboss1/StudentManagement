package JdbcConnect;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class test {
    public static void main(String[] args) {
        Connection com = null;
        String url = "jdbc:mysql://localhost:3306/MySql?user=root&password=666666&" +
                "useSSL=false&serverTimezone=UTC&characterEncoding=utf8&useUnicode=true";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            com = DriverManager.getConnection(url);
            System.out.println("数据库连接成功");
        } catch (ClassNotFoundException e) {
            System.out.println("驱动加载失败: " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("数据库连接失败: " + e.getMessage());
        } finally {
            try {
                if (com != null) {
                    com.close();
                }
            } catch (SQLException e) {
                System.out.println("关闭数据库连接时出错: " + e.getMessage());
            }
        }


    }

    }



