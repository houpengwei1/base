package com.base.lx.downfile;


import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;


/**
 * @author FuYuChuang
 * @version 1.0
 * @date 2023/4/24 16:48
 * @desc
 */
public class FileUtils {
    /**
     * 获取远程文件
     *
     * @param remoteFilePath 远程文件路径
     * @param localFilePath  本地文件路径
     */
    public static void downloadFile(String remoteFilePath, String localFilePath) {
        URL urlfile = null;
        HttpURLConnection httpUrl = null;
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;

        File f = new File(localFilePath);
        try {
            urlfile = new URL(remoteFilePath);
            httpUrl = (HttpURLConnection) urlfile.openConnection();
            httpUrl.connect();
            bis = new BufferedInputStream(httpUrl.getInputStream());
            bos = new BufferedOutputStream(new FileOutputStream(f));
            int len = 2048;
            byte[] b = new byte[len];
            while ((len = bis.read(b)) != -1) {
                bos.write(b, 0, len);
            }
            bos.flush();
            bis.close();
            httpUrl.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                bis.close();
                bos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    public static void main(String[] args) throws IOException {

        //        String localPath = ResourceUtils.getFile("classpath:").getPath();
        String localPath = FileUtils.class.getClassLoader().getResource("").getPath();
        System.out.println(localPath);
        // windos
        String filePath = "D:\\ideaproject\\base\\src\\main\\resources\\666.png";
        // linux
        //如果链接包含中文，需要转码
        String resultWordPath = URLEncoderUtils.encode("http://172.27.8.114:8888/group1/M00/00/02/rBsIcmRGTmyACO9VAACJyLSKQKo959.jpg?filename=cest.jpg");
//        String resultWordPath = URLEncoderUtils.encode("http://172.27.8.114:8888/group1/M00/00/02/rBsIcmRGRtOACPFQAAesUHeM1LA087.png?filename=微信图片_20230327171838.png");

        FileUtils.downloadFile(resultWordPath, filePath);


    }


}
