package com.base.lx.util;

import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.model.FileHeader;

import java.io.*;
import java.util.List;
import java.util.Scanner;


/**
 * zip解压带密码压缩包 并读取文件内容
 */
public class ZipUtils {


    public static void main(String[] args) throws IOException, ZipException {
        ZipUtils z = new ZipUtils();
        byte[] data = {};
        try {
            FileInputStream fis = new FileInputStream("C:\\Users\\T470\\Desktop\\cs.zip");
            ByteArrayOutputStream bos = new ByteArrayOutputStream(1000);
            byte[] b = new byte[1000];
            int n;
            while ((n = fis.read(b)) != -1) {
                bos.write(b, 0, n);
            }
            fis.close();
            data = bos.toByteArray();
            bos.close();
            System.out.println(data);
        } catch (Exception e) {
            e.printStackTrace();
        }

        String dest = "D:\\JAVA-DEMO\\base-demo\\src\\main\\resources\\static";

        String password = "1234567";

        z.unZip(data, dest, password);
    }

    /**
     * @param data   原始文件路径
     * @param dest     解压路径
     * @param password 解压文件密码(可以为空)
     */
    public void unZip(byte[]  data, String dest, String password) throws IOException, ZipException {

        BufferedOutputStream bos = null;
        FileOutputStream fos = null;
        File file = null;
        try {
            File dir = new File(dest);
            if (!dir.exists() && dir.isDirectory()) {
                dir.mkdirs();
            }
            file = new File(dest + "\\" + "cs2.zip");
            fos = new FileOutputStream(file);
            bos = new BufferedOutputStream(fos);
            bos.write(data);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (bos != null) {
                try {
                    bos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        ZipFile zFile = new ZipFile(file);
        zFile.setFileNameCharset("GBK");
        // 解压目录
        File destDir = new File(dest);
        if (!destDir.exists()) {
            // 目标目录不存在时，创建该文件夹
            destDir.mkdirs();
        }
        if (zFile.isEncrypted()) {
            // 设置密码
            zFile.setPassword(password.toCharArray());
        }
        // 将文件抽出到解压目录(解压)
        zFile.extractAll(dest);
        List<FileHeader> headerList = zFile.getFileHeaders();
        if (headerList.size() > 0) {
            File extractedFiles = new File(destDir, headerList.get(0).getFileName());
            int lineNumbers = 0;
            try (Scanner sc = new Scanner(new FileInputStream(extractedFiles), "UTF-8")) {
                while (sc.hasNext()) {
                    lineNumbers++;
                    //跳过第一行
                    if (1 == lineNumbers) {
                        sc.nextLine();
                        continue;
                    }
                    String[] text = sc.nextLine().split("\\|", -1);
                    System.out.print(text[0]);
                    System.out.print(",");
                    System.out.print(text[1]);
                    System.out.print(",");
                    System.out.print(text[2]);
                    System.out.print(",");
                    System.out.print(text[3]);
                    System.out.print(",");
                    System.out.print(text[4]);
                    System.out.print(",");
                    System.out.print(text[5]);
                    System.out.print(",");
                    System.out.print(text[6]);
                    System.out.print(",");
                    System.out.print(text[7]);
                    System.out.print(",");
                    System.out.print(text[8]);
                    System.out.print(",");
                    System.out.print(text[9]);
                    System.out.println("");
                }
                System.out.println(extractedFiles.getAbsolutePath() + "文件解压成功!");
            } catch (FileNotFoundException e) {
                System.out.println(e);
            }
        }
    }
}