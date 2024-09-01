package io.github.otag2.vivid_http.core;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@Getter
public class SseEmitterWrapper {
    private final SseEmitter sseEmitter;
    private final String topic;

    public SseEmitterWrapper(String topic) {
        this.sseEmitter = new SseEmitter();
        this.topic = topic;
    }
}
