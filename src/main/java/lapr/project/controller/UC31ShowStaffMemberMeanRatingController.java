package lapr.project.controller;

import lapr.project.model.Application;
import lapr.project.model.ApplicationReview;
import lapr.project.model.Event;
import lapr.project.model.User;
import lapr.project.utils.Logger;

import java.math.BigDecimal;
import java.util.List;

public class UC31ShowStaffMemberMeanRatingController extends SuperController {

    public float calculateMeanRatingAllEvents(User u, List<Event> eventList) {
        float result = 0;
        int reviewCounter = 0;
        for (Event e : eventList) {
            for (Application a : e.getApplicationList()) {
                float averageByApplication = calculateReviewMean(u, a);
                if (!BigDecimal.valueOf(averageByApplication).equals(BigDecimal.valueOf(-1f))) {
                    result = result + averageByApplication;
                    reviewCounter++;
                }
            }
        }
        Logger.log(USERSTR + getLoggedInUser().getUsername() + " calculated mean rating all events for user:" + u);
        return reviewCounter == 0 ? -1 : result / reviewCounter;
    }

    public float calculateReviewMean(User u, Application a) {
        for (ApplicationReview tmpReview : a.getReviews()) {
            if (tmpReview.getUser().equals(u)) {
                Logger.log(USERSTR + getLoggedInUser().getUsername() + " calculated review mean for user:" + u + " for application:" + a);
                return (tmpReview.getStaffTopicKnowledgeRating() + tmpReview.getEventAdequacyRating() + tmpReview.getInviteAdequacyRating() + tmpReview.getRequestedStandAreaRating() + tmpReview.getOverallRecommendationRating()) / 5f;
            }
        }
        return -1;
    }
}
