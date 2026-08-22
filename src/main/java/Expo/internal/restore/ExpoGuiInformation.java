package Expo.internal.restore;

import java.awt.Component;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

public final class ExpoGuiInformation extends JPanel {
   private static final long serialVersionUID = 1L;

   private final JLabel clock;

   public ExpoGuiInformation() {
      this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

      this.add(Box.createVerticalStrut(40));
      this.add(label(ExpoGuiText.TITLE, 42));
      this.add(Box.createVerticalStrut(10));
      this.add(label("", 16));
      this.add(Box.createVerticalStrut(20));
      this.add(label(ExpoGuiText.RELEASE, 18));
      this.add(Box.createVerticalStrut(10));
      this.add(label(ExpoGuiText.BUILD, 16));
      this.add(Box.createVerticalStrut(10));
      this.add(label(ExpoGuiText.USER_PREFIX + ExpoGuiData.userName(), 16));
      this.add(Box.createVerticalStrut(10));

      this.clock = label(stamp(), 16);
      this.add(this.clock);

      this.add(Box.createVerticalStrut(20));
      this.add(label(ExpoGuiText.BLURB, 14));
      this.add(Box.createVerticalStrut(10));
   }

   public void tick() {
      this.clock.setText(stamp());
   }

   private static String stamp() {
      try {
         return new SimpleDateFormat(ExpoGuiText.TIME_PATTERN).format(new Date());
      } catch (Throwable t) {
         return "";
      }
   }

   private static JLabel label(String text, int size) {
      JLabel l = new JLabel(text, JLabel.CENTER);
      l.setFont(ExpoGuiText.font(java.awt.Font.PLAIN, size));
      l.setAlignmentX(Component.CENTER_ALIGNMENT);
      return l;
   }
}
