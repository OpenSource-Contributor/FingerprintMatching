package com.hasee.fingerprint.controller;

import com.hasee.fingerprint.dto.FingerPrintMatchData;
import com.hasee.fingerprint.dto.ResponseDto;
import com.hasee.fingerprint.service.IFingerPrintService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FingerPrintController implements IFingerPrintController {
    @Autowired
    private IFingerPrintService fingerPrintService;

    @Override
    public ResponseEntity<ResponseDto<FingerPrintMatchData>> matchFingerPrints(String finger1Base64, String finger2Base64) {
        if (finger1Base64 == null || finger1Base64.isEmpty()) {
            return ResponseEntity.badRequest().body(new ResponseDto<>(-1, "Finger 1 missing"));
        }
        if (finger2Base64 == null || finger2Base64.isEmpty()) {
            return ResponseEntity.badRequest().body(new ResponseDto<>(-1, "Finger 2 missing"));
        }
        return ResponseEntity.ok(fingerPrintService.matchFingerPrints(finger1Base64, finger2Base64));
    }
}
