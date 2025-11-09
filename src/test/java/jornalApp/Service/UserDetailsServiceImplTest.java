package jornalApp.Service;

import journalApp.Entity.UserEntry;
import journalApp.Repository.UserEntryRepository;
import journalApp.Service.UserDetailsServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;

import static org.mockito.Mockito.*;

//@SpringBootTest(classes = UserDetailsServiceImpl.class)
public class UserDetailsServiceImplTest {

//    @Autowired
    @InjectMocks
    private UserDetailsServiceImpl userDetailsService;

//    @MockBean
    @Mock
    private UserEntryRepository userEntryRepository;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void loadUserByUsername(){
        when(userEntryRepository.findByUserName(ArgumentMatchers.anyString())).thenReturn(UserEntry.builder().userName("ram").password("ram").roles(new ArrayList<>()).build());
        UserDetails user = userDetailsService.loadUserByUsername("ram");
        Assertions.assertNotNull(user);
    }
}
