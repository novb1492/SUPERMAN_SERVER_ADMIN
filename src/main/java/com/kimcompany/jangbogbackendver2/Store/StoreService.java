package com.kimcompany.jangbogbackendver2.Store;

import com.kimcompany.jangbogbackendver2.Api.KakaoMapService;
import com.kimcompany.jangbogbackendver2.Store.Dto.SearchCondition;
import com.kimcompany.jangbogbackendver2.Store.Dto.SelectListDto;
import com.kimcompany.jangbogbackendver2.Store.Dto.SelectRegiDto;
import com.kimcompany.jangbogbackendver2.Store.Dto.TryInsertDto;
import com.kimcompany.jangbogbackendver2.Store.Model.StoreEntity;
import com.kimcompany.jangbogbackendver2.Store.Repo.StoreRepo;
import com.kimcompany.jangbogbackendver2.Text.BasicText;
import com.kimcompany.jangbogbackendver2.Util.UtilService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.sql.Select;
import org.json.simple.JSONObject;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static com.kimcompany.jangbogbackendver2.Text.BasicText.*;
import static com.kimcompany.jangbogbackendver2.Util.UtilService.confirmNull;
import static com.kimcompany.jangbogbackendver2.Util.UtilService.getLoginUserRole;

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
        if(storeSelectService.checkExist(address,name)){
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
    public Page<SelectRegiDto> selectForRegi(int page){
        String role = getLoginUserRole();
        if(role.equals(ROLE_ADMIN)){
            return storeSelectService.selectForRegi(page, regiEmployeePageSize);
        }else if(role.equals(ROLE_MANAGE)){
            return storeSelectService.selectForRegiManage(page, regiEmployeePageSize);
        }
        throw new IllegalArgumentException("권한이 없는 행위입니다");
    }
    public Page<SelectListDto> selectForList(SearchCondition searchCondition){
        log.info(searchCondition.toString());
        confirmRole(searchCondition.getRole());
        confirmCategory(searchCondition.getCategory(),searchCondition.getKeyword());
        if(searchCondition.getRole().equals(BasicText.ROLE_ADMIN)){
            return storeSelectService.selectForListAdmin(searchCondition, storeListPageSize);
        }
        return storeSelectService.selectForListOther(searchCondition, storeListPageSize);
    }
    private void confirmRole(String requestRole){
        String role = UtilService.getPrincipalDetails().getMemberEntity().getRole();
        if(!role.equals(requestRole)){
            throw new IllegalArgumentException("허가되지 않은 접근입니다");
        }
    }
    private void confirmCategory(String category,String val){
        if(!confirmNull(val)){
            if(confirmNull(category)){
                throw new IllegalArgumentException("검색 종류를 선택해 주세요");
            }
        }
    }
}
