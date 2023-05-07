package DataCreate;

import java.io.*;

//��������data�ļ�
public class DataFileCreater {
    private Parse parse;
    public String srcPath;
    public String targetPath;

    private File srcDir;
    private File targetDir;

    /**
     * ����DataFileCreater����
     *
     * @param SrcPath
     * @param TargetPath
     * @throws FileNotFoundException
     */
    DataFileCreater(String SrcPath, String TargetPath) throws FileNotFoundException {
        srcPath = SrcPath;
        targetPath = TargetPath;
        srcDir = new File(srcPath);
        targetDir = new File(targetPath);
        if (!srcDir.exists()) {
            //�������Դû�У��׳��쳣
            throw new FileNotFoundException();
        }
        //���Ŀ���ļ���û�У�����
        if (!targetDir.exists()) {
            targetDir.mkdirs();
        }
    }

    public void CreateDataOriginalSingal(File file) throws IOException {
        parse = new Parse();
        parse.setFile(file);
        String title = parse.titleParse();
        String[] res = parse.bodyParse();
        String srcFileName = file.getName();
        File targetFile = new File(targetPath + srcFileName.substring(0, srcFileName.length() - ".html".length()) + ".txt");
        if (!targetFile.exists()) {
            targetFile.createNewFile();
        }
        FileWriter fileWriter = new FileWriter(targetFile);
        fileWriter.write("title:" + title);
        fileWriter.write("\n");
        //�����д�д�룬ÿ����֮���һ������
        for (String e : res) {
            fileWriter.write(e);
            fileWriter.write("\n");
        }
        //д����ϣ��ر�fileWriter
        fileWriter.close();
        parse = null;
    }

    /**
     * ��������Դ�����ڷ��ظ�ǰ�˵ģ�
     * ѭ����Parse�õ��ı��������д�뵽Ŀ��Ŀ¼��
     */
    public void CreateDataOriginal() throws IOException {
        File[] srcFiles = srcDir.listFiles();
        parse = new Parse();
        for (int i = 0; i < srcFiles.length; i++) {
            //��parse������Դ�ļ���������
            parse.setFile(srcFiles[i]);
            //����������ϴ
            String title = parse.titleParse();
            String[] res = parse.bodyParse();
            //����Ŀ�������ļ� ��src�ļ�ͬ������׺��Ϊ.txt
            String srcFileName = srcFiles[i].getName();
            File targetFile = new File(targetPath + srcFileName.substring(0, srcFileName.length() - ".html".length()) + ".txt");
            if (!targetFile.exists()) {
                targetFile.createNewFile();
            }
            //д������
            else {
                continue;
            }
            FileWriter fileWriter = new FileWriter(targetFile);
            fileWriter.write("title:" + title);
            fileWriter.write("\n");
            //�����д�д�룬ÿ����֮���һ������
            for (String e : res) {
                fileWriter.write(e);
                fileWriter.write("\n");
            }
            //д����ϣ��ر�fileWriter
            fileWriter.close();
            parse = null;
        }
    }


    public static void main(String[] args) throws IOException {
        DataFileCreater dataFileCreater = new DataFileCreater("D:\\��������Դ\\html\\", "D:\\��������Դ\\��������\\");
        dataFileCreater.CreateDataOriginal();
    }
}
