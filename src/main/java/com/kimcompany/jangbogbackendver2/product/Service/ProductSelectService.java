package com.kimcompany.jangbogbackendver2.product.Service;

import com.kimcompany.jangbogbackendver2.product.Repo.ProductRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProductSelectService {
    private final ProductRepo productRepo;
}
