import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class HomeWork4 {
    static Scanner scanner = new Scanner(System.in);
    static int size;
    static int horizontalPlayer, verticalPlayer;
    static int attempts;
    static boolean homeWorkModeFlag = false;

    public static void main(String[] args) {

        System.out.println("turn on HomeWork mode? (enter Y/N)");
        System.out.println("HomeWork mode - 5x5 field. To win, you need to get 4 in a row");
        if (scanner.nextLine().equals("Y")) {
            homeWorkModeFlag = true;
        } else {
            homeWorkModeFlag = false;
        }
        if(!homeWorkModeFlag) {
            System.out.println("Enter the size of the field");
            size = scanner.nextInt();
        } else {
            size = 5;
        }
        attempts = size * size;
        char[][] field = getEmptyField(size);
        toDraw(field);
        boolean isWin = false;
        while (!isWin) {
            doPlayerMove(field);
            if (isWin(field, 'X')) {
                System.out.println("Congratulations!!! Your are winner!!!");
                break;
            }
            if (attempts == 0) {
                System.out.println("This is draw. Try again.");
                break;
            }

            doAIMove(field);
            if (isWin(field, 'O')) {
                System.out.println("Sorry!!! You are loser :(");
                break;
            }
            if (attempts == 0) {
                System.out.println("This is draw. Try again.");
                break;
            }
        }
    }

    static boolean isWin(char[][] field, char sign) {
        if (!homeWorkModeFlag) {
            // Horizontal
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    if (field[i][j] != sign) {
                        break;
                    } else {
                        if (j == size - 1) {
                            return true;
                        }
                    }
                }
            }
            // Vertical
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    if (field[j][i] != sign) {
                        break;
                    } else {
                        if (j == size - 1) {
                            return true;
                        }
                    }
                }
            }
            // Diagonals
            for (int i = 0; i < size; i++) {
                if (field[i][i] != sign) {
                    break;
                } else {
                    if (i == size - 1) {
                        return true;
                    }
                }
            }
            for (int i = 0; i < size; i++) {
                if (field[i][size - 1 - i] != sign) {
                    break;
                } else {
                    if (i == size - 1) {
                        return true;
                    }
                }
            }
            return false;
        } else {
            // Horizontal
            boolean flag = false;
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    if (field[i][j] != sign) {
                        if (j != 0) {
                            flag = false;
                            break;
                        } else {
                            flag = true;
                        }
                    } else {
                        if (flag) {
                            if (j == size - 1) {
                                return true;
                            }
                        } else {
                            if (j == size - 2) {
                                return true;
                            }
                        }
                    }
                }
            }
            // Vertical
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    if (field[j][i] != sign) {
                        if (j != 0) {
                            flag = false;
                            break;
                        } else {
                            flag = true;
                        }
                    } else {
                        if (flag) {
                            if (j == size - 1) {
                                return true;
                            }
                        } else {
                            if (j == size - 2) {
                                return true;
                            }
                        }
                    }
                }
            }
            // Diagonals
            for (int i = 0; i < size; i++) {
                if (field[i][i] != sign) {
                    if (i != 0) {
                        flag = false;
                        break;
                    } else {
                        flag = true;
                    }
                } else {
                    if (flag) {
                        if (i == size - 1) {
                            return true;
                        }
                    } else {
                        if (i == size - 2) {
                            return true;
                        }
                    }
                }
            }
            for (int i = 0; i < size; i++) {
                if (field[i][size - 1 - i] != sign) {
                    if (i != 0) {
                        flag = false;
                        break;
                    } else {
                        flag = true;
                    }
                } else {
                    if (flag) {
                        if (i == size - 1) {
                            return true;
                        }
                    } else {
                        if (i == size - 2) {
                            return true;
                        }
                    }
                }
            }

            // subDiagonals
            for (int i = 0; i < size - 1; i++) {
                if (field[i][i + 1] != sign) {
                    break;
                } else {
                    if (i == size - 2) {
                        return true;
                    }
                }
            }
            for (int i = 0; i < size - 1; i++) {
                if (field[i+1][i] != sign) {
                    break;
                } else {
                    if (i == size - 2) {
                        return true;
                    }
                }
            }
            for (int i = 0; i < size - 1; i++) {
                if (field[i][size - 2 - i] != sign) {
                    break;
                } else {
                    if (i == size - 2) {
                        return true;
                    }
                }
            }
            for (int i = 1; i < size ; i++) {
                if (field[size - i][i] != sign) {
                    break;
                } else {
                    if (i == size - 2) {
                        return true;
                    }
                }
            }
            return false;
        }
    }
    static char[][] getEmptyField(int size) {
        char[][] field = new char[size][size];
        for (int i = 0; i < size; i++) {
            Arrays.fill(field[i], '-');
        }
        return field;
    }
    static void toDraw(char[][] field) {
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field.length; j++) {
                System.out.print(field[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
    static void doPlayerMove(char[][] field) {

        do {
            System.out.println("Your chance. Please enter coordinates...");
            horizontalPlayer = getCoordinate('H');
            verticalPlayer = getCoordinate('V');
        } while (isNotEmptyCell(field, horizontalPlayer, verticalPlayer));

        field[horizontalPlayer][verticalPlayer] = 'X';
        toDraw(field);
        attempts--;
    }
    static void doAIMove(char[][] field) {
        int counter = 0;

        Random random = new Random();
        int horizontal, vertical;
        while(true) {
            counter++;
            if (counter > 20) {
                horizontal = random.nextInt(size);
                vertical = random.nextInt(size);
            } else {
                horizontal = horizontalPlayer + (-1 + random.nextInt(3));
                vertical = verticalPlayer + (-1 + random.nextInt(3));
            }
                if ((horizontal < 0 || horizontal >= size) || (vertical < 0 || vertical >= size)) {
                    continue;
                }
                if (isEmptyCell(field, horizontal, vertical)) {
                    break;
                }


        }
        field[horizontal][vertical] = 'O';
        toDraw(field);
        attempts--;
    }
    static int getCoordinate(char var) {
        int coordinate;
        do {
            System.out.printf("Please enter %s-coordinate [in range 1-%s] ...%n", var, size);
            coordinate = scanner.nextInt() - 1;
        } while (coordinate < 0 || coordinate > size - 1);
        return coordinate;
    }
    static boolean isEmptyCell(char[][] field, int horizontal, int vertical) {
        return field[horizontal][vertical] == '-';
    }
    static boolean isNotEmptyCell(char[][] field, int horizontal, int vertical) {
        return !isEmptyCell(field,horizontal,vertical);
    }
}
