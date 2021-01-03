package raj.prudhvi.in.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import raj.prudhvi.in.dao.EmployeeRepository;
import raj.prudhvi.in.exception.EmployeeNotFoundException;
import raj.prudhvi.in.model.Employee;

@Service
public class EmployeeServiceImpl implements IEmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;

	@Override
	public Employee saveEmployee(Employee employee) {
		Employee e1 = employeeRepository.save(employee);
		Integer eid = e1.getEid();
		return e1;
	}

	@Override
	public Employee getEmployeeById(Integer eid) {
		Optional<Employee> opt = employeeRepository.findById(eid);
		Employee employee = opt
				.orElseThrow(() -> new EmployeeNotFoundException("Employee with given id not available :" + eid));
		return employee;
	}

	@Override
	public List<Employee> getAllEmployees() {
		List<Employee> employees = employeeRepository.findAll();
		return employees;
	}

	@Override
	public void deleteEmployee(Integer eid) {
		Employee employee = getEmployeeById(eid);
		employeeRepository.delete(employee);
	}

}
