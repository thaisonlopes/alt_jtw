package org.primefaces.util.base;

public class AuxString {
    public static String completeADireita(String str, int tam, String comp) {
        try {
            if (str == null) {
                return completeAEsquerda(" ", tam, comp);
            }
            while (str.length() < tam) {
                str += comp;
            }
            return str.substring(0, tam);
        } catch (Exception e) {
            e.printStackTrace();
            return completeAEsquerda(" ", tam, comp);
        }
    }
    public static String completeAEsquerda(String str, int tam, String comp) {
        String temp = ""; //4573 = 000000004573
        try {
            if (str == null) {
                return completeAEsquerda(" ", tam, comp);
            }
            int itemp = tam - str.length(); //15-4 = 11
            while (temp.length() < itemp) {
                temp += comp;
            }
            temp += str;
            return temp.substring(0, tam);
        } catch (Exception e) {
            e.printStackTrace();
            return temp.substring(0, tam);
        }
    }

}
