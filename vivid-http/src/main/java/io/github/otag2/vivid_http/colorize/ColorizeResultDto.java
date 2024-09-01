package io.github.otag2.vivid_http.colorize;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class ColorizeResultDto {
    private final boolean success;
    private final String imagePath;
}
