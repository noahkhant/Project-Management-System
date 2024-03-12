package ai.group2.project_management_system.service.Impl;

import ai.group2.project_management_system.model.entity.Message;
import ai.group2.project_management_system.repository.MessageRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
class MessageServiceImplTest {
    private MessageServiceImpl messageService;

    @Mock
    private MessageRepository messageRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        messageService = new MessageServiceImpl(messageRepository);
    }
    @Test
    public void testSaveMessage() {
        // Create a sample message
        Message message = new Message();
        message.setId(1L);
        message.setContent("Test content");

        // Mock the behavior of the message repository
        when(messageRepository.save(message)).thenReturn(message);

        // Call the save method of the service
        Message savedMessage = messageService.save(message);

        // Verify that the message was saved
        assertEquals(message, savedMessage);

        // Verify that the save method of the repository was called once with the message object
        verify(messageRepository, times(1)).save(message);
    }

    @Test
    public void testGetMessagesByChannelId() {
        // Create sample data
        Long issueId = 123L;
        List<Message> messages = new ArrayList<>();
        messages.add(new Message(1L, "Message 1"));
        messages.add(new Message(2L, "Message 2"));

        // Mock the behavior of the message repository
        when(messageRepository.getMessagesByIssueId(issueId)).thenReturn(messages);

        // Call the method under test
        List<Message> retrievedMessages = messageService.getMessagesByChannelId(issueId);

        // Verify the result
        assertEquals(messages, retrievedMessages);

        // Verify that the repository method was called with the correct issue ID
        verify(messageRepository, times(1)).getMessagesByIssueId(issueId);
    }

}