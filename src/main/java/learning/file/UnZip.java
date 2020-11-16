package learning.file;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;

import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipFile;

public class UnZip {

    public static void unZipFiles(File zipFile, String descDir) throws IOException {
        File pathFile = new File(descDir);
        if (!pathFile.exists()) {
            pathFile.mkdirs();
        }
        //创建解压文件夹
        String zipName = zipFile.getName();
        zipName = zipName.replaceAll(".zip","");
        //创建解压路径
        FileStudy.judeDirExists(descDir + "\\" + zipName);
        ZipFile zip = new ZipFile(zipFile, "UTF-8");
        for (Enumeration entries = zip.getEntries(); entries.hasMoreElements(); ) {
            ZipEntry entry = (ZipEntry) entries.nextElement();
            String zipEntryName = entry.getName();
            InputStream in = zip.getInputStream(entry);
            //String outPath = (descDir + zipEntryName).replaceAll("\\/", "\\");
            String outPath = descDir + zipEntryName;

            if (!zipEntryName.contains(".obj") && !zipEntryName.contains(".fbx") && !zipEntryName.contains(".mtl")) {
                continue;
            }
            //创建文件
            File file = new File(outPath);
            file.createNewFile();
            //输出文件路径信息 
            System.out.println("11  "+outPath);
            //TODO 写文件更好的方法
            OutputStream out = new FileOutputStream(outPath);
            byte[] buf1 = new byte[1024];
            int len;
            while ((len = in.read(buf1)) > 0) {
                out.write(buf1, 0, len);
            }
            in.close();
            out.close();
        }
        System.out.println("******************解压完毕********************");
    }
}
