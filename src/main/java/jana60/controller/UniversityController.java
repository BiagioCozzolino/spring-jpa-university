package jana60.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jana60.model.Degrees;
import jana60.model.University;
import jana60.repository.DegreesRepository;
import jana60.repository.UniversityRepository;

@Controller
@RequestMapping("/")
public class UniversityController {
	
	@Autowired 
	private UniversityRepository repo;
	@Autowired
	private DegreesRepository repo2;
	
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
	
}
