package studentinfo.model;

import java.util.ArrayList;
import java.util.List;

public class Group {
    private int id;
    private String name;
    private List<Student> studentslist = new ArrayList<>();

    public Group() {

    }

    public Group(int id, String name, List<Student> studentslist) {
        this.id = id;
        this.name = name;
        this.studentslist = studentslist;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Student> getStudentslist() {
        return studentslist;
    }

    public void setStudentslist(List<Student> studentslist) {
        this.studentslist = studentslist;
    }
    
    @Override
    public String toString() {
        return getName();
    }
}
