package ir.technyx.icm_core.dto.user.accessManagement.request;

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
public class ReqAuthenticationDto {

    @NotBlank(message = "reqUserDto.username.notBlank")
    @Size(max = 50, message = "reqUserDto.username.authenticationSize")
    private String username;

    @NotBlank(message = "reqUserDto.password.notBlank")
    private String password;

}