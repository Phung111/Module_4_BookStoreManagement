package com.cg.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BookAllInfoDTO {
    private Long id;
    private String name;
    private String author;
    private String price;
    private String quantity;

    private Long rateId;
    private String rateName;

    private String avatarId;
    private String fileFolder;
    private String fileName;
    private String fileUrl;

    public BookDTO toBookDTO() {
        return new BookDTO()
                .setId(id)
                .setName(name)
                .setAuthor(author)
                .setPrice(price)
                .setQuantity(quantity)
                ;
    }

    public RateDTO toRateDTO(){
        return new RateDTO()
                .setId(rateId)
                .setName(rateName)
                ;
    }

    public BookAvatarDTO bookAvatarDTO() {
        return new BookAvatarDTO()
                .setId(avatarId)
                .setFileFolder(fileFolder)
                .setFileName(fileName)
                .setFileUrl(fileUrl)
                ;
    }

}
