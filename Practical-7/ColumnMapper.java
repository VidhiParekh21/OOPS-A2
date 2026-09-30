import java.lang.annotation.*;
import java.lang.reflect.Field;
import java.util.*;

// Column annotation
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@interface Column {
    String name();
}

// Student class
class Student {

    @Column(name = "name")
    String name;

    @Column(name = "email")
    String email;

    @Column(name = "age")
    int age;

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", age=" + age +
                '}';
    }
}

// Main class
public class ColumnMapper {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("===== COLUMN MAPPER =====");

        System.out.print("Enter header row (comma separated): ");
        String headerInput = sc.nextLine();

        System.out.print("Enter data row (comma separated): ");
        String dataInput = sc.nextLine();

        String[] headers = headerInput.split(",");
        String[] data = dataInput.split(",");

        Student student = new Student();

        try {
            Field[] fields = Student.class.getDeclaredFields();

            for (Field field : fields) {

                if (field.isAnnotationPresent(Column.class)) {

                    Column column =
                            field.getAnnotation(Column.class);

                    String columnName = column.name();

                    int index = -1;

                    for (int i = 0; i < headers.length; i++) {

                        if (headers[i].trim()
                                .equalsIgnoreCase(columnName)) {

                            index = i;
                            break;
                        }
                    }

                    if (index == -1) {
                        System.out.println(
                                "Missing column: " + columnName);
                        continue;
                    }

                    field.setAccessible(true);

                    String value = data[index].trim();

                    if (field.getType() == String.class) {

                        field.set(student, value);

                    } else if (field.getType() == int.class) {

                        field.setInt(
                                student,
                                Integer.parseInt(value)
                        );
                    }
                }
            }

            System.out.println("\n===== RESULT =====");
            System.out.println(student);

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        sc.close();
    }
}