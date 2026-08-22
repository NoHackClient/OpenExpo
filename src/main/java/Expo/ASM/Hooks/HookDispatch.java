package Expo.ASM.Hooks;

import Expo.ASM.Hooks.Player.KeyBindingHooks;
import Expo.ASM.Hooks.Player.MovementInputHooks;
import Expo.ASM.Hooks.Player.PlayerControllerHooks;
import Expo.ASM.Hooks.Render.LoadingScreenRendererHooks;
import Expo.ASM.Hooks.World.WorldHooks;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.spec.InvalidKeySpecException;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import net.minecraft.client.multiplayer.PlayerControllerMP;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.client.shader.Framebuffer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.MovementInput;
import net.minecraft.util.Vec3;

public class HookDispatch {
   private static final long a = 6474880236968L;

   public static void PlayerControllerMP$clickBlockReturn(BlockPos var0, EnumFacing var1, PlayerControllerMP var2, CallbackInfoReturnable<Boolean> var3) {
      PlayerControllerHooks.clickBlockReturn(var0, var3);
   }

   public static void Minecraft$onStartGame() {
      MinecraftHooks.onStartGame();
   }

   public static void PlayerControllerMP$onDamageBlock(BlockPos var0, EnumFacing var1, PlayerControllerMP var2, CallbackInfoReturnable<Boolean> var3) throws UnsupportedEncodingException, InvalidAlgorithmParameterException, InvalidKeyException, InvalidKeySpecException, BadPaddingException, IllegalBlockSizeException {
      PlayerControllerHooks.onDamageBlock(var0, var1, var2, var3);
   }

   public static void Minecraft$onLoadWorld() {
      MinecraftHooks.onLoadWorld();
   }

   public static boolean[] Minecraft$onPreMouseInput() {
      return MinecraftHooks.onPreMouseInput();
   }

   public static void Keybinding$onSetKeyBindState(int var0, boolean var1) {
      KeyBindingHooks.onSetKeyBindState(var0, var1);
   }

   static {
      a();
   }

   public static void PlayerControllerMP$onPlayerRightClick(
      WorldClient var0, ItemStack var1, BlockPos var2, EnumFacing var3, Vec3 var4, CallbackInfoReturnable<Boolean> var5
   ) {
      PlayerControllerHooks.onPlayerRightClick(var0, var1, var2, var3, var4, var5);
   }

   public static void Minecraft$changeCurrentItem(InventoryPlayer var0, int var1) {
      MinecraftHooks.changeCurrentItem(var0, var1);
   }

   public static void PlayerControllerMP$onPreStoppedUsingItem(CallbackInfo var0) {
      PlayerControllerHooks.onPreStoppedUsingItem(var0);
   }

   public static void Minecraft$onOptimizeWorldSwapping() {
      MinecraftHooks.onOptimizeWorldSwapping();
   }

   public static void Minecraft$onSetKeyBindState(int var0, boolean var1) {
      MinecraftHooks.onSetKeyBindState(var0, var1);
   }

   public static void LoadingScreenRenderer$forSkipProgress(int var0, CallbackInfo var1) {
      LoadingScreenRendererHooks.forSkipProgress(var0, var1);
   }

   public static void ItemInWorldManager$tryHarvestBlockHead(BlockPos var0) {
      PlayerControllerHooks.tryHarvestBlockHead(var0);
   }

   public static void Keybinding$isPressed(CallbackInfoReturnable<Boolean> var0, String var1, int var2) {
      KeyBindingHooks.isPressed(var0, var1, var2);
   }

   public static void Minecraft$onPostTick() {
      MinecraftHooks.onPostTick();
   }

   public static void Minecraft$onRightClickMouse(CallbackInfo var0) {
      MinecraftHooks.onRightClickMouse(var0);
   }

   public static void Keybinding$onTick(int var0) {
      KeyBindingHooks.onTick(var0);
   }

   public static void Minecraft$onPostRightClick() {
      MinecraftHooks.onPostRightClick();
   }

   public static void Minecraft$onClickMouse(CallbackInfo var0) {
      MinecraftHooks.onClickMouse(var0);
   }

   public static void Minecraft$onPreTick() {
      MinecraftHooks.onPreTick();
   }

   public static void PlayerControllerMP$onPostStoppedUsingItem() {
      PlayerControllerHooks.onPostStoppedUsingItem();
   }

   public static void PlayerControllerMP$onAttackEntity(Entity var0, CallbackInfo var1) {
      PlayerControllerHooks.onAttackEntity(var0, var1);
   }

   public static boolean Minecraft$notAllowUserInput() {
      return MinecraftHooks.notAllowUserInput();
   }

   public static IChatComponent Minecraft$onSaveScreenshot(File var0, int var1, int var2, Framebuffer var3) throws Throwable {
      return MinecraftHooks.onSaveScreenshot(var0, var1, var2, var3);
   }

   private static void a() {
   }

   public static void World$onEntityJoinWorld(Entity var0) {
      WorldHooks.onEntityJoinWorld(var0);
   }

   public static void Minecraft$onPostClickMouse() {
      MinecraftHooks.onPostClickMouse();
   }

   public static void MovementInputFromOptions$onUpdatePlayerMoveState(MovementInput var0, GameSettings var1, CallbackInfo var2) {
      MovementInputHooks.onUpdatePlayerMoveState(var0, var1, var2);
   }

   public static void PlayerControllerMP$onDamageBlockAfterSync(CallbackInfoReturnable<Boolean> var0) {
      PlayerControllerHooks.onDamageBlockAfterSync(var0);
   }
}
