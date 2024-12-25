package JdbcConnect;

import Project.StudentCtrlSystem.Student;
import UserLoginSystem.User;

import java.sql.*;
import java.util.ArrayList;

public class newJDBCConnextion {
    //定义数据库的url路径
    public static String url = "jdbc:mysql://localhost:3306/sys?user=root&password=666666&useSSL=false&serverTimezone=UTC&characterEncoding=utf8&useUnicode=true";

    public static boolean ContainUser(String user) {//定义比较用户名是否与数据库中有相同的方法
        String fin = "select username from sys.accounts_table ;";//设置查询语句
        try (Connection conn = DriverManager.getConnection(url)) {//连接数据库
//            PreparedStatement ps= conn.prepareStatement(fin);
//            ps.setString(1,phone);
            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery(fin);//执行查询操作
            while (result.next()) {
                String tempUser = result.getString("username");//获取用户名
                if (tempUser.equals(user)) {//判断用户名与输入的是否相同
                    //如果相同返回true
                    return true;
                }

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        //当执行完上述语句，仍没有相同的用户名则返回false
        return false;
    }

    public static boolean ContainInfo(String idcard, String phone) {//定义一个比较身份证与手机号码是否与数据库中有相同的方向
        String fin = "select idcard ,phone from sys.accounts_table ;";//sql查询身份证和手机号码的语句
        try (Connection conn = DriverManager.getConnection(url)) {//尝试连接数据库
            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery(fin);//执行sql查询语句
            while (result.next()) {//当数据库中有数据时
                String TempID = result.getString("idcard");//获取身份证号码
                String TempPhone = result.getString("phone");//获取手机号码
                if (TempID.equals(idcard) && TempPhone.equals(phone)) {//与数据库中的手机号和身份证对比
                    return true;//如果正确返回true
                }

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;//如果执行完上述语句仍然没有符合则返回false
    }

    public static void UpdatePwd(String pwd, String user) {//通过用用户名修改数据库中密码的方法
        String update = "update sys.accounts_table  set password=? where username=?;";//修改数据库中密码sql语句
        try (Connection conn = DriverManager.getConnection(url)) {//尝试连接数据库
            PreparedStatement ps = conn.prepareStatement(update);
            ps.setString(1, pwd);//获取密码
            ps.setString(2, user);//获取用户名
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
//验证登录信息代码
    public static boolean ContainLogin(String user, String pwd) {//验证数据库中账号密码登录的方法
        String fin = "select username ,password from sys.accounts_table ;";//sql查询语句
        try (Connection conn = DriverManager.getConnection(url)) {//连接数据库
            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery(fin);//执行查询语句
            while (result.next()) {
                String TempUser = result.getString("username");//获取用户名
                String TemPwd = result.getString("password");//获取密码
                if (TempUser.equals(user) && TemPwd.equals(pwd)) {//只有当用户名密码与输入的信息相符时返回true
                    return true;
                }


            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        //如果上述操作执行完成没有符合的则返回false
        return false;
    }

    public static void ReturnALLAccounts(ArrayList<User> list) {//定义一个根据数据库中得来的用户信息添加到集合中去的方法
        String fin = "select * from sys.accounts_table ;";//sql查询用户数据语句
        try (Connection conn = DriverManager.getConnection(url)) {//尝试连接数据库
            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery(fin);//执行查询操作
            while (result.next()) {
                String TempUser = result.getString("username");//获取用户名
                String TemPwd = result.getString("password");//获取密码
                String TemID = result.getString("idcard");//获取身份证号码
                String TemPhone = result.getString("phone");//获取手机号码
                list.add(new User(TempUser, TemPwd, TemID, TemPhone));//将用户信息存储到集合中去
//                System.out.println("预制添加成功");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    public static void ReturnALLStudents(ArrayList<Student> list1) {//定义一个根据数据库中得来的学生信息添加到集合中去的方法
        String fin = "select * from sys.student_info ;";//定义sql查询语句
        try (Connection conn = DriverManager.getConnection(url)) {//尝试连接数据库
            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery(fin);//执行sql查询语句
            while (result.next()) {
                String TempID = result.getString("id");//获取学生学号
                String TemName = result.getString("name");//获取学生姓名
                String TemGender = result.getString("gender");//获取学生性别
                int TemAge = result.getInt("age");//获取学生年龄
                String TemAdd = result.getString("address");//获取学生家庭地址
                list1.add(new Student(TempID,TemName,TemGender,TemAge,TemAdd));//将学生信息添加到集合中去
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}
