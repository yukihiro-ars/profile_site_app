package ars.yukihiro.repository.helper;

import ars.yukihiro.entity.NodeRelationship;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class NodeRelationshipRepositoryHelper {
    public static Specification<NodeRelationship> isRootNode() {
        return new Specification<NodeRelationship>() {
            @Override
            public Predicate toPredicate(Root<NodeRelationship> root,
                                         CriteriaQuery<?> criteriaQuery,
                                         CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.equal(root.get("parentNodeId"), "0");
            }
        };
    }
}
