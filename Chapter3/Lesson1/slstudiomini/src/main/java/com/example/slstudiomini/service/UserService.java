package com.example.slstudiomini.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.slstudiomini.model.Authority;
import com.example.slstudiomini.model.User;
import com.example.slstudiomini.repository.AuthorityRepository;
import com.example.slstudiomini.repository.UserRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AuthorityRepository authorityRepository;

    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> findByUserId(Long id) {
        return userRepository.findById(id);
    }

    public User addEnableStudentAndHashPassword(User user) {
    // 有効化
    user.setEnabled(true);
    // ハッシュ化するクラスの準備
    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    // ハッシュ化
    String hashedPassword = passwordEncoder.encode(user.getPassword());
    // パスワードの詰め直し
    user.setPassword(hashedPassword);

    Authority authority = authorityRepository.findByAuthority("ROLE_USER")
        .orElseThrow(() -> new EntityNotFoundException("Authority Not Found with name=USER"));

    user.setAuthorities(Set.of(authority));

    return userRepository.save(user);
}
}
