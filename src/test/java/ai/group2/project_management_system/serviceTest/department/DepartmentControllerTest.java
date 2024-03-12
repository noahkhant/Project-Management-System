package ai.group2.project_management_system.serviceTest.department;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.Mockito.*;

import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import ai.group2.project_management_system.controller.view.DepartmentController;
import ai.group2.project_management_system.model.entity.User;
import ai.group2.project_management_system.service.UserService;

public class DepartmentControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private DepartmentController departmentController;

    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/views/");
        viewResolver.setSuffix(".jsp");
        mockMvc = MockMvcBuilders.standaloneSetup(departmentController)
                .setViewResolvers(viewResolver)
                .build();
    }

    @Test
    public void testDepartmentPage() throws Exception {
        User mockUser = new User();
        mockUser.setName("testUser");

        // Mock the UserService to return a mock user when getCurrentUser() is called
        when(userService.getCurrentUser()).thenReturn(mockUser);

        // Perform GET request to /department endpoint
        mockMvc.perform(get("/department"))
                .andExpect(status().isOk()) // Expect status OK (200)
                .andExpect(view().name("department")) // Expect view name to be "department"
                .andExpect(model().attributeExists("user")) // Expect model attribute "user" to exist
                .andExpect(model().attribute("user", mockUser)); // Expect model attribute "user" to be the mock user
    }
    @Test
    public void testGetUserFromSession() {
        // Mock HttpSession
        HttpSession mockSession = mock(HttpSession.class);
        // Mock UserService to return a mock user
        User mockUser = new User();
        mockUser.setName("testUser");
        when(userService.getCurrentUser()).thenReturn(mockUser);
        // Call getUserFromSession method with mock HttpSession
        User userFromSession = departmentController.getUserFromSession(mockSession);
        verify(userService).getCurrentUser();
        assertEquals(mockUser, userFromSession);
    }
}

