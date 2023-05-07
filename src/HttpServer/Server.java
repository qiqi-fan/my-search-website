package HttpServer;

import IndexCreate.Index;
import LoginAndRegister.LoginAndRegister;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.spi.HttpServerProvider;

import java.io.*;
import java.net.InetSocketAddress;
import java.sql.SQLException;

public class Server {
    static final Index[] index;

    static {
        try {
            index = new Index[]{new Index()};
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    static LoginAndRegister loginAndRegister;

    static {
        try {
            loginAndRegister = new LoginAndRegister();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) throws IOException, InterruptedException {

        HttpServerProvider httpServerProvider = HttpServerProvider.provider();
        //创建服务端
        HttpServer server = httpServerProvider.createHttpServer(new InetSocketAddress(8080), 100);


        server.createContext("/", new RootHandler());


        server.createContext("/loginPage", new loginPageHandler());


        server.createContext("/login", new LoginHandler());

        server.createContext("/register",new RegisterHandler());

        server.createContext("/love/qq", new LoveQQHandler());


        server.createContext("/love/qq/getReplay", new LoveQQGetreplayHandler());

        server.createContext("/sp", new SPHandler());

        server.createContext("/sp/search", new SPSearchHandler());

        server.createContext("/questionPage", new questionPageHandler());

        server.createContext("/MyPage", new MyPageHandler());


        //启动服务
        server.start();
    }
//    HttpExchange：监听器回调时传入的参数，封装了http请求和响应的所有数据操作

    public static void response(HttpExchange exchange, byte[] index, String ContentType) throws IOException {
        exchange.getResponseHeaders().add("Content-Type", ContentType);
        exchange.getResponseHeaders().add("Access-Control-Allow-Origin", "*");
        exchange.getResponseHeaders().add("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
        exchange.sendResponseHeaders(200, index.length);
        OutputStream os = exchange.getResponseBody();
        os.write(index);
        os.close();
    }
}

class RootHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        System.out.println("Request Root");
        System.out.println(exchange.getRemoteAddress());
        StringBuilder stringBuilder = new StringBuilder();
        File index = new File("HTML\\test\\index.html");

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(index)));
        while (bufferedReader.ready()) {
            stringBuilder.append(bufferedReader.readLine());
            stringBuilder.append("\n");
        }
        Server.response(exchange, stringBuilder.toString().getBytes("UTF-8"), "text/html;charset=UTF-8");
    }
}

