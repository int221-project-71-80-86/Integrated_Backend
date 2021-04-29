package int221.project.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class ProductColorsId implements Serializable{
	@Column(name = "productcode")
	private String productcode;
	@Column(name = "colorid")
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
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + colorid;
		result = prime * result + ((productcode == null) ? 0 : productcode.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProductColorsId other = (ProductColorsId) obj;
		if (colorid != other.colorid)
			return false;
		if (productcode == null) {
			if (other.productcode != null)
				return false;
		} else if (!productcode.equals(other.productcode))
			return false;
		return true;
	}
	
	
	
}
