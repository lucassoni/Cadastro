
public class Medico extends Pessoa {
    private String endereco;
    private String imagem;

    public Medico(String nome, String telefone, String endereco, String imagem) {
        super(nome, telefone);
        this.endereco = endereco;
        this.imagem = imagem;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getEndereco() {
        return this.endereco;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    public String getImagem() {
        return this.imagem;
    }

    public void imprimeMedico() {
        System.out.println("\nNome: ");
        System.out.println(getNome());

        System.out.println("Telefone: ");
        System.out.println(getTelefone());

        System.out.println("Endereco: ");
        System.out.println(getEndereco());
        System.out.println();

        try {
            Listagem.DisplayImage(this.imagem);
        } catch (Exception e) {
            //Display stack trace
            e.printStackTrace();
        }
    }

}