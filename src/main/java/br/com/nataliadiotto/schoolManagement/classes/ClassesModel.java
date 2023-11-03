package br.com.nataliadiotto.schoolManagement.classes;

import br.com.nataliadiotto.schoolManagement.user.UserModel;
import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Data
@Entity(name = "tb_classes")
public class ClassesModel {

    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;

    @Column(unique = true)
    public String name;
    public String classroom;
    @ManyToOne
    @JoinColumn(name = "professor_id") // You should specify the actual column name that links to the professor's ID
    private UserModel userProfessor;
    public String language;
    public double duration;

}
