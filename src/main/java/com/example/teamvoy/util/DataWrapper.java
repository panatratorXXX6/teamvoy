package com.example.teamvoy.util;

import lombok.Data;

@Data
public class DataWrapper<T> {
    private T data;

    public static <T> DataWrapper<T> of(T data) {
        DataWrapper<T> dataWrapper = new DataWrapper<>();
        dataWrapper.setData(data);

        return dataWrapper;
    }

}
