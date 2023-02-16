package com.yido.clubd.common.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.simple.parser.ParseException;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class KakaoRestAPI {
	
	/**
	 * 토큰발급
	 * 
	 * @param autorize_code
	 * @param request
	 * @return
	 */
	public static String getAccessToken(String authorize_code) {

        String access_Token = "";
        String refresh_Token = "";
        String reqURL = "https://kauth.kakao.com/oauth/token";

        try {
            URL url = new URL(reqURL);

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            // URL연결은 입출력에 사용 될 수 있고, POST 혹은 PUT 요청을 하려면 setDoOutput을 true로 설정해야함.
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);

            // POST 요청에 필요로 요구하는 파라미터 스트림을 통해 전송
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
            StringBuilder sb = new StringBuilder();
            sb.append("grant_type=authorization_code");
            sb.append("&client_id=" + Globals.KakaoClientId);  //본인이 발급받은 key
            sb.append("&redirect_uri=" + Globals.KakaoRedirectUrl);     // 본인이 설정해 놓은 경로
            sb.append("&code=" + authorize_code);
            bw.write(sb.toString());
            bw.flush();

            // 결과 코드가 200이라면 성공
            int responseCode = conn.getResponseCode();
            log.info("[getAccessToken] 토큰발급 응답코드 : " + responseCode);

            // 요청을 통해 얻은 JSON타입의 Response 메세지 읽어오기
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line = "";
            String result = "";

            while ((line = br.readLine()) != null) {
                result += line;
            }
            log.info("[getAccessToken] response body: " + result);

            JSONObject element = new JSONObject(result);
          
            access_Token = element.getString("access_token");
            refresh_Token = element.getString("refresh_token");

            br.close();
            bw.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            log.debug(e.getMessage());
        }

        return access_Token;
    }

	/**
	 * 유저정보조회
	 * 
	 * @param autorize_code
	 * @param request
	 * @return
	 * @throws ParseException 
	 * @throws JSONException 
	 */
	public static HashMap<String, Object> getKakaoUserInfo(String access_Token) throws JSONException, ParseException {

	    // 요청하는 클라이언트마다 가진 정보가 다를 수 있기에 HashMap타입으로 선언
        HashMap<String, Object> userInfo = new HashMap<String, Object>();
        String reqURL = "https://kapi.kakao.com/v2/user/me";
        try {
            URL url = new URL(reqURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            // 요청에 필요한 Header에 포함될 내용
            conn.setRequestProperty("Authorization", "Bearer " + access_Token);

            int responseCode = conn.getResponseCode();
            log.info("[getKakaoUserInfo] 유저정보조회 응답코드: " + responseCode);

            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));

            String line = "";
            String result = "";

            while ((line = br.readLine()) != null) {
                result += line;
            }
            log.info("[getKakaoUserInfo] response body: " + result);

            JsonElement element = JsonParser.parseString(result);
            
            String id = element.getAsJsonObject().get("id").getAsString();
            
            JsonObject properties = element.getAsJsonObject().get("properties").getAsJsonObject();
            String nickname = properties.getAsJsonObject().get("nickname").getAsString();
            
            JsonObject kakao_account = element.getAsJsonObject().get("kakao_account").getAsJsonObject();
            String email = kakao_account.getAsJsonObject().get("email").getAsString();
            
            userInfo.put("msId", id);
            userInfo.put("msName", nickname);
            userInfo.put("msEmail", email);

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return userInfo;
    }
}
