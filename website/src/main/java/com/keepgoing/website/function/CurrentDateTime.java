package com.keepgoing.website.function;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class CurrentDateTime {
	
	public static Date getCurrentTime() {
	
		
	// 현재 날짜/시간
    LocalDateTime localDateTime  = LocalDateTime.now();
    
    //Date 타입으로 형변환 (호환성 때문에 어쩔수없음.)
    Instant instant = localDateTime.atZone(ZoneId.systemDefault()).toInstant();
    Date date = Date.from(instant);
    
    
    
    
    
    
    

   
    
    return date;
    
    
    
    
    
    // 현재 날짜/시간 출력
    // System.out.println(now); // 2021-06-17T06:43:21.419878100
    // 포맷팅
    //String formatedNow = now.format(DateTimeFormatter.ofPattern("yyyy년 MM월 dd일 HH시 mm분 ss초"));

    // 포맷팅 현재 날짜/시간 출력
    //System.out.println(formatedNow);  // 2021년 06월 17일 06시 43분 21초
	}
}
