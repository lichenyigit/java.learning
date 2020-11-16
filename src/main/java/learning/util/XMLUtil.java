package learning.util;

import com.alibaba.fastjson.JSON;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import java.util.*;

public class XMLUtil {

    static String xmlStr = "<TradeData>\n" +
            "    <BaseInfo>\n" +
            "        <TradeCode>HCSPolicyStateQuery</TradeCode>\n" +
            "        <TransNo>00320170512102700001</TransNo>\n" +
            "        <RequestDate>2018-05-11 13:33:24</RequestDate>\n" +
            "        <ResponseDate>2018-05-11 13:33:24</ResponseDate>\n" +
            "    </BaseInfo>\n" +
            "    <BackInfo>\n" +
            "        <Result>1</Result>\n" +
            "        <LCContCount>3</LCContCount>\n" +
            "        <LCCont>\n" +
            "            <AppFlag>-1</AppFlag>\n" +
            "            <UWFlag>-1</UWFlag>\n" +
            "            <QueryNo>11140399001600002666</QueryNo>\n" +
            "            <PrtNo></PrtNo>\n" +
            "            <CRPFlag>-1</CRPFlag>\n" +
            "        </LCCont>\n" +
            "        <LCCont>\n" +
            "            <AppFlag>-1</AppFlag>\n" +
            "            <UWFlag>-1</UWFlag>\n" +
            "            <QueryNo>11140399001600002478</QueryNo>\n" +
            "            <PrtNo></PrtNo>\n" +
            "            <CRPFlag>-1</CRPFlag>\n" +
            "        </LCCont>\n" +
            "        <LCCont>\n" +
            "            <AppFlag>-1</AppFlag>\n" +
            "            <UWFlag>-1</UWFlag>\n" +
            "            <QueryNo>11140399001633332478</QueryNo>\n" +
            "            <PrtNo></PrtNo>\n" +
            "            <CRPFlag>-1</CRPFlag>\n" +
            "        </LCCont>\n" +
            "        <ResultInfos/>\n" +
            "    </BackInfo>\n" +
            "</TradeData>";

    //按顺序手动或许节点
    private static void xmlToMap(){
        Document doc = null;
        try {
            doc = DocumentHelper.parseText(xmlStr);
            Element rootElt = doc.getRootElement();
            String rootNodeName = rootElt.getName();
            System.out.println("根节点名称：" + rootNodeName);
            for (Iterator iter = rootElt.elementIterator(); iter.hasNext();) {
                Element element = (Element) iter.next();
                String nodeName = element.getName();
                System.out.println("子节点名称："+nodeName);
                for(Iterator iter1 = element.elementIterator();iter1.hasNext();){
                    Element element1 = (Element) iter1.next();
                    String nodeName1 = element1.getName();
                    System.out.println(nodeName1 + "  " + element1.getData() + "   是否有子节点"+element1.elementIterator().hasNext());
                }
                System.out.println("");
            }
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }

    /**
     * 递归获取节点  并转为map
     * 如果节点map中该key已经存在， 则该key所在的值转为list集合
     */

    public static Map<String, Object> recursionXMLToMap(Element element, Map<String, Object> map){
        for (Iterator iter = element.elementIterator(); iter.hasNext();) {
            Element childElement = (Element) iter.next();
            String nodeName = childElement.getName();
            String nodeData = childElement.getTextTrim();
            //判断该节点下是否有子节点
            if(isExitsChildrenNode(childElement)){//如果有子节点
                Map<String, Object> childMap = new HashMap<>();
                Map<String, Object> result = recursionXMLToMap(childElement, childMap);

                //如果map中已经有该节点，则将该节点该为集合存储
                if(map.get(nodeName) != null){
                    List<Map<String, Object>> nodeList = new ArrayList<>();
                    System.out.println();
                    if(map.get(nodeName) instanceof List){
                        nodeList = (List<Map<String, Object>>) map.get(nodeName);
                        nodeList.add(result);
                    }else if(map.get(nodeName) instanceof Map){
                        Map<String, Object> nodeMap = (Map<String, Object>) map.get(nodeName);
                        nodeList.add(nodeMap);
                    }
                    nodeList.add(result);
                    map.put(nodeName, nodeList);
                }else{
                    map.put(nodeName, result);
                }
            }else{//如果没有子节点
                map.put(nodeName, nodeData);
            }
        }
        return map;
    }
    public static Map<String, Object> XMLToMap(String xmlStr){
        Map<String, Object> data = new HashMap<>();
        try {
            Document doc = DocumentHelper.parseText(xmlStr);

            Map<String, Object> result = new HashMap<>();
            result = recursionXMLToMap(doc.getRootElement(), result);

            //将根节点拼接进去
            data.put(doc.getRootElement().getName(), result);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return data;
    }


    /**
     *当前节点是否有子节点
     * 如果有返回true，如果没有返回false
     */
    private static boolean isExitsChildrenNode(Element element){
        Iterator iterator = element.elementIterator();
        if(iterator.hasNext())
            return true;
        return false;
    }

    /**
     * 递归将map转为xml字符串
     * @param map
     * @return
     */
    public static StringBuffer recursionMapToXML(Map<String, Object> map){
        String value ="";
        StringBuffer resultStr = new StringBuffer();
        for (Map.Entry entry : map.entrySet()){
            String key = (String) entry.getKey();
            System.out.println(key);
            if(entry.getValue() instanceof Map){//value 为Map类型
                Map<String, Object> map1 = (Map<String, Object>) entry.getValue();
                value = recursionMapToXML(map1).toString();
                resultStr.append("<"+key+">");
                resultStr.append(value);
                resultStr.append("</"+key+">");
            }else if(entry.getValue() instanceof List){//value 为List类型
                List<Map<String, Object>> list = (List<Map<String, Object>>) entry.getValue();
                for (Map<String, Object> map1 : list){
                    value = recursionMapToXML(map1).toString();
                    resultStr.append("<"+key+">");
                    resultStr.append(value);
                    resultStr.append("</"+key+">");
                }
            }else{//value 为String类型
                //正常取值
                value = (String) entry.getValue();
                resultStr.append("<"+key+">");
                resultStr.append(value);
                resultStr.append("</"+key+">");
            }
        }
        return resultStr;
    }

    /**
     * map转为xml
      * @param args
     */

    public static void main(String[] args){
        //正常逻辑调用
        //xmlToMap();

        //递归调用
        try {
            Document doc = DocumentHelper.parseText(xmlStr);

            Map<String, Object> result = new HashMap<>();
            result = recursionXMLToMap(doc.getRootElement(), result);

            //将根节点拼接进去
            Map<String, Object> data = new HashMap<>();
            data.put(doc.getRootElement().getName(), result);
            //System.out.println(data);
        } catch (DocumentException e) {
            e.printStackTrace();
        }

        System.out.println("-------------------------------------------------------");

        //map 转 xml
        Map<String, Object> tradeData = new HashMap<>();
        Map<String, Object> tradeDataDetail = new HashMap<>();

        Map<String, Object> baseInfoMap = new HashMap<>();
        Map<String, Object> baseInfoDetailMap = new HashMap<>();
        baseInfoDetailMap.put("TradeCode", "HCSPolicyStateQuery");
        baseInfoDetailMap.put("TransNo", "00320170512102700001");
        baseInfoDetailMap.put("RequestDate", "2018-05-11 13:33:24");
        baseInfoDetailMap.put("ResponseDate", "2018-05-11 13:33:24");
        tradeDataDetail.put("BaseInfo", baseInfoDetailMap);

        Map<String, Object> backInfoDetail = new HashMap<>();
        backInfoDetail.put("Result", "1");
        backInfoDetail.put("LCContCount", "2");
        List<Map<String, Object>> LCContList = new ArrayList<>();
        Map<String, Object> LCContMap = new HashMap<>();
        LCContMap.put("AppFlag", "-1");
        LCContMap.put("UWFlag", "-1");
        LCContMap.put("QueryNo", "11140399001600002666");
        LCContMap.put("PrtNo", "");
        LCContMap.put("CRPFlag", "-1");
        LCContList.add(LCContMap);
        Map<String, Object> LCContMap2 = new HashMap<>();
        LCContMap2.put("AppFlag", "-1");
        LCContMap2.put("UWFlag", "-1");
        LCContMap2.put("QueryNo", "11140399001600002478");
        LCContMap2.put("PrtNo", "");
        LCContMap2.put("CRPFlag", "-1");
        LCContList.add(LCContMap2);
        backInfoDetail.put("LCCont", LCContList);
        tradeDataDetail.put("BackInfo", backInfoDetail);
        tradeData.put("TradeData", tradeDataDetail);

        StringBuffer result = recursionMapToXML(tradeData);
        System.out.println("结果");
        System.out.println(result.toString());

    }

}
