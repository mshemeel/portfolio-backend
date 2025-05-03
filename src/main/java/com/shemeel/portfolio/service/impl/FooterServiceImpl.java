package com.shemeel.portfolio.service.impl;

import com.shemeel.portfolio.model.Footer;
import com.shemeel.portfolio.repository.FooterRepository;
import com.shemeel.portfolio.service.FooterService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FooterServiceImpl implements FooterService {

    private final FooterRepository footerRepository;

    @Override
    public Optional<Footer> getFooter() {
        return footerRepository.findAll().stream().findFirst();
    }
} 