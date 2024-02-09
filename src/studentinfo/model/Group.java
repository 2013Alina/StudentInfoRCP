package studentinfo.model;

import java.util.ArrayList;
import java.util.List;

public class Group {

    private String name;
    private List<Student> studentslist = new ArrayList<>();

    public Group() {

    }

    public Group(String name, List<Student> studentslist) {
        super();
        this.name = name;
        this.studentslist = studentslist;
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
        return name;
    }
}
