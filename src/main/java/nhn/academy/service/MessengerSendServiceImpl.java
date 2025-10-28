package nhn.academy.service;

import nhn.academy.model.Attachment;
import nhn.academy.model.Bot;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.w3c.dom.Attr;

import java.awt.*;
import java.util.ArrayList;
import java.util.Map;

@Service
public class MessengerSendServiceImpl implements MessengerSendService {

    static final String MESSENGER_API_URL = "https://nhnacademy.dooray.com/services/3204376758577275363/4187998923424121340/TIKUKgZkTXeYbodByHtL5Q";

    @Override
    public void sendMessage(Bot bot) {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Bot> httpEntity = new HttpEntity<>(bot, httpHeaders);

        ResponseEntity<Map> exchange = restTemplate.exchange(MESSENGER_API_URL, HttpMethod.POST, httpEntity, Map.class);
        System.out.println("!!!!");
        System.out.println(exchange.getStatusCode());
    }
}
