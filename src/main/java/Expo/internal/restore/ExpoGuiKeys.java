package Expo.internal.restore;

import java.awt.event.KeyEvent;

public final class ExpoGuiKeys {
   private ExpoGuiKeys() {
   }

   public static String lwjglName(KeyEvent e) {
      int c = e.getKeyCode();

      if (c >= KeyEvent.VK_A && c <= KeyEvent.VK_Z) {
         return String.valueOf((char)('A' + (c - KeyEvent.VK_A)));
      }

      if (c >= KeyEvent.VK_0 && c <= KeyEvent.VK_9) {
         return String.valueOf((char)('0' + (c - KeyEvent.VK_0)));
      }

      if (c >= KeyEvent.VK_F1 && c <= KeyEvent.VK_F12) {
         return "F" + (c - KeyEvent.VK_F1 + 1);
      }

      boolean right = e.getKeyLocation() == KeyEvent.KEY_LOCATION_RIGHT;

      switch (c) {
         case KeyEvent.VK_SHIFT:
            return right ? "RSHIFT" : "LSHIFT";
         case KeyEvent.VK_CONTROL:
            return right ? "RCONTROL" : "LCONTROL";
         case KeyEvent.VK_ALT:
            return right ? "RMENU" : "LMENU";
         case KeyEvent.VK_SPACE:
            return "SPACE";
         case KeyEvent.VK_ENTER:
            return "RETURN";
         case KeyEvent.VK_TAB:
            return "TAB";
         case KeyEvent.VK_BACK_SPACE:
            return "BACK";
         case KeyEvent.VK_UP:
            return "UP";
         case KeyEvent.VK_DOWN:
            return "DOWN";
         case KeyEvent.VK_LEFT:
            return "LEFT";
         case KeyEvent.VK_RIGHT:
            return "RIGHT";
         case KeyEvent.VK_INSERT:
            return "INSERT";
         case KeyEvent.VK_HOME:
            return "HOME";
         case KeyEvent.VK_END:
            return "END";
         case KeyEvent.VK_PAGE_UP:
            return "PRIOR";
         case KeyEvent.VK_PAGE_DOWN:
            return "NEXT";
         case KeyEvent.VK_MINUS:
            return "MINUS";
         case KeyEvent.VK_EQUALS:
            return "EQUALS";
         case KeyEvent.VK_OPEN_BRACKET:
            return "LBRACKET";
         case KeyEvent.VK_CLOSE_BRACKET:
            return "RBRACKET";
         case KeyEvent.VK_SEMICOLON:
            return "SEMICOLON";
         case KeyEvent.VK_QUOTE:
            return "APOSTROPHE";
         case KeyEvent.VK_COMMA:
            return "COMMA";
         case KeyEvent.VK_PERIOD:
            return "PERIOD";
         case KeyEvent.VK_SLASH:
            return "SLASH";
         case KeyEvent.VK_BACK_SLASH:
            return "BACKSLASH";
         case KeyEvent.VK_BACK_QUOTE:
            return "GRAVE";
         default:
            return null;
      }
   }

   public static boolean clears(KeyEvent e) {
      return e.getKeyCode() == KeyEvent.VK_ESCAPE || e.getKeyCode() == KeyEvent.VK_DELETE;
   }
}
