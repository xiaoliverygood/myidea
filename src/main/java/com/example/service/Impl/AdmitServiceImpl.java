package com.example.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.model.entity.Admit;
import com.example.service.AdmitService;
import com.example.mapper.AdmitMapper;
import org.springframework.stereotype.Service;
@Service
public class AdmitServiceImpl extends ServiceImpl<AdmitMapper, Admit>
    implements AdmitService{

}




