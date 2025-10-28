package journalApp.Controller;

import journalApp.Entity.UserEntry;
import journalApp.Service.UserEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserEntryService userEntryService;

    @GetMapping
    public List<UserEntry> getAllUsers(){
        return userEntryService.getAll();
    }

    @PostMapping
    public ResponseEntity<UserEntry> createUser(@RequestBody UserEntry userEntry){
        try{
            userEntryService.saveEntry(userEntry);
            return new ResponseEntity<>(userEntry,HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    @PutMapping("/{userName}")
    public ResponseEntity<?> updateUser(@RequestBody UserEntry user,@PathVariable String userName){
        UserEntry userIndB = userEntryService.findByUserName(userName);
        if(userIndB != null){
            userIndB.setUserName(user.getUserName());
            userIndB.setPassword(user.getPassword());
            userEntryService.saveEntry(userIndB);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
