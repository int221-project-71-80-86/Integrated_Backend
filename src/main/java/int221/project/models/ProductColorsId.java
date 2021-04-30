package int221.project.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class ProductColorsId implements Serializable{

	private static final long serialVersionUID = 1L;

	@Column(name = "productcode")
	private Integer productcode;
	@Column(name = "colorid")
	private Integer colorid;
	
	public ProductColorsId() {
	}
	
	public ProductColorsId(Integer productcode, Integer colorid) {
		this.productcode = productcode;
		this.colorid = colorid;
	}
	public Integer getProductcode() {
		return productcode;
	}
	public void setProductcode(Integer productcode) {
		this.productcode = productcode;
	}
	public Integer getColorid() {
		return colorid;
	}
	public void setColorid(Integer colorid) {
		this.colorid = colorid;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + colorid;
		result = prime * result + productcode;
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
		if (productcode != other.productcode)
			return false;
		return true;
	}

	
}
