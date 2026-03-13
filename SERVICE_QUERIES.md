# Service Query Cheat Sheet

Use these query patterns in repository classes for each service.

## 1. Account Service
Repository: `AccountRepository`

- By customer id (already used):
```java
List<Account> findByCustomer_Id(UUID customerId);
```

- By account number:
```java
Optional<Account> findByAccountNumber(String accountNumber);
```

- Accounts with balance greater than:
```java
List<Account> findByBalanceGreaterThan(double minBalance);
```

- SQL idea:
```sql
SELECT * FROM accounts WHERE customer_id = :customerId;
SELECT * FROM accounts WHERE account_number = :accountNumber;
SELECT * FROM accounts WHERE balance > :minBalance;
```

## 2. Customer Service
Repository: `CustomerRepository`

- By location code, case-insensitive (already used):
```java
List<Customer> findByLocation_CodeIgnoreCase(String locationCode);
```

- By location name, case-insensitive (already used):
```java
List<Customer> findByLocation_NameIgnoreCase(String locationName);
```

- By phone number:
```java
Optional<Customer> findByPhoneNumber(String phoneNumber);
```

- By email, case-insensitive:
```java
Optional<Customer> findByEmailIgnoreCase(String email);
```

- SQL idea:
```sql
SELECT c.* FROM customers c
JOIN location l ON c.location_id = l.location_id
WHERE LOWER(l.code) = LOWER(:locationCode);

SELECT c.* FROM customers c
JOIN location l ON c.location_id = l.location_id
WHERE LOWER(l.name) = LOWER(:locationName);

SELECT * FROM customers WHERE phone_number = :phoneNumber;
SELECT * FROM customers WHERE LOWER(email) = LOWER(:email);
```

## 3. Location Service
Repository: `LocationRepository`

- By code:
```java
Optional<Location> findByCode(String code);
```

- By name, case-insensitive:
```java
Optional<Location> findByNameIgnoreCase(String name);
```

- By type:
```java
List<Location> findByType(ELocationType type);
```

- Children by parent id:
```java
List<Location> findByParent_Id(UUID parentId);
```

- SQL idea:
```sql
SELECT * FROM location WHERE code = :code;
SELECT * FROM location WHERE LOWER(name) = LOWER(:name);
SELECT * FROM location WHERE type = :type;
SELECT * FROM location WHERE parent_id = :parentId;
```

## 4. Transaction Service
Repository: `TransactionRepository`

- By account id (already used):
```java
List<Transaction> findByAccount_Id(UUID accountId);
```

- By type:
```java
List<Transaction> findByType(ETransactionType type);
```

- By account id and type:
```java
List<Transaction> findByAccount_IdAndType(UUID accountId, ETransactionType type);
```

- By amount greater than:
```java
List<Transaction> findByAmountGreaterThan(double minAmount);
```

- SQL idea:
```sql
SELECT * FROM transactions WHERE account_id = :accountId;
SELECT * FROM transactions WHERE transaction_type = :type;
SELECT * FROM transactions WHERE account_id = :accountId AND transaction_type = :type;
SELECT * FROM transactions WHERE amount > :minAmount;
```

// 5. If You Want JPQL `@Query` Style

Example for transactions by account:
```java
@Query("SELECT t FROM Transaction t WHERE t.account.id = :accountId")
List<Transaction> getByAccount(@Param("accountId") UUID accountId);
```

Example for customers by location name ignore case:
```java
@Query("SELECT c FROM Customer c WHERE LOWER(c.location.name) = LOWER(:name)")
List<Customer> getByLocationName(@Param("name") String name);
```
