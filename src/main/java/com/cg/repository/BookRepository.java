package com.cg.repository;

import com.cg.model.dto.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cg.model.Book;

import java.util.List;
import java.util.Optional;


@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    Boolean existsByNameEquals(String name);

    @Query("SELECT NEW com.cg.model.dto.BookAllInfoDTO (" +
                "b.id, " +
                "b.name, " +
                "b.author, " +
                "b.price, " +
                "b.quantity," +
                "rate.id," +
                "rate.name," +
                "ba.id, " +
                "ba.fileFolder, " +
                "ba.fileName," +
                "ba.fileUrl" +
            ") " +
            "FROM Book AS b " +
            "JOIN Rate AS rate " +
            "ON rate.id = b.rate.id " +
            "LEFT JOIN BookAvatar AS ba " +
            "ON ba.book = b " +
            "WHERE b.deleted = false "
    )
    List<BookAllInfoDTO> findAllByDeletedIsFalse();

    @Query("SELECT NEW com.cg.model.dto.BookAllInfoDTO (" +
            "b.id, " +
            "b.name, " +
            "b.author, " +
            "b.price, " +
            "b.quantity," +
            "rate.id," +
            "rate.name," +
            "ba.id, " +
            "ba.fileFolder, " +
            "ba.fileName," +
            "ba.fileUrl" +
            ") " +
            "FROM Book AS b " +
            "JOIN Rate AS rate " +
            "ON rate.id = b.rate.id " +
            "LEFT JOIN BookAvatar AS ba " +
            "ON ba.book = b " +
            "WHERE b.deleted = false " +
            "AND b.id = :id"
    )
    Optional<BookAllInfoDTO> findBookAllInfoDTOById(@Param("id") Long id);
}
