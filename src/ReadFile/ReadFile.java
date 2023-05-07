package ReadFile;

import DataCreate.Parse;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class ReadFile {
    public ReadFile(){}
    private File file;
    public void setFile(File file) throws FileNotFoundException {
        this.file=file;
    }
    public File getFile(){
        return this.file;
    }

    /**
     *
     * @return title
     * @throws IOException
     */
    public String getTitle() throws IOException {
        InputStream inputStream=new FileInputStream(file);
        Scanner scanner=new Scanner(inputStream);
        String title=scanner.nextLine();
        inputStream.close();
        title=title.substring("title :".length()-1);
        return title;
    }

    /**
     *
     * @return »Ø´ð
     * @throws IOException
     */
    public ArrayList<String> getAns() throws IOException {
        InputStream inputStream=new FileInputStream(file);
        Scanner scanner=new Scanner(inputStream);
        String line=null;
        scanner.nextLine();
        ArrayList<String>arr=new ArrayList<>();
        while(scanner.hasNextLine()){
            line= scanner.nextLine();
            scanner.nextLine();
            scanner.nextLine();
            arr.add(line);
        }
        inputStream.close();
        return arr;
    }


}
