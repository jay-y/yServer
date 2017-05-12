package modules.system.utils;

import org.yserver.utils.CiphertextUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Description: FileUtil.<br>
 * Date: 2016/11/21 11:06<br>
 * Author: ysj
 */
public class FileUtil
{

    /**
     * 获取文件MD5值
     *
     * @param inputFile
     * @return
     * @throws IOException
     */
    public static String getFileMD5(File inputFile) throws IOException
    {
        // 缓冲区大小（这个可以抽出一个参数）
        int bufferSize = 256 * 1024;
        FileInputStream fileInputStream = null;
        DigestInputStream digestInputStream = null;
        try
        {
            // 拿到一个MD5转换器（同样，这里可以换成SHA1）
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            // 使用DigestInputStream
            fileInputStream = new FileInputStream(inputFile);
            digestInputStream = new DigestInputStream(fileInputStream, messageDigest);
            // read的过程中进行MD5处理，直到读完文件
            byte[] buffer = new byte[bufferSize];
            while (digestInputStream.read(buffer) > 0) ;
            // 获取最终的MessageDigest
            messageDigest = digestInputStream.getMessageDigest();
            // 拿到结果，也是字节数组，包含16个元素
            byte[] resultByteArray = messageDigest.digest();
            // 同样，把字节数组转换成字符串
            return CiphertextUtil.byteArrayToHex(resultByteArray);
        }
        catch (NoSuchAlgorithmException e)
        {
            return null;
        }
        finally
        {
            try
            {
                digestInputStream.close();
            }
            catch (Exception e)
            {
            }
            try
            {
                fileInputStream.close();
            }
            catch (Exception e)
            {
            }
        }
    }
}
