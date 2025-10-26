//package journalApp.Controller;
//
//import journalApp.Entity.JournalEntry;
//import journalApp.Service.JournalEntryService;
//import org.bson.types.ObjectId;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//import java.time.LocalDate;
//import java.util.*;
//
//@RestController
//@RequestMapping("/journal")
//public class JournalControllerV2 {
//
//    @Autowired
//    private JournalEntryService journalEntryService;
//
//
//    @GetMapping
//    public List<JournalEntry> getAll(){
//     return journalEntryService.getAll();
//    }
//
//    @PostMapping
//    public JournalEntry createEntry(@RequestBody JournalEntry myEntry){
//    myEntry.setDate(LocalDate.now());
//    journalEntryService.saveEntry(myEntry);
//    return myEntry;
//    }
//
//    @GetMapping("id/{myId}")
//    public JournalEntry getById(@PathVariable ObjectId myId){
//       return journalEntryService.findById(myId).orElse(null);
//    }
//
//    @DeleteMapping("id/{myId}")
//    public boolean deleteById(@PathVariable ObjectId myId){
//         journalEntryService.deleteById(myId);
//         return true;
//    }
//
//    @PutMapping("id/{myId}")
//    public JournalEntry updateById(@PathVariable ObjectId myId,@RequestBody JournalEntry updateEntry){
//       JournalEntry old_entry = journalEntryService.findById(myId).orElse(null);
//       if(old_entry != null){
//           old_entry.setTittle(updateEntry.getTittle() !=null && !updateEntry.getTittle().equals("")?updateEntry.getTittle():old_entry.getTittle());
//           old_entry.setContent(updateEntry.getContent()!=null && !updateEntry.getContent().equals("")?updateEntry.getContent():old_entry.getContent());
//       }
//       journalEntryService.saveEntry(old_entry);
//       return old_entry;
//    }
//}
