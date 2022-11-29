package ibk.ibag.challenge.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.springframework.hateoas.RepresentationModel;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@ToString
@RequiredArgsConstructor
@ApiModel(description = "System user")
public class UserDTO extends RepresentationModel<UserDTO> {

    @NotNull
    @NonNull
    @NotBlank
    @ApiModelProperty(notes = "Unique identifier of the user", example = "u1", required = true, position = 0)
    private String code;

    @NotNull
    @NonNull
    @Size(min = 2, max = 20, message = "{app.field.name.error}")
    private String name;

    @NotNull
    @NonNull
    @NotBlank
    @Size(min = 2, max = 20,  message = "{app.field.surname.error}")
    private String surname;

    @NotNull
    @NonNull
    @NotBlank
    private String documentType;

    @NotNull
    @NonNull
    @Pattern(regexp = "^[0-9]{8}$", message = "{app.field.documentNumber.error}")
    private String documentNumber;
}
