package ai.group2.project_management_system.controller.api;

import ai.group2.project_management_system.model.entity.Department;
import ai.group2.project_management_system.model.entity.Issue;
import ai.group2.project_management_system.model.entity.Message;
import ai.group2.project_management_system.service.IssueService;
import ai.group2.project_management_system.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class ChatAPI {

    private final MessageService messageService;
    private final IssueService issueService;
//    @MessageMapping("/chat")
//    @SendTo("/topic/messages")
//    public ResponseEntity<List<Message>> sendMessage(Message message) {
//
//
//        Long senderId = message.getSenderId();
//        String content = message.getContent();
//
//        System.out.println("sender   : "+ senderId);
//        System.out.println("message   : "+ content);
//
//        message.setTimeStamp(LocalDateTime.now());
//        // Process the incoming message (e.g., store in a database) and return it
//        messageService.save(message);
//        return ResponseEntity.ok();
//    }

//    @MessageMapping("/chat/{issueId}") // Dynamic channel ID
//    @SendTo("/topic/messages/{issueId}") // Dynamic topic based on channel ID
//    public ResponseEntity<List<Message>> sendMessage(@DestinationVariable(("issueId") Long channelId, Message message) {
//
//        Long senderId = message.getSenderId();
//        String content = message.getContent();
//
//        System.out.println("sender: " + senderId);
//        System.out.println("message: " + content);
//
//        message.setTimeStamp(LocalDateTime.now());
//        messageService.save(message);
//
//        // Assuming messageService has a method to retrieve messages by channelId
//        List<Message> messages = messageService.getMessagesByChannelId(channelId);
//        // Assuming you want to return messages to the client
//        return new ResponseEntity<>(messages, HttpStatus.OK);
//    }

    @MessageMapping("/chat/{issueId}") // Dynamic channel ID
    @SendTo("/topic/messages/{issueId}") // Dynamic topic based on channel ID
    public ResponseEntity<List<Message>> sendMessage(@DestinationVariable("issueId") Long issueId, Message message) {
        if(!message.getContent().equals("#")) {
            Long senderId = message.getSenderId();
            String content = message.getContent();

            System.out.println("sender: " + senderId);
            System.out.println("message: " + content);
            message.setIssueId(issueId);
            message.setTimeStamp(LocalDateTime.now());
            Message msg = messageService.save(message);
            System.out.println(msg);

            // Assuming messageService has a method to retrieve messages by channelId
            List<Message> messages = messageService.getMessagesByChannelId(issueId);
            // Assuming you want to return messages to the client
            System.out.println(messages);
            return new ResponseEntity<>(messages, HttpStatus.OK);
        }else{
            return  new ResponseEntity<>(HttpStatus.OK);
        }
    }

    @GetMapping("/get-messages/{issueId}")
    public ResponseEntity<List<Message>> getAllMessage(@PathVariable("issueId") Long issueId) {
        List<Message> messageList = messageService.getMessagesByChannelId(issueId);
        return ResponseEntity.ok(messageList);
    }

    @GetMapping("/get-issues/{userId}")
    public ResponseEntity<List<Issue>> getIssues(@PathVariable("userId") Long userId) {
        List<Issue> issues = issueService.getAllIssueByUserId(userId);
        return ResponseEntity.ok(issues);
    }

}
