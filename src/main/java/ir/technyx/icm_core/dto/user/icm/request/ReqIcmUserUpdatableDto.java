package ir.technyx.icm_core.dto.user.icm.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReqIcmUserUpdatableDto {

    @NotNull(message = "reqUserDto.id.notNull")
    private Long id;

    @NotBlank(message = "reqUserDto.username.notBlank")
    @Size(min = 3, max = 50, message = "reqUserDto.username.size")
    private String username;

    @NotBlank(message = "reqUserDto.firstName.notBlank")
    @Size(min = 3, message = "reqUserDto.firstName.size")
    private String firstName;

    private String lastName;

    @NotBlank(message = "reqUserDto.phone.notBlank")
    @Size(min = 11, message = "reqUserDto.phone.size")
    private String phone;

    private String email;

}
