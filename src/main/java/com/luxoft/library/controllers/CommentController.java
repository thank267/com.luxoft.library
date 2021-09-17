package com.luxoft.library.controllers;

import com.luxoft.library.DTO.CommentDTO;
import com.luxoft.library.entities.Comment;
import com.luxoft.library.services.CommentService;
import com.luxoft.library.utils.ObjectMapperUtils;

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

    private final CommentService commentService;

    @GetMapping("/new")
    public String newComment(Model model, @RequestParam long bookId) {
        model.addAttribute("comment",
                commentService.create(bookId).map(au -> ObjectMapperUtils.map(au, CommentDTO.class)));
        return "comments/edit";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") long id) {
        model.addAttribute("comment",
                commentService.findById(id).map(cm -> ObjectMapperUtils.map(cm, CommentDTO.class)));
        return "comments/edit";
    }

    @PostMapping()
    public String save(@ModelAttribute("comment") @Validated CommentDTO comment) {
        commentService.save(ObjectMapperUtils.map(comment, new Comment()));
        return "redirect:/books/" + comment.getBookId();

    }

    @GetMapping("/{id}/delete")
    public String delete(@PathVariable("id") long id, @RequestParam long bookId) {
        commentService.delete(id);
        return "redirect:/books/" + bookId;
    }

}
