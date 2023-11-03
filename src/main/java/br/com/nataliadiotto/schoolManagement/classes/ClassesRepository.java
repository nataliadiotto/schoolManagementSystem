package br.com.nataliadiotto.schoolManagement.classes;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface ClassesRepository extends JpaRepository<ClassesModel, UUID> {

    ClassesModel findByName(String name);

}
