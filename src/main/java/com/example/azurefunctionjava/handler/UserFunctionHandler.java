package com.example.azurefunctionjava.handler;

import com.example.azurefunctionjava.dto.UserCommand;
import com.example.azurefunctionjava.service.UserService;
import com.microsoft.azure.functions.*;
import com.microsoft.azure.functions.annotation.AuthorizationLevel;
import com.microsoft.azure.functions.annotation.BindingName;
import com.microsoft.azure.functions.annotation.FunctionName;
import com.microsoft.azure.functions.annotation.HttpTrigger;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserFunctionHandler {

    private final UserService userService;

    public UserFunctionHandler(UserService userService) {
        this.userService = userService;
    }

    @FunctionName("userSave")
    public HttpResponseMessage save(
            @HttpTrigger(name = "userSaveRequest", methods = {HttpMethod.POST}, authLevel = AuthorizationLevel.ANONYMOUS) HttpRequestMessage<UserCommand> request,
            ExecutionContext context
    ) {
        UserCommand command = request.getBody();

        return request.createResponseBuilder(HttpStatus.OK)
                .body(userService.saveUser(command))
                .build();
    }

    @FunctionName("userGet")
    public HttpResponseMessage get(
            @HttpTrigger(name = "userGetRequest", methods = {HttpMethod.GET}, authLevel = AuthorizationLevel.ANONYMOUS) HttpRequestMessage request,
            @BindingName("id") Long id,
            ExecutionContext context
    ) {
        return request.createResponseBuilder(HttpStatus.OK)
                .body(userService.getUser(id))
                .build();
    }

}
