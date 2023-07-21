package in.serosoft.service;

import java.util.List;

import in.serosoft.dto.StudentBranchInfo;
import in.serosoft.entity.Student;

public interface StudentService {
	public Student findById(int id);
	public Iterable<Student> findAll();
	public Student save(Student student);
	public Student delete(int id);
	public List<Student> findByBranch(String branch);
	public List<Student> findBySemesterBetween(int low, int high);
	public Iterable<Student> findAllSorted(String field);
	public List<StudentBranchInfo> getStudentBranchInfo();
	public List<StudentBranchInfo> getStudentByLaptopBrand(String brand);
}
