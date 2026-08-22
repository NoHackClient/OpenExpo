package Expo.ASM.Util;

import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.MethodNode;

public interface MethodPredicate {
   boolean x(ClassNode var1, MethodNode var2);
}
