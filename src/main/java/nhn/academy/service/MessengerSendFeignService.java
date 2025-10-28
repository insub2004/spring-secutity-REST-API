package nhn.academy.service;

import nhn.academy.model.Bot;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "messageSendClient", url = "https://nhnacademy.dooray.com/services")
public interface MessengerSendFeignService {

    @PostMapping(value = "/{serviceId}/{botId}/{botToken}")
    String sendMessage(@RequestBody Bot bot, @PathVariable Long serviceId, @PathVariable Long botId, @PathVariable String botToken);
}
