package io.k2c1.hereyougo.domain;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class Address
{
    private String sido; // 광역자치단체: 서울, 경기도 등
    private String sgg; // 기초자치단체: 의정부시, 관악구 등
    private String doro; // 도로명주소
    private String jibun; // 지번주소
    private String zipNo; // 우편번호
}