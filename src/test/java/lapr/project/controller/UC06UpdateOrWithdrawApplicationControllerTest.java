package lapr.project.controller;


public class UC06UpdateOrWithdrawApplicationControllerTest {

    /*
    private UC06UpdateOrWithdrawApplicationController controller;

    private static User ADMIN_USER = new User("Filipe", "filipe@gmail.com", "batatoon1", "Filipe");
    private static User INVALID_USER = new User("Filipe", "filipe@gmail.com", "batatoon1", "Filipe");
    private static User EXPECTED_USER_1 = new User("Rafael", "rpm@gmail.com", "batatoon", "Rafa");
    private static Stand EXPECTED_STAND_1 = new Stand( "ab",1);
    private static List<Keyword> EXPECTED_KEYWORD_LIST = new ArrayList<>();
    private static List<DisplayProduct> EXPECTED_DISPLAY_PRODUCT_LIST = new ArrayList<>();
    private static List<ApplicationReview>EXPECTED_REVIEW_LIST = new ArrayList<>();
    private static Application EXPECTED_APPLICATION = new Application();

    public UC06UpdateOrWithdrawApplicationControllerTest() {
        EXPECTED_APPLICATION.setCompanyTradeName("Oracle");
        EXPECTED_APPLICATION.setVatNumber(123654789);
        EXPECTED_APPLICATION.setPhoneNumber(963258741);
        EXPECTED_APPLICATION.setNumberOfInvitations(20);
        EXPECTED_APPLICATION.setIntendedStandArea(20);
        EXPECTED_APPLICATION.setDisplayProducts(EXPECTED_DISPLAY_PRODUCT_LIST);
        EXPECTED_APPLICATION.setKeywords(EXPECTED_KEYWORD_LIST);
        EXPECTED_APPLICATION.setStatus("accepted");
        EXPECTED_APPLICATION.setAssignedStand(EXPECTED_STAND_1);
        EXPECTED_APPLICATION.setReviews(EXPECTED_REVIEW_LIST);
        EXPECTED_APPLICATION.setAuthor(EXPECTED_USER_1);
        EXPECTED_APPLICATION.setDescription("description");
        ADMIN_USER.setIsAdmin(true);
    }

    @Test
    public void updateApplicationTest() {

        User user = new User("Rafael", "rpm@gmail.com", "batatoon", "Rafa");
        Stand stand = new Stand( "ab",1);
        Keyword keyword = new Keyword("TEST_KEYWORD");
        List<Keyword> keywordList = new ArrayList<>();
        DisplayProduct displayProduct = new DisplayProduct("TEST_PRODUCT");
        List<DisplayProduct> displayProducts = new ArrayList<>();
        ApplicationReview applicationReview = new ApplicationReview();
        List<ApplicationReview> applicationReviews = new ArrayList<>();
        Application application = new Application();
        Event event = new Event(1, "NOT IT", "Everywhere", "Exposul", "2018/9/9", "2018/6/6");
        displayProducts.add(displayProduct);

        keywordList.add(keyword);

        applicationReview.setUser(user);
        applicationReview.setIsAccepted(false);
        applicationReview.setJustification("because");
        applicationReview.setStaffTopicKnowledgeRating(1);
        applicationReview.setEventAdequacyRating(2);
        applicationReview.setInviteAdequacyRating(3);
        applicationReview.setRequestedStandAreaRating(4);
        applicationReview.setOverallRecommendationRating(5);
        applicationReviews.add(applicationReview);

        application.setCompanyTradeName("Oracle");
        application.setVatNumber(123654789);
        application.setPhoneNumber(963258741);
        application.setNumberOfInvitations(20);
        application.setIntendedStandArea(20);
        application.setDisplayProducts(displayProducts);
        application.setKeywords(keywordList);
        application.setStatus("accepted");
        application.setAssignedStand(stand);
        application.setReviews(applicationReviews);
        application.setAuthor(user);
        application.setDescription("description");
        event.addApplication(application);

        controller = new UC06UpdateOrWithdrawApplicationController();

        Expo.getInstance().setLoggedInUser(EXPECTED_USER_1);
        controller.setLoggedInUser(EXPECTED_USER_1);
        controller.setEvent(event);
        //Arrange
        controller.setDescription(EXPECTED_APPLICATION.getDescription());
        controller.setCompanyTradeName(EXPECTED_APPLICATION.getCompanyTradeName());
        controller.setDisplayProducts(EXPECTED_APPLICATION.getDisplayProducts());
        controller.setIntendedStandArea(EXPECTED_APPLICATION.getIntendedStandArea());
        controller.setKeywords(EXPECTED_APPLICATION.getKeywords());
        controller.setNumberOfInvitations(EXPECTED_APPLICATION.getNumberOfInvitations());
        controller.setPhoneNumber(EXPECTED_APPLICATION.getPhoneNumber());
        controller.setVatNumber(EXPECTED_APPLICATION.getVatNumber());
        Assertions.assertNotNull(controller.getUsers());
        Assertions.assertNotNull(controller.getEvents());
        //Act
        assertTrue(controller.updateConfirm());
        //Assert
    }

    @Test
    public void updateApplicationWithAdminTest() {
        User user = new User("Rafael", "rpm@gmail.com", "batatoon", "Rafa");
        Stand stand = new Stand( "ab",1);
        Keyword keyword = new Keyword("TEST_KEYWORD");
        List<Keyword> keywordList = new ArrayList<>();
        DisplayProduct displayProduct = new DisplayProduct("TEST_PRODUCT");
        List<DisplayProduct> displayProducts = new ArrayList<>();
        ApplicationReview applicationReview = new ApplicationReview();
        List<ApplicationReview> applicationReviews = new ArrayList<>();
        Application application = new Application();
        Event event = new Event(1, "NOT IT", "Everywhere", "Exposul", "2018/9/9", "2018/6/6");
        displayProducts.add(displayProduct);

        keywordList.add(keyword);

        applicationReview.setUser(user);
        applicationReview.setIsAccepted(false);
        applicationReview.setJustification("because");
        applicationReview.setStaffTopicKnowledgeRating(1);
        applicationReview.setEventAdequacyRating(2);
        applicationReview.setInviteAdequacyRating(3);
        applicationReview.setRequestedStandAreaRating(4);
        applicationReview.setOverallRecommendationRating(5);
        applicationReviews.add(applicationReview);

        application.setCompanyTradeName("Oracle");
        application.setVatNumber(123654789);
        application.setPhoneNumber(963258741);
        application.setNumberOfInvitations(20);
        application.setIntendedStandArea(20);
        application.setDisplayProducts(displayProducts);
        application.setKeywords(keywordList);
        application.setStatus("accepted");
        application.setAssignedStand(stand);
        application.setReviews(applicationReviews);
        application.setAuthor(user);
        application.setDescription("description");
        event.addApplication(application);

        controller = new UC06UpdateOrWithdrawApplicationController();
        controller.setLoggedInUser(ADMIN_USER);
        controller.setEvent(event);
        //Arrange
        controller.setDescription(EXPECTED_APPLICATION.getDescription());
        controller.setCompanyTradeName(EXPECTED_APPLICATION.getCompanyTradeName());
        controller.setDisplayProducts(EXPECTED_APPLICATION.getDisplayProducts());
        controller.setIntendedStandArea(EXPECTED_APPLICATION.getIntendedStandArea());
        controller.setKeywords(EXPECTED_APPLICATION.getKeywords());
        controller.setNumberOfInvitations(EXPECTED_APPLICATION.getNumberOfInvitations());
        controller.setPhoneNumber(EXPECTED_APPLICATION.getPhoneNumber());
        controller.setVatNumber(EXPECTED_APPLICATION.getVatNumber());
        Assertions.assertNotNull(controller.getUsers());
        Assertions.assertNotNull(controller.getEvents());

        assertTrue(controller.updateConfirm());
    }

    @Test
    public void shouldNotUpdateApplicationWithInvalidUserTest() {
        controller = new UC06UpdateOrWithdrawApplicationController();
        User user = new User("Rafael", "rpm@gmail.com", "batatoon", "Rafa");
        Stand stand = new Stand( "ab",1);
        Keyword keyword = new Keyword("TEST_KEYWORD");
        List<Keyword> keywordList = new ArrayList<>();
        DisplayProduct displayProduct = new DisplayProduct("TEST_PRODUCT");
        List<DisplayProduct> displayProducts = new ArrayList<>();
        ApplicationReview applicationReview = new ApplicationReview();
        List<ApplicationReview> applicationReviews = new ArrayList<>();
        Application application = new Application();
        Event event = new Event(1, "NOT IT", "Everywhere", "Exposul", "2018/9/9", "2018/6/6");
        displayProducts.add(displayProduct);

        keywordList.add(keyword);

        applicationReview.setUser(user);
        applicationReview.setIsAccepted(false);
        applicationReview.setJustification("because");
        applicationReview.setStaffTopicKnowledgeRating(1);
        applicationReview.setEventAdequacyRating(2);
        applicationReview.setInviteAdequacyRating(3);
        applicationReview.setRequestedStandAreaRating(4);
        applicationReview.setOverallRecommendationRating(5);
        applicationReviews.add(applicationReview);

        application.setCompanyTradeName("Oracle");
        application.setVatNumber(123654789);
        application.setPhoneNumber(963258741);
        application.setNumberOfInvitations(20);
        application.setIntendedStandArea(20);
        application.setDisplayProducts(displayProducts);
        application.setKeywords(keywordList);
        application.setStatus("accepted");
        application.setAssignedStand(stand);
        application.setReviews(applicationReviews);
        application.setAuthor(user);
        application.setDescription("description");

        event.addApplication(application);

        controller.setLoggedInUser(INVALID_USER);
        controller.setEvent(event);
        //Arrange
        controller.setDescription(EXPECTED_APPLICATION.getDescription());
        controller.setCompanyTradeName(EXPECTED_APPLICATION.getCompanyTradeName());
        controller.setDisplayProducts(EXPECTED_APPLICATION.getDisplayProducts());
        controller.setIntendedStandArea(EXPECTED_APPLICATION.getIntendedStandArea());
        controller.setKeywords(EXPECTED_APPLICATION.getKeywords());
        controller.setNumberOfInvitations(EXPECTED_APPLICATION.getNumberOfInvitations());
        controller.setPhoneNumber(EXPECTED_APPLICATION.getPhoneNumber());
        controller.setVatNumber(EXPECTED_APPLICATION.getVatNumber());
        assertFalse(controller.updateConfirm());
    }

    @Test
    public void testControllerNullEvent() {
        controller = new UC06UpdateOrWithdrawApplicationController();
        controller.setEvent(null);
        controller.setLoggedInUser(EXPECTED_USER_1);
        controller.setCompanyTradeName(EXPECTED_APPLICATION.getCompanyTradeName());
        controller.setDisplayProducts(EXPECTED_APPLICATION.getDisplayProducts());
        controller.setIntendedStandArea(EXPECTED_APPLICATION.getIntendedStandArea());
        controller.setKeywords(EXPECTED_APPLICATION.getKeywords());
        controller.setNumberOfInvitations(EXPECTED_APPLICATION.getNumberOfInvitations());
        controller.setPhoneNumber(EXPECTED_APPLICATION.getPhoneNumber());
        controller.setVatNumber(EXPECTED_APPLICATION.getVatNumber());
        assertEquals(false, controller.updateConfirm());
    }

    @Test
    public void testControllerNegativeArea() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            controller = new UC06UpdateOrWithdrawApplicationController();
            controller.setIntendedStandArea(-1);
        });
    }

    /*@Test
    public void ensureWithdrawApplication() {
        User user = new User("Rafael", "rpm@gmail.com", "batatoon", "Rafa");
        Stand stand = new Stand( "ab",1);
        Keyword keyword = new Keyword("TEST_KEYWORD");
        List<Keyword> keywordList = new ArrayList<>();
        DisplayProduct displayProduct = new DisplayProduct("TEST_PRODUCT");
        List<DisplayProduct> displayProducts = new ArrayList<>();
        ApplicationReview applicationReview = new ApplicationReview();
        List<ApplicationReview> applicationReviews = new ArrayList<>();
        Application application = new Application();
        Event event = new Event(1, "NOT IT", "Everywhere", "Exposul", "2018/9/9", "2018/6/6");
        displayProducts.add(displayProduct);

        keywordList.add(keyword);

        applicationReview.setUser(user);
        applicationReview.setIsAccepted(false);
        applicationReview.setJustification("because");
        applicationReview.setStaffTopicKnowledgeRating(1);
        applicationReview.setEventAdequacyRating(2);
        applicationReview.setInviteAdequacyRating(3);
        applicationReview.setRequestedStandAreaRating(4);
        applicationReview.setOverallRecommendationRating(5);
        applicationReviews.add(applicationReview);

        application.setCompanyTradeName("Oracle");
        application.setVatNumber(123654789);
        application.setPhoneNumber(963258741);
        application.setNumberOfInvitations(20);
        application.setIntendedStandArea(20);
        application.setDisplayProducts(displayProducts);
        application.setKeywords(keywordList);
        application.setStatus("accepted");
        application.setAssignedStand(stand);
        application.setReviews(applicationReviews);
        application.setAuthor(user);
        application.setDescription("description");
        event.addApplication(application);

        application = event.getApplicationList().get(0);
        controller.setLoggedInUser(application.getAuthor());
        controller.setEvent(event);
        controller.setCompanyTradeName(application.getCompanyTradeName());
        controller.setVatNumber(application.getVatNumber());
        controller.setDescription(application.getDescription());
        assertTrue(controller.withdrawConfirm());
        assertEquals(event.getApplicationList().size(), 0);
    }

    @Test
    public void withdrawApplicationWithAdminTest() {
        User user = new User("Rafael", "rpm@gmail.com", "batatoon", "Rafa");
        Stand stand = new Stand( "ab",1);
        Keyword keyword = new Keyword("TEST_KEYWORD");
        List<Keyword> keywordList = new ArrayList<>();
        DisplayProduct displayProduct = new DisplayProduct("TEST_PRODUCT");
        List<DisplayProduct> displayProducts = new ArrayList<>();
        ApplicationReview applicationReview = new ApplicationReview();
        List<ApplicationReview> applicationReviews = new ArrayList<>();
        Application application = new Application();
        Event event = new Event(1, "NOT IT", "Everywhere", "Exposul", "2018/9/9", "2018/6/6");
        displayProducts.add(displayProduct);

        keywordList.add(keyword);

        applicationReview.setUser(user);
        applicationReview.setIsAccepted(false);
        applicationReview.setJustification("because");
        applicationReview.setStaffTopicKnowledgeRating(1);
        applicationReview.setEventAdequacyRating(2);
        applicationReview.setInviteAdequacyRating(3);
        applicationReview.setRequestedStandAreaRating(4);
        applicationReview.setOverallRecommendationRating(5);
        applicationReviews.add(applicationReview);

        application.setCompanyTradeName("Oracle");
        application.setVatNumber(123654789);
        application.setPhoneNumber(963258741);
        application.setNumberOfInvitations(20);
        application.setIntendedStandArea(20);
        application.setDisplayProducts(displayProducts);
        application.setKeywords(keywordList);
        application.setStatus("accepted");
        application.setAssignedStand(stand);
        application.setReviews(applicationReviews);
        application.setAuthor(user);
        application.setDescription("description");
        event.addApplication(application);

        application = event.getApplicationList().get(0);
        controller.setLoggedInUser(ADMIN_USER);
        controller.setEvent(event);
        controller.setCompanyTradeName(application.getCompanyTradeName());
        controller.setVatNumber(application.getVatNumber());
        controller.setDescription(application.getDescription());
        assertTrue(controller.withdrawConfirm());
        assertEquals(event.getApplicationList().size(), 0);
    }

    @Test
    public void shouldNotWithdrawApplicationWithInvalidUserTest() {
        User user = new User("Rafael", "rpm@gmail.com", "batatoon", "Rafa");
        Stand stand = new Stand( "ab",1);
        Keyword keyword = new Keyword("TEST_KEYWORD");
        List<Keyword> keywordList = new ArrayList<>();
        DisplayProduct displayProduct = new DisplayProduct("TEST_PRODUCT");
        List<DisplayProduct> displayProducts = new ArrayList<>();
        ApplicationReview applicationReview = new ApplicationReview();
        List<ApplicationReview> applicationReviews = new ArrayList<>();
        Application application = new Application();
        Event event = new Event(1, "NOT IT", "Everywhere", "Exposul", "2018/9/9", "2018/6/6");
        displayProducts.add(displayProduct);

        keywordList.add(keyword);

        applicationReview.setUser(user);
        applicationReview.setIsAccepted(false);
        applicationReview.setJustification("because");
        applicationReview.setStaffTopicKnowledgeRating(1);
        applicationReview.setEventAdequacyRating(2);
        applicationReview.setInviteAdequacyRating(3);
        applicationReview.setRequestedStandAreaRating(4);
        applicationReview.setOverallRecommendationRating(5);
        applicationReviews.add(applicationReview);

        application.setCompanyTradeName("Oracle");
        application.setVatNumber(123654789);
        application.setPhoneNumber(963258741);
        application.setNumberOfInvitations(20);
        application.setIntendedStandArea(20);
        application.setDisplayProducts(displayProducts);
        application.setKeywords(keywordList);
        application.setStatus("accepted");
        application.setAssignedStand(stand);
        application.setReviews(applicationReviews);
        application.setAuthor(user);
        application.setDescription("description");
        event.addApplication(application);

        application = event.getApplicationList().get(0);
        controller.setLoggedInUser(INVALID_USER);
        controller.setEvent(event);
        controller.setCompanyTradeName(application.getCompanyTradeName());
        controller.setVatNumber(application.getVatNumber());
        controller.setDescription(application.getDescription());
        assertFalse(controller.withdrawConfirm());
    }

    @Test
    public void testControllerNullEvent2() {
        User user = new User("Rafael", "rpm@gmail.com", "batatoon", "Rafa");
        Stand stand = new Stand( "ab",1);
        Keyword keyword = new Keyword("TEST_KEYWORD");
        List<Keyword> keywordList = new ArrayList<>();
        DisplayProduct displayProduct = new DisplayProduct("TEST_PRODUCT");
        List<DisplayProduct> displayProducts = new ArrayList<>();
        ApplicationReview applicationReview = new ApplicationReview();
        List<ApplicationReview> applicationReviews = new ArrayList<>();
        Application application = new Application();
        Event event = new Event(1, "NOT IT", "Everywhere", "Exposul", "2018/9/9", "2018/6/6");
        displayProducts.add(displayProduct);

        keywordList.add(keyword);

        applicationReview.setUser(user);
        applicationReview.setIsAccepted(false);
        applicationReview.setJustification("because");
        applicationReview.setStaffTopicKnowledgeRating(1);
        applicationReview.setEventAdequacyRating(2);
        applicationReview.setInviteAdequacyRating(3);
        applicationReview.setRequestedStandAreaRating(4);
        applicationReview.setOverallRecommendationRating(5);
        applicationReviews.add(applicationReview);

        application.setCompanyTradeName("Oracle");
        application.setVatNumber(123654789);
        application.setPhoneNumber(963258741);
        application.setNumberOfInvitations(20);
        application.setIntendedStandArea(20);
        application.setDisplayProducts(displayProducts);
        application.setKeywords(keywordList);
        application.setStatus("accepted");
        application.setAssignedStand(stand);
        application.setReviews(applicationReviews);
        application.setAuthor(user);
        application.setDescription("description");
        event.addApplication(application);

        application = event.getApplicationList().get(0);
        controller.setLoggedInUser(application.getAuthor());
        controller.setEvent(null);
        controller.setLoggedInUser(application.getAuthor());
        controller.setCompanyTradeName(application.getCompanyTradeName());
        controller.setVatNumber(application.getVatNumber());
        controller.setDescription(application.getDescription());
        assertEquals(false, controller.withdrawConfirm());
    }*/
}
