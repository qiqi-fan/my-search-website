package HttpServer;

import ReadFile.ReadFile;
import com.alibaba.fastjson.JSON;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class SPSearchHandler implements HttpHandler {
    public static class TitleAndFilename {
        public TitleAndFilename(String fileName, String title) {
            this.fileName = fileName;
            this.title = title;
        }

        public String fileName;
        public String title;
    }
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        System.out.println("----------");
        System.out.println(exchange.getRequestURI().getQuery());
        String word = exchange.getRequestURI().getQuery();
        ArrayList<File> arr = Server.index[0].getByWord(word);
        ReadFile readFile = new ReadFile();
        ArrayList<TitleAndFilename> arrTitle = new ArrayList<>();
        for (File e : arr) {
            readFile.setFile(e);
            arrTitle.add(new TitleAndFilename(e.getName(), readFile.getTitle()));
        }
        String Json = JSON.toJSONString(arrTitle);
        Server.response(exchange, Json.getBytes("UTF-8"), "application/json;charset=UTF-8");
    }
}
