package nhn.academy.controller;

import nhn.academy.model.Attachment;
import nhn.academy.model.Bot;
import nhn.academy.service.MessengerSendFeignService;
import nhn.academy.service.MessengerSendService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.awt.*;
import java.util.ArrayList;

@RestController
public class SendController {

    private final MessengerSendFeignService messengerSendFeignService;
    private final MessengerSendService messengerSendService;

    public SendController(MessengerSendFeignService messengerSendFeignService, MessengerSendService messengerSendService) {
        this.messengerSendFeignService = messengerSendFeignService;
        this.messengerSendService = messengerSendService;
    }

    @PostMapping("/send")
    public ResponseEntity post() {
        Attachment attachment = new Attachment("test입니다!", "테스트!", "http://naver.com", "https://static.dooray.com/static_images/dooray-bot.png", Color.red);
        ArrayList<Attachment> objects = new ArrayList<>();
//        objects.add(attachment);
        Bot bot = new Bot("test입니다", "테스트", objects);

        messengerSendService.sendMessage(bot);

        return ResponseEntity.ok(bot);
    }

    @PostMapping("/feign/send")
    public ResponseEntity postFeign() {
        Attachment attachment = new Attachment("FeignClient", "FeignClient", "http://naver.com", "https://static.dooray.com/static_images/dooray-bot.png", Color.RED);
        ArrayList<Attachment> objects = new ArrayList<>();
        objects.add(attachment);
        Bot bot = new Bot("FeignClient", "FeignClient", objects);

        try {
            messengerSendFeignService.sendMessage(bot,
                    3204376758577275363L,
                    4187998923424121340L,
                    "TIKUKgZkTXeYbodByHtL5Q"
            );
        }catch (Exception e){
            System.out.println(e);
        }

        return ResponseEntity.ok().build();
    }

}
