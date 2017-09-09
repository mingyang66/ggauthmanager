package extensions;

import play.templates.JavaExtensions;
/**
 * 
 * @author  模板函数扩展类
 *
 */
public class NumberExtension extends JavaExtensions{
	public static int parseInt(String number) {
	     return Integer.parseInt(number);
	  }
}
