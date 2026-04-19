package com.tushar.User.Service;

import com.tushar.User.Service.Controller.UserController;
import com.tushar.User.Service.Exceptiom.GlobalExceptionHandler;
import com.tushar.User.Service.Exceptiom.UserException;
import com.tushar.User.Service.Model.User;
import com.tushar.User.Service.Service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class UserServiceApplicationTests {

	@Mock
	private UserService userService;

	@InjectMocks
	private UserController userController;

	private MockMvc mockMvc;

	@BeforeEach
	void setUp() {
		LocalValidatorFactoryBean validator = new LocalValidatorFactoryBean();
		validator.afterPropertiesSet();
		mockMvc = MockMvcBuilders.standaloneSetup(userController)
				.setControllerAdvice(new GlobalExceptionHandler())
				.setValidator(validator)
				.build();
	}

	private User buildUser(String username, String email) {
		User user = new User();
		user.setFullname("Test User");
		user.setUsername(username);
		user.setEmail(email);
		user.setPhone("9999999999");
		user.setRole("CUSTOMER");
		user.setPassword("password");
		return user;
	}

	@Test
	void createAndGetUserEndpointsWork() throws Exception {
		User user = buildUser("john", "john@example.com");
		User savedUser = buildUser("john", "john@example.com");
		savedUser.setId(1L);

		when(userService.createUser(any(User.class))).thenReturn(savedUser);
		when(userService.getUserById(1L)).thenReturn(savedUser);

		mockMvc.perform(post("/api/users/createUser")
						.contentType(MediaType.APPLICATION_JSON)
						.content("""
								{
								  \"fullname\": \"Test User\",
								  \"username\": \"john\",
								  \"email\": \"john@example.com\",
								  \"phone\": \"9999999999\",
								  \"role\": \"CUSTOMER\",
								  \"password\": \"password\"
								}
								"""))
				.andExpect(status().isCreated())
				.andExpect(jsonPath("$.id").isNumber())
				.andExpect(jsonPath("$.username").value("john"));

		mockMvc.perform(get("/api/users/user/{id}", 1L))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.id").value(1L))
				.andExpect(jsonPath("$.email").value("john@example.com"));

		verify(userService).createUser(any(User.class));
		verify(userService).getUserById(1L);
	}

	@Test
	void deleteEndpointUsesPathVariableAndReturnsOk() throws Exception {
		doReturn("user deleted successfully").when(userService).deleteUser(5L);

		mockMvc.perform(delete("/api/users/deleteUser/{id}", 5L))
				.andExpect(status().isOk());

		verify(userService).deleteUser(5L);
	}

	@Test
	void validationAndNotFoundAreHandled() throws Exception {
		doThrow(new UserException("user not found")).when(userService).getUserById(999999L);

		mockMvc.perform(post("/api/users/createUser")
						.contentType(MediaType.APPLICATION_JSON)
						.content("""
								{
								  \"fullname\": \"Test User\",
								  \"username\": \"\",
								  \"email\": \"not-an-email\",
								  \"phone\": \"9999999999\",
								  \"role\": \"\",
								  \"password\": \"\"
								}
								"""))
				.andExpect(status().isBadRequest())
				.andExpect(jsonPath("$.message").exists());

		mockMvc.perform(get("/api/users/user/{id}", 999999L))
				.andExpect(status().isNotFound())
				.andExpect(jsonPath("$.message").value("user not found"));
	}
}
