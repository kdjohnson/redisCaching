package edu.oakland;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.jdbc.core.JdbcTemplate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@Service
public class BannerDao implements IBannerDao {

  @Autowired
  JdbcTemplate jdbcTemplate;

  private final Logger logger = LoggerFactory.getLogger(getClass());

  @Cacheable(value="edu.oakland.BannerDao.getFirstName", key="{#root.methodName, #pidm}") 
  public List<String> getFirstName(String pidm) {
    try {
      List<String> d = jdbcTemplate.queryForList("", new Object[] {pidm}, String.class);
      return d;
    } catch(Exception e) {
      logger.error("{}", e);
      return null;
    }
  
  } 

}
