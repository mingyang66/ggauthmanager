
package ggauth.shiro.user.common;

import ggauth.shiro.user.model.User;

import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

/**
 * @Description:TODO
 * @version 1.0
 * @since JDK1.7
 * @author yaomy
 * @company xxxxxxxxxxxxxx
 * @copyright (c) 2017 yaomy Co'Ltd Inc. All rights reserved.
 * @date 2017年9月18日 下午4:21:40
 */
public class PasswordHelper {
	//用户生成随机数
	private RandomNumberGenerator randomNumberGenerator = new SecureRandomNumberGenerator();
	
	private String algorithmName = "md5";//加密算法名
	private int hashIterations = 2;//hash值的迭代次数
	
	public void encryptPassword(User user){
		user.setSalt(randomNumberGenerator.nextBytes().toHex());
		String newPassword = new SimpleHash(algorithmName,//加密算法名
											user.getPassword(), //需要被加密的字符串
											ByteSource.Util.bytes(user.getCredentialsSalt()),//盐salt=username+salt
											hashIterations).toHex();
		user.setPassword(newPassword);
	}
}
