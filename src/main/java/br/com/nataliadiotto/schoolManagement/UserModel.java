package br.com.nataliadiotto.schoolManagement;

import jakarta.persistence.Entity;
import lombok.Data;

@Data
public class UserModel {

    public String username;
    public String name;
    public String password;

}
