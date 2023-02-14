package fun.moyujian.hause.util;

import lombok.extern.slf4j.Slf4j;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Base64;
import java.util.UUID;

/**
 * base64编码图片工具类
 * @author Tian
 * @date 2021/8/26
 */
@Slf4j(topic = "base64图片工具类")
public class Base64PicUtil {

    private static final String JPEG_HEAD_INFO = "data:image/jpeg;base64,";

    private static final String PNG_HEAD_INFO = "data:image/png;base64,";

    private static final String JPG_HEAD_INFO = "data:image/jpg;base64,";

    /**
     * base64图片解码
     * @param base64Pic base64字符串
     * @return 解码后的字节码
     */
    private static byte[] base64PicDecode(String base64Pic) {
        String base64;

        //去除头部信息
        if (base64Pic.contains(JPEG_HEAD_INFO)) {
            base64 = base64Pic.replace(JPEG_HEAD_INFO, "");
        } else if (base64Pic.contains(PNG_HEAD_INFO)) {
            base64 = base64Pic.replace(PNG_HEAD_INFO, "");
        } else {
            base64 = base64Pic.replace(JPG_HEAD_INFO, "");
        }

        byte[] bytes = Base64.getDecoder().decode(base64);

        //调整异常数据
        for (int i = 0; i < bytes.length; i++) {
            if (bytes[i] < 0) {
                bytes[i] += 256;
            }
        }

        return bytes;
    }

    /**
     * 解码并保存base64图片（一律保存为jpg）
     * @param base64Pic base64图片
     * @param path 保存的路径
     * @return 图片的名称
     */
    public static String decodeAndSavePic(String base64Pic, String path) {
        byte[] bytes = base64PicDecode(base64Pic);

        if (bytes == null) {
            return null;
        }

        String fileName = UUID.nameUUIDFromBytes(bytes).toString().replace("-", "") + ".jpg";
        File file = new File(path + fileName);

        if (file.exists()) {
            return fileName;
        }

        try(OutputStream os = new FileOutputStream(file)) {
            os.write(bytes);
            os.flush();
        } catch (IOException e) {
            log.error("图片文件存储异常:{}", e.getMessage());
            return null;
        }

        return fileName;
    }
}
