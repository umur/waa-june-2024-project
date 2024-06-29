package miu.waa.project1.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Thread extends BaseEntity {
	private String comment;
	@Setter
	@ManyToOne
	@JsonIgnore
	Discussion discussion;
}
