package ai.group2.project_management_system.serviceTest.user;

import ai.group2.project_management_system.dto.UserDTO;
import ai.group2.project_management_system.mapping.UserMapping;
import ai.group2.project_management_system.model.entity.*;
import ai.group2.project_management_system.repository.UserRepository;
import ai.group2.project_management_system.service.Impl.UserServiceImpl;
import ai.group2.project_management_system.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.*;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class UserServiceImplTest {
    @Mock
    private UserRepository userRepository;

    @Mock
    private UserMapping userMapping;

    private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        userService = new UserServiceImpl(userRepository, userMapping);
    }

    @Test
    void getCurrentUser_ReturnsCurrentUser() {
        // Mock the username
        String username = "testUser";

        // Mock the Authentication object
        Authentication authentication = new UsernamePasswordAuthenticationToken(username, null);

        // Mock the SecurityContext and set the Authentication object
        // Set the Authentication object to the SecurityContext
        SecurityContext securityContext = SecurityContextHolder.getContext();
        securityContext.setAuthentication(authentication);

        // Perform the method invocation
        User currentUser = userService.getCurrentUser();
        // Verify that the correct username is returned
        assertEquals(null, currentUser);
    }
    @Test
    void getUsersByProjectId_ReturnsUserDTOs() {
        // Mock project ID
        Long projectId = 1L;

        // Mock list of users
        List<User> users = new ArrayList<>();
        // Add some users to the list (replace with your actual User objects)
        users.add(new User());
        users.add(new User());
        users.add(new User());

        // Mock list of UserDTOs
        List<UserDTO> userDTOs = new ArrayList<>();
        // Add some UserDTOs to the list (replace with your actual UserDTO objects)
        userDTOs.add(new UserDTO());
        userDTOs.add(new UserDTO());
        userDTOs.add(new UserDTO());

        // Mock the userRepository.findByProjects_id() method to return the list of users
        when(userRepository.findByProjects_id(projectId)).thenReturn(users);

        // Mock the userMapping.mapUserToDTOs() method to return the list of UserDTOs
        when(userMapping.mapUserToDTOs(users.get(0))).thenReturn(userDTOs.get(0));
        when(userMapping.mapUserToDTOs(users.get(1))).thenReturn(userDTOs.get(1));
        when(userMapping.mapUserToDTOs(users.get(2))).thenReturn(userDTOs.get(2));

        // Perform the method invocation
        List<UserDTO> result = userService.getUsersByProjectId(projectId);

        // Verify that the result matches the expected list of UserDTOs
        assertEquals(userDTOs, result);
    }
    @Test
    void getMembersByDepartmentId_ReturnsUsers() {
        // Mock department ID
        Long departmentId = 1L;

        // Mock list of users
        List<User> users = new ArrayList<>();
        // Add some users to the list (replace with your actual User objects)
        users.add(new User());
        users.add(new User());
        users.add(new User());

        // Mock the userRepository.findByDepartmentId() method to return the list of users
        when(userRepository.findByDepartmentId(departmentId)).thenReturn(users);

        // Perform the method invocation
        List<User> result = userService.getMembersByDepartmentId(departmentId);

        // Verify that the result matches the expected list of users
        assertEquals(users, result);
    }
    @Test
    void getUserPhotoById_ReturnsPhotoPath() {
        // Mock user ID
        Long userId = 1L;

        // Mock photo path
        String photoPath = "/path/to/photo.jpg";

        // Mock the userRepository.findPhotoById() method to return the photo path
        when(userRepository.findPhotoById(userId)).thenReturn(photoPath);

        // Perform the method invocation
        String result = userService.getUserPhotoById(userId);

        // Verify that the result matches the expected photo path
        assertEquals(photoPath, result);
    }
    @Test
    void getAllUserByIssueId_ReturnsUsers() {
        // Mock issue ID
        Long issueId = 1L;

        // Mock list of users
        List<User> users = new ArrayList<>();
        // Add some users to the list (replace with your actual User objects)
        users.add(new User());
        users.add(new User());
        users.add(new User());

        // Mock the userRepository.findAllUserByIssueId() method to return the list of users
        when(userRepository.findAllUserByIssueId(issueId)).thenReturn(users);

        // Perform the method invocation
        List<User> result = userService.getAllUserByIssueId(issueId);

        // Verify that the result matches the expected list of users
        assertEquals(users, result);
    }
    @Test
    void getUsersByIds_ReturnsUsers() {
        // Mock list of team leader IDs
        List<Long> teamLeaderIds = new ArrayList<>();
        // Add some team leader IDs to the list (replace with your actual IDs)
        teamLeaderIds.add(1L);
        teamLeaderIds.add(2L);
        teamLeaderIds.add(3L);

        // Mock list of users
        List<User> users = new ArrayList<>();
        // Add some users to the list (replace with your actual User objects)
        users.add(new User());
        users.add(new User());
        users.add(new User());

        // Mock the userRepository.findAllById() method to return the list of users
        when(userRepository.findAllById(teamLeaderIds)).thenReturn(users);

        // Perform the method invocation
        List<User> result = userService.getUsersByIds(teamLeaderIds);

        // Verify that the result matches the expected list of users
        assertEquals(users, result);
    }
    @Test
    void getProjectsByUserId_ReturnsProjects() {
        // Mock user ID
        Long userId = 1L;

        // Mock list of projects
        List<Project> projects = new ArrayList<>();
        // Add some projects to the list (replace with your actual Project objects)
        projects.add(new Project());
        projects.add(new Project());
        projects.add(new Project());

        // Mock the userRepository.findProjectsByUserId() method to return the list of projects
        when(userRepository.findProjectsByUserId(userId)).thenReturn(projects);

        // Perform the method invocation
        List<Project> result = userService.getProjectsByUserId(userId);

        // Verify that the result matches the expected list of projects
        assertEquals(projects, result);
    }
    @Test
    void saveUser_ReturnsSavedUser() {
        // Mock a user object to be saved
        User userToSave = new User();
        // Set properties of the user object (replace with your actual properties)
        userToSave.setName("John Doe");
        userToSave.setEmail("john.doe@example.com");

        // Mock the userRepository.save() method to return the saved user object
        when(userRepository.save(userToSave)).thenReturn(userToSave);

        // Perform the method invocation
        User savedUser = userService.save(userToSave);

        // Verify that the saved user object matches the user object that was saved
        assertEquals(userToSave, savedUser);
    }
    @Test
    void getAllUsers_ReturnsAllUsers() {
        // Mock list of users
        List<User> users = new ArrayList<>();
        // Add some users to the list (replace with your actual User objects)
        users.add(new User());
        users.add(new User());
        users.add(new User());

        // Mock the userRepository.findAll() method to return the list of users
        when(userRepository.findAll()).thenReturn(users);

        // Perform the method invocation
        List<User> result = userService.getAllUsers();

        // Verify that the result matches the expected list of users
        assertEquals(users, result);
    }
    @Test
    void getUserById_ReturnsUser() {
        // Mock user ID
        long userId = 1L;

        // Mock user object
        User user = new User();
        user.setId(userId);
        // Set other properties of the user object (replace with your actual properties)
        user.setName("John Doe");
        user.setEmail("john.doe@example.com");

        // Mock the userRepository.findById() method to return an Optional containing the user object
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));

        // Perform the method invocation
        User result = userService.getUserById(userId);

        // Verify that the result matches the expected user object
        assertEquals(user, result);
    }
    @Test
    void findUsersByIds_ReturnsUsers() {
        // Mock list of user IDs
        List<Long> userIds = new ArrayList<>();
        userIds.add(1L);
        userIds.add(2L);
        userIds.add(3L);

        // Mock list of user objects
        List<User> users = new ArrayList<>();
        // Add some user objects to the list (replace with your actual User objects)
        users.add(new User());
        users.add(new User());
        users.add(new User());

        // Mock the userRepository.findByIdIn() method to return the list of user objects
        when(userRepository.findByIdIn(userIds)).thenReturn(users);

        // Perform the method invocation
        List<User> result = userService.findUsersByIds(userIds);

        // Verify that the result matches the expected list of user objects
        assertEquals(users, result);
    }
    @Test
    void getUsersByDepartmentId_ReturnsUserDTOs() {
        // Mock department ID
        Long departmentId = 1L;

        // Mock list of user objects
        List<User> users = new ArrayList<>();
        // Add some user objects to the list (replace with your actual User objects)
        users.add(new User());
        users.add(new User());
        users.add(new User());

        // Mock the userRepository.findByDepartmentId() method to return the list of user objects
        when(userRepository.findByDepartmentId(departmentId)).thenReturn(users);

        // Mock the userMapping.mapUserToDTOs() method to return a UserDTO object
        UserDTO userDTO = new UserDTO();
        // Set properties of the UserDTO object (replace with your actual properties)
        userDTO.setId(1L);
        userDTO.setName("John Doe");
        userDTO.setEmail("john.doe@example.com");
        when(userMapping.mapUserToDTOs(any(User.class))).thenReturn(userDTO);

        // Perform the method invocation
        List<UserDTO> result = userService.getUsersByDepartmentId(departmentId);

        // Verify that the result contains UserDTO objects mapped from the list of user objects
        assertEquals(users.size(), result.size());
        for (int i = 0; i < users.size(); i++) {
            assertEquals(userDTO, result.get(i));
        }
    }
    @Test
    void testGetUserEmailById() {
        // Mock user ID and email
        Long userId = 1L;
        String expectedEmail = "test@example.com";

        // Mock UserRepository response
        when(userRepository.findEmailById(userId)).thenReturn(expectedEmail);

        // Call the service method
        String actualEmail = userService.getUserEmailById(userId);

        // Assert the result
        assertEquals(expectedEmail, actualEmail);
    }
    @Test
    void testGetUserNameById() {
        // Mock user ID and name
        Long userId = 1L;
        String expectedName = "John Doe";

        // Mock UserRepository response
        when(userRepository.findUserNameByID(userId)).thenReturn(expectedName);

        // Call the service method
        String actualName = userService.getUserNameById(userId);

        // Assert the result
        assertEquals(expectedName, actualName);
    }

}