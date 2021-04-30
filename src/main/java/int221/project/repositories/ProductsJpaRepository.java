package int221.project.repositories;


import org.springframework.data.jpa.repository.JpaRepository;

import int221.project.models.Products;

public interface ProductsJpaRepository extends JpaRepository<Products, Integer>{
	
//	@Query("SELECT p FROM Products p WHERE p.name = ?1")
//	Products findProductByProductName(String name);

}