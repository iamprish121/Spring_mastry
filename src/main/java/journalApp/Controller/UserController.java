package journalApp.Controller;

import journalApp.Entity.UserEntry;
import journalApp.Repository.UserEntryRepository;
import journalApp.Service.UserEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserEntryService userEntryService;

    @Autowired
    private UserEntryRepository userEntryRepository;

    @PutMapping
    public ResponseEntity<?> updateUser(@RequestBody UserEntry user){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();

        UserEntry userIndB = userEntryService.findByUserName(userName);
        userIndB.setUserName(user.getUserName());
        userIndB.setPassword(user.getPassword());
        userEntryService.saveNewUser(userIndB);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<?> deleteUser(@RequestBody UserEntry user){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        userEntryRepository.deleteByUserName(authentication.getName());
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
