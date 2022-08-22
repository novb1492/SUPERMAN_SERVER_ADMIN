package com.kimcompany.jangbogbackendver2.Order;

import com.kimcompany.jangbogbackendver2.Order.Dto.SearchCondition;
import com.kimcompany.jangbogbackendver2.Order.Service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @RequestMapping("/user/order/list/{storeId}/{state}")
    public ResponseEntity<?>selectForList(HttpServletRequest request, @PathVariable String storeId, @PathVariable String state){
        SearchCondition searchCondition = SearchCondition.set(Long.parseLong(storeId)
                , request.getParameter("category")
                , request.getParameter("keyword")
                ,Integer.parseInt(request.getParameter("state"))
                ,request.getParameter("periodFlag")
                ,request.getParameter("startDate")
                ,request.getParameter("endDate"));
        return ResponseEntity.ok().body(orderService.selectForList(searchCondition));
    }
}
