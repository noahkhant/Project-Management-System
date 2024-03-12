package ai.group2.project_management_system.model.entity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import ai.group2.project_management_system.model.entity.Department;
import ai.group2.project_management_system.model.entity.Position;
import ai.group2.project_management_system.model.Enum.Role;
import ai.group2.project_management_system.model.entity.User;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

class UserTest {
    @Test
    void testUserEntity() {
        // Create a test user
        User user = new User();
        user.setId(1L);
        user.setName("John Doe");
        user.setDepartment(new Department(1L, "Engineering"));
        user.setEducation("Bachelor's");
        user.setDob(LocalDate.of(1990, 1, 1));
        user.setGender("Male");
        user.setEmail("john.doe@example.com");
        user.setAddress("123 Main St");
        user.setPhone("123-456-7890");
        user.setProfilePictureFileName("profile.jpg");
        user.setActive(true);
        user.setPassword("password");
        user.setPosition(new Position(8L, "Software Engineer"));
        user.setRole(Role.MEMBER);

        // Verify the values set to the user
        assertEquals(1L, user.getId());
        assertEquals("John Doe", user.getName());
        assertEquals("Engineering", user.getDepartment().getName());
        assertEquals("Bachelor's", user.getEducation());
        assertEquals(LocalDate.of(1990, 1, 1), user.getDob());
        assertEquals("Male", user.getGender());
        assertEquals("john.doe@example.com", user.getEmail());
        assertEquals("123 Main St", user.getAddress());
        assertEquals("123-456-7890", user.getPhone());
        assertEquals("profile.jpg", user.getProfilePictureFileName());
        assertTrue(user.isActive());
        assertEquals("password", user.getPassword());
        assertEquals(null, user.getPosition().getPositionName());
        assertEquals(Role.MEMBER, user.getRole());
    }

}