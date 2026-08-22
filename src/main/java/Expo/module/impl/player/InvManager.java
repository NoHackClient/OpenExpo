package Expo.module.impl.player;

import Expo.module.Category;

import Expo.event.EventBus;
import Expo.event.EventSubscriber;
import Expo.event.binder.InvManagerBinder;
import Expo.event.events.DrawScreenEvent;
import Expo.event.events.PickUpItemEvent;
import Expo.event.events.PreUpdateEvent;
import Expo.module.PriorityModule;
import Expo.setting.settings.BooleanSetting;
import Expo.setting.settings.HeaderSetting;
import Expo.setting.settings.ModeSetting;
import Expo.setting.settings.NumberSetting;
import Expo.setting.settings.TextSetting;
import Expo.util.ItemUtil;
import Expo.util.KeyBindUtil;
import Expo.util.MathUtil;
import Expo.util.Pair;
import Expo.util.TimerUtil;
import Expo.util.packet.PacketManager;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.spec.InvalidKeySpecException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.init.Items;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemEgg;
import net.minecraft.item.ItemEnderPearl;
import net.minecraft.item.ItemFireball;
import net.minecraft.item.ItemFishingRod;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemPotion;
import net.minecraft.item.ItemShears;
import net.minecraft.item.ItemSnowball;
import net.minecraft.item.ItemSpade;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.item.ItemTool;
import net.minecraft.network.play.client.C0DPacketCloseWindow;
import net.minecraft.network.play.client.C16PacketClientStatus.EnumState;
import net.minecraft.network.play.client.C16PacketClientStatus;
import org.lwjgl.input.Keyboard;


public class InvManager extends PriorityModule implements EventSubscriber {
   private static Map hb;
   private static long ob;
   public static NumberSetting maxTrashThrows;
   public static NumberSetting projectilesSlot;
   public static NumberSetting shovelSlot;
   private static String[] bb;
   public static TextSetting silentKey;
   private static long R;
   public static NumberSetting axeSlot;
   public static HeaderSetting D;
   private boolean J;
   public static BooleanSetting projectilesIsTrash;
   public static NumberSetting swordSlot;
   public static NumberSetting maxArrowSlots;
   public static HeaderSetting h;
   private static Map nb;
   public static BooleanSetting potionIsTrash;
   public static BooleanSetting normalFoodIsTrash;
   public static BooleanSetting onlySortOnce;
   private static String[] gb;
   public static NumberSetting startDelay;
   private static long[] ib;
   public static BooleanSetting onlyItemsConfiguredAreTrash;
   public static NumberSetting minDelay;
   private final TimerUtil o;
   public static NumberSetting enderPearlSlot;
   public static NumberSetting shearsSlot;
   public static NumberSetting pickaxeSlot;
   public static NumberSetting blockSlot;
   public static NumberSetting bowSlot;
   public static BooleanSetting toolsAreTrash;
   public static ModeSetting silentMode;
   public static NumberSetting fireballSlot;
   public static HeaderSetting M;
   private boolean L;
   public static NumberSetting foodSlot;
   public static BooleanSetting autoClose;
   public static BooleanSetting bowIsTrash;
   public static NumberSetting maxBlockSlots;
   public static NumberSetting potionSlot;
   public static BooleanSetting throwTrash;
   public static ModeSetting mode;
   public static BooleanSetting autoArmor;
   public static NumberSetting maxDelay;


   private void r(char var1, int var2) {
      new Thread(
            () -> {
               int var7 = 43868;


               int var20 = 235;

               if (this.o.L(ob, true)) {
                  this.T(true);

                  try {
                     if (onlySortOnce.c()) {
                        this.L = true;
                     }

                     Thread.sleep((long)startDelay.L());
                     List var23 = ItemUtil.O(0L, f.thePlayer.inventory);
                     this.K(var23, 56263869791253L);
                     this.S(8978, 16269485, (byte)var20);
                     if (throwTrash.c()) {
                        int var24 = 0;
                        int var25 = 0;
                        int var26 = 0;

                        for (int var27 = 0; var27 < f.thePlayer.inventory.getSizeInventory(); var27++) {
                           ItemStack var28 = f.thePlayer.inventory.getStackInSlot(var27);
                           if (var28 != null) {
                              this.K(ItemUtil.O(0L, f.thePlayer.inventory), 56263869791253L);
                              this.S(8978, 16269485, (byte)var20);
                              if (this.m(0L)) {
                                 return;
                              }

                              if (var24 >= maxTrashThrows.L()) {
                                 return;
                              }

                              var23 = ItemUtil.O(0L, f.thePlayer.inventory);
                              if (var28.getItem() instanceof ItemArmor && ((ItemArmor)var28.getItem()).armorType == 0) {
                                 if (ItemUtil.M(var28) <= ItemUtil.M((ItemStack)((Pair)var23.get(0)).a()) && var27 != 39) {
                                    ItemUtil.c(8537, 12546, (char)var7, var27);
                                    var24++;
                                    Thread.sleep((long)MathUtil.h(minDelay.L(), maxDelay.L()));
                                 }
                              } else if (var28.getItem() instanceof ItemArmor && ((ItemArmor)var28.getItem()).armorType == 1) {
                                 if (ItemUtil.M(var28) <= ItemUtil.M((ItemStack)((Pair)var23.get(1)).a()) && var27 != 38) {
                                    ItemUtil.c(8537, 12546, (char)var7, var27);
                                    var24++;
                                    Thread.sleep((long)MathUtil.h(minDelay.L(), maxDelay.L()));
                                 }
                              } else if (var28.getItem() instanceof ItemArmor && ((ItemArmor)var28.getItem()).armorType == 2) {
                                 if (ItemUtil.M(var28) <= ItemUtil.M((ItemStack)((Pair)var23.get(2)).a()) && var27 != 37) {
                                    ItemUtil.c(8537, 12546, (char)var7, var27);
                                    var24++;
                                    Thread.sleep((long)MathUtil.h(minDelay.L(), maxDelay.L()));
                                 }
                              } else if (var28.getItem() instanceof ItemArmor && ((ItemArmor)var28.getItem()).armorType == 3) {
                                 if (ItemUtil.M(var28) <= ItemUtil.M((ItemStack)((Pair)var23.get(3)).a()) && var27 != 36) {
                                    ItemUtil.c(8537, 12546, (char)var7, var27);
                                    var24++;
                                    Thread.sleep((long)MathUtil.h(minDelay.L(), maxDelay.L()));
                                 }
                              } else if (var28.getItem() instanceof ItemSword && (Integer)ItemUtil.q(45121668772412L, f.thePlayer.inventory).p() != var27) {
                                 ItemUtil.c(8537, 12546, (char)var7, var27);
                                 var24++;
                                 Thread.sleep((long)MathUtil.h(minDelay.L(), maxDelay.L()));
                              } else if ((
                                    var28.getItem() instanceof ItemSnowball
                                       || var28.getItem() instanceof ItemEgg
                                       || var28.getItem() instanceof ItemFishingRod
                                       || var28.getItem() instanceof ItemEnderPearl
                                 )
                                 && projectilesIsTrash.c()) {
                                 ItemUtil.c(8537, 12546, (char)var7, var27);
                                 var24++;
                                 Thread.sleep((long)MathUtil.h(minDelay.L(), maxDelay.L()));
                              } else if ((
                                    !(var28.getItem() instanceof ItemBow)
                                       || ItemUtil.O(f.thePlayer.inventory).p() == null
                                       || ItemUtil.O(f.thePlayer.inventory).p() == var27
                                 )
                                 && (!bowIsTrash.c() || !(var28.getItem() instanceof ItemBow) && var28.getItem() != Items.arrow)) {
                                 if (!(var28.getItem() instanceof ItemFood)
                                    || (!normalFoodIsTrash.c() || var28.getItem() == Items.golden_apple) && ItemUtil.k(f.thePlayer.inventory).p() == var27) {
                                    if (potionIsTrash.c() && var28.getItem() instanceof ItemPotion) {
                                       ItemUtil.c(8537, 12546, (char)var7, var27);
                                       var24++;
                                       Thread.sleep((long)MathUtil.h(minDelay.L(), maxDelay.L()));
                                    } else if (var28.getItem() instanceof ItemTool) {
                                       if (toolsAreTrash.c()) {
                                          ItemUtil.c(8537, 12546, (char)var7, var27);
                                          var24++;
                                          Thread.sleep((long)MathUtil.h(minDelay.L(), maxDelay.L()));
                                       } else if (var28.getItem() instanceof ItemPickaxe) {
                                          if ((Integer)((Pair)ItemUtil.D(f.thePlayer.inventory, (short)0).get(0)).p() != var27) {
                                             ItemUtil.c(8537, 12546, (char)var7, var27);
                                             var24++;
                                             Thread.sleep((long)MathUtil.h(minDelay.L(), maxDelay.L()));
                                          }
                                       } else if (var28.getItem() instanceof ItemAxe) {
                                          if ((Integer)((Pair)ItemUtil.D(f.thePlayer.inventory, (short)0).get(1)).p() != var27) {
                                             ItemUtil.c(8537, 12546, (char)var7, var27);
                                             var24++;
                                             Thread.sleep((long)MathUtil.h(minDelay.L(), maxDelay.L()));
                                          }
                                       } else if (var28.getItem() instanceof ItemSpade
                                          && (Integer)((Pair)ItemUtil.D(f.thePlayer.inventory, (short)0).get(2)).p() != var27) {
                                          ItemUtil.c(8537, 12546, (char)var7, var27);
                                          var24++;
                                          Thread.sleep((long)MathUtil.h(minDelay.L(), maxDelay.L()));
                                       }
                                    } else if (var28.getItem() instanceof ItemShears) {
                                       if (ItemUtil.W(f.thePlayer.inventory).p() != var27) {
                                          ItemUtil.c(8537, 12546, (char)var7, var27);
                                          var24++;
                                          Thread.sleep((long)MathUtil.h(minDelay.L(), maxDelay.L()));
                                       }
                                    } else if (ItemUtil.u(var28)) {
                                       if (var25 >= maxBlockSlots.L()) {
                                          ItemUtil.c(8537, 12546, (char)var7, var27);
                                          Thread.sleep((long)MathUtil.h(minDelay.L(), maxDelay.L()));
                                       }

                                       var25++;
                                       var24++;
                                    } else if (var28.getItem() == Items.arrow) {
                                       if (var26 >= maxArrowSlots.L()) {
                                          ItemUtil.c(8537, 12546, (char)var7, var27);
                                          Thread.sleep((long)MathUtil.h(minDelay.L(), maxDelay.L()));
                                       }

                                       var26++;
                                       var24++;
                                    } else if (!onlyItemsConfiguredAreTrash.c()
                                       && !(var28.getItem() instanceof ItemPotion)
                                       && var28.getItem() != Items.arrow
                                       && !(var28.getItem() instanceof ItemTool)
                                       && !(var28.getItem() instanceof ItemShears)
                                       && !(var28.getItem() instanceof ItemSword)
                                       && !(var28.getItem() instanceof ItemFood)
                                       && !(var28.getItem() instanceof ItemBow)
                                       && !(var28.getItem() instanceof ItemArmor)
                                       && !ItemUtil.u(var28)
                                       && !(var28.getItem() instanceof ItemSnowball)
                                       && !(var28.getItem() instanceof ItemEgg)
                                       && !(var28.getItem() instanceof ItemFishingRod)
                                       && !(var28.getItem() instanceof ItemEnderPearl)
                                       && !(var28.getItem() instanceof ItemFireball)) {
                                       ItemUtil.c(8537, 12546, (char)var7, var27);
                                       var24++;
                                       Thread.sleep((long)MathUtil.h(minDelay.L(), maxDelay.L()));
                                    }
                                 } else {
                                    ItemUtil.c(8537, 12546, (char)var7, var27);
                                    var24++;
                                    Thread.sleep((long)MathUtil.h(minDelay.L(), maxDelay.L()));
                                 }
                              } else {
                                 ItemUtil.c(8537, 12546, (char)var7, var27);
                                 var24++;
                                 Thread.sleep((long)MathUtil.h(minDelay.L(), maxDelay.L()));
                              }
                           }
                        }
                     }
                  } catch (InterruptedException var29) {
                     Expo.internal.restore.ExpoDiag.attribute(var29, "InvManager.r/3#0");
                  }

                  this.J = false;
                  this.closeScreen();
                  this.T(false);
               } else {
                  this.J = false;
               }
            }
         )
         .start();
   }

   private void s(int var1, short var2, int var3) {
      long var4 = ((long)var1 << 32 | (long)var2 << 48 >>> 32 | (long)var3 << 48 >>> 48) ^ R;
      int var6 = (int)((var4 ^ 131997502585545L) >>> 48);
      int var7 = (int)((var4 ^ 131997502585545L) << 16 >>> 32);
      if (!this.Y()) {
         this.T(false);
      } else {
         if (!this.J && !this.L) {
            this.J = true;
            this.r((char)var6, var7);
         }
      }
   }



   public final void x(long var1, EventBus var3) {
      InvManagerBinder.L(var3, this);
   }

   static {
      R = 2710522998551L;
      hb = new HashMap(13);
      bb = new String[]{"\u001d^\u009bM\u001b\u00b0\u00aad", "v\u00b2c2N\u00c9 \u008e\u00e7\u0093\u00cb\u00a0T\u00f9\u00b5\u00f3", "NGs\u00119\u00df\u00c6\u00ac", "\u00d4{V\u001f\u0092+\u001a\u00f9", "\u008d\u000c\u00bb@(\u00eb\nd", "\u00e8`wKD\u0082_$\u00f4:\u00db\u00fa\u00d2H\u00a2\u00f1", "_\u00cb\u00c3\u00bd\u00cdC\u00ab$\u000e\u008c\u00c5\u0080\u00a5\u00ebp-"};
      gb = new String[7];
      nb = new HashMap(13);
      ib = new long[]{-2670471100074826616L, -5966574634042417658L, -7277742583667841876L, -3369840771532992298L, -2466407860972894568L, -3491299397836251658L, -2251256513271661673L, -4997704363658310359L, 5609941588006067246L, 624057533236308340L, 7343629218183677455L, 4974517030527135104L};
      ob = 10L;
   }

   private void closeScreen() {
      if (autoClose.c()) {
         if (f.currentScreen != null) {
            f.thePlayer.closeScreen();
         } else if (mode.R("SILENT")) {
            PacketManager.b(new C0DPacketCloseWindow(f.thePlayer.inventoryContainer.windowId));
         }
      }

      this.T(false);
   }

   private void S(int var1, int var2, byte var3) throws InterruptedException {
      long var4 = ((long)var1 << 32 | (long)var2 << 40 >>> 32 | (long)var3 << 56 >>> 56) ^ R;
      int var10 = (int)((var4 ^ 260478157012L) >>> 48);
      long var13 = var4 ^ 9261468610752L;
      if (!this.m(0L)) {
         if (swordSlot.L() != 0.0F
            && ItemUtil.q(var13, f.thePlayer.inventory).p() != null
            && (int)swordSlot.L() - 1 != (Integer)ItemUtil.q(var13, f.thePlayer.inventory).p()) {
            ItemUtil.Q(0L, (Integer)ItemUtil.q(var13, f.thePlayer.inventory).p(), (int)swordSlot.L() - 37);
            Thread.sleep((long)MathUtil.h(minDelay.L(), maxDelay.L()));
         }

         if (!this.m(0L)) {
            if (projectilesSlot.L() != 0.0F && !projectilesIsTrash.c() && ItemUtil.o(f.thePlayer.inventory).p() != null && (int)projectilesSlot.L() - 1 != ItemUtil.o(f.thePlayer.inventory).p()) {
               ItemUtil.Q(0L, ItemUtil.o(f.thePlayer.inventory).p(), (int)projectilesSlot.L() - 37);
               Thread.sleep((long)MathUtil.h(minDelay.L(), maxDelay.L()));
            }

            if (!this.m(0L)) {
               if (blockSlot.L() != 0.0F && ItemUtil.Y(f.thePlayer.inventory).p() != null && (int)blockSlot.L() - 1 != ItemUtil.Y(f.thePlayer.inventory).p()) {
                  ItemUtil.Q(0L, ItemUtil.Y(f.thePlayer.inventory).p(), (int)blockSlot.L() - 37);
                  Thread.sleep((long)MathUtil.h(minDelay.L(), maxDelay.L()));
               }

               if (!this.m(0L)) {
                  if (bowSlot.L() != 0.0F && !bowIsTrash.c() && ItemUtil.O(f.thePlayer.inventory).p() != null && (int)bowSlot.L() - 1 != ItemUtil.O(f.thePlayer.inventory).p()
                     )
                   {
                     ItemUtil.Q(0L, ItemUtil.O(f.thePlayer.inventory).p(), (int)bowSlot.L() - 37);
                     Thread.sleep((long)MathUtil.h(minDelay.L(), maxDelay.L()));
                  }

                  if (!toolsAreTrash.c()) {
                     List var15 = ItemUtil.D(f.thePlayer.inventory, (short)var10);

                     for (int var16 = 0; var16 < 3; var16++) {
                        switch (var16) {
                           case 0:
                              if (this.m(0L)) {
                                 return;
                              }

                              if (pickaxeSlot.L() != 0.0F && ((Pair)var15.get(0)).p() != null && (int)pickaxeSlot.L() - 1 != (Integer)((Pair)var15.get(0)).p()) {
                                 ItemUtil.Q(0L, (Integer)((Pair)var15.get(0)).p(), (int)pickaxeSlot.L() - 37);
                                 Thread.sleep((long)MathUtil.h(minDelay.L(), maxDelay.L()));
                              }
                              break;
                           case 1:
                              if (this.m(0L)) {
                                 return;
                              }

                              if (axeSlot.L() != 0.0F && ((Pair)var15.get(1)).p() != null && (int)axeSlot.L() - 1 != (Integer)((Pair)var15.get(1)).p()) {
                                 ItemUtil.Q(0L, (Integer)((Pair)var15.get(1)).p(), (int)axeSlot.L() - 37);
                                 Thread.sleep((long)MathUtil.h(minDelay.L(), maxDelay.L()));
                              }
                              break;
                           case 2:
                              if (this.m(0L)) {
                                 return;
                              }

                              if (shovelSlot.L() != 0.0F && ((Pair)var15.get(2)).p() != null && (int)shovelSlot.L() - 1 != (Integer)((Pair)var15.get(2)).p()) {
                                 ItemUtil.Q(0L, (Integer)((Pair)var15.get(2)).p(), (int)shovelSlot.L() - 37);
                                 Thread.sleep((long)MathUtil.h(minDelay.L(), maxDelay.L()));
                              }
                        }
                     }
                  }

                  if (!this.m(0L)) {
                     if (foodSlot.L() != 0.0F
                        && ItemUtil.k(f.thePlayer.inventory).p() != null
                        && (int)foodSlot.L() - 1 != ItemUtil.k(f.thePlayer.inventory).p()
                        && (!normalFoodIsTrash.c() || ItemUtil.k(f.thePlayer.inventory).a().getItem() == Items.golden_apple)) {
                        ItemUtil.Q(0L, ItemUtil.k(f.thePlayer.inventory).p(), (int)foodSlot.L() - 37);
                        Thread.sleep((long)MathUtil.h(minDelay.L(), maxDelay.L()));
                     }

                     if (!this.m(0L)) {
                        if (potionSlot.L() != 0.0F
                           && !potionIsTrash.c()
                           && ItemUtil.H(f.thePlayer.inventory).p() != null
                           && (int)potionSlot.L() - 1 != ItemUtil.H(f.thePlayer.inventory).p()) {
                           ItemUtil.Q(0L, ItemUtil.H(f.thePlayer.inventory).p(), (int)potionSlot.L() - 37);
                           Thread.sleep((long)MathUtil.h(minDelay.L(), maxDelay.L()));
                        }

                        if (!this.m(0L)) {
                           if (fireballSlot.L() != 0.0F && ItemUtil.i(f.thePlayer.inventory).p() != null && (int)fireballSlot.L() - 1 != ItemUtil.i(f.thePlayer.inventory).p()) {
                              ItemUtil.Q(0L, ItemUtil.i(f.thePlayer.inventory).p(), (int)fireballSlot.L() - 37);
                              Thread.sleep((long)MathUtil.h(minDelay.L(), maxDelay.L()));
                           }

                           if (!this.m(0L)) {
                              if (enderPearlSlot.L() != 0.0F
                                 && ItemUtil.F(f.thePlayer.inventory).p() != null
                                 && (int)enderPearlSlot.L() - 1 != ItemUtil.F(f.thePlayer.inventory).p()) {
                                 ItemUtil.Q(0L, ItemUtil.F(f.thePlayer.inventory).p(), (int)enderPearlSlot.L() - 37);
                                 Thread.sleep((long)MathUtil.h(minDelay.L(), maxDelay.L()));
                              }

                              if (!this.m(0L)) {
                                 if (shearsSlot.L() != 0.0F
                                    && ItemUtil.W(f.thePlayer.inventory).p() != null
                                    && (int)shearsSlot.L() - 1 != ItemUtil.W(f.thePlayer.inventory).p()) {
                                    ItemUtil.Q(0L, ItemUtil.W(f.thePlayer.inventory).p(), (int)shearsSlot.L() - 37);
                                    Thread.sleep((long)MathUtil.h(minDelay.L(), maxDelay.L()));
                                 }
                              }
                           }
                        }
                     }
                  }
               }
            }
         }
      }
   }

   public void onPreUpdate(PreUpdateEvent var1, long var2) {





      if (!(f.currentScreen instanceof GuiInventory)) {
         this.L = false;
      }

      if (f.currentScreen == null && mode.R("SILENT") && silentMode.R("KEY") && KeyBindUtil.V(Keyboard.getKeyIndex(silentKey.X().toUpperCase()), 64165991731362L)) {
         PacketManager.b(new C16PacketClientStatus(EnumState.OPEN_INVENTORY_ACHIEVEMENT));
         f.displayGuiScreen(new GuiInventory(f.thePlayer));
         this.s(23305, (short)32017, 51151);
      } else {
         switch (mode.Y()) {
            case "SILENT":
               if (silentMode.R("OPEN_INV")) {
                  if (f.currentScreen instanceof GuiInventory) {
                     this.s(23305, (short)32017, 51151);
                  } else {
                     this.J = false;
                  }
               }
               break;
            case "OPEN_INV":
               if (f.currentScreen instanceof GuiInventory) {
                  this.s(23305, (short)32017, 51151);
               } else {
                  this.J = false;
               }
         }
      }
   }

   public String g(long var1) {
      if (startDelay.L() == 0.0F && minDelay.L() == 0.0F && maxDelay.L() == 0.0F) {
         return "INSTANT";
      } else {
         return minDelay.L() == maxDelay.L() ? String.valueOf((int)minDelay.L()) : (int)minDelay.L() + "-" + (int)maxDelay.L();
      }
   }

   public void onDrawScreen(char var1, int var2, int var3, DrawScreenEvent var4) {
      long var5 = ((long)var1 << 48 | (long)var2 << 32 >>> 16 | (long)var3 << 48 >>> 48) ^ R;
      int var7 = (int)((var5 ^ 99695519223030L) >>> 32);
      long var8 = (var5 ^ 99695519223030L) << 32 >>> 32;
      if (f.currentScreen instanceof GuiInventory && mode.R("SILENT") && this.J) {
         var4.I(var7, var8);
      }
   }

   public void onPickUpItem(PickUpItemEvent var1, long var2) throws UnsupportedEncodingException, InvalidAlgorithmParameterException, InvalidKeyException, InvalidKeySpecException, BadPaddingException, IllegalBlockSizeException {




      if (var1.F instanceof EntityPlayerSP && var1.P != null && mode.R("SILENT") && silentMode.R("PICK_ITEM")) {
         PacketManager.b(new C16PacketClientStatus(EnumState.OPEN_INVENTORY_ACHIEVEMENT));
         f.displayGuiScreen(new GuiInventory(f.thePlayer));
         this.s(23305, (short)32017, 51151);
      }
   }

   public InvManager(long var1) {
      super((((R ^ (var1)) ^ 45489666346938L) >>> 16), (char)((int)(((((R ^ (var1)) ^ 45489666346938L) << 48) >>> 48))));
      // add code
      this.declare("InvManager", Category.Player, "Clean and manage your inventory");
      var1 = R ^ var1;
      this.o = new TimerUtil();
      this.J = false;
      this.L = false;
   }

   private boolean m(long var1) {

      if (f.currentScreen instanceof GuiInventory && this.o() && !KeyBindUtil.V(1, 64165991731362L)) {
         return false;
      }

      this.J = false;
      this.T(false);
      return true;
   }


   private static void a() {
   }

   private void K(List var1, long var2) throws InterruptedException {




      if (autoArmor.c()) {
         for (int var11 = 0; var11 < 4; var11++) {
            switch (var11) {
               case 0:
                  if (this.m(0L)) {
                     return;
                  }

                  if (((Pair)var1.get(0)).p() != null
                     && (Integer)((Pair)var1.get(0)).p() != 39
                     && ItemUtil.M((ItemStack)((Pair)var1.get(0)).a()) > ItemUtil.M(f.thePlayer.inventory.getStackInSlot(39))) {
                     if (f.thePlayer.getEquipmentInSlot(4) != null) {
                        ItemUtil.c(8537, 12546, (char)43868, 39);
                     }

                     Thread.sleep((long)MathUtil.h(minDelay.L(), maxDelay.L()));
                     if (this.m(0L)) {
                        return;
                     }

                     ItemUtil.B(0L, (Integer)((Pair)var1.get(0)).p());
                     Thread.sleep((long)MathUtil.h(minDelay.L(), maxDelay.L()));
                  }
                  break;
               case 1:
                  if (this.m(0L)) {
                     return;
                  }

                  if (((Pair)var1.get(1)).p() != null
                     && (Integer)((Pair)var1.get(1)).p() != 38
                     && ItemUtil.M((ItemStack)((Pair)var1.get(1)).a()) > ItemUtil.M(f.thePlayer.inventory.getStackInSlot(38))) {
                     if (f.thePlayer.getEquipmentInSlot(3) != null) {
                        ItemUtil.c(8537, 12546, (char)43868, 38);
                     }

                     Thread.sleep((long)MathUtil.h(minDelay.L(), maxDelay.L()));
                     if (this.m(0L)) {
                        return;
                     }

                     ItemUtil.B(0L, (Integer)((Pair)var1.get(1)).p());
                     Thread.sleep((long)MathUtil.h(minDelay.L(), maxDelay.L()));
                  }
                  break;
               case 2:
                  if (this.m(0L)) {
                     return;
                  }

                  if (((Pair)var1.get(2)).p() != null
                     && (Integer)((Pair)var1.get(2)).p() != 37
                     && ItemUtil.M((ItemStack)((Pair)var1.get(2)).a()) > ItemUtil.M(f.thePlayer.inventory.getStackInSlot(37))) {
                     if (f.thePlayer.getEquipmentInSlot(2) != null) {
                        ItemUtil.c(8537, 12546, (char)43868, 37);
                     }

                     Thread.sleep((long)MathUtil.h(minDelay.L(), maxDelay.L()));
                     if (this.m(0L)) {
                        return;
                     }

                     ItemUtil.B(0L, (Integer)((Pair)var1.get(2)).p());
                     Thread.sleep((long)MathUtil.h(minDelay.L(), maxDelay.L()));
                  }
                  break;
               case 3:
                  if (this.m(0L)) {
                     return;
                  }

                  if (((Pair)var1.get(3)).p() != null
                     && (Integer)((Pair)var1.get(3)).p() != 36
                     && ItemUtil.M((ItemStack)((Pair)var1.get(3)).a()) > ItemUtil.M(f.thePlayer.inventory.getStackInSlot(36))) {
                     if (f.thePlayer.getEquipmentInSlot(1) != null) {
                        ItemUtil.c(8537, 12546, (char)43868, 36);
                     }

                     Thread.sleep((long)MathUtil.h(minDelay.L(), maxDelay.L()));
                     if (this.m(0L)) {
                        return;
                     }

                     ItemUtil.B(0L, (Integer)((Pair)var1.get(3)).p());
                     Thread.sleep((long)MathUtil.h(minDelay.L(), maxDelay.L()));
                  }
            }
         }
      }
   }
   static {
      // add code
      fireballSlot = new NumberSetting("Fireball-slot", 0.0F, 0.0F, 9.0F, 1.0F);
      // update new version
      D = new HeaderSetting("Trash settings");
      // update new version
      h = new HeaderSetting("Slots settings (0 = no sort)");
      // update new version
      M = new HeaderSetting("Max item slots settings");
      pickaxeSlot = new NumberSetting("Pickaxe-slot", 5.0F, 0.0F, 9.0F, 1.0F);
      shovelSlot = new NumberSetting("Shovel-slot", 7.0F, 0.0F, 9.0F, 1.0F);
      normalFoodIsTrash = new BooleanSetting("Normal-food-is-trash", true);
      maxArrowSlots = new NumberSetting("Max-arrow-slots", 10.0F, 1.0F, 36.0F, 1.0F);
      projectilesIsTrash = new BooleanSetting("Projectiles-is-trash", false);
      autoArmor = new BooleanSetting("Auto-armor", true);
      blockSlot = new NumberSetting("Block-slot", 2.0F, 0.0F, 9.0F, 1.0F);
      foodSlot = new NumberSetting("Food-slot", 9.0F, 0.0F, 9.0F, 1.0F);
      maxDelay = new NumberSetting("Max-delay", 50.0F, 0.0F, 1000.0F, 1.0F);
      onlyItemsConfiguredAreTrash = new BooleanSetting("Only-items-configured-are-trash", false);
      autoClose = new BooleanSetting("Auto-close", false);
      swordSlot = new NumberSetting("Sword-slot", 1.0F, 0.0F, 9.0F, 1.0F);
      minDelay = new NumberSetting("Min-delay", 50.0F, 0.0F, 1000.0F, 1.0F);
      enderPearlSlot = new NumberSetting("Ender-pearl-slot", 0.0F, 0.0F, 9.0F, 1.0F);
      toolsAreTrash = new BooleanSetting("Tools-are-trash", true);
      onlySortOnce = new BooleanSetting("Only-sort-once", false);
      maxBlockSlots = new NumberSetting("Max-block-slots", 10.0F, 1.0F, 36.0F, 1.0F);
      potionSlot = new NumberSetting("Potion-slot", 8.0F, 0.0F, 9.0F, 1.0F);
      bowIsTrash = new BooleanSetting("Bow-is-trash", true);
      potionIsTrash = new BooleanSetting("Potion-is-trash", true);
      mode = new ModeSetting("Mode", "OPEN_INV", "SILENT");
      silentKey = new TextSetting("Silent-key", "NONE");
      projectilesSlot = new NumberSetting("Projectiles-slot", 3.0F, 0.0F, 9.0F, 1.0F);
      axeSlot = new NumberSetting("Axe-slot", 6.0F, 0.0F, 9.0F, 1.0F);
      shearsSlot = new NumberSetting("Shears-slot", 0.0F, 0.0F, 9.0F, 1.0F);
      bowSlot = new NumberSetting("Bow-slot", 4.0F, 0.0F, 9.0F, 1.0F);
      // add code
      silentMode = new ModeSetting("Silent-mode", "KEY", "PICK_ITEM", "OPEN_INV");
      maxTrashThrows = new NumberSetting("Max-trash-throws", 36.0F, 1.0F, 36.0F, 1.0F);
      throwTrash = new BooleanSetting("Throw-trash", true);
      startDelay = new NumberSetting("Start-delay", 50.0F, 0.0F, 1000.0F, 1.0F);
   }
}
