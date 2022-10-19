/*
[{"фамилия":"Иванов","оценка":"5","предмет":"Математика"},
{"фамилия":"Петрова","оценка":"4","предмет":"Информатика"},
{"фамилия":"Краснов","оценка":"5","предмет":"Физика"}]
Написать метод(ы), который распарсит json и, используя StringBuilder, создаст строки вида:
Студент [фамилия] получил [оценка] по предмету [предмет].
(Не sql запрос, я оговорился на вебинаре!)
Пример вывода:
Студент Иванов получил 5 по предмету Математика.
Студент Петрова получил 4 по предмету Информатика.
Студент Краснов получил 5 по предмету Физика.
 */
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Main {

    // Константы
    private static final String filePath = "students.json";

    public static void main(String[] args) {
        getJsonData(filePath);
    }

    public static void getJsonData(String pathIn) {
        try {
            // считывание файла JSON
            FileReader reader = new FileReader(pathIn);  // Читаем файл

            JSONParser jsonParser = new JSONParser();  // создаем объект-парсер JSON
            Object jsonObject = jsonParser.parse(reader);  // прасим в него файл

            if (jsonObject instanceof JSONArray) {
                JSONArray db = (JSONArray) jsonObject;  // база данных из элементов JSON

                for (int i = 0; i <db.size(); i++) {
                    Object element = db.get(i);
                    if (element instanceof JSONObject) {
                        System.out.printf("Студент %s получил %s по предмету %s %n"
                                , ((JSONObject)(element)).get("фамилия")
                                , ((JSONObject)(element)).get("оценка")
                                , ((JSONObject)(element)).get("предмет")
                        );
                    }
                }
            }
        } catch (FileNotFoundException ex) {    // обработка возможных ошибок
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (ParseException ex) {
            ex.printStackTrace();
        } catch (NullPointerException ex) {
            ex.printStackTrace();
        }
    }
}

/* Вывод программы:
Студент Иванов получил 5 по предмету Математика
Студент Петрова получил 4 по предмету Информатика
Студент Краснов получил 5 по предмету Физика 
 */