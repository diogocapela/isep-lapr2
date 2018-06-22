package lapr.project.controller;

import lapr.project.model.Application;
import lapr.project.model.ApplicationReview;
import lapr.project.model.Event;
import lapr.project.utils.Logger;

/**
 * @author Vítor Hugo Silva (1140825@isep.ipp.pt)
 */
public class UC07SubmitApplicationReviewController extends SuperController {

    private Event selectedEvent;
    private Application selectedApplication;
    private ApplicationReview appR;

    public UC07SubmitApplicationReviewController() {
        appR = new ApplicationReview();
    }

    public void setEvent(Event e) {
        selectedEvent = e;
    }

    public void setApplication(Application a) {
        selectedApplication = a;
    }

    public void setStaffTopicKnowledgeRating(int r) {
        appR.setStaffTopicKnowledgeRating(r);
    }

    public void setEventAdequacyRating(int r) {
        appR.setEventAdequacyRating(r);
    }

    public void setInviteAdequacyRating(int r) {
        appR.setInviteAdequacyRating(r);
    }

    public void setRequestedStandAreaRating(int r) {
        appR.setRequestedStandAreaRating(r);
    }

    public void setOverallRecommendationRating(int r) {
        appR.setOverallRecommendationRating(r);
    }

    public void setJustification(String justification) {
        appR.setJustification(justification);
    }

    public void setAccept(boolean check) {
        appR.setIsAccepted(check);
    }

    public boolean confirm() {
        try {
            appR.setUser(loggedInUser);
            selectedApplication.addApplicationReview(appR);
            Logger.log(USERSTR + getLoggedInUser().getUsername() + " submitted application review " + appR + " to application:" + selectedApplication + " on event:" + selectedEvent);
        } catch (IllegalArgumentException iae) {
            return false;
        }
        return true;
    }
}
