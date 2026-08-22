package Expo.ui.screen;

import Expo.internal.auth.CookieAuthService;
import Expo.util.ChatFormatting;
import java.awt.FileDialog;
import java.awt.Frame;
import java.io.File;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;


public class CookieLoginScreen extends GuiScreen {
   private static long b;
   private String O;
   private final GuiScreen a;
   private GuiButton q;
   private CompletableFuture<Boolean> Z;
   private ExecutorService Q;
   private GuiButton k;
   private String M;
   private boolean v;

   public void onGuiClosed() {
      if (this.Z != null && !this.Z.isDone()) {
         this.Z.cancel(true);
      }

      if (this.Q != null && !this.Q.isShutdown()) {
         this.Q.shutdownNow();
      }
   }


   public void initGui() {
      this.buttonList.clear();



      int var6 = this.width / 2;
      int var7 = var6 - 200 / 2;
      int var8 = this.height / 2 + this.fontRendererObj.FONT_HEIGHT / 2 + this.fontRendererObj.FONT_HEIGHT * 2;
      this.k = new GuiButton(0, var7, var8, 200, 20, "Open Cookie File");
      this.buttonList.add(this.k);
      this.q = new GuiButton(1, var7, var8 + 20 + 5, 200, 20, "Cancel");
      this.buttonList.add(this.q);
      this.O = "&fSelect a cookie file to authenticate&r";
      if (this.Q == null || this.Q.isShutdown()) {
         this.Q = Executors.newSingleThreadExecutor();
      }
   }

   static {

      b = 118137226664231L;
   }

   protected void keyTyped(char var1, int var2) {
      if (var2 == 1) {
         this.actionPerformed(this.q);
      }
   }

   protected void actionPerformed(GuiButton var1) {
      if (var1 != null && var1.enabled) {
         switch (var1.id) {
            case 0:
               try {
                  UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
               } catch (Exception var5) {
                  var5.printStackTrace();
               }

               this.x("&aOpening file picker...&r");
               SwingUtilities.invokeLater(() -> {
                  FileDialog var3 = new FileDialog((Frame)null, "Select Cookie File", 0);
                  var3.setDirectory(System.getProperty("user.home") + File.separator + "Downloads");
                  var3.setVisible(true);
                  if (var3.getFile() == null) {
                     this.x("&eFile selection canceled.&r");
                  } else {
                     File var4 = new File(var3.getDirectory(), var3.getFile());
                     if (!var4.exists()) {
                        this.C(() -> {
                           long var1xx = b ^ 115727714744226L;
                           this.x("&cSelected file does not exist!&r");
                        });
                     } else {
                        this.C(() -> {
                           long var1xx = b ^ 70318438070264L;
                           this.v = false;
                           this.x("&fReading cookie file...&r");
                        });
                        this.Z = CookieAuthService.C(var4, this);
                        this.Z.whenComplete((var1xx, var2x) -> this.C(() -> {
                           long var5x = 81800336346822L;
                           this.v = true;
                           if (Boolean.TRUE.equals(var1xx)) {
                              this.mc.displayGuiScreen(new AccountManagerScreen(var5x, this.a));
                           } else if (var2x != null) {
                              this.x("&cAuthentication failed: " + var2x.getMessage() + "&r");
                           }
                        }));
                     }
                  }
               });
               break;
            case 1:
               this.mc.displayGuiScreen(this.a);
               break;
            default:
               System.err.println("Unknown button ID: " + var1.id);
         }
      }
   }

   public CookieLoginScreen(GuiScreen var1, long var2) {
      this.v = true;
      this.a = var1;
   }

   public void x(String var1) {
      if (this.mc != null) {
         this.mc.addScheduledTask(() -> this.O = var1);
      } else {
         this.O = var1;
      }
   }

   private void C(Runnable var1) {
      this.mc.addScheduledTask(var1);
   }


   public void drawScreen(int var1, int var2, float var3) {
      if (this.k != null) {
         this.k.enabled = this.v;
      }

      this.drawDefaultBackground();
      super.drawScreen(var1, var2, var3);
      this.drawCenteredString(
         this.fontRendererObj,
         "Cookie Authentication",
         this.width / 2,
         this.height / 2 - this.fontRendererObj.FONT_HEIGHT / 2 - this.fontRendererObj.FONT_HEIGHT * 2,
         11184810
      );
      if (this.O != null) {
         this.drawCenteredString(
            this.fontRendererObj,
            ChatFormatting.y(this.O),
            this.width / 2,
            this.height / 2 - this.fontRendererObj.FONT_HEIGHT / 2,
            -1
         );
      }

      if (this.M != null) {
         String var9 = ChatFormatting.y(this.M);
         Gui.drawRect(
            0,
            this.height - 2 - this.fontRendererObj.FONT_HEIGHT - 3,
            3 + this.mc.fontRendererObj.getStringWidth(var9) + 3,
            this.height,
            1677721600
         );
         this.drawString(this.fontRendererObj, var9, 3, this.height - 2 - this.fontRendererObj.FONT_HEIGHT, -1);
      }
   }

}
