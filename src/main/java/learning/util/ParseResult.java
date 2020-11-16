package learning.util;

import java.util.Map;

public class ParseResult {

	/**
	 * 自定义解析map对象
	 * @author licy
	 * @param map
	 * @param result
	 * @param list
	 * @return
	 * 2015-11-4 16:29:58
	 */
	@SuppressWarnings({ "unchecked", "unused"})
	public static Object parseHttpResult(Map<String , Object> map, String result, String list){
		Object object1 = null; 
		
		Object objResult = null;
		if(result != null){
			object1 = map.get(result);
			objResult = object1;
			if(list != null){
				return ((Map<String, Object>) object1).get(list);
			}
			if(list == null){
				return object1;
			}
		}
		if(list != null){
			return map.get(list);
		}
		return objResult;
	}
	
}
