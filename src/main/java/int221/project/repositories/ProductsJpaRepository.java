package int221.project.repositories;


import org.springframework.data.jpa.repository.JpaRepository;

import int221.project.models.Products;

public interface ProductsJpaRepository extends JpaRepository<Products, Integer>{

}