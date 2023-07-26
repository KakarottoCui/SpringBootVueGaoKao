package com.ivo.mas.system.utils;

import com.ivo.mas.system.SysInfo;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.core.io.ClassPathResource;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Base64AndFileUtils {

    /**
     * 保存图片字节数组到某路径
     * @return
     */

    public static boolean savePic(String path, byte[] imageByte) {
        OutputStream out;
        try {
            out = new FileOutputStream(path);
            out.write(imageByte);
            out.flush();
            out.close();
            return true;
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return false;
        }
    }

    /**
     * base64转字节数组
     * @return
     */
    public static byte[] Base64ToByte(String imgBase64) {
        Base64 decoder = new Base64();
        byte[] imageByte = decoder.decode(imgBase64.replace("\r\n", ""));
        for (int i = 0; i < imageByte.length; ++i) {
            if (imageByte[i] < 0) {// 调整异常数据
                imageByte[i] += 256;
            }
        }
        return imageByte;
    }

    /**
     * base64转字节数组，去除base64图片类型
     * @return
     */
    public static byte[] Base64ToByteCut(String imgBase64) {
        Base64 decoder = new Base64();
        String[] dataInfo = imgBase64.split(",");
        String codeString = dataInfo[1];
        byte[] imageByte = decoder.decode(codeString.replace("\r\n", ""));
        for (int i = 0; i < imageByte.length; ++i) {
            if (imageByte[i] < 0) {// 调整异常数据
                imageByte[i] += 256;
            }
        }
        return imageByte;
    }

    /**
     * 保存base64图片到指定路径,生成随机文件夹用指定文件名保存
     * @return
     */
    public static String SaveBase64PicWithPath(String imgBase64, String path, String fileName) {
        try {
            String[] dataInfo = imgBase64.split(",");
            String codeString = dataInfo[1];
            String typeString = dataInfo[0];
            String type = getSubUtilSimple(typeString, "data:image/(.*?);base64");
            fileName = fileName.length() > 0 ? fileName : UUID.randomUUID().toString() + new Date().getTime();
            checkPath(path);
            path = path + "/" + fileName + "." + type;
            savePic(path, Base64ToByte(codeString));
            return fileName + "." + type;
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 以通用配置保存base64图片到指定路径
     * @return
     */
    public static String saveBase64PicWithCommonSet(String imgBase64){
        Calendar cal = Calendar.getInstance();
        String yearAndWeek = cal.get(Calendar.YEAR) + "W" + cal.get(Calendar.WEEK_OF_YEAR);
        String imgPath = SysInfo.FILE_PATH + yearAndWeek;
        return SaveBase64PicWithPathAndName(imgBase64, imgPath, UUID.randomUUID().toString() + new Date().getTime());
    }

    /**
     * 保存base64图片到指定路径,指定文件名保存,返回保存路径
     * @return
     */
    public static String SaveBase64PicWithPathAndName(String imgBase64, String path, String fileName) {
        try {
            String[] dataInfo = imgBase64.split(",");
            String codeString = dataInfo[1];
            String typeString = dataInfo[0];
            String type = getSubUtilSimple(typeString, "data:image/(.*?);base64");
            checkPath(path);
            path = path + "/" + fileName + "." + type;
            savePic(path, Base64ToByte(codeString));
            return path;
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            return null;
        }
    }

    // 正则截取特定字符之间值
    public static String getSubUtilSimple(String soap, String rgex) {
        Pattern pattern = Pattern.compile(rgex);// 匹配的模式
        Matcher m = pattern.matcher(soap);
        while (m.find()) {
            return m.group(1);
        }
        return "";
    }

    /**
     * 移动文件到指定路径并重命名文件
     * @return
     */
    public static boolean moveFileByPathAndChangeName(String opath, String tpath, String newName) {
        try {
            File file = new File(opath); // 源文件
            checkPath(tpath);
            if (file.renameTo(new File(tpath + newName))) // 源文件移动至目标文件目录
                return true;
            else
                return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 检查路径是否存在，不存在新建
     * @return
     */
    public static void checkPath(String path) {
        File folder = new File(path);
        // 文件夹路径不存在
        if (!folder.exists() && !folder.isDirectory()) {
            folder.mkdirs();
        }
    }
    /**
     * 删除指定文件
     * @return
     */
    public static boolean deleteFile(String pathname) {
        try {
            File file = new File(pathname);
            if (file.exists()) {
                file.delete();
                return true;
            } else {
                return true;
            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            return false;
        }
    }


    /**
     * 判断文件包含字符并删除
     * @return
     */
    public static void deleteFilesLikeName(File file, String likeName) {
        if (file.isFile()) {
            // 是文件
            String temp = file.getName().substring(0, file.getName().lastIndexOf("."));
            if (temp.indexOf(likeName) != -1) {
                file.delete();
            }
        } else {
            // 是目录
            File[] files = file.listFiles();
            for (int i = 0; i < files.length; i++) {
                deleteFilesLikeName(files[i], likeName);
            }
        }
    }

    // 删除某目录下文件名字包含指定字符的文件
    public static void deleteDirFilesLikeName(String dir, String likeName) {
        File file = new File(dir);
        if (file.exists()) {
            deleteFilesLikeName(file, likeName);
        }
    }

    /**
     * url转输出流
     * @return
     */
    public static OutputStream UrlToOutputStream(String url,OutputStream stream) {
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        try {
            URL url2 = new URL(url);
            //获取此路径的连接
            URLConnection conn = url2.openConnection();
            bis = new BufferedInputStream(conn.getInputStream());//构造读取流
            bos = new BufferedOutputStream(stream);//构造输出流
            byte[] buff = new byte[1024];
            int bytesRead;
            //每次读取缓存大小的流，写到输出流
            while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
                bos.write(buff, 0, bytesRead);
            }
            return stream;
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            try{
                if(null != bis){
                    bis.close();
                }
                if(null != bos){
                    bos.close();
                }
            }catch(IOException e){
                e.printStackTrace();
            }
        }
        return null;
    }


    /**
     * 将网络图片转换成Base64编码字符串
     *
     * @param imgUrl 网络图片Url
     * @return
     */
    public static String getImgUrlToBase64(String imgUrl) {
        InputStream inputStream = null;
        ByteArrayOutputStream outputStream = null;
        byte[] buffer = null;
        try {
            // 创建URL
            URL url = new URL(imgUrl);
            // 创建链接
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(5000);
            inputStream = conn.getInputStream();
            outputStream = new ByteArrayOutputStream();
            // 将内容读取内存中
            buffer = new byte[1024];
            int len = -1;
            while ((len = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, len);
            }
            buffer = outputStream.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    // 关闭inputStream流
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (outputStream != null) {
                try {
                    // 关闭outputStream流
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        // 对字节数组Base64编码
        return Base64.encodeBase64String(buffer);
    }

    /**
     * 将网络图片转换成BufferedImage
     *
     * @param imgUrl 网络图片Url
     * @return
     * @throws IOException
     */
    public static BufferedImage getImgUrlToBufferedImage(String imgUrl) throws IOException {
        InputStream inputStream = null;
        ByteArrayOutputStream outputStream = null;
        byte[] buffer = null;
        try {
            // 创建URL
            URL url = new URL(imgUrl);
            // 创建链接
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(5000);
            inputStream = conn.getInputStream();
            outputStream = new ByteArrayOutputStream();
            // 将内容读取内存中
            buffer = new byte[1024];
            int len = -1;
            while ((len = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, len);
            }
            buffer = outputStream.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    // 关闭inputStream流
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (outputStream != null) {
                try {
                    // 关闭outputStream流
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        ByteArrayInputStream bais = new ByteArrayInputStream(buffer);
        return ImageIO.read(bais);
    }

    /**
     * BufferedImage 编码转换为 base64
     * @param bufferedImage
     * @return
     */
    public static String BufferedImageToBase64(BufferedImage bufferedImage) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();//io流
        try {
            ImageIO.write(bufferedImage, "png", baos);//写入流中
        } catch (IOException e) {
            e.printStackTrace();
        }
        byte[] bytes = baos.toByteArray();//转换成字节
        Base64 base64 = new Base64();
        base64.encodeAsString(bytes);
        String png_base64 = base64.encodeAsString(bytes);
        png_base64 = png_base64.replaceAll("\n", "").replaceAll("\r", "");//删除 \r\n
        return "data:image/png;base64," + png_base64;
    }

    /**
     * base64 编码转换为 BufferedImage
     * @param base64
     * @return
     */
    public  static BufferedImage base64ToBufferedImage(String base64) {
        Base64 b64 = new Base64();
        try {
            byte[] bytes1 = b64.decode(base64);
            ByteArrayInputStream bais = new ByteArrayInputStream(bytes1);
            return ImageIO.read(bais);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * resource路径图片文件 转换为 BufferedImage
     * @param path resource路径地址
     * @return
     * @throws IOException
     */
    public static BufferedImage readImgByResourcePath(String path) throws IOException {
        ClassPathResource classPathResource = new ClassPathResource(path);
        InputStream inputStream =classPathResource.getInputStream();
        BufferedImage bi = ImageIO.read(inputStream);
        inputStream.close();
        return bi;
    }

    /**
     * 将BufferedImage转换为InputStream
     * @param image
     * @return
     */
    public static InputStream bufferedImageToInputStream(BufferedImage image){
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        try {
            ImageIO.write(image, "png", os);
            InputStream input = new ByteArrayInputStream(os.toByteArray());
            return input;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    // inputStream转outputStream
    public static OutputStream streamParse(final InputStream in) throws Exception {
        final ByteArrayOutputStream swapStream = new ByteArrayOutputStream();
        int ch;
        while ((ch = in.read()) != -1) {
            swapStream.write(ch);
        }
        return swapStream;
    }

    // outputStream转inputStream
    public static InputStream streamParse(final OutputStream out) throws Exception {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        baos = (ByteArrayOutputStream) out;
        final ByteArrayInputStream swapStream = new ByteArrayInputStream(baos.toByteArray());
        return swapStream;
    }

}
