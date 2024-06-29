package miu.waa.project1.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class DiscussionCategory extends BaseEntity {
	private String title;

	@OneToMany(mappedBy = "category",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Discussion> discussions;
}
