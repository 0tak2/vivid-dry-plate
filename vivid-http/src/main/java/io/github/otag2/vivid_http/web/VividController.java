package io.github.otag2.vivid_http.web;

import io.github.otag2.vivid_http.core.ColorizeService;
import io.github.otag2.vivid_http.core.RelicDetail;
import io.github.otag2.vivid_http.core.VividService;
import io.github.otag2.vivid_http.web.dto.BasicResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
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

    @GetMapping(value = "/dryplates/{relicId}/image", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public Resource getRelicImage(@PathVariable("relicId") String relicId, @RequestParam("colorized") boolean colorized) {
        return colorized ? this.vividService.getColorizedImage(relicId)
                : this.vividService.getOriginalImage(relicId);
    }
}
