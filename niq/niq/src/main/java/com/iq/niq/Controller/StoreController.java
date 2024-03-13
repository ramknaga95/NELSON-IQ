package com.iq.niq.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.iq.niq.bean.Product;
import com.iq.niq.bean.Shopper;
import com.iq.niq.service.ProductAndShelfService;


@RestController
@RequestMapping("/store")
public class StoreController {

	@Autowired
	private ProductAndShelfService productAndShelfService;

	@PostMapping("/product")
	public void addProductDetails(@RequestBody Product product) {
		productAndShelfService.addProduct(product);

	}
	
	@PostMapping("/shopper")
	public void addshopperDetails(@RequestBody Shopper shopper) {
		productAndShelfService.addshopperDetails(shopper);

	}

}
