package br.com.nataliadiotto.schoolManagement.classes;

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

    @PostMapping("/")
    public ResponseEntity create(@RequestBody ClassesModel classesModel) {
        var classVar = this.classesRepository.findByName(classesModel.getName());


        var classCreated = this.classesRepository.save(classesModel);

        return ResponseEntity.status(HttpStatus.OK).body(classCreated);
    }


}
