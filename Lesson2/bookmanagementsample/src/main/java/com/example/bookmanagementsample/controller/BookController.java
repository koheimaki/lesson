package com.example.bookmanagementsample.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.bookmanagementsample.model.Book;
import com.example.bookmanagementsample.service.BookService;
import com.example.bookmanagementsample.service.GenreService;

import jakarta.persistence.EntityNotFoundException;

@Controller
@RequestMapping("/books")
public class BookController {
    @Autowired
    private BookService bookService;
    @Autowired
    private GenreService genreService;

    @GetMapping("/list")
    public String listBook(Model model) {
        List<Book> books = bookService.findAllBooks();
        model.addAttribute("books", books);
        return "books/list";
    }

    @GetMapping("/add")
    public String addBookForm(Model model) {
        model.addAttribute("book", new Book());
        model.addAttribute("genres", genreService.findAllGenres());
        return "books/add";
    }

    @PostMapping("/add")
    public String addBook(Book book) {
        bookService.saveBook(book);
        return "redirect:/books/list";
    }

    @GetMapping("/edit/{bookId}")
    public String editBookForm(@PathVariable("bookId") Long bookId, Model model) {
        Book book = bookService.findBookById(bookId)
                .orElseThrow(() -> new EntityNotFoundException("Book not found with id: " + bookId));
        model.addAttribute("book", book);
        return "books/edit";
    }

    @PostMapping("/edit")
    public String editBookForm(Book book) {
        bookService.updateBook(book);
        return "redirect:/books/list";
    }

    @GetMapping("/delete/{bookId}")
    public String deleteBook(@PathVariable("bookId") Long bookId) {
        bookService.deleteBookById(bookId);
        return "redirect:/books/list";
    }

    @GetMapping("/search")
    public String search(
                @RequestParam(name = "author", required = false) String author,
                Model model) {
        List<Book> books = bookService.findByAuthor(author);
        model.addAttribute("books", books);
        return "/books/search";
    }
}
