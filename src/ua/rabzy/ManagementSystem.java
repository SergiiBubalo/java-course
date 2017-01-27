package ua.rabzy;

import javax.swing.*;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.util.*;


//##################################################################

class MainFrame extends JFrame {

    public MainFrame() {
        setBounds(100, 100, 300, 200);
    }

}

//##################################################################





/**
 *
 *
 */
public class ManagementSystem {

    private List<Group> groups;
    private Collection<Student> students;

    private static ManagementSystem instance;

    private ManagementSystem() {
        loadGroups();
        loadStudents();
    }

    private static synchronized ManagementSystem getInstance() {
        if (instance == null) {
            instance = new ManagementSystem();
        }
        return instance;
    }

    private void loadGroups() {
        if (groups == null) {
            groups = new ArrayList<>();
        } else {
            groups.clear();
        }

        Group g = new Group();
        g.setGroupId(1);
        g.setGroupName("Первая");
        g.setCurator("Доктор Борменталь");
        g.setSpeciality("Создание собачек из человеков");
        groups.add(g);

        g = new Group();
        g.setGroupId(2);
        g.setGroupName("Вторая");
        g.setCurator("Профессор Преображенский");
        g.setSpeciality("Создание человеков из собачек");
        groups.add(g);
    }

    private void loadStudents() {
        if (students == null) {
            // Мы используем коллекцию, которая автоматически сортирует свои элементы
            students = new TreeSet<>();
        } else {
            students.clear();
        }

        Calendar c = Calendar.getInstance();

        // Вторая группа
        Student s = new Student();
        s.setStudentId(1);
        s.setFirstName("Иван");
        s.setGivenName("Сергеевич");
        s.setLastName("Степанов");
        s.setSex('М');
        c.set(1990, 3, 20);
        s.setDateOfBirth(c.getTime());
        s.setGroupId(2);
        s.setEducationYear(2006);
        students.add(s);

        s = new Student();
        s.setStudentId(2);
        s.setFirstName("Наталья");
        s.setGivenName("Андреевна");
        s.setLastName("Чичикова");
        s.setSex('Ж');
        c.set(1990, 6, 10);
        s.setDateOfBirth(c.getTime());
        s.setGroupId(2);
        s.setEducationYear(2007);
        students.add(s);

        // Первая группа
        s = new Student();
        s.setStudentId(3);
        s.setFirstName("Петр");
        s.setGivenName("Викторович");
        s.setLastName("Сушкин");
        s.setSex('М');
        c.set(1991, 3, 12);
        s.setDateOfBirth(c.getTime());
        s.setEducationYear(2005);
        s.setGroupId(1);
        students.add(s);

        s = new Student();
        s.setStudentId(4);
        s.setFirstName("Вероника");
        s.setGivenName("Сергеевна");
        s.setLastName("Ковалева");
        s.setSex('Ж');
        c.set(1991, 7, 19);
        s.setDateOfBirth(c.getTime());
        s.setEducationYear(2006);
        s.setGroupId(1);
        students.add(s);
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            MainFrame mainFrame = new MainFrame();
            mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            mainFrame.setVisible(true);
        });

        try {
            System.setOut(new PrintStream("out.txt"));
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
            return;
        }

        ManagementSystem ms = ManagementSystem.getInstance();
        printString("Groups full list");
        printString("#################");
        List<Group> allGroups = ms.getGroups();
        for (Group gi : allGroups) {
            printString(gi);
        }
        printString();

        // Просмотр полного списка студентов
        printString("Students full list");
        printString("***********************");
        Collection<Student> allStudends = ms.getAllStudents();
        for (Student si : allStudends) {
            printString(si);
        }
        printString();

        printString("Students list by groups");
        printString("#################");
        List<Group> groups = ms.getGroups();
        for (Group gi : groups) {
            printString("---> Группа:" + gi.getGroupName());
            // Получаем список студентов для конкретной группы
            Collection<Student> students = ms.getStudentsFromGroup(gi, 2006);
            for (Student si : students) {
                printString(si);
            }
        }
        printString();

        Student s = new Student();
        s.setStudentId(5);
        s.setFirstName("Igor");
        s.setGivenName("Volodymyrovych");
        s.setLastName("Perebuynis");
        s.setSex('М');
        Calendar c = Calendar.getInstance();
        c.set(1991, 8, 31);
        s.setDateOfBirth(c.getTime());
        s.setGroupId(1);
        s.setEducationYear(2005);
        printString("Добавление студента:" + s);
        printString("********************");
        ms.insertStudent(s);
        printString("--->> Полный список студентов после добавления");
        allStudends = ms.getAllStudents();
        for (Student si : allStudends) {
            printString(si);
        }
        printString();

        s = new Student();
        s.setStudentId(5);
        s.setFirstName("Игорь");
        s.setGivenName("Владимирович");
        s.setLastName("Новоперебежкин");
        s.setSex('М');
        c = Calendar.getInstance();
        c.set(1991, 8, 31);
        s.setDateOfBirth(c.getTime());
        s.setGroupId(1);
        s.setEducationYear(2007);
        printString("Редактирование данных студента:" + s);
        printString("*******************************");
        ms.updateStudent(s);
        printString("--->> Полный список студентов после редактирования");
        allStudends = ms.getAllStudents();
        for (Student si : allStudends) {
            printString(si);
        }
        printString();

        printString("Student deleting:" + s);
        printString("******************");
        ms.deleteStudent(s);
        printString("--->> Полный список студентов после удаления");
        allStudends = ms.getAllStudents();
        for (Student si : allStudends) {
            printString(si);
        }
        printString();

        Group g1 = groups.get(0);
        Group g2 = groups.get(1);
        printString("Перевод студентов из 1-ой во 2-ю группу");
        printString("***************************************");
        ms.moveStudentsToGroup(g1, 2006, g2, 2007);
        printString("--->> Полный список студентов после перевода");
        allStudends = ms.getAllStudents();
        for (Student si : allStudends) {
            printString(si);
        }
        printString();

        // Удаляем студентов из группы
        printString("Удаление студентов из группы:" + g2 + " в 2006 году");
        printString("*****************************");
        ms.removeStudentsFromGroup(g2, 2006);
        printString("--->> Полный список студентов после удаления");
        allStudends = ms.getAllStudents();
        for (Iterator i = allStudends.iterator(); i.hasNext();) {
            printString(i.next());
        }
        printString();
    }

    private void removeStudentsFromGroup(Group group, int year) {
        Collection<Student> tmp = new TreeSet<Student>();
        for (Student si : students) {
            if (si.getGroupId() != group.getGroupId() || si.getEducationYear() != year) {
                tmp.add(si);
            }
        }
        students = tmp;
    }

    private void moveStudentsToGroup(Group oldGroup, int oldYear, Group newGroup, int newYear) {
        for (Student si : students) {
            if (si.getGroupId() == oldGroup.getGroupId() && si.getEducationYear() == oldYear) {
                si.setGroupId(newGroup.getGroupId());
                si.setEducationYear(newYear);
            }
        }
    }

    private void deleteStudent(Student student) {
        Student delStudent = null;
        for (Student si : students) {
            if (si.getStudentId() == student.getStudentId()) {
                // Вот этот студент - запоминаем его и прекращаем цикл
                delStudent = si;
                break;
            }
        }
        students.remove(delStudent);
    }

    private void updateStudent(Student student) {
        // Надо найти нужного студента (по его ИД) и заменить поля
        Student updStudent = null;
        for (Student si : students) {
            if (si.getStudentId() == student.getStudentId()) {
                // Вот этот студент - запоминаем его и прекращаем цикл
                updStudent = si;
                break;
            }
        }
        updStudent.setFirstName(student.getFirstName());
        updStudent.setGivenName(student.getGivenName());
        updStudent.setLastName(student.getLastName());
        updStudent.setSex(student.getSex());
        updStudent.setDateOfBirth(student.getDateOfBirth());
        updStudent.setGroupId(student.getGroupId());
        updStudent.setEducationYear(student.getEducationYear());
    }

    private Collection<Student> getAllStudents() {
        return students;
    }

    private void insertStudent(Student student) {
        students.add(student);
    }

    private Collection<Student> getStudentsFromGroup(Group group, int year) {
        Collection<Student> l = new TreeSet<>();
        for (Student si : students) {
            if (si.getGroupId() == group.getGroupId() && si.getEducationYear() == year) {
                l.add(si);
            }
        }
        return l;
    }

    private List<Group> getGroups() {
        return groups;
    }

    private static void printString(Object s) {
        try {
            System.out.println(new String(s.toString().getBytes("utf-8"), "utf-8"));
        } catch (UnsupportedEncodingException ex) {
            ex.printStackTrace();
        }
    }

    private static void printString() {
        System.out.println();
    }
}
