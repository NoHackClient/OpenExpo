package Expo.event.events;

import Expo.event.Event;
import net.minecraft.client.gui.ScaledResolution;

public class Render2DEvent extends Event {
   private static final long a = 98039059035453L;
   public float r;
   public final ScaledResolution C;

   public Render2DEvent(int var1, short var2, float var3, short var4, ScaledResolution var5) {
      super();
      this.r = var3;
      this.C = var5;
   }}
