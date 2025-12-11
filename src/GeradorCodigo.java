//sem warnings que possam causar problemas

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
public class GeradorCodigo {
    private static final String CHARS = "0123456789";
    private static final int CODE_LENGTH = 8;
    private static List<String> codigosGerados = new ArrayList<>();

    public GeradorCodigo () {
        codigosGerados = null;
    }
    public GeradorCodigo (GeradorCodigo code){
        codigosGerados = code.getCodigosGerados();
    }
    public GeradorCodigo (List<String> codigos){
        codigosGerados = codigos;
    }

    public void add_codigo (String code){
        codigosGerados.add(code);
    }

    public List<String> getCodigosGerados (){
        return codigosGerados;
    }

    public void setCodigosGerados(List<String> codigos){
        codigosGerados = codigos;
    }
    public static void addCode(String code){
        codigosGerados.add(code);
    }
    public static String gerarCodigo() {
        Random random = new Random();
        String codigo;

        do {
            StringBuilder sb = new StringBuilder(CODE_LENGTH);

            for (int i = 0; i < CODE_LENGTH; i++) {
                sb.append(CHARS.charAt(random.nextInt(CHARS.length())));
            }

            codigo = sb.toString();
        } while (codigosGerados.contains(codigo));

        codigosGerados.add(codigo);

        return codigo;
    }
}
