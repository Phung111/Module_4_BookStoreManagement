package com.cg.model.dto;
import com.cg.model.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BookDTO {

    private Long id;
    private String name;
    private String author;
    private String price;
    private String quantity;

    public Book toBook(RateDTO rateDTO) {
        return new Book()
                .setId(id)
                .setName(name)
                .setAuthor(author)
                .setPrice(price)
                .setQuantity(quantity)
                .setRate(rateDTO.toRate())
                ;
    }

}
