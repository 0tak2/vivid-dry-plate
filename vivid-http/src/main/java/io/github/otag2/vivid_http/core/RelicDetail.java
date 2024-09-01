package io.github.otag2.vivid_http.core;

import io.github.otag2.vivid_http.emuseum.EmuseumRelicInfo;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class RelicDetail {
    private final String relicId;
    private final String name;
    private final String desc;
    private final String relicCode;
    private final String originalImgUri;
    private final String originalThumbUri;
    private final String sizeInfo;

    public static RelicDetail of(EmuseumRelicInfo emuseumRelicInfo) {
        EmuseumRelicInfo.EmuseumRelicDetail emuseumRelicDetail = emuseumRelicInfo.getList().get(0);
        return new RelicDetail(
                emuseumRelicDetail.getId(),
                emuseumRelicDetail.getName(),
                emuseumRelicDetail.getDesc(),
                emuseumRelicDetail.getMuseumName3() + emuseumRelicDetail.getRelicNo(),
                emuseumRelicDetail.getImgUri(),
                emuseumRelicDetail.getImgThumUriL(),
                emuseumRelicDetail.getSizeInfo()
        );
    }
}
