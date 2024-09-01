package io.github.otag2.vivid_http.core;

import io.github.otag2.vivid_http.emuseum.EmuseumPort;
import io.github.otag2.vivid_http.emuseum.EmuseumRelicInfo;
import io.github.otag2.vivid_http.support.properties.ImagesProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Service;

import java.nio.file.Paths;

@Service
@RequiredArgsConstructor
public class VividService {
    private final EmuseumPort emuseumPort;
    private final ImagesProperties imagesProperties;

    public RelicDetail getRelicDetail(String relicId) {
        EmuseumRelicInfo relicInfoFromEmuseum = this.emuseumPort.fetchRelicInfo(relicId);
        return RelicDetail.of(relicInfoFromEmuseum);
    }

    public FileSystemResource getColorizedImage(String relicId) {
        return new FileSystemResource(Paths.get(
                this.imagesProperties.getPath() + "/" + this.imagesProperties.getResultPath() + "/" + relicId + ".jpg"));
    }

    public FileSystemResource getOriginalImage(String relicId) {
        // todo: 예외처리 (404 등)

        return new FileSystemResource(Paths.get(
                this.imagesProperties.getPath() + "/" + this.imagesProperties.getOriginalPath() + "/" + relicId + ".jpg"));
    }
}
