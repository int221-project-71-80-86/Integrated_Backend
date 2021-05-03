package int221.project.models;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import java.util.TreeSet;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class Products implements Comparable<Products>,Serializable
{
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "productcode")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer productcode; // Id or Primary Key

    private String name;
    private String description;
    private Double price;
    
    @Temporal(TemporalType.DATE)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date date;
    private String image;
    private Integer warranty;
    private Integer brandid;

    @OneToMany(mappedBy = "products", fetch=FetchType.LAZY, cascade = {CascadeType.ALL})
    Set<Productcolors> productcolors = new TreeSet<>();
    
	public Set<Productcolors> getProductcolors() {
		return productcolors;
	}

	public void setProductcolors(Set<Productcolors> productcolors) {
		this.productcolors = productcolors;
	}

	public Integer getProductcode() {
		return productcode;
	}

	public void setProductcode(Integer productcode) {
		this.productcode = productcode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Integer getWarranty() {
		return warranty;
	}

	public void setWarranty(Integer warranty) {
		this.warranty = warranty;
	}

	public Integer getBrandid() {
		return brandid;
	}

	public void setBrandid(Integer brandid) {
		this.brandid = brandid;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
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
		Products other = (Products) obj;
		if (productcode == null) {
			if (other.productcode != null)
				return false;
		} else if (!productcode.equals(other.productcode))
			return false;
		return true;
	}

	@Override
	public int compareTo(Products o) {
		return this.getProductcode() - o.getProductcode();
	}
    
	}
