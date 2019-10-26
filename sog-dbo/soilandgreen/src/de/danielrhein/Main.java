package de.danielrhein;

import java.io.IOException;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

public class Main implements DocumentReadingListener{

    public static void main(String[] args) {
        try {
            //SaxPlantDTOInterpreter interpreter = new SaxPlantDTOInterpreter();
            SaxReader saxReader= new SaxReader();
            //saxReader.addSaxListener(interpreter);
            saxReader.readFile("resources/informations.html");
            //saxReader.readFile("resources/informations-vg1.html");

        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void documentReaded(List<PlantsDTO> dtoList) {
       for(PlantsDTO plantsDTO:dtoList)
       {
           System.out.println(plantsDTO.getPlant_name());
        System.out.println(plantsDTO.getCultivation_start());
        System.out.println(plantsDTO.getCultivation_end());
        System.out.println(plantsDTO.getHarvest_start());
        System.out.println(plantsDTO.getHarvest_end());
        System.out.println(plantsDTO.getSow_start());
        System.out.println(plantsDTO.getSow_end());
        System.out.println(plantsDTO.getDaysUntilCultivation());
           System.out.println(plantsDTO.getDaysUntilHarvest());
           System.out.println(plantsDTO.getDistance());
       }
    }
}
