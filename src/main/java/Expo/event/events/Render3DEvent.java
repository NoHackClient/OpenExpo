package Expo.event.events;

import Expo.event.Event;
import net.minecraft.client.gui.ScaledResolution;

public class Render3DEvent extends Event {
   public final ScaledResolution O;
   public float j;
   private static final long a = 89522927187898L;

   public Render3DEvent(float var1, ScaledResolution var4) {
      super();
      this.j = var1;
      this.O = var4;
   }}
