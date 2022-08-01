package com.kimcompany.jangbogbackendver2.Store;

import com.kimcompany.jangbogbackendver2.Api.KakaoMapService;
import com.kimcompany.jangbogbackendver2.Store.Dto.TryInsertDto;
import com.kimcompany.jangbogbackendver2.Store.Model.StoreEntity;
import com.kimcompany.jangbogbackendver2.Store.Repo.StoreRepo;
import com.kimcompany.jangbogbackendver2.Util.UtilService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedHashMap;

/**
 * 매장 로직 관려 서비스 class입니다
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class StoreService {
    private final StoreRepo storeRepo;
    private final StoreSelectService storeSelectService;
    private final KakaoMapService kakaoMapService;
    @Transactional
    public void save(TryInsertDto tryInsertDto){
        //중복검사
        confirmExist(tryInsertDto.getAddress(), tryInsertDto.getName());
        //매장 오픈 시간 검사
        confirmTime(tryInsertDto.getOpenTime(),tryInsertDto.getCloseTime());
        //주소검사
        confirmAddress(tryInsertDto.getAddress());
        StoreEntity storeEntity = TryInsertDto.dtoToEntity(tryInsertDto);
        storeRepo.save(storeEntity);
    }
    private void confirmAddress(String address){
        JSONObject response = kakaoMapService.getAddress(address);
        if(kakaoMapService.getTotalCount((LinkedHashMap<String, Object>) response.get("meta"))==0){
            throw new IllegalArgumentException("존재 하지 않는 주소입니다");
        }
    }
    private void confirmTime(String openTime,String closeTime){
        if(checkTime(openTime,closeTime)){
            throw new IllegalArgumentException("오픈시간과 마감시간을 다시 입력해주세요");
        }
    }
    private void confirmExist(String address,String name){
        if(storeSelectService.checkExist(address,name, UtilService.getLoginUserId())){
            throw new IllegalArgumentException("이미 존재하는 매장입니다");
        }
    }
    private boolean checkTime(String openTime,String closeTime){
        String[] openTimeArr = openTime.split(":");
        String[] closeTimeArr=closeTime.split(":");
        int openHour = Integer.parseInt(openTimeArr[0]);
        int closeHour = Integer.parseInt(closeTimeArr[0]);
        int openMin = Integer.parseInt(openTimeArr[1]);
        int closeMin = Integer.parseInt(closeTimeArr[1]);
        if(openHour<1||openHour>24||closeHour>24||closeHour<1){
            return true;
        }else if(openMin>60||openMin<0||closeMin>60||closeMin<0){
            return true;
        }
        return false;
    }
}
