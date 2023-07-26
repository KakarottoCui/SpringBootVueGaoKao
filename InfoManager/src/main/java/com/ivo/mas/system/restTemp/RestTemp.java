package com.ivo.mas.system.restTemp;

import com.alibaba.fastjson.JSON;
import com.ivo.mas.system.SysInfo;
import lombok.Setter;
import org.springframework.http.*;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Setter
public class RestTemp {

    private HttpHeaders headers;

    private RestTemplate restTemplate;

    public RestTemp(){
        if(restTemplate == null)
            restTemplate = new RestTemplate();
        if(headers == null)
            headers = new HttpHeaders();
        this.headers.set(HttpHeaders.USER_AGENT, SysInfo.SYS_HEADER);
        this.headers.set("sys", SysInfo.SYS_NAME);
        // 设置ContentType
        this.headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
    }

    public <T> T postWithJson(String url, MultiValueMap<String, Object> paramMap , Class<T> responseType){
        // 请求中设置param和headers
        HttpEntity<MultiValueMap<String, Object>> request = new HttpEntity<>(paramMap, headers);
        // post请求并返回
        try {
            return restTemplate.postForObject(url, request, responseType);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public String postWithJsonForString(String url, MultiValueMap<String, Object> paramMap){
        return postWithJson(url,paramMap,String.class);
    }

    public <T> T getForObject(String url, Class<T> responseType) {
        return JSON.parseObject(getForString(url),responseType);
    }

    public String getForString(String url) {
        // 设置请求头
        HttpEntity<String> requestEntity = new HttpEntity<>(null, headers);
        try {
            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, requestEntity, String.class);
            return response.getBody();
        }catch (Exception e){
            return null;
        }
    }

}
