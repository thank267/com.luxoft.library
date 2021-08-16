package com.luxoft.library.controllers;

import com.luxoft.library.entities.Comment;
import com.luxoft.library.repositories.CommentRepository;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Controller
@RequestMapping("/comments")
public class CommentController {

    private CommentRepository commentRepository;
    

    @GetMapping("/new")
    public String newComment(Model model, @RequestParam long bookId ) {
        model.addAttribute("comment", new Comment());
        model.addAttribute("bookId", bookId);
        return "comments/new";
        
       
    }

    @PostMapping()
    public String create(@ModelAttribute("comment") @Validated Comment comment) {
        commentRepository.save(comment);
        return "redirect:/books/"+ comment.getBook().getId();
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") long id) {
        model.addAttribute("comment", commentRepository.findById(id));
        return "comments/edit";
    }

    @PostMapping("/{id}/update")
    public String update(@ModelAttribute("comment") @Validated Comment comment, @PathVariable("id") long id) {
        comment.setId(id);
        commentRepository.save(comment);
        return "redirect:/books/"+ comment.getBook().getId();

    }

    @GetMapping("/{id}/delete")
    public String delete(@PathVariable("id") long id, @RequestParam long bookId) {
        commentRepository.deleteById(id);
        return "redirect:/books/" + bookId;
    }
    
}
