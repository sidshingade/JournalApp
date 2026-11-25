package com.Sidhu.journalApp.Scheduler;

import com.Sidhu.journalApp.Entity.JournalEntry;
import com.Sidhu.journalApp.Entity.User;
import com.Sidhu.journalApp.Enums.Sentiment;
import com.Sidhu.journalApp.Repository.UserRepositoryImpl;
import com.Sidhu.journalApp.cache.Appcache;
import com.Sidhu.journalApp.service.EmailService;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
@EnableScheduling
public class UserScheduler {

    @Autowired
    private EmailService emailService;

    @Autowired
    private UserRepositoryImpl  userRepositoryimpl;

//    @Autowired
//    private SentimentAnalysisService  sentimentAnalysisService;

    @Autowired
    private Appcache appcache;

//    @Scheduled(cron="0 0 9 * * SUN")
    public void fetchUserAndSentimentAnalysis(){

        List<User> users = userRepositoryimpl.getUserForSA();
        for(User user:users){
            List<JournalEntry> journalEntries = user.getJournalEntries();
            List<Sentiment> sentiments = journalEntries.stream().filter(x -> x.getDate().isAfter(LocalDateTime.now().minus(7, ChronoUnit.DAYS))).map(x -> x.getSentiment()).collect(Collectors.toList());
            Map<Sentiment,Integer> sentimentCounts =new HashMap<>();
            for(Sentiment sentiment:sentiments){
                if(sentiment!=null) {
                    sentimentCounts.put(sentiment, sentimentCounts.getOrDefault(sentiment, 0) + 1);
                }
            }
            Sentiment mostFrequentSenti =null;
            int maxcount =0;
            for(Map.Entry<Sentiment,Integer> entry:sentimentCounts.entrySet()){
                if(entry.getValue()>maxcount){
                    maxcount=entry.getValue();
                    mostFrequentSenti =entry.getKey();
                }
            }
            if(mostFrequentSenti!=null){
                emailService.sendMail(user.getEmail(), "Sentiment for last 7 days", mostFrequentSenti.toString());
            }
        }

    }

    @Scheduled(initialDelay = 5000, fixedDelay = 600000)
    public void clearAppCache(){
        appcache.init();
    }
}
