package udodog.goGetterServer.controller.api.message;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import udodog.goGetterServer.model.dto.DefaultRes;
import udodog.goGetterServer.model.dto.response.message.MessageRoomResponseDto;
import udodog.goGetterServer.model.entity.Message;
import udodog.goGetterServer.service.message.MessageService;


@Api(tags = {"메시지 관련 API"})
@RestController
@RequiredArgsConstructor
public class MessageController {

    private final MessageService messageService;

    private final SimpMessagingTemplate simpMessagingTemplate;

    @ApiOperation(value = "채팅방 생성 API",notes = "채팅방 생성시 사용되는 API 입니다.")
    @ApiResponses(value ={
            @ApiResponse(code=200, message = "1. 생성성공"),
            @ApiResponse(code=422, message = "1. 발신자정보없음 \n 2. 수신자정보없음")
    })
    @GetMapping("/api/users/chat/newChat")
    public ResponseEntity<DefaultRes<MessageRoomResponseDto>> newMessageRoom(
            @RequestParam("receiverId") Long receiverId,
            @RequestParam("senderId") Long senderId){

        DefaultRes<MessageRoomResponseDto> result = messageService.newMessageRoom(receiverId, senderId);

        if(result.getStatusCode() == HttpStatus.OK.value()){
            return new ResponseEntity<>(result, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(result, HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }


    @MessageMapping("/chat/send")
    public void SendToMessage(Message message){
        messageService.save(message);
        simpMessagingTemplate.convertAndSend("/topic/"+message.getMessageRoom().getId() , message);
    }
}
