package com.bank.digitalWallet.domain;

import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "transactions")
public class Transaction {


    @Id
    @Column(name = "Transaction_id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID Id;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;

    @ManyToMany
    @JoinTable(
            name = "transaction_cards",
            joinColumns = @JoinColumn(name = "transaction_id"),
            inverseJoinColumns = @JoinColumn(name = "card_id")
    )
    private List<Card> cards;

    @Column(name = "amount")
    private double Amount;

    @Column(name = "transaction_type")
    @Enumerated(EnumType.STRING)
    private ETransactionType Type;

    public UUID getId() {
        return Id;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public void setId(UUID id) {
        Id = id;
    }
    
    public List<Card> getCards() {
        return cards;
    }

    public void setCards(List<Card> cards) {
        this.cards = cards;
    }

    public double getAmount() {
        return Amount;
    }

    public void setAmount(double amount) {
        Amount = amount;
    }

    public ETransactionType getType() {
        return Type;
    }

    public void setType(ETransactionType type) {
        Type = type;
    }
}
