package lapr.project.model;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Testing the Application class.
 * <p>
 * Created by Diogo Capela [1171316@isep.ipp.pt] on 03/06/2018.
 */
public class ApplicationTest {

    Keyword TEST_KEYWORD_1 = new Keyword("lol");

    DisplayProduct TEST_DISPLAY_PRODUCT_1 = new DisplayProduct("water");

    Stand TEST_STAND_1 = new Stand("Stand2", 40);

    Workshop TEST_WORKSHOP_1 = new Workshop("w1", "workshop test", 100);

    ApplicationReview TEST_REVIEW_1 = new ApplicationReview();

    User TEST_USER_0 = new User("0", "0", "00000000", "0");
    User TEST_USER_1 = new User("1", "1", "00000000", "1");
    User TEST_USER_2 = new User("2", "2", "00000000", "2");

    List<User> TEST_USER_LIST_1 = new ArrayList<>();

    Application TEST_APPLICATION_0 = new Application();
    Application TEST_APPLICATION_1 = new Application();

    public ApplicationTest() {
        TEST_REVIEW_1.setStaffTopicKnowledgeRating(1);
        TEST_REVIEW_1.setEventAdequacyRating(1);
        TEST_REVIEW_1.setInviteAdequacyRating(1);
        TEST_REVIEW_1.setRequestedStandAreaRating(1);
        TEST_REVIEW_1.setOverallRecommendationRating(1);

        TEST_APPLICATION_1.setAuthor(TEST_USER_1);
        TEST_APPLICATION_1.setCompanyTradeName("Lol");
        TEST_APPLICATION_1.setDescription("description test");
        TEST_APPLICATION_1.setVatNumber(125478963);
        TEST_APPLICATION_1.setPhoneNumber(985632147);
        TEST_APPLICATION_1.setNumberOfInvitations(10);
        TEST_APPLICATION_1.setIntendedStandArea(50);
        TEST_APPLICATION_1.setAssignedStand(TEST_STAND_1);
        TEST_APPLICATION_1.addWorkshop(TEST_WORKSHOP_1);
        TEST_APPLICATION_1.addApplicationReview(TEST_REVIEW_1);
        TEST_APPLICATION_1.addKeyword(TEST_KEYWORD_1);
        TEST_APPLICATION_1.addStaffReviewer(TEST_USER_0);
        TEST_APPLICATION_1.addDisplayProduct(TEST_DISPLAY_PRODUCT_1);

        TEST_USER_LIST_1.add(TEST_USER_2);
    }

    @Test
    public void testGetAndSetCompanyTradeName() {
        String expected = "Trade Company Test Name";
        Application application = new Application();
        application.setCompanyTradeName("Trade Company Test Name");
        String result = application.getCompanyTradeName();
        assertEquals(expected, result);
    }

    @Test
    public void testGetAndSetVatNumber() {
        int expected = 123456789;
        Application application = new Application();
        application.setVatNumber(123456789);
        int result = application.getVatNumber();
        assertEquals(expected, result);
    }

    @Test
    public void testGetAndSetPhoneNumber() {
        int expected = 123456789;
        Application application = new Application();
        application.setPhoneNumber(123456789);
        int result = application.getPhoneNumber();
        assertEquals(expected, result);
    }

    @Test
    public void testGetAndSetNumberOfInvitations() {
        int expected = 50;
        Application application = new Application();
        application.setNumberOfInvitations(50);
        int result = application.getNumberOfInvitations();
        assertEquals(expected, result);
    }

    @Test
    public void testGetAndSetIntendedStand() {
        double expected = 10;
        Application application = new Application();
        application.setIntendedStandArea(expected);
        double result = application.getIntendedStandArea();
        assertEquals(expected, result);
    }

    @Test
    public void testGetAndSetDisplayProducts() {
        List<DisplayProduct> displayProducts = new ArrayList<>();
        List<DisplayProduct> expected = displayProducts;
        Application application = new Application();
        application.setDisplayProducts(displayProducts);
        List<DisplayProduct> result = application.getDisplayProducts();
        assertEquals(expected, result);
    }

    @Test
    public void testGetAndSetKeywords() {
        List<Keyword> keywords = new ArrayList<>();
        List<Keyword> expected = keywords;
        Application application = new Application();
        application.setKeywords(keywords);
        List<Keyword> result = application.getKeywords();
        assertEquals(expected, result);
    }

    @Test
    public void testGetAndSetStatus() {
        String expected = "accepted";
        Application application = new Application();
        application.setStatus("accepted");
        String result = application.getStatus();
        assertEquals(expected, result);
    }

    @Test
    public void testGetAndSetAssignedStand() {
        Stand expected = new Stand();
        Application application = new Application();
        application.setAssignedStand(expected);
        Stand result = application.getAssignedStand();
        assertEquals(expected, result);
    }

    @Test
    public void testGetAndSetReviews() {
        List<ApplicationReview> reviews = new ArrayList<>();
        List<ApplicationReview> expected = reviews;
        Application application = new Application();
        application.setReviews(reviews);
        List<ApplicationReview> result = application.getReviews();
        assertEquals(expected, result);
    }

    @Test
    public void testGetAndSetReviewers() {
        List<User> expected = TEST_USER_LIST_1;
        TEST_APPLICATION_1.setStaffReviewers(TEST_USER_LIST_1);
        List<User> result = TEST_APPLICATION_1.getStaffReviewers();
        assertEquals(expected, result);
    }

    @Test
    public void testAddValidReviewer() {
        int expected = TEST_APPLICATION_1.getStaffReviewers().size() + 1;
        TEST_APPLICATION_1.addStaffReviewer(TEST_USER_2);
        int result = TEST_APPLICATION_1.getStaffReviewers().size();
        assertEquals(expected, result);
    }

    @Test
    public void testAddNotValidReviewer() throws Exception {
        assertThrows(IllegalArgumentException.class, () -> {
            TEST_APPLICATION_1.addStaffReviewer(TEST_USER_0);
        });
    }

    @Test
    public void testAddDisplayProduct() throws Exception {
        List<DisplayProduct> expected = new ArrayList<>();
        expected.add(new DisplayProduct("Product"));
        Application application = new Application();
        application.addDisplayProduct(new DisplayProduct("Product"));
        List<DisplayProduct> result = application.getDisplayProducts();
        assertArrayEquals(expected.toArray(), result.toArray());
    }

    @Test
    public void testAddKeyword() throws Exception {
        List<Keyword> expected = new ArrayList<>();
        expected.add(new Keyword("Doors"));
        Application application = new Application();
        application.addKeyword(new Keyword("Doors"));
        List<Keyword> result = application.getKeywords();
        assertArrayEquals(expected.toArray(), result.toArray());
    }

    @Test
    public void testAddApplicationReview() throws Exception {
        List<ApplicationReview> expected = new ArrayList<>();
        expected.add(new ApplicationReview(new User(), true, "String", 1, 2, 3, 4, 5));
        Application application = new Application();
        application.addApplicationReview(new ApplicationReview(new User(), true, "String", 1, 2, 3, 4, 5));
        List<ApplicationReview> result = application.getReviews();
        assertEquals(expected, result);
    }

    @Test
    public void testSetAccepted() {
        String expected = "accepted";
        Application application = new Application();
        application.setAccepted();
        String result = application.getStatus();
        assertEquals(expected, result);
    }

    @Test
    public void testSetRejected() {
        String expected = "rejected";
        Application application = new Application();
        application.setRejected();
        String result = application.getStatus();
        assertEquals(expected, result);
    }

    @Test
    public void testToString() {
        String expected = "Company Trade Name: Amazon | Description: nice | VAT Number: 123456789";
        Application application = new Application();
        application.setCompanyTradeName("Amazon");
        application.setDescription("nice");
        application.setVatNumber(123456789);
        String result = application.toString();
        assertEquals(expected, result);
    }

    @Test
    public void ensureSameObjectIsEqual() {
        Application expected = new Application();
        assertEquals(expected, expected);
    }

    @Test
    public void ensureDifferentObjectsAreNotEqual() {
        Application expected = new Application();
        Object result = new Object();
        assertNotEquals(expected, result);
    }

    @Test
    public void ensureHashCodeIsCorrect() {
        int expected = -1421576819;
        Application app = TEST_APPLICATION_1;
        int result = app.hashCode();
        assertEquals(expected, result);
    }

}