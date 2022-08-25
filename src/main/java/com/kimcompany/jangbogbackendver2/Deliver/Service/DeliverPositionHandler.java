package com.kimcompany.jangbogbackendver2.Deliver.Service;

import com.kimcompany.jangbogbackendver2.Member.Model.MemberEntity;
import com.kimcompany.jangbogbackendver2.Member.Model.PrincipalDetails;
import com.kimcompany.jangbogbackendver2.Text.BasicText;
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

import static com.kimcompany.jangbogbackendver2.Text.BasicText.ROLE_ADMIN;

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
                }
            }

        } catch (NullPointerException e) {
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
        String role=params.get("role").toString();
        if(role.equals(ROLE_ADMIN)){
            adminAction(Integer.parseInt(deliverId));
        }else{
            clientAction(session,Integer.parseInt(deliverId));
        }
    }

    /**
     * 직원이 접속시 방이 존재 하지 않는다면
     * 새 배달방생성
     * @param deliverId
     */
    private void adminAction(int deliverId){
        if(!roomList.containsKey(deliverId)){
            List<Map<String, Object>> room = new ArrayList<>();
            roomList.put(deliverId, room);
        }
    }

    /**
     * 주문자가 배달조회시 입장
     * @param session
     * @param deliverId
     */
    private void clientAction(WebSocketSession session,int deliverId){
        if(!roomList.containsKey(deliverId)){
            throw new IllegalArgumentException("아직 배달이 시작되지 않았습니다");
        }
        //배달방 정보 가져오기
        List<Map<String, Object>> room = roomList.get(deliverId);
        //현재 접속 웹세션 정보 배열에 추가
        room.add(makeRoomDetail(session, Integer.toString(deliverId), 1L));
        roomList.put(deliverId, room);
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
