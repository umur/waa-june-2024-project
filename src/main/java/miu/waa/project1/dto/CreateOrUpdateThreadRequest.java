package miu.waa.project1.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import miu.waa.project1.model.Thread;

@Getter
public class CreateOrUpdateThreadRequest {
	Thread thread;
	@JsonProperty("discussion_id")
	Long discussionId;
}
