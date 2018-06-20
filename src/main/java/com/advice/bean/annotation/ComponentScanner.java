package com.advice.bean.annotation;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by yuch on 2018/6/20.
 */
public class ComponentScanner {

    public static HashMap<String, String> getComponentClassName(
            String packageName) {
        List<String> classes = getClassName(packageName);
        HashMap<String, String> components = new HashMap<String, String>();
        try {
            for (String cl : classes) {
                //cl = cl.replace("com.advice.bean.annotation.bean.", "");
                Component comp = Class.forName(cl).getAnnotation(Component.class);
                if (comp != null) {
                    components.put(comp.id(), cl);
                }
            }
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return components;
    }

    public static List<String> getClassName(String packageName) {
        String filePath = ClassLoader.getSystemResource("").getPath()
                + packageName.replace(".", "\\");
        List<String> fileNames = getClassName(filePath, null);
        return fileNames;
    }

    private static List<String> getClassName(String filePath
            , List<String> className) {
        List<String> myClassName = new ArrayList<String>();
        File file = new File(filePath);
        File[] childFiles = file.listFiles();
        for (File childFile : childFiles) {
            if (childFile.isDirectory()) {
                myClassName.addAll(getClassName(childFile.getPath()
                        , myClassName));
            } else {
                String childFilePath = childFile.getPath();
                childFilePath = childFilePath.substring(childFilePath
                        .indexOf("classes") + 8, childFilePath.lastIndexOf("."));
                childFilePath = childFilePath.replace("\\", ".");
                myClassName.add(childFilePath);
            }
        }

        return myClassName;
    }

    public static void main(String[] args) {
        getComponentClassName("com.advice.bean.annotation.bean");
    }
}
