package io.github.otag2.vivid_http.colorize;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class ColorizePort {
    private final RabbitTemplate rabbitTemplate;
    private final String ROUTING_KEY = "colorizing_request";

    public void requestColorize(ColorizeRequestDto requestDto) {
        rabbitTemplate.convertAndSend(ROUTING_KEY, requestDto);
        log.info("Emit Message : {}", requestDto.toString());
    }
}
