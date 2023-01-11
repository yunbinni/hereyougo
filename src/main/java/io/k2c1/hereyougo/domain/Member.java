package io.k2c1.hereyougo.domain;

import lombok.Getter;

import java.util.List;

public class Member
{
    private Long id;
    private String email;
    private String password;
    private String nickname;
    private Address address;
    private List<Post> posts;
    private List<Appointment> appointments;
}
