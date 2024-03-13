package com.iq.niq.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.iq.niq.bean.Product;
import com.iq.niq.service.ProductAndShelfService;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;

@RestController
@RequestMapping("/shopper")
public class ShopperController {
	@Autowired
	private ProductAndShelfService productAndShelfService;

	@GetMapping("/product")
	public ResponseEntity<List<Product>> addProductDetails(@RequestParam(name = "shopperId") @NotNull String shopperId,
			@RequestParam(name = "category", required = false) String category,
			@RequestParam(name = "brand", required = false) String brand,
			@RequestParam(name = "limit", defaultValue = "10") @Max(100) Integer limit) {

		List<Product> products = productAndShelfService.getProductsByShopper(shopperId, category, brand, limit);
		return new ResponseEntity<>(products, HttpStatus.OK);

	}

}
