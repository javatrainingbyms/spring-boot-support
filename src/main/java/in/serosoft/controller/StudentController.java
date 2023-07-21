package in.serosoft.controller;

import java.util.List;
import java.util.Spliterator;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.serosoft.dto.GenericResponseEntity;
import in.serosoft.dto.StudentBranchInfo;
import in.serosoft.dto.StudentDTO;
import in.serosoft.entity.Student;
import in.serosoft.service.StudentService;

@RestController
@RequestMapping("/students")
public class StudentController {

	@Autowired
	private StudentService studentService;

	@Autowired
	private DozerBeanMapper mapper;
	
	@GetMapping(value = "/laptop/brand/{br}", produces = "application/json")
	public List<StudentBranchInfo> getStudentByLaptopBrand(@PathVariable("br") String brand) {
		return studentService.getStudentByLaptopBrand(brand);
	}
	@GetMapping(value = "/branchinfo", produces = "application/json")
	public List<StudentBranchInfo> getStudentBranchInfo() {
		return studentService.getStudentBranchInfo();
	}
	@GetMapping(value="sort/{field}")
	public Iterable<Student> findAllSorted(@PathVariable("field") String field) {
		return studentService.findAllSorted(field);
	}
	@GetMapping(value = "/{low}/{high}", produces = "application/json")
	public List<Student> findBySemesterBetween(@PathVariable("low") int low, @PathVariable("high") int high) {
		return studentService.findBySemesterBetween(low, high);
	}
	@GetMapping(value="branch/{br}")
	public List<StudentDTO> findAllByBranch(@PathVariable("br") String branch) {
		List<Student> students= studentService.findByBranch(branch);
		return students.stream().map((student)->mapper.map(student, StudentDTO.class)).collect(Collectors.toList());
	}

	@DeleteMapping(value = "/{id}", produces = "application/json")
	public StudentDTO deleteById(@PathVariable("id") int id) {
		Student student = studentService.delete(id);
		return mapper.map(student, StudentDTO.class);
	}
	@PostMapping(produces="application/json", consumes="application/json")
	public StudentDTO saveStudent(@RequestBody StudentDTO studentDTO) {
		Student student=mapper.map(studentDTO, Student.class);
		Student studentReturned=studentService.save(student);
		return mapper.map(studentReturned, StudentDTO.class);
	}
	@PutMapping(produces="application/json", consumes="application/json")
	public StudentDTO updateStudent(@RequestBody StudentDTO studentDTO) {
		Student student=mapper.map(studentDTO, Student.class);
		Student studentReturned=studentService.save(student);
		return mapper.map(studentReturned, StudentDTO.class);
	}
	@GetMapping(value = "/{id}", produces = "application/json")
	public GenericResponseEntity<StudentDTO> findById(@PathVariable("id") int id) {
		Student student = studentService.findById(id);
		StudentDTO studentDTO=null;
		if (student != null) {
			studentDTO = mapper.map(student, StudentDTO.class);
		}
		GenericResponseEntity<StudentDTO> response = null;
		if (student != null) {
			response = new GenericResponseEntity<>(studentDTO, "success", HttpStatus.OK);
		} else {
			response = new GenericResponseEntity<>(studentDTO, "not found", HttpStatus.NO_CONTENT);
		}
		return response;
	}

	/*
	 * @GetMapping(value="/{id}",produces="application/json") public
	 * ResponseEntity<StudentDTO> findById(@PathVariable("id") int id) { Student
	 * student=studentService.findById(id);
	 * 
	 * ResponseEntity<StudentDTO> response=null; if(student!=null) { response=new
	 * ResponseEntity<>(student,HttpStatus.OK); }else{ response=new
	 * ResponseEntity<>(student,HttpStatus.NO_CONTENT); } return response; }
	 */
	@GetMapping
	public GenericResponseEntity<List<StudentDTO>> findAll() {
	
		System.out.println("called...");
		Iterable<Student> students= studentService.findAll();
		Spliterator<Student> spliter=students.spliterator();
		Stream<Student> stream=StreamSupport.stream(spliter, false);
		
		List<StudentDTO> studentDTOList=stream.map((student)->mapper.map(student, StudentDTO.class)).collect(Collectors.toList());
		/*
		//System.out.println(studentDTOList.size());
		List<StudentDTO> studentDTOList=new ArrayList<>();
		for(Student student:students) {
			studentDTOList.add(mapper.map(student, StudentDTO.class));
		}
		*/
		GenericResponseEntity<List<StudentDTO>> response=new GenericResponseEntity<>(studentDTOList,"success",HttpStatus.OK);
		return response;
	}

	@GetMapping(value="/hello", produces="application/msword")
	public String hello() {
		return "hello user..";
	}

	@GetMapping("/welcome")
	public String welcome() {
		return "welcome user..";
	}
}
