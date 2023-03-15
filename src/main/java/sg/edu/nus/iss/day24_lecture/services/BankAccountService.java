package sg.edu.nus.iss.day24_lecture.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import sg.edu.nus.iss.day24_lecture.models.BankAccount;
import sg.edu.nus.iss.day24_lecture.repositories.BankAccountRepo;

@Service
public class BankAccountService {
    
    @Autowired
    BankAccountRepo bankAccountRepo;

    @Transactional(isolation = Isolation.READ_UNCOMMITTED)
    public Boolean transferMoney(Integer accountFrom, Integer accountTo, Float amount) {

        Boolean bTransferred = false;
        Boolean bWithdrawn = false;
        Boolean bDeposited = false;
        Boolean bCanWithdraw = false;

        Boolean bDepositAccountValid = false;
        Boolean bWithdrawalAccountValid = false;
        BankAccount depositAccount = null;
        BankAccount withdrawalAccount = null;
        Boolean bProceed = false;

        // check if accounts are valid (active)
        withdrawalAccount = bankAccountRepo.retrieveAccountDetails(accountFrom);
        depositAccount = bankAccountRepo.retrieveAccountDetails(accountTo);
        bWithdrawalAccountValid = withdrawalAccount.getIsActive();
        bDepositAccountValid = depositAccount.getIsActive();
        if (bWithdrawalAccountValid && bDepositAccountValid)
            bProceed = true;

        // check if sufficient amount available for withdrawal
        if (bProceed) {
            if (withdrawalAccount.getBalance() >= amount)
                bCanWithdraw = true;
        }

        if (bCanWithdraw) {
            // perform the withdrawal (requires transaction)
            bWithdrawn = bankAccountRepo.withdrawAmount(accountFrom, amount);

            // Simulating error before withdrawal
            bWithdrawn = false;
            if (!bWithdrawn) {
                throw new IllegalArgumentException("Simulate error before withdrawal");
            }
    
            // perform the deposit (requires transaction)
            bDeposited = bankAccountRepo.depositAmount(accountTo, amount);
        }

        // check if transactions successful
        if (bWithdrawn && bDeposited)
            bTransferred = true;
        
        return bTransferred;
    }
}
