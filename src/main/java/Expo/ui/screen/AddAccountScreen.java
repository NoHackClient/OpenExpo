package Expo.ui.screen;

import Expo.util.Sneaky;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;

public class AddAccountScreen extends GuiScreen {
   private final GuiScreen s;
   private static long a;

   public void onGuiClosed() {
   }

   static void $jnicClinit() throws UnsupportedEncodingException, InvalidAlgorithmParameterException, InvalidKeyException, NoSuchAlgorithmException, InvalidKeySpecException, BadPaddingException, IllegalBlockSizeException, NoSuchPaddingException {
      a = 12041044611841L;
   }

   public GuiScreen j() {
      return this.s;
   }

   protected void actionPerformed(GuiButton var1) {
       try {long var2 = 35240121263301L;

      if (var1 != null) {
         switch (var1.id) {
            case 0:
               this.mc.displayGuiScreen(new MicrosoftLoginScreen(this.s));
               break;
            case 1:
               this.mc.displayGuiScreen(new CookieLoginScreen(this.s, 80809518142778L));
               break;
            case 2:
               this.mc.displayGuiScreen(new OfflineLoginScreen(this.s));
               break;
            case 3:
               this.mc.displayGuiScreen(new AccessTokenLoginScreen(this.s, 53839872381595L));
               break;
            case 4:
               this.mc.displayGuiScreen(new AccountManagerScreen(81800336346822L, this.s));
               break;
            case 5:
               this.mc.displayGuiScreen(new RefreshTokenLoginScreen(this.s, 81544777000287L));
         }
      }
   } catch (Throwable zkm$t) { throw Sneaky.rethrow(zkm$t); } }

   public void drawScreen(int var1, int var2, float var3) {
       try {long var4 = 48433265682448L;
      this.drawDefaultBackground();
      this.drawCenteredString(
         this.fontRendererObj,
         "Choose Account Type to Add",
         this.width / 2,
         this.height / 2 - 70,
         16777215
      );
      super.drawScreen(var1, var2, var3);
   } catch (Throwable zkm$t) { throw Sneaky.rethrow(zkm$t); } }

   public AddAccountScreen(GuiScreen var1) {
      this.s = var1;
   }

   public void initGui() {
       try {long var1 = 59250142702415L;
      this.buttonList
         .add(
            new GuiButton(
               0,
               this.width / 2 - 100,
               this.height / 2 - 45,
               200,
               20,
               "Microsoft"
            )
         );
      this.buttonList
         .add(
            new GuiButton(
               1,
               this.width / 2 - 100,
               this.height / 2 - 20,
               200,
               20,
               "Cookie"
            )
         );
      this.buttonList
         .add(
            new GuiButton(
               2,
               this.width / 2 - 100,
               this.height / 2 + 5,
               200,
               20,
               "Offline"
            )
         );
      this.buttonList
         .add(
            new GuiButton(
               3,
               this.width / 2 - 100,
               this.height / 2 + 30,
               200,
               20,
               "Access Token"
            )
         );
      this.buttonList
         .add(
            new GuiButton(
               5,
               this.width / 2 - 100,
               this.height / 2 + 55,
               200,
               20,
               "Refresh Token"
            )
         );
      this.buttonList
         .add(
            new GuiButton(
               4,
               this.width / 2 - 100,
               this.height / 2 + 80,
               200,
               20,
               "Back"
            )
         );
   } catch (Throwable zkm$t) { throw Sneaky.rethrow(zkm$t); } }

   static {
      try {
         $jnicClinit();
      } catch (UnsupportedEncodingException | InvalidAlgorithmParameterException | InvalidKeyException | NoSuchAlgorithmException | InvalidKeySpecException | BadPaddingException | IllegalBlockSizeException | NoSuchPaddingException var0) {
         throw new RuntimeException(var0);
      }
   }
}
