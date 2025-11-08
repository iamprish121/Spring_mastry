package journalApp.Service;

import journalApp.Entity.UserEntry;
import journalApp.Repository.UserEntryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserEntryRepository userEntryRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       UserEntry user =  userEntryRepository.findByUserName(username);
       if(user != null){
          return User.builder().username(user.getUserName())
                   .password(user.getPassword())
                   .roles(user.getRoles().toArray(new String[0]))
                   .build();
       }
       throw new UsernameNotFoundException("User not found with username:" + username);
    }
}
