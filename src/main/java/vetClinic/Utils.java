package vetClinic;

import vetClinic.exception.EmptyModelListException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Locale;
import java.util.stream.IntStream;

public class Utils {
    public static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static String listInSeparatedLines(List<?> list) {
        if (list.isEmpty()) return "List of is empty";

        StringBuilder builder = new StringBuilder();

        IntStream.range(0, list.size()).mapToObj(i -> "\n" + i + ": " + list.get(i)).forEach(builder::append);
        return String.valueOf(builder);
    }

    public static String listWithTitle(List<?> list) {
        try {
            if (list.isEmpty()) return "Empty list";

            StringBuilder builder = new StringBuilder();

            builder.append("******************** ").append(getTypeOfObject(list)).append(" ********************");
            return String.valueOf(builder.append(listInSeparatedLines(list)));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    private static String getTypeOfObject(List<?> list) {
        Class<?> modelClass = list.get(0).getClass();
        if (modelClass.getSuperclass() != Object.class) {
            return modelClass.getSuperclass().getSimpleName();
        } else {
            return modelClass.getSimpleName();
        }
    }

    public static int readNumber() throws IOException {
        try {
            return Integer.parseInt(reader.readLine());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return readNumber();
        }
    }

    public static String readStringUpperCaseWithoutSpace() throws IOException {
        return reader.readLine().toUpperCase(Locale.ROOT).replaceAll(" ", "");
    }
}
