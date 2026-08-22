package Expo.internal.restore;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.List;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import javax.swing.UIManager;


public final class ExpoGuiWindow {

   public static final String PROPERTY = "expo.gui";

   private static ExpoGuiWindow instance;

   private JFrame frame;
   private JTabbedPane tabs;
   private ExpoGuiInformation information;
   private ExpoGuiConfig config;
   private ExpoGuiModules modules;
   private ExpoGuiTerminal terminal;
   private ExpoGuiChangelog changelog;
   private Timer clock;
   private Timer refresher;

   private ExpoGuiWindow() {
   }

   private static String unsupportedReason;

   public static boolean armed() {
      String v = System.getProperty(PROPERTY);

      if ("0".equals(v) || "false".equalsIgnoreCase(v) || "off".equalsIgnoreCase(v)) {
         unsupportedReason = "turned off by -D" + PROPERTY + "=" + v;
         return false;
      }

      if ("1".equals(v) || "true".equalsIgnoreCase(v) || "on".equalsIgnoreCase(v)) {
         unsupportedReason = null;
         return true;
      }

      if ("1".equals(System.getProperty("expo.selftest"))) {
         unsupportedReason = "suppressed under -Dexpo.selftest (it would pause the world)";
         return false;
      }

      return supported();
   }

   public static boolean supported() {
      unsupportedReason = null;

      try {
         if (Boolean.getBoolean("java.awt.headless")) {
            unsupportedReason = "java.awt.headless=true";
            return false;
         }

         if (java.awt.GraphicsEnvironment.isHeadless()) {
            unsupportedReason = "GraphicsEnvironment.isHeadless()";
            return false;
         }

         String android = androidMarker();

         if (android != null) {
            unsupportedReason = "Android/phone launcher detected (" + android + ")";
            return false;
         }

         java.awt.GraphicsDevice[] screens =
            java.awt.GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices();

         if (screens == null || screens.length == 0) {
            unsupportedReason = "no screen device";
            return false;
         }

         if (java.awt.Toolkit.getDefaultToolkit() == null) {
            unsupportedReason = "no AWT toolkit";
            return false;
         }
      } catch (Throwable t) {
         unsupportedReason = "environment probe threw " + ExpoDiag.describe(t);
         return false;
      }

      return true;
   }

   private static String androidMarker() {
      try {
         if (System.getenv("ANDROID_ROOT") != null) {
            return "$ANDROID_ROOT";
         }

         if (System.getenv("ANDROID_DATA") != null) {
            return "$ANDROID_DATA";
         }

         if (System.getenv("POJAV_RENDERER") != null) {
            return "$POJAV_RENDERER";
         }

         String home = System.getProperty("java.home", "");
         String tmp = System.getProperty("java.io.tmpdir", "");

         if (home.startsWith("/data/") || tmp.startsWith("/data/")) {
            return "java.home/tmpdir under /data";
         }

         if (new java.io.File("/system/build.prop").exists()) {
            return "/system/build.prop";
         }
      } catch (Throwable t) {
         return null;
      }

      return null;
   }

   public static String unsupportedReason() {
      return unsupportedReason;
   }

   public static boolean install(List<String> pending) {
      if (!armed()) {
         note(pending, "Expo.gui  not opened -- " + unsupportedReason
                       + " (-D" + PROPERTY + "=1 forces it on, =0 forces it off)");
         return false;
      }

      open();
      note(pending, "Expo.gui  Swing window opened; modules visible = "
                    + ExpoGuiData.modules().size() + ", config dir = "
                    + ExpoGuiData.configDir().getAbsolutePath());
      return true;
   }

   public static void open() {
      SwingUtilities.invokeLater(new Runnable() {
         public void run() {
            try {
               get().show0();
            } catch (Throwable t) {
            }
         }
      });
   }

   public static void close() {
      SwingUtilities.invokeLater(new Runnable() {
         public void run() {
            try {
               if (instance != null && instance.frame != null) {
                  instance.frame.setVisible(false);
               }
            } catch (Throwable t) {
            }
         }
      });
   }

   public static void toggle() {
      SwingUtilities.invokeLater(new Runnable() {
         public void run() {
            try {
               ExpoGuiWindow w = get();

               if (w.frame != null && w.frame.isVisible()) {
                  w.frame.setVisible(false);
               } else {
                  w.show0();
               }
            } catch (Throwable t) {
            }
         }
      });
   }

   public static boolean visible() {
      return instance != null && instance.frame != null && instance.frame.isVisible();
   }

   public static JFrame frame() {
      return instance == null ? null : instance.frame;
   }

   static ExpoGuiWindow get() {
      if (instance == null) {
         instance = new ExpoGuiWindow();
      }

      return instance;
   }

   static void laf() {
      try {
         UIManager.setLookAndFeel(new com.formdev.flatlaf.FlatDarkLaf());
         return;
      } catch (Throwable t) {
      }

      try {
         UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
      } catch (Throwable t) {
      }
   }

   void show0() {
      if (this.frame == null) {
         laf();
         this.build();
      }

      this.frame.setLocationRelativeTo(null);
      this.frame.setVisible(true);
   }

   private void build() {
      this.frame = new JFrame(ExpoGuiText.TITLE);

      try {
         URL u = ExpoGuiWindow.class.getResource(ExpoGuiText.ICON);

         if (u != null) {
            this.frame.setIconImage(new ImageIcon(u).getImage());
         }
      } catch (Throwable t) {
      }

      this.frame.setResizable(false);
      this.frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
      try {
         JFrame.class.getMethod("setAutoRequestFocus", boolean.class)
                     .invoke(this.frame, Boolean.FALSE);
      } catch (Throwable t) {
      }
      this.frame.setSize(ExpoGuiText.FRAME_W, ExpoGuiText.FRAME_H);

      Container content = this.frame.getContentPane();
      content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));

      this.information = new ExpoGuiInformation();
      this.config = new ExpoGuiConfig();
      this.modules = new ExpoGuiModules();
      this.terminal = new ExpoGuiTerminal();
      this.changelog = new ExpoGuiChangelog();

      this.tabs = new JTabbedPane();
      this.tabs.addTab(ExpoGuiText.TAB_INFORMATION, this.information);
      this.tabs.addTab(ExpoGuiText.TAB_CONFIG, this.config);
      this.tabs.addTab(ExpoGuiText.TAB_MODULES, this.modules);
      this.tabs.addTab(ExpoGuiText.TAB_TERMINAL, this.terminal);
      this.tabs.addTab(ExpoGuiText.TAB_CHANGELOG, this.changelog);

      content.add(this.tabs);

      this.clock = new Timer(1000, new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            try {
               ExpoGuiWindow.this.information.tick();
            } catch (Throwable t) {
            }
         }
      });

      this.refresher = new Timer(500, new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            try {
               ExpoGuiWindow.this.terminal.pump();
               ExpoGuiWindow.this.modules.tick();
            } catch (Throwable t) {
            }
         }
      });

      this.clock.start();
      this.refresher.start();
   }

   private static void note(List<String> pending, String s) {
      if (pending != null) {
         pending.add(s);
      }

   }

   public static void main(String[] args) throws Exception {
      System.setProperty(PROPERTY, "1");
      boolean dump = args.length > 0 && "--dump".equals(args[0]);

      SwingUtilities.invokeAndWait(new Runnable() {
         public void run() {
            laf();
            get().build();
            get().frame.setLocationRelativeTo(null);
            get().frame.setVisible(true);
         }
      });

      System.out.println("[EXPOGUI] modules   = " + ExpoGuiData.modules().size());
      System.out.println("[EXPOGUI] configDir = " + ExpoGuiData.configDir().getAbsolutePath());
      System.out.println("[EXPOGUI] configs   = " + ExpoGuiData.configFiles().size());
      System.out.println("[EXPOGUI] user      = " + ExpoGuiData.userName());

      if (dump) {
         Thread.sleep(1200);

         final StringBuilder b = new StringBuilder();
         SwingUtilities.invokeAndWait(new Runnable() {
            public void run() {
               for (int i = 0; i < 5; i++) {
                  get().tabs.setSelectedIndex(i);
               }

               get().tabs.setSelectedIndex(0);
               b.append(ExpoGuiProbe.dump(get().frame));
            }
         });

         System.out.flush();
         System.exit(0);
      }
   }
}
