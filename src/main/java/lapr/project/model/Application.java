package lapr.project.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a company application to participate on an event.
 * <p>
 * Created by Diogo Capela (1171316@isep.ipp.pt) on 03/05/2018.
 */
@XmlRootElement
public class Application {

    // Possible status of the Application
    private static final String STATUS_REVIEW_PENDING = "reviewPending";
    private static final String STATUS_ACCEPTED = "accepted";
    private static final String STATUS_REJECTED = "rejected";

    // Instance variables
    private User author = new User();
    private String companyTradeName = "";
    private String description = "";
    private int vatNumber;
    private int phoneNumber;
    private int numberOfInvitations;
    private double intendedStandArea;
    private List<DisplayProduct> displayProducts = new ArrayList<>();
    private List<Keyword> keywords = new ArrayList<>();
    private List<Workshop> workshopList = new ArrayList<>();
    private String status = STATUS_REVIEW_PENDING;
    private Stand assignedStand = new Stand();
    private List<ApplicationReview> reviews = new ArrayList<>();
    private List<User> staffReviewers = new ArrayList<>();

    /**
     * Default public empty constructor.
     */
    public Application() {

    }

    /**
     * Constructor for the Application class.
     *
     * @param companyTradeName    Trade name of the company who's applying for a spot at the event.
     * @param vatNumber           VAT number of the company.
     * @param phoneNumber         Company's public phone number.
     * @param numberOfInvitations Number of invitations to purchase.
     * @param intendedStandArea   Intended stand area at the event.
     * @param displayProducts     List of display products.
     * @param keywords            List of keyword topics about the company (min 3, max 5).
     */
    public Application(String companyTradeName, int vatNumber, int phoneNumber, int numberOfInvitations, int intendedStandArea, List<DisplayProduct> displayProducts, List<Keyword> keywords) {
        this.companyTradeName = companyTradeName;
        this.vatNumber = vatNumber;
        this.phoneNumber = phoneNumber;
        this.numberOfInvitations = numberOfInvitations;
        this.intendedStandArea = intendedStandArea;
        this.displayProducts = displayProducts;
        this.keywords = keywords;
    }

    /* Getters & Setters
    ===================================== */

    @XmlElement
    public User getAuthor() {
        return this.author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    @XmlElement
    public String getCompanyTradeName() {
        return this.companyTradeName;
    }

    public void setCompanyTradeName(String companyTradeName) {
        this.companyTradeName = companyTradeName;
    }

    @XmlElement
    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @XmlElement
    public int getVatNumber() {
        return this.vatNumber;
    }

    public void setVatNumber(int vatNumber) {
        this.vatNumber = vatNumber;
    }

    @XmlElement
    public int getPhoneNumber() {
        return this.phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @XmlElement
    public int getNumberOfInvitations() {
        return this.numberOfInvitations;
    }

    public void setNumberOfInvitations(int numberOfInvitations) {
        this.numberOfInvitations = numberOfInvitations;
    }

    @XmlElement
    public double getIntendedStandArea() {
        return this.intendedStandArea;
    }

    public void setIntendedStandArea(double intendedStandArea) {
        this.intendedStandArea = intendedStandArea;
    }

    @XmlElement
    public List<DisplayProduct> getDisplayProducts() {
        return this.displayProducts;
    }

    public void setDisplayProducts(List<DisplayProduct> displayProducts) {
        this.displayProducts = displayProducts;
    }

    @XmlElement
    public List<Keyword> getKeywords() {
        return this.keywords;
    }

    public void setKeywords(List<Keyword> keywords) {
        this.keywords = keywords;
    }

    @XmlElement
    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @XmlElement
    public Stand getAssignedStand() {
        return this.assignedStand;
    }

    public void setAssignedStand(Stand assignedStand) {
        this.assignedStand = assignedStand;
    }

    @XmlElement
    public List<ApplicationReview> getReviews() {
        return this.reviews;
    }

    public void setReviews(List<ApplicationReview> reviews) {
        this.reviews = reviews;
    }

    @XmlElement
    public List<Workshop> getWorkshopList() {
        return workshopList;
    }

    public void setWorkshopList(List<Workshop> workshopList) {
        this.workshopList = workshopList;
    }

    public List<User> getStaffReviewers() {
        return staffReviewers;
    }

    public void setStaffReviewers(List<User> staffReviewers) {
        this.staffReviewers = staffReviewers;
    }

    /* toString, equals & hashCode
    ===================================== */

    @Override
    public String toString() {
        return String.format("Company Trade Name: %s | Description: %s | VAT Number: %d", this.companyTradeName, this.description, this.vatNumber);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Application that = (Application) o;
        return getCompanyTradeName().equals(that.getCompanyTradeName()) && getVatNumber() == that.getVatNumber() && getPhoneNumber() == that.getPhoneNumber() && getDescription().equals(that.getDescription()) && getNumberOfInvitations() == that.getNumberOfInvitations() && BigDecimal.valueOf(getIntendedStandArea()).equals(BigDecimal.valueOf(that.getIntendedStandArea())) && getDisplayProducts().equals(that.getDisplayProducts()) && getKeywords().equals(that.getKeywords()) && getStatus().equals(that.getStatus()) && getAssignedStand().equals(that.getAssignedStand()) && getWorkshopList().equals(that.getWorkshopList()) && getAuthor().equals(that.getAuthor()) && getStaffReviewers().equals(that.getStaffReviewers());
    }

    @Override
    public int hashCode() {
        return (companyTradeName.hashCode() + description.hashCode() + Integer.hashCode(vatNumber) + Integer.hashCode(phoneNumber) + Integer.hashCode(numberOfInvitations) + Double.hashCode(intendedStandArea) + displayProducts.hashCode() + keywords.hashCode() + status.hashCode() + assignedStand.hashCode() + workshopList.hashCode() + reviews.hashCode() + author.hashCode() + staffReviewers.hashCode());
    }

    /* Custom
    ===================================== */

    public void addDisplayProduct(DisplayProduct displayProduct) {
        this.displayProducts.add(displayProduct);
    }

    public void addKeyword(Keyword keyword) {
        this.keywords.add(keyword);
    }

    public void addApplicationReview(ApplicationReview applicationReview) {
        if (reviews.contains(applicationReview)) {
            throw new IllegalArgumentException("This Staff Member has already submitted a review for this Application");
        } else {
            this.reviews.add(applicationReview);
        }
    }

    public void addWorkshop(Workshop workshop) {
        this.workshopList.add(workshop);
    }

    public void setAccepted() {
        this.status = STATUS_ACCEPTED;
    }

    public void setRejected() {
        this.status = STATUS_REJECTED;
    }

    public void addStaffReviewer(User staffMember) {
        if (staffReviewers.contains(staffMember)) {
            throw new IllegalArgumentException("This Staff Member is already assigned as a reviewer in this Application");
        } else {
            staffReviewers.add(staffMember);
        }
    }
}
