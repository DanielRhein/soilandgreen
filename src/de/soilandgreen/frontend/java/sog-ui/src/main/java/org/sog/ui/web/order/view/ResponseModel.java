package net.artelis.wita.ui.web.order.view;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseModel {

    private String operation;
    private LocalDateTime insert;
    private String status;
    private String errorText;
    private String xmlMessage;
    private String changeType;
    private List<PositionModel> positions;

}
