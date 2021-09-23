package com.yenmin.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.convert.CustomConversions;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.convert.DbRefResolver;
import org.springframework.data.mongodb.core.convert.DefaultDbRefResolver;
import org.springframework.data.mongodb.core.convert.DefaultMongoTypeMapper;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;


@Configuration
public class MongoDBConfig {
	private static final Logger LOG = LoggerFactory.getLogger(MongoDBConfig.class);
	
	@Bean
	public MappingMongoConverter mappingMongoConverter(MongoDatabaseFactory mongoDbFactory,
			MongoMappingContext mongoMappingContext) {
		DbRefResolver dbRefResolver = new DefaultDbRefResolver(mongoDbFactory);
		MappingMongoConverter converter = new MappingMongoConverter(dbRefResolver, mongoMappingContext);
		converter.setTypeMapper(new DefaultMongoTypeMapper(null));

		return converter;
	}

	@Bean
	public MappingMongoConverter mappingMongoConverter(MongoDatabaseFactory factory, MongoMappingContext context,
			BeanFactory beanFactory) {
		DbRefResolver dbRefResolver = new DefaultDbRefResolver(factory);
		MappingMongoConverter mappingConverter = new MappingMongoConverter(dbRefResolver, context);
		try {
			mappingConverter.setCustomConversions(beanFactory.getBean(CustomConversions.class));
		} catch (NoSuchBeanDefinitionException ignore) {
			LOG.debug("NoSuchBeanDefinitionException Occurred");
		}
		mappingConverter.setTypeMapper(new DefaultMongoTypeMapper(null));
		return mappingConverter;
	}

	@Bean
	public MongoTemplate mongoTemplate(@Autowired MongoDatabaseFactory mongoDbFactory,
			@Autowired MappingMongoConverter mappingMongoConverter) {
		return new MongoTemplate(mongoDbFactory, mappingMongoConverter);
	}
}
