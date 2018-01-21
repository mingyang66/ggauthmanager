package common.lang;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.ObjectUtils;

public class ObjectUtilTest {

	public static void main(String[] args) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("username", ObjectUtils.NULL);
		
//		System.out.println(ObjectUtils.NULL == map.get("username"));
//		System.out.println(ObjectUtils.NULL == map.get("username1"));
//		System.out.println(null == map.get("username1"));
		/**
		 * 如果一个类没有重写toString方法本身，将会通过Object类的toString方法获取对象的字符串对象，
		 */
		String s1 = ObjectUtils.identityToString(null);
		String s2 = ObjectUtils.identityToString("");
		String s3 = ObjectUtils.identityToString(Boolean.TRUE);
//		System.out.println(s1);
//		System.out.println(s2);
//		System.out.println(s3);
		
		
	}
}
