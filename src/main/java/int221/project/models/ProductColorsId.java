package int221.project.models;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class ProductColorsId implements Serializable{
	private String productcode;
	private int colorid;
	
	public String getProductcode() {
		return productcode;
	}
	public void setProductcode(String productcode) {
		this.productcode = productcode;
	}
	public int getColorid() {
		return colorid;
	}
	public void setColorid(int colorid) {
		this.colorid = colorid;
	}
	
	
	
}
