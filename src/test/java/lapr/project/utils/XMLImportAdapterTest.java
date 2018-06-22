package lapr.project.utils;

import lapr.project.model.*;
import org.junit.jupiter.api.Test;
import org.w3c.dom.Node;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Tests for the XMLImportAdapter class.
 * <p>
 * Created by Diogo Capela [1171316@isep.ipp.pt].
 * Improved by Vítor Hugo Silva (1140825@isep.ipp.pt)
 */
public class XMLImportAdapterTest {

    User TEST_USER_0 = new User();
    User TEST_USER_1 = new User("rafa", "rpm@gmail.com", ".0000000", "Rafa Marq");
    User TEST_USER_2 = new User("capeladiogo", "capeladiogo@gmail.com", "0.0737021364", "Diogo Capela");
    User TEST_USER_3 = new User("filipe", "fmc@gmail.com", ".0000000", "fil mee");
    User TEST_USER_4 = new User("vitor", "vse@gmail.com", ".0000000", "ve vi vi");

    Date TEST_DATE_0 = new Date();
    Date TEST_DATE_1 = new Date("2018/5/5");
    Date TEST_DATE_2 = new Date("2018/6/6");

    DisplayProduct TEST_DISPLAY_PRODUCT_0 = new DisplayProduct();
    DisplayProduct TEST_DISPLAY_PRODUCT_1 = new DisplayProduct("dproduct1");

    Equipment TEST_EQUIPMENT_0 = new Equipment();
    Equipment TEST_EQUIPMENT_1 = new Equipment("Water");

    ApplicationReview TEST_APPLICATION_REVIEW_0 = new ApplicationReview();
    ApplicationReview TEST_APPLICATION_REVIEW_1 = new ApplicationReview();
    ApplicationReview TEST_APPLICATION_REVIEW_2 = new ApplicationReview();
    ApplicationReview TEST_APPLICATION_REVIEW_3 = new ApplicationReview();

    Workshop TEST_WORKSHOP_0 = new Workshop();
    Workshop TEST_WORKSHOP_1 = new Workshop("workshop1", "workshop desc", 10);
    Workshop TEST_WORKSHOP_2 = new Workshop("workshop2", "workshop desc 2", 20);

    Keyword TEST_KEYWORD_0 = new Keyword();
    Keyword TEST_KEYWORD_1 = new Keyword("Banana");

    Application TEST_APPLICATION_0 = new Application();
    Application TEST_APPLICATION_1 = new Application();
    Application TEST_APPLICATION_2 = new Application();

    StandDistance TEST_STAND_DISTANCE_0 = new StandDistance();
    StandDistance TEST_STAND_DISTANCE_1 = new StandDistance("stand1", 50);

    Stand TEST_STAND_0 = new Stand();
    Stand TEST_STAND_1 = new Stand("stand1", 10);
    Stand TEST_STAND_2 = new Stand("stand2", 20);

    Event TEST_EVENT_0 = new Event();
    Event TEST_EVENT_1 = new Event();
    Event TEST_EVENT_2 = new Event();

    XMLParser xmlParser = new XMLParser();

    public XMLImportAdapterTest() {
        TEST_EVENT_1.setId(1);
        TEST_EVENT_1.setType("exhibition");
        TEST_EVENT_1.setTitle("exh1");
        TEST_EVENT_1.setDescription("desc1");
        TEST_EVENT_1.setLocation("local1");
        TEST_EVENT_1.setDate("2000/2/2");
        TEST_EVENT_1.setDeadline("1990/3/3");

        TEST_APPLICATION_REVIEW_1.setOverallRecommendationRating(0);
        TEST_APPLICATION_REVIEW_1.setRequestedStandAreaRating(0);
        TEST_APPLICATION_REVIEW_1.setInviteAdequacyRating(0);
        TEST_APPLICATION_REVIEW_1.setEventAdequacyRating(0);
        TEST_APPLICATION_REVIEW_1.setStaffTopicKnowledgeRating(0);
        TEST_APPLICATION_REVIEW_1.setJustification("bla bla");

        TEST_APPLICATION_REVIEW_2.setOverallRecommendationRating(5);
        TEST_APPLICATION_REVIEW_2.setRequestedStandAreaRating(5);
        TEST_APPLICATION_REVIEW_2.setInviteAdequacyRating(5);
        TEST_APPLICATION_REVIEW_2.setEventAdequacyRating(5);
        TEST_APPLICATION_REVIEW_2.setStaffTopicKnowledgeRating(5);
        TEST_APPLICATION_REVIEW_2.setUser(TEST_USER_2);
        TEST_APPLICATION_REVIEW_2.setJustification("I approve it.");
        TEST_APPLICATION_REVIEW_2.setIsAccepted(true);

        TEST_APPLICATION_REVIEW_3 = TEST_APPLICATION_REVIEW_2;
        TEST_APPLICATION_REVIEW_3.setIsAccepted(false);

        TEST_APPLICATION_1.setCompanyTradeName("app1");

        TEST_WORKSHOP_2.addInterestedUser(TEST_USER_2);
        TEST_WORKSHOP_2.addInterestedUser(TEST_USER_4);
        TEST_WORKSHOP_2.addNecessaryEquipment(TEST_EQUIPMENT_1.getTitle());

        TEST_APPLICATION_1.setCompanyTradeName("comp1");
        TEST_APPLICATION_1.setVatNumber(123123123);
        TEST_APPLICATION_1.setPhoneNumber(9351151);
        TEST_APPLICATION_1.setDescription("app1");
        TEST_APPLICATION_1.setIntendedStandArea(100);
        TEST_APPLICATION_1.setNumberOfInvitations(80);

        TEST_APPLICATION_2.setCompanyTradeName("comp2");
        TEST_APPLICATION_2.setVatNumber(123123123);
        TEST_APPLICATION_2.setPhoneNumber(9351151);
        TEST_APPLICATION_2.setDescription("app2");
        TEST_APPLICATION_2.setIntendedStandArea(90);
        TEST_APPLICATION_2.setNumberOfInvitations(150);
        TEST_APPLICATION_2.addApplicationReview(TEST_APPLICATION_REVIEW_2);
        TEST_APPLICATION_2.addWorkshop(TEST_WORKSHOP_2);
        TEST_APPLICATION_2.addKeyword(TEST_KEYWORD_1);
        TEST_APPLICATION_2.addDisplayProduct(TEST_DISPLAY_PRODUCT_1);
        TEST_APPLICATION_2.setAssignedStand(TEST_STAND_2);
        TEST_APPLICATION_2.setAccepted();

        TEST_STAND_2.addRelativeDistance(TEST_STAND_DISTANCE_1);

        TEST_EVENT_2.setId(2);
        TEST_EVENT_2.setType("exhibition");
        TEST_EVENT_2.setTitle("exh2");
        TEST_EVENT_2.setDescription("desc2");
        TEST_EVENT_2.setLocation("local2");
        TEST_EVENT_2.setDate("2000/4/4");
        TEST_EVENT_2.setDeadline("1990/5/5");
        TEST_EVENT_2.addOrganiser(TEST_USER_1);
        TEST_EVENT_2.addStaffMember(TEST_USER_2);
        TEST_EVENT_2.addAttendee(TEST_USER_3);
        TEST_EVENT_2.addApplication(TEST_APPLICATION_2);
    }

    @Test
    public void testImportApplicationEmpty() throws Exception {
        // Arrange
        Application expected = TEST_APPLICATION_0;
        // Act
        Node node = xmlParser.readXMLElementFromFile("./src/test/resources/XMLImportAdapter/ApplicationEmptyExample.xml");
        Application result = XMLImportAdapter.importApplication(node);
        // Assert
        assertEquals(expected, result);
    }

    @Test
    public void testImportApplicationNoReview() throws Exception {
        //
        Application expected = TEST_APPLICATION_1;
        expected.setRejected();
        // Act
        Node node = xmlParser.readXMLElementFromFile("./src/test/resources/XMLImportAdapter/ApplicationNoReviewExample.xml");
        Application result = XMLImportAdapter.importApplication(node);
        // Assert
        assertEquals(expected, result);
    }

    @Test
    public void testImportApplication() throws Exception {
        // Arrange
        Application expected = TEST_APPLICATION_2;
        expected.setRejected();
        // Act
        Node node = xmlParser.readXMLElementFromFile("./src/test/resources/XMLImportAdapter/ApplicationExample.xml");
        Application result = XMLImportAdapter.importApplication(node);
        // Assert
        assertEquals(expected, result);
    }

    @Test
    public void testImportApplicationReviewEmpty() throws Exception {
        // Arrange
        ApplicationReview expected = TEST_APPLICATION_REVIEW_0;
        // Act
        Node node = xmlParser.readXMLElementFromFile("./src/test/resources/XMLImportAdapter/ApplicationReviewEmptyExample.xml");
        ApplicationReview result = XMLImportAdapter.importApplicationReview(node);
        // Assert
        assertEquals(expected, result);
    }

    @Test
    public void testImportApplicationNoUserReview() throws Exception {
        // Arrange
        ApplicationReview expected = TEST_APPLICATION_REVIEW_1;
        // Act
        Node node = xmlParser.readXMLElementFromFile("./src/test/resources/XMLImportAdapter/ApplicationReviewNoUserExample.xml");
        ApplicationReview result = XMLImportAdapter.importApplicationReview(node);
        // Assert
        assertEquals(expected, result);
    }

    @Test
    public void testImportApplicationReviewRejected() throws Exception {
        // Arrange
        ApplicationReview expected = TEST_APPLICATION_REVIEW_3;
        // Act
        Node node = xmlParser.readXMLElementFromFile("./src/test/resources/XMLImportAdapter/ApplicationReviewRejectedExample.xml");
        ApplicationReview result = XMLImportAdapter.importApplicationReview(node);
        // Assert
        assertEquals(expected, result);
    }

    @Test
    public void testImportApplicationReview() throws Exception {
        // Arrange
        ApplicationReview expected = TEST_APPLICATION_REVIEW_2;
        // Act
        Node node = xmlParser.readXMLElementFromFile("./src/test/resources/XMLImportAdapter/ApplicationReviewExample.xml");
        ApplicationReview result = XMLImportAdapter.importApplicationReview(node);
        // Assert
        assertEquals(expected, result);
    }

    @Test
    public void testImportDisplayProductEmpty() throws Exception {
        // Arrange
        DisplayProduct expected = TEST_DISPLAY_PRODUCT_0;
        // Act
        Node node = xmlParser.readXMLElementFromFile("./src/test/resources/XMLImportAdapter/DisplayProductEmptyExample.xml");
        DisplayProduct result = XMLImportAdapter.importDisplayProduct(node);
        // Assert
        assertEquals(expected, result);
    }

    @Test
    public void testImportDisplayProduct() throws Exception {
        // Arrange
        DisplayProduct expected = TEST_DISPLAY_PRODUCT_1;
        // Act
        Node node = xmlParser.readXMLElementFromFile("./src/test/resources/XMLImportAdapter/DisplayProductExample.xml");
        DisplayProduct result = XMLImportAdapter.importDisplayProduct(node);
        // Assert
        assertEquals(expected, result);
    }

    @Test
    public void testImportEquipmentEmpty() throws Exception {
        // Arrange
        Equipment expected = TEST_EQUIPMENT_0;
        // Act
        Node node = xmlParser.readXMLElementFromFile("./src/test/resources/XMLImportAdapter/EquipmentEmptyExample.xml");
        Equipment result = XMLImportAdapter.importEquipment(node);
        // Assert
        assertEquals(expected, result);
    }

    @Test
    public void testImportEquipment() throws Exception {
        // Arrange
        Equipment expected = TEST_EQUIPMENT_1;
        // Act
        Node node = xmlParser.readXMLElementFromFile("./src/test/resources/XMLImportAdapter/EquipmentExample.xml");
        Equipment result = XMLImportAdapter.importEquipment(node);
        // Assert
        assertEquals(expected, result);
    }

    @Test
    public void testImportEventEmpty() throws Exception {
        // Arrange
        Event expected = TEST_EVENT_0;
        // Act
        Node node = xmlParser.readXMLElementFromFile("./src/test/resources/XMLImportAdapter/EventEmptyExample.xml");
        Event result = XMLImportAdapter.importEvent(node);
        // Assert
        assertEquals(expected, result);
    }

    @Test
    public void testImportEventNoLists() throws Exception {
        // Arrange
        Event expected = TEST_EVENT_1;
        // Act
        Node node = xmlParser.readXMLElementFromFile("./src/test/resources/XMLImportAdapter/EventAloneExample.xml");
        Event result = XMLImportAdapter.importEvent(node);
        // Assert
        assertEquals(expected, result);
    }

    @Test
    public void testImportEvent() throws Exception {
        // Arrange
        Event expected = TEST_EVENT_2;
        // Act
        Node node = xmlParser.readXMLElementFromFile("./src/test/resources/XMLImportAdapter/EventExample.xml");
        Event result = XMLImportAdapter.importEvent(node);
        // Assert
        assertEquals(expected, result);
    }

    @Test
    public void testImportKeywordEmpty() throws Exception {
        // Arrange
        Keyword expected = TEST_KEYWORD_0;
        // Act
        Node node = xmlParser.readXMLElementFromFile("./src/test/resources/XMLImportAdapter/KeywordEmptyExample.xml");
        Keyword result = XMLImportAdapter.importKeyword(node);
        // Assert
        assertEquals(expected, result);
    }

    @Test
    public void testImportKeyword() throws Exception {
        // Arrange
        Keyword expected = TEST_KEYWORD_1;
        // Act
        Node node = xmlParser.readXMLElementFromFile("./src/test/resources/XMLImportAdapter/KeywordExample.xml");
        Keyword result = XMLImportAdapter.importKeyword(node);
        // Assert
        assertEquals(expected, result);
    }

    @Test
    public void testImportStandEmpty() throws Exception {
        // Arrange
        Stand expected = TEST_STAND_0;
        // Act
        Node node = xmlParser.readXMLElementFromFile("./src/test/resources/XMLImportAdapter/StandEmptyExample.xml");
        Stand result = XMLImportAdapter.importStand(node);
        // Assert
        assertEquals(expected, result);
    }

    @Test
    public void testImportStandNoDistance() throws Exception {
        // Arrange
        Stand expected = TEST_STAND_1;
        // Act
        Node node = xmlParser.readXMLElementFromFile("./src/test/resources/XMLImportAdapter/StandAloneExample.xml");
        Stand result = XMLImportAdapter.importStand(node);
        // Assert
        assertEquals(expected, result);
    }

    @Test
    public void testImportStand() throws Exception {
        // Arrange
        Stand expected = TEST_STAND_2;
        // Act
        Node node = xmlParser.readXMLElementFromFile("./src/test/resources/XMLImportAdapter/StandExample.xml");
        Stand result = XMLImportAdapter.importStand(node);
        // Assert
        assertEquals(expected, result);
    }

    @Test
    public void testImportStandDistanceEmpty() throws Exception {
        // Arrange
        StandDistance expected = TEST_STAND_DISTANCE_0;
        // Act
        Node node = xmlParser.readXMLElementFromFile("./src/test/resources/XMLImportAdapter/StandDistanceEmptyExample.xml");
        StandDistance result = XMLImportAdapter.importStandDistance(node);
        // Assert
        assertEquals(expected, result);
    }

    @Test
    public void testImportStandDistance() throws Exception {
        // Arrange
        StandDistance expected = TEST_STAND_DISTANCE_1;
        // Act
        Node node = xmlParser.readXMLElementFromFile("./src/test/resources/XMLImportAdapter/StandDistanceExample.xml");
        StandDistance result = XMLImportAdapter.importStandDistance(node);
        // Assert
        assertEquals(expected, result);
    }

    @Test
    public void testImportUserEmpty() throws Exception {
        // Arrange
        User expected = TEST_USER_0;
        // Act
        Node node = xmlParser.readXMLElementFromFile("./src/test/resources/XMLImportAdapter/UserEmptyExample.xml");
        User result = XMLImportAdapter.importUser(node);
        // Assert
        assertEquals(expected, result);
    }

    @Test
    public void testImportUser() throws Exception {
        // Arrange
        User expected = TEST_USER_2;
        // Act
        Node node = xmlParser.readXMLElementFromFile("./src/test/resources/XMLImportAdapter/UserExample.xml");
        User result = XMLImportAdapter.importUser(node);
        // Assert
        assertEquals(expected, result);
    }

    @Test
    public void testImportWorkshopEmpty() throws Exception {
        // Arrange
        Workshop expected = TEST_WORKSHOP_0;
        // Act
        Node node = xmlParser.readXMLElementFromFile("./src/test/resources/XMLImportAdapter/WorkshopEmptyExample.xml");
        Workshop result = XMLImportAdapter.importWorkshop(node);
        // Assert
        assertEquals(expected, result);
    }

    @Test
    public void testImportWorkshopNoUserList() throws Exception {
        // Arrange
        Workshop expected = TEST_WORKSHOP_1;
        // Act
        Node node = xmlParser.readXMLElementFromFile("./src/test/resources/XMLImportAdapter/WorkshopNoUserListExample.xml");
        Workshop result = XMLImportAdapter.importWorkshop(node);
        // Assert
        assertEquals(expected, result);
    }

    @Test
    public void testImportWorkshop() throws Exception {
        // Arrange
        Workshop expected = TEST_WORKSHOP_2;
        // Act
        Node node = xmlParser.readXMLElementFromFile("./src/test/resources/XMLImportAdapter/WorkshopExample.xml");
        Workshop result = XMLImportAdapter.importWorkshop(node);
        // Assert
        assertEquals(expected, result);
    }

    @Test
    public void testImportDateEmpty() throws Exception {
        // Act
        Node node = xmlParser.readXMLElementFromFile("./src/test/resources/XMLImportAdapter/DateEmptyExample.xml");
        // Assert
        assertThrows(IllegalArgumentException.class, () -> {
            Date result = XMLImportAdapter.importDate(node);
        });
    }

    @Test
    public void testImportDate() throws Exception {
        // Arrange
        Date expected = TEST_DATE_1;
        // Act
        Node node = xmlParser.readXMLElementFromFile("./src/test/resources/XMLImportAdapter/DateExample.xml");
        Date result = XMLImportAdapter.importDate(node);
        // Assert
        assertEquals(expected, result);
    }
}
