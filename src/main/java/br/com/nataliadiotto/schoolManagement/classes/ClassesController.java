// TODO: FIX THE PROFESSOR VALIDATION
// TODO: USE PROFESSOR USERNAME OR NAME AS A FK TOO



package br.com.nataliadiotto.schoolManagement.classes;

import br.com.nataliadiotto.schoolManagement.user.UserModel;
import br.com.nataliadiotto.schoolManagement.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/classes")
//https://localhost:8080/classes/

public class ClassesController {

    @Autowired
    private ClassesRepository classesRepository;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/")
    public ResponseEntity create(@RequestBody ClassesModel classesModel) {
        var classVar = this.classesRepository.findByName(classesModel.getName());

        //check if professor exists
        /*UserModel professor = this.userRepository.findByUsername
                (classesModel.getUserProfessor().getUsername());

        if (professor == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Professor does not exist");
        }*/

        //set the saved professor in the ClassesModel entity
        UserModel userProfessor = this.userRepository.
                getReferenceById(classesModel.getUserProfessor().getUser_id());
        classesModel.setUserProfessor(userProfessor);

        var classCreated = this.classesRepository.save(classesModel);

        return ResponseEntity.status(HttpStatus.OK).body(classCreated);
    }


}
