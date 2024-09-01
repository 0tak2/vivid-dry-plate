package io.github.otag2.vivid_http.emuseum;

import io.github.otag2.vivid_http.support.properties.EmuseumProperties;
import io.github.otag2.vivid_http.support.properties.ImagesProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.DefaultUriBuilderFactory;
import reactor.core.publisher.Flux;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

@Component
@RequiredArgsConstructor
@Slf4j
public class EmuseumPort {
    private final EmuseumProperties emuseumProperties;
    private final ImagesProperties imagesProperties;
    private final String baseUri = "http://www.emuseum.go.kr/openapi";

    public EmuseumRelicInfo fetchRelicInfo(String relicId) {
        // todo: 캐싱

        // ref: https://colabear754.tistory.com/122
        DefaultUriBuilderFactory factory = new DefaultUriBuilderFactory();
        factory.setEncodingMode(DefaultUriBuilderFactory.EncodingMode.NONE);

        WebClient client = WebClient.builder()
                .uriBuilderFactory(factory)
                .defaultHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
                .build();

        EmuseumRelicInfo response = client.get()
                .uri(baseUri + "/relic/detail?serviceKey=" + this.emuseumProperties.getServiceKey() + "&id=" + relicId)
                .retrieve()
                .bodyToMono(EmuseumRelicInfo.class)
                .block();

        // todo: 예외처리

        log.info("retrieved response from emuseum relic detail api. {}", response);

        return response;
    }

    public String downloadImage(String filename, String imageUri) {
        Path path = Paths.get(this.imagesProperties.getPath() + "/" + this.imagesProperties.getOriginalPath() + "/" + filename);

        log.info("will download {} to {}", imageUri, path);

        // todo: 중복 코드 정리
        DefaultUriBuilderFactory factory = new DefaultUriBuilderFactory();
        factory.setEncodingMode(DefaultUriBuilderFactory.EncodingMode.NONE);

        // ref: https://stackoverflow.com/a/62916446
        WebClient client = WebClient.builder()
                .uriBuilderFactory(factory)
                .build();
        Flux<DataBuffer> dataBufferFlux = client.get().uri(imageUri).retrieve().bodyToFlux(DataBuffer.class);
        DataBufferUtils.write(dataBufferFlux, path, StandardOpenOption.CREATE).block();

        return filename;
    }
}
