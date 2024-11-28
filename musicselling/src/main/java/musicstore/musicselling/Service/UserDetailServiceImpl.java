package musicstore.musicselling.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import musicstore.musicselling.Entity.UserEntity;
import musicstore.musicselling.Repository.UserRepository;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Retrieves the user from the database using the username
        UserEntity currentUser = userRepository.findByUsername(username);

        // If the user is not found, throw an exception
        if (currentUser == null) {
            throw new UsernameNotFoundException("User not found");
        }

        // Returns a CustomUserDetail object that implements UserDetails
        return new CustomUserDetail(currentUser);
    }
}