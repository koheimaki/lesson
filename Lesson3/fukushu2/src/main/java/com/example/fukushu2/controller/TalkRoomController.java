package com.example.fukushu2.controller;

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

import com.example.fukushu2.model.TalkRoom;
import com.example.fukushu2.model.User;
import com.example.fukushu2.service.TalkRoomService;
import com.example.fukushu2.service.UserService;

import jakarta.persistence.EntityNotFoundException;

@Controller
@RequestMapping("/talkrooms")
public class TalkRoomController {
    @Autowired
    private TalkRoomService talkRoomService;
    @Autowired
    private UserService userService;

    @GetMapping("/list")
    public String showList(Model model) {
        List<TalkRoom> talkrooms = talkRoomService.findAllTalkRooms();
        model.addAttribute("talkrooms", talkrooms);
        return "talkroom/list";
    }

    @GetMapping("/add")
    public String addForm(Model model) {
        model.addAttribute("talkRoom", new TalkRoom());
        model.addAttribute("userList", userService.findAllUsers());
        return "talkroom/add";
    }

    @PostMapping("/add")
    public String add(TalkRoom talkRoom, @RequestParam(name = "users") List<Long> usersId) {
        Set<User> userSet = new HashSet<>();

        for(Long userId : usersId) {
            userSet.add(new User(userId));
        }

        talkRoomService.saveTalkRoomWithUsers(talkRoom, userSet);
        return "redirect:/talkrooms/list";
    }

    @GetMapping("/edit/{talkRoomId}")
    public String editForm(@PathVariable("talkRoomId") Long id, Model model) {
        TalkRoom talkRoom = talkRoomService.findByTalkRoomId(id)
            .orElseThrow(() -> new EntityNotFoundException("TalkRoom not found"));
        model.addAttribute("talkRoom", talkRoom);
        model.addAttribute("users", userService.findAllUsers());
        return "talkroom/edit";
    }

    @PostMapping("/edit")
    public String updateTalkRoom(TalkRoom talkRoom, @RequestParam(name = "users") List<Long> usersId) {
        Set<User> userSet = new HashSet<>();
        for (Long userId : usersId) {
            userSet.add(new User(userId));
        }
        talkRoomService.updateTalkRoomWithUsers(talkRoom, userSet);
        return "redirect:/talkrooms/list";
    }

    @GetMapping("/delete/{talkRoomId}")
    public String delete(@PathVariable(name = "talkRoomId") Long talkRoomId) {
        talkRoomService.deleteTalkRoom(talkRoomId);
        return "redirect:/talkrooms/list";
    }
}
