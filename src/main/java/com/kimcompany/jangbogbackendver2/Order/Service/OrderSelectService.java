package com.kimcompany.jangbogbackendver2.Order.Service;

import com.kimcompany.jangbogbackendver2.Order.Dto.SearchCondition;
import com.kimcompany.jangbogbackendver2.Order.Dto.SelectDto;
import com.kimcompany.jangbogbackendver2.Order.Dto.SelectListDto;
import com.kimcompany.jangbogbackendver2.Order.Repo.OrderRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OrderSelectService {
    private final OrderRepo orderRepo;

    public Page<SelectListDto>selectForList(SearchCondition searchCondition){
        return orderRepo.selectForList(searchCondition);
    }
    public List<SelectDto>selectForDetail(long storeId,long cardId){
        return orderRepo.selectByStoreIdAndCardId(storeId, cardId);
    }

}
