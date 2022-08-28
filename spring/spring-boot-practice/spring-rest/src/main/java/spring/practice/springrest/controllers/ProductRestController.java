package spring.practice.springrest.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import spring.practice.springrest.entities.Product;
import spring.practice.springrest.repositories.ProductRepository;

import javax.validation.Valid;
import java.util.List;

@RestController
//@RequestMapping(value = "/products/")
public class ProductRestController {
    public static final Logger logger = LoggerFactory.getLogger(ProductRestController.class);
    @Autowired
    private ProductRepository productRepository;

    @RequestMapping(value = "/products/", method = RequestMethod.GET)
    public List<Product> getProducts(){
        logger.info("Searching all the products");
        return productRepository.findAll();
    }

    @RequestMapping(value = "/products/{id}", method = RequestMethod.GET)
    public Product getProduct(@PathVariable("id") Long id) {
        return productRepository.findById(id).get();
    }

    @RequestMapping(value = "/products/", method = RequestMethod.POST)
    public Product createProduct(@Valid @RequestBody Product product){
        return productRepository.save(product);
    }

    @RequestMapping(value = "/products/", method = RequestMethod.PUT)
    public Product updateProduct(@Valid @RequestBody Product product){
        return productRepository.save(product);
    }

    @RequestMapping(value = "/products/{id}", method = RequestMethod.DELETE)
    public void deleteProduct(@PathVariable("id") Long id){
        productRepository.deleteById(id);
    }
}
