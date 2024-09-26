package ir.technyx.icm_core.dto.user.icm.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReqIcmUserDto {

    @NotBlank(message = "reqUserDto.username.notBlank")
    @Size(min = 3, max = 50, message = "reqUserDto.username.size")
    private String username;

    @NotBlank(message = "reqUserDto.password.notBlank")
    @Size(min = 3, message = "reqUserDto.password.size")
    private String password;

    @NotBlank(message = "reqUserDto.firstName.notBlank")
    @Size(min = 3, message = "reqUserDto.firstName.size")
    private String firstName;

    private String lastName;

    @NotBlank(message = "reqUserDto.phone.notBlank")
    @Size(min = 11, message = "reqUserDto.phone.size")
    private String phone;

    private String email;

    private boolean rememberMe;

}
