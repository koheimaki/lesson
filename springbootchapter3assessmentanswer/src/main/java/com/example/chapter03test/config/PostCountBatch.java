package com.example.chapter03test.config;

import java.util.List;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.example.chapter03test.model.Post;
import com.example.chapter03test.model.User;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Root;

@Component
public class PostCountBatch {

    @PersistenceContext
    private EntityManager entityManager;

    @Scheduled(fixedRate = 60000)
    public void reportCurrentPostCount() {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Object[]> query = cb.createQuery(Object[].class);
        Root<Post> root = query.from(Post.class);
        Join<Post, User> user = root.join("user");

        query.multiselect(user.get("id"), user.get("name"), cb.count(root))
            .groupBy(user.get("id"), user.get("name"))
            .orderBy(cb.asc(user.get("id")));

        List<Object[]> results = entityManager.createQuery(query).getResultList();
        for (Object[] result : results) {
            System.out.println("User ID: " + result[0] + ", Name: " + result[1] + ", Posts Count: " + result[2]);
        }
    }
}
