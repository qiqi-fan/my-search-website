package LoginAndRegister;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 进行md5加密
 */
public class Encrypt {
    static MessageDigest messageDigest;

    static {
        try {
            messageDigest = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {

            throw new RuntimeException(e);

        }
    }

    public Encrypt() throws NoSuchAlgorithmException {
    }

    public static String md5(String target){
        messageDigest.update(target.getBytes());
        byte []res= messageDigest.digest();
        return toHex(res);
    }

    /**
     * 将加密后的字节数组转化为32位的十六进制
     * @param res
     * @return
     */
    private static String toHex(byte []res){
        StringBuilder stringBuilder=new StringBuilder(32);
        char []hex=new char[]{'0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f'};
        for(byte e:res){
            stringBuilder.append(hex[e>>>4&0xf]);
            stringBuilder.append(hex[e&0xf]);
        }
        return stringBuilder.toString();
    }
}
