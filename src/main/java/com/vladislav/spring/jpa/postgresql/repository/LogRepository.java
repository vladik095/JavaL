package com.vladislav.spring.jpa.postgresql.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import java.util.List;

import com.vladislav.spring.jpa.postgresql.model.Log;

@Repository
public interface LogRepository extends JpaRepository<Log, Long> {
  List<Log> findByIsLink(boolean link);

  List<Log> findByAuthorContaining(String author);

  Optional<Log> findById(long id);
}
