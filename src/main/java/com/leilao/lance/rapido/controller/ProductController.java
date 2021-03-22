package com.leilao.lance.rapido.controller;



import com.leilao.lance.rapido.model.Bid;
import com.leilao.lance.rapido.model.Comment;
import com.leilao.lance.rapido.model.Product;
import com.leilao.lance.rapido.service.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {
    private final ProductService productService;


    public ProductController(ProductService productService) { this.productService = productService;}

    @PostMapping("/product")
    Product newProduct(@RequestBody Product newProduct){
        return productService.saveProduct(newProduct);
    }

    @PostMapping("/product/bid")
    public Product saveBid(@RequestBody  Bid bid){
       return productService.addBid(bid);
    }
    @PostMapping("/product/comment")
    public Product saveComment(@RequestBody Comment comment){
        return productService.saveComment(comment);
    }
    
    @PutMapping("product/remove/user-{userId}/product-{productId}")
    public Product setProductToInactive(@RequestBody Integer productId) {
    	Product product = productService.findActiveProductById(productId);
    	if (product.getUser().getId().equals(productId)) {
    		product.setActive(false);
    		return productService.saveProduct(product);
    	}
    	return null;
    }
    
    // Finds the products the user is selling
    @GetMapping("/product/seller-items/{userId}")
    public List<Product> findByUserId(@PathVariable(value = "userId") Integer userId){
        return productService.findByUserId(userId);
    }
    
    // Finds the active products for which an user is bidding
    @GetMapping("/product/buying-items/{userId}")
    public List<Product> findActiveBidsByUserId(@PathVariable(value = "userId") Integer userId){
        return productService.findActiveBidsByUserId(userId);
    }

    // Finds the active products being sold
    @GetMapping("/catalog")
    public List<Product> getCatalog(){
        return productService.getCatalog();
    }
    
    // Finds the sold products from an user
    @GetMapping("/product/sold-items/{userId}")
    public List<Product> findSoldProductsByUserId(@PathVariable(value = "userId") Integer userId){
    	return productService.findSoldProductsByUserId(userId);
    }
    
    // Finds the products an user is selling
    @GetMapping("/product/selling-items/{userId}")
    public List<Product> findActiveProductsByUserId(@PathVariable(value = "userId") Integer userId){
    	return productService.findActiveProductsByUserId(userId);
    }
    
    // Finds the products an user bought
    @GetMapping("/product/bought-items/{userId}")
    public List<Product> findUserBoughtProducts(@PathVariable(value = "userId") Integer userId){
    	return productService.findUserBoughtProducts(userId);
    }
    
    // Finds the highest product bid
    @GetMapping("/product/highest-bid/{productId}")
    public Bid findActiveProductHighestBid(Integer productId){
    	Product product = productService.findActiveProductById(productId);
    	return product.getHighestBid();
    }

}
