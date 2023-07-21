package in.serosoft.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import in.serosoft.dto.StudentBranchInfo;
import in.serosoft.entity.Student;

public interface StudentRepository extends CrudRepository<Student,Integer>,PagingAndSortingRepository<Student, Integer>{
	public List<Student> findByBranch(String branch);
	public List<Student> findBySemesterBetween(int low, int high);
	@Query("select new in.serosoft.dto.StudentBranchInfo(s.name, s.branch, l.brand, c.name) from Student s join s.laptop l join s.courses c")
	public List<StudentBranchInfo> getStudentBranchInfo();
	@Query("select new in.serosoft.dto.StudentBranchInfo(s.name, s.branch, l.brand) from Student s join s.laptop l where l.brand=?1")
	public List<StudentBranchInfo> getStudentByLaptopBrand(String brand);
}
