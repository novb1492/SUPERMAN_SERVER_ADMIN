package com.kimcompany.jangbogbackendver2.Product.Service;

import com.kimcompany.jangbogbackendver2.ProductEvent.Service.ProductEventService;
import com.kimcompany.jangbogbackendver2.ProductKind.Service.ProductKindSelectService;
import com.kimcompany.jangbogbackendver2.Store.StoreSelectService;
import com.kimcompany.jangbogbackendver2.Util.UtilService;
import com.kimcompany.jangbogbackendver2.Product.Dto.TryInsertDto;
import com.kimcompany.jangbogbackendver2.Product.Model.ProductEntity;
import com.kimcompany.jangbogbackendver2.Product.Repo.ProductRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.kimcompany.jangbogbackendver2.Text.BasicText.cantFindStoreMessage;
import static com.kimcompany.jangbogbackendver2.Util.UtilService.getLoginUserId;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductSelectService productSelectService;
    private final ProductRepo productRepo;
    private final ProductEventService productEventService;
    private final ProductKindSelectService productKindSelectService;

    @Transactional(rollbackFor = Exception.class)
    public void save(TryInsertDto tryInsertDto){
        if(UtilService.confirmPrice(tryInsertDto.getPrice())){
            throw new IllegalArgumentException("금액에 잘못된값이 있습니다");
        }
        long storeId = Long.parseLong(tryInsertDto.getId());
        confirmExist(storeId, tryInsertDto.getName());
        confirmCategory(Long.parseLong(tryInsertDto.getCategory()));
        ProductEntity productEntity = TryInsertDto.dtoToEntity(tryInsertDto);
        productRepo.save(productEntity);
        productEventService.save(productEntity.getId(), tryInsertDto.getEvents());
    }
    private void confirmExist(long storeId,String productName){
        if(productSelectService.exist(productName,storeId)){
            throw new IllegalArgumentException("이미 등록 되어있는 상품입니다");
        }
    }
    private void confirmCategory(long categoryId){
        if(productKindSelectService.exist(categoryId)){
            throw new IllegalArgumentException("존재하지 않는 카테고리 이거나 더이상 사용할 수없는 카테고리입니다");
        }
    }

}
