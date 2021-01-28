package pl.coderslab.charity.dtos.email;



import lombok.Data;
import pl.coderslab.charity.validation.groups.Update;
import pl.coderslab.charity.validation.validators.EmailNotExist;

import javax.validation.constraints.Email;


@Data
public class EmailDTO  {
    @Email
    @EmailNotExist(groups=Update.class)
    private String email;
}
