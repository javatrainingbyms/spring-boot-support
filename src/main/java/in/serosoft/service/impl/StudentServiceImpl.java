package in.serosoft.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import in.serosoft.dto.StudentBranchInfo;
import in.serosoft.entity.Student;
import in.serosoft.repository.StudentRepository;
import in.serosoft.service.StudentService;

@Service
public class StudentServiceImpl implements StudentService {

	@Autowired
	private StudentRepository studentRepository;
	
	public Student deleteById(int id) {
		Student student=findById(id);
		studentRepository.deleteById(id);
		return student;
	}
	
	@Override
	public Student findById(int id) {
		Optional<Student> optional=studentRepository.findById(id);
		Student student=null;
		if(optional.isPresent()){
			student=optional.get();
		}
		return student;
	}

	@Override
	public Iterable<Student> findAll() {
		Iterable<Student> students=studentRepository.findAll();
		return students;
	}

	@Override
	public Student save(Student student) {
		return studentRepository.save(student);
	}

	@Override
	public Student delete(int id) {
		Student student=studentRepository.findById(id).get();
		studentRepository.deleteById(id);
		return student;
	}

	@Override
	public List<Student> findByBranch(String branch) {
		return studentRepository.findByBranch(branch);
	}

	@Override
	public List<Student> findBySemesterBetween(int low, int high) {
		return studentRepository.findBySemesterBetween(low, high);
	}

	@Override
	public Iterable<Student> findAllSorted(String field) {
		Sort sort=Sort.by(field);
		return studentRepository.findAll(sort);
	}

	@Override
	public List<StudentBranchInfo> getStudentBranchInfo() {
		return studentRepository.getStudentBranchInfo();
	}

	@Override
	public List<StudentBranchInfo> getStudentByLaptopBrand(String brand) {
		return studentRepository.getStudentByLaptopBrand(brand);
	}

}
