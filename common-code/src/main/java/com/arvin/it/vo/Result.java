package com.arvin.it.vo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result<T> {

    private Integer code;

    private String msg;

    private T data;

    public Result(T data) {
        this.data = data;
        this.code = 200;
        this.msg = "success";
    }
}
