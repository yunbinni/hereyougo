package io.k2c1.hereyougo.domain;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import io.k2c1.hereyougo.constant.Basic;
import io.k2c1.hereyougo.constant.Region;

public class Address
{
    private Long id;
    private Region region; // 광역자치단체: 서울, 경기도 등
    private Basic basic; // 기초자치단체: 의정부시, 관악구 등
    private String detail; // 상세주소 (지번, 도로명)
    private String postalCode; // 우편번호
    
    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;
}