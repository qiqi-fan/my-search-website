package HttpServer;

import com.alibaba.fastjson.JSON;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.SQLException;

public class LoginHandler implements HttpHandler {
    public static class UserInfo {
        String user;
        String password;
        public String getUser() {
            return user;
        }

        public UserInfo(String user, String password) {
            this.user = user;
            this.password = password;
        }

        public void setUser(String user) {
            this.user = user;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }


    }

    public static class UserInfoRequest {
        public String getUser() {
            return user;
        }

        public void setUser(String user) {
            this.user = user;
        }

        public UserInfoRequest(String user, String status) {
            this.user = user;
            this.status = status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getStatus() {
            return status;
        }

        String user;
        String status;
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        System.out.println("Login Request");
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
        UserInfo userInfo = JSON.parseObject(stringBuilder.toString(), UserInfo.class);
        boolean flag = false;
        try {
            flag = Server.loginAndRegister.login(userInfo.user, userInfo.password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        UserInfoRequest userInfoRequest;
        if (flag) {
            userInfoRequest = new UserInfoRequest(userInfo.user, "success");
        } else {
            userInfoRequest = new UserInfoRequest(null, "fail");
        }
        String Json = JSON.toJSONString(userInfoRequest);
        Server.response(exchange, Json.getBytes("UTF-8"), "application/json;charset=UTF-8");
    }
}
