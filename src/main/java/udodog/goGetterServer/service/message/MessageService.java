package udodog.goGetterServer.service.message;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import udodog.goGetterServer.model.dto.DefaultRes;
import udodog.goGetterServer.model.dto.request.message.MessageSendRequestDto;
import udodog.goGetterServer.model.dto.response.message.MessageRoomResponseDto;
import udodog.goGetterServer.model.dto.response.message.MessageFindAllResponseDto;
import udodog.goGetterServer.model.entity.Message;
import udodog.goGetterServer.model.entity.MessageRoom;
import udodog.goGetterServer.model.entity.MessageRoomJoin;
import udodog.goGetterServer.model.entity.User;
import udodog.goGetterServer.repository.MessageRepository;
import udodog.goGetterServer.repository.MessageRoomJoinRepository;
import udodog.goGetterServer.repository.MessageRoomRepository;
import udodog.goGetterServer.repository.UserRepository;
import udodog.goGetterServer.repository.querydsl.message.MessageQueryRepository;

import java.util.*;

@Service
@RequiredArgsConstructor
public class MessageService {

    private final MessageRepository messageRepository;

    private final MessageRoomRepository messageRoomRepository;

    private final MessageRoomJoinRepository messageRoomJoinRepository;

    private final UserRepository userRepository;

    private final MessageQueryRepository messageQueryRepository;


    public DefaultRes<MessageRoomResponseDto> newMessageRoom(Long receiverId, Long senderId){

        Optional<User> optionalReceiver = userRepository.findById(receiverId);
        Optional<User> optionalSender = userRepository.findById(senderId);

        if(optionalSender.isEmpty()){
            return DefaultRes.response(HttpStatus.UNPROCESSABLE_ENTITY.value(), "수신자정보없음");
        }else if(optionalReceiver.isEmpty()){
            return DefaultRes.response(HttpStatus.UNPROCESSABLE_ENTITY.value(), "발신자정보없음");
        }

        Long roomId = check(receiverId, senderId);

        if(roomId != 0){
            //이미 존재하는 방이면 해당 방 번호 리턴
            return DefaultRes.response(HttpStatus.OK.value(), "생성성공", new MessageRoomResponseDto(roomId));

        }

        MessageRoom newMessageRoom = messageRoomRepository.save(new MessageRoom());


        createRoom(optionalReceiver.get(),newMessageRoom);
        createRoom(optionalSender.get(),newMessageRoom);

        return DefaultRes.response(HttpStatus.OK.value(), "생성성공", new MessageRoomResponseDto(newMessageRoom.getId()));
    }

    public Long check(Long receiverId, Long senderId){

        List<MessageRoomJoin> listFirst = messageRoomJoinRepository.findAllByUserId(receiverId);

        Set<MessageRoom> setFirst = new HashSet<>();

        for(MessageRoomJoin messageRoomJoin : listFirst){
            setFirst.add(messageRoomJoin.getMessageRoom());
        }

        List<MessageRoomJoin> listSecond = messageRoomJoinRepository.findAllByUserId(senderId);

        for(MessageRoomJoin chatRoomJoin : listSecond){
            if(setFirst.contains(chatRoomJoin.getMessageRoom())){
                return chatRoomJoin.getMessageRoom().getId();
            }
        }

        return 0L;
    }

    public void createRoom(User user, MessageRoom messageRoom){

        MessageRoomJoin messageRoomJoin = MessageRoomJoin
                .builder()
                .user(user)
                .messageRoom(messageRoom)
                .build();

        messageRoomJoinRepository.save(messageRoomJoin);
    }

    public void save(MessageSendRequestDto request){

        Optional<MessageRoom> optionalMessageRoom = messageRoomRepository.findById(request.getMessageRoomId());
        Optional<User> optionalUser = userRepository.findById(request.getSenderId());

        messageRepository.save(request.toEntity(optionalMessageRoom.get(), optionalUser.get()));
    }


    public DefaultRes<List<MessageFindAllResponseDto>> findAllMessage(Long userId){

        List<MessageRoomJoin> messageRoomJoinList = messageRoomJoinRepository.findAllByUserId(userId);

        List<MessageFindAllResponseDto> messageFindAllResponseDtoList = new ArrayList<>();

        messageRoomJoinList.forEach(messageRoomJoin -> {
            Message message = messageQueryRepository.findMessage(messageRoomJoin.getMessageRoom());
            User theOtherUser = messageQueryRepository.findTheOtherUser(messageRoomJoin.getMessageRoom(), userId);
            messageFindAllResponseDtoList.add(new MessageFindAllResponseDto(theOtherUser.getId(), theOtherUser.getNickName(),
                    message.getContent(), message.getSendAt(), messageRoomJoin.getMessageRoom().getId()));
        });

        return DefaultRes.response(HttpStatus.OK.value(), "조회성공", messageFindAllResponseDtoList);

    }

}
