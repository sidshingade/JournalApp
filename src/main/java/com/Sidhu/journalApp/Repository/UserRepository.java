package com.Sidhu.journalApp.Repository;

import com.Sidhu.journalApp.Entity.JournalEntry;
import com.Sidhu.journalApp.Entity.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, ObjectId> {

    User findByUserName(String userName);

    void deleteByUserName(String userName);


}
