package com.leilao.lance.rapido.controller;



import com.leilao.lance.rapido.model.*;
import com.leilao.lance.rapido.service.ProductService;
import com.leilao.lance.rapido.strategy.SaveProductStrategy.EletronicoSaveProductStrategy;
import com.leilao.lance.rapido.strategy.SaveProductStrategy.MovelSaveProductStrategy;
import com.leilao.lance.rapido.strategy.SaveProductStrategy.SaveProductStrategyBase;
import com.leilao.lance.rapido.strategy.SaveProductStrategy.VeiculoSaveProductStrategy;
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
        return productService.getFullCatalog();
    }

    // Finds the active products being sold
    @GetMapping("/catalog/{type}")
    public List<Product> getCatalog(@PathVariable(value = "type") String type){
        return productService.getCatalogByType(type);
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

    @GetMapping("/product/bought-items/{userId}/{type}")
    public List<Product> findUserBoughtProductsByType(@PathVariable(value = "userId") Integer userId,@PathVariable(value = "type") String type){
        return productService.findUserBoughtProductsByType(userId,type);
    }

    // Finds the highest product bid
    @GetMapping("/product/highest-bid/{productId}")
    public Bid findActiveProductHighestBid(@PathVariable(value = "productId") Integer productId){
        Product product = productService.findActiveProductById(productId);
        return product.getHighestBid();
    }
}
