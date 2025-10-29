package journalApp.Service;

import journalApp.Entity.JournalEntry;
import journalApp.Entity.UserEntry;
import journalApp.Repository.JournalEntryRepository;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Component
public class JournalEntryService {
    private static final Logger log = LoggerFactory.getLogger(JournalEntryService.class);

    @Autowired
    private JournalEntryRepository journalEntryRepository;

    @Autowired
    private UserEntryService userEntryService;


    public void saveEntry(JournalEntry journalEntry, String userName){
            UserEntry user = userEntryService.findByUserName(userName);
            journalEntry.setDate(LocalDate.now());
            JournalEntry saved = journalEntryRepository.save(journalEntry);
            user.getJournalEntries().add(saved);
            userEntryService.saveEntry(user);
    }
    public void saveEntry(JournalEntry journalEntry){
        journalEntry.setDate(LocalDate.now());
        journalEntryRepository.save(journalEntry);
    }

    public List<JournalEntry> getAll(){
        return journalEntryRepository.findAll();
    }

    public Optional<JournalEntry> findById(ObjectId id){
        return journalEntryRepository.findById(id);
    }

    public void deleteById(String userName ,ObjectId id){
        JournalEntry journalEntry = journalEntryRepository.findById(id).get();
        UserEntry userEntry = userEntryService.findByUserName(userName);
        userEntry.getJournalEntries().removeIf(x->x.getId().equals(id));
        userEntryService.saveEntry(userEntry);
        journalEntryRepository.deleteById(id);
    }
}
