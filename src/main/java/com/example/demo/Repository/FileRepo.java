package com.example.demo.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.model.FileInfo;

public interface FileRepo extends JpaRepository<FileInfo, Integer> {

	@Query("SELECT f FROM FileInfo f  where f.userId = :userId")
	List<FileInfo> getAll(@Param("userId") int userId);
	
	 @Modifying
	@Query("UPDATE FileInfo  set percentage = :percent  where id = :id")
	void updatePercent(@Param("percent") int percent,@Param("id") int id);
	
	 @Modifying
	@Query("UPDATE FileInfo  set status = :status  where id = :id")
	void updateStatus(@Param("status") String status,@Param("id") int id);
	
	 @Modifying
	@Query("UPDATE FileInfo  set file = :fileByte  where id = :id")
	void updateFile(@Param("fileByte") byte[] fileByte,@Param("id") int id);
	
	@Query("SELECT percentage FROM FileInfo  where id = :id")
	Integer getPercent(@Param("id") int id);
	
	@Query("SELECT id FROM FileInfo  where fileName = :fileName")
	Integer getIdByName(@Param("fileName") String fileName);
	
}
