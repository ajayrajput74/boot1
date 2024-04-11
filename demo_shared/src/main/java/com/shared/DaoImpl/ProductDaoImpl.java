package com.shared.DaoImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.hibernate.transform.Transformers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.shared.Dao.ProductDao;
import com.shared.Dto.ProductDto;
import com.shared.request.ProductRequest;

@Transactional
@Repository("productDaoImpl")
@Component
public class ProductDaoImpl implements ProductDao {

	@Autowired
	SessionFactory sessionFactory;

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	public List<ProductDto> getProduct(Integer productId) {

		logger.info(" Product Dao get " + productId);

		Session session = sessionFactory.getCurrentSession();

		try {
			String sql = "SELECT * from product ";
			Query query = session.createSQLQuery(sql);
			// query.setParameter("productId", productId);
			query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
			logger.info(" Product Sql Query " + sql);
			List<Object> rows = query.getResultList();
			List<ProductDto> ProductList = new ArrayList<>();

			for (Object result : rows) {

				ProductDto productDto = new ProductDto();
				Map<String, Object> map = (Map<String, Object>) result;

				productDto.setProductId((Integer) map.get("productid"));
				productDto.setProductName((String) map.get("productName"));
				productDto.setProductCost((Integer) map.get("cost"));
				productDto.setProductMargin((Integer) map.get("margin"));

				ProductList.add(productDto);
			}
			return ProductList;

		} catch (Exception ae) {
			ae.getStackTrace();
		} finally {
			session.flush();
			session.clear();
		}
		return null;
	}

	@Override
	public Boolean createProduct(ProductRequest productRequest) {

		Session session = sessionFactory.getCurrentSession();

		String strQuery = "insert into product (productName,cost,margin) values (:productName,:cost,\n" + ":margin)";
		try {

			Query query = session.createSQLQuery(strQuery);
			query.setParameter("productName", productRequest.getProductName());
			query.setParameter("cost", productRequest.getProductCost());
			query.setParameter("margin", productRequest.getProductTotal());

			query.executeUpdate();
			return true;

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("response Body " + e.getMessage());
		} finally {
			session.flush();
			session.clear();
		}
		return false;
	}

	@Override
	public Boolean deleteProduct(Integer productId) {

		Session session = sessionFactory.getCurrentSession();

		String strQuery = "Delete From product Where productid = :productId";

		try {
			Query query = session.createSQLQuery(strQuery);
			query.setParameter("productId", productId);

			query.executeUpdate();
			return true;

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("response Body " + e.getMessage());
		} finally {
			session.flush();
			session.clear();
		}
		return false;
	}

	@Override
	public Boolean updateProduct(ProductRequest productRequest) {

		Session session = sessionFactory.getCurrentSession();

		String strQuery = "update product set productName = :product_name, cost = :cost,margin = :margin Where productid = :productid";
		try {

			Query query = session.createSQLQuery(strQuery);
			query.setParameter("productid", productRequest.getProductId());
			query.setParameter("product_name", productRequest.getProductName());
			query.setParameter("cost", productRequest.getProductCost());
			query.setParameter("margin", productRequest.getProductTotal());

			query.executeUpdate();
			return true;

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("response Body " + e.getMessage());
		} finally {
			session.flush();
			session.clear();
		}
		return false;

	}

	@SuppressWarnings("unchecked")
	@Override
	public ProductDto getProductById(Integer productId) {

		logger.info(" Product Dao get " + productId);

		Session session = sessionFactory.getCurrentSession();

		try {
			String sql = "SELECT * from product Where productid =:productId ";
			Query query = session.createSQLQuery(sql);
			query.setParameter("productId", productId);
			query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
			logger.info(" Product Sql Query " + sql);

			ProductDto ProductDTO = null;
			Object rows = query.getSingleResult();

			System.out.println(" rows " + rows);

			Map<String, Object> map = (Map<String, Object>) rows;

			ProductDTO = new ProductDto();

			ProductDTO.setProductId((Integer) map.get("productid"));
			ProductDTO.setProductName((String) map.get("productName"));
			ProductDTO.setProductCost((Integer) map.get("cost"));
			ProductDTO.setProductMargin((Integer) map.get("margin"));

			return ProductDTO;

		} catch (Exception ae) {
			ae.getStackTrace();
		} finally {
			session.flush();
			session.clear();
		}
		return null;

	}
}
