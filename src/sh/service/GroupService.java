package sh.service;

import sh.model.Group;

import java.util.ArrayList;
import java.util.List;


public class GroupService {

    private final List<Group> groups;

    public GroupService() {
        this.groups = new ArrayList<>();
    }

    public List<Group> getGroups() {
        return groups;
    }

    public Group getGroupById(long id) {
        Group Group = null;
        for (Group st : groups) {
            if (st.getId() == id) {
                Group = st;
                break;
            }
        }
        return Group;
    }

    public Group save(Group Group) {
        groups.add(Group);
        return Group;
    }

    public static GroupService getInstance() {
        return Singleton.instance.service;
    }


    enum  Singleton {
        instance;

        GroupService service = new GroupService();
    }
}
