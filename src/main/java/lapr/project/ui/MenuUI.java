package lapr.project.ui;

import javafx.fxml.FXML;
import lapr.project.utils.AuthManager;

public class MenuUI {

    private static final String cannotAccess = "You need to be logged-in to access this section!";
    private FXPageChanger pageChanger = FXPageChanger.getStaticInstance();
    private AuthManager authManager = new AuthManager();

    @FXML
    public void openDashboard() {
        pageChanger.showPage("Dashboard.fxml");
    }

    @FXML
    public void openLogger() {
        if (authManager.isEventManager()) {
            pageChanger.showPage("Logger.fxml");
        } else {
            FXUtils.openAlertError("Only Event Managers (Admins) can access the logger!");
        }
    }

    @FXML
    public void exitAppAndSave() {
        Main.saveState();
        Main.exit();
    }

    @FXML
    public void exitApp() {
        Main.exit();
    }

    @FXML
    public void openUC01CreateEvent() {
        if (authManager.isEventManager()) {
            pageChanger.showPage("UC01CreateEvent.fxml");
        } else {
            FXUtils.openAlertError("Only Event Managers (Admins) can create events!");
        }
    }

    @FXML
    public void openUC02AssignStaffMemberToEvent() {
        if (authManager.isUser()) {
            pageChanger.showPage("UC02AssignStaffMemberToEvent.fxml");
        } else {
            FXUtils.openAlertError(cannotAccess);
        }
    }

    @FXML
    public void openUC03StartAnEventsApplicationSubmissionPeriod() {
        if (authManager.isUser()) {
            pageChanger.showPage("UC03StartAnEventsApplicationSubmissionPeriod.fxml");
        } else {
            FXUtils.openAlertError(cannotAccess);
        }
    }

    @FXML
    public void openUC04UserRegistration() {
        pageChanger.showPage("UC04UserRegistration.fxml");
    }

    @FXML
    public void openUC05SubmitApplicationToEvent() {
        if (authManager.isUser()) {
            pageChanger.showPage("UC05SubmitApplicationToEvent.fxml");
        } else {
            FXUtils.openAlertError(cannotAccess);
        }
    }

    @FXML
    public void openUC06UpdateOrWithdrawApplication() {
        if (authManager.isUser()) {
            pageChanger.showPage("UC06UpdateOrWithdrawApplication.fxml");
        } else {
            FXUtils.openAlertError(cannotAccess);
        }
    }

    @FXML
    public void openUC07SubmitApplicationReview() {
        if (authManager.isUser()) {
            pageChanger.showPage("UC07SubmitApplicationReview.fxml");
        } else {
            FXUtils.openAlertError(cannotAccess);
        }
    }

    @FXML
    public void openUC08SubmitWorkshopSurvey() {
        if (authManager.isUser()) {
            pageChanger.showPage("UC08SubmitWorkshopSurvey.fxml");
        } else {
            FXUtils.openAlertError(cannotAccess);
        }
    }

    @FXML
    public void openUCXXListEventApplications() {
        if (authManager.isUser()) {
            pageChanger.showPage("UCXXListEventApplications.fxml");
        } else {
            FXUtils.openAlertError(cannotAccess);
        }
    }

    @FXML
    public void openUC16ListEventTopics() {
        if (authManager.isUser()) {
            pageChanger.showPage("UC16ListEventTopics.fxml");
        } else {
            FXUtils.openAlertError(cannotAccess);
        }
    }

    @FXML
    public void openUC17ListEventStandsInformation() {
        if (authManager.isUser()) {
            pageChanger.showPage("UC17ListEventStandsInformation.fxml");
        } else {
            FXUtils.openAlertError(cannotAccess);
        }
    }

    @FXML
    public void openUCXXBackupManager() {
        if (authManager.isEventManager()) {
            pageChanger.showPage("UCXXBackupManager.fxml");
        } else {
            FXUtils.openAlertError("Only Event Managers (Admins) access the backup manager!");
        }
    }

    @FXML
    public void openUC22ImportEventInformationFromAnotherApplication() {
        if (authManager.isEventManager()) {
            pageChanger.showPage("UC22ImportEventInformationFromAnotherApplication.fxml");
        } else {
            FXUtils.openAlertError("Only Event Managers (Admins) can import event information from another application!");
        }
    }

    @FXML
    public void openUC30ShowEventAcceptanceRate() {
        if (authManager.isEventManager()) {
            pageChanger.showPage("UC30ShowEventAcceptanceRate.fxml");
        } else {
            FXUtils.openAlertError("Only Event Managers (Admins) can view events acceptance rate!");
        }
    }

    @FXML
    public void openUC31ShowStaffMemberMeanRating() {
        if (authManager.isEventManager()) {
            pageChanger.showPage("UC31ShowStaffMemberMeanRating.fxml");
        } else {
            FXUtils.openAlertError("Only Event Managers (Admins) can view event staff members mean rating!");
        }
    }

    @FXML
    public void openUC32ShowDeviationBetweenStaffAverageRatingForEventMean() {
        if (authManager.isEventManager()) {
            pageChanger.showPage("UC32ShowDeviationBetweenStaffAverageRatingForEventMean.fxml");
        } else {
            FXUtils.openAlertError("Only Event Managers (Admins) can view the deviation between staff members average ratings for events!");
        }
    }

    @FXML
    public void openUC33ListWorkshopsMapColouring() {
        if (authManager.isUser()) {
            pageChanger.showPage("UC33ListWorkshopsMapColouring.fxml");
        } else {
            FXUtils.openAlertError("You need to be logged in to list workshops map colouring!");
        }
    }

    @FXML
    public void openUC34CalculateMinimumAmountOfNecessaryElectricalCable() {
        if (authManager.isEventManager()) {
            pageChanger.showPage("UC34CalculateMinimumAmountElectricalCable.fxml");
        } else {
            FXUtils.openAlertError("Only Event Managers (Admins) can calculate the minimum amount of electrical cable!");
        }
    }

    @FXML
    public void openUC40OrganizerAssignsStaffMemberToApplication() {
        if (authManager.isUser()) {
            pageChanger.showPage("UC40OrganizerAssignsStaffMemberToApplication.fxml");
        } else {
            FXUtils.openAlertError(cannotAccess);
        }
    }

    @FXML
    public void openUC41AcceptOrRejectApplication() {
        if (authManager.isUser()) {
            pageChanger.showPage("UC41AcceptOrRejectApplication.fxml");
        } else {
            FXUtils.openAlertError(cannotAccess);
        }
    }

}
