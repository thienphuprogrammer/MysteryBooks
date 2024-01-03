package com.example.mysterybook.dto.user;

import com.example.mysterybook.dto.IDtoBase;
import com.example.mysterybook.errors.ValidationError;
import com.example.mysterybook.models.User;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

import static com.example.mysterybook.utils.ValidationUtil.*;

@Setter
@Getter
public class RegisterUserDto implements IDtoBase<User> {
    private String username;
    private String fullName;
    private String email;
    private String password;
    private String confirmPassword;
    private String phoneNumber;
    private String address;

    @Override
    public List<ValidationError> validate() {
        List<ValidationError> errors = new ArrayList<>();
        if (!password.equals(confirmPassword)) {
            ValidationError error = new ValidationError();
            error.setName("errorConfirmPassword");
            error.setMessage("Confirm password does not match");
            error.setValue(confirmPassword);
            errors.add(error);
        }
        if (username == null || username.isEmpty()) {
            ValidationError error = new ValidationError();
            error.setName("errorUsername");
            error.setMessage("Username is required");
            error.setValue(username);
            errors.add(error);
        }

        if (username != null && !isUsernameValid(username)) {
            ValidationError error = new ValidationError();
            error.setName("errorUsername");
            error.setMessage("Username is invalid");
            error.setValue(username);
            errors.add(error);
        }

        if (!isEmailValid(email)) {
            ValidationError error = new ValidationError();
            error.setName("errorEmail");
            error.setMessage("Email is invalid");
            error.setValue(email);
            errors.add(error);
        }

        if (!isPasswordValid(password)) {
            ValidationError error = new ValidationError();
            error.setName("errorPassword");
            error.setMessage("Password is invalid");
            error.setValue(password);
            errors.add(error);
        }

        if (!isPhoneValid(phoneNumber)) {
            ValidationError error = new ValidationError();
            error.setName("errorPhone");
            error.setMessage("Phone is invalid");
            error.setValue(phoneNumber);
            errors.add(error);
        }

        if (!isAddressValid(address)) {
            ValidationError error = new ValidationError();
            error.setName("errorAddress");
            error.setMessage("Address is invalid");
            error.setValue(address);
            errors.add(error);
        }

        return errors;
    }

    @Override
    public void loadFromModel(User user) {
        this.username = user.getUsername();
        this.fullName = user.getFullName();
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.phoneNumber = user.getPhoneNumber();
        this.address = user.getAddress();
    }
}
