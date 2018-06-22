package lapr.project.controller;

import lapr.project.model.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 *
 * @author Vítor Hugo Silva (1140825@isep.ipp.pt)
 */
public class UC07SubmitApplicationReviewControllerTest {

    private UC07SubmitApplicationReviewController controller;
    private User TEST_USER_1 = new User("Rafael", "rpm@gmail.com", "batatoon", "Rafa");
    private User TEST_USER_2 = new User("Mafalda", "mpm@gmail.com", "companhia", "Mafi");
    private User TEST_USER_3 = new User("Neutro", "npn@gmail.com", "TVI", "Neu");
    private Stand TEST_STAND_1 = new Stand("ab", 1);
    private Stand TEST_STAND_2 = new Stand("cd", 2);
    private Keyword TEST_KEYWORD = new Keyword("TEST_KEYWORD");
    private List<Keyword> TEST_KEYWORD_LIST = new ArrayList<>();
    private DisplayProduct TEST_DISPLAY_PRODUCT = new DisplayProduct("TEST_PRODUCT");
    private List<DisplayProduct> TEST_DISPLAY_PRODUCT_LIST = new ArrayList<>();
    private List<Integer> TEST_RATINGS = new ArrayList<>();
    private ApplicationReview TEST_APPLICATION_REVIEW = new ApplicationReview();
    private List<ApplicationReview> TEST_APPLICATION_REVIEW_LIST = new ArrayList<>();
    private Application TEST_APPLICATION_1 = new Application();
    private Application TEST_APPLICATION_2 = new Application();
    private Event TEST_EVENT_1 = new Event();

    public UC07SubmitApplicationReviewControllerTest() {
        Expo.getInstance().setLoggedInUser(TEST_USER_1);
        controller = new UC07SubmitApplicationReviewController();
        controller.setLoggedInUser(TEST_USER_1);
        TEST_APPLICATION_1.setCompanyTradeName("Sonae");
        TEST_APPLICATION_1.setVatNumber(123654789);
        TEST_APPLICATION_1.setPhoneNumber(963258741);
        TEST_APPLICATION_1.setNumberOfInvitations(5);
        TEST_APPLICATION_1.setIntendedStandArea(5);
        TEST_APPLICATION_1.setDisplayProducts(TEST_DISPLAY_PRODUCT_LIST);
        TEST_APPLICATION_1.setKeywords(TEST_KEYWORD_LIST);
        TEST_APPLICATION_1.setStatus("reviewPending");
        TEST_APPLICATION_1.setAssignedStand(TEST_STAND_2);
        TEST_APPLICATION_1.addWorkshop(new Workshop("Title1", "Description1", 10, 10));
        

        TEST_EVENT_1.setId(1);
        TEST_EVENT_1.setTitle("Real Estate Fair");
        TEST_EVENT_1.setDescription("Right here, right now");
        TEST_EVENT_1.setLocation("Exponor");
        TEST_EVENT_1.setDate("2018/8/8");
        TEST_EVENT_1.setDeadline("2018/5/5");
        TEST_EVENT_1.setIsOpenToApplications(false);
        TEST_EVENT_1.addOrganiser(TEST_USER_1);
        TEST_EVENT_1.addStaffMember(TEST_USER_2);
        TEST_EVENT_1.addStand(TEST_STAND_1);
        TEST_EVENT_1.addApplication(TEST_APPLICATION_1);
    }

    @Test
    public void testValidUC() {

        controller.setEvent(TEST_EVENT_1);
        controller.setApplication(TEST_EVENT_1.getApplicationList().get(0));
        int[] rating = new int[]{1, 2, 3, 4, 5};
        controller.setEventAdequacyRating(rating[0]);
        controller.setInviteAdequacyRating(rating[1]);
        controller.setOverallRecommendationRating(rating[2]);
        controller.setRequestedStandAreaRating(rating[3]);
        controller.setStaffTopicKnowledgeRating(rating[4]);

        boolean accepted = true;

        String justification = "Example 1";

        controller.setAccept(accepted);
        controller.setJustification(justification);

        assertTrue(controller.confirm());

        //base case
        ApplicationReview appR = new ApplicationReview();
        appR.setEventAdequacyRating(rating[0]);
        appR.setInviteAdequacyRating(rating[1]);
        appR.setOverallRecommendationRating(rating[2]);
        appR.setRequestedStandAreaRating(rating[3]);
        appR.setStaffTopicKnowledgeRating(rating[4]);
        appR.setIsAccepted(accepted);
        appR.setJustification(justification);
        appR.setUser(TEST_USER_1);

        ApplicationReview test = TEST_APPLICATION_1.getReviews().get(0);

        assertEquals(appR, test);
    }

}
