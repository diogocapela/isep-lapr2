package lapr.project.controller;

import lapr.project.model.Application;
import lapr.project.model.ApplicationReview;
import lapr.project.model.Event;
import lapr.project.utils.Logger;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class UC32ShowDeviationBetweenStaffAverageRatingForEventMeanController extends SuperController {

    public BigDecimal calculateMeanDeviation(Event e, List<BigDecimal> userReviewsAverage) {

        BigDecimal eventAverage = BigDecimal.valueOf(0);
        int reviewCounter = 0;
        for (Application tmpApplication : e.getApplicationList()) {
            for (ApplicationReview tmpReview : tmpApplication.getReviews()) {
                reviewCounter++;
                eventAverage = eventAverage.add(BigDecimal.valueOf(tmpReview.getStaffTopicKnowledgeRating() + tmpReview.getEventAdequacyRating() + tmpReview.getInviteAdequacyRating() + tmpReview.getRequestedStandAreaRating() + (float) tmpReview.getOverallRecommendationRating()));
            }
        }

        //make sure not dividing by zero
        if (reviewCounter != 0) {
            eventAverage = eventAverage.divide(BigDecimal.valueOf(reviewCounter * 5f),4, BigDecimal.ROUND_HALF_UP);
        } else {
            return BigDecimal.valueOf(-6f);
        }

        List<BigDecimal> deviations = new ArrayList<>();
        BigDecimal deviationSum = BigDecimal.valueOf(0);

        for (BigDecimal userReviewAverageTmp : userReviewsAverage) {
            deviations.add(eventAverage.subtract(userReviewAverageTmp).abs());
        }

        for (BigDecimal deviationsTmp : deviations) {
            deviationSum = deviationSum.add(deviationsTmp);
        }
        Logger.log(USERSTR + getLoggedInUser().getUsername() + " calculated mean deviation for event:" + e);
        return userReviewsAverage.isEmpty() ? BigDecimal.valueOf(-6f) : deviationSum.divide(BigDecimal.valueOf(deviations.size()));
    }
}
