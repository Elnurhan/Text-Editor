/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package text.editor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;
import javax.swing.event.UndoableEditEvent;
import javax.swing.event.UndoableEditListener;
import javax.swing.undo.CannotRedoException;
import javax.swing.undo.CannotUndoException;
import static text.editor.Window.*;

/**
 *
 * @author elnur
 * Класс реализует отслеживание действий.
 */
public class Actions {
    public void actionsListen() {
        file_new.addActionListener((ActionEvent e) -> {
            text.setText(""); 
        });
        file_open.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae){
                j = new JFileChooser("c:");
                int r = j.showOpenDialog(null);
                // Если пользователь выбрал файл
                if (r == JFileChooser.APPROVE_OPTION) {
                    File file = new File(j.getSelectedFile().getAbsolutePath());
                    try {
                        String s1 = "", sl = "";
                        FileReader fr = new FileReader(file);
                        BufferedReader br = new BufferedReader(fr);
                        // Читаем текст с файла, записываем его в sl
                        sl = br.readLine();
                        while ((s1 = br.readLine()) != null) {
                            sl += "\n" + s1;
                        }
                        Window.text.setText(sl);
                    } catch (IOException er) {
                        JOptionPane.showMessageDialog(f, er.getMessage());
                }
            }
        }
        });
        file_save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                j = new JFileChooser("c:");
                int r = j.showSaveDialog(null);
               
                if (r == JFileChooser.APPROVE_OPTION) {
                    File file = new File(j.getSelectedFile().getAbsolutePath());
                   
                    try {
                        FileWriter fw = new FileWriter(file);
                        BufferedWriter bw = new BufferedWriter(fw);
                       
                        bw.write(text.getText());
                       
                        bw.flush();
                        bw.close();
                    } catch (IOException er) {
                        JOptionPane.showMessageDialog(f, er.getMessage());
                    }
                }
            }
       
        });
        file_print.addActionListener((ActionEvent e) -> {
            try {
                text.print();
            } catch (PrinterException ex) {
                Logger.getLogger(Window.class.getName()).log(Level.SEVERE, null, ex);
            }   
        });
        edit_cut.addActionListener((ActionEvent e) -> {
            text.cut();
        });
        edit_paste.addActionListener((ActionEvent e) -> {
            text.paste();
       });
        edit_copy.addActionListener((ActionEvent e) -> {
            text.copy();
        });
        text.getDocument().addUndoableEditListener(
            new UndoableEditListener(){
                public void undoableEditHappened(UndoableEditEvent e) {
                    undoManager.addEdit(e.getEdit());
                }
            });
        edit_undo.addActionListener((ActionEvent e) -> {
           try {
               undoManager.undo();
           } catch (CannotUndoException er) {}
        });
        edit_redo.addActionListener((ActionEvent e) -> {
           try {
               undoManager.redo();
           } catch (CannotRedoException er) {}
        });
        text.getActionMap().put("Undo", new AbstractAction("Undo") {
            public void actionPerformed(ActionEvent evt) {
                try {
                    if (undoManager.canUndo()) {
                            undoManager.undo();
                    }
            } catch (CannotUndoException e) {
            }
        }
        });
        text.getInputMap().put(KeyStroke.getKeyStroke("control Z"), "Undo");
        text.getActionMap().put("Redo", new AbstractAction("Redo") {
        public void actionPerformed(ActionEvent evt) {
            try {
            if (undoManager.canRedo()) {
                undoManager.redo();
            }
            } catch (CannotRedoException e) {
            }
        }
    });
    text.getInputMap().put(KeyStroke.getKeyStroke("control Y"), "Redo");
    }
}
