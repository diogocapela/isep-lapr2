package lapr.project.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Represents a review of a company application from a a staff member.
 * <p>
 * Staff members are employees that later-on, in addition to other tasks, review event applications.
 * Organisers assign applications to staff members to be reviewed. This process is only possible after
 * the applications’ submission deadline and can be achieved by importing a file with that data.
 * <p>
 * Created by Diogo Capela (1171316@isep.ipp.pt).
 */
@XmlRootElement
public class ApplicationReview {

    // Instance variables
    private User user = new User();
    private boolean isAccepted = false;
    private String justification = "";
    private int staffTopicKnowledgeRating = 0;
    private int eventAdequacyRating = 0;
    private int inviteAdequacyRating = 0;
    private int requestedStandAreaRating = 0;
    private int overallRecommendationRating = 0;

    /**
     * Default public empty constructor.
     */
    public ApplicationReview() {

    }

    /**
     * Constructor for the ApplicationReview class.
     *
     * @param user                        The staff member who reviewed the application.
     * @param isAccepted                  If the application is accepted by the staff member.
     * @param justification               Justification for the review ratings by the staff member.
     * @param staffTopicKnowledgeRating   Staff member’s knowledge about the application (0-5).
     * @param eventAdequacyRating         Application adequacy for the event (0-5).
     * @param inviteAdequacyRating        Invitations quantity adequacy for the application (0-5).
     * @param requestedStandAreaRating    Requested stand area (0-5).
     * @param overallRecommendationRating Overall recommendation (0-5).
     */
    public ApplicationReview(User user, boolean isAccepted, String justification, int staffTopicKnowledgeRating, int eventAdequacyRating, int inviteAdequacyRating, int requestedStandAreaRating, int overallRecommendationRating) {
        this.user = user;
        this.isAccepted = isAccepted;
        this.justification = justification;
        this.staffTopicKnowledgeRating = staffTopicKnowledgeRating;
        this.eventAdequacyRating = eventAdequacyRating;
        this.inviteAdequacyRating = inviteAdequacyRating;
        this.requestedStandAreaRating = requestedStandAreaRating;
        this.overallRecommendationRating = overallRecommendationRating;
    }

    /* Getters & Setters
    ===================================== */

    /**
     * Gets the staff member who wrote the review.
     *
     * @return user
     */
    @XmlElement
    public User getUser() {
        return user;
    }

    /**
     * Sets the staff member who wrote the review.
     *
     * @param user staff member
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * Gets the review status of acceptance.
     *
     * @return isAccepted
     */
    @XmlElement
    public boolean getIsAccepted() {
        return isAccepted;
    }

    /**
     * Sets the review status of acceptance.
     *
     * @param isAccepted review status
     */
    public void setIsAccepted(boolean isAccepted) {
        this.isAccepted = isAccepted;
    }

    /**
     * Gets the review ratings justification by the staff member.
     *
     * @return justification
     */
    @XmlElement
    public String getJustification() {
        return justification;
    }

    /**
     * Sets the review ratings justification by the staff member.
     *
     * @param justification justification of review rate by staff
     */
    public void setJustification(String justification) {
        this.justification = justification;
    }

    /**
     * Gets the review rating of the staff member’s knowledge about the application (0-5).
     *
     * @return staffTopicKnowledgeRating
     */
    @XmlElement
    public int getStaffTopicKnowledgeRating() {
        return staffTopicKnowledgeRating;
    }

    /**
     * Sets the review rating of the staff member’s knowledge about the application (0-5).
     *
     * @param staffTopicKnowledgeRating review rate
     */
    public void setStaffTopicKnowledgeRating(int staffTopicKnowledgeRating) {
        this.staffTopicKnowledgeRating = staffTopicKnowledgeRating;
    }

    /**
     * Gets the review rating of the application adequacy for the event (0-5).
     *
     * @return isAccepted
     */
    @XmlElement
    public int getEventAdequacyRating() {
        return eventAdequacyRating;
    }

    /**
     * Sets the review rating of the application adequacy for the event (0-5).
     *
     * @param eventAdequacyRating review rate
     */
    public void setEventAdequacyRating(int eventAdequacyRating) {
        this.eventAdequacyRating = eventAdequacyRating;
    }

    /**
     * Gets the review rating of the invitations quantity adequacy for the application (0-5).
     *
     * @return isAccepted
     */
    @XmlElement
    public int getInviteAdequacyRating() {
        return inviteAdequacyRating;
    }

    /**
     * Sets the review rating of the invitations quantity adequacy for the application (0-5).
     *
     * @param inviteAdequacyRating review rate
     */
    public void setInviteAdequacyRating(int inviteAdequacyRating) {
        this.inviteAdequacyRating = inviteAdequacyRating;
    }

    /**
     * Gets the review rating of the requested stand area (0-5).
     *
     * @return isAccepted
     */
    @XmlElement
    public int getRequestedStandAreaRating() {
        return requestedStandAreaRating;
    }

    /**
     * Sets the review rating of the requested stand area (0-5).
     *
     * @param requestedStandAreaRating requested area
     */
    public void setRequestedStandAreaRating(int requestedStandAreaRating) {
        this.requestedStandAreaRating = requestedStandAreaRating;
    }

    /**
     * Gets the rating for the overall recommendation (0-5).
     *
     * @return isAccepted
     */
    @XmlElement
    public int getOverallRecommendationRating() {
        return overallRecommendationRating;
    }

    /**
     * Sets the rating for the overall recommendation (0-5).
     *
     * @param overallRecommendationRating rate recommendation
     */
    public void setOverallRecommendationRating(int overallRecommendationRating) {
        this.overallRecommendationRating = overallRecommendationRating;
    }

    /* toString, equals & hashCode
    ===================================== */

    @Override
    public String toString() {
        return String.format("Staff Member: %s | Accepted: %s | Justification: %s | Ratings: %d/%d/%d/%d/%d", this.user.getUsername(), this.isAccepted, this.justification, this.staffTopicKnowledgeRating, this.eventAdequacyRating, this.inviteAdequacyRating, this.requestedStandAreaRating, this.overallRecommendationRating);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ApplicationReview that = (ApplicationReview) o;
        return getUser().equals(that.getUser()) && getJustification().equals(that.getJustification()) && getOverallRecommendationRating() == that.getOverallRecommendationRating() && getRequestedStandAreaRating() == that.getRequestedStandAreaRating() && getInviteAdequacyRating() == that.getInviteAdequacyRating() && getEventAdequacyRating() == that.getEventAdequacyRating() && getStaffTopicKnowledgeRating() == that.getStaffTopicKnowledgeRating();
    }

    @Override
    public int hashCode() {
        return user.hashCode() + Boolean.hashCode(isAccepted) + justification.hashCode() + staffTopicKnowledgeRating + eventAdequacyRating + inviteAdequacyRating + requestedStandAreaRating + overallRecommendationRating;
    }

}

