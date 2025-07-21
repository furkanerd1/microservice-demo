package com.kitaplik.library_service.repository;


import com.kitaplik.library_service.model.entity.Library;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface LibraryRepository extends JpaRepository<Library, UUID> {
}
