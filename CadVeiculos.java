import java.util.ArrayList;
import java.util.List;

public class CadVeiculos {

    static List<String> veiculos = new ArrayList<>();

    public static void main(String[] args) {

        IO.println("Bem-Vindo ao Sistema CadVeiculos");

        String menu = """
                MENU DE OPÇÕES
                1 - CADASTRAR VEÍCULO
                2 - LISTAR VEÍCULOS
                3 - REMOVER VEÍCULO POR ÍNDICE
                4 - BUSCAR VEÍCULO POR NOME
                5 - EDITAR VEÍCULO
                6 - REMOVER VEÍCULO POR NOME
                0 - SAIR
                """;

        int opcao;

        do {
            IO.println("\n" + menu);
            opcao = Input.scanInt("Digite a opção desejada: ");

            switch (opcao) {
                case 1 -> cadastrar();
                case 2 -> listar();
                case 3 -> excluirPorIndice();
                case 4 -> buscarPorNome();
                case 5 -> editar();
                case 6 -> excluirPorNome();
                case 0 -> IO.println("Volte sempre!");
                default -> IO.println("Opção inválida.");
            }

        } while (opcao != 0);
    }

    static void cadastrar() {
        String veiculo = IO.readln("Digite o nome do veículo: ");
        veiculo = veiculo.trim();

        if (veiculo.isEmpty()) {
            IO.println("Nome inválido.");
            return;
        }

        for (int i = 0; i < veiculos.size(); i++) {
            if (veiculos.get(i).equalsIgnoreCase(veiculo)) {
                IO.println("Esse veículo já está cadastrado.");
                return;
            }
        }

        veiculos.add(veiculo);
        IO.println("Veículo cadastrado com sucesso!");
    }

    static void listar() {
        if (veiculos.isEmpty()) {
            IO.println("A lista está vazia.");
            return;
        }

        ordenar();

        IO.println("\nLISTA DE VEÍCULOS:");
        for (int i = 0; i < veiculos.size(); i++) {
            IO.println((i + 1) + " - " + veiculos.get(i));
        }

        IO.println("Total: " + veiculos.size());
    }

    static void excluirPorIndice() {
        if (veiculos.isEmpty()) {
            IO.println("A lista está vazia.");
            return;
        }

        listar();

        int indice = Input.scanInt("Digite o índice para remover: ");

        if (indice <= 0 || indice > veiculos.size()) {
            IO.println("Veículo não encontrado.");
        } else {
            String removido = veiculos.remove(indice - 1);
            IO.println("Removido: " + removido);
        }
    }

    static void buscarPorNome() {
        if (veiculos.isEmpty()) {
            IO.println("A lista está vazia.");
            return;
        }

        String busca = IO.readln("Digite o nome: ").trim();

        if (busca.isEmpty()) {
            IO.println("Busca inválida.");
            return;
        }

        ordenar();

        boolean encontrou = false;

        for (int i = 0; i < veiculos.size(); i++) {
            if (veiculos.get(i).toLowerCase().contains(busca.toLowerCase())) {
                IO.println((i + 1) + " - " + veiculos.get(i));
                encontrou = true;
            }
        }

        if (!encontrou) {
            IO.println("Nenhum veículo encontrado.");
        }

        IO.println("Total: " + veiculos.size());
    }

    static void editar() {
        if (veiculos.isEmpty()) {
            IO.println("A lista está vazia.");
            return;
        }

        listar();

        int indice = Input.scanInt("Digite o índice para editar: ");

        if (indice <= 0 || indice > veiculos.size()) {
            IO.println("Veículo não encontrado.");
            return;
        }

        String novo = IO.readln("Novo nome: ").trim();

        if (novo.isEmpty()) {
            IO.println("Nome inválido.");
            return;
        }

        for (int i = 0; i < veiculos.size(); i++) {
            if (i != (indice - 1) && veiculos.get(i).equalsIgnoreCase(novo)) {
                IO.println("Já existe esse veículo.");
                return;
            }
        }

        veiculos.set(indice - 1, novo);
        IO.println("Editado com sucesso!");
    }

    static void excluirPorNome() {
        if (veiculos.isEmpty()) {
            IO.println("A lista está vazia.");
            return;
        }

        String nome = IO.readln("Digite o nome para remover: ").trim();

        if (nome.isEmpty()) {
            IO.println("Nome inválido.");
            return;
        }

        for (int i = 0; i < veiculos.size(); i++) {
            if (veiculos.get(i).equalsIgnoreCase(nome)) {
                String removido = veiculos.remove(i);
                IO.println("Removido: " + removido);
                return;
            }
        }

        IO.println("Veículo não encontrado.");
    }

    static void ordenar() {
        for (int i = 0; i < veiculos.size() - 1; i++) {
            for (int j = 0; j < veiculos.size() - 1 - i; j++) {
                String atual = veiculos.get(j);
                String prox = veiculos.get(j + 1);

                if (atual.toLowerCase().compareTo(prox.toLowerCase()) > 0) {
                    veiculos.set(j, prox);
                    veiculos.set(j + 1, atual);
                }
            }
        }
    }
}