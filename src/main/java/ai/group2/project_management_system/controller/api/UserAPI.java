package ai.group2.project_management_system.controller.api;

import ai.group2.project_management_system.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserAPI {
    private final UserService userService;


}
