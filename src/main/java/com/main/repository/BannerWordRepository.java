package com.main.repository;

import com.main.entity.BannerWords;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BannerWordRepository extends JpaRepository<BannerWords, Integer> {
}
