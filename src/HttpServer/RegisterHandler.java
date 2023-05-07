package HttpServer;

import LoginAndRegister.Login;
import com.alibaba.fastjson.JSON;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.SQLException;

public class RegisterHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        System.out.println("Register Request");
        InputStream in = exchange.getRequestBody();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in));
        StringBuilder stringBuilder = new StringBuilder();
        while (bufferedReader.ready()) {
            stringBuilder.append(bufferedReader.readLine());
            stringBuilder.append("\n");
        }
        in.close();
        bufferedReader.close();
        System.out.println(stringBuilder);
        LoginHandler.UserInfo userInfo = JSON.parseObject(stringBuilder.toString(), LoginHandler.UserInfo.class);
        System.out.println(userInfo.user+" "+userInfo.password);
        boolean flag = false;
        try {
            flag = Server.loginAndRegister.register(userInfo.user, userInfo.password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        LoginHandler.UserInfoRequest userInfoRequest;
        if (flag) {
            userInfoRequest = new LoginHandler.UserInfoRequest(userInfo.user, "success");
        } else {
            userInfoRequest = new LoginHandler.UserInfoRequest(null, "fail");
        }
        String Json = JSON.toJSONString(userInfoRequest);
        Server.response(exchange, Json.getBytes("UTF-8"), "application/json;charset=UTF-8");
    }

    public static void main(String[] args) {
//        LoginHandler.UserInfo userInfo=new LoginHandler.UserInfo("ljc","1234566");
//        String jsonString=JSON.toJSONString(userInfo);
//        System.out.println(jsonString);
//        LoginHandler.UserInfo userInfo1=JSON.parseObject(jsonString,userInfo.getClass());
//        System.out.println(userInfo1.user);
//        System.out.println(userInfo1.password);
    }
}
