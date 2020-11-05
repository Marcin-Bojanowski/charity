package pl.coderslab.charity.dtos.password;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class PasswordDTO {

    private String oldPassword;
    @NotBlank
    private String newPassword;
    @NotBlank
    private String rePassword;
}
