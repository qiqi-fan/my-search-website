package HttpServer;

import com.alibaba.fastjson.JSON;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.SQLException;

public class questionHandler implements HttpHandler {
    public static class questionInfo {
        String title;
        String content;

        public questionInfo(String title, String content) {
            this.title = title;
            this.content = content;
        }
        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }


    }

    public static class questionInfoRequest {
        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public questionInfoRequest(String title, String status) {
            this.title = title;
            this.status = status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getStatus() {
            return status;
        }

        String title;
        String status;
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        System.out.println("Question Request");
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
        questionInfo questionInfo = JSON.parseObject(stringBuilder.toString(), questionInfo.class);
        boolean flag = false;
        try {
            flag = Server.loginAndRegister.login(questionInfo.title, questionInfo.content);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        questionInfoRequest questionInfoRequest;
        if (flag) {
            questionInfoRequest = new questionInfoRequest(questionInfo.title, "success");
        } else {
            questionInfoRequest = new questionInfoRequest(null, "fail");
        }
        String Json = JSON.toJSONString(questionInfoRequest);
        Server.response(exchange, Json.getBytes("UTF-8"), "application/json;charset=UTF-8");
    }
}
