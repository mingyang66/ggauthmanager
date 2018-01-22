package common.lang;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.ObjectUtils;

public class ObjectUtilTest {

	/**
	 * 这个方法返回不变的值，这可以阻止编译器javac重新编译
	 * 这种方法任何关联这个字段的jar包不需要重新编译，即使字段的值在将来改变
	 */
	public final static int MAGIC_INT = ObjectUtils.CONST(12);
	public final static long MAGIC_LONG = ObjectUtils.CONST(123L);
	public final static float MAGIC_FLOAT = ObjectUtils.CONST(123.0f);
	public final static double MAGIC_DOUBLE = ObjectUtils.CONST(1.0);
	public final static String MAGIC_STR = ObjectUtils.CONST("asfd");
	public final static char MAGIC_CHAR = ObjectUtils.CONST('a');
	public final static byte MAGIC_BYTE = ObjectUtils.CONST((byte) 127);
	public final static boolean MAGIC_FLAG = ObjectUtils.CONST(true);
	//不能小于-128或者大于127
	public final static byte MAGIC_BYTE1 = ObjectUtils.CONST_BYTE(127);
	//不能小于 -32768并且大于32767
	public final static short MAGIC_SHORT = ObjectUtils.CONST_SHORT(32767);
	public final static short MAGIC_SHORT1 = ObjectUtils.CONST((short) 123);
	
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
		
		/**
		 * 累加对象产生的toString
		 * 两个参数任意一个为null都会抛出空指针异常
		 * buffer-要追加的缓冲区
		 * object-要创建对象的toString
		 */
		StringBuffer buffer = new StringBuffer();
		ObjectUtils.identityToString(buffer, "");
		ObjectUtils.identityToString(buffer, Boolean.TRUE);
		ObjectUtils.identityToString(buffer, 12);
//		System.out.println(buffer);
		/**
		 * 累加对象产生的toString,如果一个对象没有实现自己的toString方法将使用Object对象的toString
		 * 两个参数任意一个为null都会抛出空指针异常
		 * builder-要追加的缓冲区
		 * object-要创建对象的toString
		 */
		StringBuffer builder = new StringBuffer();
		ObjectUtils.identityToString(builder, "");
		ObjectUtils.identityToString(builder, Boolean.FALSE);
//		System.out.println(builder);
		/**
		 * 该方法是对NULL安全的比较方法，null是被假定为一个小于非空的值
		 * c1-第一个被比较的值，可能为null
		 * c2-第二个被比较的值，可能为null
		 */
		int result = ObjectUtils.compare(12, null);
//		System.out.println(result);
		/**
		 * 该方法是对NULL安全的比较方法，null是被假定为一个小于非空的值
		 * c1-第一个被比较的值，可能为null
		 * c2-第二个被比较的值，可能为null
		 * nullGreater-如果为true,null被认为是一个大于非空的值或者false，null被认为是小于非空的值
		 */
		result = ObjectUtils.compare(null, 23, false);
		
		
		System.out.println(result);
		
	}
}
