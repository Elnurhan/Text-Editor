/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package text.editor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.undo.UndoManager;
import javax.swing.text.JTextComponent;


/**
 *
 * @author elnur
 */
public class Window extends JFrame implements ActionListener {
    static JTextComponent text;
    static JFrame f;
    static JFileChooser j;
    static UndoManager undoManager;
    
    static JMenuBar mb = new JMenuBar();
       
    static JMenu file_menu = new JMenu("Файл");
       
    static JMenuItem file_new = new JMenuItem("Новый");
    static JMenuItem file_open = new JMenuItem("Открыть");
    static JMenuItem file_save = new JMenuItem("Сохранить");
    static JMenuItem file_print = new JMenuItem("Печать");
    
    static JMenu edit_menu = new JMenu("Изменить");
       
    static JMenuItem edit_cut = new JMenuItem("Вырезать");
    static JMenuItem edit_copy = new JMenuItem("Копировать");
    static JMenuItem edit_paste = new JMenuItem("Вставить");
    static JMenuItem edit_undo = new JMenuItem("Назад");
    static JMenuItem edit_redo = new JMenuItem("Вперёд");
    
    public Window() {
        f = new JFrame("Text Editor");
       
        text = new JTextArea();
        JScrollPane scroll = new JScrollPane(text);
       
        undoManager = new UndoManager();
       
        file_menu.add(file_new);
        file_menu.add(file_open);
        file_menu.add(file_save);
        file_menu.add(file_print);
       
        edit_menu.add(edit_cut);
        edit_menu.add(edit_copy);
        edit_menu.add(edit_paste);
        edit_menu.add(edit_undo);
        edit_menu.add(edit_redo);
       
        mb.add(file_menu);
        mb.add(edit_menu);
       
        Actions action = new Actions();
        action.actionsListen();
        
        new LinePainter(text);
        new TextLineNumber(text, scroll);
                
        f.setJMenuBar(mb);
        f.getContentPane().add(scroll);
        f.setSize(500, 500); 
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.show();
    }

    @Override
    public void actionPerformed(ActionEvent ae) {}
}
