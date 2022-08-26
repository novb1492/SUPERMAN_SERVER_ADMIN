package com.kimcompany.jangbogbackendver2.Deliver.Service;

import com.kimcompany.jangbogbackendver2.Deliver.Dto.SearchCondition;
import com.kimcompany.jangbogbackendver2.Deliver.Dto.SelectListDto;
import com.kimcompany.jangbogbackendver2.Deliver.Dto.SelectListDtoAddTotalPrice;
import com.kimcompany.jangbogbackendver2.Deliver.Repo.DeliverRepo;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class DeliverSelectService {
    private final DeliverRepo deliverRepo;

    public SelectListDtoAddTotalPrice selectForList(SearchCondition searchCondition){
        Page<SelectListDto> selectListDtos = deliverRepo.selectForList(searchCondition);
        SelectListDtoAddTotalPrice selectListDtoAddTotalPrice = new SelectListDtoAddTotalPrice();
        selectListDtoAddTotalPrice.setSelectListDtos(selectListDtos);
        selectListDtoAddTotalPrice.setTotalPrice(selectListDtos.getContent());
        return selectListDtoAddTotalPrice;
    }
}
