package com.base.lx.controller;

import com.alibaba.fastjson.JSONObject;
import com.base.lx.constant.Constants;

import java.io.*;
import java.util.Scanner;

/**
 * 拉取文件-》下载文件-》读取文件-》入库
 */
public class FileController {

    public static void main(String[] args) throws IOException {
        //读取文件
        readFile(downloadFile());
    }


    /**
     * 读取文件内容
     *
     * @param localPath 生成的本地地址
     * @throws FileNotFoundException
     */
    public static void readFile(String localPath) throws FileNotFoundException {
        try (Scanner sc = new Scanner(new FileInputStream(localPath), "UTF-8")) {
            while (sc.hasNext()) {
                String[] text = sc.nextLine().split("\\|", -1);
                //模拟保存数据库
                System.out.println(JSONObject.toJSON(text));
            }
        }
    }

    /**
     * 下载文件
     * 去远程服务器拉文件，并保存到本地
     */
    public static String downloadFile() throws IOException {
        //生成远程服务器以及文件名称  固定格式TYG_PRECREDIT_20230301.txt 模拟今天是一号
        String sftpPath = Constants.SFTP_PATH + Constants.THIRD_FILE_NAME + Constants.UNDERLINE + "20230301" + Constants.SUFFIX_TXT;
        //下载到本地
        String localPath = Constants.LOCAL_PATH + Constants.THIRD_FILE_NAME + Constants.UNDERLINE + "20230301" + Constants.SUFFIX_TXT;
        //模拟sftp文件路径去下载
        File file = new File(sftpPath);
        //下载到重新生成的本地路径
        File file1 = new File(localPath);
        byte[] buffer = new byte[1024];
        FileInputStream fis = null;
        BufferedInputStream bis = null;
        OutputStream os = null;
        try {
            fis = new FileInputStream(file);
            bis = new BufferedInputStream(fis);
            os = new FileOutputStream(file1);
            int i = bis.read(buffer);
            while (i != -1) {
                os.write(buffer, 0, i);
                i = bis.read(buffer);
            }
            file.delete();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            assert os != null;
            os.close();
            bis.close();
            fis.close();
            return localPath;
        }
    }

}
