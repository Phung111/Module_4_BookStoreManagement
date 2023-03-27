package com.cg.model.dto;

import com.cg.model.Rate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Accessors(chain = true)
public class RateDTO {

    private Long id;
    private String name;

    public Rate toRate(){
        return new Rate()
                .setId(id)
                .setName(name)
        ;
    }
}
