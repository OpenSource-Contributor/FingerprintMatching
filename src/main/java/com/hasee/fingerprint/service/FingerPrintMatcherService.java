package com.hasee.fingerprint.service;

import com.hasee.fingerprint.model.Person;
import com.machinezoo.sourceafis.FingerprintImage;
import com.machinezoo.sourceafis.FingerprintMatcher;
import com.machinezoo.sourceafis.FingerprintTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FingerPrintMatcherService
{
	public double TestFingerPrint(byte[] probeImage, byte[] candidateImage) {
		FingerprintTemplate probe = new FingerprintTemplate().dpi( 500 ).create( probeImage );
		FingerprintTemplate candidate = new FingerprintTemplate().dpi( 500 ).create( candidateImage );
		double score = new FingerprintMatcher()
				.index(probe)
				.match(candidate);
		return score;
	}

	public Person TestFingerPrintOneToMany(byte[] probeImage, List<Person> persons) {
		FingerprintTemplate probe = new FingerprintTemplate(
				new FingerprintImage()
						.dpi(500)
						.decode(probeImage));
		FingerprintMatcher matcher = new FingerprintMatcher()
				.index(probe);
		Person match = null;
		double high = 0;
		for (Person person : persons) {
			FingerprintTemplate candidate = new FingerprintTemplate(
					new FingerprintImage()
							.dpi(500)
							.decode(person.getFingerprintImage()));
			double score = matcher.match(candidate);
			if (score > high) {
				high = score;
				match = person;
			}
		}
		double threshold = 40;
		return high >= threshold ? match : null;
	}
}
