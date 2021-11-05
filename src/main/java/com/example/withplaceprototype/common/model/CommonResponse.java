package com.example.withplaceprototype.common.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@EqualsAndHashCode
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class CommonResponse<T> {
    private String message;
    private T result;

    public static <T> CommonResponse<T> OK(final T result) {
        return CommonResponse.<T>builder()
            .result(result)
            .build();
    }
}

