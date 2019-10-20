package net.artelis.wita.ui.web.order.list;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;
import net.artelis.wita.ui.util.BusinessCase;

@Getter
@Setter
public class OrderListModel {

    private int orderId;
    private BusinessCase businessCase;
    private String ticketId;

    private int contractId;
    private String spriContractId;
    private String projektId;
    private String projektNummer;
    private String projektLink;
    private String lineId;

    private String customerName;
    private String customerSurname;
    private String customerStreet;
    private String customerHouseNr;
    private String customerZip;
    private String customerCity;

    private String product;

    private LocalDate plannedRealizationDate;
}
