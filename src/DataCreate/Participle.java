package DataCreate;

import java.io.*;
import java.util.ArrayList;

public class Participle {
    private Process process;
    private OutputStream outputStream;
    private InputStream inputStream;
    private BufferedWriter writer;
    private BufferedReader reader;
    /**
     * 创建python进程并获取输入输出流
     *
     * @throws IOException
     */
    public Participle() throws IOException, InterruptedException {
        createPy();
        process = Runtime.getRuntime().exec("python jiebaTool.py");

        outputStream = process.getOutputStream();
        inputStream = process.getInputStream();
        writer=new BufferedWriter(new OutputStreamWriter(outputStream));
        reader=new BufferedReader(new InputStreamReader(inputStream));
    }

    /**
     * 创建py文件
     */
    private void createPy() throws IOException {
        String fileName = "./jiebaTool.py";
        File pyFile = new File(fileName);
        if (pyFile.exists()) {
            return;
        }
        pyFile.createNewFile();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("import jieba\n");
        stringBuilder.append("while True:\n");
        stringBuilder.append("  str=input()\n");
//        搜索引擎模式分词
        stringBuilder.append("  str_list = jieba.cut_for_search(str)\n");
        stringBuilder.append("  for e in str_list:\n");
        stringBuilder.append("      print(e)\n");
        //完成python代码的写入
        OutputStream outputStream = new FileOutputStream(pyFile);
        outputStream.write(stringBuilder.toString().getBytes());
        outputStream.close();
    }

    //进行分词 返回分词后的结果
    public ArrayList<String> Participle(String title) throws IOException, InterruptedException {
        writer.write(title + "\n");
        writer.flush();
        ArrayList<String> arrayList = new ArrayList<>();
        String line;
        while ((line = reader.readLine()) != null) {
            arrayList.add(line);
            if (!reader.ready()) {
                break;
            }
        }
        return arrayList;
    }

    public void destory() throws IOException {
        writer.close();
        reader.close();
        inputStream.close();
        outputStream.close();
        process.destroy();

    }
}
