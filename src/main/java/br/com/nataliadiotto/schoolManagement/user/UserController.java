// TODO: DECIDE WHAT TO DO WITH THE ARRAYLIST


package br.com.nataliadiotto.schoolManagement.user;

import at.favre.lib.crypto.bcrypt.BCrypt;
import br.com.nataliadiotto.schoolManagement.classes.ClassesModel;
import jakarta.persistence.OneToMany;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

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

    @OneToMany(mappedBy = "teacher") // mappedBy points to the corresponding field in the Classes entity
    private List<ClassesModel> classes; // This represents the classes taught by the teacher

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID id) {
        var user = userRepository.findById(id);
        if (user.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        userRepository.delete(user.get());
    }
}


