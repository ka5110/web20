/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import dto.TransacDto;
import java.util.List;

/**
 *
 * @author 5110
 */
public interface TransactionService {
//    List<TransacDto> getAllReceivedTransactions(long userId);
//    List<TransacDto> getAllSentTransactions(long userId);
    List<TransacDto> getPendingRequestedTransactions(long userId);
    void approveTransactionRequest(List<TransacDto> transactions);
    void rejectTransactionRequest(List<TransacDto> transactions);
}
