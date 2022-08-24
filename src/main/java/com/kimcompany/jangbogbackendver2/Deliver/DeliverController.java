package com.kimcompany.jangbogbackendver2.Deliver;

import com.kimcompany.jangbogbackendver2.Deliver.Service.DeliverService;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class DeliverController {
    private final DeliverService deliverService;

    @RequestMapping(value = "/deliver/save/{cardId}",method = RequestMethod.POST)
    public ResponseEntity<?>save(@PathVariable String cardId){
        long id = deliverService.save(Long.parseLong(cardId));
        JSONObject response = new JSONObject();
        response.put("message", "배달방 생성에 성공했습니다");
        response.put("roomId", id);
        return ResponseEntity.ok().body(response);
    }

}
