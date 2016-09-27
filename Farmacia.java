package Exercicios;

import javax.swing.*;

/**
 * Created by handyc on 17/09/16.
 */

public class Farmacia {


    static final int MAX = 5; //constante para o vetor

    static Medicamento[] medicamento = new Medicamento[MAX];
    static Medicamento[] cestaDeCompras = new Medicamento[MAX];

    static float valorDaCompra = 0;
    static int controladorVet = 0;
    static int controladorVet2 = 0;
    static int codigo = 0;

    public static void main(String[] args) {
        fullVetor();
        do {
            String op = JOptionPane.showInputDialog("\t\tDrogarias CoffeeCode\n\n"
                    + "[1] - Cadastrar Medicamentos\n"
                    + "[2] - Consultar Medicamentos\n"
                    + "[3] - Adiciona na cesta\n"
                    + "[4] - Retira da cesta\n"
                    + "[5] - Realiza Pagamento\n"
                    + "[6] - Sair\n\n");
            try {
                switch (Integer.parseInt(op)) {
                    case 1: {
                        cadastrarMedicamento();
                    }
                    break;

                    case 2: {
                        do {
                            String nome = JOptionPane.showInputDialog("CONSULTA DE MEDICAMENTOS\n\n");
                            consultaMedicamento(nome);
                        }while (JOptionPane.showConfirmDialog(null, "Deseja consultar mais algum medicamento?") == JOptionPane.YES_OPTION);
                    }
                    break;

                    case 3: {
                        do {
                            adicionaNaCesta();
                        }while (JOptionPane.showConfirmDialog(null, "Deseja comprar mais algum medicamento?") == JOptionPane.YES_OPTION);
                    }
                    break;

                    case 4: {
                        retiraDaCesta();
                    }
                    break;

                    case 5: {
                        realizaPagamento();
                    }
                    break;

                    case 6: {
                        System.exit(0);
                    }
                    break;

                    default:
                        JOptionPane.showMessageDialog(null, "Opção inválida");
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Erro: " + e.getMessage());
            }
        } while (JOptionPane.showConfirmDialog(null, "Deseja realizar mais alguma operação?") == JOptionPane.YES_OPTION);

    }

    public static int verificaFarmacia() {
        for (int i = 0; i < MAX; i++) {
            if ((medicamento[i] == null)) {
                return i;
            }
        }
        return MAX;
    }

    public static void cadastrarMedicamento() {

        boolean cad = false;
        controladorVet = verificaFarmacia();

        String nome = "";
        String descricao = "";
        String tipo = "";
        String preco = "";
        String estoque = "";
        String numTipo = "";


        do {
            try {
                nome = JOptionPane.showInputDialog("\t\tCadastro de medicamentos\n\n" +
                        "Nome: ");
                descricao = JOptionPane.showInputDialog("\t\tCadastro de medicamentos\n\n" +
                        "Descrição: ");
                do {

                    numTipo = JOptionPane.showInputDialog("\t\tCadastro de medicamentos\n\n" +
                            "TIPO DO MEDICAMENTO\n" +
                            "[1] - Solução em cápsulas/Comprimidos\n" +
                            "[2] - Solução em líquida\n" +
                            "[3] - Solução injetável\n\n"
                    );
                    switch (Integer.parseInt(numTipo)) {
                        case 1: {
                            tipo = "Solução em cápsulas/Comprimidos";
                        }
                        break;

                        case 2: {
                            tipo = "Solução em líquido";
                        }
                        break;

                        case 3: {
                            tipo = "Solução injetável";
                        }
                        break;

                        default:
                            JOptionPane.showMessageDialog(null, "Digite um valor correspondente");
                    }
                } while (Integer.parseInt(numTipo) <= 0 || Integer.parseInt(numTipo) > 3);

                preco = JOptionPane.showInputDialog("\t\tCadastro de medicamentos\n\n" +
                        "Preço: ");
                estoque = JOptionPane.showInputDialog("\t\tCadastro de medicamentos\n\n" +
                        "Estoque: ");
                cad = true;
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, e.getMessage());
                cad = false;
            } catch (NullPointerException e) {
                JOptionPane.showMessageDialog(null, e.getMessage());
                cad = false;
            }
            if (controladorVet < MAX) {
                if (cad == true) {
                    if (controladorVet > 0) {
                        for (int i = 0; i < controladorVet; i++) {
                            if ((medicamento[i].getNome().equals(nome) && medicamento[i].getTipo().equals(tipo)) && (medicamento[i].getPreco() == Float.parseFloat(preco))) {
                                int escolha = JOptionPane.showConfirmDialog(null, "Você já tem esse produto cadastrado. Deseja alterar o estoque?");
                                if (escolha == 0) {
                                    String valor = JOptionPane.showInputDialog(null, "Informe o novo valor do estoque referente ao medicamento: " + medicamento[i].getNome());
                                    codigo++;
                                    medicamento[controladorVet] = new Medicamento(codigo, nome, descricao, tipo, Float.parseFloat(preco));
                                    medicamento[i].setEstoque(Integer.parseInt(valor));
                                    JOptionPane.showMessageDialog(null, "Estoque do medicamento " + medicamento[i].getNome() + " atualizado");
                                    estoque = valor;
                                }else if(escolha == 1){
                                    JOptionPane.showMessageDialog(null, "Não foi alterado");
                                }else if(escolha == 2){
                                    JOptionPane.showMessageDialog(null, "Não foi alterado");
                                }
                            }
                        }
                    }
                    codigo++;
                    medicamento[controladorVet] = new Medicamento(codigo, nome, descricao, tipo, Float.parseFloat(preco));
                    medicamento[controladorVet].setEstoque(Integer.parseInt(estoque));
                    controladorVet++;
                } else
                    JOptionPane.showMessageDialog(null, "Não foi possível cadastrar o produto. Ocorreu erros ao salvar.");

            } else
                JOptionPane.showMessageDialog(null, "Compre mais memória em: www.alunosfucapi.com.br");

        }while (JOptionPane.showConfirmDialog(null, "Deseja cadastrar mais algum medicamento?") == JOptionPane.YES_OPTION);

        if (controladorVet == 1)
            return;

    }

    public static void  fullVetor(){
        for(int i = 0; i<MAX; i++){
            cestaDeCompras[i] = new Medicamento(0, "", "", "", 0);
        }
    }

    public static int InsereNaPosicao(){

        for (int i = 0; i < MAX; i++) {
            if (cestaDeCompras[i].getCodigo() == 0 && cestaDeCompras[i].getNome().equals("") && cestaDeCompras[i].getTipo().equals("") && cestaDeCompras[i].getPreco() == 0) {
                return i;
            }
        }
        return MAX;
    }

    public static void consultaMedicamento(String nome) {
        int i = 0;
        int naoEncontrado = 0;

        do {
            if (medicamento[i] != null) {
                if (medicamento[i].getNome().equals(nome)) {
                    medicamento[i].imprimir();
                } else
                    naoEncontrado++;
            } else
                naoEncontrado++;
            i++;
        } while (i < MAX);

        if (naoEncontrado >= MAX)
            JOptionPane.showMessageDialog(null, "O medicamento não foi encontrado");

    }



    public static void adicionaNaCesta(){

        String nmMedicamento = JOptionPane.showInputDialog("Informe o nome do medicamento que deseja comprar: ");
        String qtItem = "";
        int posicao = InsereNaPosicao();
        System.out.println("posicao: "+posicao);
        for(int i = 0; i<MAX; i++) {
            if (medicamento[i] != null) {
                if ( posicao < MAX) {
                    if (medicamento[i].getNome().equals(nmMedicamento)){
                        JOptionPane.showMessageDialog(null, "Encontramos o medicamento " + medicamento[i].getNome() + "\n\n" +
                                medicamento[i].getTipo() + "\n\nDescrição: " + medicamento[i].getDescricao() + "\nPreço: " + medicamento[i].getPreco());
                        if (JOptionPane.showConfirmDialog(null, "\nDeseja comprar este tipo medicamento? - " + medicamento[i].getTipo()) == JOptionPane.YES_OPTION) {
                            qtItem = JOptionPane.showInputDialog("\nQuantos você deseja comprar?");
                            if (Integer.parseInt(qtItem) <= medicamento[i].getEstoque() && medicamento[i].getEstoque() > 0) {
                                medicamento[i].setEstoque(medicamento[i].getEstoque() - Integer.parseInt(qtItem));
                                cestaDeCompras[posicao] = new Medicamento(medicamento[i].getCodigo(), medicamento[i].getNome(), medicamento[i].getDescricao(), medicamento[i].getTipo(), medicamento[i].getPreco());
                                valorDaCompra += medicamento[i].getPreco() * Integer.parseInt(qtItem);
                                cestaDeCompras[posicao].setQtMedicamento(Integer.parseInt(qtItem));
                                cestaDeCompras[posicao].setPrecoCompra(medicamento[i].getPreco());
                                cestaDeCompras[posicao].setCodigo(medicamento[i].getCodigo());
                                controladorVet2++;
                            } else
                                JOptionPane.showMessageDialog(null, "\nNão possuímos estoque para a quantidade solicitada.");
                        }
                    }
                }else
                    JOptionPane.showMessageDialog(null, "Cesta está lotada!");
            }
        }

        if(valorDaCompra>0) {
            JOptionPane.showMessageDialog(null, "Sua cesta de compras");
            for(int i = 0; i<MAX; i++){
                if(cestaDeCompras[i] != null && cestaDeCompras[i].getCodigo() != 0 && !cestaDeCompras[i].getNome().equals(null) && !cestaDeCompras[i].getTipo().equals(null) && cestaDeCompras[i].getPreco() != 0){
                    JOptionPane.showMessageDialog(null, "Nome do medicamento: "+cestaDeCompras[i].getNome()
                            +"\nCódigo do produto: "+cestaDeCompras[i].getCodigo()
                            +"\nDescrição: "+cestaDeCompras[i].getDescricao()
                            +"\nTipo: "+cestaDeCompras[i].getTipo()
                            +"\nPreço: "+cestaDeCompras[i].getPreco()
                            +"\nQuantidade: "+(cestaDeCompras[i].getQtMedicamento())
                    );
                }
            }
            JOptionPane.showMessageDialog(null, "Preço total da compra: "+valorDaCompra);
        }else {
            JOptionPane.showMessageDialog(null, "Cesta de compras vazia..");
        }
    }

    public static void retiraDaCesta(){

        int nEncontrado = 0;

        for (int i = 0; i < MAX; i++) {
            if (cestaDeCompras[i] != null && cestaDeCompras[i].getCodigo() != 0 && !cestaDeCompras[i].getNome().equals(null) && !cestaDeCompras[i].getTipo().equals(null) && cestaDeCompras[i].getPreco() != 0) {
                JOptionPane.showMessageDialog(null, "Nome do medicamento: " + cestaDeCompras[i].getNome()
                        + "\nDescrição: " + cestaDeCompras[i].getDescricao()
                        + "\nTipo: " + cestaDeCompras[i].getTipo()
                        + "\nPreço: " + cestaDeCompras[i].getPreco()
                        + "\nQuantidade: " + (cestaDeCompras[i].getQtMedicamento())
                );
            } else {
                nEncontrado++;
            }
        }

        if (nEncontrado >= MAX) {
            JOptionPane.showMessageDialog(null, "Cesta de compras vazia..");
            return;
        }

        JOptionPane.showMessageDialog(null, "Preço total da compra: " + valorDaCompra);

        String nome = JOptionPane.showInputDialog(null, "Informe o nome do medicamento que deseja remover: ");
        int codigo = 0;

        for (int i = 0; i < MAX; i++){
            if (cestaDeCompras[i] != null) {
                if (cestaDeCompras[i].getCodigo() != 0 && !cestaDeCompras[i].getNome().equals(null) && !cestaDeCompras[i].getTipo().equals(null) && cestaDeCompras[i].getPreco() != 0) {
                    if (cestaDeCompras[i].getNome().equals(nome)) {
                        JOptionPane.showMessageDialog(null, "Nome do medicamento: " + cestaDeCompras[i].getNome()
                                +"\nCódigo do medicamento: "+cestaDeCompras[i].getCodigo()
                                + "\nDescrição: " + cestaDeCompras[i].getDescricao()
                                + "\nTipo: " + cestaDeCompras[i].getTipo()
                                + "\nPreço: " + cestaDeCompras[i].getPreco()
                                + "\nQuantidade: " + (cestaDeCompras[i].getQtMedicamento())
                        );
                        if (JOptionPane.showConfirmDialog(null, "Deseja remover esse produto? - " + cestaDeCompras[i].getNome() + "\nTipo: " + cestaDeCompras[i].getTipo()) == JOptionPane.YES_OPTION) {
                            String qt = JOptionPane.showInputDialog("\nQuantidade: " + cestaDeCompras[i].getQtMedicamento()
                                    + "\nQuantos deseja remover?");

                            codigo = cestaDeCompras[i].getCodigo();

                            if (Integer.parseInt(qt) < cestaDeCompras[i].getQtMedicamento()) {
                                valorDaCompra = valorDaCompra - (cestaDeCompras[i].getPreco() * Integer.parseInt(qt));
                                for (i = 0; i < MAX; i++) {
                                    if (cestaDeCompras[i] != null && medicamento[i]!=null) {
                                        if (medicamento[i].getCodigo() == codigo) {
                                            System.out.println("caiu onde eu queria - 2");
                                            medicamento[i].setEstoque(medicamento[i].getEstoque() + Integer.parseInt(qt));
                                            cestaDeCompras[i].setQtMedicamento(cestaDeCompras[i].getQtMedicamento() - Integer.parseInt(qt));
                                            if (cestaDeCompras[i].getQtMedicamento() == 0) {
                                                cestaDeCompras[i].setNome("");
                                                cestaDeCompras[i].setPreco(0);
                                                cestaDeCompras[i].setDescricao("");
                                                cestaDeCompras[i].setTipo("");
                                            }
                                        }
                                    }
                                }
                            }else if(Integer.parseInt(qt) == cestaDeCompras[i].getQtMedicamento()){
                                cestaDeCompras[i].setNome("");
                                cestaDeCompras[i].setPreco(0);
                                cestaDeCompras[i].setDescricao("");
                                cestaDeCompras[i].setTipo("");
                                valorDaCompra = valorDaCompra - (cestaDeCompras[i].getPrecoCompra()*Integer.parseInt(qt));
                                i=0;
                                do{
                                    if (cestaDeCompras[i] != null && medicamento[i]!=null) {
                                        if (medicamento[i].getCodigo() != 0 && medicamento[i] != null) {
                                            if (medicamento[i].getCodigo() == codigo) {
                                                medicamento[i].setEstoque(medicamento[i].getEstoque() + Integer.parseInt(qt));
                                                cestaDeCompras[i].setQtMedicamento(cestaDeCompras[i].getQtMedicamento() - Integer.parseInt(qt));
                                                cestaDeCompras[i].setCodigo(0);
                                            }
                                        }
                                    }
                                    i++;
                                }while (i<MAX);
                            }else
                                JOptionPane.showMessageDialog(null, "Quantidade superior");
                        }
                    }
                }
            }
            JOptionPane.showMessageDialog(null, "Preço total da compra: " + valorDaCompra);
        }
    }

    public static void limpaCesta(){
        for(int  i = 0; i<MAX; i++){
            cestaDeCompras[i].setNome("");
            cestaDeCompras[i].setCodigo(0);
            cestaDeCompras[i].setDescricao("");
            cestaDeCompras[i].setTipo("");
            cestaDeCompras[i].setPrecoCompra(0);
            cestaDeCompras[i].setPreco(0);
        }
        valorDaCompra=0;

    }

    public static void realizaPagamento(){
        int opc = 0;
        int vetorVazio = 0;
        for(int i = 0; i<MAX; i++){
            if(cestaDeCompras[i].getCodigo() != 0 && !cestaDeCompras[i].getNome().equals(null) && !cestaDeCompras[i].getTipo().equals(null) && cestaDeCompras[i].getPreco() != 0){
                opc = JOptionPane.showConfirmDialog(null,"Deseja confirmar a compra?");
                if(opc == 0){
                    float desconto = Medicamento.aplicaDesconto();
                    JOptionPane.showMessageDialog(null, "Valor total da compra: "+valorDaCompra
                    +"\nPromoção da fármacia - Qualquer compra ganha desconto de 15%"+
                    "\nValor da compra com a promoção: "+(valorDaCompra - (valorDaCompra *desconto)));
                    limpaCesta();
                    JOptionPane.showMessageDialog(null, "Compra realizada com sucesso!");
                }else if(opc == 1){
                    JOptionPane.showMessageDialog(null, "Compra em aberto!");
                }else if(opc == 2){
                    int x = 0;
                    for(i = 0; i<MAX; i++){
                        if(cestaDeCompras[i].getCodigo() != 0)
                            if (cestaDeCompras[i] != null && medicamento[i]!=null) {
                                if (cestaDeCompras[x].getCodigo() == medicamento[i].getCodigo()) {
                                    medicamento[i].setEstoque(medicamento[i].getEstoque() + cestaDeCompras[x].getQtMedicamento());
                                    System.out.println("Estoque do " + medicamento[i].getNome() + "\nQuantidade: " + medicamento[i].getEstoque());
                                    x++;
                                }
                            }
                    }
                    limpaCesta();
                    JOptionPane.showMessageDialog(null, "Compra cancelada");
                }
            }else
                vetorVazio++;
        }

        if(vetorVazio>=MAX && valorDaCompra<=0) {
            JOptionPane.showMessageDialog(null, "Cesta vazia");
        }
    }
}
