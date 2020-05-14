/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import dao.TransacDao;
import dto.SystemUserDto;
import dto.TransacDto;
import entity.Transac;
import entity.TransacStatus;
import java.util.List;
import java.util.stream.Collectors;
import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.transaction.Transactional;

/**
 *
 * @author 5110
 */
@Stateless
@RolesAllowed({"users", "admins"})
public class TransactionServiceImpl implements TransactionService {

    @EJB
    TransacDao transactionDao;

    @EJB
    SystemUserService userService;

    @EJB
    TransferService transferService;

    public TransactionServiceImpl() {
    }

//    @Override
//    @Transactional(Transactional.TxType.REQUIRED)
//    public List<TransacDto> getAllReceivedTransactions(long id) {
//        SystemUserDto user = userService.findUser(id);
//        return Transac.asDto(transactionDao.getAllReceivedTransactions(user.getId()));
//    }
//
//    @Override
//    public List<TransacDto> getAllSentTransactions(long id) {
//        return Transac.asDto(transactionDao.getAllSentTransactions(id));
//    }

    @Override
    public List<TransacDto> getPendingRequestedTransactions(long userId) {
        return this.transactionDao
                .getAllReceivedRequests(userId)
                .stream()
                .filter(t -> t.getStatus() == TransacStatus.REQUESTED)
                .map(t -> {
                    TransacDto dto = t.asDto();
                    SystemUserDto user = userService.findUser(t.getTransactionPartakerId());
                    dto.setTransactionPartaker(user.asEntity());
                    return dto;
                })
                .collect(Collectors.toList());
    }


    @Override
    public void approveTransactionRequest(List<TransacDto> transactions) {
        transactions.forEach(transaction -> {
            SystemUserDto receiveUser = transaction.getTransactionHolder().toDto();
            SystemUserDto sendUser = userService.findUser(transaction.getTransactionPartakerId());

            transferService.payCredits(sendUser.getUsername(), receiveUser.getUsername(), transaction.getAmount());
            transactionDao.remove(transaction.asEntity().getId());
        });
    }

    @Override
    public void rejectTransactionRequest(List<TransacDto> transactions) {
        transactions.forEach(transaction -> {
            transactionDao.remove(transaction.asEntity().getId());
        });
    }

}