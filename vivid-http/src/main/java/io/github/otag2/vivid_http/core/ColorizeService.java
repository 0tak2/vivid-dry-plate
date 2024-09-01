package io.github.otag2.vivid_http.core;

import io.github.otag2.vivid_http.colorize.ColorizePort;
import io.github.otag2.vivid_http.colorize.ColorizeRequestDto;
import io.github.otag2.vivid_http.emuseum.EmuseumPort;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ColorizeService {
    private final ColorizePort colorizePort;
    private final EmuseumPort emuseumPort;

    public void requestColorize(String relicId) {
        RelicDetail relicDetail = RelicDetail.of(this.emuseumPort.fetchRelicInfo(relicId));

        // todo: parse 검증
        String originalImgUri = relicDetail.getOriginalImgUri();
        String filename = this.emuseumPort.downloadImage(relicId + ".jpg", originalImgUri);

        this.colorizePort.requestColorize(new ColorizeRequestDto(filename));
    }
}
