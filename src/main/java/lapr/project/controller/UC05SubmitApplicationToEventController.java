package lapr.project.controller;

import lapr.project.model.*;
import lapr.project.utils.Logger;

import java.util.List;

/**
 * Controller class
 *
 * @author Vítor Hugo Silva (1140825@isep.ipp.pt)
 */
public class UC05SubmitApplicationToEventController extends SuperController {

    private Event selectedEvent;
    private Application app;

    public UC05SubmitApplicationToEventController() {
        app = new Application();
    }

    public void setEventForApplication(Event e) {
        if (e != null) {
            selectedEvent = e;
        }
    }

    /**
     * Set application information. Custom
     */
    /**
     * Set application information.
     *
     * @param companyTradeName : TradeName of the company that applies
     */
    public void setCompanyTradeName(String companyTradeName) {
        app.setCompanyTradeName(companyTradeName);
    }

    /**
     * Set application information.
     *
     * @param vatNumber : VAT number
     */
    public void setVatNumber(int vatNumber) {
        app.setVatNumber(vatNumber);
    }

    /**
     * Set application information.
     *
     * @param phoneNumber : Phone number
     */
    public void setPhoneNumber(int phoneNumber) {
        app.setPhoneNumber(phoneNumber);
    }

    /**
     * Set application information.
     *
     * @param numberOfInvitations : Numer of inventations
     */
    public void setNumberOfInvitations(int numberOfInvitations) {
        app.setNumberOfInvitations(numberOfInvitations);
    }

    /**
     * Set application information.
     *
     * @param intendedStandArea : Intended area
     */
    public void setIntendedStandArea(double intendedStandArea) {
        if (intendedStandArea > 0) {
            app.setIntendedStandArea(intendedStandArea);
        } else {
            throw new IllegalArgumentException("Cannot set negative area!");
        }
    }

    /**
     * Set application information.
     *
     * @param displayProducts : List of required products
     */
    public void setDisplayProducts(List<DisplayProduct> displayProducts) {
        app.setDisplayProducts(displayProducts);
    }

    /**
     * Set application information.
     *
     * @param keywords : A list of topics
     */
    public void setKeywords(List<Keyword> keywords) {
        app.setKeywords(keywords);
    }

    public void setWorkshopList(List<Workshop> lstWorkshop) {
        app.setWorkshopList(lstWorkshop);
    }

    /**
     * Register application to a given event
     *
     * @return True if add with success. False if an error happened.
     */
    public boolean confirm() {
        if (selectedEvent != null) {
            app.setAuthor(loggedInUser);
            selectedEvent.addApplication(app);
            Logger.log(USERSTR + getLoggedInUser().getUsername() + " added application " + app + " to event:"+selectedEvent);
            app = new Application();
            return true;
        } else {
            return false;
        }
    }
}
