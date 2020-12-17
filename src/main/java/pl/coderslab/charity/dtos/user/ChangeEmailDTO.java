package pl.coderslab.charity.dtos.user;

import lombok.Data;
import pl.coderslab.charity.validation.groups.Registration;
import pl.coderslab.charity.validation.groups.Update;
import pl.coderslab.charity.validation.validators.Authenticate;
import pl.coderslab.charity.validation.validators.UniqueEmail;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
@Data
public class ChangeEmailDTO {
    @NotBlank
    @Authenticate
    private String password;
    @NotBlank
    @Email
    @UniqueEmail(groups = Update.class)
    private String newEmail;

}
