package com.example.springbootmockito;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.persistence.EntityNotFoundException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

/**
 * Created by mtumilowicz on 2018-08-11.
 */
@SpringBootTest
@ExtendWith(SpringExtension.class)
@ExtendWith(MockitoExtension.class)
class CustomerServiceTest {

    private static final Customer customer1 = Customer.builder()
            .id(1)
            .firstName("firstName")
            .build();

    @Test
    void findAll() {
//        given
        CustomerRepository customerRepository = mock(CustomerRepository.class);
        when(customerRepository.findAll()).thenReturn(Collections.singletonList(customer1));
        
//        and
        CustomerService customerService = new CustomerService(customerRepository);
        
//        when
        List<Customer> customers = customerService.findAll();

//        then
        verify(customerRepository).findAll();
        assertThat(customers, is(Collections.singletonList(customer1)));
    }

    @Test
    void findById_found() {
//        given
        CustomerRepository customerRepository = mock(CustomerRepository.class);
        when(customerRepository.findById(1)).thenReturn(Optional.of(customer1));
        
//        and
        CustomerService customerService = new CustomerService(customerRepository);

//        when
        Customer customer = customerService.findById(1);

//        then
        verify(customerRepository).findById(1);
        assertThat(customer, is(customer1));
    }

    @Test
    void findById_notFound() {
//        given
        CustomerRepository customerRepository = mock(CustomerRepository.class);
        when(customerRepository.findById(2)).thenReturn(Optional.empty());
        
//        and
        CustomerService customerService = new CustomerService(customerRepository);
        
//        when
        Executable executable = () -> customerService.findById(2);

//        then
        assertThrows(EntityNotFoundException.class, executable, "Customer with id = 1 cannot be found");
        verify(customerRepository, atMost(1)).findById(2);
    }
    
    
}