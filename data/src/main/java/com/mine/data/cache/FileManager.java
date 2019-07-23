package com.mine.data.cache;

import android.content.Context;
import android.content.SharedPreferences.Editor;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class FileManager {
    @Inject
    public FileManager() {

    }

    public void writeToFile(File file, String fileContent) {
        if (!file.exists()) {
            try {
                FileWriter writer = new FileWriter(file);
                writer.write(fileContent);
                writer.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e2) {
                e2.printStackTrace();
            }
        }
    }

    public String readFileContent(File file) {
        StringBuilder fileContentBuilder = new StringBuilder();
        if (file.exists()) {
            try {
                FileReader fileReader = new FileReader(file);
                BufferedReader bufferedReader = new BufferedReader(fileReader);
                while (true) {
                    String stringLine = bufferedReader.readLine();
                    if (stringLine == null) {
                        break;
                    }
                    fileContentBuilder.append(stringLine + "\n");
                }
                bufferedReader.close();
                fileReader.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e2) {
                e2.printStackTrace();
            }
        }
        return fileContentBuilder.toString();
    }

    public boolean exists(File file) {
        return file.exists();
    }

    public void clearDirectory(File directory) {
        if (directory.exists()) {
            for (File file : directory.listFiles()) {
                file.delete();
            }
        }
    }

    public void writeToPreferences(Context context, String preferenceFileName, String key, long value) {
        Editor editor = context.getSharedPreferences(preferenceFileName, 0).edit();
        editor.putLong(key, value);
        editor.apply();
    }

    public long getFromPreferences(Context context, String preferenceFileName, String key) {
        return context.getSharedPreferences(preferenceFileName, 0).getLong(key, 0);
    }
}
