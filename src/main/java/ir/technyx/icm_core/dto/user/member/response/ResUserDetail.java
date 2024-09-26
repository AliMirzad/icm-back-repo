package ir.technyx.icm_core.dto.user.member.response;

import ir.technyx.icm_core.dto.common.response.ResBaseDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResUserDetail extends ResBaseDto {

    private Long id;

    private String username;

    private String firstName;

    private String lastName;

    private LocalDate birthDate;

    private Long genderId;

    private Long locationInfoId;

    private String exactLocation;

    private String postalCode;

    private String phone;

    private String email;

    private String nationalCode;

}
