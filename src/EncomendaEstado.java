//sem warnings que possam causar problemas

public class EncomendaEstado {
    private String S_Estado;
    private int I_Estado;

    public EncomendaEstado(String nome, int numero) {
        this.S_Estado = nome;
        this.I_Estado = numero;
    }

    public EncomendaEstado() {
        this.S_Estado = "";
        this.I_Estado = 0;
    }

    public String getS_Estado() {
        return S_Estado;
    }

    public int getI_Estado() {
        return I_Estado;
    }

    public void setS_Estado(String S_Estado) {
        switch (S_Estado) {
            case "Paga" -> this.I_Estado = 1;
            case "Processamento" -> this.I_Estado = 2;
            case "Expedida" -> this.I_Estado = 3;
            case "Entregue" -> this.I_Estado = 4;
        }
        this.S_Estado = S_Estado;
    }

    public void setI_Estado(int I_Estado) {
        if (I_Estado == 0) {
            this.S_Estado = "";
        }
        else if (I_Estado == 1) {
            this.S_Estado = "Paga";
        }
        else if (I_Estado == 2){
            this.S_Estado = "Processamento";
        }
        else if (I_Estado == 3){
            this.S_Estado = "Expedida";
        }
        else {
            this.S_Estado = "Entregue";
            I_Estado = 4;
        }
        this.I_Estado = I_Estado;
    }
}

