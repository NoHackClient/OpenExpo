package Expo.event;

import Expo.event.events.StoppableEvent;
import Expo.internal.restore.ExpoDiag;
import Expo.ui.ModuleTagRenderer;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;

public class EventBus {
   private static long a;
   private final Map<Class<? extends Throwable>, Long> Y;
   private final Map<Class<?>, CopyOnWriteArrayList<ListenerBinding<?>>> P;
   private static long e;
   private final Map<Object, List<ListenerBinding<?>>> U = new ConcurrentHashMap<>();

   public void s(Object var1, long var2) {
      long var4 = var2 ^ 52528686719724L;
      if (var1 != null) {
         this.z(var4, var1);
         List var6 = this.U.get(var1);
         if (var6 != null) {
            for (ListenerBinding var8 : (Iterable<ListenerBinding>)(var6)) {
               ListenerBinding.S(var8, true);
            }

            ExpoDiag.subscribed(var1, var6);
         }
      }
   }

   public void e(Event var1, long var2) {




      if (var1 != null) {
         List var9 = this.P.get(var1.getClass());
         ExpoDiag.dispatch(var1, var9);
         if (var9 != null && !var9.isEmpty()) {
            for (ListenerBinding var11 : (Iterable<ListenerBinding>)(var9)) {
               if (ListenerBinding.o(var11)) {
                  ExpoDiag.delivered(var1, var11);
                  try {
                     v(var11, var1, 3958);
                  } catch (Throwable var13) {

                     

                     
                     ExpoDiag.busFailure(var1, var11, var13);
                     ExpoDiag.swallowed(var1, var11, var13);
                     this.O(var13, 128094061068843L);
                  }

                  if (var1.a() || var1 instanceof StoppableEvent && ((StoppableEvent)var1).p()) {
                     break;
                  }
               }
            }
         }
      }
   }

   private static void v(ListenerBinding var0, Object var1, int var2) throws UnsupportedEncodingException, Throwable, InvalidAlgorithmParameterException, InvalidKeyException, InvalidKeySpecException, BadPaddingException, IllegalBlockSizeException {
      long var5 = ((long)var2 << 32 | 383005339L) ^ a;
      long var7 = var5 ^ 1254117484529L;
      ListenerBinding.d(var0).c(var7, var1);
   }

   public synchronized void z(long var1, Object var3) {
      long var4 = var1 ^ 140543510993174L;
      if (var3 != null && !this.U.containsKey(var3)) {
         ArrayList var6 = new ArrayList();
         this.U.put(var3, var6);
         if (var3 instanceof EventSubscriber) {
            ((EventSubscriber)var3).x(var4, this);
         }
      }
   }

   private void O(Throwable var1, long var2) {


      if (ModuleTagRenderer.X) {
         long var6 = System.currentTimeMillis();
         Long var8 = this.Y.put((Class<? extends Throwable>)var1.getClass(), var6);
         if (var8 == null || var6 - var8 >= e) {
            StringWriter var9 = new StringWriter();
            var1.printStackTrace(new PrintWriter(var9));
            Expo.util.ClientUtil.t(48081174263320L, var9.toString());
         }
      }
   }

   public EventBus() {
      this.P = new ConcurrentHashMap<>();
      this.Y = new ConcurrentHashMap<>();
   }

   public void B(Object var1) {
      if (var1 != null) {
         List var2 = this.U.get(var1);
         if (var2 != null) {
            for (ListenerBinding var4 : (Iterable<ListenerBinding>)(var2)) {
               ListenerBinding.S(var4, false);
            }

            ExpoDiag.unsubscribed(var1, var2);
         }
      }
   }

   public void R(Object var1, Class var2, int var5, EventInvoker var6) {
      if (var1 != null && var2 != null && var6 != null) {
         List var7 = this.U.get(var1);
         if (var7 == null) {
            throw new IllegalStateException("Generated listener bound outside buildCache");
         }

         ListenerBinding var8 = new ListenerBinding(var6, var5);
         var7.add(var8);
         CopyOnWriteArrayList var9 = this.P.computeIfAbsent(var2, var0 -> new CopyOnWriteArrayList<>());
         var9.add(var8);
         var9.sort(Comparator.<ListenerBinding>comparingInt(var0 -> ListenerBinding.k(var0)).reversed());
      } else {
         throw new NullPointerException("owner, eventType and invoker are required");
      }
   }

   static {
      a = 34531714106406L;
      e = 500L;
   }

}
