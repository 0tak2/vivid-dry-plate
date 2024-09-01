package io.github.otag2.vivid_http.colorize;

import io.github.otag2.vivid_http.core.SseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class ColorizeReceiver {
    private final SseService sseService;

    @RabbitListener(queues = "colorizing_response")
    public void receiveMessage(ColorizeResponseDto responseDto) {
        log.info("Received Message : {}", responseDto.toString());

        if (!responseDto.isSuccess()) {
            this.sseService.sendTo(responseDto.getFilename(),
                    "completed",
                    new ColorizeResultDto(false, null),
                    false);
        }

        // todo: filename -> full image path on http
        this.sseService.sendTo(responseDto.getFilename(),
                "completed",
                new ColorizeResultDto(true, responseDto.getFilename()),
                true);
    }
}
