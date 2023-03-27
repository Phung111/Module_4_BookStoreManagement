package com.cg.service.book;

import com.cg.exception.DataInputException;
import com.cg.model.*;
import com.cg.model.dto.*;
import com.cg.repository.*;
import com.cg.service.uploadMedia.UploadService;
import com.cg.utils.UploadUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@Transactional
public class BookService implements IBookService{

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BookAvatarRepository bookAvatarRepository;

    @Autowired
    private UploadService uploadService;

    @Autowired
    private UploadUtils uploadUtils;

    @Override
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    @Override
    public Optional<Book> findById(Long id) {
        return bookRepository.findById(id);
    }

    @Override
    public Boolean existById(Long id) {
        return bookRepository.existsById(id);
    }

    @Override
    public Book save(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public void delete(Book book) {
        book.setDeleted(true);
        bookRepository.save(book);
    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public Boolean existsByNameEquals(String name) {
        return bookRepository.existsByNameEquals(name);
    }

    @Override
    public List<BookAllInfoDTO> findAllByDeletedIsFalse() {
        return bookRepository.findAllByDeletedIsFalse();
    }

    @Override
    public BookResDTO create(BookDTO bookDTO, RateDTO rateDTO, MultipartFile file) {

        Book book = bookDTO.toBook(rateDTO);
        bookRepository.save(book);
        bookDTO.setId(book.getId());

        BookAvatar bookAvatar = new BookAvatar();
        bookAvatar.setBook(book);
        bookAvatarRepository.save(bookAvatar);

        uploadAndSaveBookAvatar(file, bookAvatar);

        return new BookResDTO(bookDTO, rateDTO, bookAvatar.toBookAvatarDTO());
    }

    @Override
    public BookResDTO update(BookDTO bookDTO, RateDTO rateDTO, MultipartFile file) {

        Book book = bookDTO.toBook(rateDTO);
        bookRepository.save(book);
        bookDTO.setId(book.getId());

        BookAvatar bookAvatar = bookAvatarRepository.findByBook(book).get();

        return new BookResDTO(bookDTO, rateDTO, bookAvatar.toBookAvatarDTO());
    }

    @Override
    public BookResDTO updateWithAvatar(BookDTO bookDTO, RateDTO rateDTO, MultipartFile file) throws IOException {

        Book book = bookDTO.toBook(rateDTO);
        bookRepository.save(book);
        bookDTO.setId(book.getId());

        Optional<BookAvatar> bookAvatarOptional = bookAvatarRepository.findByBook(book);

        BookAvatar bookAvatar = new BookAvatar();

        if (!bookAvatarOptional.isPresent()) {
            bookAvatar.setBook(book);
            bookAvatarRepository.save(bookAvatar);
        }
        else {
            bookAvatar = bookAvatarOptional.get();
            uploadService.destroyImage(bookAvatar.getCloudId(), uploadUtils.buildImageUploadParams(bookAvatar));
        }

        uploadAndSaveBookAvatar(file, bookAvatar);

        return new BookResDTO(bookDTO, rateDTO, bookAvatar.toBookAvatarDTO());
    }



    private void uploadAndSaveBookAvatar(MultipartFile file, BookAvatar bookAvatar) {
        try {
            Map uploadResult = uploadService.uploadImage(file, uploadUtils.buildImageUploadParams(bookAvatar));
            String fileUrl = (String) uploadResult.get("secure_url");
            String fileFormat = (String) uploadResult.get("format");

            bookAvatar.setFileName(bookAvatar.getId() + "." + fileFormat);
            bookAvatar.setFileUrl(fileUrl);
            bookAvatar.setFileFolder(uploadUtils.IMAGE_UPLOAD_FOLDER);
            bookAvatar.setCloudId(bookAvatar.getFileFolder() + "/" + bookAvatar.getId());
            bookAvatarRepository.save(bookAvatar);

        } catch (IOException e) {
            e.printStackTrace();
            throw new DataInputException("Upload hình ảnh thất bại");
        }
    }

    @Override
    public Optional<BookAllInfoDTO> findBookAllInfoDTOById(Long id) {
        return bookRepository.findBookAllInfoDTOById(id);
    }
}
