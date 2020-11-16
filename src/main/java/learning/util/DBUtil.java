package learning.util;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Lichenyi
 * @date 2017-7-18
 */
public class DBUtil {

    /**
     * 将ResultSet转为list Map集合
     * @param
     * @return
     * @author lichenyi
     * @date 2017-7-19 11:05
     */
    public static List<Map<String, Object>> resultSetToMap(ResultSet rs) throws SQLException {
        ResultSetMetaData rsmd = rs.getMetaData();
        int columns = rsmd.getColumnCount();
        List<String> colums = new ArrayList<>();
        for(int i = 1;i <= columns;i++){
            String column = rsmd.getColumnName(i);
            colums.add(column);
        }
        List<Map<String, Object>> result = new ArrayList<Map<String,Object>>();
        while (rs.next()) {
            Map<String, Object> map = new HashMap<String, Object>();
            for (String column : colums) {
                String value = rs.getString(column);
                CommonUtils.generateMap(map, column, value);
            }
            result.add(map);
        }
        return result;
    }

}
