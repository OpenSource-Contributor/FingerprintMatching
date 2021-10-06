package com.hasee.fingerprint.model;

public class Person {
	int id;
	String name;
	byte[] fingerprintImage;

	public int getId()
	{
		return id;
	}

	public void setId( int id )
	{
		this.id = id;
	}

	public String getName()
	{
		return name;
	}

	public void setName( String name )
	{
		this.name = name;
	}

	public byte[] getFingerprintImage()
	{
		return fingerprintImage;
	}

	public void setFingerprintImage( byte[] fingerprintImage )
	{
		this.fingerprintImage = fingerprintImage;
	}
}