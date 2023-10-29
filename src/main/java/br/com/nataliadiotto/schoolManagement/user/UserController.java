package br.com.nataliadiotto.schoolManagement.user;

import at.favre.lib.crypto.bcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
//https://localhost:8080/users//

public class UserController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/")
    public ResponseEntity create(@RequestBody UserModel userModel) {
        var user = this.userRepository.findByUsername(userModel.getUsername());

        if (user != null) {
            //error message
            //status code
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User already exists!");
        }

        //encrypt password
        var hashedPassword = BCrypt.withDefaults()
                .hashToString(12, userModel.getPassword().toCharArray());

        userModel.setPassword(hashedPassword);

        var userCreated = this.userRepository.save(userModel);
        return ResponseEntity.status(HttpStatus.OK).body(userCreated);
    }

}

