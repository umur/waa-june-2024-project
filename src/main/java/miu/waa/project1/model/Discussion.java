package miu.waa.project1.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class Discussion extends BaseEntity {
	private String content;

	@Setter
	@ManyToOne
	@JoinColumn(name = "category_id")
	@JsonBackReference
	DiscussionCategory category;

	@OneToMany(mappedBy = "discussion", cascade = CascadeType.ALL)
	List<Thread> threads;
}
