//package com.Sidhu.journalApp.Service;
//
//import com.Sidhu.journalApp.Entity.User;
//import com.Sidhu.journalApp.Repository.UserRepository;
//import com.Sidhu.journalApp.service.UserDetailsServiceimpl;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.ArgumentMatchers;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.stereotype.Component;
//import org.springframework.test.context.bean.override.mockito.MockitoBean;
//
//import java.util.ArrayList;
//
//import static org.mockito.Mockito.*;
//
//@Component
//public class UserDetailsImptests {
//
//    @InjectMocks
//    private UserDetailsServiceimpl userDetailsService;
//
//    @Mock
//    private UserRepository userRepository;
//
//    @BeforeEach
//    void setUp(){
//        MockitoAnnotations.initMocks(this);
//    }
//
//    @Test
//    void loadUserByName(){
//        when(userRepository.findByUserName(ArgumentMatchers.anyString())).thenReturn(User.builder().userName("Sidhu").password("iloveyou").roles(new ArrayList<>()).build());
//        UserDetails userDetails = userDetailsService.loadUserByUsername("Sidhu");
//        Assertions.assertNotNull(userDetails);
//    }
//}
