package com.nexusll.ideasgenerator.lowService;
import com.nexusll.ideasgenerator.model.ApiData;
import com.nexusll.ideasgenerator.repository.IdeaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.*;
import java.util.List;
import java.util.Scanner;

@Service
public class IOManager {

    @Autowired
    private IdeaRepository ideaRepository;

    public void readApiListFIle(){
        try {
            File apisListFile = new File("src/main/resources/static/apis_list.txt");
            Scanner reader = new Scanner(apisListFile);
            while (reader.hasNextLine()) {
                String data = reader.nextLine();
                String [] chosenStringRaw = data.split("___");
                if (chosenStringRaw.length==3 && !ideaRepository.existsByUrl(chosenStringRaw[2])) {
                   ideaRepository.save(new ApiData(chosenStringRaw[0], chosenStringRaw[1], chosenStringRaw[2]));
                }
            }
            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public List<ApiData> pickApis(int NO_OF_PICKS) {
        return ideaRepository.getRandom(NO_OF_PICKS);
    }
}
