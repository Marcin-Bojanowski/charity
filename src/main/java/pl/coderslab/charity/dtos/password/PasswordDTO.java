package pl.coderslab.charity.dtos.password;

import lombok.Data;
import pl.coderslab.charity.validation.validators.Password;
import pl.coderslab.charity.validation.validators.SamePassword;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data @SamePassword
public class PasswordDTO {

    private String oldPassword;
    @NotBlank
    @Password
    private String password;
    @NotBlank
    private String rePassword;
}
