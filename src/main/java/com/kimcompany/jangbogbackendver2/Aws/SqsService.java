package com.kimcompany.jangbogbackendver2.Aws;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONObject;
import org.junit.Test;
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class SqsService {
    private final QueueMessagingTemplate queueMessagingTemplate;
    public void sendSqs() {
        //구성만들기
        queueMessagingTemplate.send("https://sqs.ap-northeast-2.amazonaws.com/527222691614/SUPERMAN_PHONE", MessageBuilder.withPayload(makeTitleAndText("title","text","val")).build());

    }
    private JSONObject makeTitleAndText(String title, String text, String val) {
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("title", title);
        jsonObject.put("text", text);
        jsonObject.put("val", val);
        return jsonObject;
    }
}
