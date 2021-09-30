package mn.isolvers.temp.service.internal.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import javax.persistence.GeneratedValue;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table("users")
public class User {

    @Id
    @GeneratedValue
    private Integer id;
    private String name;
    private int age;
    private double salary;

    public User(String name,int age,double salary){
        this.name = name;
        this.age = age;
        this.salary = salary;
    }
}
