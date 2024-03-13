package com.iq.niq.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.iq.niq.bean.Product;
import com.iq.niq.bean.ProductFilter;
import com.iq.niq.bean.Shelf;
import com.iq.niq.bean.Shopper;
import com.iq.niq.entity.ProductEntity;
import com.iq.niq.entity.ShelfEntity;
import com.iq.niq.repo.ProductRepo;
import com.iq.niq.repo.ShelfRepo;

@Service

public class ProductAndShelfService {

	private static final Logger log = LoggerFactory.getLogger(ProductAndShelfService.class);

	@Autowired
	private ShelfRepo shelfRepo;
	@Autowired
	private ProductRepo productRepo;

	public void addProduct(Product product) {
		ProductEntity productEntity = new ProductEntity();
		BeanUtils.copyProperties(product, productEntity);
		try {
			productRepo.save(productEntity);
		} catch (Exception e) {
			log.info("Problem ocuurent when saving product");
		}

	}

	public void addshopperDetails(Shopper shopper) {
		try {
			List<ShelfEntity> shelfEntityList = new ArrayList<>();
			for (Shelf shelf : shopper.getShelf()) {
				String productId = shelf.getProductId();
				if (productRepo.existsById(productId)) {
					ShelfEntity ShelfEntity = new ShelfEntity();
					ShelfEntity.setRelevancyScore(Double.parseDouble(shelf.getRelevancyScore()));
					ShelfEntity.setProduct(productRepo.findById(productId).get());
					ShelfEntity.setShopperId(shopper.getShopperId());
					shelfEntityList.add(ShelfEntity);
				}
			}
			if (!CollectionUtils.isEmpty(shelfEntityList)) {
				shelfRepo.saveAll(shelfEntityList);
			}

		} catch (Exception e) {
			log.info("Problem ocuurent when saving Shopper");
		}
	}

	public List<Product> getProductsByShopper(String shopperId,String category,String brand,int limit) {
		List<Product> productList = new ArrayList<>();
		List<ProductEntity> productEntityList = productRepo.findByShopperIdWithFiltersAndLimit(
				shopperId,category,brand,limit);
		if (!CollectionUtils.isEmpty(productEntityList)) {
			for (ProductEntity productEntity : productEntityList) {
				Product product = new Product();
				BeanUtils.copyProperties(productEntity, product);
				productList.add(product);

			}

		}
		return productList;
	}

}
