package br.com.nataliadiotto.schoolManagement.user;

import br.com.nataliadiotto.schoolManagement.classes.ClassesModel;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@Entity(name = "tb_users")
public class UserModel {

    @Id
    @GeneratedValue(generator = "UUID") //automatically generate Id
    private UUID user_id;

    @Column(unique = true) //constraint for granting unique users
    public String username;
    public String name;
    public String password;

   @Column(columnDefinition = "NUMERIC")
    public double hourlyRate;

   @OneToMany(mappedBy = "userProfessor", fetch=FetchType.LAZY,cascade = CascadeType.PERSIST)
   private List<ClassesModel> classes = new ArrayList<>();

    public UserModel() {

    }

    //Providing a constructor that can handle the deserialization from a JSON String
    public UserModel (String jsonString) {
        //Parse the JSON and set the fields
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            UserModel tempUser = objectMapper.readValue(jsonString, UserModel.class);
            this.username = tempUser.username != null ? tempUser.username : this.username;
            this.name = tempUser.name != null ? tempUser.name : this.name;
            this.password = tempUser.password != null ? tempUser.password : this.password;
            this.hourlyRate = tempUser.hourlyRate != 0.0 ? tempUser.hourlyRate : this.hourlyRate;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            // Handle deserialization error, e.g., log or throw an exception
        }
    }


}
