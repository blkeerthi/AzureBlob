package com.example.azureblob.Azureblob.Artifact;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ArtifactRepository extends JpaRepository<Artifact,String>, JpaSpecificationExecutor<Artifact> {
}
