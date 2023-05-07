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
        if(check(user)){//������û�
            return false;
        }
        sqlOperator.insertPassword(user,Encrypt.md5(password));
        return true;
    }
    public boolean login(String user,String password) throws SQLException {
        if(!check(user)){//�û���������
            return false;
        }
        return Encrypt.md5(password).equals(sqlOperator.selectPasswordByName(user));
    }

    private void save(String user,String password) throws FileNotFoundException {

    }
    /**
     * �жϵ�ǰ���user���ڲ�����
     * ������true����������false
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
