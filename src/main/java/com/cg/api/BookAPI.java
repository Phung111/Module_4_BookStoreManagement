package com.cg.api;

import com.cg.model.*;
import com.cg.exception.*;
import com.cg.model.dto.*;
import com.cg.service.book.IBookService;
import com.cg.service.rate.IRateService;
import com.cg.utils.AppUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/books")
public class BookAPI {

    @Autowired
    private IRateService rateService;

    @Autowired
    private IBookService bookService;

    @Autowired
    private AppUtils appUtils;

    @GetMapping
    public ResponseEntity<?> getAllBooks() {

        List<BookAllInfoDTO> bookAllInfoDTOS = bookService.findAllByDeletedIsFalse();
        return new ResponseEntity<>(bookAllInfoDTOS, HttpStatus.OK);
    }

    @GetMapping("/rate")
    public ResponseEntity<?> getAllRates() {

        List<RateDTO> rateDTOS = rateService.findAllRateDTOS();
        return new ResponseEntity<>(rateDTOS, HttpStatus.OK);
    }

    @GetMapping("/{bookId}")
    public ResponseEntity<?> getById(@PathVariable Long bookId) {

        Optional<BookAllInfoDTO> bookAllInfoDTOOptional = bookService.findBookAllInfoDTOById(bookId);

        if (!bookAllInfoDTOOptional.isPresent()) {
            throw new ResourceNotFoundException("Book not valid");
        }

        BookDTO bookDTO = bookAllInfoDTOOptional.get().toBookDTO();
        RateDTO rateDTO = bookAllInfoDTOOptional.get().toRateDTO();
        BookAvatarDTO bookAvatarDTO = bookAllInfoDTOOptional.get().bookAvatarDTO();

        BookResDTO bookResDTO = new BookResDTO(bookDTO, rateDTO, bookAvatarDTO);


        return new ResponseEntity<>(bookResDTO, HttpStatus.OK);
    }


    @PostMapping("/create")
    public ResponseEntity<?> create(@Validated BookReqDTO bookReqDTO, BindingResult bindingResult) {

        new BookReqDTO().validate(bookReqDTO, bindingResult);
        if(bindingResult.hasFieldErrors()){
            return appUtils.mapErrorToResponse(bindingResult);
        }

        BookDTO bookDTO = bookReqDTO.toBookDTO();
        RateDTO rateDTO = bookReqDTO.toRateDTO();
        MultipartFile file = bookReqDTO.getFile();

        Boolean existName = bookService.existsByNameEquals(bookDTO.getName());
        if (existName) {
            throw new EmailExistsException("The name of book is exists");
        }

        if (file != null) {

            BookResDTO bookResDTO = bookService.create(bookDTO, rateDTO, file);

            return new ResponseEntity<>(bookResDTO, HttpStatus.CREATED);
        }
        else {

            bookDTO.setId(null);
            Book book = bookDTO.toBook(rateDTO);
            book = bookService.save(book);

            BookResDTO bookResDTO = new BookResDTO();
            bookResDTO.setBook(bookDTO);
            bookResDTO.setRate(rateDTO);

            return new ResponseEntity<>(bookResDTO, HttpStatus.CREATED);
        }
    }

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @PatchMapping("/update/{bookId}")
    public ResponseEntity<?> update(@PathVariable Long bookId, @Validated BookReqDTO bookReqDTO, BindingResult bindingResult) throws IOException {

        new BookReqDTO().validate(bookReqDTO, bindingResult);
        if(bindingResult.hasFieldErrors()){
            return appUtils.mapErrorToResponse(bindingResult);
        }

        BookDTO bookDTO = bookReqDTO.toBookDTO();
        RateDTO rateDTO = bookReqDTO.toRateDTO();
        MultipartFile file = bookReqDTO.getFile();
        bookDTO.setId(bookId);

        if (file != null) {

            BookResDTO bookResDTO = bookService.updateWithAvatar(bookDTO, rateDTO, file);

            return new ResponseEntity<>(bookResDTO, HttpStatus.CREATED);
        }
        else {

            BookResDTO bookResDTO = bookService.update(bookDTO, rateDTO, file);

            return new ResponseEntity<>(bookResDTO, HttpStatus.CREATED);
        }
    }

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @PostMapping("/delete/{bookId}")
    public ResponseEntity<Book> delete(@PathVariable Long bookId) {
        Book book = bookService.findById(bookId).get();
        bookService.delete(book);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
