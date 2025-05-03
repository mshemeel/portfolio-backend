package com.shemeel.portfolio.service.impl;

import com.shemeel.portfolio.model.Profile;
import com.shemeel.portfolio.repository.ProfileRepository;
import com.shemeel.portfolio.service.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProfileServiceImpl implements ProfileService {

    private final ProfileRepository profileRepository;

    @Override
    public Optional<Profile> getProfile() {
        return profileRepository.findAll().stream().findFirst();
    }

    @Override
    public Optional<Profile> getProfileById(String id) {
        return profileRepository.findById(id);
    }

    @Override
    public List<Profile> getAllProfiles() {
        return profileRepository.findAll();
    }
} 