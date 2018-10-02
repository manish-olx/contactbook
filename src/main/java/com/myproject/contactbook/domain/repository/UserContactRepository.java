package com.myproject.contactbook.domain.repository;


import com.myproject.contactbook.domain.entity.UserContactEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserContactRepository extends JpaRepository<UserContactEntity, Integer> {

    Optional<UserContactEntity> findById(@Param("Id") int id);

    UserContactEntity findByEmail(@Param("email") String email);

    Page<UserContactEntity> findByEmailAndName(Pageable pageable, @Param("email") String email, @Param("name") String name);

    Page<UserContactEntity> findByEmail(Pageable pageable, @Param("email") String email);

    Page<UserContactEntity> findByName(Pageable pageable, @Param("name") String name);

    UserContactEntity findByPhone(@Param("name") String name);
}
