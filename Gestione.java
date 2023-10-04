package Dizionario;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.HashMap;
import java.util.Set;
import java.util.TreeSet;
import java.util.Vector;

public class Gestione {
    private HashMap<String,Integer> memory = new HashMap<>();
    private File file = new File("Dizionario.dat");
    private final int MAX_DIM_WORD = 26;

    public Gestione(){
        fillMemory();
    }
    public void addWord(String word){
        try(RandomAccessFile raf = new RandomAccessFile(file,"rw")){
            raf.seek(raf.length());
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
        try(RandomAccessFile raf = new RandomAccessFile(file,"r")){
            while((character = raf.read())!=-1){
                for(int i=0;i<MAX_DIM_WORD;i++) {
                    if(character != '\0') {
                        word.append((char) character);
                    }
                }
                memory.put(word.toString(),+1);
            }
        }catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String orderlyMemory(){
        Set<Integer> treeSet = new TreeSet<>();
        memory.forEach((key,value)->{
            treeSet.add(value);
        });

        return "das";
    }
}
