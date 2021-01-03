package raj.prudhvi.in.service;

import java.util.List;

import org.springframework.stereotype.Service;

import raj.prudhvi.in.model.Employee;

@Service
public interface IEmployeeService {

	public Employee saveEmployee(Employee employee);

	public Employee getEmployeeById(Integer eid);

	public List<Employee> getAllEmployees();

	public void deleteEmployee(Integer Id);

}
