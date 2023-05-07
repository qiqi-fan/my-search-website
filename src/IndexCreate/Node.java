package IndexCreate;

public class Node {
    public Node() {
    }

    public Node(String fileName, String title) {
        setFileName(fileName);
        setTitle(title);
    }

    static final public String filePath = "D:\\搜索数据源\\基础数据\\";
    private String fileName;
    private String title;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
