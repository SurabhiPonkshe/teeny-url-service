package com.teenyurl.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.teenyurl.entity.UrlData;

@Repository
public interface UrlRepository extends JpaRepository<UrlData, Integer> {

	Optional<UrlData> findTopByOrderByIdDesc();
	}
