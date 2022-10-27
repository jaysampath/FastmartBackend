package com.services.fastmart.service;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.springframework.stereotype.Service;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.services.fastmart.exception.OtpActionException;

@Service
public class OtpService {
	
	private static final Integer EXPIRE_MINS=2;
	
	private LoadingCache<String,Integer> otpCache;
	
	public OtpService() {
		otpCache = CacheBuilder.newBuilder().expireAfterWrite(EXPIRE_MINS, TimeUnit.MINUTES).build(new CacheLoader<String,Integer>(){
			public Integer load(String key) throws Exception {
				
				return 0;
			}
			
		});
	}
	
	public int generateOtp(String key) {
		Random random = new Random();
		int otp = 100000 + random.nextInt(900000);
		otpCache.put(key, otp);
		return otp;
		
	}
	
	public int getOtp(String key) {
		try {
			return otpCache.get(key);
		}catch(Exception e) {
			throw new OtpActionException(e.getMessage());
		}
	}
	
	public LoadingCache<String,Integer> getOtpCache() {
		return otpCache;
	}
	
	public void clearOtp(String key) {
		otpCache.invalidate(key);
	}

}
