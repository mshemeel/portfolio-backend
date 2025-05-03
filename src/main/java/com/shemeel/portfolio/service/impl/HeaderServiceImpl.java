package com.shemeel.portfolio.service.impl;

import com.shemeel.portfolio.model.Header;
import com.shemeel.portfolio.repository.HeaderRepository;
import com.shemeel.portfolio.service.HeaderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class HeaderServiceImpl implements HeaderService {

    private final HeaderRepository headerRepository;

    @Override
    public Optional<Header> getHeader() {
        return headerRepository.findAll().stream().findFirst();
    }
} 