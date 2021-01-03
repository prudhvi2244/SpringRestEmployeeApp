package raj.prudhvi.in.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.GeneratedValue;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee {

	public Employee(String ename, String ecity, Double esalary) {
		super();
		this.ename = ename;
		this.ecity = ecity;
		this.esalary = esalary;
	}

	@Id
	@GeneratedValue
	private Integer eid;

	@Column(nullable = false)
	private String ename;

	@Column(nullable = false)
	private String ecity;

	@Column(nullable = false)
	private Double esalary;

}
