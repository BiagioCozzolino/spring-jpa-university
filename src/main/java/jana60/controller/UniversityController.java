package jana60.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;

import jana60.model.Degrees;
import jana60.model.Teachers;
import jana60.model.University;
import jana60.repository.DegreesRepository;
import jana60.repository.TeachersRepository;
import jana60.repository.UniversityRepository;

@Controller
@RequestMapping("/")
public class UniversityController {
	
	@Autowired 
	private UniversityRepository repo;
	@Autowired
	private DegreesRepository repo2;
	@Autowired
	private TeachersRepository repo3;
	
	@GetMapping
	public String fristpage() {
		return "fristpage";
	}

	@GetMapping("/dipartimenti")
	public String home(Model model) {
		List<University> DepartmentsList =(List<University>)repo.findAllByOrderByName();
		model.addAttribute("DepartmentsList" ,DepartmentsList);
		//Per testare la lista vuota
		// model.addAttribute("DepartmentsList" ,new ArrayList<>());
		return "home";
	}
	
	@GetMapping("/degrees")
	public String degrees(Model model) {
		List<Degrees> DegreesList = (List<Degrees>)repo2.findAll();
		model.addAttribute("DegreesList" ,DegreesList);
		return "degrees";
	}
	
	@GetMapping("/dipartimenti/{id}")
	public String departmentDetail(Model model, @PathVariable(name = "id") Integer departmentPrimaryKey) {
		University currentDepartment = repo.findById(departmentPrimaryKey).get();
		model.addAttribute("department", currentDepartment);
		return "departmentDetail";
	}
	
	@GetMapping("/teachers")
	public String teachers(Model model) {
		List<Teachers> TeachersList = (List<Teachers>)repo3.findAll();
		model.addAttribute("TeachersList" ,TeachersList);
		return "teachers";
	}
	
	
	@GetMapping("/teachers/{id}")
	public String teachersDetail (Model model, @PathVariable(name = "id") Integer teachersPrimaryKey) {
		Optional<Teachers> queryResult = repo3.findById(teachersPrimaryKey);
	    if (queryResult.isPresent()) {
	    	Teachers currentTeachers = repo3.findById(teachersPrimaryKey).get();
			model.addAttribute("currentTeachers", currentTeachers);
	      return "teachersDetail";
	    } else {
	      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Questo insegnante non esiste");
	    }
	    
}
	
}