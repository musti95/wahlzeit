/*
 * Copyright (c) 2006-2009 by Dirk Riehle, http://dirkriehle.com
 *
 * This file is part of the Wahlzeit photo rating application.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public
 * License along with this program. If not, see
 * <http://www.gnu.org/licenses/>.
 */

package org.wahlzeit.services;

import com.google.appengine.api.datastore.Email;
import junit.framework.TestCase;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

/**
 * Test cases for the EmailAddress class.
 */
public class EmailAddressTest extends TestCase {

	/**
	 *
	 */
	public EmailAddressTest(String name) {
		super(name);
	}

	/**
	 *
	 */
	public void testGetEmailAddressFromString() {
		// invalid email addresses are allowed for local testing and online avoided by Google

		assertTrue(createEmailAddressIgnoreException("bingo@bongo"));
		assertTrue(createEmailAddressIgnoreException("bingo@bongo.com"));
		assertTrue(createEmailAddressIgnoreException("bingo.bongo@bongo.com"));
		assertTrue(createEmailAddressIgnoreException("bingo+bongo@bango"));
	}

	/**
	 *
	 */
	protected boolean createEmailAddressIgnoreException(String ea) {
		try {
			EmailAddress.getFromString(ea);
			return true;
		} catch (IllegalArgumentException ex) {
			// creation failed
			return false;
		}
	}

	/**
	 *
	 */
	public void testEmptyEmailAddress() {
		assertFalse(EmailAddress.EMPTY.isValid());
	}

	public void testAsInternetAddress() {
		InternetAddress fromEmail1 = EmailAddress.getFromString("bingo@bongo.com").asInternetAddress();
		InternetAddress fromEmail2 = EmailAddress.getFromString("bingo.bongo@bongo.com").asInternetAddress();
		InternetAddress fromEmail3 = EmailAddress.getFromString("bingo+bongo@bango").asInternetAddress();
		InternetAddress fromEmail4 = EmailAddress.getFromString("bingo@bongo").asInternetAddress();
		InternetAddress inetAddr1 = null;
		InternetAddress inetAddr2 = null;
		InternetAddress inetAddr3 = null;
		InternetAddress inetAddr4 = null;
		try {
			inetAddr1 = new InternetAddress("bingo@bongo.com");
			inetAddr2 = new InternetAddress("bingo.bongo@bongo.com");
			inetAddr3 = new InternetAddress("bingo+bongo@bango");
			inetAddr4 = new InternetAddress("bingo@bongo");
		} catch (AddressException e){
			//should not happen
		}
		assertEquals(fromEmail1, inetAddr1);
		assertEquals(fromEmail2, inetAddr2);
		assertEquals(fromEmail3, inetAddr3);
		assertEquals(fromEmail4, inetAddr4);
	}

	public void testValueObject() {
		EmailAddress mail1 = EmailAddress.getFromString("bingo@bongo.com");
		EmailAddress mail2 = EmailAddress.getFromString("bingo.bongo@bongo.com");
		EmailAddress mail3 = EmailAddress.getFromString("bingo@bongo.com");
		assertNotSame(mail1, mail2);
		assertNotSame(mail3, mail2);
		assertSame(mail1, mail3);
	}

}

