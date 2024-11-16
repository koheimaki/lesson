package com.example.bookmanagementsample.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.bookmanagementsample.model.Book;
import com.example.bookmanagementsample.model.Store;
import com.example.bookmanagementsample.service.BookService;
import com.example.bookmanagementsample.service.StoreService;

import jakarta.persistence.EntityNotFoundException;

@Controller
@RequestMapping("/stores")
public class StoreController {
    @Autowired
    private StoreService storeService;
    @Autowired
    private BookService bookService;

    @GetMapping("/show")
    public String show(Model model) {
        List<Store> stores = storeService.findAllStores();
        model.addAttribute("stores", stores);
        return "store/list";
    }

    @GetMapping("/add")
    public String addForm(Model model) {
        model.addAttribute("store", new Store());
        model.addAttribute("books", bookService.findAllBooks());
        return "store/add";
    }

    @PostMapping("/add")
    public String add(Store store, @RequestParam(name = "books") List<Long> books) {
        Set<Book> bookSet = new HashSet<>();
        for (Long bookId : books) {
            bookSet.add(new Book(bookId));
        }

        // 上の処理は以下の様に書くとかっこいいです。
        // Set<Book> bookSet = books.stream()
        //     .map(id -> new Book(id))
        //     .collect(Collectors.toSet());

        storeService.saveStoreWithBooks(store, bookSet);
        return "redirect:/stores/show";
    }

    @GetMapping("/edit/{storeId}")
    public String showEditForm(@PathVariable(name = "storeId") Long storeId, Model model) {
        Store store = storeService.findStoreById(storeId)
            .orElseThrow(() -> new EntityNotFoundException("Store not found"));
        model.addAttribute("store", store);
        model.addAttribute("allBooks", bookService.findAllBooks());
        return "store/edit";
    }

    @PostMapping("/edit")
    public String updateStore(Store store, @RequestParam(name = "books") List<Long> books) {
        Set<Book> bookSet = new HashSet<>();
        for (Long bookId : books) {
            bookSet.add(new Book(bookId));
        }

        // Set<Book> bookSet = books.stream()
        //     .map(bookId -> new Book(bookId))
        //     .collect(Collectors.toSet());

        storeService.updateStoreWithBooks(store, bookSet);
        return "redirect:/stores";
    }

    @GetMapping("/delete/{bookId}")
    public String delete(@PathVariable(name = "bookId") Long bookId) {
        storeService.deleteStore(bookId);
        return "redirect:/stores";
    }
}
