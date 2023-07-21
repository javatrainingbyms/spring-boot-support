package in.serosoft.dto;

import lombok.Data;

@Data
public class StudentDTO {
	private int id;
	private String name;
	private String email;
	private String branch;
	private int semester;
}
