package de.danielrhein;

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
        //StringBuilder stringBuilder = new StringBuilder();
        //stringBuilder.append("URI: "+ uri);
        //stringBuilder.append("LocalName: "+localName);
        //stringBuilder.append("Qname: "+ qName);
        stringStack.push(qName);
        if ("td".equals(qName)) {
            if (td != true) {
                td = true;
                plantsDTO = new PlantsDTO();
            } else {
                tdcol++;
            }
            for (int i = 0; i < attributes.getLength(); i++) {
                if ("bgcolor".equals(attributes.getQName(i))) {
                    if ("#FFFF00".equals(attributes.getValue(i))) {
                        if (plantsDTO.getCultivation_start() == null) {
                            plantsDTO.setCultivation_start(plantsDTO.monthMap.get(i));
                        } else {
                            plantsDTO.setCultivation_end(plantsDTO.monthMap.get(i));
                        }
                    }
                    if ("#00FF00".equals(attributes.getValue(i))) {
                        if (plantsDTO.getSow_start() == null) {
                            plantsDTO.setSow_start(plantsDTO.monthMap.get(i));
                        } else {
                            plantsDTO.setSow_end(plantsDTO.monthMap.get(i));
                        }
                    }
                    if ("#00FF00".equals(attributes.getValue(i))) {
                        if (plantsDTO.getSow_start() == null) {
                            plantsDTO.setSow_start(plantsDTO.monthMap.get(i));
                        } else {
                            plantsDTO.setSow_end(plantsDTO.monthMap.get(i));
                        }
                    }
                    if ("#FF6600".equals(attributes.getValue(i))) {
                        if (plantsDTO.getHarvest_start() == null) {
                            plantsDTO.setHarvest_end(plantsDTO.monthMap.get(i));
                        } else {
                            plantsDTO.setHarvest_end(plantsDTO.monthMap.get(i));
                        }
                    }
                }
            }
            if ("tr".equals(qName)) {
                plantsDTOList.add(plantsDTO);
                td = false;
                tdcol = 0;
            }


            //stringBuilder.append(" AttributeQname= "+attributes.getQName(i)+" Value:"+attributes.getValue(i));
            //     System.out.printf("Attribute no %d: %s = %s\n", i, attributes.getQName(i), attributes.getValue(i));
        }
        //System.out.println(stringBuilder.toString());
    }

    @Override
    public void endElement(java.lang.String uri, java.lang.String localName, java.lang.String qName) {
        //StringBuilder stringBuilder = new StringBuilder();
        //stringBuilder.append("URI: "+ uri);
        //stringBuilder.append("LocalName: "+localName);
        //stringBuilder.append("Qname: "+ qName);
        stringStack.pop();
        //System.out.println("End-Element: "+ stringBuilder.toString());
    }

    @Override
    public void elementContent(java.lang.String content) {
        if ("td".equals(stringStack.peek())) {
            System.out.println("TD-Content: " + content);
        }
        if ("tr".equals(stringStack.peek())) {
        }
        if ("a".equals(stringStack.peek()))
        {
            System.out.println("A-Content : " + content);
            if (content != null && !content.isEmpty()) {
                if (plantsDTO != null) {
                    if (plantsDTO.getPlant_name() != null & content.equals(plantsDTO.getPlant_name())) {
                        plantsDTO.setPlant_name(content);
                    } else {
                        plantsDTOList.add(plantsDTO);
                        plantsDTO = new PlantsDTO();
                        plantsDTO.setPlant_name(content);
                    }
                }
            }
        }
    }

    @Override
    public void startDocument() {
        plantsDTOList.clear();
    }

    @Override
    public void endDocument() {
        if (documentReadingListener == null) {
            documentReadingListener.documentReaded(this.plantsDTOList);
        }
    }

    @Override
    public void failure() {

    }
}
