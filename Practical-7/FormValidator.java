import java.lang.annotation.*;
import java.lang.reflect.Field;
import java.util.*;

// NotBlank annotation
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@interface NotBlank {
}

// MaxLength annotation
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@interface MaxLength {
    int value();
}

// Signup Form
class SignupForm {

    @NotBlank
    @MaxLength(20)
    String name;

    @NotBlank
    @MaxLength(30)
    String email;

    @NotBlank
    @MaxLength(10)
    String mobile;

    SignupForm(String name, String email, String mobile) {
        this.name = name;
        this.email = email;
        this.mobile = mobile;
    }
}

// Validator
class Validator {

    public static List<String> validate(Object obj) {

        List<String> errors = new ArrayList<>();

        Field[] fields = obj.getClass().getDeclaredFields();

        for (Field field : fields) {

            try {
                field.setAccessible(true);

                String value = (String) field.get(obj);

                if (field.isAnnotationPresent(NotBlank.class)) {

                    if (value == null || value.trim().isEmpty()) {
                        errors.add(field.getName() + " cannot be blank");
                    }
                }

                if (field.isAnnotationPresent(MaxLength.class)) {

                    MaxLength annotation =
                            field.getAnnotation(MaxLength.class);

                    int max = annotation.value();

                    if (value != null && value.length() > max) {
                        errors.add(field.getName()
                                + " must not exceed "
                                + max + " characters");
                    }
                }

            } catch (Exception e) {
                errors.add("Error checking " + field.getName());
            }
        }

        return errors;
    }
}

// Main class
public class FormValidator {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("===== SIGNUP FORM =====");

        System.out.print("Enter name: ");
        String name = sc.nextLine();

        System.out.print("Enter email: ");
        String email = sc.nextLine();

        System.out.print("Enter mobile: ");
        String mobile = sc.nextLine();

        SignupForm form =
                new SignupForm(name, email, mobile);

        List<String> errors = Validator.validate(form);

        System.out.println("\n===== VALIDATION RESULT =====");

        if (errors.isEmpty()) {
            System.out.println("Form is valid.");
        } else {
            System.out.println("Form has errors:");

            for (String error : errors) {
                System.out.println("- " + error);
            }
        }

        sc.close();
    }
}
