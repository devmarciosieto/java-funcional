package br.com.mmmsieto.intro;

@FunctionalInterface
public interface ValidadeCPF {

    Boolean apply(String cpf);

    static String calculaDigito(String cpfBase) {
        int soma = 0;
        for (int i = 0; i < cpfBase.length(); i++) {
            soma += (cpfBase.charAt(i) - '0') * (cpfBase.length() + 1 - i);
        }
        int digito = 11 - (soma % 11);
        return (digito > 9 ? "0" : Integer.toString(digito));
    }

    static Boolean validaCPF(String cpf) {
        cpf = cpf.replaceAll("[^0-9]", "");

        if (cpf.length() != 11 || cpf.chars().distinct().count() == 1) {
            return false;
        }

        String cpfBase = cpf.substring(0, 9);
        String cpfVerificadores = cpf.substring(9);

        String d1 = calculaDigito(cpfBase);
        String d2 = calculaDigito(cpfBase + d1);

        return cpfVerificadores.equals(d1 + d2);
    }
}

