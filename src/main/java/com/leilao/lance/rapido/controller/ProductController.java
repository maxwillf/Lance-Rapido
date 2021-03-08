package com.leilao.lance.rapido.controller;



import com.leilao.lance.rapido.model.Bid;
import com.leilao.lance.rapido.model.Comment;
import com.leilao.lance.rapido.model.Product;
import com.leilao.lance.rapido.service.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
    @GetMapping("/product/seller-items/{userId} ")
    public List<Product> findByUserId(@PathVariable(value = "userId") Integer userId){
        return productService.findByUserId(userId);
    }
    // Finds the products for which a user has done a bid
    @GetMapping("/product/buyer-items/{userId} ")
    public List<Product> findByBidsUserId(@PathVariable(value = "userId") Integer userId){
        return productService.findByBidsUserId(userId);
    }

    @GetMapping("/catalog")
    public List<Product> getCatalog(){
        return productService.getCatalog();
    }

}
