package com.kimcompany.jangbogbackendver2.ProductEvent.Service;

import com.kimcompany.jangbogbackendver2.Product.Dto.TryInsertDto;
import com.kimcompany.jangbogbackendver2.Product.Repo.ProductRepo;
import com.kimcompany.jangbogbackendver2.ProductEvent.Model.ProductEventEntity;
import com.kimcompany.jangbogbackendver2.ProductEvent.Repo.ProductEventRepo;
import com.kimcompany.jangbogbackendver2.Util.UtilService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ProductEventService {

    private final ProductEventRepo productEventRepo;
    private List<String> dates = new ArrayList<>();

    @Transactional
    public void save(long productId, List<Map<String,Object>>events){
        if(events.isEmpty()){
            return;
        }
        for(Map<String,Object>event:events){
            if(event.isEmpty()){
                return;
            }
            confirmDate(event);
            confirmSame(event);
            confirmPrice(event);
            confirmName(event);
            productEventRepo.save(TryInsertDto.dtoToEntity(event, productId));
        }
    }
    private void confirmName(Map<String,Object>event){
        if(event.get("name").toString().length()>20){
            throw new IllegalArgumentException("이벤트 이름은 최대 20글자입니다\n"+event.get("name"));
        }
    }
    private void confirmPrice(Map<String,Object>event){
            String price = event.get("price").toString();
            if(UtilService.confirmPrice(price)){
                throw new IllegalArgumentException("가격에 문제가있습니다\n"+price);
            }

    }
    private void confirmDate(Map<String,Object>event){
            String startDate=event.get("startDate").toString().replace("T"," ")+":00";
            String endDate=event.get("endDate").toString().replace("T"," ")+":00";
            System.out.println(startDate+","+endDate);
            if(Timestamp.valueOf(startDate).toLocalDateTime().isAfter(Timestamp.valueOf(endDate).toLocalDateTime())){
                throw new IllegalArgumentException("이벤트시작일은 이벤트 종료일보다 빨라야합니다\n"+startDate+","+endDate);
            }
            if(Timestamp.valueOf(startDate).toLocalDateTime().isEqual(Timestamp.valueOf(endDate).toLocalDateTime())){
                throw new IllegalArgumentException("이벤트시작일은 이벤트 종료일보다 빨라야합니다\n"+startDate+","+endDate);
            }
    }
    private void confirmSame(Map<String,Object>event){
        String[]date=event.get("startDate").toString().split("T");
        if(dates.contains(date[0])){
            throw new IllegalArgumentException("동일한 이벤트 시작일이 있습니다\n"+date[0]);
        }else{
            dates.add(date[0]);
        }
    }
}
