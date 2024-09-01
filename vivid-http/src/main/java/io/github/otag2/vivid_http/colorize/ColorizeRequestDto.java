package io.github.otag2.vivid_http.colorize;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@RequiredArgsConstructor
@Getter
@ToString
public class ColorizeRequestDto {
    private final String filename;
}
