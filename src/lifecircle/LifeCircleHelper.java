/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lifecircle;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;

/**
 *
 * @author timur
 */
public class LifeCircleHelper {
    public static final double CIRCLE_POSITION = 25;
    public static final double SMALL_POINTS_DIAMETER = 6;
    public static final double BIG_POINTS_DIAMETER = 10;

    public static String[] LABELS = {"Health", "Career", "Relations", "Finance",
        "Friends", "Personal Growth", "Hobbies & Recreation", "Spirituality"};

    public static void drawLifeCircle(Graphics2D g2, double width, double height, int[] data) {
        // setup
        double circleDiameter;
        
	if (width < height) {
	    circleDiameter = width - 50;
	} else {
	    circleDiameter = height - 50;
	}

        g2.setStroke(new BasicStroke(2));
        g2.setColor(Color.LIGHT_GRAY);
        
	// circle
	g2.draw(new Ellipse2D.Double(CIRCLE_POSITION, CIRCLE_POSITION, circleDiameter, circleDiameter));
	
	// lines
	g2.draw(new Line2D.Double(CIRCLE_POSITION, CIRCLE_POSITION + circleDiameter / 2, CIRCLE_POSITION + circleDiameter, CIRCLE_POSITION + circleDiameter / 2)); // --
	g2.draw(new Line2D.Double(CIRCLE_POSITION + circleDiameter / 2, CIRCLE_POSITION, CIRCLE_POSITION + circleDiameter / 2, CIRCLE_POSITION + circleDiameter)); // |
	
        double x, y, inc;
        
	x = CIRCLE_POSITION + circleDiameter / 2 - (circleDiameter / 2) / Math.sqrt(2);
	y = CIRCLE_POSITION + circleDiameter / 2 + (circleDiameter / 2) / Math.sqrt(2);
        inc = (y - x) / 20;

	g2.draw(new Line2D.Double(x, y, y, x)); // /
	g2.draw(new Line2D.Double(x, x, y, y)); // \

	// points
        g2.setStroke(new BasicStroke(1));
        int i;
        for (i = 0; i <= 20; i++) {
            g2.fill(new Ellipse2D.Double(
                    CIRCLE_POSITION + i * circleDiameter / 20 - SMALL_POINTS_DIAMETER / 2 - 1, 
                    CIRCLE_POSITION + circleDiameter / 2 - SMALL_POINTS_DIAMETER / 2 - 1, 
                    SMALL_POINTS_DIAMETER, 
                    SMALL_POINTS_DIAMETER)
            ); // --
            
            g2.fill(new Ellipse2D.Double(
                    CIRCLE_POSITION + circleDiameter / 2 - SMALL_POINTS_DIAMETER / 2 - 1, 
                    CIRCLE_POSITION + i * circleDiameter / 20 - SMALL_POINTS_DIAMETER / 2 - 1, 
                    SMALL_POINTS_DIAMETER, 
                    SMALL_POINTS_DIAMETER)
            ); // |
            
            g2.fill(new Ellipse2D.Double(
                    y - i * inc - SMALL_POINTS_DIAMETER / 2 - 1, 
                    x + i * inc - SMALL_POINTS_DIAMETER / 2 - 1, 
                    SMALL_POINTS_DIAMETER, 
                    SMALL_POINTS_DIAMETER)
            ); // /

            g2.fill(new Ellipse2D.Double(
                    x + i * inc - SMALL_POINTS_DIAMETER / 2 - 1, 
                    x + i * inc - SMALL_POINTS_DIAMETER / 2 - 1, 
                    SMALL_POINTS_DIAMETER, 
                    SMALL_POINTS_DIAMETER)
            ); // \

        }
        
	// labels

        g2.setFont(new Font("Arial", Font.BOLD, 16));
        g2.setColor(Color.BLACK);
        
        g2.drawString(LABELS[0], (float) CIRCLE_POSITION - 5, (float) (CIRCLE_POSITION + circleDiameter / 2)); // health
        g2.drawString(LABELS[1], (float) x, (float) x); // career
        g2.drawString(LABELS[2], (float) (CIRCLE_POSITION + circleDiameter / 2), (float) CIRCLE_POSITION); // relations
        g2.drawString(LABELS[3], (float) y, (float) x); // finance
        g2.drawString(LABELS[4], (float) (CIRCLE_POSITION + circleDiameter) - 50, (float) (CIRCLE_POSITION + circleDiameter / 2)); // friends
        g2.drawString(LABELS[5], (float) y - 100, (float) y); // growth
        g2.drawString(LABELS[6], (float) (CIRCLE_POSITION + circleDiameter / 2 - 100), (float) (CIRCLE_POSITION + circleDiameter)); // recreations
        g2.drawString(LABELS[7], (float) x, (float) y); // spirituality
        
	// draw graph data
        g2.setStroke(new BasicStroke(3));
        if (data != null && data.length > 0) {
            g2.fill(new Ellipse2D.Double(
                    CIRCLE_POSITION + circleDiameter / 2 - circleDiameter / 20 * data[0] - BIG_POINTS_DIAMETER / 2 - 1,
                    CIRCLE_POSITION + circleDiameter / 2 - BIG_POINTS_DIAMETER / 2 - 1,
                    BIG_POINTS_DIAMETER, 
                    BIG_POINTS_DIAMETER)
            ); // health

            g2.fill(new Ellipse2D.Double(
                    x + inc * (10 - data[1]) - BIG_POINTS_DIAMETER / 2 - 1, 
                    x + inc * (10 - data[1]) - BIG_POINTS_DIAMETER / 2 - 1, 
                    BIG_POINTS_DIAMETER, 
                    BIG_POINTS_DIAMETER)
            ); // career
            
            g2.draw(new Line2D.Double(
                    CIRCLE_POSITION + circleDiameter / 2 - circleDiameter / 20 * data[0],
                    CIRCLE_POSITION + circleDiameter / 2,
                    x + inc * (10 - data[1]), 
                    x + inc * (10 - data[1])
            ));

            g2.fill(new Ellipse2D.Double(
                    CIRCLE_POSITION + circleDiameter / 2 - BIG_POINTS_DIAMETER / 2 - 1,
                    CIRCLE_POSITION + circleDiameter / 2 - circleDiameter / 20 * data[2] - BIG_POINTS_DIAMETER / 2 - 1,
                    BIG_POINTS_DIAMETER, 
                    BIG_POINTS_DIAMETER)
            ); // relations

            g2.draw(new Line2D.Double(
                    x + inc * (10 - data[1]), 
                    x + inc * (10 - data[1]),
                    CIRCLE_POSITION + circleDiameter / 2,
                    CIRCLE_POSITION + circleDiameter / 2 - circleDiameter / 20 * data[2]
            ));
            
            g2.fill(new Ellipse2D.Double(
                    y - inc * (10 - data[3]) - BIG_POINTS_DIAMETER / 2 - 1, 
                    x + inc * (10 - data[3]) - BIG_POINTS_DIAMETER / 2 - 1, 
                    BIG_POINTS_DIAMETER, 
                    BIG_POINTS_DIAMETER)
            ); // finance
            
            g2.draw(new Line2D.Double(
                    CIRCLE_POSITION + circleDiameter / 2,
                    CIRCLE_POSITION + circleDiameter / 2 - circleDiameter / 20 * data[2],
                    y - inc * (10 - data[3]), 
                    x + inc * (10 - data[3])
            ));

            g2.fill(new Ellipse2D.Double(
                    CIRCLE_POSITION + circleDiameter / 2 + circleDiameter / 20 * data[4] - BIG_POINTS_DIAMETER / 2 - 1,
                    CIRCLE_POSITION + circleDiameter / 2 - BIG_POINTS_DIAMETER / 2 - 1,
                    BIG_POINTS_DIAMETER, 
                    BIG_POINTS_DIAMETER)
            ); // friends

            g2.draw(new Line2D.Double(
                    y - inc * (10 - data[3]),
                    x + inc * (10 - data[3]),
                    CIRCLE_POSITION + circleDiameter / 2 + circleDiameter / 20 * data[4],
                    CIRCLE_POSITION + circleDiameter / 2
            ));

            g2.fill(new Ellipse2D.Double(
                    y - inc * (10 - data[5]) - BIG_POINTS_DIAMETER / 2 - 1, 
                    y - inc * (10 - data[5]) - BIG_POINTS_DIAMETER / 2 - 1, 
                    BIG_POINTS_DIAMETER, 
                    BIG_POINTS_DIAMETER)
            ); // growth

            g2.draw(new Line2D.Double(
                    CIRCLE_POSITION + circleDiameter / 2 + circleDiameter / 20 * data[4],
                    CIRCLE_POSITION + circleDiameter / 2,
                    y - inc * (10 - data[5]), 
                    y - inc * (10 - data[5])
            ));
            
            g2.fill(new Ellipse2D.Double(
                    CIRCLE_POSITION + circleDiameter / 2 - BIG_POINTS_DIAMETER / 2 - 1,
                    CIRCLE_POSITION + circleDiameter / 2 + circleDiameter / 20 * data[6] - BIG_POINTS_DIAMETER / 2 - 1,
                    BIG_POINTS_DIAMETER, 
                    BIG_POINTS_DIAMETER)
            ); // recreation
            
            g2.draw(new Line2D.Double(
                    y - inc * (10 - data[5]), 
                    y - inc * (10 - data[5]),
                    CIRCLE_POSITION + circleDiameter / 2,
                    CIRCLE_POSITION + circleDiameter / 2 + circleDiameter / 20 * data[6]
            ));
            
            g2.fill(new Ellipse2D.Double(
                    x + inc * (10 - data[7]) - BIG_POINTS_DIAMETER / 2 - 1, 
                    y - inc * (10 - data[7]) - BIG_POINTS_DIAMETER / 2 - 1, 
                    BIG_POINTS_DIAMETER, 
                    BIG_POINTS_DIAMETER)
            ); // spirituality

            g2.draw(new Line2D.Double(
                    CIRCLE_POSITION + circleDiameter / 2,
                    CIRCLE_POSITION + circleDiameter / 2 + circleDiameter / 20 * data[6],
                    x + inc * (10 - data[7]), 
                    y - inc * (10 - data[7]) 
            ));

            g2.draw(new Line2D.Double(
                    x + inc * (10 - data[7]), 
                    y - inc * (10 - data[7]),
                    CIRCLE_POSITION + circleDiameter / 2 - circleDiameter / 20 * data[0],
                    CIRCLE_POSITION + circleDiameter / 2
            ));
        }
    }
}
