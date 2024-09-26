package ir.technyx.icm_core.dto.company.page.faq.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReqAskedQuestionItemDtoUpdatableDto extends ReqAskedQuestionItemDto {

    @NotNull
    private Long id;

}
