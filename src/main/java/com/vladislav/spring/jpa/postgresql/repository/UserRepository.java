package com.vladislav.spring.jpa.postgresql.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import java.util.List;

import com.vladislav.spring.jpa.postgresql.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
  List<User> findByIsLink(boolean published);

  List<User> findByAuthorContaining(String author);

  Optional<User> findById(long id);
}
