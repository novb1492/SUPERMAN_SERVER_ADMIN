package com.kimcompany.jangbogbackendver2.product;

import com.kimcompany.jangbogbackendver2.product.Dto.TryInsertDto;
import com.kimcompany.jangbogbackendver2.product.Service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @RequestMapping(value = "/user/product/save",method = RequestMethod.POST)
    public ResponseEntity<?>save(@Valid @RequestBody TryInsertDto tryInsertDto){
        return null;
    }
}
