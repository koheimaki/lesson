package com.example.slstudiomini.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.slstudiomini.exception.MyUniqueConstraintViolationException;
import com.example.slstudiomini.model.Authority;
import com.example.slstudiomini.model.User;
import com.example.slstudiomini.repository.AuthorityRepository;
import com.example.slstudiomini.repository.UserRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Root;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AuthorityRepository authorityRepository;
    @PersistenceContext
    private EntityManager entityManager;

    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    //ROLE_USERのみ取得
    public List<User> findAllRoleUsers() {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> cq = cb.createQuery(User.class);
        Root<User> user = cq.from(User.class);

        cq.select(user);

        Join<User, Authority> authorities = user.join("authorities");
        cq.where(
            cb.and(
                cb.equal(authorities.get("authority"), "ROLE_USER"),
                cb.isTrue(user.get("enabled"))
            ));
        
            return entityManager.createQuery(cq).getResultList();
    }

    public Optional<User> findByUserId(Long id) {
        return userRepository.findById(id);
    }

    public User addEnableStudentAndHashPassword(User user) {
        // 最初にユニーク性違反のチェックを行う
        User uniqueUser = userRepository.findByUsername(user.getUsername());
        // 既に存在する場合は自作Exceptionをスロー
        if ( uniqueUser != null ) {
            throw new MyUniqueConstraintViolationException("既に存在するユーザーです");
        }

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
