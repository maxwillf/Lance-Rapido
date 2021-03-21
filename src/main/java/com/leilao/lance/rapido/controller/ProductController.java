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
       return productService.saveBid(bid);
    }
    @PostMapping("/product/comment")
    public Product saveComment(@RequestBody Comment comment){
        return productService.saveComment(comment);
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
    /*@GetMapping("/product/bought-items/{userId}")
    public List<Product> findActiveProductsByUserId(@PathVariable(value = "userId") Integer userId){
    	return productService.findActiveProductsByUserId(userId);
    }*/
    
    // Finds the highest product bid
    /*@GetMapping("/product/highest-bid")
    public List<Product> findActiveProductsByUserId(@PathVariable(value = "userId") Integer userId){
    	return productService.findActiveProductsByUserId(userId);*/

}
