/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lifecircle;

import java.awt.*;
import javax.swing.*;

/**
 *
 * @author qwer
 */
class LifeCircleArea extends JComponent {
    
    int[] _data = {};
    
    public void setData(int[] data)
    {
        _data = data;
    }
    
    public int[] getData()
    {
        return _data;
    }
    
    @Override
    public void paintComponent(Graphics g)
    {
	super.paintComponent(g);
	Graphics2D g2 = (Graphics2D) g;
        LifeCircleHelper.drawLifeCircle(g2, getWidth(), getHeight(), _data);
    }
    
    public void update()
    {
        repaint();
    }
    
    public void reset()
    {
        _data = null;
        repaint();
    }
}
