//package com.Sidhu.journalApp.Service;
//
//import com.Sidhu.journalApp.Entity.User;
//import com.Sidhu.journalApp.Repository.UserRepository;
//import com.Sidhu.journalApp.service.JournalEntryService;
//import com.Sidhu.journalApp.service.UserService;
//import org.junit.jupiter.api.Disabled;
//import org.junit.jupiter.params.ParameterizedTest;
//import org.junit.jupiter.params.provider.ArgumentsSource;
//import org.junit.jupiter.params.provider.CsvSource;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//@SpringBootTest
//public class UserServiceTests {
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @Autowired
//    private JournalEntryService  journalEntryService;
//
//    @Autowired
//    private UserService userService;
//
//    @ParameterizedTest
//    @ArgumentsSource(UserArguments.class)
//    public void testSaveUser(User user){
//        assertTrue(userService.saveNewUser(user));
//    }
//
//    @Disabled
//    @ParameterizedTest
//    @CsvSource({
//            "1,1,2",
//            "2,10,12",
//            "3,3,9"
//    })
//    public void test(int a,int b, int expected){
//        assertEquals(expected,a+b);
//    }
//}
