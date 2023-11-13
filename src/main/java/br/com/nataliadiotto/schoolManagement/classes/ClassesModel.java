package br.com.nataliadiotto.schoolManagement.classes;

import br.com.nataliadiotto.schoolManagement.user.UserModel;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Data
@Entity(name = "tb_classes")
public class ClassesModel {

    @Id // Marks this field as the primary key
    @GeneratedValue(generator = "UUID") // Specifies that the primary key is generated using a UUID (Universally Unique Identifier) generator.
    private UUID id;

    @Column(unique = true) // Indicates that the "name" column should have a unique constraint, ensuring unique values.
    public String name;
    public String classroom;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = UserModel.class) //Defines a many-to-one relationship with the UserModel class.
    @JoinColumn(name = "teacher_id", referencedColumnName = "id", nullable = false) // Specifies the foreign key column and its properties (nullable -> there must be a user_id)
    private UserModel teacher;
    public String language;
    public double duration;

    public ClassesModel() {

    }

   /* //Providing a constructor that can handle the deserialization from a JSON String
    public ClassesModel (String jsonString) {
        //Parse the JSON and set the fields
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            ClassesModel tempClass = objectMapper.readValue(jsonString, ClassesModel.class);
            this.name = tempClass.name != null ? tempClass.name : this.name;
            this.classroom = tempClass.classroom != null ? tempClass.classroom : this.classroom;
            this.language = tempClass.language != null ? tempClass.language : this.language;
            this.duration = tempClass.duration != 0 ? tempClass.duration : this.duration;
            this.teacher = tempClass.teacher != null ? tempClass.teacher : this.teacher;

        } catch (JsonProcessingException e) {
            e.printStackTrace();
            // Handle deserialization error, e.g., log or throw an exception
        }
    }*/

}
