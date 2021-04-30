package int221.project.repositories;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import int221.project.models.Colors;
import int221.project.models.ProductColorsId;
import int221.project.models.Productcolors;

public interface ProdColorsJpaRepository extends JpaRepository<Productcolors, ProductColorsId>{

	@Transactional
	@Modifying
	@Query("DELETE FROM Productcolors pc WHERE pc.productcolors.productcode = ?1")
	void deleteProductByProductcode(Integer productCode);
	
	@Query("SELECT pc FROM Productcolors pc WHERE pc.productcolors.productcode = ?1")
	List<Productcolors> findProductcolorsByProductcode(Integer productCode);
	
}
