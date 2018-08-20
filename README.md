[![Build Status](https://travis-ci.com/mtumilowicz/springboot-mockito.svg?branch=master)](https://travis-ci.com/mtumilowicz/springboot-mockito)

# springboot-mockito
The main goal of this project is to explore basics od `Mockito` in `Spring Boot` environment.

# manual
* add `@ExtendWith(MockitoExtension.class)` to the class
* mocking
    1. no args
        ```
        CustomerRepository customerRepository = mock(CustomerRepository.class);
        when(customerRepository.findAll()).thenReturn(Collections.singletonList(customer1));
        ```
    
    1. regardless argument
        ```
        CustomerRepository customerRepository = mock(CustomerRepository.class);
        when(customerRepository.findById(any(Integer.class))).thenReturn(Optional.of(customer1));
        ```
    
    1. specific argument
        ```
        CustomerRepository customerRepository = mock(CustomerRepository.class);
        when(customerRepository.findById(1)).thenReturn(Optional.of(customer1));
        ```    
    
    * _Note that_:
        `when(...).thenReturn(...)` and `doReturn(...).when(...)`
        are equivalent for mocks, they differ when comes to spy:
        * **when thenReturn** - makes a real method call just before the specified value will 
        be returned (if the called method throws an `Exception` you have to deal with it and you
        still get the result),
        * **doReturn when** - does not call the method at all.
    
* to verify how many times method was invoked:

    1. once (no args)
        ```
        verify(customerRepository).findAll();
        ```
    
    1. twice (specific argument)
        ```
        verify(customerRepository, times(2)).findAll();
        ```
    
    1. at least once (regardless argument)
        ```
        verify(customerRepository, atLeast(1)).findById(any(Integer.class));
        ```
    
    1. at most once
        ```
        verify(customerRepository, atMost(1)).findById(2);
        ```
    
    1. zero
        ```
        verify(customerRepository, never()).findById(2);
        ```
    
# tests
**Coverage**: `70%`