package ai.group2.project_management_system.service.Impl;

import ai.group2.project_management_system.model.entity.Notification;
import ai.group2.project_management_system.repository.NotificationRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
class NotificationServiceImplTest {
    @Mock
    private NotificationRepository notificationRepository;

    @InjectMocks
    private NotificationServiceImpl notificationService;

    @Test
    public void testSave() {
        // Create a sample notification
        Notification notification = new Notification();
        notification.setId(1L);
        notification.setTitle("Test notification");

        // Mock the behavior of the notificationRepository.save method
        when(notificationRepository.save(notification)).thenReturn(notification);

        // Invoke the save method of notificationService
        Notification savedNotification = notificationService.save(notification);

        // Verify that notificationRepository.save was called with the correct notification
        verify(notificationRepository, times(1)).save(notification);

        // Verify the returned notification object
        assertEquals(notification, savedNotification);
    }
}