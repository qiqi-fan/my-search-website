package HttpServer;

import IndexCreate.Node;
import ReadFile.ReadFile;
import com.alibaba.fastjson.JSON;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;

public class LoveQQGetreplayHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        class ResponseNode {
            String title;
            ArrayList<String> arr;

            public String getTitle() {
                return title;
            }

            public ArrayList<String> getArr() {
                return arr;
            }

            ResponseNode(String title, ArrayList<String> arr) {
                this.title = title;
                this.arr = arr;
            }

        }
        URI uri = exchange.getRequestURI();
        String[] ss = uri.toString().split("/");
        String fileName = ss[ss.length - 1];
        System.out.println(fileName);
        File file = new File(Node.filePath + fileName);
        ReadFile readFile = new ReadFile();
        readFile.setFile(file);
        ArrayList<String> arr = readFile.getAns();
        String title = readFile.getTitle();
        ResponseNode resp = new ResponseNode(title, arr);
        System.out.println(resp.title);
        String strArr = JSON.toJSONString(resp);
        System.out.println(strArr);
        Server.response(exchange, strArr.getBytes("UTF-8"), "application/json;charset=UTF-8");
    }
}
