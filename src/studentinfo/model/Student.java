package studentinfo.model;

import java.util.Objects;

import org.eclipse.swt.graphics.Image;

public class Student {
    private int id;
    private String name;
    private String group;
    private String address;
    private String city;
    private int result;
    private String image;

    public Student() {

    }

    public Student(int id, String name, String group, String address, String city, int result, String image) {
        this.id = id;
        this.name = name;
        this.group = group;
        this.address = address;
        this.city = city;
        this.result = result;
        this.image = image;

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

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Name: " + getName() + "\n" + "Group: " + getGroup() + "\n" + "Address: "
                + getAddress() + "\n" + "City: " + getCity() + "\n" + "Result: " + getResult() + "\n" + "Image: "
                + getImage();
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, address, city, group, image, name, result);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        Student student = (Student) obj;
        return id == student.id && result == student.result && Objects.equals(name, student.name)
                && Objects.equals(group, student.group) && Objects.equals(address, student.address)
                && Objects.equals(city, student.city) && Objects.equals(image, student.image);
    }
    
    public Student getStudentWithAllValues() {
        return new Student(id, name, group, address, city, result, image);
    }
}
