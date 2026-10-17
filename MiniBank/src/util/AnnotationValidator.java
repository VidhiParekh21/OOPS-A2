package util;

import java.lang.reflect.Field;
import java.util.ArrayList;

import model.annotation.Positive;
import model.annotation.MaxLength;

public class AnnotationValidator {

    public static String[] validate(Object obj) {

        ArrayList<String> errors = new ArrayList<>();

        Class<?> currentClass = obj.getClass();

        while (currentClass != null) {

            Field[] fields = currentClass.getDeclaredFields();

            for (Field field : fields) {

                try {
                    field.setAccessible(true);

                    Object value = field.get(obj);

                    if (field.isAnnotationPresent(Positive.class)) {

                        Positive positive =
                                field.getAnnotation(Positive.class);

                        if (value instanceof Number) {

                            double number =
                                    ((Number) value).doubleValue();

                            if (number <= 0) {
                                errors.add(
                                    field.getName() + ": "
                                    + positive.message()
                                );
                            }
                        }
                    }

                    if (field.isAnnotationPresent(MaxLength.class)) {

                        MaxLength maxLength =
                                field.getAnnotation(MaxLength.class);

                        if (value != null &&
                            value.toString().length()
                            > maxLength.value()) {

                            errors.add(
                                field.getName()
                                + ": maximum length is "
                                + maxLength.value()
                            );
                        }
                    }

                } catch (Exception e) {

                    errors.add(
                        "Error reading field: "
                        + field.getName()
                    );
                }
            }

            currentClass = currentClass.getSuperclass();
        }

        return errors.toArray(new String[0]);
    }
}