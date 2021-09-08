package models;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Employee {

    private Long id;
    private String name;
    private String status;
    private Position position;

}
