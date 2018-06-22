package lapr.project.controller;

import lapr.project.model.Expo;
import lapr.project.utils.Logger;
import lapr.project.utils.XMLSerializer;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class UCXXBackupManagerController extends SuperController {

    public void createBackup(String filePath) throws Exception {
        XMLSerializer.serializeToXML(Expo.getInstance(), Expo.class, filePath + "/" + getTimestampString() + ".xml");
        Logger.log(USERSTR + getLoggedInUser().getUsername() + " created backup on:"+filePath);
    }

    public void importBackup(String backupName) throws Exception {
        Expo importedExpo = (Expo) XMLSerializer.deserializeFromXML(Expo.class, backupName);
        expo.setUserRegistry(importedExpo.getUserRegistry());
        expo.setEventRegistry(importedExpo.getEventRegistry());
        Logger.log(USERSTR + getLoggedInUser().getUsername() + " imported backup:" + backupName);
    }

    public String getTimestampString() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        return sdf.format(timestamp);
    }

}
