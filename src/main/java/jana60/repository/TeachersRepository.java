package jana60.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import jana60.model.Teachers;

@Repository
public interface TeachersRepository extends CrudRepository<Teachers, Integer>{

}
