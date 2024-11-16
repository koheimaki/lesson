package com.example.bookmanagementsample.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.bookmanagementsample.model.Book;
import com.example.bookmanagementsample.model.Store;
import com.example.bookmanagementsample.repository.StoreRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class StoreService {
    @Autowired
    private StoreRepository storeRepository;

    public List<Store> findAllStores() {
        return storeRepository.findAll();
    }

    public Optional<Store> findStoreById(Long storeId) {
        return storeRepository.findById(storeId);
    }

    public void saveStoreWithBooks(Store store, Set<Book> books) {
        store.setBooks(books);
        storeRepository.save(store);
    }

    @Transactional
    public void updateStoreWithBooks(Store updatedStore, Set<Book> updatedBooks) {
        Store existingStore = storeRepository.findById(updatedStore.getId())
            .orElseThrow(() -> new EntityNotFoundException("Store not found"));

        existingStore.setName(updatedStore.getName());
        existingStore.setBooks(updatedBooks);
        storeRepository.save(existingStore);
    }

    @Transactional
    public void deleteStore(Long storeId) {
        Store store = findStoreById(storeId)
            .orElseThrow(() -> new EntityNotFoundException("Store not found"));
        store.getBooks().clear();
        storeRepository.delete(store);
    }
}
