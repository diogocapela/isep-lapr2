package lapr.project.controller;

import lapr.project.model.*;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UC30ShowEventAcceptanceRateControllerTest {

    private UC30ShowEventAcceptanceRateController controller;

    private User TEST_USER_1 = new User("Rafael", "rpm@gmail.com", "batatoon", "Rafa");
    private User TEST_USER_2 = new User("Mafalda", "mpm@gmail.com", "companhia", "Mafi");
    private User TEST_USER_3 = new User("Neutro", "npn@gmail.com", "TVI", "Neu");
    private Stand TEST_STAND_1 = new Stand("ab", 1);
    private Stand TEST_STAND_2 = new Stand("cd", 1);
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
    private Event TEST_EVENT_2 = new Event(2, "NOT IT", "Everywhere", "Exposul", "2018/9/9", "2018/6/6");
    private Event TEST_EVENT_3 = new Event(2, "IDK", "This is crazy", "Expocentro?", "2018/9/10", "2018/7/7");
    private Event TEST_EVENT_4 = new Event(2, "FINALLY", "Why do I keep writting?", "ExpoILHAS", "2018/9/10", "2018/7/7");

    public UC30ShowEventAcceptanceRateControllerTest() {

        controller = new UC30ShowEventAcceptanceRateController();

        TEST_DISPLAY_PRODUCT_LIST.add(TEST_DISPLAY_PRODUCT);

        TEST_KEYWORD_LIST.add(TEST_KEYWORD);

        TEST_APPLICATION_REVIEW.setUser(TEST_USER_1);
        TEST_APPLICATION_REVIEW.setIsAccepted(false);
        TEST_APPLICATION_REVIEW.setJustification("because");
        TEST_APPLICATION_REVIEW.setStaffTopicKnowledgeRating(1);
        TEST_APPLICATION_REVIEW.setEventAdequacyRating(2);
        TEST_APPLICATION_REVIEW.setInviteAdequacyRating(3);
        TEST_APPLICATION_REVIEW.setRequestedStandAreaRating(4);
        TEST_APPLICATION_REVIEW.setOverallRecommendationRating(5);
        TEST_APPLICATION_REVIEW_LIST.add(TEST_APPLICATION_REVIEW);

        TEST_APPLICATION_1.setCompanyTradeName("Sonae");
        TEST_APPLICATION_1.setVatNumber(123654789);
        TEST_APPLICATION_1.setPhoneNumber(963258741);
        TEST_APPLICATION_1.setNumberOfInvitations(5);
        TEST_APPLICATION_1.setIntendedStandArea(5);
        TEST_APPLICATION_1.setDisplayProducts(TEST_DISPLAY_PRODUCT_LIST);
        TEST_APPLICATION_1.setKeywords(TEST_KEYWORD_LIST);
        TEST_APPLICATION_1.setStatus("reviewPending");
        TEST_APPLICATION_1.setAssignedStand(TEST_STAND_2);
        TEST_APPLICATION_1.setReviews(TEST_APPLICATION_REVIEW_LIST);

        TEST_APPLICATION_2.setCompanyTradeName("Oracle");
        TEST_APPLICATION_2.setVatNumber(123654789);
        TEST_APPLICATION_2.setPhoneNumber(963258741);
        TEST_APPLICATION_2.setNumberOfInvitations(20);
        TEST_APPLICATION_2.setIntendedStandArea(20);
        TEST_APPLICATION_2.setDisplayProducts(TEST_DISPLAY_PRODUCT_LIST);
        TEST_APPLICATION_2.setKeywords(TEST_KEYWORD_LIST);
        TEST_APPLICATION_2.setStatus("accepted");
        TEST_APPLICATION_2.setAssignedStand(TEST_STAND_1);
        TEST_APPLICATION_2.setReviews(TEST_APPLICATION_REVIEW_LIST);

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
        TEST_EVENT_3.addApplication(TEST_APPLICATION_2);
        TEST_EVENT_4.addApplication(TEST_APPLICATION_1);
        TEST_EVENT_4.addApplication(TEST_APPLICATION_2);
    }

    @Test
    void getEventAcceptanceRateZeroApplications() {
        BigDecimal expected = BigDecimal.valueOf(0.0);
        BigDecimal result = BigDecimal.valueOf(controller.getEventAcceptanceRate(TEST_EVENT_2));
        assertEquals(expected, result);
    }

    @Test
    void getEventAcceptanceRateOneApprovedApplication() {
        BigDecimal expected = BigDecimal.valueOf(100.0);
        BigDecimal result = BigDecimal.valueOf(controller.getEventAcceptanceRate(TEST_EVENT_3));
        assertEquals(expected, result);
    }

    @Test
    void getEventAcceptanceRateOneNotApprovedApplication() {
        BigDecimal expected = BigDecimal.valueOf(0.0);
        BigDecimal result = BigDecimal.valueOf(controller.getEventAcceptanceRate(TEST_EVENT_1));
        assertEquals(expected, result);
    }

    @Test
    void getEventAcceptanceRateTwoApplicationsDifferentStatus() {
        BigDecimal expected = BigDecimal.valueOf(50.0);
        BigDecimal result = BigDecimal.valueOf(controller.getEventAcceptanceRate(TEST_EVENT_4));
        assertEquals(expected, result);
    }
}