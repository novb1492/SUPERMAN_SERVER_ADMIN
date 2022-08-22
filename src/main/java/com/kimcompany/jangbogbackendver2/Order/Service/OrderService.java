package com.kimcompany.jangbogbackendver2.Order.Service;

import com.kimcompany.jangbogbackendver2.Order.Dto.SearchCondition;
import com.kimcompany.jangbogbackendver2.Order.Dto.SelectListDto;
import com.kimcompany.jangbogbackendver2.Text.BasicText;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import static com.kimcompany.jangbogbackendver2.Text.BasicText.deleteState;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderSelectService orderSelectService;

    public Page<SelectListDto>selectForList(SearchCondition searchCondition){
        if(searchCondition.getState()== deleteState){
            throw new IllegalArgumentException("조회 할 수 없는 주문입니다");
        }
        return orderSelectService.selectForList(searchCondition);
    }

}
