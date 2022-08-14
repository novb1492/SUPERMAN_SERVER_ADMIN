package com.kimcompany.jangbogbackendver2.product.Service;

import com.kimcompany.jangbogbackendver2.product.Repo.ProductRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductSelectService productSelectService;
    private final ProductRepo productRepo;

}
