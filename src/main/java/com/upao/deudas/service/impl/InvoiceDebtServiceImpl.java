package com.upao.deudas.service.impl;

import com.upao.deudas.domain.dto.RegisterInvoiceDebt;
import com.upao.deudas.domain.dto.RegisterInvoiceDetail;
import com.upao.deudas.domain.entity.InvoiceDebt;
import com.upao.deudas.domain.entity.InvoiceDetail;
import com.upao.deudas.domain.entity.User;
import com.upao.deudas.infra.repository.InvoiceDebtRepository;
import com.upao.deudas.infra.repository.UserRepository;
import com.upao.deudas.infra.security.JwtService;
import com.upao.deudas.service.InvoiceDebtService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class InvoiceDebtServiceImpl implements InvoiceDebtService {
    private final InvoiceDebtRepository invoiceDebtRepository;
    private final UserRepository userRepository;
    private final JwtService jwtService;

    @Transactional
    @Override
    public InvoiceDebt registerInvoiceDebt(RegisterInvoiceDebt registerInvoiceDebt, String token) {
        String email = jwtService.getCorreoFromToken(token);
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado con email: " + email));

        boolean exists = invoiceDebtRepository.existsByNumberDocumentAndUser(registerInvoiceDebt.numberDocument(), user);
        if (exists) {
            throw new IllegalArgumentException("Ya existe una deuda con este número de documento para este usuario.");
        }

        InvoiceDebt invoiceDebt = new InvoiceDebt();
        invoiceDebt.setNumberDocument(registerInvoiceDebt.numberDocument());
        invoiceDebt.setCompany(registerInvoiceDebt.company());
        invoiceDebt.setAmount(registerInvoiceDebt.amount());
        invoiceDebt.setDateExpiration(registerInvoiceDebt.dateExpiration());
        invoiceDebt.setClientName(registerInvoiceDebt.clientName());
        invoiceDebt.setClientAddress(registerInvoiceDebt.clientAddress());
        invoiceDebt.setPaymentMethod(registerInvoiceDebt.paymentMethod());
        invoiceDebt.setUser(user);

        List<InvoiceDetail> invoiceDetails = new ArrayList<>();
        double subtotal = 0;

        for (RegisterInvoiceDetail detailDTO : registerInvoiceDebt.invoiceDetails()) {
            InvoiceDetail detail = new InvoiceDetail();
            detail.setProduct(detailDTO.product());
            detail.setPrice(detailDTO.price());
            detail.setQuantity(detailDTO.quantity());
            double amount = detailDTO.quantity() * detailDTO.price();
            detail.setAmount(amount);
            subtotal += amount;
            detail.setInvoiceDebt(invoiceDebt);
            invoiceDetails.add(detail);
        }

        double igv = subtotal * 0.18;
        double calculatedTotal = subtotal + igv;

        if (Math.abs(calculatedTotal - registerInvoiceDebt.amount()) > 0.01) {
            throw new IllegalArgumentException("El monto total proporcionado no coincide con los detalles de la factura.");
        }

        invoiceDebt.setSubtotal(subtotal);
        invoiceDebt.setIgv(igv);

        invoiceDebt.setInvoiceDetails(invoiceDetails);

        return invoiceDebtRepository.save(invoiceDebt);
    }
}