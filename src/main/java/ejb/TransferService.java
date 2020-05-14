/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

/**
 *
 * @author 5110
 */
public interface TransferService {
  
    boolean payCredits(String from, String to, double amount);
    void requestCredits(String from, String to, double amount);

}
