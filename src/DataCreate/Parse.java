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
     * ��ȡ����
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
            //����ȡ�Ľ�����뵽����б���
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
     * �ӵ�ǰ�ļ�������ɸѡ��������
     *
     * @return
     * @throws FileNotFoundException
     */
    public String titleParse() throws FileNotFoundException {
        //�ļ������ڷ��ؿ�
        if (!file.exists()) {
            return null;
        }
        int titleBegin = fileContent.indexOf("<title>");
        titleBegin += "<title>".length();
        StringBuilder title = new StringBuilder();
        int titleEnd = fileContent.indexOf("</title>");
        title.append(fileContent.subSequence(titleBegin, titleEnd));
        title.delete(title.length() - "_�ٶ�֪��".length(), title.length());
        return title.toString();
    }

    /**
     * ���õ�ǰɸѡ�ĸ��ļ������ҽ��ļ����ݶ��뻺����
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
