package Dizionario;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

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
        if(memory.containsKey(word)){
            memory.put(word,memory.get(word)+1);
        }else memory.put(word,1);
    }
    public void fillMemory(){
        int character = 0;
        StringBuilder word;
        try(RandomAccessFile raf = new RandomAccessFile(file,"r")){
            raf.seek(0);
            while(raf.getFilePointer()<raf.length()){
                word = new StringBuilder();
                for(int i=0;i<MAX_DIM_WORD;i++) {
                    character = raf.read();
                    if(character != '\0') {
                        word.append((char) character);
                    }
                }
                if(memory.containsKey(word.toString())){
                    memory.put(word.toString(),memory.get(word.toString())+1);
                }else memory.put(word.toString(),1);
            }
        }catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public String orderlyMemory(){
        StringBuilder string = new StringBuilder();
       LinkedHashMap<String, Integer> sortedMap = memory.entrySet().stream()
                .sorted(Map.Entry.comparingByValue())
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (e1, e2) -> e1,
                        LinkedHashMap::new
                ));
        sortedMap.forEach((key,value)->{
            //System.out.println(value);
            string.append("Word: ").append(key).append(" repetitions: ").append(value).append("\n");
        });
        return string.toString();
    }
}
