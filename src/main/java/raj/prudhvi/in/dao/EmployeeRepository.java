package raj.prudhvi.in.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import raj.prudhvi.in.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

}
