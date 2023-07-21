package in.serosoft.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Laptop {
	@Id
	private int code;
	private String brand;
	private int price;
}
