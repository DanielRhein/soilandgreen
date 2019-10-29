package de.danielrhein;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.FilterWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class DocumentReaded implements  DocumentReadingListener {

    @Override
    public void documentReaded(List<PlantsDTO> dtoList)  {
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("ddmmyyyyhhMMss");
            String filename="out-" + simpleDateFormat.format(new Date()) + ".csv";
            FileWriter fileWriter = new FileWriter("out-" + simpleDateFormat.format(new Date()) + ".csv");
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            System.out.println("Write Results to file: "+filename);
            for (int i = 0; i < dtoList.size(); i++) {
                bufferedWriter.write(dtoList.get(i).toString());
            }
            bufferedWriter.close();
            fileWriter.close();
        }
        catch(Exception ex)
        {
            System.out.println(ex);
        }

    }
}