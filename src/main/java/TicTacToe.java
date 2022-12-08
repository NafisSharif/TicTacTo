import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;

public class TicTacToe extends JComponent {
    public static final int FIELD_EMPTY = 0; //пустое поле
    public static final int FIELD_X = 10; //поле с крестиком
    public static final int FIELD_0 = 200; //поле с ноликом
    int[][] field; //обьявляем наш массив игрового поля
    boolean isXturn;

    public TicTacToe() {
        enableEvents(AWTEvent.MOUSE_EVENT_MASK);
        field = new int[3][3]; //выделяе память под масси при создании компонента
        inintGame();
    }

    public void inintGame() {
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 3; ++j) {
                field[i][j] = FIELD_EMPTY; // очищаем массив, заполняя его в
            }
        }
        isXturn = true;
    }

    @Override
    protected void processMouseEvent(MouseEvent mouseEvent) {
        super.processMouseEvent(mouseEvent);
        if (mouseEvent.getButton() == MouseEvent.BUTTON1) { // проверяем что нажата левая клавиша
            int x = mouseEvent.getX(); // координата x клика
            int y = mouseEvent.getY(); // координата y клика
            // переводим координаты в индексы ячейки в массиве field
            int i = (int) ((float) x / getWidth() * 3);
            int j = (int) ((float) y / getHeight() * 3);
            //проверяем, что выбранная ячейка пуста и туда можно сходить
            if (field[i][j] == FIELD_EMPTY) {
                //проверка чей ход, если X - ставим крестик, если 0 - ставим нолик
                field[i][j] = isXturn ? FIELD_X : FIELD_0;
                isXturn = !isXturn; // меняем флаг хода.
                repaint(); // перерисовка компонента, это вызовет метод paintComponent()
                int res = checkState();
                if(res != 0) {
                    if(res == FIELD_0 * 3) {
                        //победил 0
                        JOptionPane.showMessageDialog(this, "Нолики выиграли", "Победа", JOptionPane.INFORMATION_MESSAGE);
                    } else if (res == FIELD_X * 3) {
                        //победил x
                        JOptionPane.showMessageDialog(this, "Крестики выиграли", "Победа", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(this, "Ничья", "Ничья", JOptionPane.INFORMATION_MESSAGE);
                    }
                    //перезапускаем игру
                    inintGame();
                    //перерисовываем поле
                    repaint();
                }
            }
        }
    }

    void drawX(int i, int j, Graphics graphics) {
        graphics.setColor(Color.BLACK);
        int dw = getWidth() / 3;
        int dh = getHeight() / 3;
        int x = i * dw;
        int y = j * dh;
        //линия от верхнего левого угла в правый нижний
        graphics.drawLine(x, y, x + dw, y + dh);
        //линия от левого нижнего угла до правого верхнего
        graphics.drawLine(x, y + dh, x + dw, y);
    }

    void draw0(int i, int j, Graphics graphics) {
        graphics.setColor(Color.BLACK);
        int dw = getWidth() / 3;
        int dh = getHeight() / 3;
        int x = i + dw;
        int y = j + dh;
    }

    void drawX0(Graphics graphics) {
        //вложенные циклы
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 3; ++j) {
                //если в данный ячейке крестик
                if (field[i][j] == FIELD_X) {
                    drawX(i, j, graphics);
                    //то жк для нолика
                } else if (field[i][j] == FIELD_0) {
                    draw0(i, j, graphics);
                }
            }
        }
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        //очищаем холст
        graphics.clearRect(0, 0, getWidth(), getHeight());
        //рисуем сетку из линий
        drawGrid(graphics);
        drawX0(graphics);
    }

    void drawGrid(Graphics graphics) {
        int w = getWidth();
        int h = getHeight();
        int dw = w / 3;
        int dh = h / 3;
        graphics.setColor(Color.BLUE);
        for (int i = 1; i < 3; i++) {
            graphics.drawLine(0, dh * i, w, dh * i);
            graphics.drawLine(dw * i, 0, dw * i, h);
        }
    }

    int checkState() {
        //проверяем диагонали
        int diag = 0;
        int diag2 = 0;
        for (int i = 0; i < 3; i++) {
            //сумма значений по диагонали от левого угла
            diag += field[i][i];
            //сумма значений по диагонали от правого угла
            diag2 += field[i][2 - i];
        }
        //если по диагонали стоят одни крестики или одни нолики выходим из метода
        if (diag == FIELD_0 * 3 || diag2 == FIELD_X * 3) {
            return diag;
        }
        //то же самое для второй диагонали
        if (diag2 == FIELD_0 * 3 || diag2 == FIELD_X * 3) {
            return diag2;
        }
        int check_i, check_j;
        boolean hasEmpty = false;
        // будем бегать по всем рядам
        for (int i = 0; i < 3; i++) {
            check_i = 0;
            check_j = 0;
            for (int j = 0; j < 3; j++) {
                //суммируем знаки в текущем ряду
                if (field[i][j] == 0) {
                    hasEmpty = true;
                }
                check_i += field[i][j];
                check_j += field[i][j];
            }
            //если выигрыщ крестика или нолика, то выходим
            if (check_i == FIELD_0 * 3 || check_j == FIELD_X * 3) {
                return check_i;
            }
            if (check_j == FIELD_0 * 3 || check_j == FIELD_X * 3) {
                return  check_j;
            }
        }
        if (hasEmpty) return 0; else return -1;
    }
}
