package in.serosoft.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentBranchInfo {
	private String name;
	private String branch;
	private String brand;
	private String cname;
	public StudentBranchInfo(String name, String branch, String brand) {
		super();
		this.name = name;
		this.branch = branch;
		this.brand = brand;
	}
	
	
}
