package com.kimcompany.jangbogbackendver2.Order.Repo;

import com.kimcompany.jangbogbackendver2.Order.Dto.SearchCondition;
import com.kimcompany.jangbogbackendver2.Order.Dto.SelectListDto;
import org.springframework.data.domain.Page;

public interface OrderRepoCustom {
    public Page<SelectListDto> selectForList(SearchCondition searchCondition);
}
