import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;

public class TicTacToe extends JComponent {

    public TicTacToe(){
        enableEvents(AWTEvent.MOUSE_EVENT_MASK);
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
