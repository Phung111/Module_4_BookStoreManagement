package com.cg.model;


import com.cg.model.dto.BookAllInfoDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "books")
public class Book extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    @NotBlank(message = "Name can not blank")
    private String name;

    @Column(name = "author")
    @NotBlank(message = "Author can not blank")
    private String author;

    @Column(name = "price")
    @NotBlank(message = "Price can not blank")
    private String price;

    @Column(name = "quantity")
    @NotBlank(message = "Quantity can not blank")
    private String quantity;

    @ManyToOne
    @JoinColumn(name = "rate_id", referencedColumnName = "id", nullable = false)
    private Rate rate;

    public BookAllInfoDTO toBookRenderListDTO() {
        return new BookAllInfoDTO()
                .setId(id)
                .setName(name)
                .setAuthor(author)
                .setPrice(price)
                .setQuantity(quantity)
                ;
    }



}
