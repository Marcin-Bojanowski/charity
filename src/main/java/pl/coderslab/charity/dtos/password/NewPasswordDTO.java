package pl.coderslab.charity.dtos.password;

import lombok.Data;
import pl.coderslab.charity.validation.validators.Password;
import pl.coderslab.charity.validation.validators.SamePassword;

import javax.validation.constraints.NotBlank;

@Data
@SamePassword
public class NewPasswordDTO {
    @NotBlank
    private String token;
    @NotBlank
    @Password
    private String password;
    @NotBlank
    private String rePassword;
}
