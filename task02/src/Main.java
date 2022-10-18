import java.io.IOException;
import java.util.logging.LogManager;
import java.util.logging.Logger;

/*
Задание 2 - Реализуйте алгоритм сортировки пузырьком числового массива,
результат после каждой итерации запишите в лог-файл.
 */
public class Main {
    // Константы
    final static int MAX_ARRAY_LENGTH = 30;
    final static int MAX_ARRAY_NUMBER = 100;

    // Класс для работы с логами
    private static Logger log = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {
        try {
            LogManager.getLogManager().readConfiguration( // берем конфиг для догов
                    Main.class.getResourceAsStream("./log.config"));
        } catch (IOException e) {  // печаль, беда...
            System.err.println("Could not setup logger configuration: " + e.toString());
        }

        log.info("Начинаем работу, инициализируем массив случайной величины");
        int[] testArray = new int[(int) (Math.random() * MAX_ARRAY_LENGTH)];
        log.info("Заполняем массив случайными числами");
        for (int i = 0; i < testArray.length; i++) {
            testArray[i] = (int) (Math.random() * 2 * MAX_ARRAY_NUMBER) - MAX_ARRAY_NUMBER;
        }
        System.out.println("Исходный массив:");
        System.out.println(myGetArray(testArray));
        log.info("Исходный массив:");
        log.info(myGetArray(testArray));

        testArray = bubbleSort(testArray); // сортировка пузырьком

        System.out.println("Массив после сортировки");
        System.out.println(myGetArray(testArray));
        log.info("Массив после сортировки:");
        log.info(myGetArray(testArray));
    }

    public static String myGetArray(int[] arrayIn) {
        // возвращаем красивую строку для печати
        StringBuilder ret = new StringBuilder("");
        ret.append("[ ");
        for (int i=0; i<arrayIn.length; i++) {
            if (i != 0) {
                ret.append(", ");
            }
            ret.append(arrayIn[i]);
        }
        ret.append(" ]");
        return ret.toString();
    }
    public static int[] bubbleSort(int[] arrayIn) {  // простая сортировка пузырьком
        for (int i=0; i<arrayIn.length; i++)
            for (int j=i; j<arrayIn.length; j++) {
                if (arrayIn[i] > arrayIn[j]) {
                    int temp = arrayIn[i];
                    arrayIn[i] = arrayIn[j];
                    arrayIn[j] = temp;
                    log.info(myGetArray(arrayIn));
                }
            }
        return arrayIn;
    }
}

/* Вывод программы
Исходный массив:
[ 59, -39, 34, 99, 37, 89, 29, 27, 81, 81, 51, -96, 73, 89, -48, -80, -42, -75, 48, 53, 73, 86 ]
Массив после сортировки
[ -96, -80, -75, -48, -42, -39, 27, 29, 34, 37, 48, 51, 53, 59, 73, 73, 81, 81, 86, 89, 89, 99 ]
 */