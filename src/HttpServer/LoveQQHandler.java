package HttpServer;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.*;

public class LoveQQHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        File index = new File("HTML\\test\\mess.html");
        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(index)));
        while (bufferedReader.ready()) {
            stringBuilder.append(bufferedReader.readLine());
            stringBuilder.append("\n");
        }
        Server.response(exchange, stringBuilder.toString().getBytes("UTF-8"), "text/html;charset=UTF-8");
    }
}

