package sg.edu.nus.iss.day24_lecture.repositories;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.stereotype.Repository;

import sg.edu.nus.iss.day24_lecture.models.BankAccount;

@Repository
public class BankAccountRepo {
    
    @Autowired
    JdbcTemplate jdbcTemplate;

    private final String CHECK_BALANCEBYID_SQL = "select balance from bankaccount where id = ?";
    private final String GET_ACCOUNT_SQL = "select * from bankaccount where id = ?";
    private final String WITHDRAW_SQL = "update bankaccount set balance = balance - ? where id = ?";
    private final String DEPOSIT_SQL = "update bankaccount set balance = balance + ? where id = ?";
    private final String CREATE_ACCOUNT_SQL = "insert into bankaccount (full_name, is_active, acct_type, balance) values = (?, ?, ?, ?)";

    public Boolean checkBalance(Integer accountId, Float withdrawnAmount) {
        
        Boolean bWithdrawnBalanceAvailable = false;

        Float returnedBalanace = jdbcTemplate.queryForObject(CHECK_BALANCEBYID_SQL, Float.class, accountId);
        
        if(withdrawnAmount <= returnedBalanace) {
            bWithdrawnBalanceAvailable = true;
        }

        return bWithdrawnBalanceAvailable;
    }

    public BankAccount retrieveAccountDetails(Integer accountId) {

        BankAccount bankAccount = null;

        bankAccount = jdbcTemplate.queryForObject(GET_ACCOUNT_SQL, BeanPropertyRowMapper.newInstance(BankAccount.class), accountId);

        return bankAccount;
    }

    public Boolean withdrawAmount(Integer accountId, Float withdrawnAmount) {

        Boolean bWithdrawn = false;
        int iUpdated = 0;

        iUpdated = jdbcTemplate.update(WITHDRAW_SQL, withdrawnAmount, accountId);
        
        if (iUpdated > 0) {
            bWithdrawn = true;
        }
        return bWithdrawn;
    }

    public Boolean depositAmount(Integer accountId, Float depositAmount) {

        Boolean bDeposited = false;

        int iUpdated = 0;
        iUpdated = jdbcTemplate.update(DEPOSIT_SQL, depositAmount, accountId);

        if(iUpdated > 0) {
            bDeposited = true;
        }
        return bDeposited;
    }

    public Boolean createAccount(BankAccount bankAccount) {

        Boolean bCreated = false;

        bCreated = jdbcTemplate.execute(CREATE_ACCOUNT_SQL, new PreparedStatementCallback<Boolean>() {

            @Override
            public Boolean doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
                ps.setString(1, bankAccount.getFullName());
                ps.setBoolean(2, bankAccount.getIsActive());
                ps.setString(3, bankAccount.getAcctType());
                ps.setFloat(4, bankAccount.getBalance());
                Boolean result = ps.execute();

                return result;
            }
            
        });
        return bCreated;
    }

}
