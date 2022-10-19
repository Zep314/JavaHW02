import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/*
Задание 3 - Напишите метод, который определит тип (расширение) файлов из текущей папки
и выведет в консоль результат вида
1 Расширение файла: txt
2 Расширение файла: pdf
3 Расширение файла:
4 Расширение файла: jpg
 */
public class Main {
    // показываем файлы в указанной папке. Имя файла и расширение - в отдельных полях
    // Константы
    final static String PATH_TO_LIST = "C:\\_380";  // Тут ищем файлы
    public static void main(String[] args) {

        System.out.println("Список файлов в каталоге: "+PATH_TO_LIST);
        try {  // могут быть ошибки ввода-вывода
            dirList(PATH_TO_LIST);
        } catch (IOException e) {
            System.err.println("I/O Error: " + e.toString());  // тут ругаемся
        }
    }

    public static void dirList(String pathIn) throws IOException {
        // метод может создать исключение, которое надо обрабатывать из вне
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(Paths.get(pathIn))) {
            for (Path path : stream) {
                if (!Files.isDirectory(path)) {
                    String extension = "";
                    String nameFile = "";
                    int i = path.getFileName()  // ищем, где последняя точка в имени файла
                            .toString().lastIndexOf('.');
                    if (i > 0) {
                        extension = path.getFileName()
                                .toString().substring(i+1);
                    }
                    if (i>-1) {
                        nameFile = path.getFileName()
                                .toString().substring(0, i);
                    } else {
                        nameFile = path.getFileName()
                                .toString();
                    }

                    System.out.printf("Файл: %-30s Имя: %-25s Расширение: %-5s %n"
                            ,path.getFileName().toString()+","
                            ,nameFile+","
                            ,extension
                    );

                }
            }
        }
    }
}

/* Вывод программы:
Список файлов в каталоге: C:\_380
Файл: 380.FDB,                       Имя: 380,                      Расширение: FDB
Файл: 42-5550 - 06.2022.pdf,         Имя: 42-5550 - 06.2022,        Расширение: pdf
Файл: 42-5550 - 07.2022.zip,         Имя: 42-5550 - 07.2022,        Расширение: zip
Файл: file_no_ext,                   Имя: file_no_ext,              Расширение:
Файл: report1080620.pdf,             Имя: report1080620,            Расширение: pdf
Файл: report1080621.pdf,             Имя: report1080621,            Расширение: pdf
 */