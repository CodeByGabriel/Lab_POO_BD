import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;
import com.j256.ormlite.dao.Dao;

public class HemogramaManager {
    private DatabaseHelper databaseHelper;
    private Dao<Pessoa, Integer> pessoaDao;
    private Dao<Hemograma, Integer> hemogramaDao;

    public HemogramaManager() throws SQLException {
        databaseHelper = new DatabaseHelper();
        pessoaDao = databaseHelper.getPessoaDao();
        hemogramaDao = databaseHelper.getHemogramaDao();
    }

    public void criarPessoa(String nome) throws SQLException {
        Pessoa pessoa = new Pessoa(nome);
        pessoaDao.create(pessoa);
        System.out.println("Pessoa criada: " + pessoa);
    }

    public void recuperarPessoas() throws SQLException {
        List<Pessoa> pessoas = pessoaDao.queryForAll();
        for (Pessoa pessoa : pessoas) {
            System.out.println(pessoa);
        }
    }

    public void atualizarPessoa(int id, String novoNome) throws SQLException {
        Pessoa pessoa = pessoaDao.queryForId(id);
        if (pessoa != null) {
            pessoa.setNome(novoNome);
            pessoaDao.update(pessoa);
            System.out.println("Pessoa atualizada: " + pessoa);
        } else {
            System.out.println("Pessoa não encontrada com o ID: " + id);
        }
    }

    public void deletarPessoa(int id) throws SQLException {
        Pessoa pessoa = pessoaDao.queryForId(id);
        if (pessoa != null) {
            pessoaDao.delete(pessoa);
            System.out.println("Pessoa deletada: " + pessoa);
        } else {
            System.out.println("Pessoa não encontrada com o ID: " + id);
        }
    }

    public void criarHemograma(String descricao, int pessoaId) throws SQLException {
        Pessoa pessoa = pessoaDao.queryForId(pessoaId);
        if (pessoa != null) {
            Hemograma hemograma = new Hemograma(descricao, pessoa);
            hemogramaDao.create(hemograma);
            System.out.println("Hemograma criado: " + hemograma);
        } else {
            System.out.println("Pessoa não encontrada com o ID: " + pessoaId);
        }
    }

    public void recuperarHemogramas() throws SQLException {
        List<Hemograma> hemogramas = hemogramaDao.queryForAll();
        for (Hemograma hemograma : hemogramas) {
            System.out.println(hemograma);
        }
    }

    public void atualizarHemograma(int id, String novaDescricao) throws SQLException {
        Hemograma hemograma = hemogramaDao.queryForId(id);
        if (hemograma != null) {
            hemograma.setDescricao(novaDescricao);
            hemogramaDao.update(hemograma);
            System.out.println("Hemograma atualizado: " + hemograma);
        } else {
            System.out.println("Hemograma não encontrado com o ID: " + id);
        }
    }

    public void deletarHemograma(int id) throws SQLException {
        Hemograma hemograma = hemogramaDao.queryForId(id);
        if (hemograma != null) {
            hemogramaDao.delete(hemograma);
            System.out.println("Hemograma deletado: " + hemograma);
        } else {
            System.out.println("Hemograma não encontrado com o ID: " + id);
        }
    }

    public static void main(String[] args) {
        try {
            HemogramaManager manager = new HemogramaManager();
            Scanner scanner = new Scanner(System.in);

            while (true) {
                System.out.println("Escolha uma opção: 1) Criar Pessoa 2) Recuperar Pessoas 3) Atualizar Pessoa 4) Deletar Pessoa 5) Criar Hemograma 6) Recuperar Hemogramas 7) Atualizar Hemograma 8) Deletar Hemograma 9) Sair");
                int opcao = scanner.nextInt();
                scanner.nextLine(); // consumir a nova linha

                switch (opcao) {
                    case 1:
                        System.out.println("Digite o nome:");
                        String nome = scanner.nextLine();
                        manager.criarPessoa(nome);
                        break;
                    case 2:
                        manager.recuperarPessoas();
                        break;
                    case 3:
                        System.out.println("Digite o ID para atualizar:");
                        int idAtualizar = scanner.nextInt();
                        scanner.nextLine(); // consumir a nova linha
                        System.out.println("Digite o novo nome:");
                        String novoNome = scanner.nextLine();
                        manager.atualizarPessoa(idAtualizar, novoNome);
                        break;
                    case 4:
                        System.out.println("Digite o ID para deletar:");
                        int idDeletar = scanner.nextInt();
                        manager.deletarPessoa(idDeletar);
                        break;
                    case 5:
                        System.out.println("Digite a descrição do hemograma:");
                        String descricao = scanner.nextLine();
                        System.out.println("Digite o ID da pessoa:");
                        int pessoaId = scanner.nextInt();
                        manager.criarHemograma(descricao, pessoaId);
                        break;
                    case 6:
                        manager.recuperarHemogramas();
                        break;
                    case 7:
                        System.out.println("Digite o ID do hemograma para atualizar:");
                        int idHemogramaAtualizar = scanner.nextInt();
                        scanner.nextLine(); // consumir a nova linha
                        System.out.println("Digite a nova descrição:");
                        String novaDescricao = scanner.nextLine();
                        manager.atualizarHemograma(idHemogramaAtualizar, novaDescricao);
                        break;
                    case 8:
                        System.out.println("Digite o ID do hemograma para deletar:");
                        int idHemogramaDeletar = scanner.nextInt();
                        manager.deletarHemograma(idHemogramaDeletar);
                        break;
                    case 9:
                        scanner.close();
                        System.out.println("Saindo...");
                        return;
                    default:
                        System.out.println("Opção inválida.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
