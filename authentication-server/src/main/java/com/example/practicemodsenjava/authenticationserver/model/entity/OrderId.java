package com.example.practicemodsenjava.authenticationserver.model.entity;

import by.practicemodsenjava.authenticationserver.listener.OrderIdListener;
import jakarta.persistence.EntityListeners;

@EntityListeners(OrderIdListener.class)
public class OrderId {

}
