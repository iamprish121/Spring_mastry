package journalApp.Controller;

import journalApp.Entity.UserEntry;
import journalApp.Service.UserEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
     private UserEntryService userEntryService;

     @GetMapping("/all-users")
     public ResponseEntity<?> getAllUsers(){
         List<UserEntry> all = userEntryService.getAll();
         if(all != null && !all.isEmpty()){
             return new ResponseEntity<>(all, HttpStatus.OK);
         }
         return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/create-admin-user")
    public void createUser(@RequestBody UserEntry userEntry){
        userEntryService.saveAdmin(userEntry);
    }

}
