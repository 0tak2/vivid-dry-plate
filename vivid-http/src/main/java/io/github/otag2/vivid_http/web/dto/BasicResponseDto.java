package io.github.otag2.vivid_http.web.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class BasicResponseDto<T>{
    private final boolean success;
    private final T data;
}
