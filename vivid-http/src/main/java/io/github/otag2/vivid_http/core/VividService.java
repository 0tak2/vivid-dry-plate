package io.github.otag2.vivid_http.core;

import io.github.otag2.vivid_http.emuseum.EmuseumPort;
import io.github.otag2.vivid_http.emuseum.EmuseumRelicInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VividService {
    private final EmuseumPort emuseumPort;

    public RelicDetail getRelicDetail(String relicId) {
        EmuseumRelicInfo relicInfoFromEmuseum = this.emuseumPort.fetchRelicInfo(relicId);
        return RelicDetail.of(relicInfoFromEmuseum);
    }
}
