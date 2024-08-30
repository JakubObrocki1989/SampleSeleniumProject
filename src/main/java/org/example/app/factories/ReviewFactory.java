package org.example.app.factories;

import org.example.app.models.Review;
import org.example.core.base.BaseFactory;

public class ReviewFactory extends BaseFactory {

    public Review getRandomReview() {
        Review.ReviewBuilder review = Review.builder();
        review.name(faker.name().username());
        review.email(faker.internet().emailAddress());
        review.review(faker.letterify("test"));
        return review.build();
    }
}
