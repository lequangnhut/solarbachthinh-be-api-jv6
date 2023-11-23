package com.main.service.impl;

import com.main.entity.BannerWords;
import com.main.repository.BannerWordRepository;
import com.main.service.BannerWordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BannerWordServiceImpl implements BannerWordService {
    @Autowired
    BannerWordRepository bannerWordRepository;
    @Override
    public List<BannerWords> findAll() {
        return bannerWordRepository.findAll();
    }
}
