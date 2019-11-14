package com.oc.metier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.oc.dao.TopoRepository;
import com.oc.entities.Topo;
import com.oc.forms.TopoForm;

@Service
public class TopoService {
	
	@Autowired
	private TopoRepository topoRepository;
	
	public void saveTopoForm(TopoForm topoForm, BindingResult result) {
		
		Topo newTopo = new Topo();
		newTopo.setDescription(topoForm.getDescription());
		newTopo.setName(topoForm.getName());
		newTopo.setEdate(topoForm.getEdate());
		
		topoRepository.save(newTopo);
		
	}
		

}
