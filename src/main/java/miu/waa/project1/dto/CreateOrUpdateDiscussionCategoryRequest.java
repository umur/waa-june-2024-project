package miu.waa.project1.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import miu.waa.project1.model.Discussion;

@Getter
public class CreateOrUpdateDiscussionCategoryRequest {
	String title;
}
