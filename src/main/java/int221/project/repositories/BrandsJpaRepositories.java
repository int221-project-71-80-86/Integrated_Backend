package int221.project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import int221.project.models.Brands;

public interface BrandsJpaRepositories extends JpaRepository<Brands, Integer>{
	
}
