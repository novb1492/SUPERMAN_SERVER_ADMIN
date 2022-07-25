package com.kimcompany.jangbogbackendver2.Util;

import com.kimcompany.jangbogbackendver2.Member.Model.MemberEntity;
import com.kimcompany.jangbogbackendver2.Member.Model.PrincipalDetails;
import com.kimcompany.jangbogbackendver2.Text.BasicText;
import com.kimcompany.jangbogbackendver2.Text.PropertiesText;
import org.springframework.http.ResponseCookie;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

import static com.kimcompany.jangbogbackendver2.Text.BasicText.*;

public class UtilService {

    public static HttpServletResponse getHttpSerResponse() {
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        return attr.getResponse();
    }
    public static HttpServletRequest getHttpSerRequest() {
        ServletRequestAttributes attr = (ServletRequestAttributes)RequestContextHolder.currentRequestAttributes();
        return attr.getRequest();
    }
    public static void goForward(String url,HttpServletRequest request,HttpServletResponse response){
        RequestDispatcher dp=request.getRequestDispatcher(url);
        try {
            dp.forward(request, response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }
    public static void setTokenInHeader(String accessToken,String refreshToken) {
        HttpServletResponse response = getHttpSerResponse();
        response.setHeader(AuthenticationText, accessToken);
        response.setHeader(refreshTokenHeaderName, refreshToken);
    }
    public static boolean confirmNull(String s){
        if(!StringUtils.hasText(s)){
            return true;
        }else if(s.isBlank()||s.equals("null")||s.equals("undefined")){
            return true;
        }
        return false;
    }
    public static Map<String,String> getAuthentication(){
        HttpServletRequest request = getHttpSerRequest();
        Cookie[] cookies = request.getCookies();
        Map<String, String> token = new HashMap<>();
        for(Cookie c:cookies){
            if(c.getName().equals(AuthenticationText)){
                token.put(AuthenticationText, c.getValue());
            }else if(c.getName().equals(refreshTokenHeaderName)){
                token.put(refreshTokenHeaderName,c.getValue());
            }
        }
        return token;
    }
    public static void saveAuthenticationInCookie(String accessToken,String refreshToken) {
        HttpServletResponse response = getHttpSerResponse();
        //엑세스토큰 쿠키 삽입
        ResponseCookie cookie = ResponseCookie.from(AuthenticationText, accessToken)
                .httpOnly(true).build();
        response.addHeader("Set-Cookie",cookie.toString());
        //리프레시토큰 쿠키 삽입
        ResponseCookie cookie2 = ResponseCookie.from(refreshTokenHeaderName, refreshToken)
                .httpOnly(true).build();
        response.addHeader("Set-Cookie",cookie2.toString());


    }
    public static int LoginExceptionHandle(AuthenticationException failed) {
        int state = 0;
        if (Objects.equals(failed.getMessage(), "자격 증명에 실패하였습니다.")) {
            state = notEqualPwd;
        } else if (Objects.equals(failed.getMessage(), "사용자 계정이 잠겨 있습니다.")) {
            state = accountLock;
        }
        return state;
    }
    public static PrincipalDetails getPrincipalDetails(){
        return (PrincipalDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
    public static File convert(MultipartFile multipartFile) {
        File file=new File(LocalDate.now().toString()+ UUID.randomUUID()+multipartFile.getOriginalFilename());
        try(FileOutputStream fileOutputStream=new FileOutputStream(file)){
            fileOutputStream.write(multipartFile.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return file;
    }
}
