package com.leilao.lance.rapido.controller;



import com.leilao.lance.rapido.model.Product;
import com.leilao.lance.rapido.service.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class ProductController {
    private final ProductService productService;


    public ProductController(ProductService productService) { this.productService = productService;}

    @PostMapping("/product")
    Product newProduct(@RequestBody Product newProduct){
        return productService.saveProduct(newProduct);
    }

//    @GetMapping("/{id}")
//    public Optional<Product> getProductByID(@PathVariable int productID) {
//        return productService.findProduct(productID);
//    }



}
