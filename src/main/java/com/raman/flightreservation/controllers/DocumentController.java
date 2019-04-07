package com.raman.flightreservation.controllers;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import com.raman.flightreservation.entities.Document;
import com.raman.flightreservation.repository.DocumentRepository;

@Controller
public class DocumentController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(DocumentController.class);
	
	@Autowired
	DocumentRepository repository;

	@RequestMapping(value = "/displayUpload", method = RequestMethod.GET)
	public String displayUpload(ModelMap modelMap) {
		fetchDocuments(modelMap);
		return "documentUpload";
	}

	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public String uploadDocument(@RequestParam("document") MultipartFile multipartFile, @RequestParam("id") long id, ModelMap modelMap) {
		Document document = new Document();
		document.setId(id);
		document.setName(multipartFile.getOriginalFilename());
		try {
			document.setData(multipartFile.getBytes());
		} catch (IOException e) {
			LOGGER.error("Error while uploading document: ", e);
		}
		repository.save(document);
		fetchDocuments(modelMap);
		return "documentUpload";
	}
	
	@RequestMapping("/download")
	public StreamingResponseBody download(@RequestParam("id") long id, HttpServletResponse response) {
		Document document = repository.findById(id).orElseThrow(EntityNotFoundException::new);
		byte[] data = document.getData();
		response.setHeader("content-Disposition", "attachment;filename=downloaded.jpeg");
		return outputStream -> outputStream.write(data);
	}
	
	private void fetchDocuments(ModelMap modelMap) {
		List<Document> documents = repository.findAll();
		LOGGER.info("Size of documents: {}", documents.size());
		modelMap.addAttribute("documents", documents);
	}

}
