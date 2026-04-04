import java.util.ArrayList;
import java.util.List;

List<String> veiculos = new ArrayList<>();

void main() {

    IO.println("Bem-Vindo ao Sistema CadVeiculos");

    String menu = """
            MENU DE OPÇÕES
            1 - CADASTRAR VEÍCULO
            2 - LISTAR VEÍCULOS
            3 - REMOVER POR ÍNDICE
            4 - BUSCAR VEÍCULO
            5 - EDITAR VEÍCULO
            6 - REMOVER POR NOME
            0 - SAIR
            """;

    int opcao;

    do {
        IO.println(menu);
        opcao = Input.scanInt("Digite a opção desejada: ");

        switch (opcao) {
            case 1 -> cadastrar();
            case 2 -> listar();
            case 3 -> removerIndice();
            case 4 -> buscar();
            case 5 -> editar();
            case 6 -> removerNome();
            case 0 -> IO.println("Volte sempre!");
            default -> IO.println("Opção inválida");
        }

        if (opcao != 0) {
            IO.readln("Pressione Enter para continuar");
        }

    } while (opcao != 0);
}

void cadastrar() {
    String veiculo = IO.readln("Digite o nome do veículo: ").trim();

    if (veiculo.isEmpty()) {
        IO.println("Nome inválido.");
        return;
    }

    
    for (int i = 0; i < veiculos.size(); i++) {
        if (veiculos.get(i).equalsIgnoreCase(veiculo)) {
            IO.println("Veículo já cadastrado.");
            return;
        }
    }

    veiculos.add(veiculo);
    IO.println("Veículo cadastrado com sucesso!");
}

void listar() {
    if (veiculos.isEmpty()) {
        IO.println("A lista está vazia.");
        return;
    }

    ordenar();

    IO.println("LISTA DE VEÍCULOS:");
    for (int i = 0; i < veiculos.size(); i++) {
        IO.println((i + 1) + " - " + veiculos.get(i));
    }

    IO.println("Total de veículos: " + veiculos.size());
}

void buscar() {
    if (veiculos.isEmpty()) {
        IO.println("A lista está vazia.");
        return;
    }

    String nome = IO.readln("Digite o nome para buscar: ").trim();

    if (nome.isEmpty()) {
        IO.println("Busca inválida.");
        return;
    }

    ordenar();

    boolean encontrado = false;

    
    for (int i = 0; i < veiculos.size(); i++) {
        if (veiculos.get(i).toLowerCase().contains(nome.toLowerCase())) {
            IO.println("Encontrado: " + veiculos.get(i));
            encontrado = true;
        }
    }

    if (!encontrado) {
        IO.println("Veículo não encontrado.");
    }

    IO.println("Total de veículos: " + veiculos.size());
}

void removerIndice() {
    if (veiculos.isEmpty()) {
        IO.println("A lista está vazia.");
        return;
    }

    listar();

    int indice = Input.scanInt("Digite o número do veículo para remover: ");

    if (indice < 1 || indice > veiculos.size()) {
        IO.println("Índice inválido.");
        return;
    }

    veiculos.remove(indice - 1);
    IO.println("Veículo removido com sucesso.");
}

void removerNome() {
    if (veiculos.isEmpty()) {
        IO.println("A lista está vazia.");
        return;
    }

    String nome = IO.readln("Digite o nome do veículo para remover: ").trim();

    if (nome.isEmpty()) {
        IO.println("Nome inválido.");
        return;
    }

    
    for (int i = 0; i < veiculos.size(); i++) {
        if (veiculos.get(i).equalsIgnoreCase(nome)) {
            veiculos.remove(i);
            IO.println("Veículo removido com sucesso.");
            return;
        }
    }

    IO.println("Veículo não encontrado.");
}

void editar() {
    if (veiculos.isEmpty()) {
        IO.println("A lista está vazia.");
        return;
    }

    listar();

    int indice = Input.scanInt("Digite o número do veículo para editar: ");

    if (indice < 1 || indice > veiculos.size()) {
        IO.println("Índice inválido.");
        return;
    }

    String novoNome = IO.readln("Digite o novo nome: ").trim();

    if (novoNome.isEmpty()) {
        IO.println("Nome inválido.");
        return;
    }

    
    for (int i = 0; i < veiculos.size(); i++) {
        if (i != (indice - 1) && veiculos.get(i).equalsIgnoreCase(novoNome)) {
            IO.println("Já existe um veículo com esse nome.");
            return;
        }
    }

    
    veiculos.set(indice - 1, novoNome);
    IO.println("Veículo atualizado com sucesso.");
}


void ordenar() {
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
