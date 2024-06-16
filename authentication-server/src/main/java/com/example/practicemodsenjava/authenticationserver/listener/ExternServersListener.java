package com.example.practicemodsenjava.authenticationserver.listener;

import com.example.practicemodsenjava.authenticationserver.repository.OrderIdRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ExternServersListener {
    private final OrderIdRepository recipientIdRepository;
}
