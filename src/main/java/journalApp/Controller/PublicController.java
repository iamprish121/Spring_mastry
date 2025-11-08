package journalApp.Controller;

import journalApp.Entity.UserEntry;
import journalApp.Service.UserEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/public")
public class PublicController {

    @Autowired
    private UserEntryService userEntryService;

    @PostMapping("/create-user")
    public ResponseEntity<UserEntry> createUser(@RequestBody UserEntry userEntry){
        try{
            userEntryService.saveNewUser(userEntry);
            return new ResponseEntity<>(userEntry, HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
