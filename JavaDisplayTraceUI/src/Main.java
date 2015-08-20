import java.awt.BorderLayout;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Main extends JFrame {
private static final long serialVersionUID = 1L;
JTable table;
DefaultTableModel model;

public Main(String title, String source) {
super(title);
table = new JTable();
JScrollPane scroll = new JScrollPane(table);

InputStream is;
try {
File f = new File(source);
is = new FileInputStream(f);

insertData(is);
} catch (IOException ioe) 
{
JOptionPane.showMessageDialog(this, ioe, "Error reading data", JOptionPane.ERROR_MESSAGE);
}

getContentPane().add(scroll, BorderLayout.CENTER);
pack();
}

void insertData(InputStream is) {
Scanner scan = new Scanner(is);
String[] array;
int j = 0;
while (scan.hasNextLine()) {
String line = scan.nextLine();
if (j == 0) {
array = line.split(",");
if (array.length < 2)
array = line.split("\t");
String[] colNames = array;
model = new DefaultTableModel(colNames, 0);
} else {
if (line.indexOf(",") > -1)
array = line.split(",");
else
array = line.split("\t");
Object[] data = new Object[array.length];
for (int i = 0; i < array.length; i++)
data[i] = array[i];

model.addRow(data);
}

j++;
}
table.setModel(model);
scan.close();
}

public static void main(String args[]) {
	
	/*Scanner input = new Scanner(System.in);
	System.out.println("Enter the path of the trace file");
	String filepath = input.next();*/
Main frame = new Main("LTTNG Trace", "/home/suchita/Desktop/sample2.csv");
frame.setVisible(true);
frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
}
}
