import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Objects;

public class FileUtils {
    public static long calculateFolderSize(String path) throws FileNotFoundException {
        File item = new File(path);
        long length = 0;

        if (!item.exists()) {
            throw new FileNotFoundException();
        }

        if (item.isFile()) {
            length += item.length();
        }
        else if (item.isDirectory()) {
            for (File file: Objects.requireNonNull(item.listFiles())) {
                if (file.isDirectory()) {
                    length += calculateFolderSize(file.getPath());
                } else {
                    length += file.length();
                }
            }
        }
        return length;
    }
    public static String simplifyRecording(long length) {
        StringBuilder builder = new StringBuilder();

        BigDecimal size = new BigDecimal(length);
        BigDecimal digit = new BigDecimal(1024);
        BigDecimal constant = new BigDecimal(0.5);

        int count = 0;

        while ((size.divide(digit)).compareTo(constant) == 1) {
            size = size.divide(digit);
            count++;
        }

        DecimalFormat format = new DecimalFormat("###.#");
        builder.append(format.format(size));

        switch (count) {
            case 0 -> builder.append(" байт");
            case 1 -> builder.append(" Кб");
            case 2 -> builder.append(" Мб");
            case 3 -> builder.append(" Гб");
            case 4 -> builder.append(" Тб");
        }
        return builder.toString();
    }
}
