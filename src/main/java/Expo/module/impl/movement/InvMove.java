package Expo.module.impl.movement;

import Expo.module.Category;

import Expo.event.EventBus;
import Expo.event.EventSubscriber;
import Expo.event.binder.InvMoveBinder;
import Expo.event.events.CloseScreenEvent;
import Expo.event.events.PostTickEvent;
import Expo.event.events.PreUpdateEvent;
import Expo.event.events.SendPacketEvent;
import Expo.module.Module;
import Expo.module.Modules;
import Expo.setting.settings.BooleanSetting;
import Expo.setting.settings.ModeSetting;
import Expo.ui.raven.RavenClickGuiScreen;
import Expo.ui.studio.StudioClickGuiScreen;
import Expo.util.KeyBindUtil;
import Expo.util.MinecraftRef;
import Expo.util.ScoreboardReader;
import Expo.util.packet.PacketManager;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryCraftResult;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.inventory.SlotCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.C0DPacketCloseWindow;
import net.minecraft.network.play.client.C0EPacketClickWindow;


public class InvMove extends Module implements EventSubscriber {
   public static ModeSetting containerMode;
   private static long a;
   private boolean c;
   public static ModeSetting inventoryMode;
   public static BooleanSetting clickgui;
   private final List<Packet<?>> Y;


   public static void c(long var0) {



      KeyBinding[] var10000 = new KeyBinding[7];
      var10000[0] = MinecraftRef.c((byte)0,0L).gameSettings.keyBindForward;
      var10000[1] = MinecraftRef.c((byte)0,0L).gameSettings.keyBindBack;
      var10000[2] = MinecraftRef.c((byte)0,0L).gameSettings.keyBindLeft;
      var10000[3] = MinecraftRef.c((byte)0,0L).gameSettings.keyBindRight;
      var10000[4] = MinecraftRef.c((byte)0,0L).gameSettings.keyBindJump;
      var10000[5] = MinecraftRef.c((byte)0,0L).gameSettings.keyBindSprint;
      var10000[6] = MinecraftRef.c((byte)0,0L).gameSettings.keyBindJump;
      KeyBinding[] var9 = var10000;

      for (KeyBinding var13 : var9) {
         KeyBindUtil.o(99363263780575L, var13.getKeyCode());
      }

      if (Modules.J(Sprint.class).o()) {
         KeyBindUtil.A(82009306480869L, MinecraftRef.c((byte)0,0L).gameSettings.keyBindSprint.getKeyCode(), true);
      }
   }


   public void onCloseScreen(CloseScreenEvent var1) {
      c(0L);
      f.inGameHasFocus = true;
   }


   private boolean w$r3() {
      for (Slot var2 : f.thePlayer.openContainer.inventorySlots) {
         if (var2 instanceof SlotCrafting) {
            ItemStack var3 = var2.getStack();
            if (var3 != null) {
               return false;
            }
         }
      }

      return f.thePlayer.inventory.getItemStack() == null;
   }

   public void onSendPacket(SendPacketEvent var3) throws UnsupportedEncodingException, InvalidAlgorithmParameterException, InvalidKeyException, InvalidKeySpecException, BadPaddingException, IllegalBlockSizeException {


      if (var3.B instanceof C0EPacketClickWindow) {
         C0EPacketClickWindow var13 = (C0EPacketClickWindow)var3.B;
         if (this.Z(var13.getSlotId()) || !this.isGetItemStack()) {
            this.remove(false);
            Q(0L);
            return;
         }

         if (ScoreboardReader.v(0L)) {
            if (f.currentScreen instanceof GuiContainer && !(f.currentScreen instanceof GuiInventory)) {
               if (containerMode.R("HYPIXEL")) {
                  this.Y.add(var13);
                  var3.I(21307, 3074332907L);
                  return;
               }
            } else if (f.currentScreen instanceof GuiInventory && inventoryMode.R("HYPIXEL")) {
               this.Y.add(var13);
               var3.I(21307, 3074332907L);
               return;
            }
         }

         this.c = true;
      }
   }

   private void remove(boolean var3) {

      for (Packet var5 : this.Y) {
         PacketManager.X(var5);
         this.Y.remove(var5);
      }

      if (var3) {
         PacketManager.b(new C0DPacketCloseWindow(f.thePlayer.inventoryContainer.windowId));
      }
   }

   private boolean d$r2() {
      if (f.thePlayer != null && f.thePlayer.openContainer != null) {
         for (Slot var2 : f.thePlayer.openContainer.inventorySlots) {
            if (var2 != null) {
               IInventory var3 = var2.inventory;
               if (var3 instanceof InventoryCrafting || var3 instanceof InventoryCraftResult || var2 instanceof SlotCrafting) {
                  ItemStack var4 = var2.getStack();
                  if (var4 != null) {
                     return true;
                  }
               }
            }
         }

         return false;
      } else {
         return false;
      }
   }

   public void onPostTick(PostTickEvent var3) {
      if (!this.Y.isEmpty()) {
         this.remove(this.isGetItemStack());
      }
   }


   private boolean Z(int var1) {
      if (f.thePlayer.openContainer == null) {
         return false;
      }

      if (var1 >= 0 && var1 < f.thePlayer.openContainer.inventorySlots.size()) {
         Slot var2 = (Slot)f.thePlayer.openContainer.inventorySlots.get(var1);
         if (var2 == null) {
            return false;
         }

         IInventory var3 = var2.inventory;
         return var3 instanceof InventoryCrafting || var3 instanceof InventoryCraftResult;
      } else {
         return false;
      }
   }

   public void onPreUpdate(PreUpdateEvent var3) throws UnsupportedEncodingException, InvalidAlgorithmParameterException, InvalidKeyException, InvalidKeySpecException, BadPaddingException, IllegalBlockSizeException {
      if (f.currentScreen == null) {
         this.c = false;
      }

      if (!clickgui.c() || !(f.currentScreen instanceof RavenClickGuiScreen) && !(f.currentScreen instanceof StudioClickGuiScreen)) {
         if (f.currentScreen != null) {
            if (f.currentScreen instanceof GuiContainer && !(f.currentScreen instanceof GuiInventory)) {
               switch (containerMode.Y()) {
                  case "LEGIT":
                     if (this.w$r3() && !this.c) {
                        c(0L);
                     } else {
                        Q(0L);
                     }
                     break;
                  case "VANILLA":
                     c(0L);
                     break;
                  case "HYPIXEL":
                     if (ScoreboardReader.v(0L)) {
                        if (this.isGetItemStack()) {
                           c(0L);
                        } else {
                           Q(0L);
                        }
                     }
                     break;
                  default:
                     Q(0L);
               }
            } else if (f.currentScreen instanceof GuiInventory) {
               switch (inventoryMode.Y()) {
                  case "LEGIT":
                     if (this.w$r3() && !this.c) {
                        c(0L);
                     } else {
                        Q(0L);
                     }
                     break;
                  case "VANILLA":
                     c(0L);
                     break;
                  case "HYPIXEL":
                     if (ScoreboardReader.v(0L)) {
                        if (this.isGetItemStack()) {
                           c(0L);
                        } else {
                           Q(0L);
                        }
                     }
                     break;
                  default:
                     Q(0L);
               }
            }
         }
      } else {
         c(0L);
      }
   }


   static {
      a = 71119880394628L;
   }

   public InvMove(long var1) {
      super(((a ^ (var1)) ^ 110879857448364L));
      // add code
      this.declare("InvMove", Category.Movement, "Allows you to move around while opening a container");
      var1 = a ^ var1;
      this.Y = new CopyOnWriteArrayList<>();
      this.c = false;
   }


   public final void x(long var1, EventBus var3) {
      InvMoveBinder.I(var3, this);
   }


   public static void Q(long var0) {


      KeyBinding[] var10000 = new KeyBinding[7];
      var10000[0] = MinecraftRef.c((byte)0,0L).gameSettings.keyBindForward;
      var10000[1] = MinecraftRef.c((byte)0,0L).gameSettings.keyBindBack;
      var10000[2] = MinecraftRef.c((byte)0,0L).gameSettings.keyBindLeft;
      var10000[3] = MinecraftRef.c((byte)0,0L).gameSettings.keyBindRight;
      var10000[4] = MinecraftRef.c((byte)0,0L).gameSettings.keyBindJump;
      var10000[5] = MinecraftRef.c((byte)0,0L).gameSettings.keyBindSprint;
      var10000[6] = MinecraftRef.c((byte)0,0L).gameSettings.keyBindJump;
      KeyBinding[] var7 = var10000;

      for (KeyBinding var11 : var7) {
         KeyBindUtil.A(82009306480869L, var11.getKeyCode(), false);
      }
   }




   public void A(long var1) {
      this.c = false;
   }


   private boolean isGetItemStack() {
      return f.thePlayer != null && f.thePlayer.openContainer != null && f.thePlayer.inventory.getItemStack() == null && !this.d$r2();
   }
   static {
      // add code
      clickgui = new BooleanSetting("ClickGUI", true);
   }
   static {
      // add code
      containerMode = new ModeSetting("Container-mode", "LEGIT", "HYPIXEL", "VANILLA", "NONE");
      inventoryMode = new ModeSetting("Inventory-mode", "LEGIT", "HYPIXEL", "VANILLA", "NONE");
   }
}
