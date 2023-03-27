package com.cg.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BookResDTO {

    private BookDTO book;
    private RateDTO rate;
    private BookAvatarDTO bookAvatar;
}
