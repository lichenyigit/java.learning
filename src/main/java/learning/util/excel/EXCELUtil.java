package learning.util.excel;

import com.alibaba.fastjson.JSON;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author lichenyi
 * @date 2018-05-17-0017 14:01
 */
public class EXCELUtil {

    public static final String json__name = "accBankCode";
    public static String path = "D:\\_lichenyi\\和谐字典\\";
    public static String outPath = "D:\\_lichenyi\\和谐字典\\json\\";
    public static void main(String[] args) throws Exception {

        DictModel dm = beJsonIndustry(json__name);
        DItem dItem = toDItem(dm);


        String result = JSON.toJSONString(dItem,true);

        File file = new File(outPath + json__name + ".json");

        Writer w = new FileWriter(file);
        w.write(result);
        w.close();

        System.out.println("转换完毕");

    }

    private static DItem toDItem(DictModel dm) {

        return toDItem(dm,null);
    }

    private static DItem toDItem(DictModel dm,Integer index) {
        DItem d = new DItem();
        String key = null == index ? dm.getPreKey() :dm.getPreKey() + "_" + index;
        d.setKey(key);
        d.setType(dm.getType());
        d.setCode(dm.getCode());
        d.setName(dm.getName());

        List<DictModel> childModel = dm.getChildModel();
        if(childModel != null){
            List<DItem> list = new ArrayList<DItem>();
            for (int i = 0;i < childModel.size();i++){

                DictModel tempD = childModel.get(i);

                // TODO
//                DItem item = toDItem(tempD);
                DItem item = toDItem(tempD,i);
                list.add(item);
            }

            d.setItems(list);
        }


        return d;
    }

    public static DictModel beJsonIndustry(String type) throws Exception {
        File file = new File(path+json__name + ".csv");
        BufferedReader br = new BufferedReader(new FileReader(file));

        String line;
        Boolean richLevel = true;
        Map<String, List<DictModel>> kMap = new HashMap();
        Map<String, List<DictModel>> pMap = new HashMap();


        Boolean firstLine = true;
        while (StringUtils.isNotEmpty(line = br.readLine())) {
            if (firstLine) {
                firstLine = false;
                continue;
            }

//            line = new String(line.getBytes(""),"utf-8");

            String[] fileds = line.split(",");

            DictModel dm = new DictModel(fileds);

            String flagKey = dm.getFlagKey();
            if (StringUtils.isNotEmpty(flagKey)) {

                List<DictModel> dictModels = pMap.get(flagKey);
                if (null == dictModels) {
                    dictModels = new ArrayList<DictModel>();
                    pMap.put(flagKey, dictModels);
                }
                dictModels.add(dm);
            }

            String preKey = dm.getPreKey();
//            // TODO
//            preKey = type;
            List<DictModel> list = kMap.get(preKey);
            if (null == list) {
                list = new ArrayList<DictModel>();
                kMap.put(preKey, list);
            }
            list.add(dm);
        }
        br.close();
        return groupDictModel(type, pMap, kMap);
    }

    private static DictModel groupDictModel(String type, Map<String, List<DictModel>> pMap, Map<String, List<DictModel>> kMap) {

        List<DictModel> dictModels = kMap.get(type);

        butit(pMap, dictModels);

        DictModel dm = new DictModel();
        dm.setType(type);
        dm.setPreKey(type);
        dm.setChildModel(dictModels);

        return dm;
    }

    private static void butit(Map<String, List<DictModel>> pMap, List<DictModel> dictModels) {
        for (DictModel dm : dictModels) {
            String flagKey = dm.getType() + dm.getCode();
            List<DictModel> childModel = pMap.get(flagKey);
            if (null != childModel) {
                dm.setChildModel(childModel);
                butit(pMap, childModel);
            }

        }
    }


    @Data
    public static class DictModel {
        private String type;
        private String code;
        private String name;
        private String upType;
        private String upCode;

        private String preKey; // type_upCode_index
        private String flagKey;

        private List<DictModel> childModel;

        public DictModel() {

        }

        public DictModel(String[] arr) {
            this.type = arr[0];
            this.code = arr[1];
            this.name = arr[2];
            System.out.println(this.name);
            if (arr.length == 5) {
                this.upType = arr[3];
                this.upCode = arr[4];

                this.preKey = this.type + "_" + this.upCode;
                this.flagKey = this.upType + this.upCode;
            } else {
                this.preKey = this.type;
            }

        }

        public DictModel(String type, String code, String name) {
            this.type = type;
            this.code = code;
            this.name = name;
        }

        public DictModel(String type, String code, String name, String upType, String upCode) {
            this.type = type;
            this.code = code;
            this.name = name;
            this.upType = upType;
            this.upCode = upCode;
        }
    }

}
