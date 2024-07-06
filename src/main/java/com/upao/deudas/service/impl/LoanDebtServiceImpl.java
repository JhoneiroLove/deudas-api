package com.upao.deudas.service.impl;

import com.upao.deudas.domain.dto.RegisterLoanDebt;
import com.upao.deudas.domain.entity.LoanDebt;
import com.upao.deudas.domain.entity.PaymentSchedule;
import com.upao.deudas.domain.entity.User;
import com.upao.deudas.infra.repository.LoanDebtRepository;
import com.upao.deudas.infra.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LoanDebtServiceImpl {
    private final LoanDebtRepository loanDebtRepository;
    private final UserRepository userRepository;

    @Transactional
    public LoanDebt registerLoanDebt(RegisterLoanDebt registerLoanDebt, Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));

        LoanDebt loanDebt = new LoanDebt();
        loanDebt.setNumberDocument(registerLoanDebt.numberDocument());
        loanDebt.setCompany(registerLoanDebt.company());
        loanDebt.setAmount(registerLoanDebt.amount());
        loanDebt.setDateExpiration(registerLoanDebt.dateExpiration());
        loanDebt.setInterestRate(registerLoanDebt.interestRate());
        loanDebt.setTermInMonths(registerLoanDebt.termInMonths());
        loanDebt.setUser(user);

        double balance = registerLoanDebt.amount();
        List<PaymentSchedule> paymentSchedules = new ArrayList<>();
        LocalDate dueDate = registerLoanDebt.dateExpiration();

        for (int i = 0; i <= registerLoanDebt.termInMonths(); i++) {
            PaymentSchedule paymentSchedule = new PaymentSchedule();
            paymentSchedule.setPaymentNumber(i);
            paymentSchedule.setDueDate(dueDate.plusMonths(i));
            paymentSchedule.setBalance(balance);

            double principal = calculatePrincipal(registerLoanDebt.interestRate(), i, registerLoanDebt.termInMonths(), registerLoanDebt.amount());
            double interest = calculateInterest(registerLoanDebt.interestRate(), i, registerLoanDebt.termInMonths(), balance);
            double installment = calculateInstallment(principal, interest);

            paymentSchedule.setPrincipal(principal);
            paymentSchedule.setInterest(interest);
            paymentSchedule.setInstallment(installment);
            paymentSchedule.setLoanDebt(loanDebt);

            balance = calculateBalance(balance, principal);
            paymentSchedules.add(paymentSchedule);
        }

        loanDebt.setPaymentSchedule(paymentSchedules);

        return loanDebtRepository.save(loanDebt);
    }

    public static double calculateBalance(double previousBalance, double principal) {
        return previousBalance - principal;
    }

    public static double calculatePrincipal(double annualInterestRate, int currentMonth, int totalMonths, double loanAmount) {
        double monthlyInterestRate = annualInterestRate / 12 / 100;
        return loanAmount * monthlyInterestRate * Math.pow(1 + monthlyInterestRate, totalMonths) /
                (Math.pow(1 + monthlyInterestRate, totalMonths) - 1);
    }

    public static double calculateInterest(double annualInterestRate, int currentMonth, int totalMonths, double balance) {
        double monthlyInterestRate = annualInterestRate / 12 / 100;
        return balance * monthlyInterestRate;
    }

    public static double calculateInstallment(double principal, double interest) {
        return principal + interest;
    }
}
