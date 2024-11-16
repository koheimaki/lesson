package com.example.fukushu1.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.fukushu1.model.Shop;
import com.example.fukushu1.repository.ShopRepository;

@Service
public class ShopSerivece {
    @Autowired
    private ShopRepository shopRepository;

    public List<Shop> findAllShops() {
        return shopRepository.findAll();
    }

    public Shop saveShop(Shop shop) {
        return shopRepository.save(shop);
    }

    public Optional<Shop> findShopById(Long id) {
        return shopRepository.findById(id);
    }

    public Shop updateShop(Shop updateShop) {
        return shopRepository.save(updateShop);
    }

    public void deleteShop(Long id) {
        Optional<Shop> optionalShop = shopRepository.findById(id);
        if (optionalShop.isPresent()) {
            Shop shop = optionalShop.get();
            shop.setDeleted(true);
            shopRepository.save(shop);
        }
    }

    public List<Shop> findByNameLike(String name) {
        return shopRepository.findByNameLikeSQL(name + "%");
    }
}
