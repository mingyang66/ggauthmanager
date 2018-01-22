package common.lang;

import org.apache.commons.lang3.ArrayUtils;

public class ArrayUtilTest {

	public static void main(String[] args) {
		boolean[] array = {true, false};
		boolean[] array_copy = ArrayUtils.add(array, false);
		
		byte[] bytes = {0, 2};
		byte[] byte_copy = ArrayUtils.add(bytes, (byte)1);
		
		char[] chars = {'w','w'};
		char[] char_copy = ArrayUtils.add(chars, '5');
		
		double[] doubles = {2.2};
		double[] double_copy = ArrayUtils.add(doubles, 2);
		System.out.println(double_copy);
	}
}
