package com.upao.deudas.service.impl;

import com.upao.deudas.domain.dto.RegisterLoanDebt;
import com.upao.deudas.domain.entity.LoanDebt;
import com.upao.deudas.domain.entity.PaymentSchedule;
import com.upao.deudas.domain.entity.User;
import com.upao.deudas.infra.repository.LoanDebtRepository;
import com.upao.deudas.infra.repository.UserRepository;
import com.upao.deudas.infra.security.JwtService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LoanDebtServiceImpl {
    private final LoanDebtRepository loanDebtRepository;
    private final UserRepository userRepository;
    private final JwtService jwtService;

    @Transactional
    public LoanDebt registerLoanDebt(RegisterLoanDebt registerLoanDebt, String token) {
        String email = jwtService.getCorreoFromToken(token);
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado con email: " + email));

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

        double monthlyInstallment = calculateMonthlyInstallment(registerLoanDebt.interestRate(), registerLoanDebt.termInMonths(), registerLoanDebt.amount());

        paymentSchedules.add(createInitialPaymentSchedule(balance, dueDate, loanDebt));

        for (int i = 1; i <= registerLoanDebt.termInMonths(); i++) {
            dueDate = dueDate.plusDays(30);  // Sumar 30 días para la siguiente fecha de vencimiento
            double interest = calculateInterest(registerLoanDebt.interestRate(), balance);
            double principal = calculatePrincipal(monthlyInstallment, interest);
            double installment = monthlyInstallment;

            balance = calculateBalance(balance, principal);

            PaymentSchedule paymentSchedule = new PaymentSchedule();
            paymentSchedule.setPaymentNumber(i);
            paymentSchedule.setDueDate(dueDate);
            paymentSchedule.setBalance(balance);
            paymentSchedule.setPrincipal(principal);
            paymentSchedule.setInterest(interest);
            paymentSchedule.setInstallment(installment);
            paymentSchedule.setLoanDebt(loanDebt);

            paymentSchedules.add(paymentSchedule);
        }

        loanDebt.setPaymentSchedule(paymentSchedules);

        return loanDebtRepository.save(loanDebt);
    }

    private PaymentSchedule createInitialPaymentSchedule(double balance, LocalDate dueDate, LoanDebt loanDebt) {
        PaymentSchedule initialPayment = new PaymentSchedule();
        initialPayment.setPaymentNumber(0);
        initialPayment.setDueDate(dueDate);
        initialPayment.setBalance(balance);
        initialPayment.setPrincipal(0.0);
        initialPayment.setInterest(0.0);
        initialPayment.setInstallment(0.0);
        initialPayment.setLoanDebt(loanDebt);
        return initialPayment;
    }

    public static double calculateBalance(double previousBalance, double principal) {
        return previousBalance - principal;
    }

    public static double calculateMonthlyInstallment(double annualInterestRate, int totalMonths, double loanAmount) {
        double monthlyInterestRate = annualInterestRate / 12 / 100;
        return loanAmount * monthlyInterestRate / (1 - Math.pow(1 + monthlyInterestRate, -totalMonths));
    }

    public static double calculatePrincipal(double installment, double interest) {
        return installment - interest;
    }

    public static double calculateInterest(double annualInterestRate, double balance) {
        double monthlyInterestRate = annualInterestRate / 12 / 100;
        return balance * monthlyInterestRate;
    }
}
