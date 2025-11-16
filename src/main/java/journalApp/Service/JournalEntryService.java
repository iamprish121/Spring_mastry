package journalApp.Service;

import journalApp.Entity.JournalEntry;
import journalApp.Entity.UserEntry;
import journalApp.Repository.JournalEntryRepository;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
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
    @Transactional
    public void saveEntry(JournalEntry journalEntry, String userName){
        try{
            UserEntry user = userEntryService.findByUserName(userName);
            journalEntry.setDate(LocalDate.now());
            JournalEntry saved = journalEntryRepository.save(journalEntry);
            user.getJournalEntries().add(saved);
//            user.setUserName(null);
            userEntryService.saveEntry(user);
        }catch (Exception e){
            System.out.println(e);
            throw new RuntimeException("An occurred while performing transactional");
        }
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

   @Transactional
    public boolean deleteById(String userName ,ObjectId id){
        try {
            UserEntry userEntry = userEntryService.findByUserName(userName);
            boolean removed = userEntry.getJournalEntries().removeIf(x->x.getId().equals(id));
            if(removed){
                userEntryService.saveEntry(userEntry);
                journalEntryRepository.deleteById(id);
            }
            return removed;
        }catch (Exception e){
            System.out.println(e);
            throw new RuntimeException("An Error occurred while deleting the entry",e);
        }
    }
}
