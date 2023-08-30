package com.blopapi.payload;

import lombok.Data;

@Data
public class SignUpDto {

    private Long id;
    private String name;
    private String username;
    private String email;
    private String password;
}
