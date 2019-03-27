package dao;

import domain.Customer;

public interface CustomerRepository {
	Customer findById(String customerId);
}
