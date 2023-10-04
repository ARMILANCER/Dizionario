package Dizionario;

public class Main {
    private static Gestione dizionario = new Gestione();
    private static String[] test = {"Computer","Zaino","Dizionario","Olivetti","Mercoledi","Nota","Zaino","Dizionario","Olivetti","Computer","Computer",};

    public static void main(String[] args){
        for(int i=0;i<test.length;i++){
            dizionario.addWord(test[i]);
        }
    }
}
