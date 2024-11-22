package vn.hoidanit.laptopshop.service;

import java.util.List;

import org.springframework.stereotype.Service;

import vn.hoidanit.laptopshop.domain.User;
import vn.hoidanit.laptopshop.repository.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User handleSaveUser(User user) {
        User newUser = this.userRepository.save(user);
        System.out.println(newUser);
        return newUser;

    }

    public List<User> findAll() {
        List<User> arrayUsers = this.userRepository.findAll();
        return arrayUsers;
    }

    public List<User> findAllByEmail(String email) {
        List<User> arrayUsers = this.userRepository.findByEmail(email);
        return arrayUsers;
    }

    public User getUserDetail(long id) {
        User user = this.userRepository.getUserById(id);
        return user;
    }

}
