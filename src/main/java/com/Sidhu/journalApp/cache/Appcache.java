package com.Sidhu.journalApp.cache;

import com.Sidhu.journalApp.Entity.ConfigJournalAppEntity;
import com.Sidhu.journalApp.Repository.ConfigJournalAppRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class Appcache{

    private static final Logger log = LoggerFactory.getLogger(Appcache.class);

    public enum keys{
        WEATHER_API;
    }

    @Autowired
    private ConfigJournalAppRepository configJournalAppRepository;

   public final Map<String,String> APPCACHE = new ConcurrentHashMap<>();

    public void init(){
        try {
            List<ConfigJournalAppEntity> all = configJournalAppRepository.findAll();
            Map<String, String> refreshedCache = new ConcurrentHashMap<>();
            for (ConfigJournalAppEntity entity : all) {
                refreshedCache.put(entity.getKey(), entity.getValue());
            }
            APPCACHE.clear();
            APPCACHE.putAll(refreshedCache);
        } catch (DataAccessException exception) {
            log.warn("Could not refresh application cache from MongoDB; retaining the current cache", exception);
        }
    }
}
