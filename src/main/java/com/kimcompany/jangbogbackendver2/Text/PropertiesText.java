package com.kimcompany.jangbogbackendver2.Text;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class PropertiesText {

    public static int accessTokenExpireMin;
    public static int refreshTokenExpireDay;
    public static String accessTokenName;
    public static String refreshTokenName;
    public static String accessTokenSign;
    public static int redisPort;
    public static String redisHost;
    public static String refreshTokenSign;
    public static String awsAccessKey;
    public static String awsSecret;
    public static String awsUrl;
    public static String coolSmsKey;
    public static String coolSmsSecret;
    public static String companyNum;
    public static String sqsPhoneEndPoint;

    @Value("${sqs.phone.endpoint}")
    public void setSqsPhoneEndPoint(String value){
        sqsPhoneEndPoint = value;
    }

    @Value("${cool.apikey}")
    public void setCoolSmsKey(String value){
        coolSmsKey = value;
    }
    @Value("${cool.apiSecret}")
    public void setCoolSmsSecret(String value){
        coolSmsSecret = value;
    }
    @Value("${company.num}")
    public void setCompanyNum(String value){
        companyNum = value;
    }
    @Value("${aws.s3.url}")
    public void setAwsUrl(String value){
        awsUrl = value;
    }
    @Value("${cloud.aws.credentials.access-key}")
    public void setAwsAccessKey(String value){
        awsAccessKey = value;
    }
    @Value("${cloud.aws.credentials.secret-key}")
    public void setAwsSecret(String value){
        awsSecret = value;
    }

    @Value("${spring.redis.host}")
    public void setRedisHost(String value){
        redisHost = value;
    }
    @Value("${spring.redis.port}")
    public void setRedisPort(String value){
        redisPort = Integer.parseInt(value);
    }
    @Value("${access.token.name}")
    public  void setAccessTokenName(String accessTokenName) {
        PropertiesText.accessTokenName = accessTokenName;
    }

    @Value("${refresh.token.name}")
    public  void setRefreshTokenName(String refreshTokenName) {
        PropertiesText.refreshTokenName = refreshTokenName;
    }

    @Value("${access.token.sign}")
    public void setAccessTokenSign(String value) {
        accessTokenSign = value;
    }
    @Value("${refresh.token.sign}")
    public void setRefreshTokenSign(String value) {
        refreshTokenSign = value;
    }

    @Value("${refresh.token.day}")
    public void setRefreshTokenExpireDay(String value) {
        refreshTokenExpireDay = Integer.parseInt(value);
    }

    @Value("${access.token.min}")
    public void setAccessTokenExpireMin(String value) {
        this.accessTokenExpireMin = Integer.parseInt(value);
    }
}
