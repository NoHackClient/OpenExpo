package Expo.ui.swing;

import java.util.Arrays;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

public class Notification {
   private static final String title = "Expo - This is not the proper installation method.";
   private static final List<String> message = Arrays.asList(
      "Expo is not an application.",
      "",
      "If you are having issues installing this software, check these requirements",
      "* A proper and functioning Minecraft 1.8.9 environment",
      "* A skidonion account owning valid subscription of this software",
      "* The correct PC that matches the HWID record of skidonion",
      "* Run Minecraft client using JDK version 1.8 (Try Eclipse Adoptium JDK)",
      "* \"-noverify\" Java Virtual Machine argument added",
      "* Clean Windows 11 x86-64 OS environment",
      "* Not using any CPU which is not x86-64 architecture",
      "* Not running under a virtual machine",
      "* Do not modify this Jar file",
      "",
      "After following these steps, if you are still having issues launching the game. Contact me via discord as soon as possible.",
      "Make sure you describe the problems clearly. If you have logs, send it",
      "",
      "By NoHackClient",
      "© 2026 NoHackClient. All rights reserved."
   );

   public static void main(String[] var0) {
      try {
         UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
      } catch (Exception var2) {
         return;
      }

      JOptionPane.showMessageDialog(null, String.join("\n", message), "Expo - This is not the proper installation method.", 1);
   }
}
