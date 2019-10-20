package net.artelis.wita.ui.util;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class BusinessCaseConverter implements AttributeConverter<BusinessCase, String> {
    private static final Map<String, BusinessCase> MAPPING = new HashMap<>();


    static {
        MAPPING.put("NEU", BusinessCase.NEU);
        MAPPING.put("KUE-KD", BusinessCase.KUE_KD);
        MAPPING.put("REX-MK", BusinessCase.REX_MK);
        MAPPING.put("PV", BusinessCase.PV);
        MAPPING.put("VBL", BusinessCase.VBL);
    }

    public BusinessCaseConverter() {
        super();
    }

    @Override
    public String convertToDatabaseColumn(BusinessCase sourceValue) {
        if (MAPPING.containsValue(sourceValue)) {
            Optional<Map.Entry<String, BusinessCase>> result = MAPPING.entrySet().stream().filter(x -> x.getValue().equals(sourceValue)).findFirst();
            if (result.isPresent()) {
                return result.get().getKey();
            }
        }
        return BusinessCase.UNKNOWN.name();
    }

    @Override
    public BusinessCase convertToEntityAttribute(String businessCase) {
        if (MAPPING.containsKey(businessCase)) {
            return MAPPING.get(businessCase);
        } else {
            return BusinessCase.UNKNOWN;
        }
    }
}
