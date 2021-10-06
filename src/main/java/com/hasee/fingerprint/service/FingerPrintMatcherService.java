package com.hasee.fingerprint.service;

import com.hasee.fingerprint.model.Person;
import com.machinezoo.sourceafis.FingerprintImage;
import com.machinezoo.sourceafis.FingerprintMatcher;
import com.machinezoo.sourceafis.FingerprintTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FingerPrintMatcherService
{
	@Autowired
	private Environment environment;

	public double TestFingerPrint(byte[] probeImage, byte[] candidateImage) {
		Integer dpi = Integer.parseInt( environment.getProperty( "fp.dpi.value") );
		FingerprintTemplate probe = new FingerprintTemplate().dpi( dpi ).create( probeImage );
		FingerprintTemplate candidate = new FingerprintTemplate().dpi( dpi ).create( candidateImage );
		double score = new FingerprintMatcher()
				.index(probe)
				.match(candidate);
		return score;
	}

	public Person TestFingerPrintOneToMany(byte[] probeImage, List<Person> persons) {
		Integer dpi = Integer.parseInt( environment.getProperty( "fp.dpi.value") );
		FingerprintTemplate probe = new FingerprintTemplate(
				new FingerprintImage()
						.dpi(dpi)
						.decode(probeImage));
		FingerprintMatcher matcher = new FingerprintMatcher()
				.index(probe);
		Person match = null;
		double high = 0;
		for (Person person : persons) {
			FingerprintTemplate candidate = new FingerprintTemplate(
					new FingerprintImage()
							.dpi(dpi)
							.decode(person.getFingerprintImage()));
			double score = matcher.match(candidate);
			if (score > high) {
				high = score;
				match = person;
			}
		}
		double threshold = Double.parseDouble( ( environment.getProperty( "threshold") ) );;
		return high >= threshold ? match : null;
	}
}
