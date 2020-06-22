package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.Repository.FileRepo;
import com.example.demo.model.FileInfo;

@Component
@javax.transaction.Transactional
@Service
public class FileService {

	@Autowired
	FileRepo fileRepo;

	public void save(FileInfo fileInfo) {

		fileRepo.save(fileInfo);
	}

	public FileInfo getSingleInfo(int id) {

		FileInfo singleFileInfo = fileRepo.getOne(id);
		return singleFileInfo;

	}

	public List<FileInfo> getAll(int userId) {

		List<FileInfo> list = fileRepo.getAll(userId);
		return list;

	}

	public void deleteById(int id) {

		fileRepo.deleteById(id);
	}

	public void updatePercentage(int percent, int id) {

		fileRepo.updatePercent(percent, id);
	}

	public int getPercentage(int id) {

		Integer percent = fileRepo.getPercent(id);
		if (percent != null) {
			return percent;
		} else {
			return 0;
		}

	}
	
	public int getIdByName(String fileName) {

		Integer id = fileRepo.getIdByName(fileName);
		if (id != null) {
			return id;
		} else {
			return 0;
		}

	}

	public void updateStatus(int id,String status)
	{
		fileRepo.updateStatus(status, id);
	}
	
	public void updateFile(int id,byte[] fileByte)
	{
		fileRepo.updateFile(fileByte, id);
	}
	
}
