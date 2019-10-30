package de.danielrhein;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import org.xml.sax.Attributes;

public class SaxPlantDTOInterpreter implements SaxListener {

    private List<PlantsDTO> plantsDTOList = new ArrayList<>();
    private PlantsDTO plantsDTO;
    private int tdcol = 0;

    private Stack<String> stringStack = new Stack<>();
    private Stack<URL> url = new Stack<>();

    private DocumentReadingListener documentReadingListener;

    //CONSTANCE OF CURRENT INTERPRETATION
    private static String SOWCOLOR= "#00FF00";
    private static String HARVESTCOLOR="#FF6600";
    private static String CULTIVATIONCOLOR= "#FFFF00";
    private static String BACKGROUNDCOLOR="bgcolor";
    private static String LINK="a";
    private static String TABLEROW="tr";
    private static String TABLECOLUMNS="td";
    private static String URL="href";
    private static Integer DAYSOFCULTIVATION=14;
    private static Integer DAYSUNTILHARVEST=15;
    private static Integer DISTANCETOEACHOTHER=16;

    public SaxPlantDTOInterpreter(DocumentReadingListener listener) {
        this.documentReadingListener = listener;
    }


    @Override
    public void startElement(java.lang.String uri, java.lang.String localName, java.lang.String qName, Attributes attributes) {
        stringStack.push(qName);
        if (LINK.equals(qName)) {
            for (int i = 0; i < attributes.getLength(); i++) {
                if (URL.equals(attributes.getQName(i))) {
                    try {
                        url.push(new URL(attributes.getValue(i)));
                    } catch (Exception ex) {
                        System.err.println(ex);
                    }

                }
            }
        }
        if (TABLECOLUMNS.equals(qName)) {
            if (plantsDTO == null) {
                plantsDTO = new PlantsDTO();
                plantsDTO.setMonthMapOffset(0);
            }
            tdcol++;
            for (int i = 0; i < attributes.getLength(); i++) {
                if (BACKGROUNDCOLOR.equals(attributes.getQName(i))) {
                    if (CULTIVATIONCOLOR.equals(attributes.getValue(i))) {
                        if (plantsDTO.getCultivation_start() == null) {
                            plantsDTO.setCultivation_start(plantsDTO.monthMap.get(tdcol));
                        } else {
                            plantsDTO.setCultivation_end(plantsDTO.monthMap.get(tdcol));
                        }
                    }
                    if (HARVESTCOLOR.equals(attributes.getValue(i))) {
                        if (plantsDTO.getSow_start() == null) {
                            plantsDTO.setSow_start(plantsDTO.monthMap.get(tdcol));
                        } else {
                            plantsDTO.setSow_end(plantsDTO.monthMap.get(tdcol));
                        }
                    }
                    if (SOWCOLOR.equals(attributes.getValue(i))) {
                        if (plantsDTO.getHarvest_start() == null) {
                            plantsDTO.setHarvest_start(plantsDTO.monthMap.get(tdcol));
                        } else {
                            plantsDTO.setHarvest_end(plantsDTO.monthMap.get(tdcol));
                        }
                    }
                }
            }
        }
        if (TABLEROW.equals(qName)) {
            tdcol = 0;
            if (plantsDTO != null) {
                plantsDTO.setMonthMapOffset(0);
            }
        }
    }

    @Override
    public void endElement(java.lang.String uri, java.lang.String localName, java.lang.String qName) {
        stringStack.pop();
    }

    @Override
    public void elementContent(java.lang.String content) {
        if (TABLECOLUMNS.equals(stringStack.peek())) {
            if (content.matches("[0-9]*")) {
                if (content != null && tdcol == DAYSOFCULTIVATION) {
                    plantsDTO.setDaysUntilCultivation(Integer.parseInt(content));
                }
                if (content != null && tdcol == DAYSUNTILHARVEST) {
                    plantsDTO.setDaysUntilHarvest(Integer.parseInt(content));
                }
                if (content != null && tdcol == DISTANCETOEACHOTHER) {
                    plantsDTO.setDistance(Integer.parseInt(content));
                }
            }
            System.out.println("TD-Content: " + content + "tdcolcount: " + tdcol);
        }
        if (TABLEROW.equals(stringStack.peek())) {
        }
        if (LINK.equals(stringStack.peek())) {
            System.out.println("A-Content : " + content);
            if (content != null && !content.isEmpty()) {
                content = content.trim().replace("\n\r","");
                if (plantsDTO != null) {
                    if (plantsDTO.getPlant_name() != null) {
                        plantsDTOList.add(plantsDTO);
                        plantsDTO = new PlantsDTO();
                        plantsDTO.setMonthMapOffset(1);
                        plantsDTO.setPlant_name(content);
                        plantsDTO.setUrl(url.pop());
                    } else {
                        plantsDTO.setPlant_name(content);
                        plantsDTO.setUrl(url.pop());
                        plantsDTO.setMonthMapOffset(1);
                    }
                }
            }
        }
    }

    @Override
    public void startDocument() {
        plantsDTOList.clear();
        plantsDTO = null;
    }

    @Override
    public void endDocument() {
        if (!this.plantsDTOList.contains(plantsDTO)) {
            this.plantsDTOList.add(plantsDTO);
        }
        if (documentReadingListener != null) {
            documentReadingListener.documentReaded(this.plantsDTOList);
        }
    }

    @Override
    public void failure() {

    }
}
