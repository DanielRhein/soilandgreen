package net.artelis.wita.ui.web.order.view;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import net.artelis.wita.ui.util.BusinessCase;
import net.artelis.wita.ui.util.ChangeType;

@Getter
@Setter
public class OrderViewModel {

    private int orderId;
    private BusinessCase businessCase;
    private ChangeType changeType;
    private String partnerId;
    private String serviceId;
    private String externalOrderId;
    //private ProcessStatus orderStatus;
    private LocalDateTime inserted;
    private LocalDateTime updated;
    private String productCode;
    private String productType;
    // private ContractStatus contractStatus;
    private int orderProgress;
    private LocalDate plannedRealizationDate;

    private String projektId;
    private String projektNummer;
    private String projektLink;

    private String ticketId;
    private String distributionNumber;
    private String switchName;
    private String wbcid;
    private String lineDocId;

    private String transmissionMethod;

    private List<ResponseModel> responses;


}
