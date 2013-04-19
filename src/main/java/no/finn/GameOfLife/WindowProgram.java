package no.finn.GameOfLife;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created with IntelliJ IDEA.
 * User: javalons
 * Date: 04/04/13
 * Time: 16:33
 * To change this template use File | Settings | File Templates.
 */
public class WindowProgram {

    private JButton b;
    private JTextField t;

    public static void main(String[] args) throws InterruptedException {
        new WindowProgram();
    }

    public WindowProgram() throws InterruptedException {
        JFrame myWindow = new JFrame("This is my window");

        //myWindow.setSize(800, 800);
        //myWindow.getContentPane().setLayout(new BorderLayout());
        myWindow.getContentPane().setLayout(new FlowLayout());

        b = new JButton("Load");
        t = new JTextField(60);
        myWindow.getContentPane().add(b);
        myWindow.getContentPane().add(t);

        b.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                t.setText ("Hello World");
            }
        });

        //GameOfLife model = new GameOfLife(5);
        GameOfLife model = new GameOfLife("/Users/javalons/Documents/GameOfLife/gosperglidergun_105.lif");
        //GameOfLife model = new GameOfLife("/Users/javalons/Documents/GameOfLife/Glider.lif.txt");
        JTable table = new JTable(model);

        table.setDefaultRenderer(Integer.class, new MyRender());
        table.setShowGrid(true);
        table.setGridColor(Color.black);

        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setMaxWidth(5);
        }

        /*table.getColumnModel().getColumn(0).setMaxWidth(5);
        table.getColumnModel().getColumn(1).setMaxWidth(5);
        table.getColumnModel().getColumn(2).setMaxWidth(5);
        table.getColumnModel().getColumn(0).setMinWidth(5);
        table.getColumnModel().getColumn(1).setMinWidth(5);
        table.getColumnModel().getColumn(2).setMinWidth(5);*/



        /*DefaultTableModel model = new DefaultTableModel();
        JTable tabla = new JTable(model);
        model.addColumn("etiqueta columna 1");
        model.addColumn("etiqueta columna 2");
        Object [] fila = new Object[2];
        fila[0] = "dato columna 1";
        fila[1] = "dato columna 3";
        fila[0] = "dato columna 1";
        fila[1] = "dato columna 3";

        model.addRow(fila); // Añade una fila al final
        model.addRow(fila); // Añade una fila al final
        model.addRow(fila); // Añade una fila al final
        model.addRow(fila); // Añade una fila al final
        model.addRow(fila); // Añade una fila al final
        model.addRow(fila); // Añade una fila al final*/



        /*model.setValueAt(1, 0, 1);
        model.setValueAt(1, 2, 1);
        model.setValueAt(1, 3, 1);
        model.setValueAt(1, 3, 2);
        model.setValueAt(1, 2, 2);
        model.setValueAt(1, 4, 1);
        model.setValueAt(1, 4, 2);*/

        //modelo.removeRow (0); // Borra la primera fila
        myWindow.getContentPane().add(table);

        myWindow.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        myWindow.pack();
        myWindow.setVisible(true);

        do {
            Thread.sleep(50);
            model.nextGeneration();
            table.updateUI();
        } while (true);
    }

    public class buttonAction implements ActionListener {

        public void actionPerformed (ActionEvent e) {
            t.setText("Hello world");
        }
    }

}
