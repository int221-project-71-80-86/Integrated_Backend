package int221.project.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import int221.project.exceptions.DataRelatedException;
import int221.project.exceptions.ExceptionResponse.ERROR_CODE;
import int221.project.models.*;
import int221.project.repositories.*;
import int221.project.services.FileStoreServices;

@RestController
@CrossOrigin("http://localhost:8081")
public class ProductsRestController {

	@Autowired ProductsJpaRepository prodRepo;
	@Autowired ProdColorsJpaRepository pcRepo;
	@Autowired ColorsJpaRepositories colorRepo;
	@Autowired FileStoreServices storeService;

	/**************
	 * Get Method *
	 **************/
	@GetMapping("/products/{code}")
	public Products show(@PathVariable int code) {
		Products p = prodRepo.findById(code).orElse(null);
		if (p == null) {
			throw new DataRelatedException(ERROR_CODE.PRODUCT_DOESNT_FOUND,
					"Product code: '" + code + "' does not exist.");
		}
		return p;
	}

	@GetMapping("/products")
	public Page<Products> showAllImplementsPaging(@RequestParam(defaultValue = "0") int pageNo,
			@RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue = "productcode") String sortBy) {
		return prodRepo.findAll(PageRequest.of(pageNo, size, Sort.by(sortBy)));
	}

//	@GetMapping("/products")
//	public List<Products> showAll() {
//		return prodRepo.findAll();
//	}

	/*******************
	 * Post&Put Method *
	 *******************/

//	@PostMapping("/save")
//	public Products addProducts(@RequestBody Products product) {
//		checkColors(product);
//		product.setProductcode(0);
//		addPrimaryKey(product);
//		return prodRepo.save(product);
//	}
	
	@PostMapping(value = "/save", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
	public Products addProductsV2(@RequestParam(value = "file", required = true)MultipartFile photo, 
									@RequestPart Products product) {
		String filename = storeService.save(photo);
		product.setImage(filename);
		checkColors(product);
		product.setProductcode(0);
		addPrimaryKey(product);
		return prodRepo.save(product);
	}

//	@PutMapping("/edit")
//	public Products editProducts(@RequestBody Products products) {
//		if (prodRepo.findById(products.getProductcode()).orElse(null) == null) {
//			throw new DataRelatedException(ERROR_CODE.PRODUCT_DOESNT_FOUND,
//					"Product with code: " + products.getProductcode() + " does not exists.");
//		}
//		checkColors(products);
//		addPrimaryKey(products);
//		resetProductcode(products.getProductcode());
//		return prodRepo.save(products);
//	}
	
	@PutMapping(value = "/edit", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
	public Products editProductsV2(@RequestParam(value = "file", required = false)MultipartFile photo, 
			@RequestPart Products product) {
		Products oldProd = prodRepo.findById(product.getProductcode()).orElse(null);
		if (oldProd == null) {
			throw new DataRelatedException(ERROR_CODE.PRODUCT_DOESNT_FOUND,
					"Product with code: " + product.getProductcode() + " does not exists.");
		}
		// If send photo. delete old and add new. If not just use old.
		if(photo != null) {
			storeService.deleteOne(oldProd.getImage());
			String photoname = storeService.save(photo);
			product.setImage(photoname);
		} else {
			product.setImage(oldProd.getImage());
		}
		// Check and save
		checkColors(product);
		addPrimaryKey(product);
		resetProductcode(product.getProductcode());
		return prodRepo.save(product);
	}

	private void resetProductcode(Integer productcode) {
		pcRepo.deleteProductByProductcode(productcode);
	}

	private void addPrimaryKey(Products product) {
		for (Productcolors p : product.getProductcolors()) {
			p.setProductcolors(new ProductColorsId(product.getProductcode(), p.getColors().getColorid()));
			p.setProducts(product);
		}
	}
	
	private void checkColors(Products product) {
		if (product.getProductcolors().isEmpty()) {
			throw new DataRelatedException(ERROR_CODE.COLOR_DOESNT_FOUND, "Product does not contain any color!");
		}
	}

	/*****************
	 * Delete Method *
	 *****************/

	@DeleteMapping("/delete/{productcode}")
	public Products removeProducts(@PathVariable Integer productcode) {
		Products delProd = prodRepo.findById(productcode).orElse(null);
		prodRepo.deleteById(productcode);
		return delProd;
	}

}
