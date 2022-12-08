import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;

public class TicTacToe extends JComponent {
    public static final int FIELD_EMPTY = 0; //пустое поле
    public static final int FIELD_X = 10; //поле с крестиком
    public static final int FIELD_0 = 200; //поле с ноликом
    int[][] field; //обьявляем наш массив игрового поля
    boolean isXturn;

    public TicTacToe(){
        enableEvents(AWTEvent.MOUSE_EVENT_MASK);
        field = new int[3][3]; //выделяе память под масси при создании компонента
        inintGame();
    }

    public void inintGame(){
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 3; ++j) {
                field[i][j] = FIELD_EMPTY; // очищаем массив, заполняя его в
            }
        }
        isXturn = true;
    }

    @Override
    protected void processMouseEvent(MouseEvent mouseEvent){
        super.processMouseEvent(mouseEvent);
        if(mouseEvent.getButton() == MouseEvent.BUTTON1){ // проверяем что нажата левая клавиша
            int x = mouseEvent.getX(); // координата x клика
            int y = mouseEvent.getY(); // координата y клика
            // переводим координаты в индексы ячейки в массиве field
            int i = (int) ((float) x / getWidth() * 3);
            int j = (int) ((float) y / getHeight() * 3);
            //проверяем, что выбранная ячейка пуста и туда можно сходить
            if (field[i][j] == FIELD_EMPTY) {
                //проверка чей ход, если X - ставим крестик, если 0 - ставим нолик
                field[i][j] = isXturn?FIELD_X:FIELD_0;
                isXturn = !isXturn; // меняем флаг хода.
                repaint(); // перерисовка компонента, это вызовет метод paintComponent()
            }
        }
    }

    void drawX(int i, int j, Graphics graphics) {
        graphics.setColor(Color.BLACK);
        int dw = getWidth() / 3;
        int dh =getHeight() / 3;
        int x = i * dw;
        int y = j * dh;
        //линия от верхнего левого угла в правый нижний
        graphics.drawLine(x,y,x + dw, y + dh);
        //линия от левого нижнего угла до правого верхнего
        graphics.drawLine(x,y + dh, x + dw, y);
    }

    void draw0(int i, int j, Graphics graphics) {
        graphics.setColor(Color.BLACK);
        int dw = getWidth() / 3;
        int dh = getHeight() /3;
        int x = i + dw;
        int y = j + dh;
    }

    @Override
    protected void paintComponent(Graphics graphics){
        super.paintComponent(graphics);
        graphics.setColor(Color.RED);
        graphics.drawOval(10,10,100,100);
    }

    void drawGrid(Graphics graphics){
        int w = getWidth();
        int h = getHeight();
        int dw = w / 3;
        int dh = h / 3;
        graphics.setColor(Color.BLUE);
        for (int i = 1; i < 3; i++){
            graphics.drawLine(0,dh * i,w,dh * i);
            graphics.drawLine(dw * i,0,dw * i,h);
        }
    }
}
