package com.kimcompany.jangbogbackendver2.Aws;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kimcompany.jangbogbackendver2.Api.CoolSms;
import com.kimcompany.jangbogbackendver2.Text.PropertiesText;
import com.kimcompany.jangbogbackendver2.Util.UtilService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.nurigo.java_sdk.exceptions.CoolsmsException;
import org.json.simple.JSONObject;
import org.junit.Test;
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate;
import org.springframework.cloud.aws.messaging.listener.annotation.SqsListener;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import static com.kimcompany.jangbogbackendver2.Util.UtilService.stringToJson;

/**
 * sqs 메세지를 수신받는 서비스
 * 서버 자체를 분리해야하나
 * 토이플젝이므로 하나에 구축
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class SqsService {
    private final QueueMessagingTemplate queueMessagingTemplate;

    @SqsListener("SUPERMAN_PHONE")
    public void loadSMSMessage(String message) {
        log.info("loadSMSMessage:{}",message);
        try {
            JSONObject request = stringToJson(message);
            CoolSms.sendMessage(request.get("val").toString(), request.get("text").toString());
        }catch (CoolsmsException e){
            log.info("coolsms 예외:{},코드:{}",e.getMessage(),e.getCode());
        }catch (Exception e){
            log.info("문자전송실패:{}",e.getMessage());
        }
    }
    public void sendSqs(String endPoint) {
        queueMessagingTemplate.send(endPoint, MessageBuilder.withPayload(makeTitleAndText("title","text","val")).build());
    }
    private JSONObject makeTitleAndText(String title, String text, String val) {
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("title", title);
        jsonObject.put("text", text);
        jsonObject.put("val", val);
        return jsonObject;
    }
}
