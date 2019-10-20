package net.artelis.wita.ui.web.order.view;

import java.io.IOException;
import java.io.StringReader;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.stream.StreamSource;
import javax.xml.xpath.XPathExpressionException;

import de.dialogika.infport.event.v1.schema.EventRequest;
import de.dialogika.infport.event.v1.schema.TEventResponse;
import de.dialogika.infport.schema.wita.types.v5.TMessage;
import de.dialogika.infport.schema.wita.types.v5.TMessagePosition;
import de.dialogika.infport.schema.wita.types.v5.TOrderInfo;
import lombok.extern.slf4j.Slf4j;
import net.artelis.common.date.DateUtil;
import net.artelis.wita.ui.config.WitaUIProperties;
import net.artelis.wita.ui.db.inubit.domain.WitaOrder;
import net.artelis.wita.ui.db.inubit.domain.WitaSoapLog;
import net.artelis.wita.ui.db.inubit.repository.OrderRepository;
import net.artelis.wita.ui.db.inubit.repository.SoapLogRepository;
import net.artelis.wita.ui.util.XmlSaxFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.xml.sax.SAXException;

@Controller
@Slf4j
public class OrderViewController {

    private static final String SOAP_BODY_PATH = "/*[local-name()='Envelope']/*[local-name()='Body']";
    private static final String SOAP_BODY_EVENTRESPONSE_PATH = SOAP_BODY_PATH + "/*[local-name()='eventResponse']";
    private static final String DEFAULT_STATUS = "UNKOWN";

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private SoapLogRepository soapLogRepository;
    @Autowired
    private WitaUIProperties properties;

    @GetMapping("/order/{id}")
    public String getView(@PathVariable(name = "id") int id, Model model) {
        Optional<WitaOrder> order = orderRepository.findById(id);
        model.addAttribute("order", mapToOrderModel(order.get()));
        return "order/view";
    }

    private OrderViewModel mapToOrderModel(WitaOrder order) {
        OrderViewModel m = new OrderViewModel();
        m.setOrderId(order.getId());
        m.setBusinessCase(order.getOrdertype());
        m.setTransmissionMethod(order.getTransfermethod());
        m.setProjektNummer(order.getProjectNumber());
        m.setInserted(DateUtil.toLocalDateTime(order.getDtinsert()));
        m.setUpdated(DateUtil.toLocalDateTime(order.getDtedit()));
        m.setSwitchName(order.getSwitchdistributionSwitch());
        m.setProductCode(order.getProduct());
        m.setProductType(order.getProducttype());
        m.setTicketId(order.getTicketid());
        m.setProjektId(order.getPmsProjectDocId());
        m.setProjektLink(properties.getPmsLinkProjekt() + order.getPmsProjectDocId());
        m.setLineDocId(order.getPmsLineDocId());
        List<WitaSoapLog> events = soapLogRepository.findByGlobalProcessId(order.getTicketid());
        List<ResponseModel> responses = events.stream().sorted(Comparator.comparing(WitaSoapLog::getDtinsert)).map(this::mapToResponseModel).collect(Collectors.toList());
        m.setResponses(responses);
        return m;
    }

    private ResponseModel mapToResponseModel(WitaSoapLog soapLog) {
        ResponseModel m = new ResponseModel();
        m.setInsert(soapLog.getDtinsert());
        m.setOperation(soapLog.getOperation());
        m.setXmlMessage(soapLog.getMessage());
        String message = soapLog.getMessage();
        if (message != null) {
            try {
                if (m.getOperation().contains("eventResponse")) {
                    TEventResponse response = extractResponse(message);
                    m.setStatus(getResponseCode(response));
                } else if (m.getOperation().contains("eventRequest")) {
                    EventRequest request = extractRequest(message);
                    if (request != null) {
                        TMessage eventMessage = request.getInfportEvent().getDetails().getMessage();
                        TOrderInfo info = request.getInfportEvent().getDetails().getOrder().getInfo();
                        if (eventMessage != null) {
                            List<TMessagePosition> positions = eventMessage.getPosition();
                            m.setPositions(positions.stream().map(x -> mapTMPositionToPositionModel(x)).collect(Collectors.toList()));
                        }
                        m.setChangeType(getChangeType(info));
                        m.setStatus(getStatus(info));
                    }
                }
            } catch (Exception e) {
                log.warn("Could not unmarshal the response xml for response " + soapLog.getId(), e);
            }
        }
        return m;
    }

    private String getStatus(TOrderInfo info) {
        if (info != null && info.getPubState() != null) {
            return info.getPubState().value();
        } else {
            return DEFAULT_STATUS;
        }
    }

    private String getChangeType(TOrderInfo info) {
        if (info != null && info.getChangeType() != null) {
            return info.getChangeType().value();
        } else {
            return null;
        }

    }

    private String getResponseCode(TEventResponse response) {
        if (response.getResponseCode() == null) {
            return DEFAULT_STATUS;
        } else {
            return response.getResponseCode();
        }

    }

    private PositionModel mapTMPositionToPositionModel(TMessagePosition tmPosition) {
        PositionModel position = new PositionModel();
        position.setMessageCode(tmPosition.getCode());
        position.setMessageText(tmPosition.getText());
        return position;
    }

    private TEventResponse extractResponse(String message) throws TransformerException, XPathExpressionException, ParserConfigurationException, SAXException, IOException {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setClassesToBeBound(TEventResponse.class);
        marshaller.setMappedClass(TEventResponse.class);
        String soapBodyMessage = XmlSaxFilter.getInstance().getSoapBody(message, SOAP_BODY_PATH);
        TEventResponse response = (TEventResponse) marshaller
                .unmarshal(new StreamSource(new StringReader(soapBodyMessage)));
        return response;

    }

    private EventRequest extractRequest(String message) throws TransformerException, XPathExpressionException, ParserConfigurationException, SAXException, IOException {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setClassesToBeBound(EventRequest.class);
        marshaller.setMappedClass(EventRequest.class);
        String soapBodyMessage = XmlSaxFilter.getInstance().getSoapBody(message, SOAP_BODY_PATH);
        EventRequest request = (EventRequest) marshaller
                .unmarshal(new StreamSource(new StringReader(soapBodyMessage)));
        return request;
    }

}
