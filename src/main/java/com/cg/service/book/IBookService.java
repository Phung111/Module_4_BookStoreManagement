package com.cg.service.book;


import com.cg.model.*;
import com.cg.model.dto.*;
import com.cg.service.IGeneralService;
import org.springframework.data.repository.query.Param;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface IBookService extends IGeneralService<Book> {

    Boolean existsByNameEquals(String name);

    List<BookAllInfoDTO> findAllByDeletedIsFalse();

    BookResDTO create(BookDTO bookDTO, RateDTO rateDTO, MultipartFile file);

    Optional<BookAllInfoDTO> findBookAllInfoDTOById(Long id);

    BookResDTO update(BookDTO bookDTO, RateDTO rateDTO, MultipartFile file);

    BookResDTO updateWithAvatar(BookDTO bookDTO, RateDTO rateDTO, MultipartFile file) throws IOException;
}
