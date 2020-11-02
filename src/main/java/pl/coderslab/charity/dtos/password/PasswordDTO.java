package pl.coderslab.charity.dtos.password;

import lombok.Data;

@Data
public class PasswordDTO {

    private String oldPassword;
    private String newPassword;
    private String rePassword;
}
