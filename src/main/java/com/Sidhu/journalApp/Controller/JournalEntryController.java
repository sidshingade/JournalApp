//package com.Sidhu.journalApp.Controller;
//
//import com.Sidhu.journalApp.Entity.JournalEntry;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//@RestController
//@RequestMapping("/_journal")
//public class JournalEntryController {
//
//    private Map<Long, JournalEntry> je =new HashMap<>();
//
//    @GetMapping()
//    public List<JournalEntry> getAll() {
//        return new ArrayList<>(je.values());
//    }
//
//    @GetMapping("/id/{myId}")
//    public JournalEntry getEntryById(@PathVariable Long myId) {
//        return je.get(myId);
//    }
//
//    @PostMapping
//    public boolean createEntry(@RequestBody JournalEntry myEntry) {
//        je.put(myEntry.getId(), myEntry);
//        return true;
//    }
//
//    @DeleteMapping("id/{myId}")
//    public JournalEntry deleteEntry(@PathVariable Long myId) {
//        return je.remove(myId);
//    }
//
//    @PutMapping("id/{myId}")
//    public JournalEntry updateEntry(@PathVariable Long myId, @RequestBody JournalEntry myEntry) {
//        return je.put(myId, myEntry);
//    }
//
//}
