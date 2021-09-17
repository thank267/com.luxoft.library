package com.luxoft.library.services;

import com.luxoft.library.entities.Book;
import com.luxoft.library.entities.Comment;
import com.luxoft.library.repositories.BookRepository;
import com.luxoft.library.repositories.CommentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class CommentService {

    private final CommentRepository commentRepository;

    private final BookRepository bookRepository;

    public List<Comment> findAll() {
        return commentRepository.findAll();
    }

    public Optional<Comment> findById(long id) {
        return commentRepository.findById(id);
    }

    public long save(Comment comment) {
        return commentRepository.save(comment);
    }

    public void delete(long id) {
        commentRepository.deleteById(id);
    }

    @Transactional(rollbackOn = Exception.class)
    public Optional<Comment> create(final long bookId) {
        Comment comment = new Comment();
        Book book = bookRepository.findById(bookId).orElseThrow();
        comment.setBook(book);
        return Optional.of(comment);
    }

}
