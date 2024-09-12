package cookieclicker;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.Timer;

public class Main {
	
	JLabel counterLabel, perSecLabel;
	JButton button1, button2, button3, button4;
	int cookieCounter, timerSpeed, cursorNumber, cursorPrice, grandpaNumber, grandpaPrice, farmNumber, farmPrice, minesNumber, minesPrice;
	double perSecond;
	boolean timerOn, grandpaUnlocked, farmUnlocked, minesUnlocked;
	Font font1, font2;
	CookieHandler cHandler = new CookieHandler();
	Timer timer;
	JTextArea messageText;
	MouseHandler mHandler = new MouseHandler();

	public static void main(String[] args) {
		
		new Main();
	}
	
	public Main() {
		
		
		timerOn = false;
		perSecond = 0;
		
		cookieCounter = 0;
		
		cursorNumber = 0;
		cursorPrice = 10;
		grandpaNumber = 0;
		grandpaPrice = 100;
		grandpaUnlocked = false;
		farmNumber = 0;
		farmPrice = 1000;
		farmUnlocked = false;
		minesNumber = 0;
		minesPrice = 5000;
		minesUnlocked = false;
		
		createFont();
		createUI();
	}
	public void createFont() {
		
		font1 = new Font("Comic Sans MS", Font.PLAIN, 32);
		font2 = new Font("Comic Sans MS", Font.PLAIN, 16);
		
	}
	public void createUI() {
		
		JFrame window = new JFrame();
		window.setSize(800, 600);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.getContentPane().setBackground(Color.white);
		window.setLayout(null);
/////////////////////////////////////////////////////////////		
		JPanel cookiePanel = new JPanel();
		cookiePanel.setBounds(100, 220, 200, 200);
		cookiePanel.setBackground(Color.white);
		window.add(cookiePanel);
/////////////////////////////////////////////////////////////		
		ImageIcon cookie = new ImageIcon(getClass().getClassLoader().getResource("pixil-frame-0.png"));
		
		JButton cookieButton = new JButton();
		cookieButton.setBackground(Color.white);
		cookieButton.setFocusPainted(false);
		cookieButton.setBorder(null);
		cookieButton.setIcon(cookie);
		
		// Click button, cHandler gets called
		cookieButton.addActionListener(cHandler);
		cookieButton.setActionCommand("cookie");
		cookiePanel.add(cookieButton);
/////////////////////////////////////////////////////////////
		JPanel counterPanel = new JPanel();
		counterPanel.setBounds(100, 100, 200, 100);
		counterPanel.setBackground(Color.white);
		counterPanel.setLayout(new GridLayout(2,1));
		window.add(counterPanel);
/////////////////////////////////////////////////////////////		
		counterLabel = new JLabel(cookieCounter + " cookies");
		counterLabel.setForeground(Color.black);
		counterPanel.add(counterLabel);
		counterLabel.setFont(font1);
		
		perSecLabel = new JLabel();
		perSecLabel.setForeground(Color.black);
		perSecLabel.setFont(font2);
		counterPanel.add(perSecLabel);
/////////////////////////////////////////////////////////////
		JPanel itemPanel = new JPanel();
		itemPanel.setBounds(500, 170, 250, 250);
		itemPanel.setBackground(Color.gray);
		itemPanel.setLayout(new GridLayout(4,1));
		window.add(itemPanel);
		
		button1 = new JButton("Cursor");
		button1.setFont(font1);
		button1.setFocusPainted(false);
		button1.addActionListener(cHandler);
		button1.setActionCommand("Cursor");
		itemPanel.add(button1);
		button1.addMouseListener(mHandler);
		
		button2 = new JButton("?");
		button2.setFont(font1);
		button2.setFocusPainted(false);
		button2.addActionListener(cHandler);
		button2.setActionCommand("Grandpa");
		itemPanel.add(button2);
		button2.addMouseListener(mHandler);
		
		button3 = new JButton("?");
		button3.setFont(font1);
		button3.setFocusPainted(false);
		button3.addActionListener(cHandler);
		button3.setActionCommand("Farm");
		itemPanel.add(button3);
		button3.addMouseListener(mHandler);
		
		button4 = new JButton("?");
		button4.setFont(font1);
		button4.setFocusPainted(false);
		button4.addActionListener(cHandler);
		button4.setActionCommand("Mines");
		itemPanel.add(button4);
		button4.addMouseListener(mHandler);
/////////////////////////////////////////////////////////////
		
		JPanel messagePanel = new JPanel();
		messagePanel.setBounds(500, 70, 250, 150);
		messagePanel.setBackground(Color.white);
		window.add(messagePanel);
		
		messageText = new JTextArea();
		messageText.setBounds(500, 70, 250, 150);
		messageText.setForeground(Color.black);
		messageText.setBackground(Color.white);
		messageText.setFont(font2);
		messageText.setLineWrap(true);
		messageText.setWrapStyleWord(true);
		messageText.setEditable(false);
		messagePanel.add(messageText);
/////////////////////////////////////////////////////////////		
		
		
		
		window.setVisible(true);
	}
//___________________________________________________________		
	public void setTimer() {
		timer = new Timer(timerSpeed, new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				cookieCounter++;
				counterLabel.setText(cookieCounter + " cookies");
				
				if(grandpaUnlocked == false) {
					if (cookieCounter >= 100) {
						grandpaUnlocked = true;
						button2.setText("Grandpa (" + grandpaNumber + ")");
					}
				}
				if(farmUnlocked == false) {
					if (cookieCounter >= 1000) {
						farmUnlocked = true;
						button3.setText("Farm (" + farmNumber + ")");
					}
				}
				if(minesUnlocked == false) {
					if (cookieCounter >= 5000) {
						minesUnlocked = true;
						button4.setText("Mines (" + minesNumber + ")");
					}
				}
			}
		});
	}
//___________________________________________________________	
	public void timerUpdate() {
		
		if(timerOn == false) {
			timerOn = true;
		}
		else if (timerOn) {
			timer.stop();
		}
		
		double speed = 1/perSecond*1000;
		timerSpeed = (int)Math.round(speed);
		
		String str = String.format("%.1f", perSecond);
		perSecLabel.setText("persecond: "+ str);
		
		setTimer();
		timer.start();
	}
//___________________________________________________________	
	public class CookieHandler implements ActionListener{
		
		public void actionPerformed(ActionEvent event) {
			
			String action = event.getActionCommand();
			
			switch(action) {
			case "cookie":
				cookieCounter++;
				counterLabel.setText(cookieCounter + " cookies");
				break;
				
			case "Cursor":
				if (cookieCounter >= cursorPrice) {
					cookieCounter -= cursorPrice;
					cursorPrice *= 1.2;
					counterLabel.setText(cookieCounter + " cookies");
					
					cursorNumber++;
					button1.setText("Cursor (" + cursorNumber + ")");
					messageText.setText("Cursor\n[Price: " + cursorPrice +"]\nAutoclick once every 10 seconds");
					perSecond += 0.1;
					timerUpdate();
				}	
				else {
					messageText.setText("You cant afford this!");
				}
				break;
			case "Grandpa":
				if (cookieCounter >= grandpaPrice) {
					cookieCounter -= grandpaPrice;
					grandpaPrice *= 1.1;
					counterLabel.setText(cookieCounter + " cookies");
					
					grandpaNumber++;
					button2.setText("Grandpa (" + grandpaNumber + ")");
					messageText.setText("Grandpa\n[Price: " + grandpaPrice + "]\nGrandpa bakes 1 cookie per second");
					perSecond += 1;
					timerUpdate();
				}	
				else {
					messageText.setText("You cant afford this!");
				}
				break;
			case "Farm":
				if (cookieCounter >= farmPrice) {
					cookieCounter -= farmPrice;
					farmPrice *= 1.1;
					counterLabel.setText(cookieCounter + " cookies");
					
					farmNumber++;
					button3.setText("Farm (" + farmNumber + ")");
					messageText.setText("Farm\n[Price: " + farmPrice + "]\nCookies will grow at 10 cookies per second");
					perSecond += 10;
					timerUpdate();
				}	
				else {
					messageText.setText("You cant afford this!");
				}
				break;
			case "Mines":
				if (cookieCounter >= minesPrice) {
					cookieCounter -= minesPrice;
					minesPrice *= 1.05;
					counterLabel.setText(cookieCounter + " cookies");
					
					minesNumber++;
					button4.setText("Mines (" + minesNumber + ")");
					messageText.setText("Mines\n[Price: " + minesPrice + "]\nMines for cookies at 100 cookies per second!");
					perSecond += 100;
					timerUpdate();
				}	
				else {
					messageText.setText("You cant afford this!");
				}
				break;
			}
		}
	}
//___________________________________________________________	
	public class MouseHandler implements MouseListener{
		
		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		@Override
		public void mouseEntered(MouseEvent e) {
			
			JButton button = (JButton)e.getSource();
			
			// Cursor
			if(button == button1) {
				messageText.setText("Cursor\n[Price: " + cursorPrice +"]\nAutoclick once every 10 seconds");
			}
			else if (button == button2) {
				if (grandpaUnlocked == false) {
					messageText.setText("This item is currently locked");
				}
				else {
					messageText.setText("Grandpa\n[Price: " + grandpaPrice + "]\nGrandpa bakes 1 cookie per second");
				}
				
				
			}
			else if (button == button3) {
				if (farmUnlocked == false) {
					messageText.setText("This item is currently locked");
				}
				else {
					messageText.setText("Farm\n[Price: " + farmPrice + "]\nCookies will grow at 10 cookies per second");
				}
			}
			else if (button == button4) {
				if (minesUnlocked == false) {
					messageText.setText("This item is currently locked");
				}
				else {
					messageText.setText("Mines\n[Price: " + minesPrice + "]\nMines for cookies at 100 cookies per second!");
				}
			}
			
		}
		@Override
		public void mouseExited(MouseEvent e) {
			
			messageText.setText(null);
			
		}
	}
}
