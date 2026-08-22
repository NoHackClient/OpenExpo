import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.commons.Remapper;
import org.objectweb.asm.commons.RemappingClassAdapter;

public class InnerAwareAdapter extends RemappingClassAdapter {

    public InnerAwareAdapter(ClassVisitor cv, Remapper remapper) {
        super(cv, remapper);
    }

    @Override
    public void visitInnerClass(String name, String outerName, String innerName, int access) {
        String mappedName = remapper.mapType(name);
        String mappedOuter = outerName == null ? null : remapper.mapType(outerName);
        String mappedInner = innerName;
        if (innerName != null) {
            int dollar = mappedName.lastIndexOf('$');
            mappedInner = dollar >= 0
                    ? mappedName.substring(dollar + 1)
                    : mappedName.substring(mappedName.lastIndexOf('/') + 1);
        }
        cv.visitInnerClass(mappedName, mappedOuter, mappedInner, access);
    }
}
