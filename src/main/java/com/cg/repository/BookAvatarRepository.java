package com.cg.repository;

import com.cg.model.BookAvatar;
import com.cg.model.Book;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.Optional;

@Repository
public interface BookAvatarRepository extends JpaRepository<BookAvatar, String> {
    Optional<BookAvatar> findByBook(Book book);
}
