package GamesCollection.service;

import GamesCollection.model.users.Role;
import GamesCollection.model.users.User;
import GamesCollection.repo.RoleCrudRepository;
import GamesCollection.repo.UserCrudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    private UserCrudRepository userCrudRepository;
    private RoleCrudRepository roleCrudRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserService(UserCrudRepository userCrudRepository, RoleCrudRepository roleCrudRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userCrudRepository = userCrudRepository;
        this.roleCrudRepository = roleCrudRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userCrudRepository.findByUsername(s);
        if(user == null){
            throw new UsernameNotFoundException("User not found");
        }
        return user;
    }

    public User findUserById(Long id){
        Optional<User> userFromDb = userCrudRepository.findById(id);
        return userFromDb.orElse(new User());
    }

    public boolean deleteUser(Long id){
        if(userCrudRepository.findById(id).isPresent()){
            userCrudRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public boolean saveUser(User user){
        User userFromDb = userCrudRepository.findByUsername(user.getUsername());

        if(userFromDb != null){
            return false;
        }

        user.setRoles(Collections.singleton(new Role(1L, "ROLE_USER")));
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userCrudRepository.save(user);
        return true;
    }
}
