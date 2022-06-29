package com.springbootjpa.QueryApp;


import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    /*
      JPQL and Native
    Первый вариант использует JPQL используется по умолчанию. Второй вариант с SQL, для этого нужно
    установить значение атрибута nativeQuery в true  и определить собственный SQL - запрос в атрибуте value.
    */
    @Query("SELECT u FROM User u WHERE u.status = 1")
    Collection<User> findAllActiveUsers();

    @Query(
            value = "SELECT * FROM USERS u WHERE u.status = 1",
            nativeQuery = true
    )
    Collection<User> findAllActiveUsersNative();
    //Не забыть выяснить почему работает без Query
    User findUserByName(String name);
//====================================================================================================================

    @Query(value = "SELECT u FROM User u")
    List<User> findAllUsers(Sort sort);
}
