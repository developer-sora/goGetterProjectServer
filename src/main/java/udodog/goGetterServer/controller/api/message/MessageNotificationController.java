package udodog.goGetterServer.controller.api.message;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import udodog.goGetterServer.model.dto.DefaultRes;
import udodog.goGetterServer.model.dto.request.message.MessageNotificationRequest;
import udodog.goGetterServer.model.dto.response.message.MessageNotificationResponse;
import udodog.goGetterServer.service.message.MessageNotificationService;


@Api(tags = {"쪽지 알림 API"})
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class MessageNotificationController {

    private final MessageNotificationService messageNotificationService;

    @ApiOperation(value = "쪽지 알림 API",notes = "읽지 않은 쪽지의 개수를 알림으로 표출해주는 API 입니다. (블랙회원 사용 o)")
    @ApiResponses(value ={
            @ApiResponse(code=200, message = "1. 조회 성공 2. 알림 미수신 설정"),
    })
    @GetMapping("/bkusers/messages/notice")
    public ResponseEntity<DefaultRes<MessageNotificationResponse>> messageNotification(@RequestBody MessageNotificationRequest request) {

        return new ResponseEntity<>(messageNotificationService.messageNotification(request), HttpStatus.OK);
    }


}
