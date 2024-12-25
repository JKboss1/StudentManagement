package JdbcConnect;
import java.sql.*;
public class JDBC {


            public static void main(String[] args) throws ClassNotFoundException, SQLException {
                /**
                 * ��һ��������Mysql���ݿ⣬ֻ��Ҫ���д��룬ʵ�������о���
                 * 1.Class.forName("com.mysql.cj.jdbc.Driver");
                 *   ���ã�����Mysql���������ڴ��У����д���������Mysql���ݿ��ʱ����Բ��ӣ��������
                 *        �������ݿ���Ҫ�ӣ���ΪMysql���еĿ����Զ�����������
                 * 2.String url = "jdbc:mysql://localhost:3306/mydb?serverTimezone=UTC";
                 *   ˵����
                 *      1������Mysql���ݿ�����Ҫ�ṩ������Ϣ��
                 *         a.�ṩmysql��������ڵ�����ip��ַ����������
                 *         b.�ṩMysql����˵Ķ˿ں�
                 *         c.�ṩMysql����˵��û���
                 *         d.�ṩMysql����˵�����
                 *         e.�ṩ����Ҫ���������ݿ�����
                 *       2����url�ֶδ����ͣ�
                 *         jdbc:����Э�顢mysql:����Э��
                 *         localhost��ʾ���ӱ�����mysql���ݿ⡢3306�Ƕ˿ںš�mydb��Mysql���һ�����ݿ�����
                 *         ?serverTimezone=UTC ��ʾ����ʱ��Ϊ��׼����ʱ���������û���쳣�������Ӳ��ϣ���һ���ճ�����
                 * 3.Connection con = DriverManager.getConnection(url,"root","password");
                 *   ˵����
                 *       1��DriverManager �����������࣬��java.sql���µ���
                 *       2��getConnection �ǻ�ȡһ�����ݿ����ӵķ���
                 *          url: ��һ������������д����
                 *          "root"��Mysql���ݿ���û����������Լ���
                 *          "password"��Mysql���ݿ����룬�����Լ���
                 *           ����һ�����Ӷ���
                 * ע�⣺ʹ��������ͷ�����Ҫ����java.sql������ΪJDBC�����Ľӿڰ��������У����·��������쳣�׳���
                 *      ����ֱ�Ӹ��Ӹ�main�������С�
                 */
                Class.forName("com.mysql.cj.jdbc.Driver");
                String url = "jdbc:mysql://localhost:3306/MySql?serverTimezone=UTC";
                Connection con = DriverManager.getConnection(url,"root","666666");
                //��������ߵ�������Mysql���ݿ�����ӳɹ��ˣ��������ʧ�ܣ���ͻ�ͷ���¼�����url�����û��������Ƿ�������ȷ
                System.out.println("���ݿ����ӳɹ���");

                /**
                 * �ڶ�����ʵ�����Ӽ�¼�Ĳ���
                 * ����Ϊ��
                 * 1.��д���Ӽ�¼��sql���
                 * 2.ͨ��con��sql��䴫��һ��PreparedStatement����,ͬʱ��������󷵻�
                 * 3.ͨ�����ص�PreparedStatement�����executeUpdate������������sql��䣬ͬʱ���ݿ�᷵��һ���������������Ӱ��ļ�¼��
                 * 4.���������ʹ��con�����˺�PreparedStatement�����˾��ͷ���Դ
                 * ע�⣺���˲�ѯ����������һ�ɶ�ʹ��executeUpdate����
                 */
                //1.��дsql��䣺sql�ֶδ������Ǿ����sql��䣬�����ҾͲ���˵��
                String sql ="insert into item values(1.1,2.2)";
                //2.��ȡPreparedStatement����
                PreparedStatement ps = con.prepareStatement(sql);//������sql��䴫��
                //3.ͨ��ps����sql���,ͬʱ��ȡ����ֵ��Ӱ��ļ�¼��
                int len = ps.executeUpdate();
                //4.����㲻���������˾͹رգ�����close()�������һ�Ҫ����ɾ�����޸ġ���ѯ�����Ͳ��ر���

                /**
                 * ��������ʵ��ɾ����¼�Ĳ����������������ƣ�ֻ��sql��䲻һ�����ѣ�
                 * ����Ϊ��
                 * 1.��д���Ӽ�¼��sql���
                 * 2.ͨ��con��sql��䴫��һ��PreparedStatement����,ͬʱ��������󷵻�
                 * 3.ͨ�����ص�PreparedStatement�����executeUpdate������������sql��䣬ͬʱ���ݿ�᷵��һ���������������Ӱ��ļ�¼��
                 * 4.���������ʹ��con�����˺�PreparedStatement�����˾��ͷ���Դ
                 * ע�⣺���˲�ѯ����������һ�ɶ�ʹ��executeUpdate����
                 */
                //1.��дsql��䣺sql�ֶδ������Ǿ����sql��䣬�����ҾͲ���˵��
                sql ="detele";
                //2.��ȡPreparedStatement����
                ps = con.prepareStatement(sql);//������sql��䴫��
                //3.ͨ��ps����sql���,ͬʱ��ȡ����ֵ��Ӱ��ļ�¼��
                len = ps.executeUpdate();
                //4.����㲻���������˾͹رգ�����close()�������һ�Ҫ����ɾ�����޸ġ���ѯ�����Ͳ��ر���

                /**
                 * ���Ĳ���ʵ���޸ļ�¼�Ĳ����������������ƣ�ֻ��sql��䲻һ�����ѣ�
                 * ����Ϊ��
                 * 1.��д���Ӽ�¼��sql���
                 * 2.ͨ��con��sql��䴫��һ��PreparedStatement����,ͬʱ��������󷵻�
                 * 3.ͨ�����ص�PreparedStatement�����executeUpdate������������sql��䣬ͬʱ���ݿ�᷵��һ���������������Ӱ��ļ�¼��
                 * 4.���������ʹ��con�����˺�PreparedStatement�����˾��ͷ���Դ
                 * ע�⣺���˲�ѯ����������һ�ɶ�ʹ��executeUpdate����
                 */
                //1.��дsql��䣺sql�ֶδ������Ǿ����sql��䣬�����ҾͲ���˵��
                sql ="���������޸ĵ�sql���";
                //2.��ȡPreparedStatement����
                ps = con.prepareStatement(sql);//������sql��䴫��
                //3.ͨ��ps����sql���,ͬʱ��ȡ����ֵ��Ӱ��ļ�¼��
                len = ps.executeUpdate();
                //4.����㲻���������˾͹رգ�����close()�������һ�Ҫ����ɾ�����޸ġ���ѯ�����Ͳ��ر���

                /**
                 * ���岽��ʵ�ֲ�ѯ��¼�Ĳ��������������ͬ����ϸ����
                 * ����Ϊ��
                 * 1.��д���Ӽ�¼��sql���
                 * 2.ͨ��con��sql��䴫��һ��PreparedStatement����,ͬʱ��������󷵻�
                 * 3.ͨ�����ص�PreparedStatement�����***executeQuery��������***����sql���
                 *    ע�⣺
                 *        1����ѯ��䶼��ʹ��executeQuery��������
                 *        2�����ݿⷵ��������һ��ResultSet������ߴ��������Ҫ��ѯ������
                 * 4.������������ResultSet���󣬴�ӡ���ݣ�������򿴴���ע��
                 * 5.���������ʹ��con�����˺�PreparedStatement�����˾��ͷ���Դ
                 * ע�⣺���˲�ѯ����������һ�ɶ�ʹ��executeUpdate����
                 */
                //1.��дsql��䣺sql�ֶδ������Ǿ����sql��䣬�����ҾͲ���˵��
                sql ="�������ǲ�ѯ��sql���";
                //2.��ȡPreparedStatement����
                ps = con.prepareStatement(sql);//������sql��䴫��
                //3.ͨ��ps����sql���,ͬʱ��ȡ����ֵ����һ�����ݵļ���
                ResultSet set = ps.executeQuery();
                //4.�����ü���
                //set.next() �÷������ж��Ƿ�����һ������
                while(set.next()) {//һ�ζ�ȡһ������
                    //ע�⣺�����Object�������滻Ϊ������������ͣ���Ϊ����ʾ���õ�Object
                    Object value1 = set.getObject(1);//�����б���ֶ�ֵ��ţ���1��
                    Object value2 = set.getObject(2);//�����б���ֶ�ֵ��ţ���2��
                    Object value3 = set.getObject(3);//�����б���ֶ�ֵ��ţ���3��
                    //����ȡ����һ�еĲ�ͬ�ֶ�ֵ���δ�ӡ��������
                    System.out.println(value1 + "\t" + value2 + "\t" + value3);
                }
                //4.����㲻���������˾͹رգ�����close()�������һ�Ҫ����ɾ�����޸ġ���ѯ�����Ͳ��ر���
                con.close();//�ر�����
                ps.close();//�ر�PreparedStatement����
            }
        }



