package com.jiyun.nbcschedulerdevelop.exception;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class LoginException extends RuntimeException {

    private String message;

}
