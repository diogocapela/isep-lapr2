package lapr.project.controller;

import lapr.project.model.Application;
import lapr.project.model.ApplicationReview;
import lapr.project.model.Event;
import lapr.project.model.User;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UC32ShowDeviationBetweenStaffAverageRatingForEventMeanControllerTest {

    UC32ShowDeviationBetweenStaffAverageRatingForEventMeanController controller;
    DecimalFormat df = new DecimalFormat("#.##");

    User TEST_USER_1 = new User("Rafael", "rpm@gmail.com", "batatoon", "Rafa");
    User TEST_USER_2 = new User("Mafalda", "mpm@gmail.com", "companhia", "Mafi");

    ApplicationReview TEST_APPLICATION_REVIEW_1 = new ApplicationReview();
    ApplicationReview TEST_APPLICATION_REVIEW_2 = new ApplicationReview();
    ApplicationReview TEST_APPLICATION_REVIEW_3 = new ApplicationReview();

    Application TEST_APPLICATION_1 = new Application();
    Application TEST_APPLICATION_2 = new Application();

    Event TEST_EVENT_0 = new Event(2, "NOT IT", "Everywhere", "Exposul", "2018/9/9", "2018/6/6");
    Event TEST_EVENT_1 = new Event();
    Event TEST_EVENT_2 = new Event(2, "IDK", "This is crazy", "Expocentro?", "2018/9/10", "2018/7/7");

    List<Event> TEST_EVENT_LIST_0 = new ArrayList<>();
    List<Event> TEST_EVENT_LIST_1 = new ArrayList<>();
    List<Event> TEST_EVENT_LIST_2 = new ArrayList<>();

    List<BigDecimal> TEST_BIGDECIMAL_LIST_0 = new ArrayList<>();
    List<BigDecimal> TEST_BIGDECIMAL_LIST_1 = new ArrayList<>();
    List<BigDecimal> TEST_BIGDECIMAL_LIST_2 = new ArrayList<>();
    List<BigDecimal> TEST_BIGDECIMAL_LIST_3 = new ArrayList<>();
    List<BigDecimal> TEST_BIGDECIMAL_LIST_35 = new ArrayList<>();
    List<BigDecimal> TEST_BIGDECIMAL_LIST_4 = new ArrayList<>();
    List<BigDecimal> TEST_BIGDECIMAL_LIST_5 = new ArrayList<>();

    public UC32ShowDeviationBetweenStaffAverageRatingForEventMeanControllerTest() {
        Locale.setDefault(new Locale("en", "GB"));
        controller = new UC32ShowDeviationBetweenStaffAverageRatingForEventMeanController();
        controller.setLoggedInUser(new User("UsernameTEST", "test@isep.ipp.pt", "123456789", "User TEST"));

        TEST_APPLICATION_REVIEW_1.setUser(TEST_USER_1);
        TEST_APPLICATION_REVIEW_1.setIsAccepted(false);
        TEST_APPLICATION_REVIEW_1.setJustification("because");
        TEST_APPLICATION_REVIEW_1.setStaffTopicKnowledgeRating(0);
        TEST_APPLICATION_REVIEW_1.setEventAdequacyRating(0);
        TEST_APPLICATION_REVIEW_1.setInviteAdequacyRating(0);
        TEST_APPLICATION_REVIEW_1.setRequestedStandAreaRating(0);
        TEST_APPLICATION_REVIEW_1.setOverallRecommendationRating(0);

        TEST_APPLICATION_1.setCompanyTradeName("Sonae");
        TEST_APPLICATION_1.setVatNumber(123654789);
        TEST_APPLICATION_1.setPhoneNumber(963258741);
        TEST_APPLICATION_1.setNumberOfInvitations(5);
        TEST_APPLICATION_1.setIntendedStandArea(5);
        TEST_APPLICATION_1.addApplicationReview(TEST_APPLICATION_REVIEW_1);

        TEST_EVENT_1.setId(1);
        TEST_EVENT_1.setTitle("Real Estate Fair");
        TEST_EVENT_1.setDescription("Right here, right now");
        TEST_EVENT_1.setLocation("Exponor");
        TEST_EVENT_1.setDate("2018/8/8");
        TEST_EVENT_1.setDeadline("2018/5/5");
        TEST_EVENT_1.setIsOpenToApplications(false);
        TEST_EVENT_1.addOrganiser(TEST_USER_2);
        TEST_EVENT_1.addStaffMember(TEST_USER_1);
        TEST_EVENT_1.addApplication(TEST_APPLICATION_1);

        TEST_APPLICATION_REVIEW_2.setUser(TEST_USER_2);
        TEST_APPLICATION_REVIEW_2.setIsAccepted(false);
        TEST_APPLICATION_REVIEW_2.setJustification("because");
        TEST_APPLICATION_REVIEW_2.setStaffTopicKnowledgeRating(5);
        TEST_APPLICATION_REVIEW_2.setEventAdequacyRating(5);
        TEST_APPLICATION_REVIEW_2.setInviteAdequacyRating(5);
        TEST_APPLICATION_REVIEW_2.setRequestedStandAreaRating(5);
        TEST_APPLICATION_REVIEW_2.setOverallRecommendationRating(5);

        TEST_APPLICATION_REVIEW_3.setUser(TEST_USER_2);
        TEST_APPLICATION_REVIEW_3.setIsAccepted(false);
        TEST_APPLICATION_REVIEW_3.setJustification("because");
        TEST_APPLICATION_REVIEW_3.setStaffTopicKnowledgeRating(2);
        TEST_APPLICATION_REVIEW_3.setEventAdequacyRating(2);
        TEST_APPLICATION_REVIEW_3.setInviteAdequacyRating(2);
        TEST_APPLICATION_REVIEW_3.setRequestedStandAreaRating(2);
        TEST_APPLICATION_REVIEW_3.setOverallRecommendationRating(2);

        TEST_APPLICATION_2.setCompanyTradeName("Oracle");
        TEST_APPLICATION_2.setVatNumber(123654789);
        TEST_APPLICATION_2.setPhoneNumber(963258741);
        TEST_APPLICATION_2.setNumberOfInvitations(20);
        TEST_APPLICATION_2.setIntendedStandArea(20);
        TEST_APPLICATION_2.addApplicationReview(TEST_APPLICATION_REVIEW_2);
        TEST_APPLICATION_2.addApplicationReview(TEST_APPLICATION_REVIEW_3);

        TEST_EVENT_2.addStaffMember(TEST_USER_1);
        TEST_EVENT_2.addStaffMember(TEST_USER_2);
        TEST_EVENT_2.addApplication(TEST_APPLICATION_2);

        TEST_BIGDECIMAL_LIST_0.add(BigDecimal.valueOf(0));
        TEST_BIGDECIMAL_LIST_0.add(BigDecimal.valueOf(0));

        TEST_BIGDECIMAL_LIST_1.add(BigDecimal.valueOf(1));
        TEST_BIGDECIMAL_LIST_1.add(BigDecimal.valueOf(1));

        TEST_BIGDECIMAL_LIST_2.add(BigDecimal.valueOf(2));
        TEST_BIGDECIMAL_LIST_2.add(BigDecimal.valueOf(2));

        TEST_BIGDECIMAL_LIST_3.add(BigDecimal.valueOf(3));
        TEST_BIGDECIMAL_LIST_3.add(BigDecimal.valueOf(3));

        TEST_BIGDECIMAL_LIST_35.add(BigDecimal.valueOf(3));
        TEST_BIGDECIMAL_LIST_35.add(BigDecimal.valueOf(4));

        TEST_BIGDECIMAL_LIST_4.add(BigDecimal.valueOf(4));
        TEST_BIGDECIMAL_LIST_4.add(BigDecimal.valueOf(4));

        TEST_BIGDECIMAL_LIST_5.add(BigDecimal.valueOf(5));
        TEST_BIGDECIMAL_LIST_5.add(BigDecimal.valueOf(5));

    }

    @Test
    void calculateMeanDeviationEmptyEvent() {
        BigDecimal expected = BigDecimal.valueOf(-6f);
        BigDecimal result = controller.calculateMeanDeviation(TEST_EVENT_0, TEST_BIGDECIMAL_LIST_0);
        assertEquals(expected, result);
    }

    @Test
    void calculateMeanDeviationEventAverage0UserAverage0() {
        BigDecimal expected = BigDecimal.valueOf(0).divide(BigDecimal.ONE,4, BigDecimal.ROUND_HALF_UP);
        BigDecimal result = controller.calculateMeanDeviation(TEST_EVENT_1, TEST_BIGDECIMAL_LIST_0);
        assertEquals(expected, result);
    }

    @Test
    void calculateMeanDeviationEventAverage0UserAverage1() {
        BigDecimal expected = BigDecimal.valueOf(-1).abs().divide(BigDecimal.ONE,4, BigDecimal.ROUND_HALF_UP);
        BigDecimal result = controller.calculateMeanDeviation(TEST_EVENT_1, TEST_BIGDECIMAL_LIST_1);
        assertEquals(expected, result);
    }

    @Test
    void calculateMeanDeviationEventAverage0UserAverage2() {
        BigDecimal expected = BigDecimal.valueOf(-2).abs().divide(BigDecimal.ONE,4, BigDecimal.ROUND_HALF_UP);
        BigDecimal result = controller.calculateMeanDeviation(TEST_EVENT_1, TEST_BIGDECIMAL_LIST_2);
        assertEquals(expected, result);
    }

    @Test
    void calculateMeanDeviationEventAverage0UserAverage3() {
        BigDecimal expected = BigDecimal.valueOf(-3).abs().divide(BigDecimal.ONE,4, BigDecimal.ROUND_HALF_UP);
        BigDecimal result = controller.calculateMeanDeviation(TEST_EVENT_1, TEST_BIGDECIMAL_LIST_3);
        assertEquals(expected, result);
    }

    @Test
    void calculateMeanDeviationEventAverage0UserAverage4() {
        BigDecimal expected = BigDecimal.valueOf(-4).abs().divide(BigDecimal.ONE,4, BigDecimal.ROUND_HALF_UP);
        BigDecimal result = controller.calculateMeanDeviation(TEST_EVENT_1, TEST_BIGDECIMAL_LIST_4);
        assertEquals(expected, result);
    }

    @Test
    void calculateMeanDeviationEventAverage0UserAverage5() {
        BigDecimal expected = BigDecimal.valueOf(-5).abs().divide(BigDecimal.ONE,4, BigDecimal.ROUND_HALF_UP);
        BigDecimal result = controller.calculateMeanDeviation(TEST_EVENT_1, TEST_BIGDECIMAL_LIST_5);
        assertEquals(expected, result);
    }

    @Test
    void calculateMeanDeviationEventAverage3_5UserAverage0() {
        BigDecimal expected = BigDecimal.valueOf(3.5f).divide(BigDecimal.ONE,4, BigDecimal.ROUND_HALF_UP);
        BigDecimal result = controller.calculateMeanDeviation(TEST_EVENT_2, TEST_BIGDECIMAL_LIST_0);
        assertEquals(expected, result);
    }

    @Test
    void calculateMeanDeviationEventAverage3_5UserAverage1() {
        BigDecimal expected = BigDecimal.valueOf(2.5f).divide(BigDecimal.ONE,4, BigDecimal.ROUND_HALF_UP);
        BigDecimal result = controller.calculateMeanDeviation(TEST_EVENT_2, TEST_BIGDECIMAL_LIST_1);
        assertEquals(expected, result);
    }

    @Test
    void calculateMeanDeviationEventAverage3_5UserAverage2() {
        BigDecimal expected = BigDecimal.valueOf(1.5f).divide(BigDecimal.ONE,4, BigDecimal.ROUND_HALF_UP);
        BigDecimal result = controller.calculateMeanDeviation(TEST_EVENT_2, TEST_BIGDECIMAL_LIST_2);
        assertEquals(expected, result);
    }

    @Test
    void calculateMeanDeviationEventAverage3_5UserAverage3() {
        BigDecimal expected = BigDecimal.valueOf(.5f).divide(BigDecimal.ONE,4, BigDecimal.ROUND_HALF_UP);
        BigDecimal result = controller.calculateMeanDeviation(TEST_EVENT_2, TEST_BIGDECIMAL_LIST_3);
        assertEquals(expected, result);
    }

    @Test
    void calculateMeanDeviationEventAverage3_5UserAverage4() {
        BigDecimal expected = BigDecimal.valueOf(-.5f).abs().divide(BigDecimal.ONE,4, BigDecimal.ROUND_HALF_UP);
        BigDecimal result = controller.calculateMeanDeviation(TEST_EVENT_2, TEST_BIGDECIMAL_LIST_4);
        assertEquals(expected, result);
    }

    @Test
    void calculateMeanDeviationEventAverage3_5UserAverage5() {
        BigDecimal expected = BigDecimal.valueOf(-1.5f).abs().divide(BigDecimal.ONE,4, BigDecimal.ROUND_HALF_UP);
        BigDecimal result = controller.calculateMeanDeviation(TEST_EVENT_2, TEST_BIGDECIMAL_LIST_5);
        assertEquals(expected, result);
    }

    @Test
    void calculateMeanDeviationEventAverage35UserAverage35() {
        BigDecimal expected = BigDecimal.valueOf(0.5f).abs().divide(BigDecimal.ONE,4, BigDecimal.ROUND_HALF_UP);
        BigDecimal result = controller.calculateMeanDeviation(TEST_EVENT_2, TEST_BIGDECIMAL_LIST_35);
        assertEquals(expected, result);
    }
}
