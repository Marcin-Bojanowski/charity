package pl.coderslab.charity.dtos.user;

import lombok.Data;
import pl.coderslab.charity.validation.groups.Registration;
import pl.coderslab.charity.validation.validators.Password;
import pl.coderslab.charity.validation.validators.SamePassword;
import pl.coderslab.charity.validation.validators.UniqueEmail;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
@Data @SamePassword
public class NewUserDTO {
    @NotBlank
    @Email
    @UniqueEmail(groups = Registration.class)
    private String email;
    @NotBlank
    private String name;
    @NotBlank
    private String surname;
    @NotBlank
    @Password
    private String password;
    @NotBlank
    private String rePassword;
}
