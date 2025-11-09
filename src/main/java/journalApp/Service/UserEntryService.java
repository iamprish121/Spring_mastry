package journalApp.Service;

import journalApp.Entity.UserEntry;
import journalApp.Repository.UserEntryRepository;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
public class UserEntryService {
    private static final Logger log = LoggerFactory.getLogger(UserEntryService.class);

    @Autowired
    private UserEntryRepository userEntryRepository;

    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();


    public void saveEntry(UserEntry userEntry){
     userEntryRepository.save(userEntry);
    }

    public void saveNewUser(UserEntry userEntry){
        userEntry.setPassword(passwordEncoder.encode(userEntry.getPassword()));
        userEntry.setRoles(Arrays.asList("USER"));
        userEntryRepository.save(userEntry);
    }

    public void saveAdmin(UserEntry userEntry){
        userEntry.setPassword(passwordEncoder.encode(userEntry.getPassword()));
        userEntry.setRoles(Arrays.asList("USER","ADMIN"));
        userEntryRepository.save(userEntry);
    }

    public List<UserEntry> getAll(){
        return userEntryRepository.findAll();
    }

    public Optional<UserEntry> findById(ObjectId id){
        return userEntryRepository.findById(id);
    }

    public void deleteById(ObjectId id){
        userEntryRepository.deleteById(id);
    }

    public UserEntry findByUserName(String userName){
       return userEntryRepository.findByUserName(userName);
    }
}
