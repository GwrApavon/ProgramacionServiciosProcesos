package util;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

public class StreamCapturer extends OutputStream {

    private StringBuilder buffer;
    private String prefix;
    private Consumer consumer;
    private PrintStream old;

    /**
     * Constructor de un capturador de Stream, que extiende de OutputStream
     * @param prefix	Prefijo que se mostrará en la salida
     * @param consumer	El lugar donde se va a imprimir la salida
     * @param old		El lugar donde "tradicionalmente" se imprimiría la salida
     */
    public StreamCapturer(String prefix, Consumer consumer, PrintStream old) {
        this.prefix = prefix;
        buffer = new StringBuilder(128);
        buffer.append("[").append(prefix).append("] ");
        this.old = old;
        this.consumer = consumer;
    }

    /**
     * Sobreescribo el método write para que además de escribir en la salida "tradicional"
     * escriba también en la salida personalizada (que es de tipo "Consumer")
     */
    @Override
    public void write(int b) throws IOException {
        char c = (char) b;
        String value = Character.toString(c);
        buffer.append(value);
        if (value.equals("\n")) {
            // cuando detecte salto de linea añado lo que hay en el buffer al "consumer"
            consumer.appendText(buffer.toString());
            // y lo preparo para la siguiente línea
            buffer.delete(0, buffer.length());
            buffer.append("[").append(prefix).append("] ");
        }
        old.print(c);
    }        
}    