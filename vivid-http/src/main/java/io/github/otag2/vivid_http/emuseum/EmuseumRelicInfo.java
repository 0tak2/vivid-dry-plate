package io.github.otag2.vivid_http.emuseum;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.util.List;

@RequiredArgsConstructor
@Getter
@ToString
public class EmuseumRelicInfo {
    private final EmuseumParams params;
    private final Integer numOfRows;
    private final Integer pageNo;
    private final Integer totalCount;
    private final String resultCode;
    private final String resultMsg;
    private final List<EmuseumRelicDetail> list;

    @RequiredArgsConstructor
    @Getter
    @ToString
    public static class EmuseumParams {
        private final String serviceKey;
        private final String id;
    }

    @RequiredArgsConstructor
    @Getter
    @ToString
    public static class EmuseumRelicDetail {
        private final String museumName3;
        private final String purposeName2;
        private final String materialName2;
        private final String purposeName1;
        private final String museumName1;
        private final String museumName2;
        private final String desc;
        private final String purposeName3;
        private final String sizeRangeCode;
        private final String relicSubNo;
        private final String museumCode1;
        private final String id;
        private final String museumCode2;
        private final String materialCode1;
        private final String materialCode2;
        private final String nationalityCode;
        private final String museumCode3;
        private final String name;
        private final String museumCode;
        private final String purposeCode;
        private final String imgThumUriM;
        private final String sizeRangeName;
        private final String purposeCode1;
        private final String imgThumUriS;
        private final String purposeCode2;
        private final String purposeCode3;
        private final String nationalityCode2;
        private final String nationalityCode1;
        private final String sizeInfo;
        private final String relicNo;
        private final String indexWord;
        private final String imgUri;
        private final String glsv;
        private final String materialCode;
        private final String nationalityName2;
        private final String nationalityName1;
        private final String materialName1;
        private final String imgThumUriL;
        private final String nameKr;
    }
}
