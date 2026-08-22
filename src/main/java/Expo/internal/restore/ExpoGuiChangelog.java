package Expo.internal.restore;

import java.awt.Component;
import java.awt.Font;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

public final class ExpoGuiChangelog extends JPanel {
   private static final long serialVersionUID = 1L;

   public static final List<String> ENTRIES = new ArrayList<String>(Arrays.asList(
      "OpenSource by NoHackClient#1337"
   ));

   public ExpoGuiChangelog() {
      this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
      this.setBorder(BorderFactory.createEmptyBorder(ExpoGuiText.PAD, ExpoGuiText.PAD,
                                                     ExpoGuiText.PAD, ExpoGuiText.PAD));

      JLabel head = new JLabel(ExpoGuiText.CHANGELOG_HEADER, JLabel.LEFT);
      head.setFont(ExpoGuiText.font(Font.BOLD, 18));
      head.setAlignmentX(Component.LEFT_ALIGNMENT);
      this.add(head);

      for (String e : entries()) {
         JLabel l = new JLabel(ExpoGuiText.CHANGE_PREFIX + e, JLabel.LEFT);
         l.setFont(ExpoGuiText.font(Font.PLAIN, 14));
         l.setAlignmentX(Component.LEFT_ALIGNMENT);
         this.add(l);
      }
   }

   private static List<String> entries() {
      try {
         if (!ENTRIES.isEmpty()) {
            return ENTRIES;
         }
      } catch (Throwable t) {
      }

      return new ArrayList<String>();
   }
}
