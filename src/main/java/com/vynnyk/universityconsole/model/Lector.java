package com.vynnyk.universityconsole.model;

import com.vynnyk.universityconsole.model.enumetarion.Degree;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "lector")
public class Lector {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;

	@Enumerated(EnumType.STRING)
	private Degree degree;

	private double salary;

	@ManyToMany
	@JoinTable(
			name = "lector_department",
			joinColumns = @JoinColumn(name = "lector_id"),
			inverseJoinColumns = @JoinColumn(name = "department_id"))
	private List<Department> departments;
}
