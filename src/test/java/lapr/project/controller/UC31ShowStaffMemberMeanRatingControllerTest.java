package lapr.project.controller;

import lapr.project.model.*;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UC31ShowStaffMemberMeanRatingControllerTest {
    UC31ShowStaffMemberMeanRatingController controller;
    List<Event> TEST_EVENT_LIST_0 = new ArrayList<>();
    List<Event> TEST_EVENT_LIST_1 = new ArrayList<>();
    List<Event> TEST_EVENT_LIST_2 = new ArrayList<>();
    private User TEST_USER_1 = new User("Rafael", "rpm@gmail.com", "batatoon", "Rafa");
    private User TEST_USER_2 = new User("Mafalda", "mpm@gmail.com", "companhia", "Mafi");
    private Stand TEST_STAND_1 = new Stand("ab", 1);
    private Stand TEST_STAND_2 = new Stand("cd", 1);
    private Keyword TEST_KEYWORD = new Keyword("TEST_KEYWORD");
    private List<Keyword> TEST_KEYWORD_LIST = new ArrayList<>();
    private DisplayProduct TEST_DISPLAY_PRODUCT = new DisplayProduct("TEST_PRODUCT");
    private List<DisplayProduct> TEST_DISPLAY_PRODUCT_LIST = new ArrayList<>();
    private ApplicationReview TEST_APPLICATION_REVIEW_1 = new ApplicationReview();
    private ApplicationReview TEST_APPLICATION_REVIEW_2 = new ApplicationReview();
    private List<ApplicationReview> TEST_APPLICATION_REVIEW_LIST_0 = new ArrayList<>();
    private List<ApplicationReview> TEST_APPLICATION_REVIEW_LIST_1 = new ArrayList<>();
    private List<ApplicationReview> TEST_APPLICATION_REVIEW_LIST_2 = new ArrayList<>();
    private List<ApplicationReview> TEST_APPLICATION_REVIEW_LIST_3 = new ArrayList<>();
    private Application TEST_APPLICATION_0 = new Application();
    private Application TEST_APPLICATION_1 = new Application();
    private Application TEST_APPLICATION_2 = new Application();
    private Application TEST_APPLICATION_3 = new Application();
    private Event TEST_EVENT_0 = new Event(2, "NOT IT", "Everywhere", "Exposul", "2018/9/9", "2018/6/6");
    private Event TEST_EVENT_1 = new Event();
    private Event TEST_EVENT_2 = new Event(2, "IDK", "This is crazy", "Expocentro?", "2018/9/10", "2018/7/7");
    private Event TEST_EVENT_3 = new Event(2, "FINALLY", "Why do I keep writting?", "ExpoILHAS", "2018/9/10", "2018/7/7");

    public UC31ShowStaffMemberMeanRatingControllerTest() {
        controller = new UC31ShowStaffMemberMeanRatingController();

        TEST_DISPLAY_PRODUCT_LIST.add(TEST_DISPLAY_PRODUCT);

        TEST_KEYWORD_LIST.add(TEST_KEYWORD);

        TEST_APPLICATION_0.setReviews(TEST_APPLICATION_REVIEW_LIST_0);
        TEST_EVENT_0.addApplication(TEST_APPLICATION_0);

        TEST_APPLICATION_REVIEW_1.setUser(TEST_USER_1);
        TEST_APPLICATION_REVIEW_1.setIsAccepted(false);
        TEST_APPLICATION_REVIEW_1.setJustification("because");
        TEST_APPLICATION_REVIEW_1.setStaffTopicKnowledgeRating(1);
        TEST_APPLICATION_REVIEW_1.setEventAdequacyRating(2);
        TEST_APPLICATION_REVIEW_1.setInviteAdequacyRating(3);
        TEST_APPLICATION_REVIEW_1.setRequestedStandAreaRating(4);
        TEST_APPLICATION_REVIEW_1.setOverallRecommendationRating(5);

        TEST_APPLICATION_1.setCompanyTradeName("Sonae");
        TEST_APPLICATION_1.setVatNumber(123654789);
        TEST_APPLICATION_1.setPhoneNumber(963258741);
        TEST_APPLICATION_1.setNumberOfInvitations(5);
        TEST_APPLICATION_1.setIntendedStandArea(5);
        TEST_APPLICATION_1.setDisplayProducts(TEST_DISPLAY_PRODUCT_LIST);
        TEST_APPLICATION_1.setKeywords(TEST_KEYWORD_LIST);
        TEST_APPLICATION_1.setStatus("reviewPending");
        TEST_APPLICATION_1.setAssignedStand(TEST_STAND_2);
        TEST_APPLICATION_1.addApplicationReview(TEST_APPLICATION_REVIEW_1);

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

        TEST_APPLICATION_REVIEW_2.setUser(TEST_USER_1);
        TEST_APPLICATION_REVIEW_2.setIsAccepted(false);
        TEST_APPLICATION_REVIEW_2.setJustification("because");
        TEST_APPLICATION_REVIEW_2.setStaffTopicKnowledgeRating(5);
        TEST_APPLICATION_REVIEW_2.setEventAdequacyRating(5);
        TEST_APPLICATION_REVIEW_2.setInviteAdequacyRating(5);
        TEST_APPLICATION_REVIEW_2.setRequestedStandAreaRating(5);
        TEST_APPLICATION_REVIEW_2.setOverallRecommendationRating(5);

        TEST_APPLICATION_2.setCompanyTradeName("Oracle");
        TEST_APPLICATION_2.setVatNumber(123654789);
        TEST_APPLICATION_2.setPhoneNumber(963258741);
        TEST_APPLICATION_2.setNumberOfInvitations(20);
        TEST_APPLICATION_2.setIntendedStandArea(20);
        TEST_APPLICATION_2.setDisplayProducts(TEST_DISPLAY_PRODUCT_LIST);
        TEST_APPLICATION_2.setKeywords(TEST_KEYWORD_LIST);
        TEST_APPLICATION_2.setStatus("accepted");
        TEST_APPLICATION_2.setAssignedStand(TEST_STAND_1);
        TEST_APPLICATION_2.addApplicationReview(TEST_APPLICATION_REVIEW_2);

        TEST_EVENT_2.addApplication(TEST_APPLICATION_2);

        TEST_APPLICATION_REVIEW_LIST_3.add(TEST_APPLICATION_REVIEW_1);
        TEST_APPLICATION_REVIEW_LIST_3.add(TEST_APPLICATION_REVIEW_2);
        TEST_APPLICATION_3.setReviews(TEST_APPLICATION_REVIEW_LIST_3);
        TEST_EVENT_3.addApplication(TEST_APPLICATION_3);

        TEST_EVENT_LIST_1.add(TEST_EVENT_2);

        TEST_EVENT_LIST_2.add(TEST_EVENT_1);
        TEST_EVENT_LIST_2.add(TEST_EVENT_2);


    }

    @Test
    void testCalculateNullReviewMean() {
        BigDecimal expected = BigDecimal.valueOf(-1f);
        BigDecimal result = BigDecimal.valueOf(controller.calculateReviewMean(TEST_USER_1, TEST_APPLICATION_0));
        assertEquals(expected, result);
    }

    @Test
    void testCalculateWrongUserReviewMean() {
        BigDecimal expected = BigDecimal.valueOf(-1f);
        BigDecimal result = BigDecimal.valueOf(controller.calculateReviewMean(TEST_USER_2, TEST_APPLICATION_2));
        assertEquals(expected, result);
    }

    @Test
    void testCalculateOneApplicationReviewMean() {
        BigDecimal expected = BigDecimal.valueOf(5f);
        BigDecimal result = BigDecimal.valueOf(controller.calculateReviewMean(TEST_USER_1, TEST_APPLICATION_2));
        assertEquals(expected, result);
    }

    @Test
    void testCalculateMultipleApplicationReviewsMean() {
        BigDecimal expected = BigDecimal.valueOf(3f);
        BigDecimal result = BigDecimal.valueOf(controller.calculateReviewMean(TEST_USER_1, TEST_APPLICATION_3));
        assertEquals(expected, result);
    }

    @Test
    void testCalculateEmptyEventListMean() {
        BigDecimal expected = BigDecimal.valueOf(-1f);
        BigDecimal result = BigDecimal.valueOf(controller.calculateMeanRatingAllEvents(TEST_USER_1, TEST_EVENT_LIST_0));
        assertEquals(expected, result);
    }

    @Test
    void testCalculateOneEventListMean() {
        BigDecimal expected = BigDecimal.valueOf(5f);
        BigDecimal result = BigDecimal.valueOf(controller.calculateMeanRatingAllEvents(TEST_USER_1, TEST_EVENT_LIST_1));
        assertEquals(expected, result);
    }

    @Test
    void testCalculateTwoEventListMean() {
        BigDecimal expected = BigDecimal.valueOf(4f);
        BigDecimal result = BigDecimal.valueOf(controller.calculateMeanRatingAllEvents(TEST_USER_1, TEST_EVENT_LIST_2));
        assertEquals(expected, result);
    }
}