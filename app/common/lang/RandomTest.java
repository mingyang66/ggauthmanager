package common.lang;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;

public class RandomTest {

	public static void main(String[] args) {
		/**
		 * count 创建一个随机字符串，其长度是指定的字符数,字符将从参数的字母数字字符集中选择，如参数所示。
		 * letters true,生成的字符串可以包括字母字符
		 * numbers true,生成的字符串可以包含数字字符
		 */
		String random = RandomStringUtils.random(15, true, false);
		/**
		 * 创建一个随机字符串，其长度是指定的字符数。
		 * 将从所有字符集中选择字符
		 */
		random = RandomStringUtils.random(22);
		/**
		 * 创建一个随机字符串，其长度是指定的字符数。
		 * 字符将从字符串指定的字符集中选择，不能为空。如果NULL，则使用所有字符集。
		 */
		random = RandomStringUtils.random(15, "abcdefgABCDEFG123456789");
		
		/**
		 * 创建一个随机字符串，其长度是指定的字符数,字符将从参数的字母数字字符集中选择，如参数所示。
		 * count:计算创建的随机字符长度
		 * start:字符集在开始时的位置
		 * end:字符集在结束前的位置，必须大于65
		 * letters true,生成的字符串可以包括字母字符
		 * numbers true,生成的字符串可以包含数字字符
		 * 
		 */
		random = RandomStringUtils.random(1009, 5, 129, true, true);
		/**
		 * 产生一个长度为指定的随机字符串的字符数，字符将从拉丁字母（a-z、A-Z的选择）。
		 * count:创建随机字符串的长度
		 */
		random = RandomStringUtils.randomAlphabetic(15);
		/**
		 * 创建一个随机字符串，其长度介于包含最小值和最大最大值之间,，字符将从拉丁字母（a-z、A-Z的选择）。
		 * minLengthInclusive ：要生成的字符串的包含最小长度
		 * maxLengthExclusive ：要生成的字符串的包含最大长度
		 */
		random = RandomStringUtils.randomAlphabetic(2, 15);
		/**
		 * 创建一个随机字符串，其长度是指定的字符数，字符将从拉丁字母（a-z、A-Z）和数字0-9中选择。
		 * count ：创建的随机数长度
		 */
		random = RandomStringUtils.randomAlphanumeric(15);
		/**
		 * 创建一个随机字符串，其长度介于包含最小值和最大最大值之间,字符将从拉丁字母（a-z、A-Z）和数字0-9中选择。
		 * minLengthInclusive ：要生成的字符串的包含最小长度
		 * maxLengthExclusive ：要生成的字符串的包含最大长度
		 * 
		 */
		random = RandomStringUtils.randomAlphanumeric(5, 68);
		/**
		 * 创建一个随机字符串，其长度是指定的字符数，字符将从ASCII值介于32到126之间的字符集中选择（包括）
		 * count:随机字符串的长度
		 */
		random = RandomStringUtils.randomAscii(15);
		/**
		 * 创建一个随机字符串，其长度介于包含最小值和最大最大值之间,字符将从ASCII值介于32到126之间的字符集中选择（包括）
		 * minLengthInclusive ：要生成的字符串的包含最小长度
		 * maxLengthExclusive ：要生成的字符串的包含最大长度
		 */
		random = RandomStringUtils.randomAscii(15, 30);
		/**
		 * 创建一个随机字符串，其长度是指定的字符数，将从数字字符集中选择字符。
		 * count:生成随机数的长度
		 */
		random = RandomStringUtils.randomNumeric(15);
		/**
		 * 创建一个随机字符串，其长度介于包含最小值和最大最大值之间,将从数字字符集中选择字符.
		 * minLengthInclusive, 要生成的字符串的包含最小长度
		 * maxLengthExclusive 要生成的字符串的包含最大长度
		 */
		random = RandomStringUtils.randomNumeric(15, 20);
		System.out.println(random);
		/**
		 * 生成一个随机的布尔值
		 */
		boolean flag = RandomUtils.nextBoolean();
		System.out.println(flag);
		/**
		 * 创建一个bytes随机数组
		 */
		byte[] byt = RandomUtils.nextBytes(6);
		System.out.println(byt);
		/**
		 * 返回一个0 - Integer.MAX_VALUE的随机 整数
		 */
		int intt = RandomUtils.nextInt();
		/**
		 * 返回一个在指定区间内的整数
		 * startInclusive 可以返回的最小值必须是非负的
		 * endExclusive 上限（不包括）
		 */
		intt = RandomUtils.nextInt(20, 60);
		/**
		 * 返回一个在区间0 - Long.MAX_VALUE的long类型的数
		 */
		long lontt = RandomUtils.nextLong();
		/**
		 * 返回一个在指定区间的long类型的随机数
		 * startInclusive 可以返回的最小值必须是非负的
		 * endExclusive 上限（不包括）
		 */
		lontt = RandomUtils.nextLong(34, 68);
		/**
		 * 返回一个在区间0 - Double.MAX_VALUE double随机数
		 * 
		 */
		double dout = RandomUtils.nextDouble();
		/**
		 * 返回一个在指定区间的double随机数
		 * startInclusive 可以返回的最小值必须是非负的
		 * endExclusive 上限（不包括）
		 * 
		 */
		dout = RandomUtils.nextDouble(23.0, 34);
		/**
		 * 返回一个在0 - Float.MAX_VALUE之间的float类型随机数
		 */
		float flott = RandomUtils.nextFloat();
		/**
		 * 返回一个指定区间的float类型随机数
		 * startInclusive 可以返回的最小值必须是非负的
		 * endExclusive 上限（不包括）
		 */
		flott = RandomUtils.nextFloat(23, 56);
		System.out.println(flott);
	}
}
