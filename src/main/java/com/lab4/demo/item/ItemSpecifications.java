package com.lab4.demo.item;

import com.lab4.demo.item.model.Item;
import com.lab4.demo.item.model.dto.ItemFilterRequestDto;
import com.lab4.demo.review.model.Review;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Subquery;

import java.time.LocalDateTime;

import static com.lab4.demo.review.Rating.EXCELENT;
import static java.util.Optional.ofNullable;

public class ItemSpecifications {


    public static Specification<Item> withReviews() {
        return (root, query, cb) -> root.fetch("reviews");
    }


        public static Specification<Item> similarNames(String name) {
        return (root, query, cb) -> cb.like(root.get("name"), name);
    }

    public static Specification<Item> createdAfter(LocalDateTime date) {
        return (root, query, cb) -> cb.greaterThan(root.get("dateCreated"), date);
    }

    public static Specification<Item> onlyExcellentRated() {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.exists(reviewRatingSubquery(root, query, criteriaBuilder));
    }

    private static Subquery<Review> reviewRatingSubquery(Root<Item> root, CriteriaQuery<?> query,
                                                         CriteriaBuilder criteriaBuilder) {
        final Subquery<Review> reviewSubquery = query.subquery(Review.class);
        final Root<Review> fromReview = reviewSubquery.from(Review.class);
        return reviewSubquery.select(fromReview).where(criteriaBuilder.and(
                criteriaBuilder.equal(fromReview.get("item"), root),
                criteriaBuilder.equal(fromReview.get("rating"), EXCELENT)
                )
        );
    }

    public static Specification<Item> specificationsFromFilter(ItemFilterRequestDto filter) {
        final Specification<Item> spec = (root, query, cb) -> cb.and();
        ofNullable(filter.getName()).ifPresent(s -> spec.and(similarNames(s)));
        ofNullable(filter.getCreatedAfter()).ifPresent(localDateTime -> spec.and(createdAfter(localDateTime)));
        if (filter.getOnlyExcellent()) {
            spec.and(onlyExcellentRated());
        }
        return spec;
    }
}
