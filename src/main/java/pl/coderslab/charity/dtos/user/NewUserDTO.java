package pl.coderslab.charity.dtos.user;

import lombok.Data;
import pl.coderslab.charity.validation.groups.Registration;
import pl.coderslab.charity.validation.validators.UniqueEmail;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
@Data
public class NewUserDTO {
    @NotBlank(message = "{not.blank.message}")
    @Email
    @UniqueEmail(groups = Registration.class)
    private String email;
    private String name;
    private String surname;
    @NotBlank(message = "{not.blank.message}")
    @Size(min = 4, max = 12)
    private String password;
    @NotBlank(message = "{not.blank.message}")
    @Size(min = 4, max = 12)
    private String rePassword;
}
