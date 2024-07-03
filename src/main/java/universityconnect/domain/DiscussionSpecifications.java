package universityconnect.domain;

import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;
import universityconnect.domain.Discussion;
import universityconnect.domain.DiscussionSearchCriteria;
import universityconnect.dto.DiscussionDTO;

public class DiscussionSpecifications {

    public static Specification<Discussion> withCriteria(DiscussionSearchCriteria discussionSearchCriteria){
        return (root, query, builder) -> {
            Predicate predicate = builder.conjunction();

            if (discussionSearchCriteria.getKeyword() != null) {
                String keywordPattern = "%" + discussionSearchCriteria.getKeyword() + "%";
                Predicate topicPredicate = builder.like(root.get("topic"),keywordPattern);
                Predicate descriptionPredicate = builder.like(root.get("description"),keywordPattern);
                predicate = builder.and(predicate, builder.or(topicPredicate, descriptionPredicate));
            }

            if (discussionSearchCriteria.getCategoryName() != null) {
                predicate = builder.and(predicate, builder.equal(root.get("category").get("name"), discussionSearchCriteria.getCategoryName()));
            }

            return predicate;
        };
    }
}
