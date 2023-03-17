package com.neo.demo.user.infrastructure.repository.mapper;

import com.neo.demo.user.infrastructure.repository.po.UserPO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMapper extends JpaRepository<UserPO, String> {
    UserPO getByUsername(String username);

    boolean existsByUsername(String username);

    @Query("select userId from UserPO order by username")
    List<String> findAllUserIds();

    @Query("select username from UserPO order by username")
    List<String> findAllUsernames();

}
