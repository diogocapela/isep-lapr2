package lapr.project.utils;

import lapr.project.model.*;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public final class XMLImportAdapter {

    // Variables to comply with SonarQube string duplication...
    private static final String SONAR_QUBE_TITLE = "title";
    private static final String SONAR_QUBE_DESCRIPTION = "description";

    // Application
    private static final String APPLICATION_COMPANY_TRADE_NAME = "companyTradeName";
    private static final String APPLICATION_VAT_NUMBER = "vatNumber";
    private static final String APPLICATION_DESCRIPTION = "description";
    private static final String APPLICATION_PHONE_NUMBER = "phoneNumber";
    private static final String APPLICATION_NUMBER_OF_INVITATIONS = "invitesQuantity";
    private static final String APPLICATION_INTENDED_STAND_AREA = "boothArea";
    private static final String APPLICATION_DISPLAY_PRODUCTS = "displayProducts";
    private static final String APPLICATION_KEYWORDS = "topics";
    private static final String APPLICATION_STATUS = "accepted";
    private static final String APPLICATION_ASSIGNED_STAND = "assignedStand";
    private static final String APPLICATION_REVIEWS = "reviews";
    private static final String APPLICATION_WORKSHOPS = "workshops";

    // ApplicationReview
    private static final String APPLICATION_REVIEW_IS_ACCEPTED = "decision";
    private static final String APPLICATION_REVIEW_JUSTIFICATION = "text";
    private static final String APPLICATION_REVIEW_STAFF_TOPIC_KNOWLEDGE_RATING = "staffTopicKnowledge";
    private static final String APPLICATION_REVIEW_EVENT_ADEQUACY_RATING = "eventAdequacy";
    private static final String APPLICATION_REVIEW_INVITE_ADEQUACY_RATING = "inviteAdequacy";
    private static final String APPLICATION_REVIEW_REQUESTED_STAND_AREA_RATING = "requestedStandAreaRating";
    private static final String APPLICATION_REVIEW_OVERALL_RECOMMENDATION_RATING = "recommendation";

    // DisplayProduct
    private static final String DISPLAY_PRODUCT_NAME = "name";

    // Equipment
    private static final String EQUIPMENT_TITLE = SONAR_QUBE_TITLE;

    // Event
    private static final String EVENT_ROOT = "event";
    private static final String EVENT_ID = "id";
    private static final String EVENT_TITLE = SONAR_QUBE_TITLE;
    private static final String EVENT_DESCRIPTION = "eventDescription";
    private static final String EVENT_LOCATION = "location";
    private static final String EVENT_DATE = "date";
    private static final String EVENT_DEADLINE = "deadline";
    private static final String EVENT_IS_OPEN_TO_APPLICATIONS = "isOpenToApplications";
    private static final String EVENT_ORGANISERS = "organisers";
    private static final String EVENT_STAFF_MEMBERS = "StaffSet";
    private static final String EVENT_ATTENDEES = "attendees";
    private static final String EVENT_STAND_LIST = "stands";
    private static final String EVENT_APPLICATION_LIST = "applicationSet";

    // Keyword
    private static final String KEYWORD_VALUE = "topic";

    // Stand
    private static final String STAND_ROOT = "stand";
    private static final String STAND_DESCRIPTION = SONAR_QUBE_DESCRIPTION;
    private static final String STAND_AREA = "area";
    private static final String STAND_RELATIVE_DISTANCE_SET = "relativeDistanceSet";

    // StandDistance
    private static final String STAND_DISTANCE_ROOT = "distance";
    private static final String STAND_DISTANCE_DESCRIPTION = SONAR_QUBE_DESCRIPTION;
    private static final String STAND_DISTANCE_VALUE = "value";

    // User
    private static final String USER_USERNAME = "username";
    private static final String USER_EMAIL = "email";
    private static final String USER_PASSWORD = "password";
    private static final String USER_NAME = "name";
    private static final String USER_IS_ADMIN = "isAdmin";

    // Workshop
    private static final String WORKSHOP_TITLE = SONAR_QUBE_TITLE;
    private static final String WORKSHOP_DESCRIPTION = SONAR_QUBE_DESCRIPTION;
    private static final String WORKSHOP_ROOM = "room";
    private static final String WORKSHOP_DURATION = "duration";
    private static final String WORKSHOP_NECESSARY_EQUIPMENT = "necessaryEquipment";
    private static final String WORKSHOP_INTERESTED_USERS = "interestedUsers";

    private XMLImportAdapter() {
        throw new IllegalStateException("Utility class!");
    }

    public static Application importApplication(Node node) throws ParserConfigurationException {
        Application application = new Application();
        // create a new document
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.newDocument();
        document.appendChild(document.importNode(node, true));
        // get companyTradeName (String)
        Node companyTradeNameNode = document.getElementsByTagName(APPLICATION_COMPANY_TRADE_NAME).item(0);
        if (companyTradeNameNode != null) {
            application.setCompanyTradeName(companyTradeNameNode.getTextContent());
        }
        // get vatNumber (int)
        Node vatNumberNode = document.getElementsByTagName(APPLICATION_VAT_NUMBER).item(0);
        if (vatNumberNode != null) {
            application.setVatNumber(Integer.parseInt(vatNumberNode.getTextContent()));
        }
        // get description (String)
        Node descriptionNode = document.getElementsByTagName(APPLICATION_DESCRIPTION).item(0);
        if (descriptionNode != null) {
            application.setDescription(descriptionNode.getTextContent());
        }
        // get phoneNumber (int)
        Node phoneNumberNode = document.getElementsByTagName(APPLICATION_PHONE_NUMBER).item(0);
        if (phoneNumberNode != null) {
            application.setPhoneNumber(Integer.parseInt(phoneNumberNode.getTextContent()));
        }
        // get numberOfInvitations (int)
        Node numberOfInvitationsNode = document.getElementsByTagName(APPLICATION_NUMBER_OF_INVITATIONS).item(0);
        if (numberOfInvitationsNode != null) {
            application.setNumberOfInvitations(Integer.parseInt(numberOfInvitationsNode.getTextContent()));
        }
        // get intendedStand (Stand)
        Node intendedStandNode = document.getElementsByTagName(APPLICATION_INTENDED_STAND_AREA).item(0);
        if (intendedStandNode != null) {
            application.setIntendedStandArea(Double.parseDouble(intendedStandNode.getTextContent()));
        }
        // get displayProducts (List<DisplayProduct>)
        Node displayProductsMegaNode = document.getElementsByTagName(APPLICATION_DISPLAY_PRODUCTS).item(0);
        if (displayProductsMegaNode != null) {
            NodeList displayProductsNodeList = displayProductsMegaNode.getChildNodes();
            for (int i = 0; i < displayProductsNodeList.getLength(); i++) {
                Node displayProductNode = displayProductsNodeList.item(i);
                try {
                    application.addDisplayProduct(importDisplayProduct(displayProductNode));
                } catch (Exception ex) {
                    continue;
                }
            }
        }
        // get keywords (List<Keyword>)
        Node keywordsMegaNode = document.getElementsByTagName(APPLICATION_KEYWORDS).item(0);
        if (keywordsMegaNode != null) {
            NodeList keywordsNodeList = keywordsMegaNode.getChildNodes();
            for (int i = 0; i < keywordsNodeList.getLength(); i++) {
                Node keywordNode = keywordsNodeList.item(i);
                try {
                    application.addKeyword(importKeyword(keywordNode));
                } catch (Exception ex) {
                    continue;
                }
            }
        }
        // get status (String)
        Node statusNode = document.getElementsByTagName(APPLICATION_STATUS).item(0);
        if (statusNode != null) {
            String statusFromFile = statusNode.getTextContent();
            if ("true".equals(statusFromFile)) {
                application.setStatus("accepted");
            } else {
                application.setStatus("rejected");
            }
        }
        // get assignedStand (Stand)
        Node assignedStandNode = document.getElementsByTagName(APPLICATION_ASSIGNED_STAND).item(0);
        if (assignedStandNode != null) {
            application.setAssignedStand(importStand(assignedStandNode));
        }
        // get reviews (List<ApplicationReview>)
        Node reviewsMegaNode = document.getElementsByTagName(APPLICATION_REVIEWS).item(0);
        if (reviewsMegaNode != null) {
            NodeList reviewsNodeList = reviewsMegaNode.getChildNodes();
            for (int i = 0; i < reviewsNodeList.getLength(); i++) {
                Node applicationReviewNode = reviewsNodeList.item(i);
                try {
                    application.addApplicationReview(importApplicationReview(applicationReviewNode));
                } catch (Exception ex) {
                    continue;
                }
            }
        }
        // get workshops (List<Workshop>)
        Node workshopsMegaNode = document.getElementsByTagName(APPLICATION_WORKSHOPS).item(0);
        if (workshopsMegaNode != null) {
            NodeList workshopNodeList = workshopsMegaNode.getChildNodes();
            for (int i = 0; i < workshopNodeList.getLength(); i++) {
                Node workshopNode = workshopNodeList.item(i);
                try {
                    application.addWorkshop(importWorkshop(workshopNode));
                } catch (Exception ex) {
                    continue;
                }
            }
        }
        return application;
    }

    public static ApplicationReview importApplicationReview(Node node) throws ParserConfigurationException {
        ApplicationReview applicationReview = new ApplicationReview();
        // create a new document
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.newDocument();
        document.appendChild(document.importNode(node, true));
        // user (User) - <assignment><staff>
        Node staffNode = document.getElementsByTagName("staff").item(0);
        if (staffNode != null) {
            applicationReview.setUser(importUser(staffNode));
        }
        // get isAccepted (boolean) - <decision>
        Node isAcceptedNode = document.getElementsByTagName(APPLICATION_REVIEW_IS_ACCEPTED).item(0);
        if (isAcceptedNode != null) {
            String content = isAcceptedNode.getTextContent();
            if ("accepted".equals(content)) {
                applicationReview.setIsAccepted(true);
            }
        }
        // get justification (String) - <text>
        Node justificationNode = document.getElementsByTagName(APPLICATION_REVIEW_JUSTIFICATION).item(0);
        if (justificationNode != null) {
            applicationReview.setJustification(justificationNode.getTextContent());
        }
        // get staffTopicKnowledgeRating (int) - <staffTopicKnowledge>
        Node staffTopicKnowledgeRatingNode = document.getElementsByTagName(APPLICATION_REVIEW_STAFF_TOPIC_KNOWLEDGE_RATING).item(0);
        if (staffTopicKnowledgeRatingNode != null) {
            applicationReview.setStaffTopicKnowledgeRating(Integer.parseInt(staffTopicKnowledgeRatingNode.getTextContent()));
        }
        // get eventAdequacyRating (int) - <eventAdequacy>
        Node eventAdequacyRatingNode = document.getElementsByTagName(APPLICATION_REVIEW_EVENT_ADEQUACY_RATING).item(0);
        if (eventAdequacyRatingNode != null) {
            applicationReview.setEventAdequacyRating(Integer.parseInt(eventAdequacyRatingNode.getTextContent()));
        }
        // get inviteAdequacyRating (int) - <inviteAdequacy>
        Node inviteAdequacyRatingNode = document.getElementsByTagName(APPLICATION_REVIEW_INVITE_ADEQUACY_RATING).item(0);
        if (inviteAdequacyRatingNode != null) {
            applicationReview.setInviteAdequacyRating(Integer.parseInt(inviteAdequacyRatingNode.getTextContent()));
        }
        // get requestedStandAreaRating (int) - <requestedStandAreaRating>
        Node requestedStandAreaRatingNode = document.getElementsByTagName(APPLICATION_REVIEW_REQUESTED_STAND_AREA_RATING).item(0);
        if (requestedStandAreaRatingNode != null) {
            applicationReview.setRequestedStandAreaRating(Integer.parseInt(requestedStandAreaRatingNode.getTextContent()));
        }
        // get overallRecommendationRating (int) - <recommendation>
        Node overallRecommendationRatingNode = document.getElementsByTagName(APPLICATION_REVIEW_OVERALL_RECOMMENDATION_RATING).item(0);
        if (overallRecommendationRatingNode != null) {
            applicationReview.setOverallRecommendationRating(Integer.parseInt(overallRecommendationRatingNode.getTextContent()));
        }
        return applicationReview;
    }

    public static Date importDate(Node node) throws ParserConfigurationException {
        Date date = new Date();
        // create document builder
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.newDocument();
        document.appendChild(document.importNode(node, true));
        // get name
        Node dateNode = document.getElementsByTagName(EVENT_DATE).item(0);
        if (dateNode != null) {
            date = new Date(dateNode.getTextContent());
        }
        return date;
    }

    public static DisplayProduct importDisplayProduct(Node node) throws ParserConfigurationException {
        DisplayProduct displayProduct = new DisplayProduct();
        // create document builder
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.newDocument();
        document.appendChild(document.importNode(node, true));
        // get name
        Node nameNode = document.getElementsByTagName(DISPLAY_PRODUCT_NAME).item(0);
        if (nameNode != null) {
            displayProduct.setName(nameNode.getTextContent());
        }
        return displayProduct;
    }

    public static Equipment importEquipment(Node node) throws ParserConfigurationException {
        Equipment equipment = new Equipment();
        // create document builder
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.newDocument();
        document.appendChild(document.importNode(node, true));
        // get title
        NodeList titleNodeList = document.getElementsByTagName(EQUIPMENT_TITLE);
        Node titleNode = titleNodeList.item(0);
        if (titleNode != null) {
            equipment.setTitle(titleNode.getTextContent());
        }
        return equipment;
    }

    public static Event importEvent(Node node) throws ParserConfigurationException {
        Event event = new Event();
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.newDocument();
        document.appendChild(document.importNode(node, true));
        // root element (Event)
        NodeList rootNodeList = document.getElementsByTagName(EVENT_ROOT);
        Element root = (Element) rootNodeList.item(0);
        // id (int)
        Node idNode = root.getElementsByTagName(EVENT_ID).item(0);
        if (idNode != null) {
            event.setId(Integer.parseInt(idNode.getTextContent()));
        }
        // title (String)
        Node titleNode = root.getElementsByTagName(EVENT_TITLE).item(0);
        if (titleNode != null) {
            event.setTitle(titleNode.getTextContent());
        }
        // description (String)
        Node descriptionNode = root.getElementsByTagName(EVENT_DESCRIPTION).item(0);
        if (descriptionNode != null) {
            event.setDescription(descriptionNode.getTextContent());
        }
        // location (String)
        Node locationNode = root.getElementsByTagName(EVENT_LOCATION).item(0);
        if (locationNode != null) {
            event.setLocation(locationNode.getTextContent());
        }
        // date (String)
        Node dateNode = root.getElementsByTagName(EVENT_DATE).item(0);
        if (dateNode != null) {
            event.setDate(dateNode.getTextContent());
        }
        // deadline (String)
        Node deadlineNode = root.getElementsByTagName(EVENT_DEADLINE).item(0);
        if (deadlineNode != null) {
            event.setDeadline(deadlineNode.getTextContent());
        }
        // isOpenToApplications (boolean)
        Node isOpenToApplicationsNode = root.getElementsByTagName(EVENT_IS_OPEN_TO_APPLICATIONS).item(0);
        if (isOpenToApplicationsNode != null) {
            event.setIsOpenToApplications(Boolean.parseBoolean(isOpenToApplicationsNode.getTextContent()));
        }
        // organisers (List<User>)
        Node organisersMegaNode = root.getElementsByTagName(EVENT_ORGANISERS).item(0);
        if (organisersMegaNode != null) {
            NodeList organisersNodeList = organisersMegaNode.getChildNodes();
            for (int i = 0; i < organisersNodeList.getLength(); i++) {
                Node userNode = organisersNodeList.item(i);
                if (userNode != null) {
                    event.addOrganiser(importUser(userNode));
                }
            }
        } else {
            // add event manager as an organiser if there is no organisers
            AuthManager authManager = new AuthManager();
            event.addOrganiser(authManager.getLoggedInUser());
        }
        // staffMembers (List<User>)
        Node staffMembersMegaNode = root.getElementsByTagName(EVENT_STAFF_MEMBERS).item(0);
        if (staffMembersMegaNode != null) {
            NodeList staffMembersNodeList = staffMembersMegaNode.getChildNodes();
            for (int i = 0; i < staffMembersNodeList.getLength(); i++) {
                try {
                    Element staffPseudoElement = (Element) staffMembersNodeList.item(i);
                    Node userNode = staffPseudoElement.getElementsByTagName("user").item(0);
                    if (userNode != null) {
                        event.addStaffMember(importUser(userNode));
                    }
                } catch (Exception ex) {
                    continue;
                }
            }
        }
        // attendees (List<User>)
        Node attendeesMegaNode = root.getElementsByTagName(EVENT_ATTENDEES).item(0);
        if (attendeesMegaNode != null) {
            NodeList attendeesNodeList = attendeesMegaNode.getChildNodes();
            for (int i = 0; i < attendeesNodeList.getLength(); i++) {
                Node userNode = attendeesNodeList.item(i);
                if (userNode != null) {
                    event.addAttendee(importUser(userNode));
                }
            }
        }
        // standList (List<Stand>)
        Node standListMegaNode = root.getElementsByTagName(EVENT_STAND_LIST).item(0);
        if (standListMegaNode != null) {
            NodeList standListNodeList = standListMegaNode.getChildNodes();
            for (int i = 0; i < standListNodeList.getLength(); i++) {
                try {
                    Node standNode = standListNodeList.item(i);
                    if (standNode != null) {
                        event.addStand(importStand(standNode));
                    }
                } catch (Exception ex) {
                    continue;
                }
            }
        }
        // applicationList (List<Application>)
        Node applicationListMegaNode = root.getElementsByTagName(EVENT_APPLICATION_LIST).item(0);
        if (applicationListMegaNode != null) {
            NodeList applicationListNodeList = applicationListMegaNode.getChildNodes();
            for (int i = 0; i < applicationListNodeList.getLength(); i++) {
                Node applicationNode = applicationListNodeList.item(i);
                try {
                    if (applicationNode != null) {
                        event.addApplication(importApplication(applicationNode));
                    }
                } catch (Exception ex) {
                    continue;
                }
            }
        }
        return event;
    }

    public static Keyword importKeyword(Node node) throws ParserConfigurationException {
        Keyword keyword = new Keyword();
        // create a new document
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.newDocument();
        document.appendChild(document.importNode(node, true));
        // get value (String)
        Node valueNode = document.getElementsByTagName(KEYWORD_VALUE).item(0);
        if (valueNode != null) {
            keyword.setValue(valueNode.getTextContent());
        }
        return keyword;
    }

    public static Stand importStand(Node node) throws ParserConfigurationException {
        Stand stand = new Stand();
        // create a new document
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.newDocument();
        document.appendChild(document.importNode(node, true));
        // root element (Stand)
        NodeList standNodeList = document.getElementsByTagName(STAND_ROOT);
        Element elementStand = (Element) standNodeList.item(0);
        // description (String)
        Node nodeDescription = elementStand.getElementsByTagName(STAND_DESCRIPTION).item(0);
        if (nodeDescription != null) {
            stand.setDescription(nodeDescription.getTextContent());
        }
        // area (int)
        Node nodeArea = elementStand.getElementsByTagName(STAND_AREA).item(0);
        if (nodeArea != null) {
            stand.setArea(Double.parseDouble(nodeArea.getTextContent()));
        }
        // relativeDistanceSet (List<Distance>)
        Node nodeDistanceList = elementStand.getElementsByTagName(STAND_RELATIVE_DISTANCE_SET).item(0);
        if (nodeDistanceList != null) {
            for (int i = 0; i < nodeDistanceList.getChildNodes().getLength(); i++) {
                Node nodeDistance = nodeDistanceList.getChildNodes().item(i);
                try {
                    if (nodeDistance != null) {
                        stand.addRelativeDistance(importStandDistance(nodeDistance));
                    }
                } catch (Exception ex) {
                    continue;
                }
            }
        }
        return stand;
    }

    public static StandDistance importStandDistance(Node node) throws ParserConfigurationException {
        StandDistance standDistance = new StandDistance();
        // create a new document
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.newDocument();
        document.appendChild(document.importNode(node, true));
        // root element (Distance)
        NodeList distanceNodeList = document.getElementsByTagName(STAND_DISTANCE_ROOT);
        Element elementDistance = (Element) distanceNodeList.item(0);
        // description (String)
        Node descriptionNode = elementDistance.getElementsByTagName(STAND_DISTANCE_DESCRIPTION).item(0);
        if (descriptionNode != null) {
            standDistance.setDistanceDescription(descriptionNode.getTextContent());
        }
        // value (String)
        Node valueNode = elementDistance.getElementsByTagName(STAND_DISTANCE_VALUE).item(0);
        if (valueNode != null) {
            standDistance.setDistanceValue(Integer.parseInt(valueNode.getTextContent()));
        }
        return standDistance;
    }

    public static User importUser(Node node) throws ParserConfigurationException {
        User user = new User();
        // create a new document
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.newDocument();
        document.appendChild(document.importNode(node, true));
        // get username (String)
        Node usernameNode = document.getElementsByTagName(USER_USERNAME).item(0);
        if (usernameNode != null) {
            user.setUsername(usernameNode.getTextContent());
        }
        // get email (String)
        Node emailNode = document.getElementsByTagName(USER_EMAIL).item(0);
        if (emailNode != null) {
            user.setEmail(emailNode.getTextContent());
        }
        // get password (String)
        Node passwordNode = document.getElementsByTagName(USER_PASSWORD).item(0);
        if (passwordNode != null) {
            user.setPassword(passwordNode.getTextContent());
        }
        // get name (String)
        Node nameNode = document.getElementsByTagName(USER_NAME).item(0);
        if (nameNode != null) {
            user.setName(nameNode.getTextContent());
        }
        // get isAdmin (boolean)
        Node isAdminNode = document.getElementsByTagName(USER_IS_ADMIN).item(0);
        if (isAdminNode != null) {
            user.setIsAdmin(Boolean.parseBoolean(isAdminNode.getTextContent()));
        }
        return user;
    }

    public static Workshop importWorkshop(Node node) throws ParserConfigurationException {
        Workshop workshop = new Workshop();
        // create a new document
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.newDocument();
        document.appendChild(document.importNode(node, true));
        // get title (String)
        Node titleNode = document.getElementsByTagName(WORKSHOP_TITLE).item(0);
        if (titleNode != null) {
            workshop.setWorkshopTitle(titleNode.getTextContent());
        }
        // get description (String)
        Node descriptionNode = document.getElementsByTagName(WORKSHOP_DESCRIPTION).item(0);
        if (descriptionNode != null) {
            workshop.setWorkshopDescription(descriptionNode.getTextContent());
        }
        // get room (int)
        Node roomNode = document.getElementsByTagName(WORKSHOP_ROOM).item(0);
        if (roomNode != null) {
            workshop.setRoom(Integer.parseInt(roomNode.getTextContent()));
        }
        // get duration (int)
        Node durationNode = document.getElementsByTagName(WORKSHOP_DURATION).item(0);
        if (durationNode != null) {
            workshop.setDuration(Integer.parseInt(durationNode.getTextContent()));
        }
        // get necessaryEquipment (List<String>)
        Node necessaryEquipmentNode = document.getElementsByTagName(WORKSHOP_NECESSARY_EQUIPMENT).item(0);
        if (necessaryEquipmentNode != null) {
            String[] necessaryEquipmentStringArray = necessaryEquipmentNode.getTextContent().split(",");
            for (String equipment : necessaryEquipmentStringArray) {
                workshop.addNecessaryEquipment(equipment);
            }
        }
        // get interestedUsers (List<User>)
        Node interestedUsersMegaNode = document.getElementsByTagName(WORKSHOP_INTERESTED_USERS).item(0);
        if (interestedUsersMegaNode != null) {
            NodeList interestedUsersNodeList = interestedUsersMegaNode.getChildNodes();
            for (int i = 0; i < interestedUsersNodeList.getLength(); i++) {
                Node userNode = interestedUsersNodeList.item(i);
                try {
                    workshop.addInterestedUser(importUser(userNode));
                } catch (Exception ex) {
                    continue;
                }
            }
        }
        return workshop;
    }

}
