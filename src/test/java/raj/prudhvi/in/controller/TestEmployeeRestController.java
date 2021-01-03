package raj.prudhvi.in.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;

import raj.prudhvi.in.model.Employee;
import raj.prudhvi.in.service.IEmployeeService;

@RunWith(SpringRunner.class)
@WebMvcTest(value = EmployeeRestController.class)
public class TestEmployeeRestController {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private IEmployeeService employeeService;

	private static final ObjectMapper om = new ObjectMapper();

	@Test
	@DisplayName("Testing Get All Employees")
	public void testGetAllEmployees() throws Exception {
		List<Employee> employees = Arrays.asList(new Employee("Prudhvi", "Bangalore", 78900.23),
				new Employee("Praveen", "Chennai", 88900.23), new Employee("Pavan", "Hyderabad", 98900.23));

		when(employeeService.getAllEmployees()).thenReturn(employees);

		mockMvc.perform(get("/employees/getAllEmployees"))
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isOk())
				.andExpect(jsonPath("$", hasSize(3))).andExpect(jsonPath("$[0].ename", is("Prudhvi")))
				.andExpect(jsonPath("$[0].ecity", is("Bangalore"))).andExpect(jsonPath("$[0].esalary", is(78900.23)))
				.andExpect(jsonPath("$[1].ename", is("Praveen"))).andExpect(jsonPath("$[1].ecity", is("Chennai")))
				.andExpect(jsonPath("$[1].esalary", is(88900.23)));

		verify(employeeService, times(1)).getAllEmployees();

	}

	@Test
	public void testSaveEmployee() throws Exception {

		Employee employee1 = new Employee(1, "Pavan", "Hyderabad", 25000.12);
		when(employeeService.saveEmployee(Mockito.any(Employee.class))).thenReturn(employee1);

		mockMvc.perform(MockMvcRequestBuilders.post("/employees/saveEmployee").content(om.writeValueAsString(employee1))
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)).andExpect(status().isCreated())
				.andExpect(jsonPath("$.eid", is(1))).andExpect(jsonPath("$.ename", is("Pavan")))
				.andExpect(jsonPath("$.ecity", is("Hyderabad"))).andExpect(jsonPath("$.esalary", is(25000.12)));

		assertThat(employeeService.saveEmployee(employee1)).isNotNull();
		verify(employeeService, times(2)).saveEmployee(employee1);

	}

}
