package udodog.goGetterServer.model.dto.response.discussion;

import lombok.*;
import udodog.goGetterServer.model.entity.DiscussionBoard;
import udodog.goGetterServer.model.entity.User;

import java.time.LocalDate;

@NoArgsConstructor
@Getter
@ToString
public class DiscussionDetailResponse {

    private Long id;
    private String userNickname;
    private String title;
    private String content;
    private LocalDate createAt;

    public DiscussionDetailResponse(DiscussionBoard discussionBoard){
        this.id = discussionBoard.getId();
        this.userNickname = discussionBoard.getUser().getNickName();
        this.title = discussionBoard.getTitle();
        this.content = discussionBoard.getContent();
        this.createAt = discussionBoard.getCreateAt();
    }
}
