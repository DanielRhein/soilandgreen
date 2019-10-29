package de.danielrhein;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import org.xml.sax.Attributes;

public class SaxPlantDTOInterpreter implements SaxListener {

    private List<PlantsDTO> plantsDTOList = new ArrayList<>();
    private PlantsDTO plantsDTO;
    private boolean td = false;
    private int tdcol = 0;

    private Stack<String> stringStack = new Stack<>();

    private DocumentReadingListener documentReadingListener;

    public SaxPlantDTOInterpreter(DocumentReadingListener listener) {
        this.documentReadingListener = listener;
    }


    @Override
    public void startElement(java.lang.String uri, java.lang.String localName, java.lang.String qName, Attributes attributes) {
        stringStack.push(qName);
        if ("a".equals(qName)) {
            for (int i = 0; i < attributes.getLength(); i++) {
                if ("href".equals(attributes.getQName(i))) {
                    try {
                        plantsDTO.setUrl(new URL(attributes.getValue(i)));
                    } catch (Exception ex) {
                        System.err.println(ex);
                    }

                }
            }
        }
        if ("td".equals(qName)) {
            if (plantsDTO == null) {
                plantsDTO = new PlantsDTO();
                plantsDTO.setMonthMapOffset(0);
            }
            tdcol++;
            for (int i = 0; i < attributes.getLength(); i++) {
                if ("bgcolor".equals(attributes.getQName(i))) {
                    if ("#FFFF00".equals(attributes.getValue(i))) {
                        if (plantsDTO.getCultivation_start() == null) {
                            plantsDTO.setCultivation_start(plantsDTO.monthMap.get(tdcol));
                        } else {
                            plantsDTO.setCultivation_end(plantsDTO.monthMap.get(tdcol));
                        }
                    }
                    if ("#00FF00".equals(attributes.getValue(i))) {
                        if (plantsDTO.getSow_start() == null) {
                            plantsDTO.setSow_start(plantsDTO.monthMap.get(tdcol));
                        } else {
                            plantsDTO.setSow_end(plantsDTO.monthMap.get(tdcol));
                        }
                    }
                    if ("#00FF00".equals(attributes.getValue(i))) {
                        if (plantsDTO.getSow_start() == null) {
                            plantsDTO.setSow_start(plantsDTO.monthMap.get(tdcol));
                        } else {
                            plantsDTO.setSow_end(plantsDTO.monthMap.get(tdcol));
                        }
                    }
                    if ("#FF6600".equals(attributes.getValue(i))) {
                        if (plantsDTO.getHarvest_start() == null) {
                            plantsDTO.setHarvest_start(plantsDTO.monthMap.get(tdcol));
                        } else {
                            plantsDTO.setHarvest_end(plantsDTO.monthMap.get(tdcol));
                        }
                    }
                }
            }
        }
        if ("tr".equals(qName)) {
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
        if ("td".equals(stringStack.peek())) {
            if (content.matches("[0-9]*")) {
                if (content != null && tdcol == 14) {
                    plantsDTO.setDaysUntilCultivation(Integer.parseInt(content));
                }
                if (content != null && tdcol == 15) {
                    plantsDTO.setDaysUntilHarvest(Integer.parseInt(content));
                }
                if (content != null && tdcol == 16) {
                    plantsDTO.setDistance(Integer.parseInt(content));
                }
            }
            System.out.println("TD-Content: " + content + "tdcolcount: " + tdcol);
        }
        if ("tr".equals(stringStack.peek())) {
        }
        if ("a".equals(stringStack.peek())) {
            System.out.println("A-Content : " + content);
            if (content != null && !content.isEmpty()) {
                if (content.contains("\n"))
                {
                    content.replace("\n","");
                }
                if (plantsDTO != null) {
                    if (plantsDTO.getPlant_name() != null) {
                        plantsDTOList.add(plantsDTO);
                        plantsDTO = new PlantsDTO();
                        plantsDTO.setMonthMapOffset(1);
                        plantsDTO.setPlant_name(content);
                    } else {
                        plantsDTO.setPlant_name(content);
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
