package learning.file;

import com.github.junrar.Archive;
import com.github.junrar.VolumeManager;
import com.github.junrar.exception.RarException;
import com.github.junrar.rarfile.FileHeader;

import java.io.*;
import java.util.List;

public class UnRar {
    /**
     * @param rarFileName rar file name
     * @param outFilePath output file path
     * @return success Or Failed
     * @author zhuss
     * @throws Exception
     */
    public static boolean  unrar(String rarFileName, String outFilePath)  throws  Exception{

        boolean flag = false;
        try  {
            Archive archive = new  Archive(new FileInputStream(rarFileName));
            /*if(archive == null){
                throw new FileNotFoundException(rarFileName + " NOT FOUND!");
            }
            if(archive.isEncrypted()){
                throw new Exception(rarFileName + " IS ENCRYPTED!");
            }
            List<FileHeader> files =  archive.getFileHeaders();
            for (FileHeader fh : files) {
                if(fh.isEncrypted()){
                    throw new Exception(rarFileName + " IS ENCRYPTED!");
                }
                String fileName = fh.getFileNameW();
                if(fileName != null && fileName.trim().length() > 0){
                    String saveFileName = outFilePath+"\\"+fileName;
                    File saveFile = new File(saveFileName);
                    File parent =  saveFile.getParentFile();
                    if(!parent.exists()){
                        parent.mkdirs();
                    }
                    if(!saveFile.exists()){
                        saveFile.createNewFile();
                    }
                    FileOutputStream fos = new FileOutputStream(saveFile);
                    try {
                        archive.extractFile(fh, fos);
                        fos.flush();
                        fos.close();
                    } catch (RarException e) {
                        if(e.getType().equals(RarException.RarExceptionType.notImplementedYet)){
                        }
                    }finally{
                    }
                }
            }
            flag = true;*/
        } catch  (Exception e) {
            e.printStackTrace();
            throw e;
        }
        return flag;
    }

    /**
     *
     * @param rarFile
     *            rar文件路径
     *
     * @param savePath
     *            要解压的路径
     *
     */
    public static void Rar(String rarFile, String savePath) {


        // 判断是否rar文件
        if (!rarFile.endsWith(".rar")) {
            System.err.println("打开的文件不是rar文件！");
            return;
        }
        try {
            FileOutputStream fos = null;
            // 创建一个rar档案文件
            Archive rarArchive = new Archive(new File(rarFile), null);
            // 判断是否有加密
            if (rarArchive != null) {
                if (rarArchive.isEncrypted()) {
                    rarArchive.close();// 关闭资源
                    return;
                }
                FileHeader fileHeader = rarArchive.nextFileHeader();
                while (fileHeader != null) {
                    if (!fileHeader.isDirectory()) {
                        // 从压缩文件中解压出来的文件名，有可能带目录的文件名
//						String name = fileHeader.getFileNameString().trim();
                        String name = fileHeader.getFileNameW().isEmpty() ? fileHeader
                                .getFileNameString() : fileHeader.getFileNameW();
                        String outPutFileName = savePath + "\\" + name;
                        // 分离文件名，为了创建目录
                        File dir = new File(outPutFileName.substring(0, outPutFileName.lastIndexOf("\\")));
                        // 创建文件夹
                        if (!dir.exists() || !dir.isDirectory()) {
                            dir.mkdirs();
                        }
                        fos = new FileOutputStream(new File(outPutFileName));
                        // 保存解压的文件
                        rarArchive.extractFile(fileHeader, fos);
                        // 关闭资源
                        fos.close();
                        fos = null;
                    }
                    fileHeader = rarArchive.nextFileHeader();
                }
            }
            rarArchive.close();// 关闭资源
        } catch (RarException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
