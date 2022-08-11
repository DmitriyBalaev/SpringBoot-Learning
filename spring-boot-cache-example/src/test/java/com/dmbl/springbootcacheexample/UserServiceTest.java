package com.dmbl.springbootcacheexample;

import com.dmbl.springbootcacheexample.entity.User;
import com.dmbl.springbootcacheexample.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityNotFoundException;

@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
public class UserServiceTest {

    @Autowired
    private UserService service;

    @Test
    public void get(){
        User user1 = service.createUser(new User("Alesha", "Alesha@mail.tu"));
        User user2 = service.createUser(new User("Misha", "Misha@mail.tu"));

        getAndPrint(user1.getId());
        getAndPrint(user2.getId());
        getAndPrint(user1.getId());
        getAndPrint(user2.getId());
    }

    private void getAndPrint(Long id){
        log.info("user found: {}", service.getUser(id));
    }

    @Test
    public  void createWithParameters(){
        createAndPrint("Ivan", "Ivan@mail.ru");
        createAndPrint("Ivan", "Ivan123124@mail.ru");
        createAndPrint("Oleg", "Ivan@mail.ru");

        log.info("all entries are below:");
        service.getAll().forEach(user -> log.info("{}", user.toString()));
    }
    private void createAndPrint(String name, String email){
        log.info("create user: {}", service.createUser(name, email));
    }

    @Test
    public void createAndRefresh(){
        User user1 = service.createOrReturnCached(new User("Anton", "anton@mail.ru"));
        log.info("created user1: {}", user1);

        User user2 = service.createOrReturnCached(new User("Anton", "mashs@mail.ru"));
        log.info("created user2: {}", user2);

        User user3 = service.createAndRefreshCache(new User("Anton", "jora@mail.ru"));
        log.info("created user3: {}", user3);

        User user4 = service.createOrReturnCached(new User("Anton", "serega@mail.ru"));
        log.info("created user4: {}", user4);
    }

    @Test(expected = EntityNotFoundException.class)
    public void delete(){
        User user1 = service.createUser(new User("Vasya", "vasya@mail.ru"));
        log.info("{}", service.getUser(user1.getId()));

        User user2 = service.createUser(new User("Vasya", "vasya@mail.ru"));
        log.info("{}", service.getUser(user2.getId()));

        service.delete(user1.getId());
        service.deleteAndEvict(user2.getId());

        log.info("{}", service.getUser(user1.getId()));
        log.info("{}", service.getUser(user2.getId()));
    }
}
