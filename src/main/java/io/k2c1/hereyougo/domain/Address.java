package io.k2c1.hereyougo.domain;

import javax.persistence.*;

import lombok.Data;

@Data
@Entity
public class Address
{
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "address_id")
    private Long id;

    private String region; // 광역자치단체: 서울, 경기도 등
    private String basic; // 기초자치단체: 의정부시, 관악구 등
    private String doro; // 도로명주소
    private String jibun; // 지번주소
    private String zipNo; // 우편번호
    
    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;
}