import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class MyFrame extends JFrame{
	JButton button;
	JButton button2;
	JTextField text_field;
	public static int toint(String t) {
		return Integer.parseInt(t);

	}

	public static String toString(int i) {

		return Integer.toString(i);

	}

	public static int fibonacci(int n) {
		if (n > 1) {
			return fibonacci(n - 1) + fibonacci(n - 2);
		} else {
			return n;
		}
	}
	MyFrame(){
		Path p = Paths.get("fibonacci_sequence.txt");
		if(Files.exists(p)) {
				System.out.println(p +" already exists");
			}else {
				try {
					Path path = Files.createFile(p);
					System.out.println(path.toString() +" was created");
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		this.setSize(400, 400);
		this.setTitle("FIBONACCI SEQUENCE");
		JLabel label = new JLabel("enter the index number of the fibonacci sequence:");
		label.setFont(new Font(null,Font.CENTER_BASELINE,16));
		label.setForeground(Color.magenta);
		this.add(label);
		text_field = new JTextField();
		text_field.setBackground(Color.black);
		text_field.setFont(new Font(null,Font.ITALIC,44));
		text_field.setForeground(Color.cyan);
		this.add(text_field);
		this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
		
		button = new JButton("confirm");
		this.add(button);
		
		
		button2 = new JButton("see previous answers");
		this.add(button2);
		button2.addActionListener( (e) -> {
				try {
					List<String> list = Files.readAllLines(p);
					for(String s:list) {
						System.out.println(s);
					}
				} catch (IOException e1) {
					
					e1.printStackTrace();
				
				}
		});
		this.setLayout(new GridLayout(4,1));
		
		button.addActionListener((e) -> {
				String content = toString(fibonacci(toint(text_field.getText())))+"\n";
				try {
					Files.write(p, content.getBytes(), StandardOpenOption.APPEND);
				} catch (IOException e1) {
					
					e1.printStackTrace();
				}
				
			
			
		});
		this.setVisible(true);
		
		
	}
}
