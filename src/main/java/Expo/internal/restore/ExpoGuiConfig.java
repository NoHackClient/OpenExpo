package Expo.internal.restore;

import java.awt.BorderLayout;
import java.awt.Desktop;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.filechooser.FileNameExtensionFilter;


public final class ExpoGuiConfig extends JPanel {

   private static final long serialVersionUID = 1L;

   private final DefaultListModel<String> model = new DefaultListModel<String>();
   private final JList<String> list = new JList<String>(this.model);

   public ExpoGuiConfig() {
      super(new BorderLayout());
      this.setBorder(BorderFactory.createEmptyBorder(ExpoGuiText.PAD, ExpoGuiText.PAD,
                                                     ExpoGuiText.PAD, ExpoGuiText.PAD));

      this.list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
      this.list.setFont(ExpoGuiText.font(Font.PLAIN, 14));

      JPanel row = new JPanel(new FlowLayout(FlowLayout.CENTER,
                                             ExpoGuiText.ROW_HGAP, ExpoGuiText.ROW_VGAP));

      this.add(new JScrollPane(this.list), BorderLayout.CENTER);
      this.add(row, BorderLayout.SOUTH);

      row.add(button(ExpoGuiText.BTN_REFRESH, "refresh"));
      row.add(button(ExpoGuiText.BTN_IMPORT, "import"));
      row.add(button(ExpoGuiText.BTN_LOAD, "load"));
      row.add(button(ExpoGuiText.BTN_SAVE, "save"));
      row.add(button(ExpoGuiText.BTN_DELETE, "delete"));
      row.add(button(ExpoGuiText.BTN_RESET, "reset"));
      row.add(button(ExpoGuiText.BTN_OPEN_FOLDER, "folder"));

      this.refresh();
   }

   public void refresh() {
      String keep = this.list.getSelectedValue();
      this.model.clear();

      for (File f : ExpoGuiData.configFiles()) {
         this.model.addElement(f.getName());
      }

      if (keep != null) {
         int idx = this.model.indexOf(keep);

         if (idx >= 0) {
            this.list.setSelectedIndex(idx);
         }
      }
   }

   private JButton button(String text, final String action) {
      JButton b = new JButton(text);
      b.setActionCommand(text);
      b.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            try {
               ExpoGuiConfig.this.run(action);
            } catch (Throwable t) {
               ExpoGuiConfig.this.say(String.valueOf(t));
            }
         }
      });
      return b;
   }

   private void run(String action) {
      if ("refresh".equals(action)) {
         this.refresh();
      } else if ("import".equals(action)) {
         this.doImport();
      } else if ("load".equals(action)) {
         this.doLoad(this.selected());
      } else if ("save".equals(action)) {
         this.doSave();
      } else if ("delete".equals(action)) {
         this.doDelete();
      } else if ("reset".equals(action)) {
         this.doReset();
      } else if ("folder".equals(action)) {
         this.doFolder();
      }
   }

   private File selected() {
      String n = this.list.getSelectedValue();
      return n == null ? null : new File(ExpoGuiData.configDir(), n);
   }

   private void doImport() {
      JFileChooser fc = new JFileChooser();
      fc.setDialogTitle(ExpoGuiText.CHOOSE_FILE);
      fc.setApproveButtonText(ExpoGuiText.CHOOSE_FILE_PROMPT);
      fc.setFileFilter(new FileNameExtensionFilter(ExpoGuiText.JSON_FILTER, ExpoGuiText.JSON_EXT));

      if (fc.showOpenDialog(this) != JFileChooser.APPROVE_OPTION) {
         this.say(ExpoGuiText.CANCELLED);
         return;
      }

      File src = fc.getSelectedFile();

      if (src == null || !src.isFile()) {
         this.say(ExpoGuiText.CANCELLED);
         return;
      }

      File dst = new File(ExpoGuiData.configDir(), src.getName());

      if (!copy(src, dst)) {
         this.say("Import failed");
         return;
      }

      this.refresh();
      this.say(ExpoGuiText.CFG_QUOTE + dst.getName() + ExpoGuiText.CFG_SAVED);
   }

   private void doLoad(File f) {
      if (f == null || !f.isFile()) {
         this.say(ExpoGuiText.CHOOSE_FILE_PROMPT);
         return;
      }

      int[] counts = ExpoGuiData.applyFile(f);
      this.say(ExpoGuiText.CFG_QUOTE + f.getName() + ExpoGuiText.CFG_LOADED
               + " (" + counts[0] + " modules, " + counts[1] + " fields, "
               + counts[2] + " settings)");
   }

   private void doSave() {
      String name = JOptionPane.showInputDialog(this, ExpoGuiText.SAVE_PROMPT,
                                                ExpoGuiText.SAVE_TITLE,
                                                JOptionPane.PLAIN_MESSAGE);

      if (name == null) {
         this.say(ExpoGuiText.CANCELLED);
         return;
      }

      name = name.trim();

      if (name.length() == 0 || name.indexOf(File.separatorChar) >= 0
          || name.indexOf('/') >= 0 || name.indexOf('\\') >= 0) {
         this.say(ExpoGuiText.SAVE_CANCELLED);
         return;
      }

      if (!name.toLowerCase().endsWith(ExpoGuiText.JSON_SUFFIX)) {
         name = name + ExpoGuiText.JSON_SUFFIX;
      }

      File out = new File(ExpoGuiData.configDir(), name);
      Map<String, Object> root = ExpoGuiData.merged(out);

      if (!ExpoGuiData.write(out, ExpoGuiJson.write(root))) {
         this.say(ExpoGuiText.SAVE_CANCELLED);
         return;
      }

      this.refresh();
      this.say(ExpoGuiText.CFG_QUOTE + name + ExpoGuiText.CFG_SAVED);
   }

   private void doDelete() {
      File f = this.selected();

      if (f == null || !f.isFile()) {
         this.say(ExpoGuiText.CHOOSE_FILE_PROMPT);
         return;
      }

      if (JOptionPane.showConfirmDialog(this, ExpoGuiText.CFG_QUOTE + f.getName() + "\"?",
                                        ExpoGuiText.BTN_DELETE,
                                        JOptionPane.YES_NO_OPTION) != JOptionPane.YES_OPTION) {
         this.say(ExpoGuiText.CANCELLED);
         return;
      }

      boolean gone = f.delete();
      this.refresh();
      this.say(gone ? ExpoGuiText.CFG_QUOTE + f.getName() + ExpoGuiText.CFG_DELETED
                    : ExpoGuiText.CANCELLED);
   }

   private void doReset() {
      File def = new File(ExpoGuiData.configDir(),
                          ExpoGuiText.DEFAULT_NAME + ExpoGuiText.JSON_SUFFIX);

      if (!def.isFile()) {
         this.say("No " + def.getName() + " in " + ExpoGuiData.configDir().getPath());
         return;
      }

      int[] counts = ExpoGuiData.applyFile(def);
      this.say(ExpoGuiText.CFG_DEFAULTED + " (" + counts[0] + " modules, "
               + counts[1] + " fields, " + counts[2] + " settings)");
   }

   private void doFolder() {
      File dir = ExpoGuiData.configDir();

      try {
         Desktop.getDesktop().open(dir);
      } catch (Throwable t) {
         this.say(dir.getAbsolutePath());
      }
   }

   private void say(String text) {
      JOptionPane.showMessageDialog(this, text, ExpoGuiText.TITLE, JOptionPane.PLAIN_MESSAGE);
   }

   private static boolean copy(File src, File dst) {
      InputStream in = null;
      OutputStream out = null;

      try {
         in = new FileInputStream(src);
         out = new FileOutputStream(dst);
         byte[] buf = new byte[8192];
         int k;

         while ((k = in.read(buf)) > 0) {
            out.write(buf, 0, k);
         }

         return true;
      } catch (Throwable t) {
         return false;
      } finally {
         close(in);
         close(out);
      }
   }

   private static void close(java.io.Closeable c) {
      if (c != null) {
         try {
            c.close();
         } catch (Throwable t) {
         }
      }
   }
}
