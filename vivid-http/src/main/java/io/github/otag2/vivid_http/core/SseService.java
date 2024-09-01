package io.github.otag2.vivid_http.core;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Service
@Slf4j
public class SseService {
    private final List<SseEmitterWrapper> sseEmitterList = new CopyOnWriteArrayList<>();

    public SseEmitter newSseEmitter(String topic){
        SseEmitterWrapper sseEmitterWrapper = new SseEmitterWrapper(topic);
        this.sseEmitterList.add(sseEmitterWrapper);
        try {
            sseEmitterWrapper.getSseEmitter().send(SseEmitter.event()
                            .name("connected")
                            .data("connected").build());
        } catch (IOException e) {
            log.error("error when add new sse emitter. {}", e.getMessage());
            throw new RuntimeException(e);
        }
        return sseEmitterWrapper.getSseEmitter();
    }

    public void sendTo(String topic, String eventName, Object eventContent, boolean removeAfterSend) {
        if (topic == null) {
            throw new RuntimeException("Topic cannot be null");
        }

        this.sseEmitterList
                .stream()
                .filter(sseEmitterWrapper -> sseEmitterWrapper.getTopic().equals(topic))
                .forEach(sseEmitterWrapper ->
                {
                    try {
                        sseEmitterWrapper.getSseEmitter().send(SseEmitter.event().name(eventName).data(eventContent));
                        log.info("sent SSE event name={} content={}", eventName, eventContent);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });

        if (removeAfterSend) {
            this.sseEmitterList.removeIf(sseEmitterWrapper -> sseEmitterWrapper.getTopic().equals(topic));
        }
    }
}
