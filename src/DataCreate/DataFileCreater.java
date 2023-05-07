package DataCreate;

import java.io.*;

//用来生成data文件
public class DataFileCreater {
    private Parse parse;
    public String srcPath;
    public String targetPath;

    private File srcDir;
    private File targetDir;

    /**
     * 构建DataFileCreater对象
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
            //如果数据源没有，抛出异常
            throw new FileNotFoundException();
        }
        //如果目标文件夹没有，创建
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
        //将所有答案写入，每个答案之间空一个换行
        for (String e : res) {
            fileWriter.write(e);
            fileWriter.write("\n");
        }
        //写入完毕，关闭fileWriter
        fileWriter.close();
        parse = null;
    }

    /**
     * 创建数据源（用于返回给前端的）
     * 循环将Parse得到的标题和正文写入到目标目录中
     */
    public void CreateDataOriginal() throws IOException {
        File[] srcFiles = srcDir.listFiles();
        parse = new Parse();
        for (int i = 0; i < srcFiles.length; i++) {
            //将parse和数据源文件关联起来
            parse.setFile(srcFiles[i]);
            //进行数据清洗
            String title = parse.titleParse();
            String[] res = parse.bodyParse();
            //创建目标数据文件 与src文件同名，后缀名为.txt
            String srcFileName = srcFiles[i].getName();
            File targetFile = new File(targetPath + srcFileName.substring(0, srcFileName.length() - ".html".length()) + ".txt");
            if (!targetFile.exists()) {
                targetFile.createNewFile();
            }
            //写入数据
            else {
                continue;
            }
            FileWriter fileWriter = new FileWriter(targetFile);
            fileWriter.write("title:" + title);
            fileWriter.write("\n");
            //将所有答案写入，每个答案之间空一个换行
            for (String e : res) {
                fileWriter.write(e);
                fileWriter.write("\n");
            }
            //写入完毕，关闭fileWriter
            fileWriter.close();
            parse = null;
        }
    }


    public static void main(String[] args) throws IOException {
        DataFileCreater dataFileCreater = new DataFileCreater("D:\\搜索数据源\\html\\", "D:\\搜索数据源\\基础数据\\");
        dataFileCreater.CreateDataOriginal();
    }
}
