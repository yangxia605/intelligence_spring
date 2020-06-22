package com.intelligent.dao;

import com.intelligent.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.TransactionScoped;
import javax.transaction.Transactional;
import java.util.Optional;

/**
 * 持久层
 */
public interface UsersDao extends JpaRepository<Users, Integer>{
    // JPA内置的数据库操作，一般多为查找操作，很方便但是有局限，一般可以自己写sql比较方便
    Optional<Users> findById(Integer id);

    // 查询数据
    Users findByNameAndPassword(String name, String password);

    // 查询数据
    @Query(nativeQuery = true, value = "select * from users where name=?1 and password=?2")
    Users getUser(String name, String password);

    // 插入数据操作
    @Transactional
    @Modifying
    @Query(nativeQuery = true,value = "insert into users(name,password) values(?1,?2)")
    void insertUser(String name, String password);

    // 修改数据
    @TransactionScoped
    @Modifying
    @Query(nativeQuery = true, value = "update users set name=?1, gender=?2 where id=?3")
    void updateUser(String name, boolean gender, int id);

    // 删除操作类似

}
