package com.jhonelee.jfdf.menu.controller;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jhonelee.jfdf.icon.entity.Icon;
import com.jhonelee.jfdf.icon.service.IconService;
import com.jhonelee.jfdf.menu.dto.MenuDto;
import com.jhonelee.jfdf.menu.dto.MenuTreeNode;
import com.jhonelee.jfdf.menu.entity.Menu;
import com.jhonelee.jfdf.menu.repository.MenuRepository;
import com.jhonelee.jfdf.menu.service.MenuService;
import com.jhonelee.jfdf.web.convert.ConvertUtils;

@Controller
public class MenuController {

	@Autowired
	private MenuService menuService;

	@Autowired
	private MenuRepository menuRepository;

	@Autowired
	private IconService iconService;

	@InitBinder
	public void InitBinder(WebDataBinder binder) {

	}

	@RequestMapping(value = "/menu/page", method = RequestMethod.GET)
	public String resource(Model model) {

		LinkedHashMap<String, List<Icon>> fontAwesomeMap = this.iconService.findFontAwesomeGroupBySubGroup();
		List<Icon> glyphicons = this.iconService.findGlyphicons();

		model.addAttribute("fontAwesomeMap", fontAwesomeMap);
		model.addAttribute("glyphicons", glyphicons);

		return "menu/menu";
	}

	@RequestMapping(value = "/menu/{parentId}/children", method = RequestMethod.GET)
	@ResponseBody
	public List<MenuTreeNode> loadChildren(@PathVariable("parentId") Long parentId) {
		List<Menu> children = this.menuService.loadByParentId(parentId);

		List<MenuTreeNode> result = new ArrayList<MenuTreeNode>();

		CollectionUtils.collect(children, input -> {
			MenuTreeNode menuTreeNode = new MenuTreeNode();
			menuTreeNode.setId(input.getId());
			menuTreeNode.setName(input.getName());
			menuTreeNode.setIsParent(input.getChildren().size() > 0);
			menuTreeNode.setUrl(input.getUrl());
			menuTreeNode.setCode(input.getMenuCode());
			menuTreeNode.setIconPath(input.getIconPath());
			menuTreeNode.setParentId(input.getParent() == null ? null : input.getParent().getId());
			return menuTreeNode;
		}, result);

		return result;
	}

	@RequestMapping(value = "/menu/{id}", method = RequestMethod.GET)
	@ResponseBody
	public MenuDto read(@PathVariable Long id) {
		Menu menu = this.menuRepository.findOne(id);

		return ConvertUtils.convert(menu, source -> {
			MenuDto menuDto = new MenuDto();
			menuDto.setId(source.getId());
			menuDto.setName(source.getName());
			menuDto.setMenuCode(source.getMenuCode());
			menuDto.setSystemId(source.getSystemId());
			menuDto.setUrl(source.getUrl());
			menuDto.setSequence(source.getSequence());
			;
			menuDto.setIconPath(source.getIconPath());
			return menuDto;
		});
	}

	@RequestMapping(value = "/menu", method = RequestMethod.POST)
	@ResponseBody
	public MenuDto create(@RequestBody Menu menu) {
		this.menuService.saveAndUpdate(menu);
		return ConvertUtils.convert(menu, source -> {
			MenuDto menuDto = new MenuDto();
			menuDto.setId(source.getId());
			menuDto.setName(source.getName());
			menuDto.setMenuCode(source.getMenuCode());
			menuDto.setSystemId(source.getSystemId());
			menuDto.setUrl(source.getUrl());
			menuDto.setSequence(source.getSequence());
			menuDto.setIconPath(source.getIconPath());
			return menuDto;
		});
	}

}