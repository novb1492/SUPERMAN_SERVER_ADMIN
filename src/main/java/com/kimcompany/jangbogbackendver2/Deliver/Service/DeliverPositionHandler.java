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
        Map<String,Object>params=UtilService.getQueryMap(session.getUri().getQuery());
        System.out.println(params.toString());
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
        if(roomList.containsKey(deliverId)){
            List<Map<String, Object>> room = roomList.get(deliverId);
            Map<String, Object> stringObjectMap = new HashMap<>();
            stringObjectMap.put("clientId",1);
            stringObjectMap.put("cardId",10);
            room.add(stringObjectMap);
            roomList.put(Integer.parseInt(deliverId), room);
        }
    }
//    public void closeAtStore(JSONObject xAndYRoom) {
//        System.out.println("closeAtStore");
//        //배달은 계속 되야하니 예외 잡아줌 안끊기게
//        try {
//            int roomId=Integer.parseInt(xAndYRoom.get("roomid").toString());
//            int userId=Integer.parseInt(xAndYRoom.get("userid").toString());
//            List<Map<String,Object>>room=roomList.get(roomId);
//            int num=0;
//            System.out.println(userId);
//            try {
//                for(Map<String,Object>roomDetail:room){
//                    if(Integer.parseInt(roomDetail.get("userId").toString())==userId){
//                        System.out.println((roomDetail.get("userId").toString()));
//                        break;
//                    }
//                    num+=1;
//                }
//                room.remove(num);
//                roomList.replace(roomId, room);
//            } catch (NullPointerException e) {
//                e.printStackTrace();
//                //배달 완료전까지 손님이 방에 안들어 왔을 수도 있으므로 무시
//            }
//            String mchtTrdNo=xAndYRoom.get("mchtTrdNo").toString();
//            String state=xAndYRoom.get("state").toString();
//            int storeId=Integer.parseInt(xAndYRoom.get("storeid").toString());
////            deliveryService.changeStateDetail(storeId, userId, mchtTrdNo, state,roomId);
//        } catch (Exception e) {
//            //TODO: handle exception
//        }
//
//    }
//    public void closeAtUser(int roomId,int userId) {
//        System.out.println("closeAtUser");
//        //배달방에서 나가기
//        List<Map<String,Object>>room=roomList.get(roomId);
//        int num=0;
//        for(Map<String,Object>roomDetail:room){
//            if(Integer.parseInt(roomDetail.get("userId").toString())==userId){
//                System.out.println((roomDetail.get("userId").toString()));
//                break;
//            }
//            num+=1;
//        }
//        System.out.println(num);
//        room.remove(num);
//        roomList.replace(roomId, room);
//    }

//    public void actionAtUser(WebSocketSession session,int id) {
//        //int roomId=0;//상점에서준 방번호 꺼내기 로직 추가해야함
//        List<Integer>roomIds=deliveryService.selectRoomIdByUserIdAndFlag(id,intenums.NOT_FLAG.get());
//        if(roomIds.isEmpty()){
//            UtilService.ExceptionValue("배달이 존재하지 않습니다", DeliverPositionHandler.class);
//            throw new IllegalArgumentException("배달이 존재하지 않습니다");
//        }
//        for(int roomId:roomIds){
//            List<Map<String,Object>>room=new ArrayList<>();
//            boolean findFlag=false;
//            //배달번호로 된 방이 있나검사
//            try {
//                room=roomList.get(roomId);
//                for(Map<String,Object>rd:room){
//                    if(rd.get("userId").equals(id)){
//                        //방이 있고 기존유저 소켓정보 수정
//                        rd.put("sessionId", session.getId());
//                        rd.put("session", session);
//                        findFlag=true;
//                        break;
//                    }
//                }
//                //방이 이미 있고 새유저가 왔을때
//                if(!findFlag){
//                    room.add(makeRoomDetail(session, roomId, id));
//                }
//            } catch (NullPointerException e) {
//                //배달번호 관계없이 방이 하나도 없을경우만듬
//                room=new ArrayList<>();
//                room.add(makeRoomDetail(session, roomId, id));
//                roomList.put(roomId, room);
//            }
//        }
//    }
    public Map<String,Object> makeRoomDetail(WebSocketSession session, int roomId, int userId) {
        Map<String,Object>roomDetail=new HashMap<>();
        roomDetail.put("roomNumber", roomId);
        roomDetail.put("userId",userId);
        roomDetail.put("sessionId", session.getId());
        roomDetail.put("session", session);
        return roomDetail;
    }
}
