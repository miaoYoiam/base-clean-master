package com.mine.master.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.net.URLDecoder;

public class FileHelperUtils {

    private static volatile FileHelperUtils Instance = null;

    public static FileHelperUtils getInstance() {
        FileHelperUtils instance = Instance;
        if (instance == null) {
            synchronized (FileHelperUtils.class) {
                instance = Instance;
                if (instance == null) {
                    Instance = instance = new FileHelperUtils();
                }
            }
        }
        return instance;
    }

    public void writeContentToTXT(String fileName, String filePath, String content) {
        try {
            File fileDir = new File(filePath);
            if (!fileDir.exists()) {
                fileDir.mkdir();
            }
            File file = new File(filePath + fileName);
            if (!file.exists()) {
                file.createNewFile();
            }

            String strContent = URLDecoder.decode(content + "\r\n", "UTF-8");
            FileOutputStream stream = new FileOutputStream(file);
            OutputStreamWriter streamWriter = new OutputStreamWriter(stream);
            streamWriter.write(strContent);
            streamWriter.flush();
        } catch (Exception e) {
        }
    }
}
