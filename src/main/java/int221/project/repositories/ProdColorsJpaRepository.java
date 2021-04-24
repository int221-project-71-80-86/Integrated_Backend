package int221.project.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import int221.project.models.Colors;
import int221.project.models.Productcolors;

public interface ProdColorsJpaRepository extends JpaRepository<Productcolors, String>{

	/* error: mapping try fixing or change method */
//	@Query("SELECT c FROM productcolors pc JOIN colors c ON pc.colorid = c.colorid WHERE pc.productcode = ?1")
//	List<Colors> findColorsByProductCode(String productCode);
	
	@Query("SELECT pc.colorid FROM Productcolors pc WHERE productcode = ?1")
	List<Integer> findColorsByProductCode(String productCode);

	
}
