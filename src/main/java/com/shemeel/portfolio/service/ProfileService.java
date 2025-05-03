package com.shemeel.portfolio.service;

import com.shemeel.portfolio.model.Profile;

import java.util.List;
import java.util.Optional;

public interface ProfileService {
    Optional<Profile> getProfile();
    Optional<Profile> getProfileById(String id);
    List<Profile> getAllProfiles();
} 