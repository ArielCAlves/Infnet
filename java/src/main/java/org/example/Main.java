package org.example;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import Exceptions.ClienteException;
import Exceptions.ContratoAluguelException;
import Exceptions.EnderecoException;
import Exceptions.ImovelException;
import Models.Cliente;
import Models.ContratoAluguel;
import Models.Endereco;
import Models.Fisica;
import Models.Imovel;
import Models.Juridica;
import Models.TipoImovelEnum;

import java.time.LocalDate;

public class Main {
    private static Map<String, List<ContratoAluguel>> contratosPorImovel = new HashMap<>();
    private static Map<String, List<ContratoAluguel>> contratosPorClienteCPF = new HashMap<>();
    private static Map<String, List<ContratoAluguel>> contratosPorClienteCNPJ = new HashMap<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Imovel> imoveis = new ArrayList<>();
        List<Cliente> clientes = new ArrayList<>();
        List<ContratoAluguel> contratos = new ArrayList<>();

        while (true) {
            System.out.println("Selecione uma opção:");
            System.out.println("1. Adicionar Cliente");
            System.out.println("2. Adicionar Imóvel");
            System.out.println("3. Adicionar Contrato de Aluguel");
            System.out.println("4. Buscar Cliente e Exibir Contratos");
            System.out.println("5. Buscar Imóvel e Exibir Contratos");
            System.out.println("6. Exportar Contratos em CSV");
            System.out.println("7. Sair");

            int opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
            case 1:
                Cliente novoCliente = null;

                while (novoCliente == null) {
                    System.out.println("Nome do cliente:");
                    String nomeCliente = scanner.nextLine();

                    while (nomeCliente.trim().isEmpty()) {
                        System.out.println("Nome do cliente não pode ser vazio. Por favor, insira novamente: ");
                        nomeCliente = scanner.nextLine();
                    }

                    System.out.println("Telefone do cliente com o DDD (formato esperado:21999999999): ");
                    String telefoneCliente = scanner.nextLine();

                    while (telefoneCliente.trim().isEmpty()) {
                        System.out.println("Telefone do cliente não pode ser vazio. Por favor, insira novamente: ");
                        telefoneCliente = scanner.nextLine();
                    }

                    System.out.println("Email do cliente (formato esperado: abc@gmail.com): ");
                    String emailCliente = scanner.nextLine();

                    while (emailCliente.trim().isEmpty()) {
                        System.out.println("Email do cliente não pode ser vazio. Por favor, insira novamente: ");
                        emailCliente = scanner.nextLine();
                    }

                    System.out.println("É pessoa física (1) ou jurídica (2)?");
                    int tipoCliente = scanner.nextInt();
                    scanner.nextLine();

                    while (tipoCliente != 1 && tipoCliente != 2) {
                        System.out.println("Opção inválida para tipo de cliente. Por favor, insira novamente:");
                        tipoCliente = scanner.nextInt();
                        scanner.nextLine();
                    }

                    if (tipoCliente == 1) {
                        String cpfCliente;
                        while (true) {
                            System.out.println("CPF do cliente (11 dígitos numéricos):");
                            cpfCliente = scanner.nextLine();

                            if (cpfCliente.isEmpty() || cpfCliente.length() != 11 || !cpfCliente.matches("\\d+")) {
                                System.out.println("CPF inválido. Deve conter 11 dígitos numéricos. Por favor, insira novamente:");
                            } else {
                                try {
                                    novoCliente = new Fisica(nomeCliente, telefoneCliente, emailCliente, new ArrayList<>(), cpfCliente);
                                    break;
                                } catch (ClienteException e) {
                                    System.err.println("Erro ao criar cliente: " + e.getMessage());
                                    break;
                                }
                            }
                        }
                    } else if (tipoCliente == 2) {
                        String cnpjCliente;
                        while (true) {
                            System.out.println("CNPJ do cliente (14 dígitos numéricos):");
                            cnpjCliente = scanner.nextLine();

                            if (cnpjCliente.isEmpty() || cnpjCliente.length() != 14 || !cnpjCliente.matches("\\d+")) {
                                System.out.println("CNPJ inválido. Deve conter 14 dígitos numéricos. Por favor, insira novamente:");
                            } else {
                                try {
                                    novoCliente = new Juridica(nomeCliente, telefoneCliente, emailCliente, new ArrayList<>(), cnpjCliente);
                                    break;
                                } catch (ClienteException e) {
                                    System.err.println("Erro ao criar cliente: " + e.getMessage());
                                    break;
                                }
                            }
                        }
                    }
                }

                clientes.add(novoCliente);
                System.out.println("Cliente adicionado com sucesso!");
                break;


                

            case 2:
                System.out.println("Registro do imóvel:");
                String registroImovel = scanner.nextLine();

                // Verifica se o registro do imóvel já existe
                boolean registroExistente = false;
                for (Imovel imovelExistente : imoveis) {
                    if (imovelExistente.getRegistro().equals(registroImovel)) {
                        registroExistente = true;
                        System.out.println("Erro: Este registro de imóvel já existe.");
                        break;
                    }
                }

                if (!registroExistente) {
                    System.out.println("Nome do imóvel:");
                    String nomeImovel = scanner.nextLine();

                    // Solicita informações sobre o endereço
                    System.out.println("Endereço do imóvel:");
                    System.out.println("Logradouro:");
                    String logradouro = scanner.nextLine();
                    System.out.println("Tipo do Logradouro:");
                    String tipoLogradouro = scanner.nextLine();
                    System.out.println("Número:");
                    String numero = scanner.nextLine();
                    System.out.println("Complemento:");
                    String complemento = scanner.nextLine();
                    System.out.println("Cidade:");
                    String cidade = scanner.nextLine();
                    System.out.println("Estado:");
                    String estado = scanner.nextLine();
                    String cep;

                    while (true) {
                        System.out.println("CEP (Digite 8 números): ");
                        cep = scanner.nextLine();

                        try {
                            Endereco endereco = new Endereco(logradouro, tipoLogradouro, numero, complemento, cidade, estado, cep);
                            // Verifica se o endereço já existe
                            boolean enderecoExistente = imoveis.stream()
                                    .anyMatch(imovelExistente -> imovelExistente.getEndereco().equals(endereco));

                            if (enderecoExistente) {
                                System.out.println("Erro: Este endereço já está associado a um imóvel.");
                            } else {
                                // Se não houver duplicatas de endereço, continua com a criação do imóvel
                                System.out.println("Selecione o tipo do imóvel:");
                                System.out.println("1. Comercial");
                                System.out.println("2. Residencial");
                                int tipoImovelEscolhido = scanner.nextInt();
                                TipoImovelEnum tipoImovel;

                                if (tipoImovelEscolhido == 1) {
                                    tipoImovel = TipoImovelEnum.COMERCIAL;
                                } else if (tipoImovelEscolhido == 2) {
                                    tipoImovel = TipoImovelEnum.RESIDENCIAL;
                                } else {
                                    System.out.println("Opção inválida para tipo de imóvel.");
                                    tipoImovel = TipoImovelEnum.RESIDENCIAL;
                                }

                                System.out.println("Valor do aluguel:");
                                float valorAluguel = scanner.nextFloat();

                                try {
                                    Imovel novoImovel = new Imovel(registroImovel, nomeImovel, endereco, tipoImovel, valorAluguel, new ArrayList<>());
                                    imoveis.add(novoImovel);
                                    System.out.println("Imóvel adicionado com sucesso!");
                                    break;
                                } catch (ImovelException e) {
                                    System.err.println("Erro ao criar imóvel: " + e.getMessage());
                                }
                            }
                        } catch (EnderecoException e) {
                            System.err.println("Erro: " + e.getMessage());
                        }
                    }
                }
                break;


                
                

                case 3:
                    System.out.println("CPF ou CNPJ do cliente:");
                    String documentoCliente = scanner.nextLine();
                    System.out.println("Registro do imóvel:");
                    String registroImovelContrato = scanner.nextLine();

                    Cliente clienteEncontrado = null;
                    for (Cliente cliente : clientes) {
                        if (cliente instanceof Fisica && ((Fisica) cliente).getCpf().equals(documentoCliente)) {
                            clienteEncontrado = cliente;
                            break;
                        }
                        if (cliente instanceof Juridica && ((Juridica) cliente).getCnpj().equals(documentoCliente)) {
                            clienteEncontrado = cliente;
                            break;
                        }
                    }

                    Imovel imovelEncontrado = null;
                    for (Imovel imovel : imoveis) {
                        if (imovel.getRegistro().equals(registroImovelContrato)) {
                            imovelEncontrado = imovel;
                            break;
                        }
                    }

                    if (clienteEncontrado != null && imovelEncontrado != null) {
                        LocalDate dataInicioContrato = LocalDate.now();
                        LocalDate dataTerminoContrato = LocalDate.now().plusMonths(12);

                        try {
                            ContratoAluguel novoContrato = new ContratoAluguel(imovelEncontrado, clienteEncontrado, dataInicioContrato, dataTerminoContrato);
                            contratos.add(novoContrato);
                            System.out.println("Contrato de aluguel adicionado com sucesso!");

                            if (!contratosPorImovel.containsKey(registroImovelContrato)) {
                                contratosPorImovel.put(registroImovelContrato, new ArrayList<>());
                            }
                            contratosPorImovel.get(registroImovelContrato).add(novoContrato);

                            if (clienteEncontrado instanceof Fisica) {
                                if (!contratosPorClienteCPF.containsKey(documentoCliente)) {
                                    contratosPorClienteCPF.put(documentoCliente, new ArrayList<>());
                                }
                                contratosPorClienteCPF.get(documentoCliente).add(novoContrato);
                            } else if (clienteEncontrado instanceof Juridica) {
                                if (!contratosPorClienteCNPJ.containsKey(documentoCliente)) {
                                    contratosPorClienteCNPJ.put(documentoCliente, new ArrayList<>());
                                }
                                contratosPorClienteCNPJ.get(documentoCliente).add(novoContrato);
                            }
                        } catch (ContratoAluguelException e) {
                            System.err.println("Erro ao adicionar contrato de aluguel: " + e.getMessage());
                        }
                    } else {
                        System.out.println("Cliente ou imóvel não encontrado. Verifique os dados fornecidos.");
                    }
                    break;

                case 4:
                    System.out.println("CPF ou CNPJ do cliente:");
                    String documentoClienteConsulta = scanner.nextLine();

                    List<ContratoAluguel> contratosCliente = new ArrayList<>();
                    if (contratosPorClienteCPF.containsKey(documentoClienteConsulta)) {
                        contratosCliente.addAll(contratosPorClienteCPF.get(documentoClienteConsulta));
                    }
                    if (contratosPorClienteCNPJ.containsKey(documentoClienteConsulta)) {
                        contratosCliente.addAll(contratosPorClienteCNPJ.get(documentoClienteConsulta));
                    }

                    if (!contratosCliente.isEmpty()) {
                        System.out.println("Contratos do cliente " + documentoClienteConsulta + ":");
                        for (ContratoAluguel contrato : contratosCliente) {
                            System.out.println("Registro do imóvel: " + contrato.getImovel().getRegistro());
                            System.out.println("Nome do imóvel: " + contrato.getImovel().getNome());
                            System.out.println("Data de início: " + contrato.getDataInicio());
                            System.out.println("Data de término: " + contrato.getDataTermino());
                            System.out.println("Valor do contrato: " + contrato.calcularValorContrato());
                            System.out.println();
                        }
                    } else {
                        System.out.println("O cliente não possui contratos de aluguel.");
                    }
                    break;

                case 5:
                    System.out.println("Registro do imóvel:");
                    String registroBuscaImovel = scanner.nextLine();

                    if (contratosPorImovel.containsKey(registroBuscaImovel)) {
                        List<ContratoAluguel> contratosImovel = contratosPorImovel.get(registroBuscaImovel);
                        if (!contratosImovel.isEmpty()) {
                            System.out.println("Contratos do imóvel " + registroBuscaImovel + ":");
                            for (ContratoAluguel contrato : contratosImovel) {
                                if (contrato.getCliente() instanceof Fisica) {
                                    System.out.println("CPF do cliente: " + ((Fisica) contrato.getCliente()).getCpf());
                                } else if (contrato.getCliente() instanceof Juridica) {
                                    System.out.println("CNPJ do cliente: " + ((Juridica) contrato.getCliente()).getCnpj());
                                }
                                System.out.println("Nome do cliente: " + contrato.getCliente().getNome());
                                System.out.println("Data de início: " + contrato.getDataInicio());
                                System.out.println("Data de término: " + contrato.getDataTermino());
                                System.out.println("Valor do contrato: " + contrato.calcularValorContrato());
                                System.out.println();
                            }
                        } else {
                            System.out.println("O imóvel não possui contratos de aluguel.");
                        }
                    } else {
                        System.out.println("Imóvel não encontrado. Verifique o registro fornecido.");
                    }
                    break;

                case 6:
                    System.out.println("Digite o nome do arquivo CSV:");
                    String nomeArquivo = scanner.nextLine();

                    if (!nomeArquivo.endsWith(".csv")) {
                        nomeArquivo += ".csv";
                    }

                    try {
                        FileWriter writer = new FileWriter(nomeArquivo);

                        writer.write("Registro do Imóvel, Nome do Imóvel, Endereço, Tipo, Valor do Aluguel, Nome do Cliente, Telefone, Data de Início, Data de Término, Contrato Vencido\n");

                        for (ContratoAluguel contrato : contratos) {
                            writer.write(contrato.toCSV() + "\n");
                        }

                        writer.close();
                        System.out.println("Contratos exportados com sucesso para " + nomeArquivo);
                    } catch (IOException e) {
                        System.err.println("Erro ao escrever o arquivo CSV.");
                        e.printStackTrace();
                    }
                    break;

                case 7:
                    System.out.println("Encerrando o programa.");
                    scanner.close();
                    System.exit(0);

                default:
                    System.out.println("Opção inválida. Por favor, selecione uma opção válida.");
                    break;
            }
        }
    }
}

