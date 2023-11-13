// TODO: FIX THE PROFESSOR VALIDATION - VALIDATE USERNAME OR ID?
// TODO: IMPLEMENT CRUD OPERATIONS



package br.com.nataliadiotto.schoolManagement.classes;

import br.com.nataliadiotto.schoolManagement.user.UserModel;
import br.com.nataliadiotto.schoolManagement.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

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


        classesModel.setTeacher(teacher);

        var classCreated = this.classesRepository.save(classesModel);
        return ResponseEntity.status(HttpStatus.OK).body(classCreated);
    }

    @GetMapping("/")
    public List<ClassesModel> listAll() {
        return classesRepository.findAll();
    }

    @GetMapping("/{id}")
    public ClassesModel search(@PathVariable UUID id){
        var tempClass = classesRepository.findById(id);
        if (tempClass.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return tempClass.get();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID id) {
        var tempClass = classesRepository.findById(id);
        if (tempClass.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        classesRepository.delete(tempClass.get());
    }

    @PutMapping("/{id}")
    public ResponseEntity update(@PathVariable UUID id, @RequestBody ClassesModel classesModel) {
        var tempClass = classesRepository.findById(id);
        if (tempClass.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        classesModel.setId(id);
        var updatedClass = this.classesRepository.save(classesModel);
        return ResponseEntity.status(HttpStatus.OK).body(updatedClass);
    }


}
