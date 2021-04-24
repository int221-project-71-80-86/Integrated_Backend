package int221.project.models;

import java.util.List;

public class ProductsForShow {
	private Products product;
	private List<Colors> colors;
	
	public ProductsForShow(Products product, List<Colors> colors) {
		this.product = product;
		this.colors = colors;
	}

	public Products getProduct() {
		return product;
	}

	public void setProduct(Products product) {
		this.product = product;
	}

	public List<Colors> getColors() {
		return colors;
	}

	public void setColors(List<Colors> colors) {
		this.colors = colors;
	}
	
}
