package vn.hoidanit.laptopshop.service;

import java.util.List;

import org.springframework.stereotype.Service;

import jakarta.validation.Valid;
import vn.hoidanit.laptopshop.domain.Role;
import vn.hoidanit.laptopshop.domain.User;
import vn.hoidanit.laptopshop.domain.dto.UserDTO;
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

    public void handleUpdateUser(User userUpdate) {
        User user = this.userRepository.getUserById(userUpdate.getId());
        user.setFullName(userUpdate.getFullName());
        user.setAddress(userUpdate.getAddress());
        user.setTelephone(userUpdate.getTelephone());
        this.userRepository.save(user);
    }

    public void removeUserById(long id) {
        this.userRepository.deleteById(id);
    }

    public boolean handleCheckEmail(String email) {
        return this.userRepository.existsByEmail(email);
    }

    public User handleConvertToUser(UserDTO userDTO) {
        User user = new User();
        if (userDTO.getLastName() != null) {
            user.setFullName(userDTO.getFirstName() + " " + userDTO.getLastName());
        } else {
            user.setFullName(userDTO.getFirstName());
        }
        user.setPassword(userDTO.getPassword());
        user.setEmail(userDTO.getEmail());
        return user;
    }

    public User findByEmail(String email) {
        return this.userRepository.findOneByEmail(email);
    }

}
