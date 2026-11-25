package com.Sidhu.journalApp.repository;

import com.Sidhu.journalApp.Repository.UserRepositoryImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@Import(UserRepositoryImpl.class)
class UserRepositoryImplTest {

    @Autowired
    private UserRepositoryImpl userRepository;

    @Test
    void getUserForSA_returnsUsers() {
        assertNotNull(userRepository.getUserForSA());
    }
}
