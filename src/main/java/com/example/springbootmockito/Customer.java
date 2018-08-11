package com.example.springbootmockito;

import lombok.Builder;
import lombok.Value;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by mtumilowicz on 2018-08-10.
 */
@Entity
@Value
@Builder
public class Customer {
    @Id
    Integer id;
    String firstName;
}
