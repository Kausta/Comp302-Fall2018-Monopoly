package cabernet1.monopoly.ui.util;

import javax.swing.filechooser.FileFilter;
import java.io.File;

public class JsonFileType extends FileFilter {
    @Override
    public boolean accept(File f) {
        if (f == null) {
            return false;
        }
        if (f.isDirectory()) {
            return true;
        }
        String name = f.getName();
        String extension = getExtensionFromFilename(name);
        return extension.equals("json");
    }

    @Override
    public String getDescription() {
        return "Monopoly Save Games {.json}";
    }

    private static String getExtensionFromFilename(String name) {
        int lastDot = name.lastIndexOf('.');
        if (lastDot == -1) {
            return name;
        }
        return name.substring(lastDot + 1).toLowerCase();
    }
}
