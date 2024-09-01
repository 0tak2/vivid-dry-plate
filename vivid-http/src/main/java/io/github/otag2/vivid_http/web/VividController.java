package io.github.otag2.vivid_http.web;

import io.github.otag2.vivid_http.core.ColorizeService;
import io.github.otag2.vivid_http.core.RelicDetail;
import io.github.otag2.vivid_http.core.VividService;
import io.github.otag2.vivid_http.web.dto.BasicResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1")
@RequiredArgsConstructor
public class VividController {
    private final VividService vividService;
    private final ColorizeService colorizeService;

    @GetMapping("/dryplates/{relicId}")
    public BasicResponseDto<RelicDetail> getRelicInfo(@PathVariable("relicId") String relicId) {
        RelicDetail relicDetail = this.vividService.getRelicDetail(relicId);
        return new BasicResponseDto<>(true, relicDetail);
    }

    @PostMapping("/dryplates/{relicId}/colorize")
    public BasicResponseDto<String> requestColorize(@PathVariable("relicId") String relicId) {
        this.colorizeService.requestColorize(relicId);
        return new BasicResponseDto<>(true, "Okay");
    }
}
