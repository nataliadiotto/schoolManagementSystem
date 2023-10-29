package br.com.nataliadiotto.schoolManagement.user;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

import java.util.UUID;

@Data
@Entity(name = "tb_users")
public class UserModel {

    @Id
    @GeneratedValue(generator = "UUID") //automatically generate Id
    private UUID id;

    @Column(unique = true) //constraint for granting unique users
    public String username;
    public String name;
    public String password;
    public String hourlyRate;

}
