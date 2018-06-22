package lapr.project.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

/**
 * Testing the ApplicationReview class.
 * <p>
 * Created by Diogo Capela (1171316@isep.ipp.pt) on 02/06/2018.
 */
public class ApplicationReviewTest {

    private User TEST_USER = new User("diogocapela", "diogocapela@gmail.com", "password123", "Diogo Capela");
    private ApplicationReview TEST_APPLICATION_REVIEW = new ApplicationReview(TEST_USER, true, "This application is amazing!", 1, 2, 3, 4, 5);

    @Test
    public void testGetUser() {
        User expected = TEST_USER;
        User result = TEST_APPLICATION_REVIEW.getUser();
        assertEquals(expected, result);
    }

    @Test
    public void testGetIsAccepted() {
        boolean expected = true;
        boolean result = TEST_APPLICATION_REVIEW.getIsAccepted();
        assertEquals(expected, result);
    }

    @Test
    public void testGetJustification() {
        String expected = "This application is amazing!";
        String result = TEST_APPLICATION_REVIEW.getJustification();
        assertEquals(expected, result);
    }

    @Test
    public void testGetStaffTopicKnowledgeRating() {
        int expected = 1;
        int result = TEST_APPLICATION_REVIEW.getStaffTopicKnowledgeRating();
        assertEquals(expected, result);
    }

    @Test
    public void testGetEventAdequacyRating() {
        int expected = 2;
        int result = TEST_APPLICATION_REVIEW.getEventAdequacyRating();
        assertEquals(expected, result);
    }

    @Test
    public void testGetInviteAdequacyRating() {
        int expected = 3;
        int result = TEST_APPLICATION_REVIEW.getInviteAdequacyRating();
        assertEquals(expected, result);
    }

    @Test
    public void testGetRequestedStandAreaRating() {
        int expected = 4;
        int result = TEST_APPLICATION_REVIEW.getRequestedStandAreaRating();
        assertEquals(expected, result);
    }

    @Test
    public void testGetOverallRecommendationRating() {
        int expected = 5;
        int result = TEST_APPLICATION_REVIEW.getOverallRecommendationRating();
        assertEquals(expected, result);
    }

    @Test
    public void testSetUser() {
        User expected = new User("johndoe", "johndoe@gmail.com", "passwd123", "John Doe");
        TEST_APPLICATION_REVIEW.setUser(expected);
        User result = TEST_APPLICATION_REVIEW.getUser();
        assertEquals(expected, result);
    }

    @Test
    public void testSetIsAccepted() {
        boolean expected = false;
        TEST_APPLICATION_REVIEW.setIsAccepted(false);
        boolean result = TEST_APPLICATION_REVIEW.getIsAccepted();
        assertEquals(expected, result);
    }

    @Test
    public void testSetJustification() {
        String expected = "This application is bad!";
        TEST_APPLICATION_REVIEW.setJustification(expected);
        String result = TEST_APPLICATION_REVIEW.getJustification();
        assertEquals(expected, result);
    }

    @Test
    public void testSetStaffTopicKnowledgeRating() {
        int expected = 1;
        TEST_APPLICATION_REVIEW.setStaffTopicKnowledgeRating(expected);
        int result = TEST_APPLICATION_REVIEW.getStaffTopicKnowledgeRating();
        assertEquals(expected, result);
    }

    @Test
    public void testSetEventAdequacyRating() {
        int expected = 5;
        TEST_APPLICATION_REVIEW.setEventAdequacyRating(expected);
        int result = TEST_APPLICATION_REVIEW.getEventAdequacyRating();
        assertEquals(expected, result);
    }

    @Test
    public void testSetInviteAdequacyRating() {
        int expected = 5;
        TEST_APPLICATION_REVIEW.setInviteAdequacyRating(expected);
        int result = TEST_APPLICATION_REVIEW.getInviteAdequacyRating();
        assertEquals(expected, result);
    }

    @Test
    public void testSetRequestedStandAreaRating() {
        int expected = 5;
        TEST_APPLICATION_REVIEW.setRequestedStandAreaRating(expected);
        int result = TEST_APPLICATION_REVIEW.getRequestedStandAreaRating();
        assertEquals(expected, result);
    }

    @Test
    public void testSetOverallRecommendationRating() {
        int expected = 5;
        TEST_APPLICATION_REVIEW.setOverallRecommendationRating(expected);
        int result = TEST_APPLICATION_REVIEW.getOverallRecommendationRating();
        assertEquals(expected, result);
    }

    @Test
    public void testToString() {
        String expected = "Staff Member: diogocapela | Accepted: true | Justification: This application is amazing! | Ratings: 1/2/3/4/5";
        String result = TEST_APPLICATION_REVIEW.toString();
        assertEquals(expected, result);
    }

    @Test
    public void testHashCode() {
        int expected = 179993910;
        ApplicationReview ar = TEST_APPLICATION_REVIEW;
        int result = ar.hashCode();
        assertEquals(expected, result);
    }

    @Test
    public void ensureSameObjectIsEqual() {
        ApplicationReview expected = TEST_APPLICATION_REVIEW;
        assertEquals(expected, expected);
    }

    @Test
    public void ensureDifferentObjectsAreNotEqual() {
        ApplicationReview expected = TEST_APPLICATION_REVIEW;
        Object result = new Object();
        assertNotEquals(expected, result);
    }

}