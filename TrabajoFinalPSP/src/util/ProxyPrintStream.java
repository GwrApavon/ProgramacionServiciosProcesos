package util;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;


public class ProxyPrintStream extends PrintStream {    
    private PrintStream fileStream = null;
    private PrintStream originalPrintStream = null;
    
    /**
     * Constructor para crear una clase que escribe a la vez que por un PrintStream
     * por un fichero
     * @param out	El "PrintStream" por que queremos escribir "tradicionalmente" 
     * @param FilePath	El fichero en el que queremos escribir a la vez
     */
    public ProxyPrintStream(PrintStream out, String FilePath) {
        super(out);
        originalPrintStream = out;
         try {
             FileOutputStream fout = new FileOutputStream(FilePath,true);
             fileStream = new PrintStream(fout);
        } catch (FileNotFoundException e) {
                e.printStackTrace();
        }
    }    
    
    /**
     * Cuando imprimimos escribo también en el fichero
     */
    public void print(final String str) {
        originalPrintStream.print(str);
        fileStream.print(str);
    }
    
    /**
     * Cuando imprimimos escribo también en el fichero
     */
    public void println(final String str) {
        originalPrintStream.println(str);
        fileStream.println(str);
    }     
}
