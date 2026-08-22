package Expo.internal.restore;

import java.awt.Component;
import java.awt.Container;
import java.awt.Window;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.swing.AbstractButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListModel;
import javax.swing.border.TitledBorder;
import javax.swing.text.JTextComponent;


public final class ExpoGuiProbe {

   private ExpoGuiProbe() {
   }

   public static String dump(Window w) {
      StringBuilder b = new StringBuilder();

      if (w == null) {
         return "";
      }

      String root = "window[0]";
      emit(b, root + "#name", w.getName());

      if (w instanceof java.awt.Frame) {
         emit(b, root + "#title", ((java.awt.Frame)w).getTitle());
      }

      walk(b, w, root);
      return b.toString();
   }

   private static void walk(StringBuilder b, Container c, String path) {
      Component[] kids;

      try {
         kids = c.getComponents();
      } catch (Throwable t) {
         return;
      }

      for (int i = 0; i < kids.length; i++) {
         Component k = kids[i];

         if (k == null) {
            continue;
         }

         String p = path + "/" + i;
         describe(b, k, p);

         if (k instanceof Container) {
            walk(b, (Container)k, p);
         }
      }
   }

   private static void describe(StringBuilder b, Component c, String p) {
      emit(b, p + "#name", c.getName());

      String text = null;

      if (c instanceof JLabel) {
         text = ((JLabel)c).getText();
      } else if (c instanceof AbstractButton) {
         text = ((AbstractButton)c).getText();
         emit(b, p + "#actionCommand", ((AbstractButton)c).getActionCommand());
      } else if (c instanceof JTextComponent) {
         text = ((JTextComponent)c).getText();
      }

      emit(b, p + "#text", text);

      if (c instanceof JComponent) {
         try {
            javax.swing.border.Border border = ((JComponent)c).getBorder();

            if (border instanceof TitledBorder) {
               emit(b, p + "#borderTitle", ((TitledBorder)border).getTitle());
            }
         } catch (Throwable t) {
         }
      }

      if (c instanceof JList) {
         items(b, p, ((JList<?>)c).getModel());
      } else if (c instanceof JComboBox) {
         JComboBox<?> combo = (JComboBox<?>)c;

         for (int i = 0; i < combo.getItemCount(); i++) {
            emit(b, p + "#item" + i, String.valueOf(combo.getItemAt(i)));
         }
      }

      if (c instanceof javax.swing.JTabbedPane) {
         javax.swing.JTabbedPane t = (javax.swing.JTabbedPane)c;

         for (int i = 0; i < t.getTabCount(); i++) {
            emit(b, p + "#item" + i, t.getTitleAt(i));
         }
      }
   }

   private static void items(StringBuilder b, String p, ListModel<?> m) {
      if (m == null) {
         return;
      }

      int n = Math.min(m.getSize(), 400);

      for (int i = 0; i < n; i++) {
         Object v = m.getElementAt(i);
         emit(b, p + "#item" + i, v == null ? null : String.valueOf(v));
      }
   }

   private static void emit(StringBuilder b, String key, String value) {
      if (value == null || value.length() == 0) {
         return;
      }

      Map<String, Object> one = new LinkedHashMap<String, Object>();
      one.put(key, value);
      String json = ExpoGuiJson.write(one).trim();
      int q = json.indexOf(": ");
      b.append(key).append('\t').append(json.substring(q + 2, json.length() - 1).trim()).append('\n');
   }
}
