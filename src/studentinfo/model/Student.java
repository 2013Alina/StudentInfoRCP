package studentinfo.model;

import java.util.Objects;

public class Student {
    private String name;
    private String group;
    private String address;
    private String city;
    private int result;
    private String image;

    public Student() {

    }

    public Student(String name, String group, String address, String city, int result, String image) {
        this.name = name;
        this.group = group;
        this.address = address;
        this.city = city;
        this.result = result;
        this.image = image;
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
        return "Name: " + getName() + "\n" + "Group: " + getGroup() + "\n" + "Address: " + getAddress() + "\n"
                + "City: " + getCity() + "\n" + "Result: " + getResult() + "\n" + "Image: " + getImage();
    }

    @Override
    public int hashCode() {
        return Objects.hash(address, city, group, image, name, result);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Student other = (Student) obj;
        return Objects.equals(getAddress(), other.getAddress()) && Objects.equals(getCity(), other.getCity())
                && Objects.equals(getGroup(), other.getGroup()) && Objects.equals(getImage(), other.getImage())
                && Objects.equals(getName(), other.getName()) && getResult() == other.getResult();
    }
    
    public Student getStudentWithAllValues() {
        return new Student(name, group, address, city, result, image);
    }
}
