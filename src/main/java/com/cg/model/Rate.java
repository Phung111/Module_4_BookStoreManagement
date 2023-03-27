package com.cg.model;

import com.cg.model.dto.RateDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "rates")
public class Rate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @OneToMany(targetEntity = User.class, fetch = FetchType.EAGER)
    private List<Book> books;

    public RateDTO toRateDTO(){
        return new RateDTO()
                .setId(id)
                .setName(name)
        ;
    }

}
