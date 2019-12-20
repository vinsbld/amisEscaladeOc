package com.oc.metier;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.oc.dao.SiteEscaladeRepository;
import com.oc.entities.SiteEscalade;

@Service("métier")
public class Metier implements IMetier {
	
	// injections repositories
	@Autowired
	private SiteEscaladeRepository siteEscaladeRepository;
	
	// interface implémentation
	/* ================== #Sites escalades ================== */
	@Override
	public List<SiteEscalade> getAllSites() {
		return Lists.newArrayList(siteEscaladeRepository.findAll());
	}

	@Override
	public List<SiteEscalade> findByUserGrimp(Long idUserGrimp) {
		return Lists.newArrayList(siteEscaladeRepository.findByUserGrimp(idUserGrimp));
	}

}
