package com.kimcompany.jangbogbackendver2.ProductKind;

import com.kimcompany.jangbogbackendver2.ProductKind.Service.ProductKindSelectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ProductCategoryController {
    private final ProductKindSelectService productKindSelectService;

    @RequestMapping(value = "/user/category/list",method = RequestMethod.GET)
    public ResponseEntity<?> getCategorys(){
        return ResponseEntity.ok().body(productKindSelectService.selectAll());
    }
}
