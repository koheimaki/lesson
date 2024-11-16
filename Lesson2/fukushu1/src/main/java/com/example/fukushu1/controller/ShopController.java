package com.example.fukushu1.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.fukushu1.model.Shop;
import com.example.fukushu1.service.ShopSerivece;

@Controller
@RequestMapping("/shops")
public class ShopController {
    @Autowired
    private ShopSerivece shopServiece;

    @GetMapping("/show")
    public String showList(Model model) {
        List<Shop> shops = shopServiece.findAllShops();
        model.addAttribute("shops", shops);
        return "/shop/show";
    }

    @GetMapping("/add")
    public String addFrom(Model model) {
        model.addAttribute("shop", new Shop());
        return "/shop/add";
    }

    @PostMapping("/add")
    public String add(Shop shop) {
        shopServiece.saveShop(shop);
        return "redirect:/shops/add";
    }

    @GetMapping("/edit/{shopId}")
    public String editForm(@PathVariable("shopId") Long shopId, Model model) {
        Shop shop = shopServiece.findShopById(shopId)
            .orElseThrow(() -> new IllegalArgumentException("Inbalid shop Id" + shopId));
        model.addAttribute("shop", shop);
        return "/shop/edit";
    }

    @PostMapping("/edit")
    public String edit(Shop shop) {
        shopServiece.updateShop(shop);
        return "redirect:/shops/show";
    }

    @GetMapping("/search")
    public String search(
            @RequestParam(name = "name", required = false) String name,
            Model model) {
        List<Shop> shops = shopServiece.findByNameLike(name);
        model.addAttribute("shops", shops);
        return "/shop/search";
    }
}
