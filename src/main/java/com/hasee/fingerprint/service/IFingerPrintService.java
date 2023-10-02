package com.hasee.fingerprint.service;

import com.hasee.fingerprint.dto.FingerPrintMatchData;
import com.hasee.fingerprint.dto.ResponseDto;

public interface IFingerPrintService
{
	ResponseDto<FingerPrintMatchData> matchFingerPrints(String finger1Base64, String finger2Base64 );
}
