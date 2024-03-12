package ai.group2.project_management_system.mapping;

import ai.group2.project_management_system.dto.UserDTO;
import ai.group2.project_management_system.model.entity.*;
import ai.group2.project_management_system.model.Enum.Role;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserMappingTest {

    @Test
    public void testMapUserToDTO() {
        // Create sample User
        User user = new User();
        user.setId(1L);
        user.setName("John Doe");
        user.setEducation("Computer Science");
        user.setDob(LocalDate.of(1990, 5, 15));
        user.setGender("Male");
        user.setEmail("john.doe@example.com");
        user.setAddress("123 Main St, City");
        user.setPhone("123-456-7890");
        user.setProfilePictureFileName("profile.jpg");
        user.setPassword("password");
        user.setRole(Role.MEMBER);
        user.setPosition(new Position());

        // Map User to UserDTO using the mapping class
        UserMapping userMapping = new UserMapping();
        UserDTO userDTO = userMapping.mapUserToDTOs(user);

        // Verify the mapping
        assertEquals(user.getId(), userDTO.getId());
        assertEquals(user.getName(), userDTO.getName());
        assertEquals(user.getEducation(), userDTO.getEducation());
        assertEquals(user.getDob(), userDTO.getDob());
        assertEquals(user.getGender(), userDTO.getGender());
        assertEquals(user.getEmail(), userDTO.getEmail());
        assertEquals(user.getAddress(), userDTO.getAddress());
        assertEquals(user.getPhone(), userDTO.getPhone());
        assertEquals(user.getProfilePictureFileName(), userDTO.getProfilePictureName());
        assertEquals(user.getPassword(), userDTO.getPassword());
        assertEquals(user.getRole(), userDTO.getRole());
        assertEquals(user.getPosition(), userDTO.getPosition());
    }
}
