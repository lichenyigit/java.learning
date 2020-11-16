package learning.util.excel;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class DItem implements Cloneable {


    private String type;
    private String key;
    /**
     * 名称
     */
    private String name;
    /**
     * 代码
     */
    private String code;
    /**
     * 对应commonKey
     */
    private String commonKey;


    private List<DItem> items;

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
