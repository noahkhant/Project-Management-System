package ai.group2.project_management_system.serviceTest.department;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class DepartmentAPITest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGetDepartments() throws Exception {
        mockMvc.perform(get("/departments"))
                .andExpect(status().is3xxRedirection()) // Expect redirection (status code 3xx)
                .andExpect(redirectedUrl("http://localhost/login")); // Adjust the URL as needed
    }

    @Test
    public void testAddDepartment() throws Exception {
        String requestBody = "{\"name\": \"Test Department\"}";

        mockMvc.perform(post("/add-department")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().is3xxRedirection()) // Expect redirection (status code 3xx)
                .andExpect(redirectedUrl("http://localhost/login")); // Adjust the URL as needed
    }

    @Test
    public void testUpdateDepartment() throws Exception {
        String requestBody = "{\"name\": \"Updated Department Name\"}";

        mockMvc.perform(put("/edit-department/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().is3xxRedirection()) // Expect redirection (status code 3xx)
                .andExpect(redirectedUrl("http://localhost/login")); // Adjust the URL as needed
    }

    @Test
    public void testUpdateDepartmentStatus() throws Exception {
        mockMvc.perform(put("/api/updateRecord/{departmentId}", 1L))
                .andExpect(status().is3xxRedirection()) // Expect redirection (status code 3xx)
                .andExpect(redirectedUrl("http://localhost/login")); // Adjust the URL as needed
    }


}