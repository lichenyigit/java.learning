package learning.file;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

//import org.junit.Test;

@SuppressWarnings("all")
public class ImgCompareCssJs {

	private final static String imgPath = "E:\\_lichenyi\\2016-10\\youxiduolibao\\misc\\img";
	private final static String cssJsPath[] = {"E:\\_lichenyi\\2016-10\\youxiduolibao\\misc\\css", "E:\\workspace\\com.youxiduo.www.mainstage1.0\\src\\main\\webapp\\WEB-INF\\template\\libao.ftl", "E:\\_lichenyi\\2016-10\\youxiduolibao\\misc\\js"};//, "E:\\_lichenyi\\2016-10\\youxiduolibao\\misc\\js"
	private final static String exportPath = "E:\\_lichenyi\\2016-10\\static_libao_txt";
	private final static String exportFileUser = "\\imgUse.txt";
	private final static String exportFileUnuser = "\\imgUnuse.txt";
	
	
	/**
	 * value -1 表示图片没有使用; 非-1表示图片在某个位置中使用了(具体信息可以在key中展示)
	 */
	private Map<String, Integer> mapLog = new LinkedHashMap<String, Integer>();
	
	/**
	 * Integer --> imgCompareCssJs's parameter
	 * 1 - 记录图片路径在cssjs文件中的位置（行号）  (用了)
	 * 2 - 图片路径是否存在于css js文件中，如果没有则进行记录（没用）
	 */
	
	
	/**
	 * 图片路径是否存在于css js文件中，如果没有则进行记录
	 * @author lichenyi
	 * 2016年10月9日 上午9:14:19
	 */
	//@Test
	public void imgInCssJsLineTest(){
		try {
			//get img file
			File imgFile = getFiles(this.imgPath);
			//get css js file
			for (String string : this.cssJsPath) {
				File cssJsFile = getFiles(string);
				//imgFile is file 
				if(imgFile.isFile()){
					System.out.println("total: "+1);
					imgCompareCssJs(this.imgPath, cssJsFile, 1);
					System.out.println(this.imgPath+" -  文件比较成功! ");
				}else{//imgFile is directory
					System.out.println("total: "+imgFile.listFiles().length);
					for (File file : imgFile.listFiles()) {
						imgCompareCssJs(file.getPath(), cssJsFile, 1);
						System.out.println(file.getPath()+" -  文件比较成功! ");
					}
				}
			}
			apentToTxt(this.exportPath+this.exportFileUser, "\n");
			System.out.println(" -  文件比较完毕! ");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 记录图片路径在cssjs文件中的位置（行号）
	 * @author lichenyi
	 * 2016年10月9日 上午9:20:05
	 */
	//@Test
	public void imgUnuserInCssJsTest(){
		try {
			//get img file
			File imgFile = getFiles(this.imgPath);
			//get css js file
			for (String string : this.cssJsPath) {
				File cssJsFile = getFiles(string);
				
				//imgFile is file 
				if(imgFile.isFile()){
					System.out.println("total: "+1);
					imgCompareCssJs(this.imgPath, cssJsFile, 2);
					System.out.println(this.imgPath+" -  文件比较成功! ");
				}else{//imgFile is directory
					System.out.println("total: "+imgFile.listFiles().length);
					for (File file : imgFile.listFiles()) {
						imgCompareCssJs(file.getPath(), cssJsFile, 2);
						System.out.println(file.getPath()+" -  文件比较成功! ");
					}
				}
			}
			mapUnuseToTxt(this.exportPath+this.exportFileUnuser);
			apentToTxt(this.exportPath+this.exportFileUnuser, "\n");
			System.out.println(" -  文件比较完毕! ");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	/**
	 * 单个img文件比较多个或单个cssjs文件
	 * @author lichenyi
	 * @param imgPath
	 * @param cssJsFile
	 * @throws FileNotFoundException
	 * 2016年10月8日 下午5:27:07
	 */
	private void imgCompareCssJs(String imgPath, File cssJsFile, Integer sign) throws FileNotFoundException{
		//css js is file 
		if(cssJsFile.isFile()){
			compareCssJs(imgPath, cssJsFile, sign);
		}else{//css js is dirctory
			for (File file : cssJsFile.listFiles()) {
				compareCssJs(imgPath, file, sign);
			}
		}
	}
	
	/**
	 * 对比img在file中是否存在，如果存在则记录信息至指定文件，如果不存在，则不进行记录
	 * @author lichenyi
	 * @param imgPath
	 * @param file
	 * @throws FileNotFoundException
	 * 2016年10月8日 下午4:37:37
	 */
	private void compareCssJs(String imgPath, File file, Integer sign) throws FileNotFoundException{
		try {
			String filename = parseFileName(imgPath);
			if(mapLog.get(filename) == null && !mapLog.containsKey(filename)){
				mapLog.put(filename, -1);
			}
			BufferedReader br = new BufferedReader(new FileReader(file));// 从字符输入流中读取文件中的内容,封装了一个new InputStreamReader的对象
			String tempStr = null;
			int line = 1;
			while((tempStr = br.readLine()) != null){
				if(!tempStr.isEmpty() && (compareContent(filename, tempStr) || compareContent(filename, tempStr.replaceAll("/", "\\\\")))){
					//write log to txt
					mapLog.put(filename, line);
					if(new Integer(1).compareTo(sign) == 0){
						apentToTxt(this.exportPath+this.exportFileUser, imgPath+" - "+line+" - "+file.getName()+" - "+tempStr+"\n");
					}
				}
				line++;
			}
		} catch (FileNotFoundException e) {
			throw new FileNotFoundException(String.format(" 【%s】 is not found.", file.getName()));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	
	/**
	 * 比对文件
	 * @author lichenyi
	 * @param compareStr
	 * @param beCompareStr
	 * @return
	 * 2016年10月8日 下午4:37:22
	 */
	private Boolean compareContent(String compareStr, String beCompareStr){
		if(beCompareStr.trim().contains(compareStr)){
			return true;
		}
		return false;
	}
	
	/**
	 * 获取文件通用方法
	 * @author lichenyi
	 * @param pathname
	 * @return
	 * @throws Exception
	 * 2016年10月8日 下午4:37:09
	 */
	private File getFiles(String pathname) throws Exception{
		File file = new File(pathname);
		if(file != null){
			return file;
		}
		throw new Exception(String.format("【%s】   is not defind. ", pathname));
	} 
	
	/**
	 * 把mapLog中的数据写入txt
	 * @author lichenyi
	 * @throws IOException
	 * 2016年10月8日 下午5:55:17
	 */
	private void mapUnuseToTxt(String pathname) throws IOException {
		for (Entry<String, Integer> entry : mapLog.entrySet()) {
			String key = entry.getKey();
			Integer value = entry.getValue();
			if(value < 0){
				apentToTxt(pathname, key+"\n");
			}
		}
	}
	
	/**
	 * 解析文件名
	 * @author lichenyi
	 * @param pathname
	 * @return
	 * 2016年10月9日 上午9:37:22
	 */
	private String parseFileName(String pathname){
		String prefixStr = pathname.substring(0, pathname.lastIndexOf("\\"));
		String prefixStr2 = prefixStr.substring(prefixStr.lastIndexOf("\\")+1, prefixStr.length());
		if(pathname.indexOf("\\") > -1){
			pathname = pathname.substring(pathname.lastIndexOf("\\")+1, pathname.length());
		}
		return prefixStr2+"\\"+pathname;
	}
	
	/**
	 * 将context写入path中对应的文件下
	 * @author lichenyi
	 * @param pathname
	 * @param content
	 * @throws IOException
	 * 2016年10月8日 下午5:28:52
	 */
	private void apentToTxt(String pathname, String content) throws IOException{
		File file = new File(pathname);
		File parent = file.getParentFile(); 
		if(parent!=null&&!parent.exists())
			parent.mkdirs(); 
		if(!file.exists())
			file.createNewFile();
		FileWriter fileWriter = new FileWriter(file, true);
		BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
		bufferedWriter.write(content);
		bufferedWriter.close();
	}
	
}
