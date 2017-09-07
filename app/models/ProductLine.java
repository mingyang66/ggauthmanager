
package models;
/**
 * @Description:产品线
 * @version 1.0
 * @since JDK1.7
 * @author yaomy
 * @company xxxxxxxxxxxxxx
 * @copyright (c) 2017 yaomy Co'Ltd Inc. All rights reserved.
 * @date 2017年9月7日 下午7:06:43
 */
public class ProductLine {

	private Long id;
	private Integer product_line;
	private String product_name;
	private String product_desc;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Integer getProduct_line() {
		return product_line;
	}
	public void setProduct_line(Integer product_line) {
		this.product_line = product_line;
	}
	public String getProduct_name() {
		return product_name;
	}
	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}
	public String getProduct_desc() {
		return product_desc;
	}
	public void setProduct_desc(String product_desc) {
		this.product_desc = product_desc;
	}
	
}
