package pl.coderslab.charity.dtos.user;

import lombok.Data;
import pl.coderslab.charity.validation.groups.Registration;
import pl.coderslab.charity.validation.validators.UniqueEmail;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
public class UserDTO {

    private Long id;
    @NotBlank
    @Email
    @UniqueEmail(groups = Registration.class)
    private String email;
    @NotBlank
    private String name;
    @NotBlank
    private String surname;
    private Boolean locked;
}
