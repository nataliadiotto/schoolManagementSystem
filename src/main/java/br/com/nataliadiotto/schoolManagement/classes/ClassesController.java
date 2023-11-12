// TODO: FIX THE PROFESSOR VALIDATION - VALIDATE USERNAME OR ID?
// TODO: USE PROFESSOR USERNAME OR NAME AS A FK TOO OR LEAVE THE ID?



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

        //check if the professor exists by username
        UserModel teacher = this.userRepository.findByUsername(classesModel.
                        getTeacher().getUsername());

        //if the professor does not exist, return a bad request response
        if (teacher == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Teacher does not exist.");
        }

        //the professor exists, so set it in the ClassesModel entity
        /* UserModel userProfessor = this.userRepository.
                getReferenceById(classesModel.getUserProfessor().getUser_id());
        classesModel.setUserProfessor(userProfessor);*/
        classesModel.setTeacher(teacher);

        var classCreated = this.classesRepository.save(classesModel);
        return ResponseEntity.status(HttpStatus.OK).body(classCreated);
    }


}
