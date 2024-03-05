package studentinfo.model;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.FileLocator;

import studentinfo.Activator;

//public class StudentDataManager {
//
//    public static List<Group> addGroups() {
//        List<Group> groups = new ArrayList<>();
//        Group group1 = new Group("Group 1", createStudentsForGroup1());
//        Group group2 = new Group("Group 2", createStudentsForGroup2());
//        groups.add(group1);
//        groups.add(group2);
//
//        return groups;
//    }
//
//    public static Group getGroup(List<Group> groups, int index) {
//        if (index >= 0 && index < groups.size()) {
//            return groups.get(index);
//        } else {
//            return null;
//        }
//    }
//
//    public static List<Student> getAllStudents(List<Group> groups) {
//        List<Student> allStudents = new ArrayList<>();
//        for (Group group : groups) {
//            allStudents.addAll(group.getStudentslist());
//        }
//        return allStudents;
//    }
//
//    private static List<Student> createStudentsForGroup1() {
//        List<Student> students = new ArrayList<>();
//        
//        URL urlIvan = Activator.getDefault().getBundle().getEntry("/icons/ivan.png"); //относительный путь внутри плагина
//        String pathIvan = "/icons/ivan.png";
//        try {
//            pathIvan = FileLocator.resolve(urlIvan).getPath();
//            System.out.println("PATHIVAN === " + pathIvan);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        students.add(new Student("Ivan Ivanov", "Group 1", "Gogolya street, 25", "Dnepr", 5, pathIvan));
//        
//        URL urlPetr = Activator.getDefault().getBundle().getEntry("/icons/petr.png");
//        String pathPetr = "/icons/petr.png";
//        try {
//            pathPetr = FileLocator.resolve(urlPetr).getPath();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        students.add(new Student("Petr Petrov", "Group 1", "S.Kovalevskoy, 35", "Dnepr", 4, pathPetr));
//        return students;
//    }
//
//    private static List<Student> createStudentsForGroup2() {
//        List<Student> students = new ArrayList<>();
//        
//        URL urlAlex = Activator.getDefault().getBundle().getEntry("/icons/alex.png");
//        String pathAlex = "/icons/alex.png";
//        try {
//            pathAlex = FileLocator.resolve(urlAlex).getPath();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        students.add(new Student("Alex Alexeev", "Group 2", "Zelena, 165", "Lvov", 3, pathAlex));
//        
//        URL urlNikolay = Activator.getDefault().getBundle().getEntry("/icons/nikolay.png");
//        String pathNikolay = "/icons/nikolay.png";
//        try {
//            pathNikolay = FileLocator.resolve(urlNikolay).getPath();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        students.add(new Student("Nikolay Nikolaev", "Group 2", "Lichakovskaya, 77", "Lvov", 5, pathNikolay));
//        return students;
//    }
//}

public class StudentDataManager {

    public static List<Group> addGroups() {
        List<Group> groups = new ArrayList<>();
        Group group1 = new Group(0, "Group 1", createStudentsForGroup1());
        Group group2 = new Group(1, "Group 2", createStudentsForGroup2());
        groups.add(group1);
        groups.add(group2);

        return groups;
    }
    
    public static Group getGroup(List<Group> groups, int index) {
        if (index >= 0 && index < groups.size()) {
            return groups.get(index);
        } else {
            return null;
        }
    }

    public static List<Student> getAllStudents(List<Group> groups) {
        List<Student> allStudents = new ArrayList<>();
        for (Group group : groups) {
            allStudents.addAll(group.getStudentslist());
        }
        return allStudents;
    }

    private static List<Student> createStudentsForGroup1() {
        List<Student> students = new ArrayList<>();
        String pathIvan = "C:\\DevTools\\GitSources\\StudentInfoRCP\\icons\\ivan.png";
        String pathPetr = "C:\\DevTools\\GitSources\\StudentInfoRCP\\icons\\petr.png";
        students.add(new Student(0, "Ivan Ivanov", "Group 1", "Gogolya street, 25", "Dnepr", 5, pathIvan));
        students.add(new Student(1, "Petr Petrov", "Group 1", "S.Kovalevskoy, 35", "Dnepr", 4, pathPetr));
        return students;
    }

    private static List<Student> createStudentsForGroup2() {
        List<Student> students = new ArrayList<>();
        String pathAlex = "C:\\DevTools\\GitSources\\StudentInfoRCP\\icons\\alex.png";
        String pathNikolay = "C:\\DevTools\\GitSources\\StudentInfoRCP\\icons\\nikolay.png";
        students.add(new Student(0, "Alex Alexeev", "Group 2", "Zelena, 165", "Lvov", 3, pathAlex));
        students.add(new Student(1, "Nikolay Nikolaev", "Group 2", "Lichakovskaya, 77", "Lvov", 5, pathNikolay));
        return students;
    }
}
