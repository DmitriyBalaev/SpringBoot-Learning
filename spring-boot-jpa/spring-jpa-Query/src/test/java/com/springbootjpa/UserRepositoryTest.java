package com.springbootjpa;

import com.springbootjpa.QueryApp.User;
import com.springbootjpa.QueryApp.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Before
    public void setup(){
        User user = new User("Anton", 1);
        User user2 = new User("Kata", 1);
        User user3 = new User("Dima", 0);
        userRepository.save(user);
        userRepository.save(user2);
        userRepository.save(user3);
    }

    @Test
    public void findAllActiveUsersWithJPQA(){
        Collection<User> activeUser = userRepository.findAllActiveUsers();
        assertEquals(activeUser.size(), 2);
    }

    @Test
    public void findAllActiveUsersWithNativeQuery(){
        Collection<User> activeUser = userRepository.findAllActiveUsersNative();
        assertEquals(activeUser.size(), 2);
    }

    @Test
    public void findUserByName(){
        User userFromRepository = userRepository.findUserByName("Dima");
        assertEquals(userFromRepository.getName(), "Dima");
    }

    @Test
    public void findAllUsersWithSort(){
        List<User> sortUsers = userRepository.findAllUsers(Sort.by("name"));
        assertEquals(userRepository.findUserByName("Dima"), sortUsers.get(1));
    }
}