package com.blopapi.payload;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
public class PostDto {

    private Long id;

    @NotEmpty(message="title should have something")
    @Size(min=2 ,message="title should at least 2 character")
    private String title;

    @NotEmpty(message=" description should have something")
    @Size(min=2, message="description should at least 2 character")
    private String description;

    @NotEmpty(message=" content should have something")
    @Size(min=2 ,message="content should at least 2 character")
    private String content;

}
