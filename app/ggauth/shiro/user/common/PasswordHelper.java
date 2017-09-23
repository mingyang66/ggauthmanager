
package ggauth.shiro.user.common;

import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.Sha256Hash;

import ggauth.shiro.user.model.User;

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
	
	public void encryptPassword(User user){
		String salt = randomNumberGenerator.nextBytes().toHex();
		user.setSalt(salt);
		
		String newPassword = new Sha256Hash(user.getPassword(), salt, 1024).toBase64();
		user.setPassword(newPassword);
	}
	public String encryptPassword(String password, String salt) {
		return new Sha256Hash(password, salt, 1024).toBase64();
	}
}
