package ru.geekbrains.market.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.market.dto.ProductDto;
import ru.geekbrains.market.exceptions.ResourceNotFoundException;
import ru.geekbrains.market.repositories.specifications.ProductSpecifications;
import ru.geekbrains.market.services.ProductService;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping
    public Page<ProductDto> findAllProducts(
            @RequestParam MultiValueMap<String, String> params,
            @RequestParam (name = "p", defaultValue = "1") Integer page) {
        if (page < 1) {page = 1;}
        return productService.findAll(ProductSpecifications.build(params), page, 5);
    }

    @GetMapping ("/{id}")
    public ProductDto findProductById(@PathVariable Long id) {
        return productService.findProductDtoById(id).orElseThrow(()-> new ResourceNotFoundException("Product" +
                "with id " + id + " doesn't exists"));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductDto saveNewProduct(@RequestBody ProductDto productDto) {
        return productService.saveOrUpdate(productDto);
    }

    @PutMapping
    public ProductDto updateProduct(@RequestBody ProductDto productDto) {
        return productService.saveOrUpdate(productDto);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id) {
        productService.deleteById(id);
    }
}
