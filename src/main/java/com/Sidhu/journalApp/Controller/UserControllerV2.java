package com.Sidhu.journalApp.Controller;

import com.Sidhu.journalApp.Entity.User;
import com.Sidhu.journalApp.apiResponse.WeatherResponse;
import com.Sidhu.journalApp.service.UserService;
import com.Sidhu.journalApp.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;


@RestController
// Keep /user as the canonical route and support the URL currently used in Postman.
@RequestMapping({"/user"})
public class UserControllerV2 {

    @Autowired
    private UserService userService;

    @Autowired
    WeatherService weatherService;

    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody User newUser) {
        if (newUser == null || newUser.getUserName() == null || newUser.getUserName().isBlank()
                || newUser.getPassword() == null || newUser.getPassword().isBlank()) {
            return ResponseEntity.badRequest().body("userName and password are required");
        }
        try {
            userService.saveNewUser(newUser);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (DuplicateKeyException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("That userName is already registered");
        }
    }

    @PutMapping
    public ResponseEntity<?> updateUser(@RequestBody User user) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String  userName = authentication.getName();

        User userIndb = userService.findByUserName(userName);
        if (userIndb != null) {
            if (user.getUserName() != null && !user.getUserName().isBlank()) {
                userIndb.setUserName(user.getUserName());
            }
            if (user.getPassword() != null && !user.getPassword().isBlank()) {
                userService.updatePassword(userIndb, user.getPassword());
            } else {
                userService.saveUser(userIndb);
            }
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping
    public ResponseEntity<?> deleteUserById(@RequestBody User user) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        userService.deleteUserByName(authentication.getName());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping
    public ResponseEntity<?> greeting(@RequestParam() String city) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        WeatherResponse resp = weatherService.getWeather(city);
        String greeting = "";
        if (resp != null){
            greeting = " The weather in " + city + " is " + resp.getCurrent().getCondition().getText() + ".";
        }
        return ResponseEntity.ok("Hi " + authentication.getName() + "." + greeting);
    }
}
