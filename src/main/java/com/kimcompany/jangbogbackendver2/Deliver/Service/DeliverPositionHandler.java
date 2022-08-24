package com.kimcompany.jangbogbackendver2.Deliver.Service;

import com.kimcompany.jangbogbackendver2.Member.Model.MemberEntity;
import com.kimcompany.jangbogbackendver2.Member.Model.PrincipalDetails;
import com.kimcompany.jangbogbackendver2.Util.UtilService;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class DeliverPositionHandler extends TextWebSocketHandler {
    Map<Integer, List<Map<String,Object>>> roomList =new HashMap<>(); //웹소켓 세션을 담아둘 리스트 ---roomListSessions

    private final DeliverService deliverService;
    private final DeliverSelectService deliverSelectService;


    @Override//메세지가오는함수
    public void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        JSONObject xAndYAndRoom= UtilService.stringToJson(message.getPayload());
//        //배달종료 메시지가 있다면 store만 해당됨
//        if(xAndYAndRoom.containsKey("state")){
//            closeAction(session, xAndYAndRoom);
//            return;
//        }
        System.out.println(xAndYAndRoom.toString());
        int roomId=Integer.parseInt(xAndYAndRoom.get("roomid").toString());
        //배달방번호 조회 검증 로직 추가해야함
        try {
            for(Map<String,Object>room:roomList.get(roomId)){
                try {
                    //보내기만 하면됨 n번방 세션 들 다꺼내기
                    WebSocketSession wss = (WebSocketSession) room.get("session");
                    wss.sendMessage(new TextMessage(xAndYAndRoom.toJSONString()));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        } catch (NullPointerException e) {
            e.printStackTrace();
            UtilService.ExceptionValue("만들어진 방이 없습니다",DeliverPositionHandler.class);
        }

    }
    public void closeAction(WebSocketSession session,JSONObject xAndYRoom) {
//        Map<Object,Object>infor=getLoginInfor(session);
//        String role=infor.get("role").toString();
//        if(role.equals(senums.company_role.get())){
//            closeAtStore(xAndYRoom);
//        }
    }
    @Override//연결이되면 자동으로 작동하는함수
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        /**
         * 점원이 연결시 최소에 배달방있는지 검사 없다면 객체생성
         */
        Map<String,Object>params=UtilService.getQueryMap(session.getUri().getQuery());
        String deliverId = params.get("roomid").toString();
        if(!roomList.containsKey(Integer.parseInt(deliverId))){
            List<Map<String, Object>> room = new ArrayList<>();
            /**
             * 테스트용 임시 손님 입력 로직
             */
            room.add(makeRoomDetail(session, deliverId, 1L));
            roomList.put(Integer.parseInt(deliverId), room);
        }
    }
    private MemberEntity getLoginInfo(WebSocketSession session) {
        AbstractAuthenticationToken principal=(AbstractAuthenticationToken) session.getPrincipal();
        PrincipalDetails principalDetails= (PrincipalDetails) principal.getPrincipal();
        return principalDetails.getMemberEntity();
    }
    @Override //연결이끊기면 자동으로 작동하는함수
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        //권한에 따라
        Map<String,Object>params=UtilService.getQueryMap(session.getUri().getQuery());
        String deliverId = params.get("roomid").toString();

    }

    public Map<String,Object> makeRoomDetail(WebSocketSession session, String roomId, long userId) {
        Map<String,Object>roomDetail=new HashMap<>();
        roomDetail.put("roomNumber", roomId);
        roomDetail.put("userId",userId);
        roomDetail.put("sessionId", session.getId());
        roomDetail.put("session", session);
        return roomDetail;
    }
/**
 *  나중에 구매자 웹소캣에 들어갈 내용
 */
//    List<Map<String, Object>> room = new ArrayList<>();
//            room.add(makeRoomDetail(session, deliverId, 1L));
//            roomList.put(Integer.parseInt(deliverId), room);

}
