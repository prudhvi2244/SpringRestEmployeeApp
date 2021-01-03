package raj.prudhvi.in.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import raj.prudhvi.in.model.Employee;
import raj.prudhvi.in.service.IEmployeeService;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(value = "/employees")
public class EmployeeRestController {

	@Autowired
	private IEmployeeService employeeService;

	@GetMapping(value = "/getAllEmployees", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Employee>> getAllEmployees() {
		List<Employee> employees = employeeService.getAllEmployees();
		return new ResponseEntity<List<Employee>>(employees, HttpStatus.OK);
	}

	@PostMapping(value = "/saveEmployee", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Employee> saveEmployee(@RequestBody Employee employee) {
		Employee e1= employeeService.saveEmployee(employee);
		return new ResponseEntity<Employee>(e1, HttpStatus.CREATED);
	}

	@GetMapping(value = "/getOneEmployee/{eid}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Employee> getOneEmployee(@PathVariable Integer eid) {
		Employee employee = employeeService.getEmployeeById(eid);
		return new ResponseEntity<Employee>(employee, HttpStatus.OK);
	}

	@DeleteMapping(value = "/deleteOneEmployee/{eid}")
	public ResponseEntity<String> deleteOneEmployee(@PathVariable Integer eid) {
		Employee employee = employeeService.getEmployeeById(eid);
		employeeService.deleteEmployee(employee.getEid());
		return new ResponseEntity<String>("Employee with id :" + eid + " deleted", HttpStatus.OK);
	}

}
