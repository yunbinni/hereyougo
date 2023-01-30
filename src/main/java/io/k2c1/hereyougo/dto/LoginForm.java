package io.k2c1.hereyougo.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class LoginForm
{
    @NotBlank
    private String email;

    @NotBlank
    private String password;
}
