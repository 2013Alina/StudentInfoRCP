package studentinfo.model;

import java.util.ArrayList;
import java.util.List;

public class StudentDataManager {

    public static List<Group> getGroups() {
        List<Group> groups = new ArrayList<>();
        Group group1 = new Group("Group 1", createStudentsForGroup1());
        Group group2 = new Group("Group 2", createStudentsForGroup2());
        groups.add(group1);
        groups.add(group2);

        return groups;
    }

    private static List<Student> createStudentsForGroup1() {
        List<Student> students = new ArrayList<>();
        students.add(new Student("Ivan Ivanov", "Group 1", "Gogolya street, 25", "Dnepr", 5, "ivan.png"));
        students.add(new Student("Petr Petrov", "Group 1", "S.Kovalevskoy, 35", "Dnepr", 4, "petr.png"));
        return students;
    }

    private static List<Student> createStudentsForGroup2() {
        List<Student> students = new ArrayList<>();
        students.add(new Student("Alex Alexeev", "Group 2", "Zelena, 165", "Lvov", 3, "alex.png"));
        students.add(new Student("Nikolay Nikolaev", "Group 2", "Lichakovskaya, 77", "Lvov", 5, "nikolay.png"));
        return students;
    }
}
