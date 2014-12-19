/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lifecircle;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import javax.swing.*;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author qwer
 */
class MainFrame extends JFrame {
    private final int _width = 800;
    private final int _height = 600;

    public JPanel topPanel;
    public JPanel leftPanel;

    public JButton drawButton;
    public JButton resetButton;
    
    public JLabel healthLabel;
    public JLabel careerLabel;
    public JLabel relationsLabel;
    public JLabel financeLabel;
    public JLabel friendsLabel;
    public JLabel growthLabel;
    public JLabel recreationLabel;
    public JLabel spiritLabel;

    public JTextField healthTextField;
    public JTextField careerTextField;
    public JTextField relationsTextField;
    public JTextField financeTextField;
    public JTextField friendsTextField;
    public JTextField growthTextField;
    public JTextField recreationTextField;
    public JTextField spiritTextField;

    public LifeCircleArea lifeCircleArea;
    
    private JMenuBar menuBar;
    private JMenu fileMenu;
    private JMenuItem exportMenuItem;
    private JMenuItem aboutMenuItem;
    private JMenuItem quitMenuItem;

    public FileDialog fd;
    
    public MainFrame()
    {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screen = toolkit.getScreenSize();

	setSize(_width, _height);
	setTitle("Life Circle");
        setLocation(screen.width / 2 - _width / 2, screen.height / 2 - _height / 2);
        setResizable(false);

	gui();
    }

    public void gui()
    {
        // actions
        DrawButtonClickListener draw = new DrawButtonClickListener();
        ResetButtonClickListener reset = new ResetButtonClickListener();
        ExportMenuItemListener export = new ExportMenuItemListener();
        QuitMenuItemListener quit = new QuitMenuItemListener();
        AboutMenuItemListener about = new AboutMenuItemListener();
        
        // init
	topPanel = new JPanel();
	leftPanel = new JPanel();
	drawButton = new JButton("Draw");
	resetButton = new JButton("Reset");
        
	healthLabel = new JLabel("Health:");
	careerLabel = new JLabel("Career:");
	relationsLabel = new JLabel("Relations:");
	financeLabel = new JLabel("Finance:");
	friendsLabel = new JLabel("Friends:");
	growthLabel = new JLabel("Personal Growth:");
	recreationLabel = new JLabel("Hobbies & Recreation:");
	spiritLabel = new JLabel("Spirituality:");

	healthTextField = new JTextField("5", 5);
	careerTextField = new JTextField("5", 5);
	relationsTextField = new JTextField("5", 5);
	financeTextField = new JTextField("5", 5);
	friendsTextField = new JTextField("5", 5);
	growthTextField = new JTextField("5", 5);
	recreationTextField = new JTextField("5", 5);
	spiritTextField = new JTextField("5", 5);

	lifeCircleArea = new LifeCircleArea();

        menuBar = new JMenuBar();
        fileMenu = new JMenu("File");
        exportMenuItem = new JMenuItem("Export As Image File...");
        aboutMenuItem  = new JMenuItem("About...");
        quitMenuItem  = new JMenuItem("Quit");
        
        fd = new FileDialog(this, "New file", FileDialog.SAVE);
        
        // set listeners
        drawButton.addActionListener(draw);
        resetButton.addActionListener(reset);
        exportMenuItem.addActionListener(export);
        quitMenuItem.addActionListener(quit);
        aboutMenuItem.addActionListener(about);
        
	// compose
	GroupLayout layout = new GroupLayout(leftPanel);
	leftPanel.setLayout(layout);

	layout.setAutoCreateGaps(true);
	layout.setAutoCreateContainerGaps(true);

	layout.setHorizontalGroup(
	    layout.createSequentialGroup()
		.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
		    .addComponent(healthLabel)
		    .addComponent(careerLabel)
		    .addComponent(relationsLabel)
		    .addComponent(financeLabel)
		    .addComponent(friendsLabel)
		    .addComponent(growthLabel)
		    .addComponent(recreationLabel)
		    .addComponent(spiritLabel)
	    )
		.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
		    .addComponent(healthTextField)
		    .addComponent(careerTextField)
		    .addComponent(relationsTextField)
		    .addComponent(financeTextField)
		    .addComponent(friendsTextField)
		    .addComponent(growthTextField)
		    .addComponent(recreationTextField)
		    .addComponent(spiritTextField)
		    .addComponent(drawButton)
		    .addComponent(resetButton)
	    )
	);

	layout.setVerticalGroup(
	    layout.createSequentialGroup()
		.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
		    .addComponent(healthLabel)
		    .addComponent(healthTextField)
		)
		.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
		    .addComponent(careerLabel)
		    .addComponent(careerTextField)
		)
		.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
		    .addComponent(relationsLabel)
		    .addComponent(relationsTextField)
		)
		.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
		    .addComponent(financeLabel)
		    .addComponent(financeTextField)
		)
		.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
		    .addComponent(friendsLabel)
		    .addComponent(friendsTextField)
		)
		.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
		    .addComponent(growthLabel)
		    .addComponent(growthTextField)
		)
		.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
		    .addComponent(recreationLabel)
		    .addComponent(recreationTextField)
		)
		.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
		    .addComponent(spiritLabel)
		    .addComponent(spiritTextField)
		)
		.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
		    .addComponent(drawButton)
		)
		.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
		    .addComponent(resetButton)
		)
	);

	// add(topPanel, BorderLayout.NORTH);
        fileMenu.add(exportMenuItem);
        fileMenu.add(aboutMenuItem);
        fileMenu.add(new JSeparator());
        fileMenu.add(quitMenuItem);
        menuBar.add(fileMenu);
        setJMenuBar(menuBar);
	add(leftPanel, BorderLayout.WEST);
	add(lifeCircleArea, BorderLayout.CENTER);
	
    }    

    class DrawButtonClickListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {
            int[] data = new int[8];
            data[0] = Integer.parseInt(healthTextField.getText());
            data[1] = Integer.parseInt(careerTextField.getText());
            data[2] = Integer.parseInt(relationsTextField.getText());
            data[3] = Integer.parseInt(financeTextField.getText());
            data[4] = Integer.parseInt(friendsTextField.getText());
            data[5] = Integer.parseInt(growthTextField.getText());
            data[6] = Integer.parseInt(recreationTextField.getText());
            data[7] = Integer.parseInt(spiritTextField.getText());
            lifeCircleArea.setData(data);
            lifeCircleArea.update();
        }

    }

    class ResetButtonClickListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {
            lifeCircleArea.reset();
        }
    }
    

    class ExportMenuItemListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                // TODO: create image
                fd.setFile("Untitled.png");
                fd.setVisible(true);
                String filename = fd.getDirectory() + fd.getFile();
                
                BufferedImage circle = new BufferedImage(600, 600, BufferedImage.TYPE_INT_ARGB);
                
                Graphics2D g2 = circle.createGraphics();
                
                LifeCircleHelper.drawLifeCircle(g2, getWidth(), getHeight(), lifeCircleArea.getData());
                
                g2.dispose();
                
                File imageFile = new File(filename);
                ImageIO.write(circle, "png", imageFile);
            } catch (IOException ex) {
                Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    class QuitMenuItemListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }
    }

    MainFrame frame = this;
    
    class AboutMenuItemListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {
            JOptionPane.showMessageDialog(frame, "by timur560\n(c)2014", "About", JOptionPane.INFORMATION_MESSAGE);
        }
    }
        
}

