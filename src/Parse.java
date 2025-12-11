import java.io.*;


public class Parse {
    Vintage vintage = new Vintage();

    public Vintage parse() {
        try (FileInputStream fileIn = new FileInputStream("data.obj")) {
            ObjectInputStream input = new ObjectInputStream(fileIn);
            Object info;
            while ((info = input.readObject()) != null) {
                switch(info.getClass().getSimpleName()) {
                    case "Artigos":
                        Artigos artigos = (Artigos) info;
                        vintage.add_Stock(artigos);
                        GeradorCodigo.addCode(artigos.getCode());
                        break;
                    case "Comprador":
                        Comprador comprador = (Comprador) info;
                        vintage.add_Rich(comprador);
                        break;
                    case "Vendedor":
                        Vendedor vendedor = (Vendedor) info;
                        vintage.add_Poor(vendedor);
                        break;
                    case "Transportadora":
                        Transportadora transporte = (Transportadora) info;
                        vintage.add_Deliver(transporte);
                        break;
                    case "Encomenda":
                        Encomenda encomenda = (Encomenda) info;
                        vintage.add_Encomendas(encomenda);
                    default:
                        throw new IllegalArgumentException("ERRO");
                }
            }
            fileIn.close();
            input.close();
        } catch (EOFException e) {
            System.out.println("Finalizado o parse");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return vintage;
    }
}