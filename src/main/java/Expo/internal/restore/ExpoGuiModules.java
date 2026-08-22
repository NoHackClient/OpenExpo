package Expo.internal.restore;

import Expo.module.Module;
import Expo.setting.Setting;
import Expo.setting.settings.BooleanSetting;
import Expo.setting.settings.ColorSetting;
import Expo.setting.settings.ModeSetting;
import Expo.setting.settings.NumberSetting;
import Expo.setting.settings.PercentageSetting;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.swing.BorderFactory;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JSpinner;
import javax.swing.ListSelectionModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;


public final class ExpoGuiModules extends JPanel {

   private static final long serialVersionUID = 1L;

   private final DefaultListModel<Object> model = new DefaultListModel<Object>();
   private final JList<Object> list = new JList<Object>(this.model);
   private final JPanel grid = new JPanel(new GridBagLayout());

   private Module current;
   private JButton toggleButton;
   private JButton bindButton;
   private boolean binding;
   private int lastIndex = -1;

   public ExpoGuiModules() {
      super(new BorderLayout());
      this.setBorder(BorderFactory.createEmptyBorder(ExpoGuiText.PAD, ExpoGuiText.PAD,
                                                     ExpoGuiText.PAD, ExpoGuiText.PAD));

      this.list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
      this.list.setFont(ExpoGuiText.font(Font.PLAIN, 14));
      this.list.setCellRenderer(new Renderer());

      JScrollPane left = new JScrollPane(this.list);
      left.setPreferredSize(new Dimension(ExpoGuiText.LIST_W, 0));
      left.getVerticalScrollBar().setUnitIncrement(16);

      JPanel holder = new JPanel(new BorderLayout());
      holder.add(this.grid, BorderLayout.NORTH);

      JScrollPane right = new JScrollPane(holder);
      right.getVerticalScrollBar().setUnitIncrement(16);

      this.add(left, BorderLayout.WEST);
      this.add(right, BorderLayout.CENTER);

      this.list.addListSelectionListener(new ListSelectionListener() {
         public void valueChanged(ListSelectionEvent e) {
            if (!e.getValueIsAdjusting()) {
               ExpoGuiModules.this.onSelect();
            }
         }
      });

      this.refreshList();
   }

   public void refreshList() {
      Object keep = this.list.getSelectedValue();
      this.model.clear();

      Map<String, List<Module>> byCategory = new LinkedHashMap<String, List<Module>>();
      String[] order = ExpoGuiData.categories();

      for (int i = 0; i < order.length; i++) {
         byCategory.put(order[i], new ArrayList<Module>());
      }

      for (Module m : ExpoGuiData.modules()) {
         String c = ExpoGuiData.category(m);
         List<Module> bucket = byCategory.get(c);

         if (bucket == null) {
            bucket = new ArrayList<Module>();
            byCategory.put(c, bucket);
         }

         bucket.add(m);
      }

      for (Map.Entry<String, List<Module>> e : byCategory.entrySet()) {
         if (e.getValue().isEmpty()) {
            continue;
         }

         java.util.Collections.sort(e.getValue(), new java.util.Comparator<Module>() {
            public int compare(Module x, Module y) {
               String a = ExpoGuiData.name(x);
               String b = ExpoGuiData.name(y);
               return (a == null ? "" : a).compareToIgnoreCase(b == null ? "" : b);
            }
         });

         this.model.addElement(new Header(e.getKey()));

         for (Module m : e.getValue()) {
            this.model.addElement(m);
         }
      }

      if (keep != null) {
         int idx = this.model.indexOf(keep);

         if (idx >= 0) {
            this.list.setSelectedIndex(idx);
            return;
         }
      }

      this.selectFirstModule();
   }

   static final class StateButton extends JButton {

      private static final long serialVersionUID = 1L;

      StateButton(String text) {
         super(text);
         this.setContentAreaFilled(false);
         this.setBorderPainted(false);
         this.setFocusPainted(false);
         this.setForeground(Color.WHITE);
      }

      protected void paintComponent(Graphics g) {
         g.setColor(this.getBackground());
         g.fillRect(0, 0, this.getWidth(), this.getHeight());
         super.paintComponent(g);
      }
   }

   static void paintState(JButton b, boolean on) {
      b.setBackground(on ? ExpoGuiText.STATE_ON : ExpoGuiText.STATE_OFF);
      b.setForeground(Color.WHITE);
      b.repaint();
   }

   public void tick() {
      if (this.current != null && this.toggleButton != null && !this.binding) {
         boolean on = ExpoGuiData.enabled(this.current);
         String want = on ? ExpoGuiText.BTN_ENABLED : ExpoGuiText.BTN_DISABLED;

         if (!want.equals(this.toggleButton.getText())) {
            this.toggleButton.setText(want);
            this.toggleButton.setActionCommand(want);
            paintState(this.toggleButton, on);
         }
      }

      if (this.current != null && this.bindButton != null && !this.binding) {
         String want = ExpoGuiText.BIND_PREFIX + ExpoGuiData.keyName(ExpoGuiData.keyBind(this.current));

         if (!want.equals(this.bindButton.getText())) {
            this.bindButton.setText(want);
            this.bindButton.setActionCommand(want);
         }
      }

      this.list.repaint();
   }

   private void selectFirstModule() {
      for (int i = 0; i < this.model.size(); i++) {
         if (this.model.get(i) instanceof Module) {
            this.list.setSelectedIndex(i);
            return;
         }
      }

      this.current = null;
      this.paintModuleList(null);
   }

   private void onSelect() {
      int i = this.list.getSelectedIndex();

      if (i < 0) {
         return;
      }

      Object v = this.model.get(i);

      if (v instanceof Header) {
         int step = i >= this.lastIndex ? 1 : -1;
         int j = i + step;

         while (j >= 0 && j < this.model.size()) {
            if (this.model.get(j) instanceof Module) {
               this.list.setSelectedIndex(j);
               return;
            }

            j += step;
         }

         this.list.setSelectedIndex(this.lastIndex >= 0 ? this.lastIndex : i);
         return;
      }

      this.lastIndex = i;
      this.current = (Module)v;
      this.paintModuleList(this.current);
   }

   private void paintModuleList(Module m) {
      this.grid.removeAll();
      this.toggleButton = null;
      this.bindButton = null;
      this.binding = false;

      GridBagConstraints c = new GridBagConstraints();
      c.gridx = 0;
      c.gridy = 0;
      c.anchor = GridBagConstraints.WEST;
      c.fill = GridBagConstraints.HORIZONTAL;
      c.insets = new Insets(3, 6, 3, 6);
      c.weightx = 0.0D;
      c.weighty = 0.0D;

      if (m == null) {
         c.gridwidth = 2;
         this.grid.add(new JLabel(ExpoGuiText.NO_SETTINGS), c);
         this.grid.revalidate();
         this.grid.repaint();
         return;
      }

      c.gridwidth = 2;
      c.weightx = 1.0D;
      this.grid.add(this.header(m), c);

      c.gridy++;
      JLabel desc = new JLabel(ExpoGuiData.description(m));
      desc.setFont(ExpoGuiText.font(Font.PLAIN, 13));
      this.grid.add(desc, c);

      List<Setting> settings = ExpoGuiData.settings(m);

      if (settings.isEmpty()) {
         c.gridy++;
         this.grid.add(new JLabel(ExpoGuiText.NO_SETTINGS), c);
      } else {
         for (Setting s : settings) {
            if (s == null) {
               continue;
            }

            if (s instanceof Expo.setting.settings.HeaderSetting) {
               String text = ((Expo.setting.settings.HeaderSetting)s).L();

               if (text != null && text.length() > 0) {
                  c.gridy++;
                  c.gridx = 0;
                  c.gridwidth = 2;
                  c.weightx = 1.0D;
                  JLabel band = new JLabel(text);
                  band.setFont(ExpoGuiText.font(Font.BOLD, 13));
                  this.grid.add(band, c);
                  c.gridwidth = 1;
               }

               continue;
            }

            Component widget = this.forSettings(m, s);

            if (widget == null) {
               continue;
            }

            c.gridy++;
            c.gridx = 0;
            c.gridwidth = 1;
            c.weightx = 0.0D;
            String nm = ExpoGuiData.settingName(s);
            JLabel label = new JLabel(nm.length() == 0 ? "" : nm + ":");
            label.setFont(ExpoGuiText.font(Font.PLAIN, 13));
            this.grid.add(label, c);

            c.gridx = 1;
            c.weightx = 1.0D;
            this.grid.add(widget, c);
         }
      }

      this.grid.revalidate();
      this.grid.repaint();
   }

   private JPanel header(final Module m) {
      JPanel panel = new JPanel(new BorderLayout(8, 0));

      JLabel title = new JLabel(ExpoGuiData.name(m) + ExpoGuiText.SETTINGS_SUFFIX);
      title.setFont(ExpoGuiText.font(Font.BOLD, 18));
      panel.add(title, BorderLayout.WEST);

      JPanel row = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 0));
      panel.add(row, BorderLayout.EAST);

      final JButton toggle = new StateButton(ExpoGuiData.enabled(m) ? ExpoGuiText.BTN_ENABLED
                                                                    : ExpoGuiText.BTN_DISABLED);
      toggle.setActionCommand(toggle.getText());
      paintState(toggle, ExpoGuiData.enabled(m));
      toggle.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            boolean now = ExpoGuiData.setEnabled(m, !ExpoGuiData.enabled(m));
            toggle.setText(now ? ExpoGuiText.BTN_ENABLED : ExpoGuiText.BTN_DISABLED);
            toggle.setActionCommand(toggle.getText());
            paintState(toggle, now);
            ExpoGuiModules.this.list.repaint();
         }
      });
      row.add(toggle);
      this.toggleButton = toggle;

      final JButton bind = new JButton(ExpoGuiText.BIND_PREFIX
                                       + ExpoGuiData.keyName(ExpoGuiData.keyBind(m)));
      bind.setActionCommand(bind.getText());
      bind.setFocusable(true);
      bind.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            ExpoGuiModules.this.binding = true;
            bind.setText(ExpoGuiText.BIND_PREFIX + "...");
            bind.requestFocusInWindow();
         }
      });
      bind.addKeyListener(new KeyAdapter() {
         public void keyPressed(KeyEvent e) {
            if (!ExpoGuiModules.this.binding) {
               return;
            }

            ExpoGuiModules.this.binding = false;

            if (ExpoGuiKeys.clears(e)) {
               ExpoGuiData.setKeyBind(m, 0);
            } else {
               String name = ExpoGuiKeys.lwjglName(e);
               int code = name == null ? 0 : ExpoGuiData.keyIndex(name);

               if (code != 0) {
                  ExpoGuiData.setKeyBind(m, code);
               }
            }

            bind.setText(ExpoGuiText.BIND_PREFIX + ExpoGuiData.keyName(ExpoGuiData.keyBind(m)));
            bind.setActionCommand(bind.getText());
            e.consume();
         }
      });
      row.add(bind);
      this.bindButton = bind;

      JButton save = new JButton(ExpoGuiText.BTN_REFRESH_SAVE);
      save.setActionCommand(ExpoGuiText.BTN_REFRESH_SAVE);
      save.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            ExpoGuiModules.this.paintModuleList(m);
            ExpoGuiModules.this.persist();
         }
      });
      row.add(save);

      return panel;
   }

   private void persist() {
      try {
         java.io.File out = new java.io.File(ExpoGuiData.configDir(), "current.json");
         ExpoGuiData.write(out, ExpoGuiJson.write(ExpoGuiData.merged(out)));
      } catch (Throwable t) {
      }
   }

   private Component forSettings(Module m, final Setting s) {
      if (s instanceof BooleanSetting) {
         final BooleanSetting b = (BooleanSetting)s;
         final JCheckBox box = new JCheckBox();
         box.setSelected(b.c());
         box.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
               ExpoGuiData.setBoolean(b, box.isSelected());
               box.setSelected(b.c());
            }
         });
         return box;
      }

      if (s instanceof ModeSetting) {
         final ModeSetting ms = (ModeSetting)s;
         List<String> opts = ExpoGuiData.modeOptions(ms);
         final JComboBox<String> combo = new JComboBox<String>(opts.toArray(new String[opts.size()]));
         combo.setSelectedItem(ms.Y());
         combo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
               Object v = combo.getSelectedItem();

               if (v != null) {
                  ExpoGuiData.setMode(ms, String.valueOf(v));
               }
            }
         });
         return combo;
      }

      if (s instanceof NumberSetting) {
         return this.numberSettingSpinner((NumberSetting)s);
      }

      if (s instanceof PercentageSetting) {
         return this.percentageSettingSpinner((PercentageSetting)s);
      }

      if (s instanceof ColorSetting) {
         final ColorSetting cs = (ColorSetting)s;
         final JButton b = new JButton("#" + ExpoGuiData.colorValue(cs));
         b.setActionCommand(b.getText());
         b.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
               Color start;

               try {
                  start = new Color(Integer.parseInt(ExpoGuiData.colorValue(cs), 16));
               } catch (Throwable t) {
                  start = Color.WHITE;
               }

               Color picked = JColorChooser.showDialog(b, ExpoGuiData.settingName(cs), start);

               if (picked != null) {
                  String hex = String.format("%06X", Integer.valueOf(picked.getRGB() & 0xFFFFFF));
                  ExpoGuiData.setColor(cs, hex);
                  b.setText("#" + ExpoGuiData.colorValue(cs));
                  b.setActionCommand(b.getText());
               }
            }
         });
         return b;
      }

      if (s instanceof Expo.setting.settings.TextSetting) {
         final Expo.setting.settings.TextSetting ts = (Expo.setting.settings.TextSetting)s;
         final javax.swing.JTextField field = new javax.swing.JTextField(ExpoGuiData.textValue(ts));
         field.setColumns(12);

         final ActionListener commit = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
               ExpoGuiData.setText(ts, field.getText());
               field.setText(ExpoGuiData.textValue(ts));
            }
         };

         field.addActionListener(commit);
         field.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent e) {
               commit.actionPerformed(null);
            }
         });
         return field;
      }

      return null;
   }

   private Component numberSettingSpinner(final NumberSetting ns) {
      final float lo = ExpoGuiData.numberMin(ns);
      final float hi = ExpoGuiData.numberMax(ns);
      final float step = ExpoGuiData.numberStep(ns);
      final boolean[] guard = new boolean[1];

      final JSlider slider = new JSlider(0, 1000, scale(ExpoGuiData.numberValue(ns), lo, hi));
      final JSpinner spinner = new JSpinner(new SpinnerNumberModel(
         (double)ExpoGuiData.numberValue(ns), (double)lo, (double)hi, (double)step));

      slider.addChangeListener(new ChangeListener() {
         public void stateChanged(ChangeEvent e) {
            if (guard[0]) {
               return;
            }

            guard[0] = true;

            try {
               float v = unscale(slider.getValue(), lo, hi);
               ExpoGuiData.setNumber(ns, v);
               spinner.setValue(Double.valueOf(ExpoGuiData.numberValue(ns)));
            } finally {
               guard[0] = false;
            }
         }
      });

      spinner.addChangeListener(new ChangeListener() {
         public void stateChanged(ChangeEvent e) {
            if (guard[0]) {
               return;
            }

            guard[0] = true;

            try {
               ExpoGuiData.setNumber(ns, ((Number)spinner.getValue()).floatValue());
               slider.setValue(scale(ExpoGuiData.numberValue(ns), lo, hi));
            } finally {
               guard[0] = false;
            }
         }
      });

      JPanel row = new JPanel(new BorderLayout(6, 0));
      row.add(slider, BorderLayout.CENTER);
      row.add(spinner, BorderLayout.EAST);
      return row;
   }

   private Component percentageSettingSpinner(final PercentageSetting ps) {
      final boolean[] guard = new boolean[1];
      final JSlider slider = new JSlider(0, 100, ExpoGuiData.percentageValue(ps));
      final JSpinner spinner = new JSpinner(new SpinnerNumberModel(
         (double)ExpoGuiData.percentageValue(ps), 0.0D, 100.0D, 1.0D));

      slider.addChangeListener(new ChangeListener() {
         public void stateChanged(ChangeEvent e) {
            if (guard[0]) {
               return;
            }

            guard[0] = true;

            try {
               ExpoGuiData.setPercentage(ps, slider.getValue());
               spinner.setValue(Double.valueOf(ExpoGuiData.percentageValue(ps)));
            } finally {
               guard[0] = false;
            }
         }
      });

      spinner.addChangeListener(new ChangeListener() {
         public void stateChanged(ChangeEvent e) {
            if (guard[0]) {
               return;
            }

            guard[0] = true;

            try {
               ExpoGuiData.setPercentage(ps, (int)((Number)spinner.getValue()).doubleValue());
               slider.setValue(ExpoGuiData.percentageValue(ps));
            } finally {
               guard[0] = false;
            }
         }
      });

      JPanel row = new JPanel(new GridBagLayout());
      GridBagConstraints c = new GridBagConstraints();
      c.gridx = 0;
      c.fill = GridBagConstraints.HORIZONTAL;
      c.weightx = 1.0D;
      row.add(slider, c);

      c.gridx = 1;
      c.fill = GridBagConstraints.NONE;
      c.weightx = 0.0D;
      c.insets = new Insets(0, 6, 0, 4);
      row.add(spinner, c);

      c.gridx = 2;
      c.insets = new Insets(0, 0, 0, 0);
      row.add(new JLabel("%"), c);
      return row;
   }

   private static int scale(float v, float lo, float hi) {
      if (hi <= lo) {
         return 0;
      }

      int k = Math.round((v - lo) / (hi - lo) * 1000.0F);
      return k < 0 ? 0 : k > 1000 ? 1000 : k;
   }

   private static float unscale(int k, float lo, float hi) {
      return hi <= lo ? lo : lo + (hi - lo) * k / 1000.0F;
   }

   static final class Header {

      final String text;

      Header(String text) {
         this.text = text;
      }

      public String toString() {
         return this.text;
      }
   }

   final class Renderer extends DefaultListCellRenderer {

      private static final long serialVersionUID = 1L;

      public Component getListCellRendererComponent(JList<?> l, Object value, int index,
                                                    boolean selected, boolean focus) {
         String text;
         boolean header = value instanceof Header;

         if (header) {
            text = ((Header)value).text;
         } else if (value instanceof Module) {
            text = ExpoGuiData.name((Module)value);
            int bind = ExpoGuiData.keyBind((Module)value);

            if (bind != 0) {
               text = text + " (" + ExpoGuiData.keyName(bind) + ")";
            }
         } else {
            text = String.valueOf(value);
         }

         Component c = super.getListCellRendererComponent(l, text, index,
                                                          selected && !header, focus);
         JLabel label = c instanceof JLabel ? (JLabel)c : null;

         if (label != null) {
            label.setHorizontalAlignment(SwingConstants.LEFT);
            label.setHorizontalTextPosition(SwingConstants.LEFT);
         }

         if (header) {
            c.setFont(ExpoGuiText.font(Font.BOLD, ExpoGuiText.CATEGORY_SIZE));
            c.setBackground(ExpoGuiText.CATEGORY_BG);
            c.setForeground(Color.WHITE);

            if (label != null) {
               label.setOpaque(true);
               label.setBorder(BorderFactory.createEmptyBorder(3, 6, 3, 6));
            }

            return c;
         }

         c.setFont(ExpoGuiText.font(Font.PLAIN, ExpoGuiText.MODULE_SIZE));

         if (label != null) {
            label.setBorder(BorderFactory.createEmptyBorder(1, 14, 1, 6));
         }

         if (value instanceof Module) {
            c.setForeground(ExpoGuiData.enabled((Module)value) ? ExpoGuiText.STATE_ON
                                                              : ExpoGuiText.STATE_OFF);
         }

         return c;
      }
   }
}
