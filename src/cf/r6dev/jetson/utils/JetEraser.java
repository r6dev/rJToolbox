package cf.r6dev.jetson.utils;

import org.jetbrains.annotations.NotNull;

import java.io.File;

public class JetEraser {
    public static void main(String[] args) {
        if (args != null) {
            if (args.length == 1) {
                File potentialTarget = new File(args[0]);

                if (potentialTarget.exists()) {
                    if (!erase(potentialTarget)) {
                        System.err.println("JetEraser: Could not erase: " + potentialTarget.getName());
                    }
                }
            }
        }
    }

    public static boolean erase(@NotNull File target) {
        if (target.isFile()) {
            return target.delete();
        } else if (target.isDirectory()) {
            File[] targetChildren = target.listFiles();

            if (targetChildren != null) {
                for (File child : targetChildren) {
                    if (child.isDirectory()) {
                        erase(child);
                    } else if (child.isFile()) {
                        if (!child.delete()) {
                            System.err.println("JetEraser: Error: Could not erase: " + child.getParentFile().getName() + "\\" + child.getName());
                        }
                    }
                }
            } else {
                return target.delete();
            }
        }

        return false;
    }
}
