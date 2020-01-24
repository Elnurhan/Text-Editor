package text.editor;

import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.text.Element;
import javax.swing.text.JTextComponent;

public class TextLineNumber extends JFrame {
   private static JTextComponent component;
   private static JTextArea lines;
   private JScrollPane jsp;
   public TextLineNumber(JTextComponent component, JScrollPane scroll) {
      this.jsp = scroll;
      this.component = component;
      lines = new JTextArea("1");
      lines.setBackground(Color.LIGHT_GRAY);
      lines.setEditable(false);
      //  Code to implement line numbers inside the JTextArea
      component.getDocument().addDocumentListener(new DocumentListener() {
         public String getText() {
            int caretPosition = component.getDocument().getLength();
            Element root = component.getDocument().getDefaultRootElement();
            String text = "1" + System.getProperty("line.separator");
               for(int i = 2; i < root.getElementIndex(caretPosition) + 2; i++) {
                  text += i + System.getProperty("line.separator");
               }
            return text;
         }
         @Override
         public void changedUpdate(DocumentEvent de) {
            lines.setText(getText());
         }
         @Override
         public void insertUpdate(DocumentEvent de) {
            lines.setText(getText());
         }
         @Override
         public void removeUpdate(DocumentEvent de) {
            lines.setText(getText());
         }
      });
      jsp.getViewport().add(component);
      jsp.setRowHeaderView(lines);
      add(jsp);
   }
}