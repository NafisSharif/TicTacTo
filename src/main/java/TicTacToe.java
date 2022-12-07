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
