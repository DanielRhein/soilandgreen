package net.artelis.wita.ui.web.order.list;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import net.artelis.wita.ui.config.WitaUIProperties;
import net.artelis.wita.ui.db.inubit.domain.WitaOrder;
import net.artelis.wita.ui.db.inubit.repository.OrderRepository;
import net.artelis.wita.ui.util.BusinessCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class OrderListController {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private WitaUIProperties properties;

    @GetMapping("/order/list")
    public String getList(
            Model model,
            @RequestParam(name = "bc", required = false) Set<BusinessCase> bc,
            @RequestParam(name = "q", required = false) String q,
            @RequestParam(name = "p", defaultValue = "0") int page) {

        Page<WitaOrder> results = orderRepository.findByBusinessCaseAndChangeTypeAndStatusAndSearchTerm(bc, q, PageRequest.of(page, 10, Sort.by(Direction.DESC, "id")));
        List<OrderListModel> orders = results.stream().map(this::mapToOrderModel).collect(Collectors.toList());

        model.addAttribute("items", orders);
        model.addAttribute("totalPages", results.getTotalPages());
        model.addAttribute("page", results.getNumber());

        return "order/list";
    }

    @GetMapping("find/order")
    public String getOrder(
            Model model,
            @RequestParam(name = "q", required = false) String q
    ) {
        if (q != null) {
            Page<WitaOrder> results = orderRepository.findByBusinessCaseAndChangeTypeAndStatusAndSearchTerm(null, q, PageRequest.of(0, 10, Sort.by(Direction.DESC, "id")));
            if (results.getTotalElements() > 1) {

                return "redirect:/order/list?q="+q;
            } else {
                if (results.get().findFirst().isPresent()) {
                    return "redirect:/order/" + results.get().findFirst().get().getId();
                }

            }
        }
        return "redirect:/";
    }

    @GetMapping("/login")
    public String login(HttpServletRequest request) throws ServletException {
        return "redirect:/";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request) throws ServletException {
        request.logout();
        return "redirect:/";
    }

    private OrderListModel mapToOrderModel(WitaOrder order) {
        OrderListModel m = new OrderListModel();
        m.setOrderId(order.getId());
        if (null != order.getLocationsAPersonName()) {
            m.setCustomerName(order.getLocationsAPersonName());
            m.setCustomerSurname(order.getLocationsAPersonFirstname());
        } else {
            m.setCustomerName(order.getLocationsACompanyName());
        }
        m.setCustomerCity(order.getLocationsACity());
        m.setCustomerStreet(order.getLocationsAStreet());
        m.setCustomerZip(order.getLocationsAZip());
        m.setBusinessCase(order.getOrdertype());
        m.setProduct(order.getProduct());
        m.setProjektNummer(order.getProjectNumber());
        m.setProjektId(order.getPmsProjectDocId());
        m.setProjektLink(properties.getPmsLinkProjekt() + m.getProjektId());
        m.setLineId(order.getPmsLineDocId());
        m.setTicketId(order.getTicketid());
        return m;
    }

}

