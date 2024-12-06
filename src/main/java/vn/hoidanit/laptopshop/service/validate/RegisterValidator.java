package vn.hoidanit.laptopshop.service.validate;

import org.springframework.stereotype.Service;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import vn.hoidanit.laptopshop.domain.dto.UserDTO;
import vn.hoidanit.laptopshop.service.UserService;

@Service
public class RegisterValidator implements ConstraintValidator<RegisterCheck, UserDTO> {

    private final UserService userService;

    public RegisterValidator(UserService userService) {
        this.userService = userService;
    }

    @Override
    public boolean isValid(UserDTO userDTO, ConstraintValidatorContext context) {
        boolean result = true;

        if (!userDTO.getPassword().equals(userDTO.getConfirmPassword())) {
            context.buildConstraintViolationWithTemplate("Password is not match")
                    .addPropertyNode("confirmPassword")
                    .addConstraintViolation();
            result = false;
        }

        if (checkEmailExist(userDTO.getEmail())) {
            context.buildConstraintViolationWithTemplate("Email is existed.")
                    .addPropertyNode("email")
                    .addConstraintViolation();
            result = false;
        }

        return result;
    }

    public boolean checkEmailExist(String email) {
        return this.userService.handleCheckEmail(email);
    }

}
