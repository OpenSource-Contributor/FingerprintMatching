package com.hasee.fingerprint.service;

import com.hasee.fingerprint.dto.ResponseDto;

public interface IFingerPrintService
{
	ResponseDto<Boolean> matchFingerPrints( String finger1Base64, String finger2Base64 );
}
