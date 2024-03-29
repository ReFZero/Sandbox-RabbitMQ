package pl.ReFZero.StudentApi.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@SequenceGenerator(name = "seqIdGen", initialValue = 20000, allocationSize = 1)

public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqIdGen")
    private Long id;
    @NotBlank
    private String firstName;
    @NotEmpty
    @Size(min = 3)
    private String lastName;
    @NotBlank
    @Column(unique = true)
    @Email
    private String email;
    @Enumerated(EnumType.STRING)
    @NotNull
    private Status status;

    public enum Status {
        ACTIVE,
        INACTIVE
    }
}
