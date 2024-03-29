import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        for (;;) {
            try {
                Scanner scanner = new Scanner(System.in);
                System.out.println("Введите путь к папке:");
                String path = scanner.nextLine();

                long result = FileUtils.calculateFolderSize(path);
                String recording = FileUtils.simplifyRecording(result);
                System.out.println("Размер папки " + path + " составляет " + recording);
            }
            catch (FileNotFoundException fileNotFoundException) {
                System.err.println("Файл не найден. Неверно введён путь к папке");
                fileNotFoundException.printStackTrace();
            }
            catch(Exception exception) {
                exception.printStackTrace();
            }
        }
    }
}
