package com.iq.niq.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.iq.niq.entity.ProductEntity;

public interface ProductRepo extends JpaRepository<ProductEntity, String> {

	@Query("SELECT DISTINCT p FROM ProductEntity p INNER JOIN ShelfEntity s ON p.productId = s.product.productId  "
			+ "WHERE (s.shopperId = ?1) " + "AND (?2 IS NULL OR p.category = ?2) "
			+ "AND (?3 IS NULL OR p.brand = ?3) " + "ORDER BY p.productId " + "LIMIT ?4")
	List<ProductEntity> findByShopperIdWithFiltersAndLimit(String shopperId, String category, String brand, int limit);

}
