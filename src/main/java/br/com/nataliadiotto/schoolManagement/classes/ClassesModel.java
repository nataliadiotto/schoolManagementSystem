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

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = UserModel.class)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", nullable = false)
    private UserModel userProfessor;
    public String language;
    public double duration;


}
