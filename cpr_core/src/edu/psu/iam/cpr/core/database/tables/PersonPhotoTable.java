/* SVN FILE: $Id: PersonPhotoTable.java 5340 2012-09-27 14:48:52Z jvuccolo $ */
package edu.psu.iam.cpr.core.database.tables;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import org.hibernate.Query;
import org.hibernate.Session;
import edu.psu.iam.cpr.core.database.Database;
import edu.psu.iam.cpr.core.database.beans.PersonPhoto;
import edu.psu.iam.cpr.core.service.returns.PhotoReturn;
import edu.psu.iam.cpr.core.util.Utility;

/**
 *  This class provides an implementation for interfacing with the Photo database
 * table.  There are methods within here to add and get a photo for a 
 * person in the CPR.
 *
 * Copyright 2012 The Pennsylvania State University
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * @package edu.psu.iam.cpr.core.database.tables
 * @author $Author: jvuccolo $
 * @version $Rev: 5340 $
 * @lastrevision $Date: 2012-09-27 10:48:52 -0400 (Thu, 27 Sep 2012) $
 */
public class PersonPhotoTable {

	/** Contains a reference to the person photo database bean */
	private PersonPhoto personPhotoBean;

	/**
	 * Constructor.
	 */
	public PersonPhotoTable() {
		super();
	}

	/**
	 * Constructor
	 * @param personId contains the person identifier from the CPR to the person who will be assigned the photo.
	 * @param photo contains the byte representation of the image.
	 * @param datePhotoTaken contains the date the photo was taken.
	 * @param updatedBy contains the identity that updated the record.
	 */
	public PersonPhotoTable(long personId, byte[] photo, Date datePhotoTaken, String updatedBy) {
		
		final PersonPhoto bean = new PersonPhoto();
		final Date d = new Date();
		
		bean.setPersonId(personId);
		bean.setPhoto(photo);
		bean.setDateTaken(datePhotoTaken);
		
		bean.setLastUpdateBy(updatedBy);
		bean.setLastUpdateOn(d);
		
		bean.setCreatedBy(updatedBy);
		bean.setCreatedOn(d);
		
		setPersonPhotoBean(bean);
	}
	
	/**
	 * @param personPhotoBean the personPhotoBean to set
	 */
	public final void setPersonPhotoBean(PersonPhoto personPhotoBean) {
		this.personPhotoBean = personPhotoBean;
	}

	/**
	 * @return the personPhotoBean
	 */
	public PersonPhoto getPersonPhotoBean() {
		return personPhotoBean;
	}
	
	/**
	 * This routine will be used to add a photo to the database.  If the photo already exists, it will be updated
	 * with the photo information contained in the database bean.
	 * @param db Contains a reference to a database connection.
	 */
	public void addPhoto(Database db) {
		
		final Session session = db.getSession();
		final PersonPhoto bean = getPersonPhotoBean();

		// Perform a query to determine if a photo already exists for the person.
		final String sqlQuery = "from PersonPhoto where personId = :person_id_in";
		final Query query = session.createQuery(sqlQuery);
		query.setParameter("person_id_in", bean.getPersonId());
		final Iterator<?> it = query.list().iterator();

		// Photo Exist?  Yes, do an update.
		if (it.hasNext()) {
			PersonPhoto dbBean = (PersonPhoto) it.next();
			dbBean.setDateTaken(bean.getDateTaken());
			dbBean.setPhoto(bean.getPhoto());
			dbBean.setLastUpdateBy(bean.getLastUpdateBy());
			dbBean.setLastUpdateOn(bean.getLastUpdateOn());
			session.update(dbBean);
			session.flush();
		}

		// Photo does not exist, so we need to do an add.
		else {
			session.save(bean);
			session.flush();
		}
	}
	
	/**
	 * This routine is used to obtain photo information from the database.  It will be executed as the result
	 * of a service call to the "GetPhoto" service.
	 * @param db contains a database reference.
	 * @param personId contains the person identifier whose photo is to be retrieved.
	 * @return will return an array of PhotoReturn objects if successful.
	 */
	public PhotoReturn[] getPhoto(Database db, long personId) {
	
		final ArrayList<PhotoReturn> results = new ArrayList<PhotoReturn>();
		final Session session = db.getSession();

		// Build the query string.
		final String sqlQuery = "from PersonPhoto where personId = :person_id_in";

		// Set up hibernate for the query, bind parameters.
		final Query query = session.createQuery(sqlQuery);
		query.setParameter("person_id_in", personId);

		// Perform the query.
		for (final Iterator<?> it = query.list().iterator(); it.hasNext(); ) {
			PersonPhoto personPhoto = (PersonPhoto) it.next();
			PhotoReturn photoReturn = new PhotoReturn();

			photoReturn.setPhoto(personPhoto.getPhoto());
			photoReturn.setDateTaken(Utility.convertTimestampToString(personPhoto.getDateTaken()));
			photoReturn.setLastUpdateBy(personPhoto.getLastUpdateBy());
			photoReturn.setLastUpdateOn(Utility.convertTimestampToString(personPhoto.getLastUpdateOn()));
			photoReturn.setCreatedBy(personPhoto.getCreatedBy());
			photoReturn.setCreatedOn(Utility.convertTimestampToString(personPhoto.getCreatedOn()));

			results.add(photoReturn);
		}

		// Check on the results.
		return results.toArray(new PhotoReturn[results.size()]);
	}
}
