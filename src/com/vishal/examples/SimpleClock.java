package com.vishal.examples;


import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.TimeUnit;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class SimpleClock  {
	JLabel jlab;
	long start;
	JFrame jframe;
	private Timer timer;
    private int count;
    public static void main(String[] args) {
        new SimpleClock();
    }

    public SimpleClock() {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
                }

                 jframe = new JFrame("Vishal Countdown App");
                 jframe.setResizable(false);
                 
            	jframe.setAlwaysOnTop (true);
        		jframe.setLayout(new FlowLayout());                
        		jframe.setSize(280, 100);
        		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        		JButton start = new JButton("Start");
        		JButton stop = new JButton("Stop");
        		start.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						callAction(e);
						
					}
				});
        		stop.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						callAction(e);
						
					}
				});
        		jframe.add(start);
        		jframe.add(stop);
        		jlab = new JLabel("Press Start to begin timing.", SwingConstants.CENTER);
        		jframe.add(jlab);
        		jframe.setVisible(true);
            }
        });
    }

    public class TestPane extends JPanel {

        


        public TestPane() {
           
          
           
            timer = new Timer(500, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    count++;
                    if (count < 100000) {
                        jlab.setText(Integer.toString(count));
                        try {
							TimeUnit.SECONDS.sleep(1);
						} catch (InterruptedException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
                    } else {
                        ((Timer)(e.getSource())).stop();
                    }
                }
            });
            timer.setInitialDelay(0);
            timer.start();
        }

      
    }
    
	public void callAction(ActionEvent ae) {
		if (ae.getActionCommand().equals("Start")) {
			jframe.add(new TestPane());
            jframe.pack();
			start = ae.getWhen();			
		} else {
			long result = ((ae.getWhen()) - start) / 1000;
			jlab.setText("" + result + " seconds.");
			timer.stop();
		}

	}
}
