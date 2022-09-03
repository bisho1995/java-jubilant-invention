package spring.practice.springrest.entities;

import lombok.*;
import org.hibernate.validator.constraints.Range;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // this is used to auto-increment the field
    private long id;
    @NotNull
    private String name;
    @Size(max = 100, message = "Length of description should be less than 100")
    private String description;
    @Range(min = 1, max = 100, message = "1 to 100, not more or less")
    private Double price;
}
