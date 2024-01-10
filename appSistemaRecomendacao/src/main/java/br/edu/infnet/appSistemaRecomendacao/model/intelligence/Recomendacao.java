//package br.edu.infnet.appSistemaRecomendacao.model.intelligence;
//import java.util.ArrayList;
//import java.util.List;
//import de.mrapp.apriori.Apriori;
//import de.mrapp.apriori.AssociationRule;
//import de.mrapp.apriori.RuleSet;
//
//import br.edu.infnet.appSistemaRecomendacao.model.domain.Assinatura;
//import br.edu.infnet.appSistemaRecomendacao.model.domain.Multimidia;
//
//public class Recomendacao {
//	public static void runAnalysis(List<Assinatura> assinaturas) {        
//        List<List<Multimidia>> transactions = extractTransactions(assinaturas);
//
//        int[][] binaryData = applyBinaryTransformation(transactions);
//
//        // Análise Apriori
//        double minSupport = 0.2;
//        Apriori<Integer> apriori = new Apriori.Builder<Integer>().generateRules(true).support(minSupport).create();
//        RuleSet<Integer> ruleSet = apriori.execute(binaryData);
//
//        // Filtros
//        List<AssociationRule<Integer>> filteredRules = filterRules(ruleSet);
//
//        for (AssociationRule<Integer> rule : filteredRules) {
//            System.out.println(rule);
//        }
//    }
//
//    private static List<List<Multimidia>> extractTransactions(List<Assinatura> assinaturas) {
//        List<List<Multimidia>> transactions = new ArrayList<>();
//
//        for (Assinatura assinatura : assinaturas) {
//            List<Multimidia> multimidias = new ArrayList<>(assinatura.getMultimidias());
//            transactions.add(multimidias);
//        }
//
//        return transactions;
//    }
//
//    private static int[][] applyBinaryTransformation(List<List<Multimidia>> transactions) {
//        int[][] binaryData = new int[transactions.size()][];
//        for (int i = 0; i < transactions.size(); i++) {
//            List<Multimidia> transaction = transactions.get(i);
//            binaryData[i] = new int[transaction.size()];
//            for (int j = 0; j < transaction.size(); j++) {
//                // Se a condição for satisfeita, atribuir 1; caso contrário, 0
//                binaryData[i][j] = (transaction.get(j).getValorCondicional() > 0) ? 1 : 0;
//            }
//        }
//        return binaryData;
//    }
//
//    private static List<AssociationRule<Integer>> filterRules(RuleSet<Integer> ruleSet) {
//        // Inserir lógica
//        return ruleSet.asList();
//    }
//}
