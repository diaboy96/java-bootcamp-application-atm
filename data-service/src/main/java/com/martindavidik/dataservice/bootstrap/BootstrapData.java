package com.martindavidik.dataservice.bootstrap;

import com.martindavidik.dataservice.domain.BankAccount;
import com.martindavidik.dataservice.domain.Card;
import com.martindavidik.dataservice.domain.Client;
import com.martindavidik.dataservice.service.BankAccountService;
import com.martindavidik.dataservice.service.CardService;
import com.martindavidik.dataservice.service.ClientService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootstrapData implements CommandLineRunner {

    private final BankAccountService bankAccountService;
    private final CardService cardService;
    private final ClientService clientService;

    public BootstrapData(BankAccountService bankAccountService, CardService cardService, ClientService clientService) {
        this.bankAccountService = bankAccountService;
        this.cardService = cardService;
        this.clientService = clientService;
    }

    /**
     * Bootstraps testing data
     */
    @Override
    public void run(String... args) {
        // create client instances (simulated process of registration to internet banking)
        Client clientAdam = new Client("Adam", "Novák", "9911115445", "adam.novak@email.cz");
        clientService.save(clientAdam);

        Client clientJosef = new Client("Josef", "Přibyl", "7307164942", "jpribyl12@gmail.com");
        clientService.save(clientJosef);

        Client clientMarie = new Client("Marie", "Vyskočilová", "7559104432", "marvys@seznam.cz");
        clientService.save(clientMarie);

        Client clientJan = new Client("Jan", "Skočdopole", "9701263099", "skocdopole.jan54@seznam.cz");
        clientService.save(clientJan);

        Client clientLubos = new Client("Luboš", "Smejkal", "9303268051", "lubos.sme@centrum.cz");
        clientService.save(clientLubos);

        Client clientZdena = new Client("Zdena", "Mašková", "0151031771", "zdena63@email.cz");
        clientService.save(clientZdena);


        // create bank accounts for the clients (the contract for establishing a bank account was signed by the clients)
        BankAccount adamsBankAccount = new BankAccount(clientAdam);
        adamsBankAccount.setBalance(28543.58);
        bankAccountService.save(adamsBankAccount);

        BankAccount josefsBankAccount = new BankAccount(clientJosef);
        josefsBankAccount.setBalance(11300);
        bankAccountService.save(josefsBankAccount);

        BankAccount mariesBankAccount = new BankAccount(clientMarie);
        mariesBankAccount.setBalance(573.02);
        bankAccountService.save(mariesBankAccount);

        BankAccount jansBankAccount = new BankAccount(clientJan);
        bankAccountService.save(jansBankAccount);

        BankAccount lubossBankAccount = new BankAccount(clientLubos);
        bankAccountService.save(lubossBankAccount);

        BankAccount zdenasBankAccount = new BankAccount(clientZdena);
        bankAccountService.save(zdenasBankAccount);


        // issuing a payment cards to the clients
        Card adamsCard = new Card(adamsBankAccount, "4024007146695589", "02", "26", "124");
        cardService.save(adamsCard);

        Card josefsCard = new Card(josefsBankAccount, "4916885075448851", "02", "27", "212");
        cardService.save(josefsCard);

        Card mariesCard = new Card(mariesBankAccount, "5278458530499205", "10", "29", "355");
        cardService.save(mariesCard);

        Card jansCard = new Card(jansBankAccount, "5322958759809972", "07", "27", "512");
        cardService.save(jansCard);

        Card LubossCard = new Card(lubossBankAccount, "5237615706602470", "05", "23", "349");
        cardService.save(LubossCard);

        Card zdenasCard = new Card(zdenasBankAccount, "6011010733248143", "09", "27", "319");
        cardService.save(zdenasCard);


        // deletion of payment card
        cardService.delete(jansCard);

        // deletion of bank account (with payment card linked to the account)
        bankAccountService.delete(jansBankAccount);

        // deletion of client (with his bank accounts and cards linked to accounts)
        clientService.delete(clientZdena);


        // set pin code for Adam´s payment card
        cardService.setPinCodeForTheCard(adamsCard, "0123");
    }
}
