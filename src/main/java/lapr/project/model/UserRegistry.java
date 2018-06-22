package lapr.project.model;


import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement
public class UserRegistry {

    private List<User> userList = new ArrayList<>();

    public UserRegistry() {
        // Registry works like a Data Base
    }

    @XmlElement
    public List<User> getUsers() {
        return userList;
    }

    public void addUser(User user) throws IllegalArgumentException {
        if (userList.contains(user)) {
            throw new IllegalArgumentException("This user is already registered.");
        } else {
            userList.add(user);
        }
    }

    public void deleteUser(User user) {
        userList.remove(user);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        UserRegistry otherUserRegistry = (UserRegistry) obj;
        return getUsers().equals(otherUserRegistry.getUsers());
    }

    @Override
    public int hashCode() {
        return getUsers().hashCode();
    }

}
