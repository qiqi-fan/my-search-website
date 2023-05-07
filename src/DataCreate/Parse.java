package DataCreate;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

enum State {
    LABEL, CONTEXT
}

public class Parse {
    final String RICH_CONTENT_CONTAINER = "<div class=\"rich-content-container rich-text-\">";
    StringBuilder fileContent;

    /**
     * 获取正文
     *
     * @return
     */
    public String[] bodyParse() {
        int resIndex = 0;
        List<StringBuilder> list = new ArrayList<>();
        while (true) {
            resIndex = fileContent.indexOf(RICH_CONTENT_CONTAINER, resIndex);
            if (resIndex == -1) {
                break;
            }
            resIndex += RICH_CONTENT_CONTAINER.length() + 1;
            char ch = fileContent.charAt(resIndex);
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = resIndex; ch != '\n'; i++) {
                ch = fileContent.charAt(i);
                stringBuilder.append(ch);
            }
            stringBuilder.append('\n');
            //将获取的结果加入到结果列表里
            list.add(stringBuilder);
        }
        String[] resS = new String[list.size()];
        int index = 0;
        for (StringBuilder e : list) {
            resS[index++] = e.toString();
        }
        return resS;
    }


    /**
     * 从当前文件内容中筛选出来标题
     *
     * @return
     * @throws FileNotFoundException
     */
    public String titleParse() throws FileNotFoundException {
        //文件不存在返回空
        if (!file.exists()) {
            return null;
        }
        int titleBegin = fileContent.indexOf("<title>");
        titleBegin += "<title>".length();
        StringBuilder title = new StringBuilder();
        int titleEnd = fileContent.indexOf("</title>");
        title.append(fileContent.subSequence(titleBegin, titleEnd));
        title.delete(title.length() - "_百度知道".length(), title.length());
        return title.toString();
    }

    /**
     * 设置当前筛选哪个文件，并且将文件内容读入缓冲区
     *
     * @param file
     * @throws FileNotFoundException
     */
    public void setFile(File file) throws FileNotFoundException {
        this.file = file;
        InputStream inputStream = new FileInputStream(file);
        Scanner sc = new Scanner(inputStream);
        fileContent = new StringBuilder();
        while (sc.hasNextLine()) {
            fileContent.append(sc.nextLine());
            fileContent.append('\n');
        }
    }

    public File getFile() {
        return file;
    }

    Parse(File file) throws FileNotFoundException {
        setFile(file);
    }

    public Parse() {

    }

    private File file;

}
