package com.howwow.cppnotificationservice.business.service.impl;

import com.howwow.cppnotificationservice.business.service.PaymentTemplateEmailService;
import com.howwow.event.PaymentEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;


@RequiredArgsConstructor
@Service
public class PaymentTemplateEmailServiceImpl implements PaymentTemplateEmailService {
    private final TemplateEngine templateEngine;

    @Override
    public String generatePaymentEmail(PaymentEvent event) {
        Context context = new Context();
        context.setVariable("event", event);
        return templateEngine.process("payment", context);
    }
}
