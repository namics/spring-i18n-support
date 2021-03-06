/*
 * Copyright 2000-2014 Namics AG. All rights reserved.
 */

package com.namics.oss.spring.support.i18n.config;

import com.namics.oss.spring.support.i18n.DaoMessageSource;
import com.namics.oss.spring.support.i18n.dao.MessageSourceDao;
import com.namics.oss.spring.support.i18n.dao.MessageSourceManagementDao;
import com.namics.oss.spring.support.i18n.excel.ExcelReader;
import com.namics.oss.spring.support.i18n.excel.ExcelWriter;
import com.namics.oss.spring.support.i18n.service.MessageManagementService;
import com.namics.oss.spring.support.i18n.service.MessageManagementServiceImpl;
import com.namics.oss.spring.support.i18n.text.SqlScriptWriter;
import com.namics.oss.spring.support.i18n.text.TextWriter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;
import org.springframework.jmx.export.annotation.AnnotationMBeanExporter;

/**
 * I18nConfigTemplate.
 *
 * @author aschaefer, Namics AG
 * @since 18.03.14 16:58
 */
public class I18nConfigTemplate {
	@Value("${classpath*:/templates/template.sql}")
	protected Resource scriptTemplate;

	@Bean
	public AnnotationMBeanExporter annotationMBeanExporter() {
		return new AnnotationMBeanExporter();
	}

	@Bean
	public ExcelReader excelReader() {
		return new ExcelReader();
	}

	@Bean
	public ExcelWriter excelWriter() {
		return new ExcelWriter();
	}

	@Bean
	public TextWriter textWriter() {
		return new SqlScriptWriter().scriptTemplate(scriptTemplate);
	}

	@Bean
	public DaoMessageSource i18nMessageSource(MessageSourceDao messageSourceDao) {
		DaoMessageSource source = new DaoMessageSource();
		source.setMessageSourceDao(messageSourceDao);
		return source;
	}

	@Bean
	public MessageManagementService messageManagementService(MessageSourceManagementDao messageSourceDao) {
		MessageManagementServiceImpl service = new MessageManagementServiceImpl();
		service.setExcelReader(excelReader());
		service.setExcelWriter(excelWriter());
		service.setTextWriter(textWriter());
		service.setMessageSourceDao(messageSourceDao);
		return service;
	}
}
