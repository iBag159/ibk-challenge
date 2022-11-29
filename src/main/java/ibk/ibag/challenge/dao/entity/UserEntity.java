package ibk.ibag.challenge.dao.entity;

import lombok.*;
import org.springframework.hateoas.RepresentationModel;

import javax.persistence.*;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "app_user", schema = "public")
public class UserEntity extends RepresentationModel<UserEntity> {
    @NonNull
    @Id
    private String code;
    @NonNull
    private String name;
    @NonNull
    private String surname;
    @NonNull
    @Column(name = "document_type")
    private String documentType;
    @NonNull
    @Column(name = "document_number")
    private String documentNumber;
}
