package SqlUtil;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;

public class SQLOperator {
    public static final String url="jdbc:mysql://localhost:3306/test";
    public static final String user="root";
    public static final String password="030218";
    DataSource dataSource=new MysqlDataSource();
    Connection connection;

    /**
     * ππ‘ÏSQLOperator
     * @param url
     * @param user
     * @param password
     * @throws SQLException
     */
    public SQLOperator(String url,String user,String password) throws SQLException {
        ((MysqlDataSource)dataSource).setURL(url);
        ((MysqlDataSource)dataSource).setUser(user);
        ((MysqlDataSource)dataSource).setPassword(password);
        connection=dataSource.getConnection();
    }
    public void insertPassword(String user,String password) throws SQLException {
        insert("user_password(user_name,user_password)","'"+user+"','"+password+"'");
    }
    //insert into student(name,id) values(name,id);
    private void insert(String table,String value) throws SQLException {
        String sql="insert into "+table+" values("+value+");";
        System.out.println(sql);
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.executeUpdate();
    }
    //select id,name from student;
    //zhangsan 1
    //lisi 2
    public String selectPasswordByName(String userName) throws SQLException {
        ResultSet resultSet=select_p("user_password","user_password","user_name<=>'"+userName+"'");
        String pass=null;
        while(resultSet.next()){
            pass=resultSet.getString("user_password");
        }
        resultSet.close();
        return pass;
    }

    private ResultSet select_p(String select,String table) throws SQLException {
        String sql="select "+select+" from "+table+";";
        System.out.println(sql);
        PreparedStatement preparedStatement=connection.prepareStatement(sql);
        return preparedStatement.executeQuery();
    }
    private ResultSet select_p(String select,String table,String where) throws SQLException {
        String sql="select "+select+" from "+table+" where "+where+";";
        System.out.println(sql);
        PreparedStatement preparedStatement=connection.prepareStatement(sql);
        return preparedStatement.executeQuery();
    }
    public void close() throws SQLException {
        connection.close();
    }
}
