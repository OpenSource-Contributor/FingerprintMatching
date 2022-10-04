package com.hasee.fingerprint.controller;

import com.hasee.fingerprint.dto.ResponseDto;
import org.springframework.http.ResponseEntity;

public interface IFingerPrintController
{
	ResponseEntity<ResponseDto<Boolean>> matchFingerPrints( String finger1Base64, String finger2Base64 );
}
