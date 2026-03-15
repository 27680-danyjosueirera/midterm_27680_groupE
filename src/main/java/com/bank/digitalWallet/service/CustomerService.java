package com.bank.digitalWallet.service;

import com.bank.digitalWallet.domain.Customer;
import com.bank.digitalWallet.domain.Location;
import com.bank.digitalWallet.repository.CustomerRepository;
import com.bank.digitalWallet.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepo;

    @Autowired
    private LocationRepository locationRepo;

    public String saveCustomer(Customer customer) {
        if (customer == null) {
            return "Customer payload is required.";
        }

        if (customer.getPhoneNumber() == null || customer.getPhoneNumber().isBlank()) {
            return "Phone number is required.";
        }

        if (customerRepo.existsByPhoneNumber(customer.getPhoneNumber())) {
            return "Customer with this phone number already exists.";
        }

        customerRepo.save(customer);
        return "Customer saved successfully.";
    }

    public List<Customer> findByLocationCode(String locationCode) {
        if (locationCode == null || locationCode.isBlank()) {
            return Collections.emptyList();
        }

        return customerRepo.findByLocation_CodeIgnoreCase(locationCode);
    }

    public List<Customer> findByLocationName(String locationName) {
        if (locationName == null || locationName.isBlank()) {
            return Collections.emptyList();
        }

        return customerRepo.findByLocation_NameIgnoreCase(locationName);
    }

    public List<Customer> findByLocationCodeTree(String locationCode) {
        if (locationCode == null || locationCode.isBlank()) {
            return Collections.emptyList();
        }

        Optional<Location> root = locationRepo.findByCodeIgnoreCase(locationCode);
        if (root.isEmpty()) {
            return Collections.emptyList();
        }

        Set<UUID> locationIds = collectDescendantLocationIds(root.get().getId());
        if (locationIds.isEmpty()) {
            return Collections.emptyList();
        }

        return customerRepo.findByLocation_IdIn(locationIds);
    }

    private Set<UUID> collectDescendantLocationIds(UUID rootId) {
        List<Location> allLocations = locationRepo.findAll();

        Map<UUID, List<Location>> childrenByParent = new HashMap<>();
        for (Location location : allLocations) {
            Location parent = location.getParent();
            if (parent != null && parent.getId() != null) {
                UUID parentId = parent.getId();
                childrenByParent.computeIfAbsent(parentId, k -> new ArrayList<>()).add(location);
            }
        }

        Set<UUID> ids = new HashSet<>();
        Deque<UUID> stack = new ArrayDeque<>();
        stack.push(rootId);

        while (!stack.isEmpty()) {
            UUID id = stack.pop();
            if (!ids.add(id)) {
                continue;
            }
            List<Location> children = childrenByParent.get(id);
            if (children != null) {
                for (Location child : children) {
                    if (child.getId() != null) {
                        stack.push(child.getId());
                    }
                }
            }
        }

        return ids;
    }
}
