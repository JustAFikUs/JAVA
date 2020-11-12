package cross.or.dots;


import java.util.Random;
import java.util.Scanner;

public class Main {

    public static char [] [] field;
    public static final int size = 3;
    public static final int autowin = 3;

    public static final char empty_dot = '*';
    public static final char dot_x = 'X';
    public static final char dot_o = 'O';

    public static void main(String[] args) {

        MakeAField();
        ShowMyField();

            while (true) {

                PlayerTurn();
                ShowMyField();
                if (CheckAWin(dot_x)) {
                    System.out.println("Победил человек ");
                    break;
                }
                if (CheckTheField() == true) {
                    System.out.println("Ничья");
                    break;
                }

                AITurn();
                ShowMyField();
                if(CheckAWin(dot_o)) {
                    System.out.println("Победил ИИ ");
                    break;
                }
                if (CheckTheField() == true) {
                    System.out.println("Ничья");
                    break;
                }
            }
            System.out.println("Игра окончена!");
    }

    //создание поля
    public static void MakeAField () {
        field = new char[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                field [i] [j] = empty_dot;
            }
        }
    }

    //Вывод поля на экран
    public static void ShowMyField () {
        for (int i = 0; i <= size; i++) {
            System.out.print(i + " ");
        }
        System.out.println();
        for (int i = 0; i < size; i++) {
            System.out.print((i + 1) + " ");
            for (int j = 0; j < size; j++) {
                System.out.print(field [i] [j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    //Проверка клетки
    public static boolean CheckTheCell (int x, int y) {
        if ((x <= 0) || (x > size) || (y <= 0) || (y > size)) {
            return false;
        }
        if (field [x] [y] == empty_dot) {
            return true;
        } else {
            return false;
        }
    }

    //Ход игрока
    public static Scanner Readme = new Scanner(System.in);
    public static void PlayerTurn () {
        int x;
        int y;
        do {
            System.out.print("Введите координаты установки точки в формате Х У -> ");
            x = Readme.nextInt() - 1;
            y = Readme.nextInt() - 1;
        } while (CheckTheCell(x, y) == true);
        field [y] [x] = dot_x;
    }

    //Ход ИИ
    public static Random AI = new Random();
    public static void AITurn () {
        int x;
        int y;
        do {
            x = AI.nextInt(size);
            y = AI.nextInt(size);
        } while (CheckTheCell(x, y) == true);
        System.out.println("Компьютер сделал ход на " + (x + 1) + " " + (y + 1));
    }

    //Проверка победы
    public static boolean CheckAWin (char point) {
        int count = 0;
        boolean check = false;
        for (int i = 0; i < size; i++) { //проверка на победу по строкам
            if (count == 2) {
                check = true;
                break;
            }
            for (int j = 0; j < size - 1; j++) {
                if ((field [i] [j] == point) && (field [i] [j + 1] == point)) {
                    count = count + 1;
                }
            }
        }

        for (int i = 0; i < size; i++) { //проверка на победу по столбцам
            if (count == 2) {
                check = true;
                break;
            } //
            for (int j = 0; j < size - 1; j++) {
                if ((field [j] [i] == point) && (field [j + 1] [i] == point)) {
                    count = count + 1;
                }
            }
        }

        count = 0;
        int j = 0;
        for (int i = 0; i < size - 1; i++) { // проверка на победу по главной диагонали
            if ( (field [i] [j] == point) && (field [i + 1] [j + 1] == point) ) {
                count = count + 1;
            }
            if (count == 2) {
                check = true;
                break;
            }
        }

        count = 0;
        int k = 0;
        for (int i = 2; i != 0; i++) { // проверка на победу по побочной диагонали
            if ( (field [i] [k] == point) && (field [i + 1] [j + 1] == point) ) {
                count = count + 1;
            }
            if (count == 2) {
                check = true;
                break;
            }
        }
        
        if (check == true) {
            return true;
        }
        else {
            return false;
        }
        
    }
    
    //Проверка на заполнение поля
    public static boolean CheckTheField () {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (field [i] [j] == empty_dot) {
                    return true;
                }
            }
        }
        return false;
    }
}
