package ai.group2.project_management_system.dto;
import ai.group2.project_management_system.model.Enum.Role;
import ai.group2.project_management_system.model.entity.Position;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockMultipartFile;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserDTOTest {

    @Test
    public void testUserDTO() {
        // Sample data
        Long id = 1L;
        String name = "John Doe";
        String education = "Bachelor's in Computer Science";
        LocalDate dob = LocalDate.of(1990, 5, 15);
        String gender = "Male";
        String email = "john.doe@example.com";
        String address = "123 Main Street, City";
        String phone = "123-456-7890";
        String profilePictureName = "profile.jpg";
        boolean isActive = true;
        String password = "password123";
        Position position = new Position(1L);
        Role role = Role.PM;

        // Create a mock MultipartFile
        MockMultipartFile file = new MockMultipartFile("file", "test.txt", "text/plain", "content".getBytes());

        // Create a UserDTO instance
        UserDTO userDTO = new UserDTO(id, name, education, dob, gender, email, address, phone, profilePictureName, isActive, password, position, role, file);

        // Verify the data
        assertEquals(id, userDTO.getId());
        assertEquals(name, userDTO.getName());
        assertEquals(education, userDTO.getEducation());
        assertEquals(dob, userDTO.getDob());
        assertEquals(gender, userDTO.getGender());
        assertEquals(email, userDTO.getEmail());
        assertEquals(address, userDTO.getAddress());
        assertEquals(phone, userDTO.getPhone());
        assertEquals(profilePictureName, userDTO.getProfilePictureName());
        assertEquals(isActive, userDTO.isActive());
        assertEquals(password, userDTO.getPassword());
        assertEquals(position, userDTO.getPosition());
        assertEquals(role, userDTO.getRole());
        assertEquals(file, userDTO.getFile());
    }
}
