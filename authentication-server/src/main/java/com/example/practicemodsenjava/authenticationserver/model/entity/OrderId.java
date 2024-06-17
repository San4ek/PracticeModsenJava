package com.example.practicemodsenjava.authenticationserver.model.entity;

import com.example.practicemodsenjava.authenticationserver.listener.OrderIdListener;
import jakarta.persistence.EntityListeners;

@EntityListeners(OrderIdListener.class)
public class OrderId {

}
