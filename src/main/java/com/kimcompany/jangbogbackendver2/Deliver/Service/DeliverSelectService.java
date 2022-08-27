package com.kimcompany.jangbogbackendver2.Deliver.Service;

import com.kimcompany.jangbogbackendver2.Deliver.Dto.SearchCondition;
import com.kimcompany.jangbogbackendver2.Deliver.Dto.SelectDto;
import com.kimcompany.jangbogbackendver2.Deliver.Dto.SelectListDto;
import com.kimcompany.jangbogbackendver2.Deliver.Repo.DeliverRepo;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class DeliverSelectService {
    private final DeliverRepo deliverRepo;

    public Page<SelectListDto> selectForList(SearchCondition searchCondition){
        return  deliverRepo.selectForList(searchCondition);
    }
    public List<SelectDto>selectForDetail(long storeId,long deliverId){
        return deliverRepo.selectForDetail(storeId, deliverId);
    }

}
