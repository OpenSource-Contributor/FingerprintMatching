package com.hasee.fingerprint;

import com.hasee.fingerprint.model.Person;
import com.hasee.fingerprint.service.FingerPrintMatcherService;
import com.machinezoo.sourceafis.FingerprintTemplate;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class FingerPrintMatcherTest
{
	@Test
	public void testFingerPrintOneToOne() throws IOException
	{
		byte[] probeImage = Files.readAllBytes( Paths.get("D:\\Special Docs\\FP\\probe.jpg"));
		byte[] candidateImage = Files.readAllBytes(Paths.get("D:\\Special Docs\\FP\\candidate.jpg"));

		FingerprintTemplate probe = new FingerprintTemplate().dpi( 500 ).create( probeImage );

		FingerPrintMatcherService fingerPrintMatcherService = new FingerPrintMatcherService();
		double score = fingerPrintMatcherService.TestFingerPrint( probeImage, candidateImage );
		if (score > 40) {
			System.out.println( "Fingerprint Matched. (Accuracy - " + score + " )" );
		} else {
			System.out.println( "Fingerprint Not Matched" );
		}
	}

	@Test
	public void testFingerPrintOneToMany() throws IOException
	{
		byte[] probeImage = Files.readAllBytes( Paths.get("D:\\fingerprint\\probe.jpg"));
		List<Person> personList = new ArrayList<>();

		//Person 1
		Person person1 = new Person();
		person1.setId( 1 );
		person1.setName( "Kalana" );
		person1.setFingerprintImage( Files.readAllBytes(Paths.get("D:\\fingerprint\\fingers\\kalana.png")) );
		personList.add( person1 );

		//Person 2
		Person person2 = new Person();
		person2.setId( 2 );
		person2.setName( "Chamith" );
		person2.setFingerprintImage( Files.readAllBytes(Paths.get("D:\\fingerprint\\fingers\\chamith.jpg")) );
		personList.add( person2 );

		//Person 3
		Person person3 = new Person();
		person3.setId( 3 );
		person3.setName( "Madhawa" );
		person3.setFingerprintImage( Files.readAllBytes(Paths.get("D:\\fingerprint\\fingers\\madhawa.jpg")) );
		personList.add( person3 );

		FingerPrintMatcherService fingerPrintMatcherService = new FingerPrintMatcherService();
		Person person = fingerPrintMatcherService.TestFingerPrintOneToMany( probeImage, personList );
		if (person != null) {
			System.out.println( "Fingerprint Matched with " + person.getName() );
		} else {
			System.out.println( "Fingerprint not Matched with any person" );
		}
	}
}