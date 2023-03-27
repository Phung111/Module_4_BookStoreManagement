package com.cg.module_4_exam.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;


@Entity
@Table(name = "thanhphos")
public class ThanhPho {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ten")
    @NotBlank(message = "Thanh Pho can not blank")
    private String ten;

    @Column(name = "quocgia")
    @NotBlank(message = "Quoc Gia can not blank")
    private String quocgia;

    @Column(name = "dientich")
    @NotBlank(message = "Dien tich can not blank")
    @Pattern(regexp="^([0-9]*[1-9][0-9]*(\\.[0-9]+)?|[0]+\\.[0-9]*[1-9][0-9]*)$", message = "Diện tích phải nguyên dương")
    private String dientich;

    @Column(name = "danso")
    @NotBlank(message = "Dan so can not blank")
    @Pattern(regexp="^([0-9]*[1-9][0-9]*(\\.[0-9]+)?|[0]+\\.[0-9]*[1-9][0-9]*)$", message = "Dân số phải nguyên dương")
    private String danso;

    @Column(name = "GDP")
    @NotBlank(message = "GDP can not blank")
    @Pattern(regexp="^([0-9]*[1-9][0-9]*(\\.[0-9]+)?|[0]+\\.[0-9]*[1-9][0-9]*)$", message = "GDP phải nguyên dương")
    private String GDP;

    @Column(name = "mota")
    private String mota;

    public ThanhPho() {
    }

    public ThanhPho(Long id, String ten, String quocgia, String dientich, String danso, String GDP, String mota) {
        this.id = id;
        this.ten = ten;
        this.quocgia = quocgia;
        this.dientich = dientich;
        this.danso = danso;
        this.GDP = GDP;
        this.mota = mota;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getQuocgia() {
        return quocgia;
    }

    public void setQuocgia(String quocgia) {
        this.quocgia = quocgia;
    }

    public String getDientich() {
        return dientich;
    }

    public void setDientich(String dientich) {
        this.dientich = dientich;
    }

    public String getDanso() {
        return danso;
    }

    public void setDanso(String danso) {
        this.danso = danso;
    }

    public String getGDP() {
        return GDP;
    }

    public void setGDP(String GDP) {
        this.GDP = GDP;
    }

    public String getMota() {
        return mota;
    }

    public void setMota(String mota) {
        this.mota = mota;
    }
}
