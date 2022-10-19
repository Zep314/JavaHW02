/*
Задание 1 - Дана строка sql-запроса "select * from students where ". Сформируйте часть
WHERE этого запроса, используя StringBuilder. Данные для фильтрации приведены ниже в виде json строки.
Если значение null, то параметр не должен попадать в запрос.
Параметры для фильтрации: {"name":"Ivanov", "country":"Russia", "city":"Moscow", "age":"null"}
*/

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.Objects;

public class Main {
    public static void main(String[] args) {
        String[] jsonWheres = new String[] {  // всякие комбинации для проверки
                "{\"name\":\"Ivanov\", \"country\":\"Russia\", \"city\":\"Moscow\", \"age\":\"null\"}",
                "{\"name\":\"Petrov\", \"country\":\"Belarus\", \"city\":\"Minsk\", \"age\":\"20\"}",
                "{\"name\":\"null\", \"country\":\"null\", \"city\":\"Moscow\", \"age\":\"20\"}",
                "{\"name\":\"null\", \"country\":\"null\", \"city\":\"null\", \"age\":\"null\"}",
        };
        for (String jsonWhere: jsonWheres) {
            System.out.printf("Исходная строка: %s %nSQL строка: %s%n%n"
                    , jsonWhere
                    , getMySQLStr(jsonWhere)
            );
        }
    }

    public static String getMySQLStr(String jsonString) { // возвращаяе SQL строку из JSON
        try {
            JSONParser jsonParser = new JSONParser();  // создаем объект-парсер JSON
            JSONObject jsonObject = (JSONObject) jsonParser.parse(jsonString);  // прасим в него строку

            StringBuilder sqlPart = new StringBuilder(""); // тут "собираем" SQL строку

            if (!Objects.equals(jsonObject.get("name").toString(),"null")) {
                sqlPart.append("name=\""+jsonObject.get("name").toString()+"\"");
            }

            if (!Objects.equals(jsonObject.get("country").toString(),"null")) {
                if (sqlPart.length()>0) {
                    sqlPart.append(" and ");
                }
                sqlPart.append("country=\""+jsonObject.get("country").toString()+"\"");
            }

            if (!Objects.equals(jsonObject.get("city").toString(),"null")) {
                if (sqlPart.length()>0) {
                    sqlPart.append(" and ");
                }
                sqlPart.append("city=\""+jsonObject.get("city").toString()+"\"");
            }

            if (!Objects.equals(jsonObject.get("age").toString(),"null")) {
                if (sqlPart.length()>0) {
                    sqlPart.append(" and ");
                }
                sqlPart.append("age="+jsonObject.get("age").toString());
            }

            if (sqlPart.length()>0) {
                sqlPart.insert(0," where ");
            }
            sqlPart.insert(0,"select * from students");
            sqlPart.append(";");
            return sqlPart.toString();
        } catch (ParseException ex) { // обработка возможно ошибки
            ex.printStackTrace();
        }
        return ""; // надо чтото вернуть, если ошибка
    }
}

/* Вывод программы:
Исходная строка: {"name":"Ivanov", "country":"Russia", "city":"Moscow", "age":"null"}
SQL строка: select * from students where name="Ivanov" and country="Russia" and city="Moscow";

Исходная строка: {"name":"Petrov", "country":"Belarus", "city":"Minsk", "age":"20"}
SQL строка: select * from students where name="Petrov" and country="Belarus" and city="Minsk" and age=20;

Исходная строка: {"name":"null", "country":"null", "city":"Moscow", "age":"20"}
SQL строка: select * from students where city="Moscow" and age=20;

Исходная строка: {"name":"null", "country":"null", "city":"null", "age":"null"}
SQL строка: select * from students;
 */