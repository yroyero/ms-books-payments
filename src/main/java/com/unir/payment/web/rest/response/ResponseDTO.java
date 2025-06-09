package com.unir.payment.web.rest.response;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ResponseDTO<T> implements Serializable {
    private transient T data;
}
