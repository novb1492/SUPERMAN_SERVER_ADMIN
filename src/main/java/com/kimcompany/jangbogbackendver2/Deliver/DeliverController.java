package com.kimcompany.jangbogbackendver2.Deliver;

import com.kimcompany.jangbogbackendver2.Deliver.Dto.SearchCondition;
import com.kimcompany.jangbogbackendver2.Deliver.Dto.TryInsertDto;
import com.kimcompany.jangbogbackendver2.Deliver.Service.DeliverSelectService;
import com.kimcompany.jangbogbackendver2.Deliver.Service.DeliverService;
import com.kimcompany.jangbogbackendver2.Text.BasicText;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import static com.kimcompany.jangbogbackendver2.Text.BasicText.deliverPageSize;

@RestController
@RequiredArgsConstructor
public class DeliverController {
    private final DeliverService deliverService;
    private final DeliverSelectService deliverSelectService;

    /**
     * 배달방 생성
     * @param tryInsertDto
     * @return
     */
    @RequestMapping(value = "/deliver/save",method = RequestMethod.POST)
    public ResponseEntity<?>save(@Valid @RequestBody TryInsertDto tryInsertDto){
        long id = deliverService.save(tryInsertDto);
        JSONObject response = new JSONObject();
        response.put("message", "배달방 생성에 성공했습니다");
        response.put("roomId", id);
        return ResponseEntity.ok().body(response);
    }
    @RequestMapping(value = "/deliver/list/{storeId}/{deliverId}/{state}",method = RequestMethod.POST)
    public ResponseEntity<?>selectForList(HttpServletRequest request,@PathVariable String storeId,@PathVariable String deliverId,@PathVariable String state){
        int page = Integer.parseInt(request.getParameter("page"));
        long storeIdToLong = Long.parseLong(storeId);
        long deliverIdToLong = Long.parseLong(deliverId);
        int stateToInt = Integer.parseInt(state);
        SearchCondition searchCondition = SearchCondition.set(page, deliverPageSize, storeIdToLong, deliverIdToLong,stateToInt);
        return ResponseEntity.ok().body(deliverSelectService.selectForList(searchCondition));
    }

}
