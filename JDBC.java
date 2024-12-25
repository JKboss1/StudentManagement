package JdbcConnect;
import java.sql.*;
public class JDBC {


            public static void main(String[] args) throws ClassNotFoundException, SQLException {
                /**
                 * 第一步：连接Mysql数据库，只需要三行代码，实际上两行就行
                 * 1.Class.forName("com.mysql.cj.jdbc.Driver");
                 *   作用：加载Mysql驱动类在内存中，这行代码在连接Mysql数据库的时候可以不加，如果连接
                 *        其他数据库需要加，因为Mysql特有的可以自动导入驱动类
                 * 2.String url = "jdbc:mysql://localhost:3306/mydb?serverTimezone=UTC";
                 *   说明：
                 *      1）连接Mysql数据库你需要提供以下信息：
                 *         a.提供mysql服务端所在的主机ip地址或者主机名
                 *         b.提供Mysql服务端的端口号
                 *         c.提供Mysql服务端的用户名
                 *         d.提供Mysql服务端的密码
                 *         e.提供你需要操作的数据库名称
                 *       2）对url字段串解释：
                 *         jdbc:是主协议、mysql:是子协议
                 *         localhost表示连接本机的mysql数据库、3306是端口号、mydb是Mysql里的一个数据库名称
                 *         ?serverTimezone=UTC 表示设置时区为标准国际时区，不设置会出异常导致连接补上，这一行照抄就行
                 * 3.Connection con = DriverManager.getConnection(url,"root","password");
                 *   说明：
                 *       1）DriverManager 是驱动管理类，是java.sql包下的类
                 *       2）getConnection 是获取一个数据库连接的方法
                 *          url: 第一个参数，跟着写就行
                 *          "root"：Mysql数据库的用户名，用你自己的
                 *          "password"：Mysql数据库密码，用你自己的
                 *           返回一个连接对象
                 * 注意：使用以上类和方法需要导入java.sql包，因为JDBC技术的接口包都在其中，以下方法都有异常抛出，
                 *      我们直接给扔给main方法就行。
                 */
                Class.forName("com.mysql.cj.jdbc.Driver");
                String url = "jdbc:mysql://localhost:3306/MySql?serverTimezone=UTC";
                Connection con = DriverManager.getConnection(url,"root","666666");
                //程序如果走到这里，你的Mysql数据库就链接成功了，如果连接失败，你就回头从新检查参数url或者用户名密码是否设置正确
                System.out.println("数据库连接成功！");

                /**
                 * 第二步：实现增加记录的操作
                 * 步骤为：
                 * 1.编写增加记录的sql语句
                 * 2.通过con将sql语句传入一个PreparedStatement对象,同时将这个对象返回
                 * 3.通过返回的PreparedStatement对象的executeUpdate（）方法发送sql语句，同时数据库会返回一个结果，这个结果是影响的记录数
                 * 4.如果不继续使用con连接了和PreparedStatement对象了就释放资源
                 * 注意：除了查询，其他方法一律都使用executeUpdate（）
                 */
                //1.编写sql语句：sql字段串里面是具体的sql语句，这里我就不多说了
                String sql ="insert into item values(1.1,2.2)";
                //2.获取PreparedStatement对象
                PreparedStatement ps = con.prepareStatement(sql);//参数将sql语句传入
                //3.通过ps发送sql语句,同时获取返回值，影响的记录数
                int len = ps.executeUpdate();
                //4.如果你不继续操作了就关闭，都是close()方法，我还要继续删除、修改、查询操作就不关闭了

                /**
                 * 第三步：实现删除记录的操作（基本上面类似，只是sql语句不一样而已）
                 * 步骤为：
                 * 1.编写增加记录的sql语句
                 * 2.通过con将sql语句传入一个PreparedStatement对象,同时将这个对象返回
                 * 3.通过返回的PreparedStatement对象的executeUpdate（）方法发送sql语句，同时数据库会返回一个结果，这个结果是影响的记录数
                 * 4.如果不继续使用con连接了和PreparedStatement对象了就释放资源
                 * 注意：除了查询，其他方法一律都使用executeUpdate（）
                 */
                //1.编写sql语句：sql字段串里面是具体的sql语句，这里我就不多说了
                sql ="detele";
                //2.获取PreparedStatement对象
                ps = con.prepareStatement(sql);//参数将sql语句传入
                //3.通过ps发送sql语句,同时获取返回值，影响的记录数
                len = ps.executeUpdate();
                //4.如果你不继续操作了就关闭，都是close()方法，我还要继续删除、修改、查询操作就不关闭了

                /**
                 * 第四步：实现修改记录的操作（基本上面类似，只是sql语句不一样而已）
                 * 步骤为：
                 * 1.编写增加记录的sql语句
                 * 2.通过con将sql语句传入一个PreparedStatement对象,同时将这个对象返回
                 * 3.通过返回的PreparedStatement对象的executeUpdate（）方法发送sql语句，同时数据库会返回一个结果，这个结果是影响的记录数
                 * 4.如果不继续使用con连接了和PreparedStatement对象了就释放资源
                 * 注意：除了查询，其他方法一律都使用executeUpdate（）
                 */
                //1.编写sql语句：sql字段串里面是具体的sql语句，这里我就不多说了
                sql ="这里面是修改的sql语句";
                //2.获取PreparedStatement对象
                ps = con.prepareStatement(sql);//参数将sql语句传入
                //3.通过ps发送sql语句,同时获取返回值，影响的记录数
                len = ps.executeUpdate();
                //4.如果你不继续操作了就关闭，都是close()方法，我还要继续删除、修改、查询操作就不关闭了

                /**
                 * 第五步：实现查询记录的操作（和上面大不相同，详细看）
                 * 步骤为：
                 * 1.编写增加记录的sql语句
                 * 2.通过con将sql语句传入一个PreparedStatement对象,同时将这个对象返回
                 * 3.通过返回的PreparedStatement对象的***executeQuery（）方法***发送sql语句
                 *    注意：
                 *        1）查询语句都是使用executeQuery（）方法
                 *        2）数据库返回来的是一个ResultSet对象，里边存放了你想要查询的数据
                 * 4.遍历返回来的ResultSet对象，打印数据，具体规则看代码注释
                 * 5.如果不继续使用con连接了和PreparedStatement对象了就释放资源
                 * 注意：除了查询，其他方法一律都使用executeUpdate（）
                 */
                //1.编写sql语句：sql字段串里面是具体的sql语句，这里我就不多说了
                sql ="这里面是查询的sql语句";
                //2.获取PreparedStatement对象
                ps = con.prepareStatement(sql);//参数将sql语句传入
                //3.通过ps发送sql语句,同时获取返回值，是一个数据的集合
                ResultSet set = ps.executeQuery();
                //4.遍历该集合
                //set.next() 该方法是判断是否还有下一行数据
                while(set.next()) {//一次读取一行数据
                    //注意：这里的Object都可以替换为具体的数据类型，我为了演示才用的Object
                    Object value1 = set.getObject(1);//数据中表的字段值序号，第1列
                    Object value2 = set.getObject(2);//数据中表的字段值序号，第2列
                    Object value3 = set.getObject(3);//数据中表的字段值序号，第3列
                    //将获取到的一行的不同字段值依次打印出来看看
                    System.out.println(value1 + "\t" + value2 + "\t" + value3);
                }
                //4.如果你不继续操作了就关闭，都是close()方法，我还要继续删除、修改、查询操作就不关闭了
                con.close();//关闭连接
                ps.close();//关闭PreparedStatement对象
            }
        }



