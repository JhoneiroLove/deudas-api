package com.upao.deudas.service.impl;

import com.upao.deudas.domain.dto.RegisterCollectionDetail;
import com.upao.deudas.domain.dto.RegisterTaxDebt;
import com.upao.deudas.domain.entity.CollectionDetail;
import com.upao.deudas.domain.entity.TaxDebt;
import com.upao.deudas.domain.entity.User;
import com.upao.deudas.infra.repository.TaxDebtRepository;
import com.upao.deudas.infra.repository.UserRepository;
import com.upao.deudas.infra.security.JwtService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TaxDebtServiceImpl {
    private final TaxDebtRepository taxDebtRepository;
    private final UserRepository userRepository;
    private final JwtService jwtService;

    @Transactional
    public TaxDebt registerTaxDebt(RegisterTaxDebt registerTaxDebt, String token) {
        String email = jwtService.getCorreoFromToken(token);
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado con email: " + email));

        TaxDebt taxDebt = new TaxDebt();
        taxDebt.setNumberDocument(registerTaxDebt.numberDocument());
        taxDebt.setCompany(registerTaxDebt.company());
        taxDebt.setAmount(registerTaxDebt.amount());
        taxDebt.setDateExpiration(registerTaxDebt.dateExpiration());
        taxDebt.setPeriod(registerTaxDebt.period());
        taxDebt.setInterestRate(registerTaxDebt.interestRate());
        taxDebt.setUser(user);

        List<RegisterCollectionDetail> collectionDetailsDto = calculateCollectionDetails(registerTaxDebt.amount());
        List<CollectionDetail> collectionDetails = new ArrayList<>();

        for (RegisterCollectionDetail detailDTO : collectionDetailsDto) {
            CollectionDetail detail = new CollectionDetail();
            detail.setDescription(detailDTO.description());
            detail.setAmount(detailDTO.amount());
            detail.setTaxDebt(taxDebt);
            collectionDetails.add(detail);
        }

        taxDebt.setTaxDetails(collectionDetails);

        return taxDebtRepository.save(taxDebt);
    }

    private List<RegisterCollectionDetail> calculateCollectionDetails(double totalAmount) {
        List<RegisterCollectionDetail> details = new ArrayList<>();
        details.add(new RegisterCollectionDetail("property tax", totalAmount * 0.375));
        details.add(new RegisterCollectionDetail("public cleaning", totalAmount * 0.018));
        details.add(new RegisterCollectionDetail("green areas", totalAmount * 0.418));
        details.add(new RegisterCollectionDetail("public safety", totalAmount * 0.175));
        details.add(new RegisterCollectionDetail("tax forms", totalAmount * 0.013));
        return details;
    }
}
