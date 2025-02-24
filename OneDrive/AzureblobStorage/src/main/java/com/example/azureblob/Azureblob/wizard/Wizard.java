package com.example.azureblob.Azureblob.wizard;

import com.example.azureblob.Azureblob.Artifact.Artifact;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Wizard implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @OneToMany(cascade = {CascadeType.PERSIST,CascadeType.MERGE}, mappedBy = "owner")
    private List<Artifact> artifactList = new ArrayList<>();

    public Wizard() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Artifact> getArtifactList() {
        return artifactList;
    }

    public void setArtifactList(List<Artifact> artifactList) {
        this.artifactList = artifactList;
    }

    public void addArtifact(Artifact artifact) {
        artifact.setOwner(this);
        this.artifactList.add(artifact);
    }
}
