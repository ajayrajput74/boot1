package com.shared.DaoImpl;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import com.shared.Dao.HomeForRentDao;

@Repository
@Transactional
public class HomeForRentDaoImpl implements HomeForRentDao {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	SessionFactory sessionFactory;

	@Override
	public Boolean getRegisterUserHomeDetail(String address, Integer postalCode, BigDecimal latitude,
			BigDecimal longitude, String city, BigInteger phoneNumber, String landmark, List<MultipartFile> file,
			String state) {

		try {

			logger.info("address " + address, "postalCode " + postalCode, "latitude " + latitude,
					"longitude " + longitude, "phoneNumber " + phoneNumber, "landmark " + landmark, "file " + file,
					"city " + city, "state " + state);

			String Sql = "";

			Session session = sessionFactory.getCurrentSession();

			Query query = session.createSQLQuery(Sql);
			query.setParameter(0, postalCode);
			query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
			query.executeUpdate();

			return true;

		} catch (Exception e) {
			logger.info("error of register User Home Detail " + e);
			logger.info("message of register User Home Detail " + e);
		}

		return false;
	}

}
