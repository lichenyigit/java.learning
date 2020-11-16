package learning;

import com.alibaba.fastjson.JSONObject;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;

public class ExcelTest {
    private XSSFSheet sheet;

    /**
     * 构造函数，初始化excel数据
     *
     * @param filePath  excel路径
     * @param sheetName sheet表名
     */
    public void Init(String filePath, String sheetName) {
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(filePath);
            XSSFWorkbook sheets = new XSSFWorkbook(fileInputStream);
            //获取sheet
            sheet = sheets.getSheet(sheetName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 根据行和列的索引获取单元格的数据
     *
     * @param row
     * @param column
     * @return
     */
    public String getExcelDateByIndex(int row, int column) {
        XSSFRow row1 = sheet.getRow(row);
        String cell = row1.getCell(column).toString();
        return cell;
    }

    /**
     * 根据某一列值为“******”的这一行，来获取该行第x列的值
     *
     * @param caseName
     * @param currentColumn 当前单元格列的索引
     * @param targetColumn  目标单元格列的索引
     * @return
     */
    public String getCellByCaseName(String caseName, int currentColumn, int targetColumn) {
        String operateSteps = "";
        //获取行数
        int rows = sheet.getPhysicalNumberOfRows();
        for (int i = 0; i < rows; i++) {
            XSSFRow row = sheet.getRow(i);
            String cell = row.getCell(currentColumn).toString();
            if (cell.equals(caseName)) {
                operateSteps = row.getCell(targetColumn).toString();
                break;
            }
        }
        return operateSteps;
    }

    //打印excel数据
    public void readExcelData() {
        //获取行数
        int rows = sheet.getPhysicalNumberOfRows();
        for (int i = 0; i < rows; i++) {
            //获取列数
            XSSFRow row = sheet.getRow(i);
            int columns = row.getPhysicalNumberOfCells();
            for (int j = 0; j < columns; j++) {
                String cell = row.getCell(j).toString();
                System.out.println(cell);
            }
        }
    }

    @Test
    public void test() {
        JSONObject jsonObject = new JSONObject();
        //遍历excel
        Init("C:\\Users\\Administrator\\Documents\\dest\\countryFlags.xlsx", "Sheet1");
        for (int i = 1; i < 210; i++) {
            //获取每行第6，7列
            String cell6 = getExcelDateByIndex(i, 5);
            String cell7 = getExcelDateByIndex(i, 7);
            jsonObject.put(cell7, cell6);
        }

        //获取所有图片文件
        File file = new File("C:\\Users\\Administrator\\Documents\\dest\\png250px");

        if (file.exists()) {
            File[] list = file.listFiles();
            for (File f : list) {
                String v = jsonObject.getString(f.getName());
                System.out.println(f.getName() + "   " + v);
                f.renameTo(new File("C:\\Users\\Administrator\\Documents\\dest\\png250px\\" + v + ".png"));
            }
        }
    }

    @Test
    public void PNGRename() {
        File file = new File("E:\\git_repository\\unity\\AquariumTorus\\Assets\\Image\\Flag");
        if (file.exists()) {
            File[] list = file.listFiles();
            for (File f : list) {
                if (f.getName().endsWith(".png")) {
                    String n1 = f.getName();
                    String n2 = n1.replaceAll(".png", "");
                    f.renameTo(new File("E:\\git_repository\\unity\\AquariumTorus\\Assets\\Image\\Flag\\" + n2 + ".png"));
                    System.out.println(n2 + ".png");
                }
            }
        }
    }
}
