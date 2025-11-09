package jornalApp.Service;

import journalApp.Entity.UserEntry;
import journalApp.JournalApplication;
import journalApp.Repository.UserEntryRepository;
import journalApp.Service.UserEntryService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(classes = JournalApplication.class)
public class UserEntryServiceTests {

    @Autowired
    private UserEntryRepository userEntryRepository;

    @Autowired
    private UserEntryService userEntryService;

//    @BeforeAll , @BeforeAfter

//    @BeforeEach
//    void setUp(){
//
//    }


//    @ValueSource(strings = {
//            "anand",
//            "two"
//    })
    @ParameterizedTest
    @ArgumentsSource(UserArgumentsProvider.class)
    public void testSaveNewUserName(UserEntry user){
        assertTrue(userEntryService.saveNewUser(user));
    }

    @Disabled
    @ParameterizedTest
    @CsvSource({
            "1,1,2",
            "2,10,12",
            "3,3,9"
    })
    public void test(int a,int b,int expected){
        assertEquals(expected,a+b);
    }
}
