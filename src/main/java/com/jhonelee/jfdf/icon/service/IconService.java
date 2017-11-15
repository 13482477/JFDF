package com.jhonelee.jfdf.icon.service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jhonelee.jfdf.icon.entity.Icon;
import com.jhonelee.jfdf.icon.entity.IconType;
import com.jhonelee.jfdf.icon.repository.IconRepository;

@Service
public class IconService {
	
	@Autowired
	private IconRepository iconRepository;
	
	@Transactional
	public Icon save(IconType iconType, String subGroup, String name, String value) {
		Icon icon = new Icon();
		icon.setIconType(iconType);
		icon.setSubGroup(subGroup);
		icon.setName(name);
		icon.setValue(value);
		return this.iconRepository.save(icon);
	}
	
	public LinkedHashMap<String, List<Icon>> findFontAwesomeGroupBySubGroup() {
		List<Icon> icons = this.iconRepository.findByIconType(IconType.FONT_AWESOME);
		LinkedHashMap<String, List<Icon>> result = new LinkedHashMap<String, List<Icon>>();
		
		for (Icon icon : icons) {
			if (result.get(icon.getSubGroup()) == null) {
				List<Icon> iconList = new ArrayList<Icon>();
				result.put(icon.getSubGroup(), iconList);
			}
			result.get(icon.getSubGroup()).add(icon);
			
		}
		
		return result;
	}
	
	public List<Icon> findGlyphicons() {
		return this.iconRepository.findByIconType(IconType.GLYPHICON);
	}

}
