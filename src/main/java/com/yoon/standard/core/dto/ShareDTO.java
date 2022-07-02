package com.yoon.standard.core.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
public class ShareDTO {
	
	boolean isOk = true;
	String message = "SUCCESS";
	
	public ShareDTO(boolean isOk, String message) {
		this.isOk = isOk;
		this.message = message;
	}
}
