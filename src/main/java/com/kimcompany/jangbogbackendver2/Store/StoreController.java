package com.kimcompany.jangbogbackendver2.Store;

import com.kimcompany.jangbogbackendver2.Store.Dto.SearchCondition;
import com.kimcompany.jangbogbackendver2.Store.Dto.TryInsertDto;
import com.kimcompany.jangbogbackendver2.Util.UtilService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import static com.kimcompany.jangbogbackendver2.Util.UtilService.confirmNull;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@Slf4j
@RequiredArgsConstructor
public class StoreController {
    private final StoreService  storeService;

    @RequestMapping(value = "/admin/store/save",method = POST)
    public ResponseEntity<?> regiStore(@Valid @RequestBody TryInsertDto tryInsertDto){
        storeService.save(tryInsertDto);
        JSONObject response = new JSONObject();
        response.put("message","매장등록이 완료 되었습니다");
        return ResponseEntity.ok().body(response);
    }
    @RequestMapping(value = "/admin/store/regi/list",method = GET)
    public ResponseEntity<?>selectList(HttpServletRequest request){
        int page = Integer.parseInt(request.getParameter("page"));
        return ResponseEntity.ok().body(storeService.selectForRegi(page));
    }

    @RequestMapping(value = "/{role}/store/list",method = GET)
    public ResponseEntity<?>selectList(HttpServletRequest request, @PathVariable String role){
        int page = Integer.parseInt(request.getParameter("page"));
        String category=request.getParameter("category");
        return ResponseEntity.ok().body(storeService.selectForList(SearchCondition.set(page,request.getParameter("keyword"),role,category)));
    }

}
