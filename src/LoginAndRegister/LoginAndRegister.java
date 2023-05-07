package LoginAndRegister;

import ReadFile.ReadFile;
import SqlUtil.SQLOperator;

import java.io.*;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Scanner;

public class LoginAndRegister {
    SQLOperator sqlOperator=new SQLOperator(SQLOperator.url,SQLOperator.user,SQLOperator.password);
    public LoginAndRegister() throws IOException, SQLException {
    }
    public boolean register(String user,String password) throws FileNotFoundException, SQLException {
        if(check(user)){//如果有用户
            return false;
        }
        sqlOperator.insertPassword(user,Encrypt.md5(password));
        return true;
    }
    public boolean login(String user,String password) throws SQLException {
        if(!check(user)){//用户名不存在
            return false;
        }
        return Encrypt.md5(password).equals(sqlOperator.selectPasswordByName(user));
    }

    private void save(String user,String password) throws FileNotFoundException {

    }
    /**
     * 判断当前这个user存在不存在
     * 存在是true，不存在是false
     * @param user
     * @return
     */
    private boolean check(String user) throws SQLException {
        if(sqlOperator.selectPasswordByName(user)!=null){
            return true;
        }
        return false;
    }
}
