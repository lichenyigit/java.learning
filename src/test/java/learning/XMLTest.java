package learning;

import com.alibaba.fastjson.JSON;
import learning.util.XMLUtil;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author lichenyi
 * @date 2018-05-15-0015 16:22
 */
public class XMLTest {

    String xmlStr = "<?xml version=\"1.0\" encoding=\"GBK\"?>\n" +
            "<TradeData>\n" +
            "\t<BaseInfo>\n" +
            "\t\t<TradeCode>HCSContConfirm</TradeCode>\n" +
            "\t\t<RequestDate>2018-05-03 09:31:17</RequestDate>\n" +
            "\t\t<UserCode>test</UserCode>\n" +
            "\t\t<Password>1234@abcd</Password>\n" +
            "\t</BaseInfo>\n" +
            "\t<ContInfo>\n" +
            "\t\t<LCCont>\n" +
            "\t\t\t<OldAppointMentNo>07320180503000000004</OldAppointMentNo>\n" +
            "\t\t\t<AppointMentNo>07320180503000000004</AppointMentNo>\n" +
            "\t\t\t<CompanyFlag>HCS</CompanyFlag>\n" +
            "\t\t\t<PolApplyDate>2018-05-03</PolApplyDate>\n" +
            "\t\t\t<ManageCom>9090101</ManageCom>\n" +
            "\t\t\t<Operator>Auto0001</Operator>\n" +
            "\t\t\t<PayIntv>12</PayIntv>\n" +
            "\t\t\t<SellType>13</SellType>\n" +
            "\t\t\t<SaleChnl>1</SaleChnl>\n" +
            "\t\t\t<BusinessChnl>02</BusinessChnl>\n" +
            "\t\t\t<GetPolMode>2</GetPolMode>\n" +
            "\t\t\t<ContSource>073</ContSource>\n" +
            "\t\t\t<BizSource>9</BizSource>\n" +
            "\t\t</LCCont>\n" +
            "\t\t<Agents>\n" +
            "\t\t\t<AgentCount>1</AgentCount>\n" +
            "\t\t\t<Agent>\n" +
            "\t\t\t\t<AgentCode>114005497</AgentCode>\n" +
            "\t\t\t\t<AgentName>王慧楠</AgentName>\n" +
            "\t\t\t\t<AgentCom>111019</AgentCom>\n" +
            "\t\t\t\t<AgentGroup></AgentGroup>\n" +
            "\t\t\t\t<Busirate></Busirate>\n" +
            "\t\t\t\t<RecommenderNo>114005497</RecommenderNo>\n" +
            "\t\t\t</Agent>\n" +
            "\t\t</Agents>\n" +
            "\t\t<LCAppnt>\n" +
            "\t\t\t<AppntName>赵晓</AppntName>\n" +
            "\t\t\t<AppntSex>0</AppntSex>\n" +
            "\t\t\t<AppntBirthday>1987-08-24</AppntBirthday>\n" +
            "\t\t\t<AppntIDType>0</AppntIDType>\n" +
            "\t\t\t<AppntIDNo>511304198708242814</AppntIDNo>\n" +
            "\t\t\t<IDTypeStartDate>2010-07-11</IDTypeStartDate>\n" +
            "\t\t\t<IDTypeEndDate>2060-07-11</IDTypeEndDate>\n" +
            "\t\t\t<NativePlace>CHN</NativePlace>\n" +
            "\t\t\t<OccupationType>1</OccupationType>\n" +
            "\t\t\t<OccupationCode>3010101</OccupationCode>\n" +
            "\t\t\t<LCAddress>\n" +
            "\t\t\t\t<Province>110000</Province>\n" +
            "\t\t\t\t<City>110000</City>\n" +
            "\t\t\t\t<County>110101</County>\n" +
            "\t\t\t\t<PostalAddress>四川省成都市高新区天府大道966天府国际金融中心2号楼</PostalAddress>\n" +
            "\t\t\t\t<ZipCode></ZipCode>\n" +
            "\t\t\t\t<AppntReturnCall>3</AppntReturnCall>\n" +
            "\t\t\t\t<Mobile>15008429540</Mobile>\n" +
            "\t\t\t\t<Email>2259531839@qq.com</Email>\n" +
            "\t\t\t</LCAddress>\n" +
            "\t\t</LCAppnt>\n" +
            "\t\t<LCInsureds>\n" +
            "\t\t\t<LCInsuredCount>1</LCInsuredCount>\n" +
            "\t\t\t<LCInsured>\n" +
            "\t\t\t\t<Name>赵晓儿</Name>\n" +
            "\t\t\t\t<Sex>0</Sex>\n" +
            "\t\t\t\t<Birthday>2016-01-29</Birthday>\n" +
            "\t\t\t\t<IDType>0</IDType>\n" +
            "\t\t\t\t<IDNo>511304201601292816</IDNo>\n" +
            "\t\t\t\t<IDTypeStartDate>2016-07-11</IDTypeStartDate>\n" +
            "\t\t\t\t<IDTypeEndDate>2066-07-11</IDTypeEndDate>\n" +
            "\t\t\t\t<NativePlace>CHN</NativePlace>\n" +
            "\t\t\t\t<OccupationType>1</OccupationType>\n" +
            "\t\t\t\t<OccupationCode>3010101</OccupationCode>\n" +
            "\t\t\t\t<SequenceNo>1</SequenceNo>\n" +
            "\t\t\t\t<RelationToMainInsured>00</RelationToMainInsured>\n" +
            "\t\t\t\t<RelationToAppnt>01</RelationToAppnt>\n" +
            "\t\t\t\t<LCAddress>\n" +
            "\t\t\t\t\t<Province>110000</Province>\n" +
            "\t\t\t\t\t<City>110000</City>\n" +
            "\t\t\t\t\t<County>110101</County>\n" +
            "\t\t\t\t\t<PostalAddress>四川省成都市高新区天府大道966天府国际金融中心2号楼</PostalAddress>\n" +
            "\t\t\t\t\t<ZipCode></ZipCode>\n" +
            "\t\t\t\t\t<AppntReturnCall>3</AppntReturnCall>\n" +
            "\t\t\t\t\t<Mobile>15008429541</Mobile>\n" +
            "\t\t\t\t\t<Email>2259531830@qq.com</Email>\n" +
            "\t\t\t\t</LCAddress>\n" +
            "\t\t\t</LCInsured>\n" +
            "\t\t</LCInsureds>\n" +
            "\t\t<Risks>\n" +
            "\t\t\t<RiskCount>3</RiskCount>\n" +
            "\t\t\t<Risk>\n" +
            "\t\t\t\t<RiskSelNo>1</RiskSelNo>\n" +
            "\t\t\t\t<RiskCode>2160413</RiskCode>\n" +
            "\t\t\t\t<MainRiskCode>2160413</MainRiskCode>\n" +
            "\t\t\t\t<Amnt>50000</Amnt>\n" +
            "\t\t\t\t<Prem>100000</Prem>\n" +
            "\t\t\t\t<Mult>1</Mult>\n" +
            "\t\t\t\t<PayIntv>12</PayIntv>\n" +
            "\t\t\t\t<InsuYearFlag>A</InsuYearFlag>\n" +
            "\t\t\t\t<InsuYear>25</InsuYear>\n" +
            "\t\t\t\t<Payendyearflag>A</Payendyearflag>\n" +
            "\t\t\t\t<Payendyear>21</Payendyear>\n" +
            "\t\t\t\t<Dutys>\n" +
            "\t\t\t\t\t<DutyCount>1</DutyCount>\n" +
            "\t\t\t\t\t<Duty>\n" +
            "\t\t\t\t\t\t<DutyCode>906100</DutyCode>\n" +
            "\t\t\t\t\t\t<Amnt>50000</Amnt>\n" +
            "\t\t\t\t\t\t<Prem>100000</Prem>\n" +
            "\t\t\t\t\t\t<PayIntv>12</PayIntv>\n" +
            "\t\t\t\t\t\t<InsuYearFlag>A</InsuYearFlag>\n" +
            "\t\t\t\t\t\t<InsuYear>25</InsuYear>\n" +
            "\t\t\t\t\t\t<Payendyearflag>A</Payendyearflag>\n" +
            "\t\t\t\t\t\t<Payendyear>21</Payendyear>\n" +
            "\t\t\t\t\t</Duty>\n" +
            "\t\t\t\t</Dutys>\n" +
            "\t\t\t</Risk>\n" +
            "\t\t\t<Risk>\n" +
            "\t\t\t\t<RiskSelNo>2</RiskSelNo>\n" +
            "\t\t\t\t<RiskCode>2150022</RiskCode>\n" +
            "\t\t\t\t<MainRiskCode>2160413</MainRiskCode>\n" +
            "\t\t\t\t<Amnt>50000</Amnt>\n" +
            "\t\t\t\t<Prem>0</Prem>\n" +
            "\t\t\t\t<Mult>1</Mult>\n" +
            "\t\t\t\t<PayIntv>0</PayIntv>\n" +
            "\t\t\t\t<InsuYearFlag>Y</InsuYearFlag>\n" +
            "\t\t\t\t<InsuYear>1</InsuYear>\n" +
            "\t\t\t\t<Payendyearflag>Y</Payendyearflag>\n" +
            "\t\t\t\t<Payendyear>1</Payendyear>\n" +
            "\t\t\t\t<Dutys>\n" +
            "\t\t\t\t\t<DutyCount>1</DutyCount>\n" +
            "\t\t\t\t\t<Duty>\n" +
            "\t\t\t\t\t\t<DutyCode>902300</DutyCode>\n" +
            "\t\t\t\t\t\t<Amnt>50000</Amnt>\n" +
            "\t\t\t\t\t\t<Prem>0</Prem>\n" +
            "\t\t\t\t\t\t<PayIntv>0</PayIntv>\n" +
            "\t\t\t\t\t\t<InsuYearFlag>Y</InsuYearFlag>\n" +
            "\t\t\t\t\t\t<InsuYear>1</InsuYear>\n" +
            "\t\t\t\t\t\t<Payendyearflag>Y</Payendyearflag>\n" +
            "\t\t\t\t\t\t<Payendyear>1</Payendyear>\n" +
            "\t\t\t\t\t</Duty>\n" +
            "\t\t\t\t</Dutys>\n" +
            "\t\t\t</Risk>\n" +
            "\t\t\t<Risk>\n" +
            "\t\t\t\t<RiskSelNo>3</RiskSelNo>\n" +
            "\t\t\t\t<RiskCode>2140082</RiskCode>\n" +
            "\t\t\t\t<MainRiskCode>2160413</MainRiskCode>\n" +
            "\t\t\t\t<Amnt>50000</Amnt>\n" +
            "\t\t\t\t<Prem>0</Prem>\n" +
            "\t\t\t\t<Mult>1</Mult>\n" +
            "\t\t\t\t<PayIntv>0</PayIntv>\n" +
            "\t\t\t\t<InsuYearFlag>Y</InsuYearFlag>\n" +
            "\t\t\t\t<InsuYear>1</InsuYear>\n" +
            "\t\t\t\t<Payendyearflag>Y</Payendyearflag>\n" +
            "\t\t\t\t<Payendyear>1</Payendyear>\n" +
            "\t\t\t\t<Dutys>\n" +
            "\t\t\t\t\t<DutyCount>1</DutyCount>\n" +
            "\t\t\t\t\t<Duty>\n" +
            "\t\t\t\t\t\t<DutyCode>902400</DutyCode>\n" +
            "\t\t\t\t\t\t<Amnt>50000</Amnt>\n" +
            "\t\t\t\t\t\t<Prem>0</Prem>\n" +
            "\t\t\t\t\t\t<PayIntv>0</PayIntv>\n" +
            "\t\t\t\t\t\t<InsuYearFlag>Y</InsuYearFlag>\n" +
            "\t\t\t\t\t\t<InsuYear>1</InsuYear>\n" +
            "\t\t\t\t\t\t<Payendyearflag>Y</Payendyearflag>\n" +
            "\t\t\t\t\t\t<Payendyear>1</Payendyear>\n" +
            "\t\t\t\t\t</Duty>\n" +
            "\t\t\t\t</Dutys>\n" +
            "\t\t\t</Risk>\n" +
            "\t\t</Risks>\n" +
            "\t\t<LCBnfs>\n" +
            "\t\t\t<LCBnfCount>0</LCBnfCount>\n" +
            "\t\t</LCBnfs>\n" +
            "\t\t<CusImps>\n" +
            "\t\t\t<CusImpCount>0</CusImpCount>\n" +
            "\t\t</CusImps>\n" +
            "\t</ContInfo>\n" +
            "</TradeData>\n";

    @Test
    public void xmlToMap(){
        Map<String, Object> map = XMLUtil.XMLToMap(xmlStr);
        System.out.println(JSON.toJSON(map));
    }

    @Test
    public void print(){
        Map<String, Object> tradeDataDetail = new HashMap<>();

        Map<String, Object> BaseInfoDetail = new HashMap<>();
        BaseInfoDetail.put("TradeCode", "HCSPolicyStateQuery");
        BaseInfoDetail.put("RequestDate", "2016-12-22 15:47:30");
        BaseInfoDetail.put("TransNo", "009201706270919020019");
        BaseInfoDetail.put("UserCode", "test");
        BaseInfoDetail.put("Password", "1234@abcd");
        tradeDataDetail.put("BaseInfo", BaseInfoDetail);

        Map<String, Object> ContInfoDetail = new HashMap<>();
        ContInfoDetail.put("QueryType", "2");
        ContInfoDetail.put("LCContCount", "3");
        List<Map<String, Object>> LCContList = new ArrayList<>();
        Map<String, Object> LCContDetail = new HashMap<>();
        LCContDetail.put("QueryNo", "11140399001600002666");
        LCContList.add(LCContDetail);
        Map<String, Object> LCContDetail1 = new HashMap<>();
        LCContDetail1.put("QueryNo", "11140399001600002478");
        LCContList.add(LCContDetail1);
        Map<String, Object> LCContDetail2 = new HashMap<>();
        LCContDetail2.put("QueryNo", "11140399001633332478");
        LCContList.add(LCContDetail2);
        ContInfoDetail.put("LCCont", LCContList);
        tradeDataDetail.put("ContInfo", ContInfoDetail);

        Map<String, Object> tradeData = new HashMap<>();
        tradeData.put("TradeData", tradeDataDetail);
        System.out.println(JSON.toJSON(tradeData));
    }

}
