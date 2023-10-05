package com.hasee.fingerprint.service;

import com.hasee.fingerprint.dto.FingerPrintMatchData;
import com.hasee.fingerprint.dto.ResponseDto;
import com.hasee.fingerprint.utility.ImageUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

@Service
public class FingerPrintService implements IFingerPrintService {
    @Autowired
    private FingerPrintMatcherService fingerPrintMatcherService;

    @Autowired
    private Environment environment;

    @Override
    public ResponseDto<FingerPrintMatchData> matchFingerPrints(String finger1Base64, String finger2Base64) {
        try {
            byte[] finger1 = ImageUtility.Base64ToByteArray(finger1Base64);
            byte[] finger2 = ImageUtility.Base64ToByteArray(finger2Base64);
            if (finger1 == null || finger2 == null) {
                return new ResponseDto<>(-1, "Invalid finger images");
            }
            Double score = fingerPrintMatcherService.TestFingerPrint(finger1, finger2);
            double threshold = Double.parseDouble((environment.getProperty("threshold")));

            Boolean isMatched = false;
            if (score >= threshold) {
                isMatched = Boolean.TRUE;
            }
            FingerPrintMatchData fingerPrintMatchData = new FingerPrintMatchData(score, threshold, isMatched);
            return new ResponseDto<>(1, fingerPrintMatchData, "Fingerprint verification success");
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseDto<>(-1, "Fingerprint verification failed");
        }
    }
}
