package com.example.fukushu2.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.fukushu2.model.TalkRoom;
import com.example.fukushu2.model.User;
import com.example.fukushu2.repository.TalkRoomRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class TalkRoomService {
    @Autowired
    private TalkRoomRepository talkRoomRepository;

    public List<TalkRoom> findAllTalkRooms() {
        return talkRoomRepository.findAll();
    }

    public Optional<TalkRoom> findByTalkRoomId(Long id) {
        return talkRoomRepository.findById(id);
    }

    @Transactional
    public void saveTalkRoomWithUsers(TalkRoom talkRoom, Set<User> userSet) {
        talkRoom.setUsers(userSet);
        talkRoomRepository.save(talkRoom);
    }

    @Transactional
    public void updateTalkRoomWithUsers(TalkRoom updateTalkRoom, Set<User> updateUsers) {
        TalkRoom existingTalkRoom = talkRoomRepository.findById(updateTalkRoom.getId())
            .orElseThrow(() -> new EntityNotFoundException("TalkRoom not found"));
        
        existingTalkRoom.setRoomName(updateTalkRoom.getRoomName());
        existingTalkRoom.setUsers(updateUsers);
        talkRoomRepository.save(existingTalkRoom);
    }

    @Transactional
    public void deleteTalkRoom(Long talkRoomId) {
        TalkRoom talkRoom = findByTalkRoomId(talkRoomId)
            .orElseThrow(() -> new EntityNotFoundException("TalkRoom not found"));
        for (User user : talkRoom.getUsers()) {
            user.setDeleted(true);
        }

        talkRoom.setDeleted(true);
        talkRoomRepository.save(talkRoom);
    }
}
