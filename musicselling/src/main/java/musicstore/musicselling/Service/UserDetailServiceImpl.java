package musicstore.musicselling.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
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
        UserEntity currentUser = userRepository.findByUsername(username);

        if (currentUser == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return new CustomUserDetail(currentUser);
    }
}
