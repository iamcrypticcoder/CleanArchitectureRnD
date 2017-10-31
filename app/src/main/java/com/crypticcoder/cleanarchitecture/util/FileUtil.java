package com.crypticcoder.cleanarchitecture.util;

import android.content.Context;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import static com.crypticcoder.cleanarchitecture.util.LogUtil.LOGD;
import static com.crypticcoder.cleanarchitecture.util.LogUtil.makeLogTag;

/**
 * Created by mahbub on 4/15/2015.
 */
public class FileUtil {
    private static final String DEBUG_TAG = makeLogTag("FileUtil");

    public static final String FILE_PUBLIC_STORY_FILTER = "public_story_filter";
    public static final String FILE_MY_STORY_FILTER = "my_story_filter";

    /**
     * Write a serializable object into file
     * @param object
     * @param fileName
     * @return true, if write successful, false otherwise
     */
    public static boolean writeToFile(Context context, Object object, String fileName) {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(context.openFileOutput(fileName, Context.MODE_PRIVATE));
            oos.writeObject(object);
            oos.close();
            LOGD(DEBUG_TAG, "Object written to file : " + fileName);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Read a serializable object from file
     * @param fileName
     * @return
     */
    public static Object readFromFile(Context context, String fileName) {
        try {
            ObjectInputStream ois = new ObjectInputStream(context.openFileInput(fileName));
            Object ret = ois.readObject();
            ois.close();
            LOGD(DEBUG_TAG, "Object read from file : " + fileName);
            return ret;
        } catch (FileNotFoundException e) {
            LOGD(DEBUG_TAG, "FileNotFoundException in readFromFile()");
            return null;
        } catch (IOException e) {
            LOGD(DEBUG_TAG, "IOException in readFromFile()");
            return null;
        } catch (ClassNotFoundException e) {
            LOGD(DEBUG_TAG, "ClassNotFoundException in readFromFile()");
            return null;
        }
    }

    /**
     * Return true is file exists
     * @param context
     * @param fileName
     * @return
     */
    public static boolean isFileExist(Context context, String fileName) {
        File file = context.getFileStreamPath(fileName);
        if(file == null || !file.exists()) return false;
        return true;
    }
}
