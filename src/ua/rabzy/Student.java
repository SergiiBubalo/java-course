package ua.rabzy;

import java.text.Collator;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

/**
 *
 */
public class Student implements Comparable {
    private int studentId;
    private String firstName;
    private String lastName;
    private String givenName;
    private Date dateOfBirth;
    private char sex;
    private int groupId;
    private int educationYear;

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getGivenName() {
        return givenName;
    }

    public void setGivenName(String givenName) {
        this.givenName = givenName;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public char getSex() {
        return sex;
    }

    public void setSex(char sex) {
        this.sex = sex;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int grioupId) {
        this.groupId = grioupId;
    }

    public int getEducationYear() {
        return educationYear;
    }

    public void setEducationYear(int educationYear) {
        this.educationYear = educationYear;
    }


    public String toString() {
        return lastName + " " + firstName + " " + givenName + ", "
                + DateFormat.getDateInstance(DateFormat.SHORT).format(dateOfBirth)
                + ", Группа ИД=" + groupId + " Год:" + educationYear;
    }

    @Override
    public int compareTo(Object obj) {
        Collator c = Collator.getInstance(new Locale("ru"));
        c.setStrength(Collator.PRIMARY);
        return c.compare(this.toString(), obj.toString());
    }
}
