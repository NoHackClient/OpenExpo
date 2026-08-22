package Expo.internal.restore;

import Expo.command.ExpoCommands;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.text.AttributeSet;
import javax.swing.text.Document;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

public final class ExpoGuiTerminal extends JPanel {
   private static final long serialVersionUID = 1L;

   private static final Map<Character, Color> COLORS = new HashMap<Character, Color>();

   static {
      COLORS.put(Character.valueOf('0'), new Color(0, 0, 0));
      COLORS.put(Character.valueOf('1'), new Color(0, 0, 170));
      COLORS.put(Character.valueOf('2'), new Color(0, 170, 0));
      COLORS.put(Character.valueOf('3'), new Color(0, 170, 170));
      COLORS.put(Character.valueOf('4'), new Color(170, 0, 0));
      COLORS.put(Character.valueOf('5'), new Color(170, 0, 170));
      COLORS.put(Character.valueOf('6'), new Color(255, 170, 0));
      COLORS.put(Character.valueOf('7'), new Color(170, 170, 170));
      COLORS.put(Character.valueOf('8'), new Color(85, 85, 85));
      COLORS.put(Character.valueOf('9'), new Color(85, 85, 255));
      COLORS.put(Character.valueOf('a'), new Color(85, 255, 85));
      COLORS.put(Character.valueOf('b'), new Color(85, 255, 255));
      COLORS.put(Character.valueOf('c'), new Color(255, 85, 85));
      COLORS.put(Character.valueOf('d'), new Color(255, 85, 255));
      COLORS.put(Character.valueOf('e'), new Color(255, 255, 85));
      COLORS.put(Character.valueOf('f'), new Color(255, 255, 255));
   }

   private final JTextPane pane = new JTextPane();
   private final JScrollPane scroll;
   private final JTextField input = new JTextField();
   private final List<String> history = new ArrayList<String>();

   private int historyIndex;
   private boolean autoScroll = true;
   private int consumed;

   public ExpoGuiTerminal() {
      super(new BorderLayout(5, 5));
      this.setBorder(BorderFactory.createEmptyBorder(ExpoGuiText.PAD, ExpoGuiText.PAD,
                                                     ExpoGuiText.PAD, ExpoGuiText.PAD));

      JLabel head = new JLabel(ExpoGuiText.TAB_TERMINAL, JLabel.CENTER);
      head.setFont(ExpoGuiText.font(Font.BOLD, 16));

      this.pane.setEditable(false);
      this.pane.setFont(ExpoGuiText.font(Font.PLAIN, 13));
      this.pane.setBackground(new Color(0x1E1E1E));
      this.pane.setForeground(Color.WHITE);
      this.pane.setCaretColor(Color.WHITE);

      this.scroll = new JScrollPane(this.pane);
      this.scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
      this.scroll.setBorder(BorderFactory.createEmptyBorder());

      this.input.setFont(ExpoGuiText.font(Font.PLAIN, 13));
      this.input.setBorder(BorderFactory.createTitledBorder(ExpoGuiText.TERMINAL_INPUT_BORDER));

      this.add(head, BorderLayout.NORTH);
      this.add(this.scroll, BorderLayout.CENTER);
      this.add(this.input, BorderLayout.SOUTH);

      this.input.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            ExpoGuiTerminal.this.submit();
         }
      });

      this.input.addKeyListener(new KeyAdapter() {
         public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_UP) {
               ExpoGuiTerminal.this.recall(-1);
            } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
               ExpoGuiTerminal.this.recall(1);
            }
         }
      });

      JScrollBar bar = this.scroll.getVerticalScrollBar();
      bar.addAdjustmentListener(new java.awt.event.AdjustmentListener() {
         public void adjustmentValueChanged(java.awt.event.AdjustmentEvent e) {
            JScrollBar b = (JScrollBar)e.getAdjustable();
            ExpoGuiTerminal.this.autoScroll = b.getValue() + b.getVisibleAmount() >= b.getMaximum() - 24;
         }
      });

      this.append("§7[Expo] terminal ready. Type §f.help§7 for the command list.\n");
   }

   public void focusInput() {
      this.input.requestFocusInWindow();
   }

   public void pump() {
      List<String> src = ExpoGuiData.transcript();

      if (src == null) {
         return;
      }

      try {
         int size = src.size();

         if (size < this.consumed) {
            this.consumed = 0;
         }

         while (this.consumed < size) {
            String line = src.get(this.consumed++);

            if (line != null) {
               this.append(line + "\n");
            }
         }
      } catch (Throwable t) {
      }
   }

   public void append(String text) {
      if (text == null) {
         return;
      }

      try {
         Document doc = this.pane.getStyledDocument();
         SimpleAttributeSet attr = new SimpleAttributeSet();
         StyleConstants.setForeground(attr, Color.WHITE);
         StringBuilder run = new StringBuilder();

         for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);

            if ((c == '§' || c == '&') && i + 1 < text.length()) {
               char code = Character.toLowerCase(text.charAt(i + 1));
               Color col = COLORS.get(Character.valueOf(code));

               if (col != null || code == 'r') {
                  this.flush(doc, run, attr);
                  attr = new SimpleAttributeSet();
                  StyleConstants.setForeground(attr, col == null ? Color.WHITE : col);
                  i++;
                  continue;
               }

               if (code >= 'k' && code <= 'o') {
                  i++;
                  continue;
               }
            }

            run.append(c);
         }

         this.flush(doc, run, attr);

         if (this.autoScroll) {
            this.pane.setCaretPosition(doc.getLength());
         }
      } catch (Throwable t) {
      }
   }

   private void flush(Document doc, StringBuilder run, AttributeSet attr) {
      if (run.length() == 0) {
         return;
      }

      try {
         doc.insertString(doc.getLength(), run.toString(), attr);
      } catch (Throwable t) {
      }

      run.setLength(0);
   }

   private void submit() {
      String line = this.input.getText();

      if (line == null || line.trim().length() == 0) {
         return;
      }

      line = line.trim();
      this.input.setText("");
      this.history.add(line);
      this.historyIndex = this.history.size();
      this.append("§8> §f" + line + "\n");

      if (line.charAt(0) != ExpoCommands.PREFIX) {
         line = ExpoCommands.PREFIX + line;
      }

      if (!ExpoGuiData.dispatch(line)) {
         this.append("§cNot a command: §f" + line + "\n");
      }

      this.pump();
   }

   private void recall(int delta) {
      if (this.history.isEmpty()) {
         return;
      }

      int next = this.historyIndex + delta;

      if (next < 0) {
         next = 0;
      }

      if (next >= this.history.size()) {
         this.historyIndex = this.history.size();
         this.input.setText("");
         return;
      }

      this.historyIndex = next;
      this.input.setText(this.history.get(next));
   }
}
