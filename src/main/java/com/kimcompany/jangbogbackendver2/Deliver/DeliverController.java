package com.kimcompany.jangbogbackendver2.Deliver;

import com.kimcompany.jangbogbackendver2.Deliver.Dto.TryInsertDto;
import com.kimcompany.jangbogbackendver2.Deliver.Service.DeliverService;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class DeliverController {
    private final DeliverService deliverService;

    @RequestMapping(value = "/deliver/save",method = RequestMethod.POST)
    public ResponseEntity<?>save(@Valid @RequestBody TryInsertDto tryInsertDto){
        long id = deliverService.save(tryInsertDto);
        JSONObject response = new JSONObject();
        response.put("message", "배달방 생성에 성공했습니다");
        response.put("roomId", id);
        return ResponseEntity.ok().body(response);
    }

}
