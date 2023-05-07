import IndexCreate.Index;
import ReadFile.ReadFile;
import com.alibaba.fastjson.JSON;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Made {
    public static void main(String[] args) throws IOException, InterruptedException {
        Index index = new Index();
        System.out.println("ÊäÈëÒª¼ìË÷µÄ´Ê");
        Scanner sc=new Scanner(System.in);
        String word=sc.nextLine();

        ArrayList<File> arrFile = index.getByWord(word);
        ArrayList<String>arrTitle=new ArrayList<>();
        ReadFile readFile=new ReadFile();
        for(File e:arrFile){
            readFile.setFile(e);
            arrTitle.add(readFile.getTitle());
        }
        String jsonArr=JSON.toJSONString(arrTitle);
        List<String> list = JSON.parseArray(jsonArr,String.class);
        System.out.println(jsonArr);
        for(String e:list){
            System.out.println(e);
        }
    }
}
