package Dizionario;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.HashMap;

public class Gestione {
    private final int MAX_DIM_WORD = 26;

    public Gestione(){
        fillMemory();
    }
    private HashMap<String,Integer> memory = new HashMap<>();
    public void addWord(String word){
        try(RandomAccessFile raf = new RandomAccessFile("Dizionario.dat","rw")){
            raf.writeBytes(word);
            for(int i=0;i<(MAX_DIM_WORD-word.length());i++){
                raf.writeByte('\0');
            }
        }catch (IOException e){
            e.printStackTrace();
        }
        memory.put(word,+1);
    }

    public void fillMemory(){
        int character = 0;
        StringBuilder word = new StringBuilder();
        try(RandomAccessFile raf = new RandomAccessFile("Dizionario.dat","wr")){
            while((character = raf.read())!=-1){
                for(int i=0;i<MAX_DIM_WORD;i++){
                    word.append((char)character);
                }
                memory.put(word.toString(),+1);
            }
        }catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
