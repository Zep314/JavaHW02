import java.io.IOException;
import java.util.logging.LogManager;
import java.util.logging.Logger;

/*
Задание 4 - К калькулятору из предыдущего дз добавить логирование.
 */
// Реализовал калькулятор с 5-ю действиями и скобками. Такой делали на курсе Python.
// Алгоритм остался прежний, переписал толко код с Python на  Java
public class Main {
    // Класс для работы с логами
    private static Logger log = Logger.getLogger(Main.class.getName());
    public static void main(String[] args) {
        try {
            LogManager.getLogManager().readConfiguration( // берем конфиг для логов
                    Main.class.getResourceAsStream("./log.config"));
        } catch (IOException e) {  // печаль, беда...
            System.err.println("Could not setup logger configuration: " + e.toString());
        }
        //log.setLevel(Level.FINE);
        log.info("Начинаем работу");

        MyCalculator calc = new MyCalculator();
        String[] arr = new String[] {
                "12*3+3+5+2*2",
                "1+2*(3+5)",
                "15/(7-(1+1))*3-(2+(1+1))",
                "2^5",
                "-2*5",
                "-2^5",
                "2+2",
                "1+2*3",
                "1-2*3",
                "1+2*3",
                "(1+2)*3",
                "1/2+1/3",
                "1-22/22-2/2*2+1",
                "(35.57*4^3*2-3^2^3/(3+5^5-1.33)-(46*(7.7-1.12/(5*97^2-3.36))*(1.1-0.09)-(0.01+1)-74*(59+1-98)/31+58)*61-7*(1+2*6.6666))/(3*366.98/(2+2*2)+98.98)+989",
        };
        for (String s: arr){
            log.fine("Решаем: " + s);
            String calcString = calc.Calculate(s,log);
            System.out.printf("Вычисляем: '%s' = %s\n"
                    , s
                    , calcString
            );
            log.info("Вычисляем: " + s + " = " + calcString);
        }
        log.info("Работа завершена!");
    }
}
