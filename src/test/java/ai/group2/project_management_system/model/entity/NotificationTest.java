package ai.group2.project_management_system.model.entity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import ai.group2.project_management_system.model.entity.Notification;
import ai.group2.project_management_system.model.entity.User;
import org.junit.jupiter.api.Test;

class NotificationTest {
    @Test
    void testNotificationEntity() {
        // Create a test notification
        Notification notification = new Notification();
        notification.setId(1L);
        notification.setTitle("Test Title");
        notification.setContent("Test Content");
        User sender = new User();
        sender.setId(1L);
        notification.setSender(sender);
        User sendTo = new User();
        sendTo.setId(2L);
        notification.setSendTo(sendTo);
        notification.setRedirectURL("http://example.com");

        // Verify the values set to the notification
        assertEquals(1L, notification.getId());
        assertEquals("Test Title", notification.getTitle());
        assertEquals("Test Content", notification.getContent());
        assertEquals(1L, notification.getSender().getId());
        assertEquals(2L, notification.getSendTo().getId());
        assertEquals("http://example.com", notification.getRedirectURL());
    }

}