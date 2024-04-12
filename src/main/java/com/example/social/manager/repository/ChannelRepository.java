package com.example.social.manager.repository;

import com.example.social.manager.domain.Channel;
import com.example.social.manager.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ChannelRepository extends JpaRepository<Channel, Integer> {

}
