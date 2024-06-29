package miu.waa.project1.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import miu.waa.project1.model.Discussion;
import miu.waa.project1.model.DiscussionCategory;
import miu.waa.project1.model.Thread;

@Getter
public class CreateOrUpdateDiscussionRequest {
	Discussion discussion;
	@JsonProperty("category_id")
	Long categoryId;
}
