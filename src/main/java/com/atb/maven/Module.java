package com.atb.maven;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * 每次合主分支代码都报一堆红 需要执行mvn idea:module
 * 每个文件夹都要执行一遍也太麻烦了
 * 交给你了 java
 *
 * @Author 呆呆
 * @Datetime 2022/1/17 14:04
 */
public class Module {

    private static String COMMAND = "mvn idea:module";

    public static void main(String[] args) throws Exception {
        //Runtime.getRuntime().exec();
        File file = new File("E:\\Project-4X-Server");
        List<String> names = new ArrayList<String>();
        File[] tempList = file.listFiles();
        for (File f : tempList) {
            if (f.isDirectory()) {
                File[] files = f.listFiles();
                for (File file1 : files) {
                    if (file1.getName().equals("pom.xml")) {
                        names.add(f.toString());
                    }
                }
            }
        }
        //File f=new File(names.get(0));
        for (String name : names) {
            Process exec = Runtime.getRuntime().exec("cmd.exe /c " + COMMAND, null, new File(name));
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(exec.getInputStream(), "gbk"));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
            }
        }

    }
}
