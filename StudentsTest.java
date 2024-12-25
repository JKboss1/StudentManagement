package Project.StudentCtrlSystem;

import JdbcConnect.jdbcConnection;
import UserLoginSystem.UserTest;


import java.util.ArrayList;
import java.util.Scanner;

import static JdbcConnect.jdbcConnection.*;
import static JdbcConnect.newJDBCConnextion.ReturnALLStudents;

public class StudentsTest {
    /* 实际开发中，常量一般作为系统的配置信息，方便维护，提高可读性。
     常量的命名规范:
             ·单个单词:全部大写
     多个单词:全部大写，单词之间用下划线隔开*/
    // 常量的应用，提高代码的可读性 如下 final修饰相当于是常量不可变
    private static final String FIND_MENU = "1";
    private static final String ADD_INFO = "2";
    private static final String MODIFY_INFO = "3";
    private static final String REMOVE_INFO = "4";
    private static final String EXIT = "5";
    static ArrayList<Student> list = new ArrayList<>();

    static {
        ReturnALLStudents(list);
    }

    public static void StartStudntSystem() {
//        ArrayList<Student> list = new ArrayList<>();
        /*loop:*/
        while (true) {//loop 指定循环的名字
            Scanner sc = new Scanner(System.in);
            System.out.println("----------------欢迎来到DIY学生信息管理系统--------------");
            System.out.println("------------------请输入您的选择-----------------------");
            System.out.println("----------------1，查询学生信息------------------------");
            System.out.println("----------------2，添加学生信息------------------------");
            System.out.println("----------------3，修改学生信息------------------------");
            System.out.println("----------------4，删除学生信息------------------------");
            System.out.println("----------------5，退出管理系统------------------------");
            System.out.print("请输入您的选择：");
            String input = sc.next();
            switch (input) {
                case FIND_MENU:
                    System.out.println("这是查询学生信息按键");
                    findMenu(list);
                    break;
                case ADD_INFO:
                    System.out.println("这是添加学生信息按键");
                    addInfo(list);
                    break;
                case MODIFY_INFO:
                    System.out.println("这是修改学生信息按键");
                    modifyInfo(list);
                    break;
                case REMOVE_INFO:
                    System.out.println("这是删除学生信息按键");
                    removeInfo(list);
                    break;
                case EXIT:
                    System.out.println("这是退出系统按键");
                    System.out.println("系统已退出，感谢您的使用 ！");
                    new UserTest();
//                    System.exit(0);//退出Java虚拟机
//                    break;//loop;//退出指定的循环
                    return;
                default:
                    System.out.println("您输入的选择有误，请重新输入");
            }
        }
    }

    //查询学生的学号是否已经存在
    public static boolean Contain(ArrayList<Student> list, String id) {
        for (int i = 0; i < list.size(); i++) {//遍历整个集合
            Student index = list.get(i);//获得学生对象索引
            String contain = index.getId();//通过对象获得
            if (contain.equals(id)) {//查看是否有相同的学号
                return true;
            }

        }
        return false;
    }

    //查询学生信息的菜单方法
    public static void findMenu(ArrayList<Student> list) {

        loop:
        while (true) {
            System.out.println();
            System.out.println("学生信息查询菜单");
            System.out.println("1.查询单个学生信息");
            System.out.println("2.查询所有学生的信息");
            System.out.println("3.退出查询菜单");
            System.out.print("请输入您的查询方式：");
            Scanner sc = new Scanner(System.in);
            String chose = sc.next();
            switch (chose) {
                case "1":
//                    printInfo(list, checkInfo(list));// 查询单个学生信息
                    find_PersonStu();
                    break;
                case "2":
                    findInfoAll(list);//查询所有学生信息
                    break;
                case "3":
                    System.out.println("查询菜单已经成功退出；");
                    System.out.println();
                    break loop;
                default:
                    System.out.println("无效选择，请重新选择!");
            }
        }

    }

    //查询所有学生信息的方法
    public static void findInfoAll(ArrayList<Student> list) {
        int EnterNumber = 0;
        while (EnterNumber != 3) {
            EnterNumber++;

            System.out.print("请输入查询密码，(默认是6):");
            int password = 6;
            Scanner sc = new Scanner(System.in);
            int pwd = sc.nextInt();
            if (pwd == password) {
                jdbcConnection.fin_all();
                if (list.size() != 0) {

                    System.out.println("学号\t姓名\t性别\t年龄\t家庭住址");
                    for (int i = 0; i < list.size(); i++) {
                        Student index = list.get(i);
                        System.out.println(index.getId() + "\t" + index.getName() + "\t" + index.getGender() + "\t" +
                                index.getAge() + "\t" + index.getAdd());
                    }
                    break;
                } else {
                    System.out.println("改系统中暂时存在没有学生信息，等待录入学生信息！");
                }
            } else if (EnterNumber == 3) {
                System.out.println("您输入错误的次数超过三次，已强制退出！");


            } else
                System.out.println("您输入的密码不正确!");

        }

    }


    //  查询单个学生信息的方法
    public static void find_PersonStu(){
        System.out.println("请输入您需要查询的学生的学号（ID）:");
        Scanner sc = new Scanner(System.in);
        String input = sc.next();
                find_Personal(input);
        printInfo(list, checkInfo(list,input),input);

    }
    public static int checkInfo(ArrayList<Student> list,String input) {
        //定义一个返回值
//        System.out.println("请输入您需要查询的学生的学号（ID）:");
//        Scanner sc = new Scanner(System.in);
//        String input = sc.next();
//        find_Personal(input);
        for (int i = 0; i < list.size(); i++) {
            Student index = list.get(i);
            String id = index.getId();
            boolean equals = list.get(i).getId().equals(input);
            if (equals) {//id.equals(input)
                return i;
            }
        }

        return -1;
    }

    //查询学生信息的出来的索引遍历的方法
    public static void printInfo(ArrayList<Student> list, int j,String input) {

//        while (true) {
            if (j >= 0) {
//                find_Personal();
//                for (int i = 0; i < list.size(); i++) {
                    Student student = list.get(checkInfo(list,input));
                    System.out.println(
                            "学号:" + student.getId() +
                                    "\t姓名:" + student.getName() +
                                    "\t性别" + student.getGender() +
                                    "\t年龄:" + student.getAge() +
                                    "\t家庭地址:" + student.getAdd());
//                }
//                b
//                reak;

            } else {
                System.out.println("您输入的的学生学号不正确或者学生不存在！");
            }
//            break;
//        }
    }


    //添加学生信息的方法
    public static void addInfo(ArrayList<Student> list) {
        Scanner sc = new Scanner(System.in);
        System.out.print("请输入您需要添加的学生的个数:");
        int number = sc.nextInt();
        for (int i = 0; i < number; i++) {
//            Student stu=new Student();
            System.out.print("请输入您要添加的第" + (i + 1) + "个学生的学号（ID）:");
            String id = sc.next();
            if (Contain(list, id)) {//判断输入的学生学号是否已经存在
                System.out.println("当前添加的学生学号已经存在！您不能添加相同学号！");

            } else {

                System.out.print("请输入您要添加的第" + (i + 1) + "个学生的姓名：");
                String name = sc.next();
                System.out.print("请输入您要添加的第" + (i + 1) + "个学生的性别（男或女）：");
                String gender = sc.next();
                if (gender.equals("男") || gender.equals("女")) {//判断输入的性别是否正确
                    System.out.print("请输入您要添加的第" + (i + 1) + "个学生的年龄（不能大于30岁）:");
                    int age = sc.nextInt();
                    if (age >= 30) {//判断输入的学生年龄是否在范围之内
                        System.out.println("您输入的学生年龄有误！学生年龄不能大于30岁");

                    } else {
                        System.out.print("请输入您要添加的第" + (i + 1) + "个学生的家庭住址:");
                        String add = sc.next();
                        Add_Stu_Info(id, name, gender, age, add);
                        Student stu = new Student(id, name, gender, age, add);//将上述输入的学生学号、姓名、性别、年龄、家庭地址添加到对象中
                        list.add(stu);//将学生对象添加到集合当中去
                        System.out.println("改学生信息已经成功添加！");
                    }
                } else {
                    System.out.println("您输入的性别有误！");
                }
            }


        }
    }

    //删除学生信息的方法
    public static void removeInfo(ArrayList<Student> list) {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入您要删除的学生学号（ID）");
        String put = sc.next();//定义输入
        Delete_stu_info(put);
        if (Contain(list, put)) {//判断要删除的学号是否存在
            for (int i = 0; i < list.size(); i++) {
                Student index = list.get(i);//获得学生类的索引
                String id = index.getId();//根据索引获得学生的学号
                if (id.equals(put)) {//根据索引获得的学号是否与要要删除的学号是否相等
                    list.remove(i);
                    System.out.println("改学生信息已经成功删除！");
                }
            }
        } else {
            System.out.println("该学生学号不存在或已经删除！");
            System.out.println();
        }
    }

    //修改学生信息的方法
    public static void modifyInfo(ArrayList<Student> list) {
        System.out.println("请输入要修改的学生的学号（ID）：");
        Scanner sc = new Scanner(System.in);
        String input = sc.next();
        if (Contain(list, input)) {
            for (int i = 0; i < list.size(); i++) {
                Student index = list.get(i);
                String id = index.getId();
                if (id.equals(input)) {
                    System.out.println("请输入您要修改的学生的姓名：");
                    String new_name = sc.next();
                    System.out.println("请输入您要修改的学生的性别：");
                    String new_gender = sc.next();
                    System.out.println("请输入您要修改的学生的年龄：");
                    int newAge = sc.nextInt();
                    System.out.println("请输入您要修改的学生的家庭住址：");
                    String new_add = sc.next();
                    Student new_info = new Student(id, new_name, new_gender, newAge, new_add);
                    Update_stu_info(id, new_name, new_gender, newAge, new_add);
                    list.set(i, new_info);
                    System.out.println("该学生信息修改成功！");
                    break;

                }
            }

        } else {
            System.out.println("您输入的学号不存在！");
        }

    }
}
