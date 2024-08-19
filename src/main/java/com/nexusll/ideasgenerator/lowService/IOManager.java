package com.nexusll.ideasgenerator.lowService;

import com.nexusll.ideasgenerator.model.ApiData;
import com.nexusll.ideasgenerator.repository.IdeaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

@Component
public class IOManager {

    @Autowired
    private IdeaRepository ideaRepository;

    public IOManager() {
        // We can populate the database from the file here
        readApiListFile();
    }

    public boolean readApiListFile() {
        try {
            File apisListFile = new File("src/main/resources/static/apis_list.txt");
            Scanner reader = new Scanner(apisListFile);
            while (reader.hasNextLine()) {
                String data = reader.nextLine();
                String[] chosenStringRaw = data.split("___");
                ApiData apiData = new ApiData(chosenStringRaw[0], chosenStringRaw[1], chosenStringRaw[2]);
                ideaRepository.save(apiData);
            }
            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public List<ApiData> pickApis(int NO_OF_PICKS) {
        // Fetch all APIs from the database
        List<ApiData> allApis = ideaRepository.findAll();

        // Pick random APIs
        Random rand = new Random();
        List<ApiData> chosenApis = new ArrayList<>();
        List<Integer> chosenIndexes = new ArrayList<>();

        for (int i = 0; i < NO_OF_PICKS; i++) {
            int tempVal = rand.nextInt(allApis.size());
            if (chosenIndexes.contains(tempVal)) {
                i--;
                continue;
            } else {
                chosenIndexes.add(tempVal);
                chosenApis.add(allApis.get(tempVal));
            }
        }
        return chosenApis;
    }
}
