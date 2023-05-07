package IndexCreate;

import DataCreate.Parse;
import DataCreate.Participle;
import ReadFile.ReadFile;

import java.io.*;
import java.sql.Array;
import java.util.*;

/**
 * ��������ÿһ���ִʺ��word����Ӧ�Ľڵ����Ϣ
 */

public class Index {
    private Participle participle;
    private HashMap<String, ArrayList<String>> hashMap = null;

    /**
     * �õ�Index
     *
     * @throws IOException
     * @throws InterruptedException
     */
    public Index() throws IOException, InterruptedException {
        participle = new Participle();
        File file = new File(Node.filePath);
//        ���ó�ʼֵ
        hashMap = new HashMap<>(4000);
        if (!file.exists()) {
            throw new FileNotFoundException();
        }
        File[] fileList = file.listFiles();
        IndexBuild(fileList);
        System.out.println("�����������");
        participle.destory();
    }

    /**
     * �Ե����ļ����зִ�
     *
     * @param file
     * @throws IOException
     * @throws InterruptedException
     */
    public void IndexBuild(File file) throws IOException, InterruptedException {
        participle = new Participle();
        ReadFile readFile = new ReadFile();
        readFile.setFile(file);
        String title = readFile.getTitle();
        ArrayList<String> arr = participle.Participle(title);
        Set<String> hashSet = new HashSet<>();
        for (String word : arr) {
            if (hashSet.contains(word)) {
                continue;
            }
            hashSet.add(word);
            if (hashMap.get(word) == null) {
                hashMap.put(word, new ArrayList<String>());
            }
            hashMap.get(word).add(file.getName());
        }
        participle.destory();
    }

    /**
     * ������������
     *
     * @param fileList
     * @throws IOException
     * @throws InterruptedException
     */

    private void IndexBuild(File[] fileList) throws IOException, InterruptedException {
        System.out.println("���ڽ��е��������Ĺ�����...");
        ReadFile readFile = new ReadFile();

        for (File f : fileList) {
            readFile.setFile(f);
            String title = readFile.getTitle();
            ArrayList<String> arr = participle.Participle(title);
            Set<String> set = new HashSet<>();
            for (String word : arr) {
                if (set.contains(word)) {
                    continue;
                } else {
                    set.add(word);
                }
                if (hashMap.get(word) == null) {
                    hashMap.put(word, new ArrayList<>());
                }
                hashMap.get(word).add(f.getName());
            }
        }
    }


    /**
     * ���ݹؼ����ҵ���Ӧ���ļ�
     */
    public ArrayList<File> getByWord(String word) {
        ArrayList<String> arrNode = hashMap.get(word);
        ArrayList<File> arrFile = new ArrayList<>();
        if (arrNode == null) {
            return null;
        }
        for (String e : arrNode) {
            arrFile.add(new File(Node.filePath + e));
        }
        return arrFile;
    }

}
