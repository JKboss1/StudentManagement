package JdbcConnect;// This is a sample code to connect to MySQL database using JDBC driver.

import java.sql.*;

public class jdbcConnection {

    public static void fin_all() {

//        try {
//            Class.forName("com.mysql.cj.jdbc.Driver");
//            System.out.println("驱动加载成功 ！");
//        } catch (Exception e) {
//            System.out.println("错误信息：" + e.getMessage() + "驱动加载失败！");
//
//        }
//        String url="jdbc:mysql://localhost:3306/students?user=root&pasword=123456&useSSL=false&serverTimezone=UTC";
        String url = "jdbc:mysql://localhost:3306/MySql?user=root&password=666666&" +
                "useSSL=false&serverTimezone=UTC&characterEncoding=utf8&useUnicode=true";


        String userName = "root";
        String password = "666666";
        try {


            java.sql.Connection connection = DriverManager.getConnection(url, userName, password);
            if (connection != null) {
                System.out.println("数据库连接成功 ！");
            } else {
                System.out.println("数据库连接失败！");
            }
            //增加操作
//
//                String insert="insert into sys.students values ('2153','dzl','woman','2024-12-10','162');";
//                PreparedStatement ps = connection.prepareStatement(insert);//参数将sql语句传入
                //3.通过ps发送sql语句,同时获取返回值，影响的记录数

            //查询操作
            assert connection != null;
            Statement statement = connection.createStatement();
            String sql = "SELECT*FROM sys.student_info";

            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                System.out.println("---------------------------------------------------------------");
                System.out.println("| " + "学号:" + resultSet.getString("id") + "\t " + "姓名:" + resultSet.getString("name") + " \t"
                        + "性别:" + resultSet.getString("gender") + " \t"+"年龄:" + resultSet.getInt("age") + "\t" + "家庭住址:" + resultSet.getString("address") + " |");

            }
            System.out.println("_______________________________________________________________");
            connection.close();

        } catch (Exception e) {
            System.out.println("数据库信息获取失败！" + e.getMessage());
        }



    }
    public static void insertAccount(String username, String passwords, String idcard, String phone) {
        // SQL 插入语句，使用 ? 占位符
        String insertSQL = "INSERT INTO sys.accounts_table (username, password, idcard, phone) VALUES (?, ?, ?, ?)";
        String url = "jdbc:mysql://localhost:3306/MySql?user=root&password=666666&" +
                "useSSL=false&serverTimezone=UTC&characterEncoding=utf8&useUnicode=true";

        String userName = "root";
        String password = "666666";

        // 尝试-捕获块，确保资源正确关闭
        try (Connection conn = DriverManager.getConnection(url);
             PreparedStatement pstmt = conn.prepareStatement(insertSQL)) {

            // 设置参数
            pstmt.setString(1, username);  // 第一个占位符
            pstmt.setString(2, passwords);     // 第二个占位符
            pstmt.setString(3, idcard); // 第三个占位符
            pstmt.setString(4, phone);// 第四个占位符

            // 执行插入操作
            int rowsAffected = pstmt.executeUpdate();

            // 输出插入结果
            if (rowsAffected > 0) {
                System.out.println("数据插入成功！");
            } else {
                System.out.println("数据插入失败！");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("数据库操作失败：" + e.getMessage());
        }
    }
    public static void Add_Stu_Info(String id,String name,String gender,int age,String address){

        String sqlStuInfo="INSERT INTO sys.Student_info (id,name,gender,age,address) VALUE (?,?,?,?,?)";
        String url = "jdbc:mysql://localhost:3306/MySql?user=root&password=666666&" +
                "useSSL=false&serverTimezone=UTC&characterEncoding=utf8&useUnicode=true";
        String username="root";
        String password="666666";
        try(Connection conn= DriverManager.getConnection(url,username,password)){
            PreparedStatement pst=conn.prepareStatement(sqlStuInfo);
            pst.setString(1,id);
            pst.setString(2,name);
            pst.setString(3,gender);
            pst.setInt(4,age);
            pst.setString(5,address);
            int affected=pst.executeUpdate();
            if(affected>0){
                System.out.println("数据插入成功");

            }else{
                System.out.println("数据插入失败，请重新检查数据是否正确！");
            }

        }catch(SQLException e){
            e.printStackTrace();
            System.out.println("数据库操作失败："+e.getMessage());
        }

    }
    public static void find_Personal(String num){
        String person_info=null;
        String find_word="select * from sys.student_info where id=?";
        String url="jdbc:mysql://localhost:3306/Mysql?user=root&password=666666&useSSL" +
                "=false&serverTimezone=UTC&characterEncoding=utf8&useUnicode=true";
        String userName = "root";
        String password = "666666";
        try(Connection conn= DriverManager.getConnection(url,userName,password)){

            PreparedStatement ps=conn.prepareStatement(find_word);
            ps.setString(1,num);
            try(ResultSet rs=ps.executeQuery()){
                if(rs.next()){

                    person_info=rs.getString("id");

                    String name = rs.getString("name");
                    String gender = rs.getString("gender");
                    int age = rs.getInt("age");
                    String address = rs.getString("address");
                    System.out.println("---------------------------------------------------------------------------");
                    System.out.println("|  学号："+person_info+" |\t姓名:"+name+" |\t性别:"+gender+" |\t年龄:"+age+" |\t家庭地址:"+address+" |");
                    System.out.println("---------------------------------------------------------------------------");
                }
            }

        }catch (SQLException e){
          e.printStackTrace();
        }
    }
    public static void Update_stu_info(String num,String newname,String newgender,int newage ,String newaddress){
        String url="jdbc:mysql://localhost:3306/Mysql?user=root&password=666666&useSSL=false&serverTimezone=UTC&characterEncoding=utf8&useUnicode=true";
        String update="update sys.student_info  set name=? ,gender=? ,age=? ,address=? where id=?";
        try(Connection connection=DriverManager.getConnection(url)){
            PreparedStatement pst=connection.prepareStatement(update);
            pst.setString(1,newname);
            pst.setString(2,newgender);
            pst.setInt(3,newage);
            pst.setString(4,newaddress);
            pst.setString(5,num);
            int affected=pst.executeUpdate();
            if(affected>0){
                System.out.println("学号为"+num+"学生信息修改成功");

            }else{
                System.out.println("学生信息修改失败，请重新检查数据是否正确！");
            }


        }catch(SQLException e){
            e.printStackTrace();
        }

    }
    public static void Delete_stu_info(String delete_num){//定义一个删除学生信息得方法
        //数据库得URL地址
        String url ="jdbc:mysql://localhost:3306/Mysql?user=root&password=666666&useSSL=false&serverTimezone=UTC&characterEncoding=utf8&useUnicode=true";
        String delete_word="delete from sys.student_info where id=?";//删除操作得SQL语句
        try(Connection conn=DriverManager.getConnection(url)){//尝试连接数据库
            PreparedStatement pst=conn.prepareStatement(delete_word);//执行SQL删除操作
            pst.setString(1,delete_num);//得到id变量得值
            int affect=pst.executeUpdate();//执行SQL更新操作，即删除操作完成
            if(affect>0) {
                System.out.println("学生信息删除成功！");
            }else {
                System.out.println("学号不存在，删除失败！");
            }
        }catch(SQLException e){
            e.printStackTrace();

        }
    }




}


