package learning.file;

//import org.junit.Test;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;


@SuppressWarnings("all")
public abstract class FileStudy {

    //@Test
    public void readFile() {
        ByteArrayOutputStream baosArrayOutputStream = new ByteArrayOutputStream();
        try {
            String pathname = "F:/download tencent/Calculor.java";
            File file = new File(pathname);
            FileInputStream fis = new FileInputStream(file);

            byte[] sendBytes = new byte[1024];
            int length = 0;
            while ((length = fis.read(sendBytes)) != -1) {
                baosArrayOutputStream.write(sendBytes, 0, length);
            }

            System.out.println(new String(baosArrayOutputStream.toByteArray(), "GBK"));

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                baosArrayOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public void readTXTByLine(String path) {
        BufferedReader bre = null;
        try {
            bre = new BufferedReader(new FileReader(path));//此时获取到的bre就是整个文件的缓存流
            String str = "";
            int lineNumber = 0;
            while ((str = bre.readLine()) != null) // 判断最后一行不存在，为空结束循环
            {
                readTXTByLineExe(str, lineNumber++);
            }
            ;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    protected abstract void readTXTByLineExe(String line, int lineNumber);

    static final String removeFilePathName = "F:\\download Google\\search (1)";

    /**
     * 移除文件test
     *
     * @author lichenyi
     * 2016年10月10日 下午2:07:38
     */
    //@Test
    public void removeFileTest() {
        try {
            removeFile(this.removeFilePathName);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void removeFile(String pathname) throws FileNotFoundException {
        File file = getFile(pathname);
        if (file.delete())
            System.out.println(String.format("%s is delete success.", pathname));
        else
            System.out.println(String.format("%s is delete failed.", pathname));
    }


    static final String renameFilePath = "F:\\download Google";
    static final String renameFile1 = "\\GitHubSetup.exe";
    static final String renameFile2 = "\\GitHubSetup1.exe";

    /**
     * 文件或路径重命名 test
     *
     * @author lichenyi
     * 2016年10月10日 下午2:20:33
     */
    //@Test
    public void renameFileTest() {
        try {
            renameFile(renameFilePath);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void renameFile(String pathname) throws FileNotFoundException {
        File file = getFile(pathname + this.renameFile1);
        if (file.renameTo(new File(pathname + this.renameFile2)))
            System.out.println(String.format("%s is rename success.", pathname));
        else
            System.out.println(String.format("%s is rename failed.", pathname));

    }


    /**
     * 通用的获取文件的方法
     *
     * @param pathname
     * @return
     * @throws FileNotFoundException 2016年10月10日 下午2:02:49
     * @author lichenyi
     */
    public File getFile(String pathname) throws FileNotFoundException {
        File file = new File(pathname);
        if (file == null)
            throw new FileNotFoundException(String.format("getFile() throw 【%s】  is not found. ", pathname));
        return file;
    }

    /**
     * 重新写入内容，原来的内容会被覆盖掉
     *
     * @param content
     * @param path
     */
    public static void rewriteFile(String content, String path) {
        try {
            Files.write(Paths.get(path), content.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 追加文件内容
     *
     * @param content
     * @param path
     */
    public static void appendFile(String content, String path) {
        BufferedWriter out = null;
        try {
            out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(path, true)));
            out.write(content);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 判断文件是否存在
     * @param file
     */
    public static void judeFileExists(String path) {
        File file = new File(path);
        if (file.exists()) {
            System.out.println("file exists");
        } else {
            System.out.println("file not exists, create it ...");
            try {
                file.createNewFile();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

    }

    /**
     * 判断文件夹是否存在
     * @param file
     */
    public static void judeDirExists(String path) {
        File file = new File(path);
        if (file.exists()) {
            if (file.isDirectory()) {
                System.out.println("dir exists");
            } else {
                System.out.println("the same name file exists, can not create dir");
            }
        } else {
            System.out.println("dir not exists, create it ...");
            file.mkdir();
        }

    }
}
