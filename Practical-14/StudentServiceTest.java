import org.junit.jupiter.api.Test;
import service.StudentService;
import model.Student;
import static org.junit.jupiter.api.Assertions.*;

public class StudentServiceTest{
    @Test
    void addingStudentIncreasesCount(){
        StudentService service=new StudentService();
        service.addStudent(new Student("Amit"));
        assertEquals(1,service.getCount());
    }

    @Test
    void addingTwoStudentsIncreasesCount(){
        StudentService service=new StudentService();
        service.addStudent(new Student("Amit"));
        service.addStudent(new Student("Riya"));
        assertEquals(2,service.getCount());
    }

    @Test
    void invalidActionThrowsException(){
        StudentService service=new StudentService();
        assertThrows(IllegalArgumentException.class,()->service.performAction(5));
    }
}