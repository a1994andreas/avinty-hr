package com.avinty.hr;

import com.avinty.hr.models.CustomUserDetails;
import com.avinty.hr.models.Employee;
import com.avinty.hr.repositories.EmployeeRepository;
import com.avinty.hr.security.JWTUtility;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.LinkedList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.hamcrest.CoreMatchers.is;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class EmployeeControllerTests {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private JWTUtility jwtUtility;

	@Autowired
	private EmployeeRepository employeeRepository;

	@Test
	public void testUnauthorized() throws Exception {

		this.mockMvc.perform(get("/api/v1/employees"))
				.andExpect(status().isUnauthorized());
	}

	@Test
	public void testGetEmployees() throws Exception {

		this.mockMvc.perform(get("/api/v1/employees")
				.header("Authorization", "Bearer " +generateToken("andreas@gmail.com","admin123", "ADMIN", 1)))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$[0].email", is("andreas@gmail.com")))
				.andExpect(jsonPath("$[0].full_name", is("Andreas Paravoliasis")))
				.andExpect(jsonPath("$[0].role", is("ADMIN")));
	}

	@Test
	public void testGetEmployeesMultipleResults() throws Exception {
		Employee employee = new Employee();
		employee.setEmail("test@gmail.com");
		employee.setPassword("pass");
		employee.setFull_name("Test Username");

		employeeRepository.save(employee);

		this.mockMvc.perform(get("/api/v1/employees")
				.header("Authorization", "Bearer " +generateToken("andreas@gmail.com","admin123", "ADMIN", 1)))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$[0].email", is("andreas@gmail.com")))
				.andExpect(jsonPath("$[0].full_name", is("Andreas Paravoliasis")))
				.andExpect(jsonPath("$[0].role", is("ADMIN")))
				.andExpect(jsonPath("$[1].email", is("test@gmail.com")))
				.andExpect(jsonPath("$[1].full_name", is("Test Username")))
				.andExpect(jsonPath("$[1].role", is("USER")));
	}

	@Test
	public void testAddEmployee() throws Exception {
		String token = generateToken("andreas@gmail.com","admin123", "ADMIN", 1);

		ObjectMapper objectMapper = new ObjectMapper();

		Employee employee = new Employee();
		employee.setEmail("test@gmail.com");
		employee.setPassword("pass");
		employee.setFull_name("Test Username");

		this.mockMvc.perform(post("/api/v1/employees")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(employee))
				.header("Authorization", "Bearer " +token)).andExpect(status().isOk());


		this.mockMvc.perform(get("/api/v1/employees")
				.header("Authorization", "Bearer " +token))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$[1].email", is("test@gmail.com")))
				.andExpect(jsonPath("$[1].full_name", is("Test Username")))
				.andExpect(jsonPath("$[1].role", is("USER")));
	}



	private String generateToken(String email, String password, String role, Integer id) {
		List<GrantedAuthority> authorities = new LinkedList<>();
		authorities.add(new SimpleGrantedAuthority("ROLE_"+role));
		CustomUserDetails user = new CustomUserDetails(email, password, authorities);
		user.setId(id);
		return jwtUtility.generateToken(user);
	}

}
