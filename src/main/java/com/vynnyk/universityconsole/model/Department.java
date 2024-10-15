package com.vynnyk.universityconsole.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "department")
public class Department {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;

	@OneToOne
	private Lector headOfDepartment;

	@ManyToMany(mappedBy = "departments", fetch = FetchType.EAGER)
	private List<Lector> lectors;
}

