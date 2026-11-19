import com.android.tools.smali.baksmali.Baksmali;
import com.android.tools.smali.baksmali.BaksmaliOptions;
import com.android.tools.smali.dexlib2.Opcodes;
import com.android.tools.smali.dexlib2.dexbacked.DexBackedDexFile;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

public class DexKit {
    public static void main(String[] args) throws Exception {
        String cmd = args[0];
        if ("dis".equals(cmd)) {
            byte[] data = Files.readAllBytes(Paths.get(args[1]));
            DexBackedDexFile dex = new DexBackedDexFile(new Opcodes(34, 0), data, 0);
            BaksmaliOptions o = new BaksmaliOptions();
            o.localsDirective = false;
            o.sequentialLabels = false;
            o.debugInfo = false;
            Baksmali.disassembleDexFile(dex, new File(args[2]), 4, o);
            System.out.println("OK " + args[1] + " -> " + args[2]);
        }
    }
}
