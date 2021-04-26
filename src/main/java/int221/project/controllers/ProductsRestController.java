package int221.project.controllers;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import int221.project.exceptions.DataRelatedException;
import int221.project.exceptions.ExceptionResponse.ERROR_CODE;
import int221.project.models.*;
import int221.project.repositories.BrandsJpaRepositories;
import int221.project.repositories.ColorsJpaRepositories;
import int221.project.repositories.ProdColorsJpaRepository;
import int221.project.repositories.ProductsJpaRepository;


@RestController
public class ProductsRestController {
	
	@Autowired ProductsJpaRepository prodRepo;
	@Autowired BrandsJpaRepositories brandRepo;
	@Autowired ColorsJpaRepositories colorRepo;
	@Autowired ProdColorsJpaRepository pcRepo;
	
	// Test Mapping //
    @GetMapping("/products/{code}")
    public Products show(@PathVariable String code) {
    	Products p = prodRepo.findById(code).orElse(null);
    	if(p == null) {
    		throw new DataRelatedException(ERROR_CODE.PRODUCT_DOESNT_FOUND, "Product code: '"+code+"' does not exist.");
    	}
        return p;
    }
    
    @GetMapping("/products")
    public List<Products> showAll(){
    	return prodRepo.findAll();
    }
    
    // Not yet implemented //

//    @PutMapping
//    public Products updateProducts() {
//    	return null
//    }

    
    @GetMapping("/brands")
    public List<Brands> getBrands() {
    	return brandRepo.findAll();
    }
    
    @GetMapping("/colors")
    public List<Colors> getColors() {
    	return colorRepo.findAll();
    }
    
//    @GetMapping("/colorss/{product}")
//    public Ob getColorss(@PathVariable String product) {
//    	return pcRepo.findColorsByProductCode(product);
//    }
//    
    
    @GetMapping("/productcolors/{productcode}")
    public ProductsForShow getProducts(@PathVariable String productcode) {
    	return getProductForShow(productcode);
    }
    
    public ProductsForShow getProductForShow(String productcode) {
    	Products product = prodRepo.findById(productcode).orElse(null);
    	if(product == null) {
    		throw new DataRelatedException(ERROR_CODE.PRODUCT_DOESNT_FOUND, "Product code: '"+productcode+"' does not exist.");
    	} 
    	List<Integer> colorsId = pcRepo.findColorsByProductCode(productcode);
    	
    	List<Colors> colors = new ArrayList<>();
    	for (Iterator<Integer> i = colorsId.iterator(); i.hasNext();) {
			Colors temp = colorRepo.findById((Integer)i.next()).orElse(null);
			if (temp == null) {
	    		throw new DataRelatedException(ERROR_CODE.COLOR_DOESNT_FOUND, "Does not find the color "+temp.getName()+".");
	    	}
			colors.add(temp);
		}
    	if(colors.size() < 1) {
    		throw new DataRelatedException(ERROR_CODE.NOT_HAVE_ANY_COLORS, "Does not find any colors for the product code: "+productcode);
    	}
    	ProductsForShow pfs = new ProductsForShow(product, colors);
    	return pfs;
    }
    
    @GetMapping("/productcolors")
    public List<ProductsForShow> getAllProduct() {
    	List<Products> p = prodRepo.findAll();
    	ListIterator<Products> iter = p.listIterator();
    	List<ProductsForShow> pfs = new ArrayList<>();
    	while(iter.hasNext()) {
    		pfs.add(getProductForShow(iter.next().getProductcode()));
    	}
    	return pfs;
    }
    
    @PostMapping("/add")
    public List<Colors> addProducts(@RequestBody ProductsForShow pfs) {
    	if(prodRepo.findById(pfs.getProduct().getProductcode()).orElse(null) != null) {
    		throw new DataRelatedException(ERROR_CODE.PRODUCT_ALREADY_EXIST, "Product with code: "+pfs.getProduct().getProductcode()+" is already exists.");
    	}
    	prodRepo.save(pfs.getProduct());
    	ListIterator<Colors> iter = pfs.getColors().listIterator();
    	while(iter.hasNext()) {
    		Productcolors temp = new Productcolors();
    		temp.setProductcode(pfs.getProduct().getProductcode());
    		temp.setColorid(iter.next().getColorid());
    		pcRepo.save(temp);
    	}
    	return pfs.getColors();
    }
    
  
  @DeleteMapping("/delete/{productcode}")
  public Products removeProducts(@PathVariable String productcode) {
	  pcRepo.deleteProductByProductcode(productcode);
	  prodRepo.deleteById(productcode);
	  return prodRepo.findById(productcode).orElse(null);
  }
    
}
    
