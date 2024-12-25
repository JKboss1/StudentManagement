package UserLoginSystem;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import Project.StudentCtrlSystem.Student;
import Project.StudentCtrlSystem.StudentsTest;

import java.sql.*;

import static JdbcConnect.jdbcConnection.insertAccount;
import static JdbcConnect.newJDBCConnextion.*;


//import static Project.StudentCtrlSystem.StudentsTest.StartStudntSystem;


public class UserTest {
    //快捷键CTRL+SHIFT+U 转换成大写或小写
    private static final String LOGIN = "1";
    private static final String REGISTER = "2";
    private static final String EXIT = "3";
    private static final String FORGET = "4";
    private static final String TEST = "5";

    //静态代码块的应用 ：初始化数据对象
    static ArrayList<User> list = new ArrayList<>();


    static {
        ReturnALLAccounts(list);


//        list.add(new User("1", "1", "1", "1"));
//
//        for (int i = 0; i < list.size(); i++) {
//            User user = list.get(i);
//            System.out.println("管理员" + (i + 1) + "账号：");
//            System.out.println("用户名:" + user.getUsername() + "\t密码:" + user.getPassword() + "\t身份证号码:" + user.getId() + "\t手机号:" + user.getPhone());
//
//        }


    }

    public static void main(String[] args) {
//1.系统主界面


        while (true) {
            System.out.println("——————————————欢迎来到个人信息管理系统——————————————");
            System.out.println("——————————————1、登录————————————————————————————");
            System.out.println("——————————————2、注册————————————————————————————");
            System.out.println("——————————————3、退出————————————————————————————");
            System.out.println("——————————————4、忘记密码—————————————————————————");
            System.out.println("——————————————5、测试界面—————————————————————————");
            System.out.print("请选择您的操作:");
            Scanner sc = new Scanner(System.in);
            String input = sc.next();
            switch (input) {
                case LOGIN:
                    System.out.println("这是登录界面");
                    Login(list);
                    break;
                case REGISTER:
                    System.out.println("这是注册界面");
                    Register(list);
                    break;
                case EXIT:
                    System.out.println("这是退出系统");
                    System.out.println("系统已经成功退出");
                    System.exit(0);
                    break;
                case FORGET:
                    System.out.println("这是忘记密码界面");
                    Forget(list);
                    break;
                case TEST:
                    System.out.println("这是测试界面");
//                    CertificationCode();
//                    System.out.println(CertificationCode());
                    break;
                default:
                    System.out.println("您的输入有误，请重新输入！");
            }
        }
    }

    //将账号信息录入数据库


//    public static void Write_JDBC(String U_name,String pwd,String id_card,String my_phone){
//        try {
//            Class.forName("com.mysql.cj.jdbc.Driver");
//            System.out.println("驱动加载成功 ！");
//        } catch (Exception e) {
//            System.out.println("错误信息：" + e.getMessage() + "驱动加载失败！");
//
//        }
////        String url="jdbc:mysql://localhost:3306/students?user=root&pasword=123456&useSSL=false&serverTimezone=UTC";
//        String url = "jdbc:mysql://localhost:3306/MySql?user=root&password=666666&" +
//                "useSSL=false&serverTimezone=UTC&characterEncoding=utf8&useUnicode=true";
//
//
//        String userName = "root";
//        String password = "666666";
//        try {
//
//
//            java.sql.Connection connection = DriverManager.getConnection(url, userName, password);
//            if (connection != null) {
//                System.out.println("数据库连接成功 ！");
//            } else {
//                System.out.println("数据库连接失败！");
//            }
//            //增加操作
//
//            String insert = "insert into sys.accounts_table values (U_name,pwd,id_card,my_phone);";
//            PreparedStatement ps = connection.prepareStatement(insert);//参数将sql语句传入
//            //3.通过ps发送sql语句,同时获取返回值，影响的记录数
//        } catch (Exception e) {
//            System.out.println("数据库信息获取失败！" + e.getMessage());
//        }
//    }

    //2.用户登录
    public static void Login(ArrayList<User> list) {
        Scanner sc = new Scanner(System.in);
        int i = 0;
        while ((i++) != 3) {
            System.out.println("请输入用户名:");
            String username = sc.next();
            if (Compare(list, username) >= 0||ContainUser(username)) {
                System.out.println("请输入密码:");
                String password = sc.next();
                loop:
                while (true) {
                    System.out.println("请输入验证码：");
                    String rightCode = CertificationCode();
                    System.out.println("验证码为：" + rightCode);
                    String code = sc.next();
                    if (code.equalsIgnoreCase(rightCode)) {
                        System.out.println("验证码输入正确！");
                        break;
                    } else {
                        System.out.println("验证码输入错误！请重新输入：");
                        continue loop;
                    }

                }
                boolean b = returnIndex(list, username, password);
                if (b||ContainLogin(username,password)) {
                    System.out.println("恭喜您登录成功！");
                    StudentsTest happy = new StudentsTest();
                    happy.StartStudntSystem();
                    System.out.println();
                    break;
                } else {
                    if (i < 3) {
                        System.out.println("您的密码输入有误！登录失败！您还剩" + (3 - i) + "次机会！");
                        System.out.println("请重新登录:");
                    }
                    if (i == 3) {
                        System.out.println("登录最大次数已经用完，账号已经被锁定。请联系工作人员！");
                    }
                }


            } else {
                System.out.println("用户名不存在！");
                break;
            }
        }


    }

    //3.用户注册
    public static void Register(ArrayList<User> list) {
        loop:
        while (true) {
            Scanner sc = new Scanner(System.in);
            System.out.println("欢迎来到注册页面！");
            String phone;
            String idcar;
            String username;
            String password;
            while (true) {
                System.out.println("请输入手机号码：");
                phone = sc.next();
                boolean flag = ContainPhone(phone);
                if (!flag) {
                    System.out.println("对不起，您输入的手机号码不符合规范！");
                    System.out.println("请重新输入：");
                    continue;
                } else {
                    System.out.println("手机号格式输入正确！");
                    break;
                }
            }

            while (true) {
                System.out.println("请输入身份证号码：");
                idcar = sc.next();
                if (!ContainIdCar(idcar)) {
                    System.out.println("对不不起您输入的身份证号码不符合规范！");
                    System.out.println("请重新输入：");
                    continue;
                } else {
                    System.out.println("身份证号码格式输入正确");
                    break;
                }
            }
            while (true) {
                System.out.println("请输入用户名；");
                username = sc.next();
                if (Compare(list, username) >= 0) {
                    System.out.println("对不起！当前用户名已经存在！");
                    System.out.println("请重新输入：");
                    continue;
                } else if (!ContainUserName(username)) {
                    System.out.println("对不起，您输入的用户名不符合规范！");
                    System.out.println("请重新输入：");
                    continue;
                } else {
                    break;
                }
            }
            while (true) {
                System.out.println("请输入密码：");
                password = sc.next();
                System.out.println("请再次输入密码：");
                String containpass = sc.next();
                if ((password.equals(containpass))) {
                    System.out.println("两次密码输入相同， 验证成功！");
                    break;

                } else {
                    System.out.println("两次密码输入不正确！请重新输入：");
                    continue;
                }
            }
            User user = new User(username, password, idcar, phone);
            if (user.getUsername() != null && user.getPassword() != null && user.getId() != null && user.getPhone() != null) {
                list.add(user);

                insertAccount(username, password, idcar, phone);
                System.out.println("注册成功");
                break loop;
            }

        }

    }

    //4.忘记密码
    public static void Forget(ArrayList<User> list) {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入用户名:");
        String userName = sc.next();
        if (Compare(list, userName) < 0&&!ContainUser(userName)) {
            System.out.println("您当前输入的用户名不存在！");
            return;
        }
        System.out.println("请输入手机号码：");
        String phone = sc.next();
        System.out.println("请输入身份证号码：");
        String idCar = sc.next();
        if (PhoneAndIdCar(list, userName, phone, idCar)&&ContainInfo(idCar,phone)) {
            System.out.println("账号信息不匹配，找回密码失败！");
        } else {


            System.out.println("请输入新的密码：");
            String newPassword = sc.next();
            System.out.println("请再次输入密码：");
            String containPassword = sc.next();
            if (newPassword.equals(containPassword)) {
//                User user = new User(userName, newPassword, idCar, phone);
//                list.set(Compare(list, userName), user);
//                UpdatePwd(containPassword,userName);
                UpdatePwd(containPassword,userName);
                System.out.println("找回密码成功！");
                System.out.println("当前的密码为：" +containPassword);

            } else {
                System.out.println("对不起！两次密码输入不相同！");
            }
        }

    }

    //判断用户是否存在
    public static int Compare(ArrayList<User> list, String putId) {
        for (int i = 0; i < list.size(); i++) {//遍历集合
            User user = list.get(i);//获得索引
            String id = user.getUsername();//通过索引获得用户ID
            if (id.equals(putId)) {//通过获得的ID与传入的ID相比较
                return i;//如果相同返回i的值
            }
        }
        return -1;//当遍历完集合仍未有相同的ID则返回-1
    }

    //判断身份证是否合规的方法
    public static boolean ContainIdCar(String id) {
        if (id.length() == 18) {//判断身份证号码的长度是否符合18位
            if (id.substring(0, 1).charAt(0) + 1 == 49) {//判断身份证的开头第一位是否为零
                return false;//如果为零返回false
            }
            String end = id.substring(17);//将身份证的最后以为提取出来
            int x = end.charAt(0) + 1;//将组后以为转换成为ASCll吗
            if (!(49 <= x && x <= 58) && x != 89 && x != 121) {//判断身份证的最后一位是否为0-9之间或这是否为X或者x
                return false;

            }
            String start = id.substring(1, 17);//提取出身份证的第2到17位
            for (int i = 0; i < start.length(); i++) {//遍历判断2-17位是否为数字
                char c = start.charAt(i);
                int a = c + 1;
                if (a < 49 || a > 58) {
                    return false;//如果符合返回false
                }
            }

        } else {
            return false;
        }
        return true;

    }

    //判断注册的用户名是否符合规范
    public static boolean ContainUserName(String username) {
        if (username.length() < 3 || username.length() > 15) {//判断输入的用户名长度是否在6-15之间
            return false;
        }
        int count = 0;//定义一个count值记录数字的个数
        for (int i = 0; i < username.length(); i++) {//遍历用户名的字符串长度取出每个字符的值
            char c = username.charAt(i);

            if (c >= '0' && c <= '9') {//当有一个字符是数字时候count值加1
                count++;
            }
            boolean flag = (c >= 'A' && c <= 'Z') || (c >= 'a' && c <= 'z') || (c >= '0' && c <= '9');//定义一个值判断某个值是否在范围之内

            if (!flag) {
                return false;
            }
        }
        if (count == username.length()) {//当count值等于字符串的长度时候名字不符合规范返回false
            return false;
        }

        return true;//当全部遍历完没有错误返回true

    }

    //判断输入的手机号码是否符合规范的方法
    public static boolean ContainPhone(String phone) {
        if (phone.length() != 11) {//判断手机号码是否为11为
            return false;//如果不是11位返回false
        } else if (phone.substring(0).charAt(0) + 1 == 49) {//判断手机号码的第一位是否位0
            return false;//如果为0返回false
        }
        for (int i = 0; i < phone.length(); i++) {//遍历判断手机号码的后续位数是否都为数字
            if ((phone.charAt(i) + 1) > 58 || (phone.charAt(i) + 1) < 49) {
                return false;//如果某位不是数字返回false
            }
        }
        return true;//当所有11位都循环完则返回true
    }

    //定义一个索引方法判断用户名和密码是否与集合中的某个数据相同
    public static boolean returnIndex(ArrayList<User> list, String username, String password) {
        for (int i = 0; i < list.size(); i++) {//遍历集合
            User user = list.get(i);//通过索引获得用户对象
            String username1 = user.getUsername();////通过用户对象获取对象内的用户名
            String password1 = user.getPassword();//通过用户对象获取对象内的密码
            if (username.equals(username1) && password.equals(password1)) {//判断用户名和密码是否与集合中的某个数据相同
                return true;//如果相同返回true
            }
        }
        return false;//当遍历完集合仍未有相同的则返回false

    }

    //定义一个方法判断当前用户的手机号和身份证是否一致
    private static boolean PhoneAndIdCar(ArrayList<User> list, String username, String phone, String idCar) {
        //遍历当前集合
        for (int i = 0; i < list.size(); i++) {
            User user = list.get(i);//通过i获取用户集合中的
            String username1 = user.getUsername(); //通过索引获得集合中的用户名
            if (username1.equals(username)) {//判断用户名是否与传入的用户名一致，如果一致就进行手机号和身份证的匹配
                String phone1 = user.getPhone();//获得用户的手机号
                String idCar1 = user.getId();   //获得用户的身份证号码
                if (phone.equals(phone1) && idCar1.equalsIgnoreCase(idCar)) {//判断手机号和用户身份证是否和当前用户的手机号和身份证号码一致
                    return true;//如果一致返回true
                }
            }
        }
        return false;//当集合遍历完仍未找到一致的则返回false
    }

    //获得验证码方法
    public static String CertificationCode() {
        //1、创建一个集合添加所有的大小写字母
        ArrayList<Character> list = new ArrayList<>();
        Random random = new Random();//随机方法

        //2、将所有的大小写字母全部添加到集合当中去
        for (int i = 0; i < 26; i++) {
            list.add((char) ('a' + i));//将小写字母添加到集合当中并将其强制转换成char字符类型
            list.add((char) ('A' + i));//将大写字母添加到集合当中并将其强制转换成char字符类型
        }
//        System.out.println(list);
        StringBuilder sb = new StringBuilder();
        //3、要随机抽取4个字符
        for (int i = 0; i < 4; i++) {
            int index = random.nextInt(list.size());//获取随机索引
            char ch = list.get(index);//利用随机索引获取字符
            sb.append(ch);//将随机字符添加到sb当中
        }
//        System.out.println(sb);
        //3、把一个随机数字添加到末尾
        int number = random.nextInt(10);
        sb.append(number);
//        System.out.println(sb);
        //把字符串编程字符数组，在数组中修改，然后再创建一个新的字符串
        char[] chars = sb.toString().toCharArray();
        //拿最后一个数字索引跟随机索引进行交换
        int randomIndex = random.nextInt(chars.length);
        char temp = chars[randomIndex];// 用临时变量存储随机索引获得的值
        chars[randomIndex] = chars[chars.length - 1];//将后一个值赋值给随机索引获得的值
        chars[chars.length - 1] = temp;//把临时变量存储的值再赋值给后一个值
//        System.out.println(chars);

        return new String(chars);//返回一个新创建的值数组
    }
}

