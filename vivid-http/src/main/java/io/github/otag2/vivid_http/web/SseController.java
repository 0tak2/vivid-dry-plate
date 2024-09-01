package io.github.otag2.vivid_http.web;

import io.github.otag2.vivid_http.core.SseEmitterWrapper;
import io.github.otag2.vivid_http.core.SseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;

@Controller
@RequestMapping("/sse")
@RequiredArgsConstructor
@Slf4j
public class SseController {
    private final SseService sseService;

    @GetMapping(value = "/connect", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public ResponseEntity<SseEmitter> register(@RequestParam("topic") String topic) {
        SseEmitter sseEmitter = this.sseService.newSseEmitter(topic);
        return ResponseEntity.ok(sseEmitter);
    }
}
