package ru.dias.springdatainterview.persistence.entities;

import lombok.*;
import ru.dias.springdatainterview.persistence.entities.utils.PersistableEntity;

import javax.persistence.Entity;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Student extends PersistableEntity {

    @Size(max=128)
    @NonNull
    private String name;

    @Min(value = 18)
    @Max(value = 65)
    private int age;
}
